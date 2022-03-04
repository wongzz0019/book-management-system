package com.webtest.dao.admin;

import com.webtest.entity.Book;
import com.webtest.vo.SearchBooks;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminBookMapper {
    //查找所有书籍信息
    List<Book> getAllBook();

    //根据id查找book
    Book findBookById(Integer id);

    //根据bookname查找book
    Book findBookByName(String name);

    //添加book
    int insertBook(Book book);

    //修改用户信息
    int updateBook(Book book);

    //查找最高的书号
    int findMaxBookId();

    //删除书籍
    int deleteBookById(Integer id);

    //搜索书籍
    List<Book> searchBookByIdOrName(SearchBooks searchBooks);
}
