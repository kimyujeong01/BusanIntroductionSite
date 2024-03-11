package com.bitc.java501_team4.dto.festival;

import lombok.Data;

import java.util.List;

@Data
public class FestaKrDTO {
    private FestaHeaderDTO header;
    private List<FestaItemDTO> item;
    private int numOfRows;
    private int pageNo;
    private int totalCount;
}
