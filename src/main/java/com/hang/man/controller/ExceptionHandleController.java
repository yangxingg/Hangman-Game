package com.hang.man.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hang.man.exception.LoginFailException;

@Controller
public class ExceptionHandleController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandleController.class);
	@ExceptionHandler(LoginFailException.class)
    public String redirectToLogin(){
		logger.info("you are in exception");
        return "redirect:/jumpToSignin";
    }
}
