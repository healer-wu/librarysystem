package com.wky.controller.admin;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.wky.dao.Book1Mapper;
import com.wky.dao.BookDao;
import com.wky.dao.BookMapper;
import com.wky.dao.UserDao;
import com.wky.entity.*;
import com.wky.service.BookService;

import com.wky.service.Borrow_return_Service;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin/book")
public class Admin_Book_Controoler {
//	@Autowired
//	private UnilService unilService;
	@Autowired
	private Book1Mapper book1Mapper;
	@Autowired
	private BookMapper bookMapper;

	@Autowired
	private Borrow_return_Service borrow_return_service;
	@Autowired
	private BookDao bookDao;
	@Resource
	private BookService bookService;

	/**
	 * /admin/book/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public JSONObject add(@Valid Book book, BindingResult bindingResult, HttpSession session, HttpServletResponse response,
						  HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();

		if (bindingResult.hasErrors()) {
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		} else {
			User currentUser = (User)session.getAttribute("currentUser");
			System.out.println(currentUser);
			if(currentUser.getRole().getId()==2){
				result.put("success",false);
				result.put("msg","没有权限，请联系管理员");
				return result;
			}
			book.setCreateDateTime(new Date());
			bookDao.save(book);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;
		}
	}
	

	/**
	 *  /admin/book/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public JSONObject update(@Valid  Book book  ,BindingResult bindingResult, HttpSession session,HttpServletRequest request)throws Exception {
		JSONObject result = new JSONObject();
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
//			result.put("msg","没有修改权限，请联系管理员");
			return result;
		}else{
			User currentUser = (User)session.getAttribute("currentUser");
			System.out.println(currentUser);
			if(currentUser.getRole().getId()==2){
				result.put("success",false);
				result.put("msg","没有权限，请联系管理员");
				return result;
			}
			book.setUpdateDateTime(new Date());
			bookService.update(book);
			result.put("success", true);
			result.put("msg", "修改成功");
			return result;
		}
	}
	

	/**
	 * /admin/book/list
	 * page  默认1
	 * limit   数据多少
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Book> list = bookService.list(map, page-1, limit);
		long total = bookService.getTotal(map);

		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

	/**
	 * /admin/book/borrow_return_list
	 * page    默认1
	 *  limit   数据多少
	 */
	@ResponseBody
	@RequestMapping("/borrow_return_list")
	public Map<String, Object> borrow_return_list(@RequestParam(value = "page", required = false) Integer page,
									@RequestParam(value = "limit", required = false) Integer limit,
									HttpServletResponse response,
									HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer totil = borrow_return_service.getTotil();
		System.out.println("totil = " + totil);
		List<Borrrow_Return> borrow_returns = new ArrayList<>();
		List<User_book> list = borrow_return_service.list(page, limit);
		System.out.println("list = " + list);
		List<Book1> list1=book1Mapper.listBook1();
		System.out.println("list1 = " + list1);
		List<User1> user1s = book1Mapper.listUser1();
		System.out.println("user1s = " + user1s);
		for (User_book user_book:list) {
			for (Book1 book1:list1) {
				if(book1.getId()==user_book.getBookId()){
					for (User1 user1:user1s) {
						if(user1.getId()==user_book.getUser()){
							Borrrow_Return borrrow_return = new Borrrow_Return();
							borrrow_return.setTrue_name(user1.getTrueName());
							borrrow_return.setAuthor(book1.getAuthor());
							borrrow_return.setPress(book1.getPress());
							borrrow_return.setName(book1.getName());
							borrrow_return.setTelephone(user1.getTelephone());
							borrow_returns.add(borrrow_return);
						}
					}
				}
			}
		}
		System.out.println("borrow_returns = " + borrow_returns);
		map.put("data", borrow_returns);
		map.put("count", totil);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	
	/**
	 * /admin/book/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids,HttpSession session ,HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		User currentUser = (User)session.getAttribute("currentUser");
		System.out.println(currentUser);
		if(currentUser.getRole().getId()==2){
			result.put("success",false);
			result.put("msg","没有权限，请联系管理员");
			return result;
		}

		for (int i = 0; i < idsStr.length; i++) {
			bookDao.deleteById(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		return result;
	}

	/**
	 * /admin/book/borrow
    */
	@ResponseBody
	@RequestMapping("/borrow")
	public JSONObject borrow(@RequestParam(value = "id", required = true) Integer id,HttpSession session ,HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		User currentUser = (User)session.getAttribute("currentUser");
		int i = bookMapper.countBook(id);
		if(i<=0){
			result.put("success",false);
			result.put("msg","对不起，该书籍已借完！");
			return result;
		}
		List<User_book> user_books = bookMapper.select_User_book(currentUser.getId(), id);
		if(user_books.size()!=0){
			result.put("success",false);
			result.put("msg","您以借过该书籍，请勿重复借阅！");
			return result;
		}
		int updatenumber = bookMapper.borrow(id);
		if(updatenumber!=0){
			result.put("success",true);
			result.put("msg","借书成功，请按时归还！");
			bookMapper.insert_user_book(currentUser.getId(),id,new Date());
			return result;
		}
		result.put("success", false);
		result.put("msg","借书失败！");
		return result;
	}

	/**
	 * /admin/book/return_book
	 */
	@ResponseBody
	@RequestMapping("/return")
	public JSONObject return_book(@RequestParam(value = "id", required = true) Integer id,HttpSession session ,HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		User currentUser = (User)session.getAttribute("currentUser");
		List<User_book> user_books = bookMapper.select_User_book(currentUser.getId(), id);
		if(user_books.size()!=0){
			int i = bookMapper.return_book(id);
			int id1 = user_books.get(0).getId();
			bookMapper.delete(id1);
			result.put("success",true);
			result.put("msg","还书成功，欢迎下次光临！");
			return result;
		}
		result.put("success", false);
		result.put("msg","您好，您没有借该书籍！");
		return result;
	}

}
