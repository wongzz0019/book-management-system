package com.webtest.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private Integer id;
    private String username;
    private String stuname;
    private Integer sex;
    private String password;
    private Integer stuid;
    private String classes;
    private String role;

//    private String authority;
    //返回用户信息中所有认证权限,用户权限集
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //获取用户所有权限
        String[] roles = role.split(",");
        //取出所有的权限，并且给每个权限都要进行认证，添加到 SimpleGrantedAuthority()
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        //遍历roles，取出每一个权限，添加到 简单的授予认证类
        for (String s : roles) {
            //添加了 ROLE_ 前缀 为了前端页面thymeleaf-security 中自带的 hasRole()方法，能得到ROLE_格式的角色信息
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+s));
        }
        //返回到已经被 授予认证的权限集合 这里面的角色所拥有的权限都已经被spring security所知道
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    //返回用户名
    @Override
    public String getUsername() {
        return username;
    }

    //账户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //凭证是否过期，认证后的密码
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //用户是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }

    public User() {
    }

    public User(Integer id, String username, String stuname, Integer sex, String password, Integer stuid, String classes, String role) {
        this.id = id;
        this.username = username;
        this.stuname = stuname;
        this.sex = sex;
        this.password = password;
        this.stuid = stuid;
        this.classes = classes;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", stuname='" + stuname + '\'' +
                ", sex=" + sex +
                ", password='" + password + '\'' +
                ", stuid=" + stuid +
                ", classes='" + classes + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
