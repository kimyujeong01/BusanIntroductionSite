package com.bitc.java501_team4.dto.festival;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FestaItemDTO {
    @JsonProperty("UC_SEQ")
    private String UC_SEQ;
    @JsonProperty("MAIN_TITLE")
    private String MAIN_TITLE;
    @JsonProperty("GUGUN_NM")
    private String GUGUN_NM;
    @JsonProperty("LAT")
    private String LAT;
    @JsonProperty("LNG")
    private String LNG;
    @JsonProperty("PLACE")
    private String PLACE;
    @JsonProperty("TITLE")
    private String TITLE;
    @JsonProperty("SUBTITLE")
    private String SUBTITLE;
    @JsonProperty("MAIN_PLACE")
    private String MAIN_PLACE;
    @JsonProperty("ADDR1")
    private String ADDR1;
    @JsonProperty("ADDR2")
    private String ADDR2;
    @JsonProperty("CNTCT_TEL")
    private String CNTCT_TEL;
    @JsonProperty("HOMEPAGE_URL")
    private String HOMEPAGE_URL;
    @JsonProperty("TRFC_INFO")
    private String TRFC_INFO;
    @JsonProperty("USAGE_DAY")
    private String USAGE_DAY;
    @JsonProperty("USAGE_DAY_WEEK_AND_TIME")
    private String USAGE_DAY_WEEK_AND_TIME;
    @JsonProperty("USAGE_AMOUNT")
    private String USAGE_AMOUNT;
    @JsonProperty("MAIN_IMG_NORMAL")
    private String MAIN_IMG_NORMAL;
    @JsonProperty("MAIN_IMG_THUMB")
    private String MAIN_IMG_THUMB;
    @JsonProperty("ITEMCNTNTS")
    private String ITEMCNTNTS;
    @JsonProperty("MIDDLE_SIZE_RM1")
    private String MIDDLE_SIZE_RM1;
}
