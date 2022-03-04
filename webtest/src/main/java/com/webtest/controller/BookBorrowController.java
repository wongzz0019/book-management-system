package com.webtest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webtest.entity.Book;
import com.webtest.entity.User;
import com.webtest.service.BorrowService;
import com.webtest.service.ReturnService;
import com.webtest.service.UserService;
import com.webtest.vo.SearchBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookBorrowController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private ReturnService returnService;

    @Autowired
    private UserService userService;

    @RequestMapping("/borrow")
    public String toMyBorrow(HttpSession session, Model model,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        User user = (User)session.getAttribute("user");
        User userById = userService.findUserById(user.getId());
        model.addAttribute("userMessage",userById);
        PageHelper.startPage(pageNum,6);
        //根据学生id查所有其所有借阅信息
        List<Book> bookByBorrow = borrowService.findBookByBorrow(user.getStuid());
        PageInfo<Book> bookPageInfo = new PageInfo<>(bookByBorrow);
        model.addAttribute("pageInfo",bookPageInfo);
        return "borrow";
    }

    //搜索
    @PostMapping("/borrow/search")
    public String searchBooks(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                              Model model, SearchBooks searchBooks){
        PageHelper.startPage(pageNum,6);
//        书名或书号条件搜索
        PageInfo<Book> booklist = borrowService.findBookList(pageNum,6,searchBooks);
        model.addAttribute("pageInfo",booklist);
        return "borrow::bookList";
    }

    @GetMapping("/borrow/{bookId}/return")
    public String borrowBook(@PathVariable Integer bookId, RedirectAttributes attributes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(bookId);
        Integer stuId = user.getStuid();
        //根据学生id查所有其所有借阅信息
        List<Book> bookByBorrow = borrowService.findBookByBorrow(stuId);
        //删除借阅信息
        returnService.deleteBorrow(bookId,stuId);
        returnService.updateReturnBook(bookId);
        attributes.addFlashAttribute("message", "恭喜您，还书成功");
        return "redirect:/borrow";
    }
}
