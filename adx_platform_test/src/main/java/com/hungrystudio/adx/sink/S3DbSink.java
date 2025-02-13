package com.hungrystudio.adx.sink;

import com.alibaba.fastjson.JSONObject;
import com.hungrystudio.adx.dto.*;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.BasePathBucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.OnCheckpointRollingPolicy;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;


/**
 * @author slj
 * @date 2020/12/2
 * fe
 */
public class S3DbSink {

    public static StreamingFileSink getSinkParquetLOZ(JSONObject params) {
        String bucket = params.getString("bucket");
        String target = params.getString("target");
        String logType = params.getString("logType");
        if(logType.equals("AdxClick")){
            StreamingFileSink<AdxClickData> sink = StreamingFileSink
                    .forBulkFormat(new Path(bucket), PaulParquetAvroWriters.forReflectRecord(AdxClickData.class, CompressionCodecName.GZIP))
                    .withRollingPolicy(OnCheckpointRollingPolicy.build())
                    .withBucketAssigner(new AdxClickPathBucketAssigner(target))
                    .build();
            return sink;
        }else if(logType.equals("AdxRequest")){
            StreamingFileSink<AdxRequestData> sink = StreamingFileSink
                    .forBulkFormat(new Path(bucket), PaulParquetAvroWriters.forReflectRecord(AdxRequestData.class, CompressionCodecName.GZIP))
                    .withRollingPolicy(OnCheckpointRollingPolicy.build())
                    .withBucketAssigner(new AdxRequestPathBucketAssigner(target))
                    .build();
            return sink;
        }else if(logType.equals("AdxShow")){
            StreamingFileSink<AdxShowData> sink = StreamingFileSink
                    .forBulkFormat(new Path(bucket), PaulParquetAvroWriters.forReflectRecord(AdxShowData.class, CompressionCodecName.GZIP))
                    .withRollingPolicy(OnCheckpointRollingPolicy.build())
                    .withBucketAssigner(new AdxShowPathBucketAssigner(target))
                    .build();
            return sink;
        }else if(logType.equals("AdxGeoedge")){
            StreamingFileSink<AdxGeoedgeData> sink = StreamingFileSink
                    .forBulkFormat(new Path(bucket), PaulParquetAvroWriters.forReflectRecord(AdxGeoedgeData.class, CompressionCodecName.GZIP))
                    .withRollingPolicy(OnCheckpointRollingPolicy.build())
                    .withBucketAssigner(new AdxGeoedgePathBucketAssigner(target))
                    .build();
            return sink;
        }else if(logType.equals("AdxAlert")){
            StreamingFileSink<AdxGeoedgeAlertData> sink = StreamingFileSink
                    .forBulkFormat(new Path(bucket), PaulParquetAvroWriters.forReflectRecord(AdxGeoedgeAlertData.class, CompressionCodecName.GZIP))
                    .withRollingPolicy(OnCheckpointRollingPolicy.build())
                    .withBucketAssigner(new AdxGeoedgeAlertPathBucketAssigner(target))
                    .build();
            return sink;
        }else {
            StreamingFileSink<AdxGeoedgeScanData> sink = StreamingFileSink
                    .forBulkFormat(new Path(bucket), PaulParquetAvroWriters.forReflectRecord(AdxGeoedgeScanData.class, CompressionCodecName.GZIP))
                    .withRollingPolicy(OnCheckpointRollingPolicy.build())
                    .withBucketAssigner(new AdxGeoedgeScanPathBucketAssigner(target))
                    .build();
            return sink;
        }



    }
}

