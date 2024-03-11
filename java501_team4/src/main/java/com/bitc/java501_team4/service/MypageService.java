package com.bitc.java501_team4.service;

import com.bitc.java501_team4.dto.community.QnaDTO;
import com.bitc.java501_team4.dto.community.ReviewBoardDTO;
import com.github.pagehelper.Page;

import java.util.List;

public interface MypageService {
    Page<QnaDTO> userQnaListPaging(int pageNum, String uId) throws Exception;
    Page<ReviewBoardDTO> userReviewListPaging(int pageNum2, String uId) throws Exception;
    List<QnaDTO> userQnaList(String uId) throws Exception;
    List<ReviewBoardDTO> userReviewList(String uId) throws Exception;
}
