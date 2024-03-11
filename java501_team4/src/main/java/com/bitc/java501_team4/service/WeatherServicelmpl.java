package com.bitc.java501_team4.service;

import com.bitc.java501_team4.dto.weather.WeatherDTO;
import com.bitc.java501_team4.dto.weather.WeatherItemDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherServicelmpl implements WeatherService {
    public List<WeatherItemDTO> WeatherList(String serviceUrl) throws Exception {
        List<WeatherItemDTO> weatherList = null;

        URL url = null;
        HttpURLConnection urlCon = null;
        BufferedReader reader = null;

        try {
            url = new URL(serviceUrl);
            urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("GET");
            urlCon.setConnectTimeout(5000); // 연결 타임아웃 설정 (5초)
            urlCon.setReadTimeout(5000);    // 읽기 타임아웃 설정 (5초)
            urlCon.setRequestProperty("Accept", "application/json");

            reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            Gson gson = new Gson();

            WeatherDTO weather = gson.fromJson(sb.toString(), WeatherDTO.class);
            weatherList = weather.getResponse().getBody().getItems().getItem();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {reader.close();}
            if (urlCon != null) {urlCon.disconnect();}
        }

        if (weatherList != null) {
            // category가 "TMP"인 항목만 필터링
            weatherList = weatherList.stream()
                    .filter(item -> "TMP".equals(item.getCategory()))
                    .collect(Collectors.toList());
            if(weatherList != null){
                weatherList = weatherList.stream()
                        .filter(item -> "0600".equals(item.getFcstTime()) || "1500".equals(item.getFcstTime()) )
                        .collect(Collectors.toList());
            }
        }

        return weatherList;
    }

}
