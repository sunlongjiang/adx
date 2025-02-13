package com.hungrystudio.adx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AdxRequestData {
    // -------------流量包相关 -------------
    @JsonProperty("bundle_id") private String bundle_id;
    @JsonProperty("ver") private String ver;
    @JsonProperty("ver_n") private String ver_n;
    @JsonProperty("sdk_ver") private String sdk_ver;
    // -------------设备相关 -------------
    @JsonProperty("os") private String os;
    @JsonProperty("idfa") private String idfa;
    @JsonProperty("idfv") private String idfv;
    @JsonProperty("gaid") private String gaid;
    @JsonProperty("oaid") private String oaid;
    @JsonProperty("model") private String model;
    @JsonProperty("vendor") private String vendor;
    @JsonProperty("carrier") private String carrier;
    @JsonProperty("mcc") private String mcc;
    @JsonProperty("network") private String network;
    @JsonProperty("ip") private String ip;
    @JsonProperty("country") private String country;
    // -------------广告相关 -------------
//    @JsonProperty("dsp_name") private String dsp_name;
//    @JsonProperty("ecpm") private String ecpm;
//    @JsonProperty("adid") private String adid;
//    @JsonProperty("crid") private String crid;
//    @JsonProperty("ads_pkg_name") private String ads_pkg_name;
//    @JsonProperty("adomain") private List<String> adomain;
    @JsonProperty("bid_info") private List<String> bid_info;

    // -------------请求相关 -------------
    @JsonProperty("sid") private String sid;
    @JsonProperty("rid") private String rid;
    @JsonProperty("pid") private String pid;
    @JsonProperty("p_type") private String p_type;
    @JsonProperty("cst") private long cst;
    @JsonProperty("timestamp") private long timestamp;
    @JsonProperty("et") private long et;
    @JsonProperty("dur_t") private long dur_t;

    // -------------其他信息 -------------
    @JsonProperty("ab") private String ab;
    @JsonProperty("adfo") private String adfo;
    @JsonProperty("ext") private String ext;
    @JsonProperty("event_name") private String event_name;
    @JsonProperty("win_ad") private String win_ad;
    @JsonProperty("lid") private String lid;
    @JsonProperty("ua") private String ua;
    @JsonProperty("os_version") private String os_version;
    @JsonProperty("language") private String language;
    @JsonProperty("devicetype") private String devicetype;
    // -------------数据分区相关 -------------
    @JsonProperty("dt") private String dt;
    @JsonProperty("hour") private String hour;
    @JsonProperty("group_id") private String groupId;
    @JsonProperty("is_request") private String is_request;
}

