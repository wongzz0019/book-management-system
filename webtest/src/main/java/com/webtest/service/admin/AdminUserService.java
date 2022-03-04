package com.webtest.service.admin;

import com.github.pagehelper.PageInfo;
import com.webtest.entity.Admin;
import com.webtest.entity.User;
import com.webtest.vo.SearchStudent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface AdminUserService extends UserDetailsService {

    //查找用户名密码
    Admin findByNameAndPassword(String username, String password);

    //查找用户名
    Admin selectByUsername(String username);

    //加载用户信息，按照姓名
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    //查找所有用户信息
    List<User> findAllUser();

    //搜索学生
    PageInfo<User> findStudentList(Integer pageNumber, Integer pageSize, SearchStudent searchStudent);

    //根据ID找用户
    User getUserById(Integer id);

    //根据stuId查找用户
    User getUserByStuid(Integer stuid);

    //根据username查找用户
    User getUserByUsername(String username);

    //添加用户
    int insertUser(User user);

    //更新用户信息
    int updateUser(User user);

    //删除书籍
    int deleteUserById(Integer id);
}
