package com.wky.service;

import com.wky.entity.User_book;

import java.util.List;

public interface Borrow_return_Service {
    public List<User_book> list(Integer page,Integer limit);
    public Integer getTotil();
}
