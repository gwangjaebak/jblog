package com.poscoict.jblog.repository;

import java.util.ArrayList;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;
import com.poscoict.mysite.exception.UserRepositoryException;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo findById(String id) {
		return sqlSession.selectOne("blog.findById", id);
	}

	public int update(BlogVo vo) {
		return sqlSession.update("blog.update", vo);
	}
	
	public List<CategoryVo> findCategoryById(String id) {
		return sqlSession.selectList("blog.findCategoryById", id);
	}
	
	public int deleteByNo(Long no) {
		return sqlSession.delete("blog.delete", no);
	}

	public int addById(CategoryVo vo) {
		return sqlSession.insert("blog.insertCategory", vo);
	}

	public int addPost(PostVo vo) {
		return sqlSession.insert("blog.insertPost", vo);
	}

	public List<PostVo> findPostList(Long categoryNo) {
		return sqlSession.selectList("blog.findPostList", categoryNo);
	}
	
	public PostVo getPostOne(Long postNo){
		return sqlSession.selectOne("blog.getPostOne", postNo);
	}	
}
