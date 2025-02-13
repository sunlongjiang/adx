package com.hungrystudio.adx.sink;
import com.hungrystudio.adx.dto.AdxClickData;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.BasePathBucketAssigner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdxClickPathBucketAssigner extends BasePathBucketAssigner<AdxClickData> {

    private String target_path;

    public AdxClickPathBucketAssigner(String target_path) {
        this.target_path = target_path;
    }

    private static final Logger LOG = LoggerFactory.getLogger(AdxClickPathBucketAssigner.class);

    @Override
    public String getBucketId(AdxClickData adxClickData, Context context) {

        /**
         * data_warehouse/qz_ods/ads_model_server_log_inc_hourly_test_click/datepart=%s/hour=%s/
         */
        String bundleId = adxClickData.getBundle_id();
        String os = adxClickData.getOs();
        String HH = adxClickData.getHour();
        String yyyyMMdd = adxClickData.getDt();
        String group_id = adxClickData.getGroupId();


        String format = String.format(target_path, bundleId,os,yyyyMMdd, HH,group_id);
        return format;
    }
}