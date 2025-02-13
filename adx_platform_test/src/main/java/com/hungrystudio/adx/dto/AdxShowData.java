package com.hungrystudio.adx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AdxShowData {

    @JsonProperty("bundle_id") private String bundle_id;
    @JsonProperty("ver") private String ver;
    @JsonProperty("ver_n") private String ver_n;
    @JsonProperty("sdk_ver") private String sdk_ver;
    @JsonProperty("lid") private String lid;
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
    @JsonProperty("dsp_name") private String dsp_name;
    @JsonProperty("ecpm") private String ecpm;
    @JsonProperty("adid") private String adid;
    @JsonProperty("crid") private String crid;
    @JsonProperty("ads_pkg_name") private String ads_pkg_name;
    @JsonProperty("adomain") private String adomain;
    @JsonProperty("sid") private String sid;
    @JsonProperty("rid") private String rid;
    @JsonProperty("pid") private String pid;
    @JsonProperty("p_type") private String p_type;
    @JsonProperty("cst") private long cst;
    @JsonProperty("ab") private String ab;
    @JsonProperty("adfo") private String adfo;
    @JsonProperty("ext") private String ext;

    @JsonProperty("event_name") private String event_name;
    @JsonProperty("ua") private String ua;
    @JsonProperty("os_version") private String os_version;
    @JsonProperty("language") private String language;
    @JsonProperty("devicetype") private String devicetype;
    @JsonProperty("cid") private String cid;
    @JsonProperty("cat") private String cat;
    @JsonProperty("tagid") private String tagid;
    @JsonProperty("target_id") private String target_id;
    @JsonProperty("dsp_country") private String dsp_country;
    @JsonProperty("ad_type") private String ad_type;
    @JsonProperty("dsp_id") private String dsp_id;
    @JsonProperty("dsp_group_id") private String dsp_group_id;
    @JsonProperty("bid_floor") private String bid_floor;


    @JsonProperty("timestamp") private long timestamp;
    @JsonProperty("dt") private String dt;
    @JsonProperty("hour") private String hour;
    @JsonProperty("group_id") private String groupId;
    @JsonProperty("is_show") private String is_show;
}

