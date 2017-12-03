package com.hang.man.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String goToGamePlayingPage(HttpServletRequest request,Model model) throws IOException, Throwable {
		WordDAO wordDao = new WordDAO();
		Word wordObject = wordDao.getWord();
		String word = wordObject.getWordContent();
		logger.info(word);
		int wordLenth = word.length();
		List<Character> guessed = new ArrayList<Character>();
		List<Character> wrongGuessed = new ArrayList<Character>();
		
		model.addAttribute("word",word);
		//add word, wordLength, and guessed letter in session
		HttpSession session = request.getSession();
		//target word
		session.setAttribute("word", word);
		session.setAttribute("wordLength", wordLenth);
		//letters guessed correctly
		session.setAttribute("guessed", guessed);
		//letter guessed wrongly
		session.setAttribute("wrongGuessed", wrongGuessed );
		//guess wrong time
		session.setAttribute("guessedTimes", "0");
		session.setAttribute("img", "/Images/step0.png");
		session.setAttribute("status", "start");
		
		logger.info((String) session.getAttribute("guessedTimes"));
		return "game_play";
	}
}
