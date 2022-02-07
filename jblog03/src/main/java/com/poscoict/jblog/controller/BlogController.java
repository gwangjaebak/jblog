package com.poscoict.jblog.controller;

import java.util.Optional; 

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.BlogVo;

@Controller
@RequestMapping("/{user_id}")	//	/{id:.*} or /{id:(?!assets).*} 
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value="")
	public String blog(
			BlogVo vo,
			Model model) {
		model.addAttribute("blog", blogService.getSite(vo));
		return "blog/blog-main";
	}
	
//	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
//	public String index3(@PathVariable("id") String id,
//			@PathVariable("pathNo1") Optional<Long> pathNo1,
//			@PathVariable("pathNo2") Optional<Long> pathNo2) {
//		 Long categoryNo = 0L;
//		 Long postNo = 0L;
//		 
//		 if(pathNo2.isPresent()){
//			 categoryNo = pathNo1.get();
//			 postNo = pathNo2.get();
//		 } else if (pathNo1.isPresent()){
//			 categoryNo = pathNo1.get();
//		 }
//		 
//		 System.out.println("id : " + id);
//		 System.out.println("categoryNo : " + categoryNo);
//		 System.out.println("postNo : " + postNo);
//		return "blog/blog-admin-basic";
//	}
	
	@RequestMapping(value="/admin/basic")
	public String blog_basic(
			BlogVo vo,
			Model model) {
		model.addAttribute("blog", blogService.getSite(vo));
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/admin/category")
	public String blog_admin_category(Model model) {
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/admin/write")
	public String blog_admin_write(Model model) {
		return "blog/blog-admin-write";
	}

	@RequestMapping("/admin/updatesetting")
	public String main(
			BlogVo vo,
			@RequestParam("file") MultipartFile file) {
		String profile = fileUploadService.restore(file);
		if(profile != null) {
			vo.setLogo(profile);
		}
		blogService.update(vo);
		servletContext.setAttribute("blog", vo);
		return "blog/blog-admin-basic";
	}
}
