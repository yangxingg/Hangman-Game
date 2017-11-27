package com.hang.man.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hang.man.DAO.HistoryDAO;
import com.hang.man.DAO.UserDAO;
import com.hang.man.pojo.History;
import com.hang.man.pojo.User;

@Controller
@RequestMapping("/user/gameplay.htm")
public class GamePlayController {
	private static final Logger logger = LoggerFactory.getLogger(GamePlayController.class);
	
	@RequestMapping(method=RequestMethod.POST,params={"val"})
	public @ResponseBody String guessALetter(HttpServletRequest request, @RequestParam(value="val") String val){
		HttpSession session = request.getSession();
		
		char letter = val.charAt(0);
		logger.info("At game control play with guess letter - " + letter);
		String word = (String) session.getAttribute("word");
		List<Character> guessed = (List<Character>) session.getAttribute("guessed");
		int guessedTimes = Integer.parseInt((String) session.getAttribute("guessedTimes"));
		User user = (User) session.getAttribute("user");
		UserDAO userDao = new UserDAO();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		boolean hasLetter = false;
		
		for(int i = 0; i < word.length(); i++)
			if(word.charAt(i) == letter || Math.abs(word.charAt(i) - letter) == 32) {
				hasLetter = true;
				guessed.add(Character.toUpperCase(word.charAt(i)));
			}
		//has letter return a html with new guessing result
		String res = "";
		if(hasLetter) {
			
			session.setAttribute("guessed", guessed);
			res = guessedTimes + "||";
			boolean gameFinished = true;
			for(int i = 0; i < word.length(); i ++) {
				if(guessed.contains(Character.toUpperCase(word.charAt(i)))){
					res += "<div style=\"display:inline-block; vertical-align: middle;  width:50px; height:50px;\">"+ Character.toUpperCase(word.charAt(i))+"</div>";
				}else {
					gameFinished = false;
					res += "<div style=\"display:inline-block; vertical-align: middle;  border-bottom: 2px solid white; width:50px; height:50px;\"></div>";
				}
			}
			if(gameFinished) {//success -1 ,fail - 0
				//add success into history
				logger.info("game success");
				 History history = new History(1,dateFormat.format(date),user);
				// history.setUser(user);
				 userDao.addHistory(history, user);
				// session.setAttribute("user", user);
				 
				 res+= "||1" ;
			}
			else res += "||0";
			
		}else {
			guessedTimes ++;
			session.setAttribute("img", "/Images/step"+guessedTimes+".png");
			session.setAttribute("guessedTimes", String.valueOf(guessedTimes));
			if(guessedTimes >= 10) {
				// game over,fail-0
				//add fali into history
				logger.info("game fail");
				 History history = new History(0,dateFormat.format(date),user);
				 //history.setUser(user);
				 userDao.addHistory(history, user);
				 //session.setAttribute("user", user);
				 
				res = guessedTimes + "||";
				for(int i = 0; i < word.length(); i ++) {
					if(guessed.contains(Character.toUpperCase(word.charAt(i)))){
						res += "<div style=\"display:inline-block; vertical-align: middle;  width:50px; height:50px;\">"+Character.toUpperCase(word.charAt(i))+"</div>";
					}else {
						res += "<div style=\"display:inline-block; vertical-align: middle; color:red; border-bottom: 2px solid white; width:50px; height:50px;\">"+Character.toUpperCase(word.charAt(i))+"</div>";
					}
				}
				res += "||2";
				
			}else {
				//pic change
				res = guessedTimes + "||";
				for(int i = 0; i < word.length(); i ++) {
					if(guessed.contains(Character.toUpperCase(word.charAt(i)))){
						res += "<div style=\"display:inline-block; vertical-align: middle;  width:50px; height:50px;\">"+Character.toUpperCase(word.charAt(i))+"</div>";
					}else {
						res += "<div style=\"display:inline-block; vertical-align: middle;  border-bottom: 2px solid white; width:50px; height:50px;\"></div>";
					}
				}
				res += "||0";
				
			}
		
			
		}
		return res;
			
	}
	
	
}
