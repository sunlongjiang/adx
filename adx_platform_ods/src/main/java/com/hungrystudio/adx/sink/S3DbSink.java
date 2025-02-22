package com.hungrystudio.adx.sink;

import com.alibaba.fastjson.JSONObject;
import com.hungrystudio.adx.dto.*;
import org.apache.flink.connector.file.sink.FileSink;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.functions.sink.filesystem.BucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.BasePathBucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.OnCheckpointRollingPolicy;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

import java.util.function.Function;


/**
 * @author slj
 * @date 2020/12/2
 * fe
 */
public class S3DbSink {

    private static <T> FileSink <T> createFileSink(String bucketPath, String target, Class <T> dataTypeClass,
                                                   Function <String, BasePathBucketAssigner<T>> bucketAssignerFunction) {

        return FileSink
                .forBulkFormat(
                        new Path(bucketPath),
                        PaulParquetAvroWriters.forReflectRecord(dataTypeClass, CompressionCodecName.GZIP)
                )
                .withRollingPolicy(OnCheckpointRollingPolicy.build())
                .withBucketAssigner(bucketAssignerFunction.apply(target))
                .build();
    }


    public static FileSink <?> getSinkParquetLOZ(JSONObject params) {
        String bucket = params.getString("bucket");
        String target = params.getString("target");
        String logType = params.getString("logType");

        switch (logType) {
            case "AdxClick":
                return createFileSink(bucket, target, AdxClickData.class, AdxClickPathBucketAssigner::new);
            case "AdxRequest":
                return createFileSink(bucket, target, AdxRequestData.class, AdxRequestPathBucketAssigner::new);
            case "AdxShow":
                return createFileSink(bucket, target, AdxShowData.class, AdxShowPathBucketAssigner::new);
            case "AdxGeoedge":
                return createFileSink(bucket, target, AdxGeoedgeData.class, AdxGeoedgePathBucketAssigner::new);
            case "AdxAlert":
                return createFileSink(bucket, target, AdxGeoedgeAlertData.class, AdxGeoedgeAlertPathBucketAssigner::new);
            default:
                return createFileSink(bucket, target, AdxGeoedgeScanData.class, AdxGeoedgeScanPathBucketAssigner::new);
        }
    }
}

