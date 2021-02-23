package com.wky.service;

import com.wky.dao.BookMapper;
import com.wky.entity.User_book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//图书借阅列表
@Service
public class Borrow_return_ServiceImpl implements Borrow_return_Service {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<User_book> list(Integer page, Integer limit) {
        return bookMapper.selectAll();
    }

    @Override
    public Integer getTotil() {
        return bookMapper.selectConut();
    }
}
