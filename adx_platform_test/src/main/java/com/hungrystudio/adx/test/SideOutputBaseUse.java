package com.hungrystudio.adx.test;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.api.common.functions.AggregateFunction;

public class SideOutputBaseUse {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Tuple2<String, String>> inputStream = env.fromElements(
                Tuple2.of("user1", "page1"),
                Tuple2.of("user2", "page2"),
                Tuple2.of("user2", "page2"),
                Tuple2.of("user3", "page1"),
                Tuple2.of("user1", "page1")
        );

        // 为流分配时间戳和水印
        // 假设我们使用处理时间（ProcessingTime）或事件时间（EventTime）
        DataStream<Tuple2<String, String>> timestampedStream = inputStream
                .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<Tuple2<String, String>>(Time.seconds(5)) {
                    @Override
                    public long extractTimestamp(Tuple2<String, String> element) {
                        // 如果没有事件时间字段，需要使用系统时间，否则可以从事件中提取
                        return System.currentTimeMillis();  // 使用系统时间，模拟处理时间
                    }
                });

        OutputTag<Tuple2<String, Long>> aggregatedOutputTag = new OutputTag<Tuple2<String, Long>>("aggregated-output") {
        };
        SingleOutputStreamOperator<Tuple2<String, String>> mainStream = timestampedStream.process(
                new ProcessFunction<Tuple2<String, String>, Tuple2<String, String>>() {
                    @Override
                    public void processElement(Tuple2<String, String> value, Context ctx, Collector<Tuple2<String, String>> out) throws Exception {
                        // 明细流输出
                        out.collect(value);
                        // 这里没有进行实际聚合，只是分发数据到 Side Output
                        ctx.output(aggregatedOutputTag, Tuple2.of(value.f1, 1L));  // 使用 1 代表每个事件作为一个计数
                    }
                });


        // 从 Side Output 获取聚合数据流
        DataStream<Tuple2<String, Long>> aggregatedStream = mainStream.getSideOutput(aggregatedOutputTag)
                .keyBy(0)  // 按照 pageId 聚合
                .timeWindow(Time.seconds(10))  // 每 10 秒一个窗口
                .aggregate(new AggregateFunction<Tuple2<String, Long>, Tuple2<String, Long>, Tuple2<String, Long>>() {

                    @Override
                    public Tuple2<String, Long> createAccumulator() {
                        return Tuple2.of("", 0L);
                    }

                    @Override
                    public Tuple2<String, Long> add(Tuple2<String, Long> value, Tuple2<String, Long> accumulator) {
                        return Tuple2.of(value.f0, accumulator.f1 + value.f1);
                    }

                    @Override
                    public Tuple2<String, Long> getResult(Tuple2<String, Long> accumulator) {
                        // 这里返回一个包含平均值的 Tuple2 对象，这里是将累加器中的元素除以2，然后返回一个新元素。
                        return Tuple2.of(accumulator.f0, accumulator.f1);
                    }

                    @Override
                    public Tuple2<String, Long> merge(Tuple2<String, Long> a, Tuple2<String, Long> b) {
                        // 这里是将两个累加器中的元素相加并除以2，然后返回一个新的元素对。
                        return Tuple2.of(a.f0, a.f1 + b.f1);
                    }
                });

        // 明细流输出到一个 Sink
        mainStream.print("Detail Output");

        // 聚合流输出到另一个 Sink
        aggregatedStream.print("Aggregated Output");

        env.execute("test side output");
    }
}
