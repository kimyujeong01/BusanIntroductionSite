package com.bitc.java501_team4.mapper;

import com.bitc.java501_team4.dto.CountDTO;
import com.bitc.java501_team4.dto.UserDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO user) throws Exception;

    int cntUser(UserDTO user) throws Exception;

    int isUserInfo(String uId, String uPw) throws Exception;

    UserDTO getUserInfo(String uId) throws Exception;

    CountDTO allCount() throws Exception;

    Page<UserDTO> selectUserPageList() throws Exception;

    void updateUserCkeck(int uIdx, String yn) throws Exception;
}
