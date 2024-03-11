package com.bitc.java501_team4.service;



import com.bitc.java501_team4.dto.CountDTO;
import com.bitc.java501_team4.dto.UserDTO;
import com.bitc.java501_team4.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(UserDTO user) throws Exception {
        userMapper.insertUser(user);
    }

    @Override
    public int cntUser(UserDTO user) throws Exception {
        return userMapper.cntUser(user);
    }

    @Override
    public int isUserInfo(String uId, String uPw) throws Exception {
        return userMapper.isUserInfo(uId,uPw);
    }

    @Override
    public UserDTO getUserInfo(String uId) throws Exception {
        return userMapper.getUserInfo(uId);
    }

    @Override
    public CountDTO allCount() throws Exception {
        return userMapper.allCount();
    }

    @Override
    public Page<UserDTO> selectUserPageList(int pageNum) throws Exception {
        PageHelper.startPage(pageNum, 15);
        return userMapper.selectUserPageList();
    }

    @Override
    public void updateUserCkeck(int uIdx, String yn) throws Exception {
        userMapper.updateUserCkeck(uIdx,yn);
    }

}
