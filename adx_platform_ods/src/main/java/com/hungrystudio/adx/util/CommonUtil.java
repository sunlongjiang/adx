package com.hungrystudio.adx.util;

public class CommonUtil {

    public static String getFlinkChkParentPath(String runEnv, String appName, String chkPathPos) {
        if ("prod".equalsIgnoreCase(runEnv)) {
            return "hdfs".equalsIgnoreCase(chkPathPos) ? "hdfs:///flink_checkpoint_prod/hungry_studio_lakehouse/" + appName.toLowerCase() + "/" : "s3://hungry-studio-data-warehouse/lakehouse_conf/flink_checkpoint_prod/" + appName.toLowerCase() + "/";
        } else if ("dev".equalsIgnoreCase(runEnv)) {
            return "hdfs".equalsIgnoreCase(chkPathPos) ? "hdfs:///flink_checkpoint_dev/hungry_studio_lakehouse/" + appName.toLowerCase() + "/" : "s3://hungry-studio-data-warehouse/lakehouse_conf/flink_checkpoint_dev/" + appName.toLowerCase() + "/";
        } else if ("test".equalsIgnoreCase(runEnv)) {
            return "hdfs".equalsIgnoreCase(chkPathPos) ? "hdfs:///flink_checkpoint_test/hungry_studio_lakehouse/" + appName.toLowerCase() + "/" : "s3://hungry-studio-data-warehouse/lakehouse_conf/flink_checkpoint_test/" + appName.toLowerCase() + "/";
        } else {
            return "";
        }
    }
}
