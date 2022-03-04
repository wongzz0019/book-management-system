package com.webtest.controller;

import com.webtest.entity.User;
import com.webtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String toIndex(@RequestParam String username,
                          @RequestParam String password,
                          RedirectAttributes attributes,
                          HttpSession session, Model model){
        //获取当前用户
        User user = userService.checkUser(username);
        if (user == null){
            attributes.addFlashAttribute("message","用户名错误");
            return "redirect:/toLogin";
        } else if(!user.getPassword().equals(password)){
            attributes.addFlashAttribute("message","密码错误");
            return "redirect:/toLogin";
        } else {
            session.setAttribute("user",user);
            model.addAttribute("userMessage",user);
            return "index";
        }
    }

    @GetMapping("/logouts")
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/toLogin";
    }
}
