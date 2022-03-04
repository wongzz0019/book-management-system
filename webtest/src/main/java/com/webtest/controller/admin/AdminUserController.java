package com.webtest.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webtest.entity.User;
import com.webtest.service.admin.AdminUserService;
import com.webtest.vo.SearchStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("/students")
    public String listAllStudent(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, Model model){
        PageHelper.startPage(pageNum,6);
        List<User> allUser = adminUserService.findAllUser();
        PageInfo<User> userPageInfo = new PageInfo<>(allUser);
        model.addAttribute("pageInfo",userPageInfo);
        return "admin/student";
    }

    @PostMapping("/students/search")
    public String searchStudent(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                              Model model, SearchStudent searchStudent){
        PageHelper.startPage(pageNum,6);
        PageInfo<User> userlist = adminUserService.findStudentList(pageNum,6,searchStudent);
        model.addAttribute("pageInfo",userlist);
        return "admin/student :: stuList";
    }

    @GetMapping("/students/input")
    public String toAddStu(Model model){
        model.addAttribute("students",new User());
        return "admin/student-input";
    }

    @PostMapping("/students/add")
    public String addStu(@Valid User user, RedirectAttributes attributes){
        System.out.println("前端传过来的user：" + user);
        if (adminUserService.getUserByUsername(user.getUsername())!=null){
            attributes.addFlashAttribute("message","该用户名已被注册，请重试，建议使用学号");
            return "redirect:/admin/students/input";
        } else if(adminUserService.getUserByStuid(user.getStuid())!=null){
            attributes.addFlashAttribute("message","请检查你的学号是否是你自己的");
            return "redirect:/admin/students/input";
        }
        adminUserService.insertUser(user);
        attributes.addFlashAttribute("message","添加成功！！！");
        return "redirect:/admin/students";
    }

    @GetMapping("/students/{id}/update")
    public String toUpdateStu(@PathVariable Integer id,Model model){
        System.out.println("toUpdateStu的id：" + id);
        User userById = adminUserService.getUserById(id);
        System.out.println("userById： "+ userById);
        model.addAttribute("students",userById);
        return "admin/student-input";
    }

    @PostMapping("/students/update")
    public String updateStu(User user,RedirectAttributes attributes){
        adminUserService.updateUser(user);
        attributes.addFlashAttribute("message", "修改成功！！！");
        return "redirect:/admin/students";
    }

    @GetMapping("/students/{id}/delete")
    public String deleteBooks(@PathVariable Integer id,RedirectAttributes attributes){
        adminUserService.deleteUserById(id);
        attributes.addFlashAttribute("message","删除用户成功");
        return "redirect:/admin/books";
    }
}
