package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public BlogVo getContentsById(BlogVo vo) {
		return blogRepository.findById(vo);
	}

	public boolean update(BlogVo vo) {
		return blogRepository.update(vo) == 1;
	}
	
	public List<BlogVo> getContents() {
		return blogRepository.findAll();
	}

}