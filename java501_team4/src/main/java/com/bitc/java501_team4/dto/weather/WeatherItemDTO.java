package com.bitc.java501_team4.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherItemDTO {
    @JsonProperty("baseDate")
    private String baseDate;
    @JsonProperty("baseTime")
    private String baseTime;
    @JsonProperty("category")
    private String category;
    @JsonProperty("fcstDate")
    private String fcstDate;
    @JsonProperty("fcstTime")
    private String fcstTime;
    @JsonProperty("fcstValue")
    private String fcstValue;
    @JsonProperty("nx")
    private String nx;
    @JsonProperty("ny")
    private String ny;
}
