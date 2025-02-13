package com.hungrystudio.adx.test;

import com.alibaba.fastjson.JSONObject;
import com.hungrystudio.adx.operator.S3ForRequestFlatMap;
import com.hungrystudio.adx.sink.S3DbSink;
import com.hungrystudio.adx.util.KafkaUtil;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class KafkaToParquet {
    public static void main(String[] args) throws Exception {



        String server = "b-1.shangyehuakafka1214.wimmal.c3.kafka.us-east-2.amazonaws.com:9094," +
                "b-2.shangyehuakafka1214.wimmal.c3.kafka.us-east-2.amazonaws.com:9094," +
                "b-3.shangyehuakafka1214.wimmal.c3.kafka.us-east-2.amazonaws.com:9094";
        String topic = "topic_adx_request";
        String groupId = "shucang_02";

        // S3 参数
        JSONObject paramsJson = new JSONObject();
//        paramsJson.put("bucket", "s3://hungry-studio-data-warehouse/");
//        paramsJson.put("target", "user/sunlj/adx/data/click/dt=%s/hour=%s/group_id=%s/");
//        paramsJson.put("wait_min", "10");

        paramsJson.put("bucket", "/Users/admin/Downloads/");
        paramsJson.put("target", "test/dt=%s/hour=%s/group_id=%s/");
        paramsJson.put("logType", "AdxRequest");

        // Flink 环境配置
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Kafka 消费者
//        KafkaSource<String> kafkaSource = KafkaUtil.getkafkaSource("msk",server, groupId, topic,"AdxRequest",true);
        KafkaSource<String> kafkaSource = KafkaUtil.getkafkaSource("msk",server, groupId, topic,"AdxRequest",true,1L);

        env.fromSource(kafkaSource, WatermarkStrategy.noWatermarks(), "KafkaSource")
                .setParallelism(2)
                .flatMap(new S3ForRequestFlatMap(paramsJson))
                .setParallelism(1)
                .addSink(S3DbSink.getSinkParquetLOZ(paramsJson))
                .setParallelism(2)
                .uid("S3DbForClickSink")
                .name("S3DbForClickSink");

        env.execute("Kafka to Parquet new");
    }
}

