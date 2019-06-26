package com.library.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.common.utils.LibraryResult;
import com.library.pojo.Admin;
import com.library.pojo.ReadCard;
import com.library.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	// 登录检查Controller
	@RequestMapping(value="/api/loginCheck", method=RequestMethod.POST)
	@ResponseBody
	public Object loginCheck(
			@RequestParam("id") Integer username, 
			@RequestParam("passwd") String password,
			HttpServletRequest request) {
		System.out.println("id:" +username+ "passwd:" + password);
		// 创建一个HashMap用于向前端传送数据
		HashMap<String, String> res = new HashMap<>();
		// 检查是否为读者
		LibraryResult loginCheckReader = loginService.loginCheckReader(username, password);
		// 检查是否为管理员
		LibraryResult loginCheckAdmin = loginService.loginCheckAdmin(username, password);
		if(loginCheckReader.getStatus() == 250) {
			 ReadCard ReadCard = (ReadCard)loginCheckReader.getData();
             request.getSession().setAttribute("readercard", ReadCard);
             res.put("stateCode", "2");
             res.put("msg","读者登陆成功！");
		}else if(loginCheckAdmin.getStatus() == 200) {
			 Admin admin= (Admin)loginCheckAdmin.getData();
             request.getSession().setAttribute("admin",admin);
             res.put("stateCode", "1");
             res.put("msg","管理员登陆成功！");
		}else {
			// 否则，登录失败
			res.put("stateCode", "0");
            res.put("msg","账号或密码错误！");
		}
		
		return res;
	}
	
	/**
	 * 登出映射
	 */
	@RequestMapping("/logout.html")
	public String loginOut() {
		return "index";
	}
	
	/**
	 * 已登录映射
	 */
	@RequestMapping("/login.html")
	public String alreadyLogin() {
		return "index";
	}
}
		
		
		
