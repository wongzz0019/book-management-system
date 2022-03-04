package com.webtest.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Admin implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private String role;

    public Admin() {
    }

    public Admin(Integer id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public void setUsername(String username) {
        this.username = username;
    }

    //    private String authority;
    //返回用户信息中所有认证权限,用户权限集
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //获取用户所有权限,可能有多个权限
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
