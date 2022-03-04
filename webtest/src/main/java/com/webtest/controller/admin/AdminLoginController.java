package com.webtest.controller.admin;

import com.webtest.entity.Admin;
import com.webtest.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = {""})
    public String loginPage(){
        return "admin/login";
    }

    @RequestMapping("/tologin")
    public String toLogin(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        Admin user = adminUserService.findByNameAndPassword(username, password);
        if(user != null){
            //将密码置为空，防止密码传到前端
            user.setPassword(null);
            session.setAttribute("admin",user);
            return "admin/index";
        }else {
            //使用重定向，如果要传参只能使用RedirectAttributes，不能使用Model（有时传不了）
            attributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/admin";
    }
}
