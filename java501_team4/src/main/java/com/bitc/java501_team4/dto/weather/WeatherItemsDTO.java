package com.bitc.java501_team4.dto.weather;

import lombok.Data;

import java.util.List;

@Data
public class WeatherItemsDTO {
    private List<WeatherItemDTO> item;
}

