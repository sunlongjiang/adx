package com.hungrystudio.adx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdxGeoedgeData {

    /**
     * 表名：hungry_studio.dwd_adx_server_event_data_geoedge_hi
     */
    @JsonProperty("event_name") private String event_name;
    @JsonProperty("bundle_id") private String bundle_id;
    @JsonProperty("ver") private String ver;
    @JsonProperty("ver_n") private String ver_n;
    @JsonProperty("sdk_ver") private String sdk_ver;
    @JsonProperty("lid") private String lid;
    @JsonProperty("sid") private String sid;
    @JsonProperty("rid") private String rid;
    @JsonProperty("pid") private String pid;
    @JsonProperty("p_type") private String p_type;
    @JsonProperty("cst") private long cst;
    @JsonProperty("target_id") private String target_id;
    @JsonProperty("dsp_group_id") private String dsp_group_id;
    @JsonProperty("country") private String country;
    @JsonProperty("crid") private String crid;
    @JsonProperty("os") private String os;
    @JsonProperty("project_id") private String project_id;
    @JsonProperty("project_name") private String project_name;
    @JsonProperty("ab") private String ab;
    /**
     * 新增字段
     * adType: 三方广告位类型
     * adm：rtb的adm信息
     * resp：调用GeoEdge的响应信息
     * bundle：广告主包名
     * adId：DSP广告ID
     * adomain：广告主域名
     */
    @JsonProperty("ext") private String ext;//json类型字段
    //新增字段 2025/1/13
    @JsonProperty("adm") private String adm;//gzip压缩字段 rtb的ADM 解压

    @JsonProperty("timestamp") private long timestamp;
    @JsonProperty("dt") private String dt;
    @JsonProperty("hour") private String hour;
}

