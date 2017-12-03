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
public class GamePlayTest {
	
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
	String word = "Happy";
	String guessedTimes;
	List<Character> guessed;
	List<Character> wrongGuessed;
	
	public GamePlayTest(String letter, String expectRes,String guessedTimes, List<Character> guesed, List<Character> wrongGuessed) {
		this.letter       = letter;
		this.expectRes    = expectRes;
		this.guessedTimes = guessedTimes;
		this.guessed      = guesed;
		this.wrongGuessed = wrongGuessed;
	}
	
	@Parameters
	public static List<Object[]> guessVal(){
		return Arrays.asList(new Object[][] {
							{"k","||0", "0",new ArrayList<Character>(),  new ArrayList<Character>()},
							{"u","||0", "1", new ArrayList<Character>(), new ArrayList<String>(Arrays.asList("k"))},
							{"b","||0", "2", new ArrayList<Character>(), new ArrayList<String>(Arrays.asList("k","u"))},
							{"g","||0", "3",new ArrayList<Character>(),  new ArrayList<String>(Arrays.asList("k","u","b"))},
							{"j","||0", "4", new ArrayList<Character>(), new ArrayList<String>(Arrays.asList("k","u","b","g"))},
							{"r","||0", "5",new ArrayList<Character>(),  new ArrayList<String>(Arrays.asList("k","u","b","g","j"))},
							{"q","||0", "6", new ArrayList<Character>(), new ArrayList<String>(Arrays.asList("k","u","b","g","j","r"))},
							{"x","||0", "7",new ArrayList<Character>(),  new ArrayList<String>(Arrays.asList("k","u","b","g","j","r","q"))},
							{"v","||0", "8", new ArrayList<Character>(), new ArrayList<String>(Arrays.asList("k","u","b","g","j","r","q","x"))},
							{"m","||2", "9",new ArrayList<Character>(),  new ArrayList<String>(Arrays.asList("k","u","b","g","j","r","q","x","v"))},
							
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
	}
}
