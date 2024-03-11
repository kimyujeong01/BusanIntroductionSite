package com.bitc.java501_team4.dto.community;

import lombok.Data;

import java.util.List;

@Data
public class ReviewBoardDTO {
    private int rIdx;
    private String rTitle;
    private String rContent;
    private int rViewCnt;
    private String rCreateDate;
    private String rCreateId;
    private List<reviewFileDTO> fileList;
}
