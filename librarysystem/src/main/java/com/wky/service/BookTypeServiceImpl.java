package com.wky.service;

import javax.annotation.Resource;
import com.wky.dao.BookTypeDao;
import com.wky.entity.BookType;
import org.springframework.stereotype.Service;

@Service("bookTypeService")
public class BookTypeServiceImpl implements BookTypeService {
	@Resource
	private BookTypeDao bookTypeDao;
	
	
	@Override
	public void update(BookType bookType) {
		BookType origin = bookTypeDao.findId(bookType.getId());
		//把没有值的数据  换成原数据库的数据。
		bookType = repalce(bookType, origin);
		bookTypeDao.save(bookType);
	}
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public BookType repalce(BookType curr,BookType origin){
		
		if(curr.getName()==null){
			curr.setName(origin.getName());
		}
		if(curr.getOrderNo()==null){
			curr.setOrderNo(origin.getOrderNo());
		}
		if(curr.getCreateDateTime()==null){
			curr.setCreateDateTime(origin.getCreateDateTime());
		}
		if(curr.getUpdateDateTime()==null){
			curr.setUpdateDateTime(origin.getUpdateDateTime());
		}
		return curr;
	}

}
