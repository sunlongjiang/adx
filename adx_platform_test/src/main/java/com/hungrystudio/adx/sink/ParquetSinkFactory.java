package com.hungrystudio.adx.sink;

import com.alibaba.fastjson.JSONObject;
import com.hungrystudio.adx.dto.*;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.BasePathBucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.OnCheckpointRollingPolicy;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

public class ParquetSinkFactory {

    public static <T> StreamingFileSink<T> getSinkParquetLOZ(JSONObject params, Class<T> dataClass, Class<? extends BasePathBucketAssigner<T>> bucketAssignerClass) throws  Exception {
        String bucket = params.getString("bucket");
        String target = params.getString("target");

        return StreamingFileSink
                .forBulkFormat(new Path(bucket), PaulParquetAvroWriters.forReflectRecord(dataClass, CompressionCodecName.GZIP))
                .withRollingPolicy(OnCheckpointRollingPolicy.build())
                .withBucketAssigner(bucketAssignerClass.getConstructor(String.class).newInstance(target))
                .build();
    }


    public static StreamingFileSink<?> getSinkParquetLOZ(JSONObject params) throws Exception {
        String logType = params.getString("logType");
        switch (logType) {
            case "AdxClick":
                return getSinkParquetLOZ(params, AdxClickData.class, AdxClickPathBucketAssigner.class);
            case "AdxRequest":
                return getSinkParquetLOZ(params, AdxRequestData.class, AdxRequestPathBucketAssigner.class);
            case "AdxShow":
                return getSinkParquetLOZ(params, AdxShowData.class, AdxShowPathBucketAssigner.class);
            case "AdxGeoedge":
                return getSinkParquetLOZ(params, AdxGeoedgeData.class, AdxGeoedgePathBucketAssigner.class);
            case "AdxAlert":
                return getSinkParquetLOZ(params, AdxGeoedgeAlertData.class, AdxGeoedgeAlertPathBucketAssigner.class);
            default:
                return getSinkParquetLOZ(params, AdxGeoedgeScanData.class, AdxGeoedgeScanPathBucketAssigner.class);
        }
    }
}