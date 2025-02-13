package com.hungrystudio.adx.sink;


import com.hungrystudio.adx.dto.AdxClickData;
import com.hungrystudio.adx.dto.AdxRequestData;
import com.hungrystudio.adx.dto.AdxShowData;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.BasePathBucketAssigner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class AdxShowPathBucketAssigner extends BasePathBucketAssigner<AdxShowData> {

    private String target_path;

    public AdxShowPathBucketAssigner(String target_path) {
        this.target_path = target_path;
    }

    private static final Logger LOG = LoggerFactory.getLogger(AdxShowPathBucketAssigner.class);

    @Override
    public String getBucketId(AdxShowData adxShowData, Context context) {

        /**
         * data_warehouse/qz_ods/ads_model_server_log_inc_hourly_test_click/datepart=%s/hour=%s/
         */
        String bundleId = adxShowData.getBundle_id();
        String os = adxShowData.getOs();
        String HH = adxShowData.getHour();
        String yyyyMMdd = adxShowData.getDt();
        String group_id = adxShowData.getGroupId();

        String format = String.format(target_path, bundleId,os,yyyyMMdd, HH,group_id);
        return format;
    }

}