package com.poscoict.jblog.repository;

import java.util.HashMap;  
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.exception.UserRepositoryException;
import com.poscoict.jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired SqlSession sqlSession;
	
	public boolean insert(UserVo vo) {
		System.out.println("UserRepository 출력");
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}
	
	public UserVo findByNo(Long userNo) {
		return sqlSession.selectOne("user.findByNo", userNo);
	}

	public UserVo findByEmailAndPassword(String email, String password) throws UserRepositoryException {
		Map<String, String> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		
		return sqlSession.selectOne("user.findByEmailAndPassword", map);
	}	
}