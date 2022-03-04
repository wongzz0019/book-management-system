package com.webtest.dao;

import com.webtest.entity.Book;
import com.webtest.vo.SearchBooks;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {
    //查找所有书籍信息
    List<Book> getAllBook();

    //搜索书籍
    List<Book> searchBookByIdOrName(SearchBooks searchBooks);

    //查找书籍
    Book findBookById(Integer bookId);

    //借阅书籍
    int borrowBook(Book book);
}
