package com.wky.dao;

import com.wky.entity.User_book;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;


@Mapper
public interface BookMapper {
    @Update("update t_book b set b.num = b.num-1 where b.id = #{i}")
    public int borrow(Integer i);

    @Delete("DELETE FROM t_user_book WHERE t_user_book.id=#{id}")
    public void delete(Integer id);

    @Update("update t_book b set b.num = b.num+1 where b.id = #{i}")
    public  int return_book(Integer a);

    @Select("select t_book.num from t_book where t_book.id= #{i}")
    public  int countBook(Integer i);

    @Insert("INSERT INTO t_user_book(id,user,book_id,borrow_time) VALUES(null,#{i},#{j},#{date})")
    public  void insert_user_book(Integer i, Integer j, Date date);

    @Select("select * from t_user_book where t_user_book.user= #{i} and t_user_book.book_id=#{j}")
    public List<User_book> select_User_book(Integer i, Integer j);

    @Select("select * from t_user_book")
    public List<User_book> selectAll();

    @Select("select count(*) from t_user_book")
    public int selectConut();

    @Update("update t_borrow_return b set b.user_book = b.user_book+1 where b.id = #{i}")
    public int t_borrow_return_update(Integer i);

    @Insert("INSERT INTO t_borrow_return(id,user_book) VALUES(#{i},#{j})")
    public int t_borrow_return_insert(Integer i,Integer j);

    @Select("select t_borrow_return.user_book from t_borrow_return where t_borrow_return.id= #{i}")
    public int t_borrow_return_select(Integer i);

}

