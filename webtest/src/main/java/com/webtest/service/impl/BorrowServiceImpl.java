package com.webtest.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webtest.dao.BorrowMapper;
import com.webtest.entity.Book;
import com.webtest.service.BorrowService;
import com.webtest.vo.SearchBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowMapper borrowMapper;

    @Override
    public int insertBorrow(Integer bookId, Integer stuId, Date borrowDate) {
        return borrowMapper.insertBorrow(bookId,stuId,borrowDate);
    }

    @Override
    public List<Book> findBookByBorrow(Integer id) {
        return borrowMapper.findBookByBorrow(id);
    }

    @Override
    public PageInfo<Book> findBookList(Integer pageNumber, Integer pageSize, SearchBooks searchBooks){
        PageHelper.startPage(pageNumber,pageSize);
        List<Book> books = borrowMapper.searchBorrowBookByIdOrName(searchBooks);
        PageInfo<Book> bookPageInfo=new PageInfo<Book>(books);
        return bookPageInfo;
    }
}
