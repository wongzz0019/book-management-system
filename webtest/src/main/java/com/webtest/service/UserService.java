package com.webtest.service;

import com.webtest.entity.User;
import com.webtest.vo.UpdateUser;

public interface UserService {
    //查找用户
    User checkUser(String username);

    //修改用户信息
    int updateUser(UpdateUser updateUser);

    //根据id查找用户
    User findUserById(Integer id);

    //根据学生id查找用户
    User findUserBystuId(Integer stuid);

    //注册添加用户
    int insertUser(User user);
}
