package com.hang.man.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hang.man.HomeController;
import com.hang.man.DAO.WordDAO;
import com.hang.man.pojo.Word;

@Controller
@RequestMapping("/user/gamestart.htm")
public class GameStartController {
//	@Value(value = "classpath:word.txt")
//	private Resource words;
	private static final Logger logger = LoggerFactory.getLogger(GameStartController.class);
	
	@RequestMapping("")
	public String goToGamePlayingPage(HttpServletRequest request) throws IOException, Throwable {
		WordDAO wordDao = new WordDAO();
		Word wordObject = wordDao.getWord();
		String word = wordObject.getWordContent();
		logger.info(word);
		int wordLenth = word.length();
		List<Character> guessed = new ArrayList<Character>();
		//add word, wordLength, and guessed letter in session
		HttpSession session = request.getSession();
		session.setAttribute("word", word);
		session.setAttribute("wordLength", wordLenth);
		session.setAttribute("guessed", guessed);
		session.setAttribute("guessedTimes", "0");
		session.setAttribute("img", "/Images/step0.png");
		logger.info((String) session.getAttribute("guessedTimes"));
		return "game_play";
	}
	
	
}
