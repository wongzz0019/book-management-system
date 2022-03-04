package com.webtest.service;

import com.github.pagehelper.PageInfo;
import com.webtest.entity.Book;
import com.webtest.vo.SearchBooks;

import java.util.List;

public interface BookService {
    //查找所有书籍信息
    List<Book> getAllBook();

    //搜索书籍
    PageInfo<Book> findBookList(Integer pageNumber, Integer pageSize, SearchBooks searchBooks);

    //查找书籍
    Book findBookById(Integer bookId);

    //借阅书籍
    int borrowBook(Book book);
}
