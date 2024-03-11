package com.bitc.java501_team4.dto.weather;

import lombok.Data;

@Data
public class WeatherBodyDTO {
    private String dataType;
    private WeatherItemsDTO items;
    private int pageNo;
    private int numOfRows;
    private int totalCount;
}
