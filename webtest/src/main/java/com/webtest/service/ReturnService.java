package com.webtest.service;

public interface ReturnService {
    //归还后调整书籍数量
    int updateReturnBook(Integer bookId);

    //归还书籍
    int deleteBorrow(Integer bookId,Integer stuId);
}
