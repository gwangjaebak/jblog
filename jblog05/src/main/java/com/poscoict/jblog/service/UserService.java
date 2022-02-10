package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.UserRepository;
import com.poscoict.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired UserRepository userRepository;

	public void join(UserVo vo) {
		System.out.println("UserService 출력");
		userRepository.insert(vo);
	}
	
	public UserVo getUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public UserVo getUser(Long userNo) {
		return userRepository.findByNo(userNo);
	}
}