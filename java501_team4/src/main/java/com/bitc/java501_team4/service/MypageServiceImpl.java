package com.bitc.java501_team4.service;

import com.bitc.java501_team4.dto.community.QnaDTO;
import com.bitc.java501_team4.dto.community.ReviewBoardDTO;
import com.bitc.java501_team4.mapper.MypageMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MypageServiceImpl implements MypageService {

    @Autowired
    private MypageMapper mypageMapper;

    @Override
    public List<QnaDTO> userQnaList(String uId) throws Exception {
        return mypageMapper.userQnaList(uId);
    }

    @Override
    public List<ReviewBoardDTO> userReviewList(String uId) throws Exception {
        return mypageMapper.userReviewList(uId);
    }

    @Override
    public Page<QnaDTO> userQnaListPaging(int pageNum,String uId) throws Exception {
        PageHelper.startPage(pageNum, 5);

        return mypageMapper.userQnaListPaging(uId);
    }

    @Override
    public Page<ReviewBoardDTO> userReviewListPaging(int pageNum2,String uId) throws Exception {
        PageHelper.startPage(pageNum2, 5);

        return mypageMapper.userReviewListPaging(uId);
    }

}
