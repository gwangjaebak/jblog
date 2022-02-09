package com.poscoict.jblog.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;

@Controller
@RequestMapping({"/{id:(?!assets|images).*}"})	//	/{id:.*} or /{id:(?!assets).*} 
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="")
	public String blog(@PathVariable("id") String id) {
		session.setAttribute("blog", blogService.getContentsById(id));
//		model.addAttribute("blog", blogService.getContentsByVo(vo));
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
	public String blog_basic() {
//		model.addAttribute("blog", blogService.getContentsByVo(vo));
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/admin/category")
	public String categoryListById(Model model,
			@PathVariable("id") String id) {
		
		List<CategoryVo> list = blogService.getContentsListById(id);
		model.addAttribute("list", list);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/admin/write")
	public String blog_admin_write(Model model,
			@PathVariable("id") String id) {
		model.addAttribute("cate", blogService.getOndeById(id));
		return "blog/blog-admin-write";
	}

	@RequestMapping("/admin/updatesetting")
	public String main(
			BlogVo vo,
			@PathVariable("id") String id,
			@RequestParam("file") MultipartFile file) {
		String profile = fileUploadService.restore(file);
		System.out.println("profile:" + profile);
		if(profile != null) {
			vo.setLogo(profile);
		}
		vo.setUser_id(id);
		System.out.println("vo:" + vo);
		blogService.update(vo);
		return "redirect:/" + id;
	}
	
	@RequestMapping(value="/admin/category/delete/{no}")
	public String delete_category(
			@PathVariable("id") String id,
			@PathVariable("no") Long no) {
		blogService.deleteCategoryByNo(no);
		return "redirect:/" + id;
	}
	
	@RequestMapping(value="/admin/category/add")
	public String add_category(CategoryVo vo,
			@PathVariable("id") String id) {
		vo.setBlog_id(id);
		blogService.addCategoryById(vo);
		return "redirect:/" + id;
	}
	
	@RequestMapping(value="/admin/write/add")
	public String add_write(CategoryVo vo,
			@PathVariable("id") String id) {
		vo.setBlog_id(id);
		blogService.addCategoryById(vo);
		return "redirect:/" + id;
	}
}
