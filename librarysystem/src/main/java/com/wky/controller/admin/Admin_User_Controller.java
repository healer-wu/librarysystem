package com.wky.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.wky.dao.Book1Mapper;
import com.wky.dao.UserDao;
import com.wky.entity.User;
import com.wky.service.UserService;
import com.wky.util.CryptographyUtil;
import com.wky.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/admin/user")
public class Admin_User_Controller {
	@Autowired
	private Book1Mapper book1Mapper;
	@Resource
	private UserDao userDao;
	 
	   @Resource 
	   private UserService userService;
	  
	 /**
		 *   /admin/user/add
		 */
		@ResponseBody
		@RequestMapping("/add")
		public JSONObject add(@Valid User user  , BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
			JSONObject result = new JSONObject();
			
			if(bindingResult.hasErrors()){
				result.put("success", false);
				result.put("msg", bindingResult.getFieldError().getDefaultMessage());
				return result;
			}else{
				user.setCreateDateTime(new Date());
				user.setPwd(CryptographyUtil.md5(user.getPwd(), "java"));
				userDao.save(user);
				result.put("success", true);
				result.put("msg", "添加成功");
				return result;
			}
		}
		
		
		

		/**
		 * /admin/user/update
		 */
		@ResponseBody
		@RequestMapping("/update")
		public JSONObject update(@Valid User user ,BindingResult bindingResult, HttpServletRequest request)throws Exception {
			JSONObject result = new JSONObject();
			
			if(bindingResult.hasErrors()){
				result.put("success", false);
				result.put("msg", bindingResult.getFieldError().getDefaultMessage());
				return result;
			}else{
				user.setUpdateDateTime(new Date());
				userService.update(user);
				result.put("success", true);
				result.put("msg", "修改成功");
				return result;
			}
		}
		
		
		/**
		 * /admin/user/set_new_pwd
		 */
		@ResponseBody
		@RequestMapping("/set_new_pwd")
		public JSONObject set_new_pwd(User user, HttpServletRequest request)throws Exception {
			JSONObject result = new JSONObject();

			user.setUpdateDateTime(new Date());
			if(StringUtil.isNotEmpty(user.getPwd())){
				user.setPwd(CryptographyUtil.md5(user.getPwd(),"java"));
			}
			userService.update(user);
			result.put("success", true);
			result.put("msg", "修改成功");
			return result;
		}

	@ResponseBody
	@RequestMapping("/fixpassword")
	public JSONObject fixpassword(Integer userid, String password, HttpSession session,String repassword, HttpServletRequest request)throws Exception {
		JSONObject result = new JSONObject();
		if(password.equals(repassword)){
			book1Mapper.updatepwd(password,userid);
			result.put("msg", "修改成功");
			session.removeAttribute("currentUser");

			return result;
		}
		result.put("msg", "修改失败");
		return result;
	}
		
		/**
		 * /admin/user/list
		 * @param page    默认1
		 * @param limit   数据多少
		 */
		@ResponseBody
		@RequestMapping("/list")
		public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
				@RequestParam(value = "limit", required = false) Integer limit, 
				HttpServletResponse response,
				HttpServletRequest request) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<User> userList = userService.list(map, page-1, limit);
			long total = userService.getTotal(map);
			map.put("data", userList);
			map.put("count", total);
			map.put("code", 0);
			map.put("msg", "");
			return map;
		}
		
		/**
		 * /admin/user/delete
		 */
		@ResponseBody
		@RequestMapping("/delete")
		public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
				throws Exception {
			String[] idsStr = ids.split(",");
			JSONObject result = new JSONObject();

			for (int i = 0; i < idsStr.length; i++) {
				userDao.deleteById(Integer.parseInt(idsStr[i]));
			}
			result.put("success", true);
			return result;
		}
		
		
	
}
