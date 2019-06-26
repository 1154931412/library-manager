package com.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.common.utils.LibraryResult;
import com.library.pojo.Admin;
import com.library.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	/**
	 * 跳转到密码修改页面
	 */
	@RequestMapping("/admin_repasswd.html")
	public String showRePasswd() {
		return "admin_repasswd";
	}
	
	/**
	 * 管理员修改密码
	 */
	@RequestMapping("/admin_repasswd_do")
	public String alterPasswd(HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			String oldPasswd,
			String newPasswd) {
		// 取到管理员信息
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		System.out.println(admin);
		// 将用户输入的密码写入
		admin.setPassword(oldPasswd);
		LibraryResult result = adminService.alterAdminPwd(admin, newPasswd);
		if(result.getStatus() == 400) {
			// 修改失败
			redirectAttributes.addFlashAttribute("error", result.getMsg());
			return "redirect:/admin_repasswd.html";
		}else {
			// 修改成功
			redirectAttributes.addFlashAttribute("succ", result.getMsg());
			return "redirect:/admin_repasswd.html";
		}
		
	}

}
