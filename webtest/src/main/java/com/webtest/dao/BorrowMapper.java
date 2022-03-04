package com.webtest.dao;

import com.webtest.entity.Book;
import com.webtest.vo.SearchBooks;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface BorrowMapper {

    //插入借阅信息
    int insertBorrow(Integer bookId, Integer stuId, Date borrowDate);

    //查找借阅信息
    List<Book> findBookByBorrow(Integer stuId);

    //搜索书籍
    List<Book> searchBorrowBookByIdOrName(SearchBooks searchBooks);
}
