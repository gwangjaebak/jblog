package com.poscoict.jblog.repository;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo findById(BlogVo vo) {
		return sqlSession.selectOne("blog.findById", vo);
	}

	public int update(BlogVo vo) {
		return sqlSession.update("blog.update", vo);
	}
	
	public List<BlogVo> findAll() {
		List<BlogVo> list = new ArrayList<>();
		return sqlSession.selectList("blog.findAll", list);
	}

}
