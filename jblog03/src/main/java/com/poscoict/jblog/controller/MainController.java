package com.poscoict.jblog.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping({"", "/main"})
	public String index() {
		return "main/index";
	}
	
	@RequestMapping(value="/{id}")
	public String blog() {
		return "blog/blog-main";
	}
	
	@RequestMapping(value="/{id}/admin/basic")
	public String blog_basic() {
		return "blog/blog-admin-basic";
	}
	
}