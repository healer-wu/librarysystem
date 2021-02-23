package com.wky.controller.houtai;

import java.util.List;

import javax.annotation.Resource;

import com.wky.dao.BookDao;
import com.wky.dao.BookTypeDao;
import com.wky.entity.Book;
import com.wky.entity.BookType;
import com.wky.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/houtai/book")
public class HouTai_Book_Controller {
	@Resource
	private BookDao bookDao;
	@Resource
	private BookService bookService;
	@Resource
	private BookTypeDao bookTypeDao;
	
	
	/**
	 * /houtai/book/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "图书管理");
		mav.setViewName("/admin/page/book/book_manage");
		return mav;
	}

	@RequestMapping("/borrow_return")
	public ModelAndView borrow_retuen() throws Exception {
		ModelAndView mav1 = new ModelAndView();
		mav1.addObject("title", "图书借阅归还");
//		mav.setViewName("/admin/page/book/book_manage");
		mav1.setViewName("/admin/page/book/borrow_return");
		return mav1;
	}


	@RequestMapping("/list")
	public ModelAndView list() throws Exception {
		ModelAndView mav1 = new ModelAndView();
		mav1.addObject("title", "图书借阅表");
//		mav.setViewName("/admin/page/book/book_manage");
		mav1.setViewName("/admin/page/book/list");
		return mav1;
	}




	/**
	 * /houtai/book/add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		Pageable pageable=new PageRequest(0,100, Sort.Direction.ASC,"orderNo");
		Page<BookType> list = bookTypeDao.findAll(pageable);
		List<BookType> bookTypeList = list.getContent();//拿到list集合
		mav.addObject("bookTypeList", bookTypeList);
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/book/add");
		mav.setViewName("/admin/page/book/add_update");
		return mav;
	}
	
	
	/**
	 * /houtai/book/edit?id=1
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Pageable pageable=new PageRequest(0,100, Sort.Direction.ASC,"orderNo");
		Page<BookType> list = bookTypeDao.findAll(pageable);
		List<BookType> bookTypeList = list.getContent();//拿到list集合
		mav.addObject("bookTypeList", bookTypeList);
		
		
		Book book = bookDao.findId(id);
		mav.addObject("book", book);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/book/update?id=" + id);
		mav.setViewName("/admin/page/book/add_update");
		return mav;
	}
	
	
	
	
	
}
