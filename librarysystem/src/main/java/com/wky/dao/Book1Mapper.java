package com.wky.dao;

import com.wky.entity.Book1;
import com.wky.entity.User1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface Book1Mapper {
    @Select("select * from t_book ")
    public List<Book1> listBook1();


    @Select("select * from t_a_user")
    public List<User1> listUser1();

    @Update("update t_a_user b set b.pwd =#{i} where b.id = #{j}")
    public void updatepwd(String i,int j);


}
