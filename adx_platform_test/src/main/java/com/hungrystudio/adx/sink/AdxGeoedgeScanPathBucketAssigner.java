package com.hungrystudio.adx.sink;


import com.hungrystudio.adx.dto.AdxGeoedgeScanData;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.BasePathBucketAssigner;


public class AdxGeoedgeScanPathBucketAssigner extends BasePathBucketAssigner<AdxGeoedgeScanData> {

    private String target_path;

    public AdxGeoedgeScanPathBucketAssigner(String target_path) {
        this.target_path = target_path;
    }


    @Override
    public String getBucketId(AdxGeoedgeScanData data, Context context) {

        String HH = data.getHour();
        String yyyyMMdd = data.getDt();
        String format = String.format(target_path,yyyyMMdd, HH);
        return format;
    }

}