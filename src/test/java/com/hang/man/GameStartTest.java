package com.hang.man;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.text.IsEmptyString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hang.man.DAO.DAO;
import com.hang.man.DAO.HistoryDAO;
import com.hang.man.DAO.UserDAO;
import com.hang.man.DAO.WordDAO;
import com.hang.man.controller.GameStartController;
import com.hang.man.pojo.Word;


public class GameStartTest {
	private MockMvc mockMvc;

	@Mock
	private DAO dao;
	@Mock
	private WordDAO wordDao;
	@Mock
	private UserDAO userDao;
	@Mock
	private HistoryDAO historyDao;
	@InjectMocks
	private GameStartController gameStartController;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(gameStartController).build();
	}
	
	
	@Test
	public void startGame_WordsGenerateTest() throws Exception {
		
		mockMvc.perform(get("/user/gamestart.htm"))
			   .andDo(print())
		       .andExpect(status().isOk())
		       .andExpect(model().attribute("word",notNullValue()))
		       .andExpect(view().name("game_play"));
	}
	
}
