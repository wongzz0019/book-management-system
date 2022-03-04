package com.webtest.dao;

import com.webtest.entity.User;
import com.webtest.vo.UpdateUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    //查找用户
    User getUserByName(@Param("username") String username);

    //修改用户信息
    int updateUser(UpdateUser updateUser);

    //根据id查找用户
    User findUserById(Integer id);

    //根据学生id查找用户
    User findUserBystuId(Integer stuid);

    //注册添加用户
    int insertUser(User user);
}
