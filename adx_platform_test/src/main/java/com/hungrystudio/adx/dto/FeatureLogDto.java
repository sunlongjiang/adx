package com.hungrystudio.adx.dto;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by slj on 2022/3/1.
 */
public class FeatureLogDto implements Serializable {

    /**
     * result.put("trace_id", requestId);
     * result.put("feature_input", feature_input);
     * result.put("hour", DateSerializer.getHH(serverTime * 1000));
     * result.put("datepart", DateSerializer.getyyyyMMdd(serverTime * 1000));
     */

    private String trace_id;
    private JSONObject feature_input;
    private String hour;
    private String datepart;


    public String getTrace_id() {
        return trace_id;
    }

    public void setTrace_id(String trace_id) {
        this.trace_id = trace_id;
    }

    public JSONObject getFeature_input() {
        return feature_input;
    }

    public void setFeature_input(JSONObject feature_input) {
        this.feature_input = feature_input;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDatepart() {
        return datepart;
    }

    public void setDatepart(String datepart) {
        this.datepart = datepart;
    }
}
