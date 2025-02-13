package com.hungrystudio.adx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdxGeoedgeAlertData {

    @JsonProperty("event_name") private String event_name;
    @JsonProperty("project_id") private String project_id;
    @JsonProperty("project_name") private String project_name;
    @JsonProperty("timestamp") private long timestamp;
    @JsonProperty("alert_name") private String alert_name;
    @JsonProperty("trigger_type_id") private String trigger_type_id;
    @JsonProperty("trigger_type_name") private String trigger_type_name;
    @JsonProperty("trigger_metadata") private String trigger_metadata;
    @JsonProperty("resp_json") private String resp_json;
    //新增字段2025/1/13
    @JsonProperty("crid") private String crid;//告警的素材ID
    @JsonProperty("country") private String country;
    @JsonProperty("ext") private String ext;

    @JsonProperty("dt") private String dt;
    @JsonProperty("hour") private String hour;
}

