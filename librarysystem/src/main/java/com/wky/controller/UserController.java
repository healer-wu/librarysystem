package com.wky.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import com.wky.dao.UserDao;
import com.wky.entity.User;
import com.wky.util.CryptographyUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserDao userDao;
	
	/**
	 * /user/login
	 */
	@ResponseBody
	@RequestMapping("/login")
	public JSONObject login(String name, String password,HttpSession session, String checkcode )throws Exception {
		JSONObject result = new JSONObject();
//		User byNameAndPwd = userDao.findByNameAndPwd(name, password);
//		if(byNameAndPwd!=null){
//			byNameAndPwd.setPwd("******");
//			session.setAttribute("user",byNameAndPwd);
//			result.put("success", true);
//			result.put("msg","登陆成功");
//		}
//		else{
//			result.put("success", false);
//			result.put("msg","密码错误或者用户名不存在！");
//		}
		//获取 subject
		Subject subject=SecurityUtils.getSubject();
		//封装用户数据
		UsernamePasswordToken token=new UsernamePasswordToken(name, CryptographyUtil.md5(password, "java"));
		String validationCode =(String) session.getAttribute("validationCode");
		if(!validationCode.equals(checkcode)) {
			result.put("success", false);
			result.put("msg", "验证码错误！");
			return result;
		}

		try {
			//执行登陆  shiro的登陆
			subject.login(token);
			//执行登陆  shiro的登陆
			result.put("success", true);
			result.put("msg","登陆成功");
			User user = userDao.findByName(name);
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", user); //把当前用户信息存到session中
		}
		catch (UnknownAccountException e) {
			result.put("success", false);
			result.put("msg","用户名不存在");
		}catch (IncorrectCredentialsException e) {
			result.put("success", false);
			result.put("msg","密码错误");
		}
		return result;
	}
	/**
	 * 注销
	 *  /user/logout
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout()throws Exception{
		SecurityUtils.getSubject().logout(); //shiro的退出
		return "redirect:/login";
	}

	@RequestMapping("/fixpassword")
	public String fixpassword(){
		return "/pc/fixpassword";
	}
}
