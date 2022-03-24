package com.webtest.controller;

import com.webtest.entity.User;
import com.webtest.service.UserService;
//import com.webtest.util.GeneratePassword;
import com.webtest.vo.UpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/change")
    public String updateUser(UpdateUser updateUser,HttpSession session, RedirectAttributes attributes){
        System.out.println("change: " + updateUser);
        User user = (User)session.getAttribute("user");
        //从session中获取登录用户的id，赋值给vo对象
        Integer integer = Integer.valueOf(user.getId());
        updateUser.setId(integer);
        userService.updateUser(updateUser);
        User userById = userService.findUserById(user.getId());
        System.out.println(userById);
        attributes.addFlashAttribute("userById",userById);
        return "redirect:/alter";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UpdateUser updateUser,RedirectAttributes attributes){
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("registerUser: " + updateUser);

        Integer stuid = updateUser.getStuid();
        if(userService.findUserBystuId(stuid)!=null){
            attributes.addFlashAttribute("message","请确定你的学号是否输入有误");
            return "redirect:/toRegister";
        }

        User user = new User();
        user.setUsername(updateUser.getUsername());
        user.setStuname(updateUser.getStuname());
        user.setSex(updateUser.getSex());
        //加密
        user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
//        user.setPassword(updateUser.getPassword());
        user.setStuid(updateUser.getStuid());
        user.setClasses(updateUser.getClasses());
        userService.insertUser(user);

        attributes.addFlashAttribute("message","注册成功");
        return "redirect:/toLogin";
    }
}
