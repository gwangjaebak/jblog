package com.poscoict.jblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.service.UserService;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(UserVo vo,
			HttpServletRequest request,
			HttpServletResponse response) {
		String email = vo.getId();
		String password = vo.getPassword();
		
		UserVo authUser = userService.getUser(email, password);
		if(authUser == null) {
			request.setAttribute("result", "fail");
			request.setAttribute("email", email);
			return "redirect:/main";
		}
		
		// session 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		return "redirect:/main";
	}
	
	@RequestMapping(value="/logout")
	public String logout(
			HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session != null) {
			session.removeAttribute("authUser");
			session.invalidate();
		}
		return "redirect:/main";
	}
	
	
	// @ExceptionHandler( Exception.class )
	// public String UserControllerExceptionHandler() {
	//	return "error/exception";
	// }
	

	
//	@Auth
//	@RequestMapping(value="/update", method=RequestMethod.GET)
//	public String update(@AuthUser UserVo authUser, Model model) {
//		Long userNo = authUser.getNo();
//		UserVo userVo = userService.getUser(userNo);
//		model.addAttribute("userVo", userVo);
//		
//		return "user/update";
//	}
//	
//	@Auth
//	@RequestMapping(value="/update", method=RequestMethod.POST)
//	public String update(@AuthUser UserVo authUser, UserVo userVo) {
//		userVo.setNo(authUser.getNo());
//		userService.updateUser(userVo);
//		
//		return "redirect:/user/update";
//	}
}