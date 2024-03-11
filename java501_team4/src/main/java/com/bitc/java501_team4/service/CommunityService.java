package com.bitc.java501_team4.service;

import com.bitc.java501_team4.dto.community.*;
import com.github.pagehelper.Page;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface CommunityService {

    void updateReviewCount(int rIdx) throws Exception;

    ReviewBoardDTO selectBoardDetail(int rIdx) throws Exception;

    List<ReviewCommentDTO> selectCommentDetail(int rIdx) throws Exception;

    void writeBoard(ReviewBoardDTO reviewBoard) throws Exception;

    void updateReview(ReviewBoardDTO board, MultipartHttpServletRequest multipart) throws Exception;

    void deleteRevAllment(int rIdx) throws Exception;

    void deleteReview(int rIdx) throws Exception;

    void commentReviewWrite(ReviewCommentDTO revComm) throws Exception;

    void commentReviewDelete(int revIdx, int revBoardIdx) throws Exception;

    Page<QnaDTO> selectQnaList(int pageNum) throws Exception;

    Page<ReviewBoardDTO> selectBoardPageList(int pageNum) throws Exception;

    QnaDTO selectQnaDetail(int qIdx) throws Exception;

    List<QnaComDTO> selectQnaComList(int qIdx) throws Exception;

    void insertQna(QnaDTO qna) throws Exception;

    void updateQna(QnaDTO qna) throws Exception;

    void deleteAllMent(int qIdx) throws Exception;

    void deleteQna(int qIdx) throws Exception;

    void insertMent(QnaComDTO ment) throws Exception;

    void deleteMent(int qcIdx, int qcBoardIdx) throws Exception;

    int cntUserId(String qCreateId) throws Exception;

    reviewFileDTO selectFileInfo(int idx, int rIdx) throws Exception;

    void writeReview(ReviewBoardDTO reviewBoard, MultipartHttpServletRequest multipart) throws Exception;
}
