package com.webtest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webtest.entity.Book;
import com.webtest.entity.User;
import com.webtest.service.BookService;
import com.webtest.service.BorrowService;
import com.webtest.service.UserService;
import com.webtest.vo.SearchBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private UserService userService;

    //图书列表
    @RequestMapping("/books")
    public String toBookList(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, HttpSession session){
        PageHelper.startPage(pageNum,6);
        List<Book> allBook = bookService.getAllBook();
        PageInfo<Book> bookPageInfo = new PageInfo<>(allBook);
        model.addAttribute("pageInfo",bookPageInfo);
        User user = (User)session.getAttribute("user");
        User userById = userService.findUserById(user.getId());
        model.addAttribute("userMessage",userById);
        return "book";
    }

    //条件搜索
    @PostMapping("/books/search")
    public String searchBooks(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                              Model model, SearchBooks searchBooks){
        PageHelper.startPage(pageNum,6);
        PageInfo<Book> booklist = bookService.findBookList(pageNum,6,searchBooks);
        model.addAttribute("pageInfo",booklist);
        return "book :: bookList";
    }

    //图书借阅
    @GetMapping("/books/{bookId}/borrow")
    public String borrowBook(@PathVariable Integer bookId, RedirectAttributes attributes,HttpSession session) {
        User user = (User) session.getAttribute("user");
        //当前学生id
        Integer stuId = user.getStuid();
        Book bookById = bookService.findBookById(bookId);
        //获取当前学生所有借阅信息
        List<Book> bookByBorrow = borrowService.findBookByBorrow(stuId);
        if (bookById.getCount() <= 0) {
            attributes.addFlashAttribute("message", "对不起，该书籍已经借光，请稍后再试");
            return "redirect:/books";
        } else if (bookByBorrow != null) {
            for (Book book : bookByBorrow) {
                if (book.getUser().getStuid().equals(stuId) && book.getBookId() == bookId) {
                    attributes.addFlashAttribute("message", "对不起，您已经借了该书，请勿重复借书");
                    return "redirect:/books";
                }
            }
        }
        //用户新增借阅
        bookService.borrowBook(bookById);
        Date date = new Date();
        borrowService.insertBorrow(bookId, user.getStuid(), date);
        attributes.addFlashAttribute("message", "恭喜您，借阅成功");
        return "redirect:/books";
    }
}
