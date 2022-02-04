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
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("/{id}/admin/basic")
	public String main(Model model) {
		BlogVo blog = blogService.getSite();
		System.out.println(blog.getLogo());
		System.out.println(blog.getTitle());
		System.out.println(blog.getUser_id());
		model.addAttribute("blog", blog);
		return "blog/blog-admin-basic";
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
