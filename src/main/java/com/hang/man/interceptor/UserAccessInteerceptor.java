package com.hang.man.interceptor;

import java.io.Console;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hang.man.controller.GameStartController;

public class UserAccessInteerceptor extends HandlerInterceptorAdapter{
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
//		HttpSession session = request.getSession();	
//		final Logger logger = LoggerFactory.getLogger(UserAccessInteerceptor.class);
//		
//		logger.info("you are in pre interceptor");
//		if(session.getAttribute("user") == null) {
//			 //response.sendRedirect(request.getContextPath() + "/sign_in");
//			RequestDispatcher rd = request.getRequestDispatcher("/sign_in.jsp");
//            rd.forward(request, response);
//		}
//		
//		return true;
//		
//	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		final Logger logger = LoggerFactory.getLogger(UserAccessInteerceptor.class);
		
		logger.info("you are in post interceptor");
		if(session.getAttribute("user") == null) {
			 //response.sendRedirect(request.getContextPath() + "/sign_in");
			RequestDispatcher rd = request.getRequestDispatcher("/sign_in.jsp");
            rd.forward(request, response);
		}
		
		
		
	}
}
