package com.webtest.service.impl;

import com.webtest.dao.UserMapper;
import com.webtest.entity.User;
import com.webtest.service.UserService;
import com.webtest.vo.UpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public int updateUser(UpdateUser updateUser) {
        return userMapper.updateUser(updateUser);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findUserBystuId(Integer stuid) {
        return userMapper.findUserBystuId(stuid);
    }

    @Override
    public int insertUser(User user) {
        user.setRole("stu");
        return userMapper.insertUser(user);
    }
}
