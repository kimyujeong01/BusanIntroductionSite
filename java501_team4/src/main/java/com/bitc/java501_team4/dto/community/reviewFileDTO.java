package com.bitc.java501_team4.dto.community;

import lombok.Data;

@Data
public class reviewFileDTO {
    private int idx;
    private int rIdx;
    private String oFile;
    private String sFile;
    private long fileSize;
    private String createId;
    private String createDate;

}
