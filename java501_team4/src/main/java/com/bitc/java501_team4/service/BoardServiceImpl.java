package com.bitc.java501_team4.service;

import com.bitc.java501_team4.dto.festival.FestaDTO;
import com.bitc.java501_team4.dto.festival.FestaItemDTO;
import com.bitc.java501_team4.dto.sights.SightsDTO;
import com.bitc.java501_team4.dto.sights.SightsItemDTO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Override
    public List<FestaItemDTO> FestaList(String serviceUrl, String language) throws Exception {
        List<FestaItemDTO> festaList = null;

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
            FestaDTO festa = gson.fromJson(sb.toString(), FestaDTO.class);

            if (language.equals("En")) {
                festaList = festa.getGetFestivalEn().getItem();
            } else if (language.equals("Ja")) {
                festaList = festa.getGetFestivalJa().getItem();
            } else {
                festaList = festa.getGetFestivalKr().getItem();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (urlCon != null) {
                urlCon.disconnect();
            }
        }

        return festaList;
    }




    @Override
    public List<SightsItemDTO> SightsList(String serviceUrl, String language) throws Exception {
        List<SightsItemDTO> sightsList = null;

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

            SightsDTO sights = gson.fromJson(sb.toString(), SightsDTO.class);

            if (language.equals("En")) {
                sightsList = sights.getGetAttractionEn().getItem();
            } else if (language.equals("Ja")) {
                sightsList = sights.getGetAttractionJa().getItem();
            } else {
                sightsList = sights.getGetAttractionKr().getItem();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (urlCon != null) {
                urlCon.disconnect();
            }
        }

        return sightsList;
    }


}
