package com.hungrystudio.adx.sink;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.BasePathBucketAssigner;


/**
 * @author: xuecj@ushareit.com
 * @date: 2021/4/19 下午3:47
 * @description:
 **/
public class MyReportBasePathBucketAssignerForLog extends BasePathBucketAssigner <JSONObject> {


    private String target_path;

    public MyReportBasePathBucketAssignerForLog(String target_path) {
        this.target_path = target_path;
    }

    @Override
    public String getBucketId(JSONObject jsonObject, Context context) {
        String bucketIdSample = getBucketIdSample(jsonObject);
        if (bucketIdSample == null) {
            return target_path + "/temp";
        }
        return bucketIdSample;
    }


    public String getBucketIdSample(JSONObject jsonObject) {
        /**
         * 流量&业务流量划分逻辑如下：
         * if appType != 3 && appType !=4 || appId == "com.playplay.game" {
         *     if skuId == 0 {
         *         // 站内非电商流量日志
         *     }
         *     else{
         *         // 站内电商流量日志
         *     }
         * } else{
         *     if skuId == 0 {
         *         // 站外非电商流量日志
         *     }
         *     else{
         *         // 站外电商流量日志
         *     }
         * }
         */

        String HH = jsonObject.getString("hour");
        String yyyyMMdd = jsonObject.getString("datepart");
        int appType = jsonObject.getIntValue("app_type");
        String appId = jsonObject.getString("app_id");
        int skuId = jsonObject.getIntValue("sku_id");
        JSONArray trafficType = jsonObject.getJSONArray("traffic_type");
        int hash = Math.abs(jsonObject.getString("trace_id").hashCode() % 10);

        if (trafficType != null && trafficType.contains(4)) {
            return String.format(target_path, yyyyMMdd, HH) + "/vice_advertising/" + hash;
        } else {
            if ((appType == 3 || appType == 4) && !"com.playplay.game".equals(appId) && skuId == 0) {
                // 站外非电商流量日志 rtb
                return String.format(target_path, yyyyMMdd, HH) + "/rtb/" + appId + "/" + hash;
            }

            if (((appType != 3 && appType != 4) || "com.playplay.game".equals(appId)) && skuId == 0) {
                // 站内非电商流量日志 cpi
                return String.format(target_path, yyyyMMdd, HH) + "/ua/online/" + hash;
            }

            if ((appType == 3 || appType == 4) && !"com.playplay.game".equals(appId) && skuId != 0) {
                // 站外电商流量日志
                return String.format(target_path, yyyyMMdd, HH) + "/dpa/rtb/" + hash;
            }
            if (((appType != 3 && appType != 4) || "com.playplay.game".equals(appId)) && skuId != 0) {
                // 站内电商流量日志
                return String.format(target_path, yyyyMMdd, HH) + "/dpa/ua/" + hash;
            }
            return null;
        }
    }
}