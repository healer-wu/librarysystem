package com.wky.dao;

import com.wky.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface BookDao extends JpaRepository<Book,Integer>,JpaSpecificationExecutor< Book> {
	
	
	@Query(value="select * from t_book where id = ?1",nativeQuery = true)
	public Book  findId(Integer id);

//
//	@Transactional
//	@Modifying
//	@Query("update t_book b set b.num = b.num+1 where b.id = ?1")
//	public void saveNum(Integer id);


//	@Modifying
//	@Query("update t_book b set b.num = b.num-1 where b.id = ?1")
//	public int updaten_return(Integer id);
}
