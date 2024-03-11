package com.bitc.java501_team4.dto.community;

import lombok.Data;

@Data
public class QnaDTO {
    private int qIdx;
    private String qTitle;
    private String qContent;
    private String qCreateDate;
    private String qCreateId;
    private String qName;
    private int cnt;
}
