package com.hungrystudio.adx.sink;


import com.hungrystudio.adx.dto.AdxGeoedgeData;
import com.hungrystudio.adx.dto.AdxShowData;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.BasePathBucketAssigner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AdxGeoedgePathBucketAssigner extends BasePathBucketAssigner<AdxGeoedgeData> {

    private String target_path;

    public AdxGeoedgePathBucketAssigner(String target_path) {
        this.target_path = target_path;
    }

    private static final Logger LOG = LoggerFactory.getLogger(AdxGeoedgePathBucketAssigner.class);

    @Override
    public String getBucketId(AdxGeoedgeData data, Context context) {

        /**
         * data_warehouse/qz_ods/ads_model_server_log_inc_hourly_test_click/datepart=%s/hour=%s/
         */
        String bundleId = data.getBundle_id();
        String os = data.getOs();
        String HH = data.getHour();
        String yyyyMMdd = data.getDt();

        String format = String.format(target_path, bundleId,os,yyyyMMdd, HH);
        return format;
    }

}