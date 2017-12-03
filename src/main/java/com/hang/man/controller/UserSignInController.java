package com.hang.man.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hang.man.DAO.UserDAO;
import com.hang.man.exception.LoginFailException;
import com.hang.man.pojo.User;

@Controller
public class UserSignInController {

	
	
	@RequestMapping("/jumpToSignin.htm")
	public String jumpToSignin() {
		return "sign_in";
	}
	
	@RequestMapping(value="/user/signin.htm",method=RequestMethod.POST,params={"userName","password"})
	public String signin(HttpServletRequest request, @RequestParam(value="userName") String userName,@RequestParam(value="password") String password){
		UserDAO userDao = new UserDAO();
		User user = userDao.isValid(userName, password);
		if( user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("userName", user.getUser_Name());
			
			return "game_start";
		}
		return "sign_in";
	}
	
	@RequestMapping("/logout.htm")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "sign_in";
	}
}
