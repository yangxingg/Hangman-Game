package com.hang.man.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hang.man.DAO.HistoryDAO;
import com.hang.man.pojo.History;
import com.hang.man.pojo.User;

@Controller
public class UserHistoryController {
	private static final Logger logger = LoggerFactory.getLogger(UserHistoryController.class);
	@RequestMapping("/user/history_view.htm")
	public String historyView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user"); 
		HistoryDAO historyDao = new HistoryDAO();
		//get history list
		List<History> historyList = historyDao.getHistoryList(user);
		List<String> historyTime = new ArrayList<String>();
		List<String> historyRes = new ArrayList<String>();
		for(History h : historyList) {
			historyTime.add(h.getHisTime());
			logger.info(String.valueOf(h.getHisRes()));
			if(h.getHisRes() == 1)
				historyRes.add("Win");
			else
				historyRes.add("Fail");
		}
		session.setAttribute("historyTime", historyTime);
		session.setAttribute("historyRes", historyRes);
		session.setAttribute("historyLen", historyList.size());
		//get fail and success count
		long failCount = historyDao.getFailCount(user);
		long succeCount = historyDao.getSucceCount(user);
		//success/total
		double ratio = Math.floor(succeCount*10000/(failCount + succeCount))/100;
		session.setAttribute("failCount", failCount);
		session.setAttribute("succeCount", succeCount);
		session.setAttribute("ratio", ratio);
		
		return "history";
	}
}
