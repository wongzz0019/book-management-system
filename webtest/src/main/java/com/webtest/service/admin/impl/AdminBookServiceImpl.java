package com.webtest.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webtest.dao.admin.AdminBookMapper;
import com.webtest.entity.Book;
import com.webtest.service.admin.AdminBookService;
import com.webtest.vo.SearchBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminBookServiceImpl implements AdminBookService {

    @Autowired
    private AdminBookMapper adminBookMapper;

    @Override
    public List<Book> getAllBook() {
        return adminBookMapper.getAllBook();
    }

    @Override
    public Book findBookById(Integer id) {
        return adminBookMapper.findBookById(id);
    }

    @Override
    public Book findBookByName(String name) {
        return adminBookMapper.findBookByName(name);
    }

    @Override
    public int insertBook(Book book) {
        int maxBookId = adminBookMapper.findMaxBookId();
        Integer max = maxBookId;
        book.setBookId(max+1);
        return adminBookMapper.insertBook(book);
    }

    @Override
    public int updateBook(Book book) {
        book.setBookId(adminBookMapper.findBookById(book.getId()).getBookId());
        return adminBookMapper.updateBook(book);
    }

    @Override
    public int findMaxBookId() {
        return adminBookMapper.findMaxBookId();
    }

    @Override
    public int deleteBookById(Integer id) {
        return adminBookMapper.deleteBookById(id);
    }

    @Override
    public PageInfo<Book> findBookList(Integer pageNumber, Integer pageSize, SearchBooks searchBooks){
        PageHelper.startPage(pageNumber,pageSize);
        List<Book> books = adminBookMapper.searchBookByIdOrName(searchBooks);
        PageInfo<Book> bookPageInfo=new PageInfo<Book>(books);
        return bookPageInfo;
    }
}
