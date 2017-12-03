package com.hang.man.controller;

import java.io.IOException;
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
import com.hang.man.DAO.WordDAO;
import com.hang.man.pojo.History;
import com.hang.man.pojo.User;
import com.hang.man.pojo.Word;

@Controller
public class GamePlayController {
	private static final Logger logger = LoggerFactory.getLogger(GamePlayController.class);
	
	@RequestMapping(value ="/user/gameplay.htm",method=RequestMethod.POST,params={"val"})
	public @ResponseBody String guessALetter(HttpServletRequest request, @RequestParam(value="val") String val){
		HttpSession session = request.getSession();
		//user's input letter
		char letter = Character.toUpperCase(val.charAt(0));
		logger.info("At game control play with guess letter - " + letter);
		String word = (String) session.getAttribute("word");
		@SuppressWarnings("unchecked")
		List<Character> guessed = (List<Character>) session.getAttribute("guessed");
		@SuppressWarnings("unchecked")
		List<Character> wrongGuessd = (List<Character>) session.getAttribute("wrongGuessed");
		int guessedTimes = Integer.parseInt((String) session.getAttribute("guessedTimes"));
		User user = (User) session.getAttribute("user");
		UserDAO userDao = new UserDAO();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		boolean hasLetter = false;
		boolean guessedBefore = false;
		
		//if letter guessed correctly, add into guessed
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == letter || Math.abs(word.charAt(i) - letter) == 32) {
				hasLetter = true;  
				guessed.add(Character.toUpperCase(word.charAt(i)));
				
			}
		}
		
		//if letter guessed wrongly, check if this letter has been guessed alredy
		if(!hasLetter) {
			if(!wrongGuessd.isEmpty() && wrongGuessd.contains(letter))
			{
				guessedBefore = true;
			}
		}
		
		//has letter return a html with new guessing result
		String res = "";
		if(hasLetter) {// guess letter correctly
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
			if(gameFinished) {//success -1 ,fail - 2
				//if game finished , add success into history
				logger.info("game success");
				 History history = new History(1,dateFormat.format(date),user);
				 userDao.addHistory(history, user);
				 res+= "||1" ;
				 session.setAttribute("status", "finish");
			}
			else res += "||0";
		}else {
			//if letter has been guessed before, guesstime should not add one
			if(!guessedBefore) {
				guessedTimes ++;
				wrongGuessd.add(letter);
				session.setAttribute("wrongGuessd", wrongGuessd);
			}
			session.setAttribute("img", "/Images/step"+guessedTimes+".png");
			session.setAttribute("guessedTimes", String.valueOf(guessedTimes));
			if(guessedTimes >= 10) {
				// game over,fail-2
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
				session.setAttribute("status", "finish");
			}else {
				//game not finish, change pic
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
		logger.info(res);
		return res;
	}
	
	@RequestMapping("/user/gameRestart.htm")
	public String goToGamePlayingPage(HttpServletRequest request) throws IOException, Throwable {
		WordDAO wordDao = new WordDAO();
		Word wordObject = wordDao.getWord();
		String word = wordObject.getWordContent();
		logger.info(word);
		int wordLenth = word.length();
		List<Character> guessed = new ArrayList<Character>();
		List<Character> wrongGuessed = new ArrayList<Character>();
		
		//add word, wordLength, and guessed letter in session
		HttpSession session = request.getSession();
		session.setAttribute("word", word);
		session.setAttribute("wordLength", wordLenth);
		session.setAttribute("guessed", guessed);
		session.setAttribute("wrongGuessed", wrongGuessed );
		session.setAttribute("guessedTimes", "0");
		session.setAttribute("img", "/Images/step0.png");
		
		//restart game, if restart during game add fail in history
		if(session.getAttribute("status").equals("start")) {
			User user = (User) session.getAttribute("user");
			UserDAO userDao = new UserDAO();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			History history = new History(0,dateFormat.format(date),user);
			userDao.addHistory(history, user);
		}
		return "game_play";
	}
	
}
