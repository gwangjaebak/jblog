package com.poscoict.jblog.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.BlogVo;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value="/{id}")
	public String blog() {
		return "blog/blog-main";
	}
	
	@RequestMapping(value="/{id}/admin/basic")
	public String blog_basic(Model model) {
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/{id}/admin/category")
	public String blog_admin_category(Model model) {
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/{id}/admin/write")
	public String blog_admin_write(Model model) {
		return "blog/blog-admin-write";
	}

	@RequestMapping("/main/update")
	public String main(BlogVo blog, @RequestParam("file") MultipartFile file) {
		String profile = fileUploadService.restore(file);
		
		if(profile != null) {
			blog.setLogo(profile);
		}
		
		blogService.update(blog);
		servletContext.setAttribute("blog", blog);
		return "redirect:/admin";
	}
}
