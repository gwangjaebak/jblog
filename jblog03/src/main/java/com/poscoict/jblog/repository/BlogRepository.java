package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo find(BlogVo vo) {
		return sqlSession.selectOne("blog.findById", vo);
	}

	public int update(BlogVo vo) {
		return sqlSession.update("blog.update", vo);
	}

}
