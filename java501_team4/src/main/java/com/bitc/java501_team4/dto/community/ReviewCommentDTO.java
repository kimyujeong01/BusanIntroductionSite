package com.bitc.java501_team4.dto.community;

import lombok.Data;

@Data
public class ReviewCommentDTO {
    private int revIdx;
    private int revBoardIdx;
    private String revContent;
    private String revId;
}
