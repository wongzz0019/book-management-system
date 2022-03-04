package com.webtest.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webtest.dao.BookMapper;
import com.webtest.entity.Book;
import com.webtest.service.BookService;
import com.webtest.vo.SearchBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> getAllBook() {
        return bookMapper.getAllBook();
    }

    @Override
    public PageInfo<Book> findBookList(Integer pageNumber, Integer pageSize, SearchBooks searchBooks){
        PageHelper.startPage(pageNumber,pageSize);
        List<Book> books = bookMapper.searchBookByIdOrName(searchBooks);
        PageInfo<Book> bookPageInfo=new PageInfo<Book>(books);
        return bookPageInfo;
    }

    @Override
    public Book findBookById(Integer bookId) {
        return bookMapper.findBookById(bookId);
    }

    @Override
    public int borrowBook(Book book) {
        return bookMapper.borrowBook(book);
    }
}
