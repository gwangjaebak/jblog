package com.poscoict.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.BlogVo;

public class BlogInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private BlogService blogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		BlogVo blog = (BlogVo)request.getServletContext().getAttribute("blog");
		if(blog == null) {
			blog = blogService.getContentsById(blog);
			request.getServletContext().setAttribute("blog", blog);
		}
		
		return true;
	}

}
