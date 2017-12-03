package com.hang.man;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.map.ser.ContainerSerializers;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Contains;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hang.man.DAO.DAO;
import com.hang.man.DAO.HistoryDAO;
import com.hang.man.DAO.UserDAO;
import com.hang.man.DAO.WordDAO;
import com.hang.man.controller.GamePlayController;
import com.hang.man.controller.GameStartController;

import junit.framework.Assert;

@RunWith(Parameterized.class)
public class GamePlayTest2 {
	
	private MockMvc mockMvc;
	@Mock
	private DAO dao;
	@Mock
	private UserDAO userDao;
	@Mock
	private HistoryDAO historyDao;
	@InjectMocks
	private GamePlayController gamePlayController;
	
	private String letter = null;
	private String expectRes = null;
	private String expectPic = null;
	private String word;
	private String guessedTimes;
	private List<Character> guessed;
	private List<Character> wrongGuessed;
	
	public GamePlayTest2(String word, String letter, String expectRes,String expectPic, String guessedTimes, List<Character> guesed, List<Character> wrongGuessed) {
		this.word         = word;
		this.letter       = letter;
		this.expectRes    = expectRes;
		this.expectPic    = expectPic;
		this.guessedTimes = guessedTimes;
		this.guessed      = guesed;
		this.wrongGuessed = wrongGuessed;
	}
	
	@Parameters
	public static List<Object[]> guessVal(){
		return Arrays.asList(new Object[][] {
							{"jjj"      ,"j","||1","0||", "0",new ArrayList<Character>(),  new ArrayList<Character>()},                 //one time success
							{"beautiful","u","||0","3||", "3", new ArrayList<Character>(Arrays.asList('B','U')), new ArrayList<Character>(Arrays.asList('M','N','O'))},  //guess a right letter repeatedly
							{"beautiful","M","||0","3||", "3", new ArrayList<Character>(Arrays.asList('B','U')), new ArrayList<Character>(Arrays.asList('M','N','O'))},          //guess a wrong letter repeatedly
							{"Yellow"   ,"e","||1","2||", "2",new ArrayList<Character>(Arrays.asList('W','L','Y','O')),  new ArrayList<Character>(Arrays.asList('M','N'))},      //guess last letter correctly
							{"Yellow"   ,"a","||2","10||", "9",new ArrayList<Character>(Arrays.asList('W','L','Y','O')),  new ArrayList<Character>(Arrays.asList('M','N','U','I','P','R','B','S','V'))},  //guess last letter wrongly
							{"Max"      ,"k","||0","5||", "4",new ArrayList<Character>(),  new ArrayList<Character>(Arrays.asList('O','N','U','B'))},//guess a wrong letter
							{"LAST"     ,"t","||0","6||", "6", new ArrayList<Character>(), new ArrayList<Character>(Arrays.asList('O','N','U','B','F','Q'))}, //guess a right letter
							
							});
	}
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(gamePlayController).build();
	}
	
	@Test
	public void testWordGuess() throws Exception {
		MvcResult result = mockMvc.perform(post("/user/gameplay.htm")
				.sessionAttr("guessed", guessed)
				.sessionAttr("wrongGuessed", wrongGuessed)
				.sessionAttr("guessedTimes", guessedTimes)
				.sessionAttr("word", word)
				.param("val", letter)
			    )
			   .andDo(print())
		       .andExpect(status().isOk())
		       .andReturn();
		
		String content = result.getResponse().getContentAsString();
		assertTrue(content.contains(expectRes));
		assertTrue(content.contains(expectPic));
	}
}
