package com.hungrystudio.adx.util;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.Message;
import proto.Adx;

import java.lang.reflect.Method;

import com.google.protobuf.util.JsonFormat;

/**
 * @author slj
 * @date 2022/3/28
 */
public class CommonAnalysisUtils {


    public static String analysisToString(byte[] valueByte,String logType) {
        String value = null;
        try {
            Class<?> pbClass = null;
            if(logType.equals("AdxRequest")){
                pbClass = Adx.AdxRequest.class;
            }else if(logType.equals("AdxClick")){
                pbClass = Adx.AdxClick.class;
            }else if(logType.equals("AdxShow")){
                pbClass = Adx.AdxShow.class;
            }else if(logType.equals("AdxGeoedge")){
                pbClass = Adx.GeoEdgeRequest.class;
            }else if(logType.equals("AdxAlert")){
                pbClass = Adx.GeoEdgeAlert.class;
            }else {
                pbClass = Adx.GeoEdgeScan.class;
            }
            Method parseFunc = pbClass.getMethod("parseFrom", byte[].class);
            Message message = (Message) parseFunc.invoke(null, valueByte);
            if (message == null) {
                return null;
            }
            value = JsonFormat.printer().preservingProtoFieldNames().print(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    public static JSONObject analysisToJSON(String message) {
        return com.alibaba.fastjson.JSONObject.parseObject(message);
    }

    public static JSONObject analysis(byte[] valueByte) {
        JSONObject value = null;
        try {
            Class<?> pbClass = Adx.class;
            Method parseFunc = pbClass.getMethod("parseFrom", byte[].class);
            Message message = (Message) parseFunc.invoke(null, valueByte);
            if (message == null) {
                return null;
            }
            value = com.alibaba.fastjson.JSONObject.parseObject(JsonFormat.printer().preservingProtoFieldNames().print(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
