package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public BlogVo getSite(BlogVo vo) {
		return blogRepository.find(vo);
	}

	public boolean update(BlogVo vo) {
		return blogRepository.update(vo) == 1;
	}

}