package com.hang.man.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hang.man.DAO.UserDAO;
import com.hang.man.pojo.User;

@Controller
public class UserSignUpController {
	private static final Logger logger = LoggerFactory.getLogger(UserSignUpController.class);
	
	@RequestMapping("/jumpToSignup.htm")
	public String jumpToSignup() {
		return "sign_up";
	}
	
	@RequestMapping( value="/checkUserName.htm",method=RequestMethod.POST,params={"userName"})
	public @ResponseBody String checkUserName(HttpServletRequest request, @RequestParam(value="userName") String userName) throws UserException {
		UserDAO userDao = new UserDAO();
		
		if(userDao.exist(userName))
			return "0";
		else
			return "1";
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/user/signup.htm")
	public String signup(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password1");
		
		UserDAO userDao = new UserDAO();
		User user = userDao.createUser(userName, password);
		logger.info("username: "+ user.getUser_Name());
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		session.setAttribute("userName", user.getUser_Name());
		
		return "game_start"; 
	}
	
}
