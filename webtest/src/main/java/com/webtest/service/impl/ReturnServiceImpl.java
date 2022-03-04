package com.webtest.service.impl;

import com.webtest.dao.ReturnMapper;
import com.webtest.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnMapper returnMapper;

    @Override
    public int updateReturnBook(Integer bookId) {
        return returnMapper.updateReturnBook(bookId);
    }

    @Override
    public int deleteBorrow(Integer bookId, Integer stuId) {
        return returnMapper.deleteBorrow(bookId,stuId);
    }
}
