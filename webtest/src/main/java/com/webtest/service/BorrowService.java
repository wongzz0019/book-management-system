package com.webtest.service;

import com.github.pagehelper.PageInfo;
import com.webtest.entity.Book;
import com.webtest.vo.SearchBooks;

import java.util.Date;
import java.util.List;

public interface BorrowService {
    //插入借阅信息
    int insertBorrow(Integer bookId, Integer stuId, Date borrowDate);

    //查找借阅信息
    List<Book> findBookByBorrow(Integer id);

    //搜索书籍
    PageInfo<Book> findBookList(Integer pageNumber, Integer pageSize, SearchBooks searchBooks);
}
