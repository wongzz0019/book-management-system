package com.webtest.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReturnMapper {
    //归还后调整书籍数量
    int updateReturnBook(Integer bookId);

    //归还书籍
    int deleteBorrow(Integer bookId,Integer stuId);
}
