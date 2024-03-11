package com.bitc.java501_team4.mapper;

import com.bitc.java501_team4.dto.community.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface CommunityMapper {
    void updateReviewCount(int rIdx) throws Exception;

    ReviewBoardDTO selectBoardDetail(int rIdx) throws Exception;

    List<ReviewCommentDTO> selectCommentDetail(int rIdx) throws Exception;

    void writeBoard(ReviewBoardDTO reviewBoard) throws Exception;

    void updateReview(ReviewBoardDTO board) throws Exception;

    void deleteRevAllment(int rIdx) throws Exception;

    void deleteReview(int rIdx) throws Exception;

    void commentReviewWrite(ReviewCommentDTO revComm) throws Exception;

    void commentReviewDelete(int revIdx, int revBoardIdx) throws Exception;

    Page<QnaDTO> selectQnaList() throws Exception;

    QnaDTO selectQnaDetail(int qIdx) throws Exception;

    List<QnaComDTO> selectQnaComList(int qIdx) throws Exception;

    void insertQna(QnaDTO qna) throws Exception;

    void updateQna(QnaDTO qna) throws Exception;

    void deleteAllMent(int qIdx) throws Exception;

    void deleteQna(int qIdx) throws Exception;

    void insertMent(QnaComDTO ment) throws Exception;

    void deleteMent(int qcIdx, int qcBoardIdx) throws Exception;

    int cntUserId(String qCreateId) throws Exception;

    Page<ReviewBoardDTO> selectBoardPageList() throws Exception;

    reviewFileDTO selectFileInfo(@RequestParam("idx") int idx, @RequestParam("rIdx") int rIdx) throws Exception;

    List<reviewFileDTO> selectReviewFileList(int rIdx) throws Exception;

    void writeReview(ReviewBoardDTO reviewBoard) throws Exception;

    void insertReviewFIleList(List<reviewFileDTO> fileList) throws Exception;

    List<reviewFileDTO> selectFiledelet(int rIdx) throws Exception;

    void deleteReviewFile(int rIdx) throws Exception;
}
