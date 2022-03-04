package com.webtest.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//加密管理员账号的密码
class BCryptPasswordEncoders {
    public static String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String s = encodePassword("123456");
        System.out.println(s);
    }
}
