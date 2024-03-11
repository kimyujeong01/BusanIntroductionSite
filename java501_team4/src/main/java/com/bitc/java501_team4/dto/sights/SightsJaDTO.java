package com.bitc.java501_team4.dto.sights;

import lombok.Data;

import java.util.List;

@Data
public class SightsJaDTO {
    private SightsHeaderDTO header;
    private List<SightsItemDTO> item;
    private int numOfRows;
    private int pageNo;
    private int totalCount;
}
