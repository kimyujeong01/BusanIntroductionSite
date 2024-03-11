package com.bitc.java501_team4.service;

import com.bitc.java501_team4.dto.weather.WeatherItemDTO;

import java.util.List;

public interface WeatherService {

    List<WeatherItemDTO> WeatherList(String serviceUrl) throws Exception;

}
