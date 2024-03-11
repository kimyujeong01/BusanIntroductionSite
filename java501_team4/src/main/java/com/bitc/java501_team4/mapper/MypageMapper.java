package com.bitc.java501_team4.mapper;

import com.bitc.java501_team4.dto.community.QnaDTO;
import com.bitc.java501_team4.dto.community.ReviewBoardDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MypageMapper {

    List<QnaDTO> userQnaList(String uId) throws Exception;

    List<ReviewBoardDTO> userReviewList(String uId) throws Exception;

    Page<QnaDTO> userQnaListPaging(String uId) throws Exception;
    Page<ReviewBoardDTO> userReviewListPaging(@Param("uId")String uId) throws Exception;
}
