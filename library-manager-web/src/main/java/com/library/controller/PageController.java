package com.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/allreaders")
	public String test() {
		return "404";
		
	}
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	@RequestMapping("/admin_main")
	public String showIndex7() {
		return "admin_main";
	}
	
	@RequestMapping("/404")
	public String showIndex1() {
		return "404";
	}

	@RequestMapping("/admin_book_edit")
	public String showIndex3() {
		return "admin_book_edit";
	}
	@RequestMapping("/admin_book_lend")
	public String showIndex4() {
		return "admin_book_lend";
	}
	@RequestMapping("/admin_books")
	public String showIndex5() {
		return "admin_books";
	}
	@RequestMapping("/admin_lend_list")
	public String showIndex6() {
		return "admin_lend_list";
	}
	
	@RequestMapping("/admin_reader_add")
	public String showIndex8() {
		return "admin_reader_add";
	}
	@RequestMapping("/admin_reader_edit")
	public String showIndex9() {
		return "admin_reader_edit";
	}
	@RequestMapping("/admin_readers")
	public String showIndex10() {
		return "admin_readers";
	}
	@RequestMapping("/admin_repasswd")
	public String showIndex11() {
		return "admin_repasswd";
	}
	@RequestMapping("/reader_book_detail")
	public String showIndex12() {
		return "reader_book_detail";
	}
	@RequestMapping("/reader_book_query")
	public String showIndex13() {
		return "reader_book_query";
	}
	@RequestMapping("/reader_info_edit")
	public String showIndex14() {
		return "reader_info_edit";
	}
	@RequestMapping("/reader_info")
	public String showIndex15() {
		return "reader_info";
	}
	@RequestMapping("/reader_lend_list")
	public String showIndex16() {
		return "reader_lend_list";
	}
	@RequestMapping("/reader_main")
	public String showIndex17() {
		return "reader_main";
	}
	@RequestMapping("/reader_repasswd")
	public String showIndex18() {
		return "reader_repasswd";
	}


}
