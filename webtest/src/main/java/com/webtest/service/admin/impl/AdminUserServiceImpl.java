package com.webtest.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webtest.dao.admin.AdminUserMapper;
import com.webtest.entity.Admin;
import com.webtest.entity.User;
import com.webtest.service.admin.AdminUserService;
import com.webtest.vo.SearchStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public Admin findByNameAndPassword(String username, String password) {
        return adminUserMapper.findAdminByNameAndPassword(username,password);
    }

    @Override
    public Admin selectByUsername(String username){
        return adminUserMapper.selectAdminByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("不存在该用户!");
        }
        return user;//此时的user，是被UserDetails包装过具有可认证身份信息的用户对象。
    }

    @Override
    public List<User> findAllUser() {
        return adminUserMapper.findAllUser();
    }

    @Override
    public PageInfo<User> findStudentList(Integer pageNumber, Integer pageSize, SearchStudent searchStudent){
        PageHelper.startPage(pageNumber,pageSize);
        List<User> users = adminUserMapper.searchStudentByIdOrName(searchStudent);
        PageInfo<User> userPageInfo = new PageInfo<User>(users);
        return userPageInfo;
    }

    @Override
    public User getUserById(Integer id) {
        return adminUserMapper.getUserById(id);
    }

    @Override
    public User getUserByStuid(Integer stuid) {
        return adminUserMapper.getUserByStuid(stuid);
    }

    @Override
    public User getUserByUsername(String username) {
        return adminUserMapper.getUserByUsername(username);
    }

    @Override
    public int insertUser(User user) {
        user.setRole("stu");
        return adminUserMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return adminUserMapper.updateUser(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return adminUserMapper.deleteUserById(id);
    }
}
