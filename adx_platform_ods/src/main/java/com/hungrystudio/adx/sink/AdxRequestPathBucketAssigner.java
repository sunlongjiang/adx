package com.hungrystudio.adx.sink;


import com.hungrystudio.adx.dto.AdxClickData;
import com.hungrystudio.adx.dto.AdxRequestData;
import com.hungrystudio.adx.dto.AdxShowData;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.BasePathBucketAssigner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class AdxRequestPathBucketAssigner extends BasePathBucketAssigner<AdxRequestData> {

    private String target_path;
    private static Map<String, Integer> targetMap = new ConcurrentHashMap<>();

    static {
        targetMap.put("click", 1);
    }

    private static int number = 0;

    public AdxRequestPathBucketAssigner(String target_path) {
        this.target_path = target_path;
    }

    private static final Logger LOG = LoggerFactory.getLogger(AdxRequestPathBucketAssigner.class);


    @Override
    public String getBucketId(AdxRequestData adxRequestData, Context context) {

        /**
         * data_warehouse/qz_ods/ads_model_server_log_inc_hourly_test_click/datepart=%s/hour=%s/
         */
        String bundleId = adxRequestData.getBundle_id();
        String os = adxRequestData.getOs();
        String HH = adxRequestData.getHour();
        String yyyyMMdd = adxRequestData.getDt();
        String group_id = adxRequestData.getGroupId();


        String format = String.format(target_path, bundleId,os,yyyyMMdd, HH,group_id);
        return format;
    }

}