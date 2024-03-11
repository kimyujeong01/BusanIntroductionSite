package com.bitc.java501_team4.dto.weather;

import lombok.Data;

@Data
public class WeatherResDTO {
    private WeatherHeaderDTO header;

    private WeatherBodyDTO body;
}
