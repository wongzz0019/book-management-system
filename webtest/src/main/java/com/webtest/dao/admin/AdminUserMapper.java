package com.webtest.dao.admin;

import com.webtest.entity.Admin;
import com.webtest.entity.User;
import com.webtest.vo.SearchStudent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminUserMapper {

    //查找用户名密码
    Admin findAdminByNameAndPassword(String username, String password);

    //查找用户名
    Admin selectAdminByUsername(String username);

    //查找所有用户信息
    List<User> findAllUser();

    //搜索用户
    List<User> searchStudentByIdOrName(SearchStudent searchStudent);

    //根据ID找用户
    User getUserById(Integer id);

    //根据stuId查找用户
    User getUserByStuid(Integer stuid);

    //根据username查找用户
    User getUserByUsername(String username);

    //保存User
    int insertUser(User user);

    //更新用户信息
    int updateUser(User user);

    //删除用户
    int deleteUserById(Integer id);
}
