package com.webtest.controller;

import com.webtest.entity.User;
import com.webtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AboutController {

    @Autowired
    private UserService userService;

    @RequestMapping("/alter")
    public String toAboutMe(HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        User userById = userService.findUserById(user.getId());
        model.addAttribute("userById",userById);
        return "about";
    }
}
