package com.hungrystudio.adx.sink;


import com.hungrystudio.adx.dto.AdxGeoedgeAlertData;
import com.hungrystudio.adx.dto.AdxGeoedgeData;
import com.hungrystudio.adx.dto.AdxShowData;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.BasePathBucketAssigner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AdxGeoedgeAlertPathBucketAssigner extends BasePathBucketAssigner<AdxGeoedgeAlertData> {

    private String target_path;

    public AdxGeoedgeAlertPathBucketAssigner(String target_path) {
        this.target_path = target_path;
    }

    private static final Logger LOG = LoggerFactory.getLogger(AdxGeoedgeAlertPathBucketAssigner.class);

    @Override
    public String getBucketId(AdxGeoedgeAlertData data, Context context) {

        String HH = data.getHour();
        String yyyyMMdd = data.getDt();
        String format = String.format(target_path,yyyyMMdd, HH);
        return format;
    }

}