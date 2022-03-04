package com.webtest.controller;

import com.webtest.entity.User;
import com.webtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/","/index"})
    public String toIndex(HttpServletRequest request, HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        User userById = userService.findUserById(user.getId());
        model.addAttribute("userMessage",userById);
        return "index";
    }
}
