package com.bitc.java501_team4.service;

import com.bitc.java501_team4.common.FileUtils;
import com.bitc.java501_team4.dto.community.*;
import com.bitc.java501_team4.mapper.CommunityMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private FileUtils fileUtils;

    @Override
    public void updateReviewCount(int rIdx) throws Exception {
        communityMapper.updateReviewCount(rIdx);
    }

    @Override
    public ReviewBoardDTO selectBoardDetail(int rIdx) throws Exception {
        List<reviewFileDTO> reviewFile = communityMapper.selectReviewFileList(rIdx);
        ReviewBoardDTO board = communityMapper.selectBoardDetail(rIdx);
        board.setFileList(reviewFile);
        return board;
    }

    @Override
    public List<ReviewCommentDTO> selectCommentDetail(int rIdx) throws Exception {
        return communityMapper.selectCommentDetail(rIdx);
    }

    @Override
    public void writeBoard(ReviewBoardDTO reviewBoard) throws Exception {
        communityMapper.writeBoard(reviewBoard);
    }

    @Override
    public void updateReview(ReviewBoardDTO board, MultipartHttpServletRequest multipart) throws Exception {
        List<reviewFileDTO> reviewFile = communityMapper.selectFiledelet(board.getRIdx());
        for (int i = 0; i < reviewFile.size(); i ++){
            String oFile = reviewFile.get(i).getSFile();
            fileUtils.deleteFIle(oFile);
        }
        communityMapper.deleteReviewFile(board.getRIdx());

        List<reviewFileDTO> fileList = fileUtils.parseFileInfo(board.getRIdx(),board.getRCreateId(),multipart);
        if (!CollectionUtils.isEmpty(fileList)){
            communityMapper.insertReviewFIleList(fileList);
        }

        communityMapper.updateReview(board);
    }

    @Override
    public void deleteRevAllment(int rIdx) throws Exception {
        communityMapper.deleteRevAllment(rIdx);
    }

    @Override
    public void deleteReview(int rIdx) throws Exception {
        List<reviewFileDTO> reviewFIle = communityMapper.selectFiledelet(rIdx);

        for (int i = 0; i < reviewFIle.size(); i++){
            String oFile = reviewFIle.get(i).getSFile();
            fileUtils.deleteFIle(oFile);
        }

        communityMapper.deleteReviewFile(rIdx);
        communityMapper.deleteReview(rIdx);
    }

    @Override
    public void commentReviewWrite(ReviewCommentDTO revComm) throws Exception {
        communityMapper.commentReviewWrite(revComm);
    }

    @Override
    public void commentReviewDelete(int revIdx, int revBoardIdx) throws Exception {
        communityMapper.commentReviewDelete(revIdx,revBoardIdx);
    }

    @Override
    public Page<QnaDTO> selectQnaList(int pageNum) throws Exception {
        PageHelper.startPage(pageNum, 10);

        return communityMapper.selectQnaList();
    }

    @Override
    public QnaDTO selectQnaDetail(int qIdx) throws Exception {
        return communityMapper.selectQnaDetail(qIdx);
    }

    @Override
    public List<QnaComDTO> selectQnaComList(int qIdx) throws Exception {
        return communityMapper.selectQnaComList(qIdx);
    }

    @Override
    public void insertQna(QnaDTO qna) throws Exception {
        communityMapper.insertQna(qna);
    }

    @Override
    public void updateQna(QnaDTO qna) throws Exception {
        communityMapper.updateQna(qna);
    }

    @Override
    public void deleteAllMent(int qIdx) throws Exception {
        communityMapper.deleteAllMent(qIdx);
    }

    @Override
    public void deleteQna(int qIdx) throws Exception {
        communityMapper.deleteQna(qIdx);
    }

    @Override
    public void insertMent(QnaComDTO ment) throws Exception {
        communityMapper.insertMent(ment);
    }

    @Override
    public void deleteMent(int qcIdx, int qcBoardIdx) throws Exception {
        communityMapper.deleteMent(qcIdx,qcBoardIdx);
    }

    @Override
    public int cntUserId(String qCreateId) throws Exception {
        return communityMapper.cntUserId(qCreateId);
    }

    @Override
    public reviewFileDTO selectFileInfo(int idx, int rIdx) throws Exception {
        return communityMapper.selectFileInfo(idx,rIdx);
    }

    @Override
    public void writeReview(ReviewBoardDTO reviewBoard, MultipartHttpServletRequest multipart) throws Exception {
        communityMapper.writeReview(reviewBoard);
        List<reviewFileDTO> fileList = fileUtils.parseFileInfo(reviewBoard.getRIdx(),reviewBoard.getRCreateId(),multipart);

        if (!CollectionUtils.isEmpty(fileList)){

            communityMapper.insertReviewFIleList(fileList);
        }

    }

    @Override
    public Page<ReviewBoardDTO> selectBoardPageList(int pageNum) throws Exception {
        PageHelper.startPage(pageNum, 10);
        return communityMapper.selectBoardPageList();
    }


}
