package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public BlogVo getContentsById(String id) {
		return blogRepository.findById(id);
	}
	
	public boolean deleteCategoryByNo(Long no) {
		int count = blogRepository.deleteByNo(no);
		return count == 1;
	}

	public boolean update(BlogVo vo) {
		return blogRepository.update(vo) == 1;
	}
	
	public List<CategoryVo> getCategoryById(String id) {
		return blogRepository.findCategoryById(id);
	}

	public boolean addCategoryById(CategoryVo vo) {
		return blogRepository.addById(vo) == 1;
	}

	public boolean addPost(PostVo vo) {
		return blogRepository.addPost(vo) == 1;
	}
	
	public List<PostVo> getPostByCateNo(Long categoryNo) {
		return blogRepository.findPostList(categoryNo);
	}

	public PostVo getPostOne(Long postNo) {
		return blogRepository.getPostOne(postNo);
	}

}