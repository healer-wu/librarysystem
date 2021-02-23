//package com.wky.service;
//
//
//import com.wky.dao.BookMapper;
//import com.wky.entity.User;
//import com.wky.entity.User_book;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpSession;
//import javax.transaction.Transactional;
//
//@Service
//public class UnilService {
//    @Autowired
//    private BookMapper bookMapper;
//    @Transactional
//    public void util(HttpSession session,Integer id){
//        User currentUser = (User)session.getAttribute("currentUser");
//        bookMapper.insert_user_book(currentUser.getId(),id);
//        User_book user_book1=bookMapper.select_User_book(currentUser.getId(), id);
//        System.out.println(user_book1);
//        int i = bookMapper.t_borrow_return_insert(user_book1.getBook_id(), 1);
//        System.out.println(i);
//    }
//
//}
