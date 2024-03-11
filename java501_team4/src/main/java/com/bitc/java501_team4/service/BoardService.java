package com.bitc.java501_team4.service;

import com.bitc.java501_team4.dto.festival.FestaItemDTO;
import com.bitc.java501_team4.dto.sights.SightsItemDTO;

import java.util.List;

public interface BoardService {
    List<FestaItemDTO> FestaList(String serviceUrl, String language) throws Exception;

    List<SightsItemDTO> SightsList(String serviceUrl, String language) throws Exception;


}
