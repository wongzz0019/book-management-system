package com.webtest;

import com.github.pagehelper.PageInfo;
import com.webtest.entity.Admin;
import com.webtest.entity.Book;
import com.webtest.service.BookService;
import com.webtest.service.BorrowService;
import com.webtest.service.admin.AdminBookService;
import com.webtest.service.admin.AdminUserService;
import com.webtest.vo.SearchBooks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminBookService adminBookService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @Test
    void contextLoads() {
        Admin admin = adminUserService.selectByUsername("admin");
        System.out.println(admin);
    }

    @Test
    void loadUserByUsername() {
        UserDetails admin = adminUserService.loadUserByUsername("admin");
        System.out.println(admin);
    }

    @Test
    void test1(){
        SearchBooks searchBooks = new SearchBooks();
        searchBooks.setName("java");
        PageInfo<Book> bookList = adminBookService.findBookList(1, 6, searchBooks);
        System.out.println(bookList);
    }
    @Test
    void test2(){
        Integer stuId = 2018124;
        String s = stuId.toString();
        List<Book> bookByBorrow = borrowService.findBookByBorrow(stuId);
        for (Book book:bookByBorrow
             ) {
            System.out.println(book+"sss");
            if (bookByBorrow != null && book.getUser().getStuid().equals(stuId)) {
                System.out.println("相同学号，重复借书");
                break;
            }
        }
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void test3(){
            System.out.println(passwordEncoder.encode("123456"));
    }
}
