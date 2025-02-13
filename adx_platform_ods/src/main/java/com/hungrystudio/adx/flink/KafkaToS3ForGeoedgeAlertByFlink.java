package com.hungrystudio.adx.flink;

import com.alibaba.fastjson.JSONObject;
import com.hungrystudio.adx.operator.S3ForClickFlatMap;
import com.hungrystudio.adx.operator.S3ForGeoedgeAlertFlatMap;
import com.hungrystudio.adx.sink.S3DbSink;
import com.hungrystudio.adx.util.CommonUtil;
import com.hungrystudio.adx.util.KafkaUtil;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.concurrent.TimeUnit;

public class KafkaToS3ForGeoedgeAlertByFlink {
    public static void main(String[] args) throws Exception {

        //获取参数
        final ParameterTool params = ParameterTool.fromArgs(args);
        //是否从最新的开始
        boolean isEarliest = params.getBoolean("isEarliest");
        String server = params.get("server");
        String topic = params.get("topic");
        String groupId = params.get("groupId");
        String bucket = params.get("bucket");
        String target = params.get("target");
        int sourceNum = params.getInt("sourceNum");
        int taskNum = params.getInt("taskNum");
        int sinkNum = params.getInt("sinkNum");
        Long timestamp = params.getLong("timestamp");


        String logType = "AdxAlert";

        System.out.println("执行参数:isEarliest:"+isEarliest);
        System.out.println("执行参数:groupId:"+groupId);
        System.out.println("执行参数:bucket:"+bucket);
        System.out.println("执行参数:target:"+target);
        System.out.println("执行参数:logType:"+logType);
        System.out.println("执行参数:server:"+server);
        System.out.println("执行参数:topic:"+topic);
        System.out.println("执行参数:sourceNum:"+sourceNum);
        System.out.println("执行参数:taskNum:"+taskNum);
        System.out.println("执行参数:sinkNum:"+sinkNum);
        System.out.println("执行参数:timestamp:"+timestamp);

        // S3 参数
        JSONObject paramsJson = new JSONObject();
        paramsJson.put("bucket",bucket);
        paramsJson.put("target",target);
        paramsJson.put("logType", logType);

        // Flink 环境配置
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        String chkParentPath = CommonUtil.getFlinkChkParentPath("prod", "KafkaToS3ForGeoedgeAlertByFlink", "s3");  // 获取flink checkpoint父路径
        env.enableCheckpointing(60000); // Checkpoint 每 60 秒
        CheckpointConfig config = env.getCheckpointConfig();
        config.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        config.setCheckpointTimeout(30000);
        config.setMinPauseBetweenCheckpoints(30000);
        config.setCheckpointStorage(chkParentPath + groupId);
        config.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(
                3, // 重试次数
                Time.of(10, TimeUnit.SECONDS) // 间隔时间
        ));

        // Kafka 消费者
        KafkaSource<String> kafkaSource = KafkaUtil.getkafkaSource("msk",server, groupId, topic,logType,isEarliest,timestamp);

        env.fromSource(kafkaSource, WatermarkStrategy.noWatermarks(), "KafkaSource")
                .setParallelism(sourceNum)
                .uid("AdxAlertSource")
                .name("AdxAlertSource")
                .flatMap(new S3ForGeoedgeAlertFlatMap(paramsJson))
                .setParallelism(taskNum)
                .uid("AdxAlertTask")
                .name("AdxAlertTask")
                .addSink(S3DbSink.getSinkParquetLOZ(paramsJson))
                .setParallelism(sinkNum)
                .uid("AdxAlertSink")
                .name("AdxAlertSink");

        env.execute("Kafka to Parquet AdxAlert");
    }
}

