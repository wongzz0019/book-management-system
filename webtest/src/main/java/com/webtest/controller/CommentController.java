package com.webtest.controller;

import com.webtest.entity.Comment;
import com.webtest.entity.User;
import com.webtest.service.CommentService;
import com.webtest.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("/tocomments")
    public String toComments(Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        User userById = adminUserService.getUserById(user.getId());
        model.addAttribute("userById",userById);
        List<Comment> comments = commentService.listComment();
        model.addAttribute("comments", comments);
        return "comment";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        User userById = adminUserService.getUserById(user.getId());
        if (userById.getRole().equals("admin")) {
            comment.setAdminComment(true);
            commentService.saveComment(comment);
            return "redirect:/tocomments";
        }
        commentService.saveComment(comment);
        return "redirect:/tocomments";
    }

    //删除评论
    @GetMapping("/comment/{id}/delete")
    public String delete(@PathVariable Long id, Comment comment, RedirectAttributes attributes,HttpSession session){
        commentService.deleteComment(id);
        User user = (User)session.getAttribute("user");
        User userById = adminUserService.getUserById(user.getId());
        attributes.addFlashAttribute("userById",userById);
        List<Comment> comments = commentService.listComment();
        attributes.addFlashAttribute("comments", comments);
        return "redirect:/tocomments";
    }
}
