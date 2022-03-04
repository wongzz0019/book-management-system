package com.webtest.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webtest.entity.Book;
import com.webtest.service.admin.AdminBookService;
import com.webtest.vo.SearchBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminBookController {
    @Autowired
    private AdminBookService adminBookService;

    //列表
    @RequestMapping("/books")
    public String showBooks(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,6);
        List<Book> allBook = adminBookService.getAllBook();
        PageInfo<Book> bookPageInfo = new PageInfo<>(allBook);
        model.addAttribute("pageInfo",bookPageInfo);
        return "admin/book";
    }

    //条件搜索
    @PostMapping("/books/search")
    public String searchBooks(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                              Model model, SearchBooks searchBooks){
        PageHelper.startPage(pageNum,6);
        PageInfo<Book> booklist = adminBookService.findBookList(pageNum,6,searchBooks);
        model.addAttribute("pageInfo",booklist);
        return "admin/book::bookList";
    }

    @GetMapping("/books/input")
    public String toAddBooks(Model model){
        model.addAttribute("books",new Book());
        return "admin/book-input";
    }

    @PostMapping("/books/add")
    public String addBooks(Book book, RedirectAttributes attributes){
        if (adminBookService.findBookByName(book.getName())!=null){
            attributes.addFlashAttribute("message","该图书在管中已被添加：请添加其他书籍");
            return "redirect:/admin/books/input";
        }
        adminBookService.insertBook(book);
        attributes.addFlashAttribute("message","添加书籍成功！！！");
        return "redirect:/admin/books";
    }

    //通过图书id获取图书信息，传给前端回显
    @GetMapping("/books/{id}/update")
    public String toUpdateBooks(@PathVariable Integer id, Model model){
        Book bookById = adminBookService.findBookById(id);
        model.addAttribute("books",bookById);
        return "admin/book-input";
    }

    @PostMapping("/books/update")
    public String updateBooks(Book book,RedirectAttributes attributes){
        adminBookService.updateBook(book);
        attributes.addFlashAttribute("message", "修改书籍成功！！！");
        return "redirect:/admin/books";
    }

    @GetMapping("/books/{id}/delete")
    public String deleteBooks(@PathVariable Integer id,RedirectAttributes attributes){
        adminBookService.deleteBookById(id);
        attributes.addFlashAttribute("message","删除书籍成功");
        return "redirect:/admin/books";
    }
}
