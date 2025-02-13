package com.hungrystudio.adx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdxGeoedgeScanData {

    @JsonProperty("event_name") private String event_name;
    @JsonProperty("project_id") private String project_id;
    @JsonProperty("project_name") private String project_name;
    @JsonProperty("crid") private String crid;
    @JsonProperty("timestamp") private long timestamp;
    @JsonProperty("scan_timestamp") private long scan_timestamp;
    @JsonProperty("creative_screenshot_url") private String creative_screenshot_url;
    @JsonProperty("landing_page_screenshot_url") private String landing_page_screenshot_url;
    @JsonProperty("creative_url") private String creative_url;
    @JsonProperty("landing_page_url") private String landing_page_url;
    @JsonProperty("country") private String country;
    @JsonProperty("resp") private String resp;
    @JsonProperty("ext") private String ext;

    @JsonProperty("dt") private String dt;
    @JsonProperty("hour") private String hour;
}

