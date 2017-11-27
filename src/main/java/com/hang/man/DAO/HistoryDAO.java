package com.hang.man.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hang.man.pojo.History;
import com.hang.man.pojo.User;

public class HistoryDAO extends DAO{
	private static final Logger logger = LoggerFactory.getLogger(HistoryDAO.class);
	
	public List<History> getHistoryList(User user){
		int user_ID = user.getUser_ID();
		try {
        	begin();
        	logger.info("USER ID: "+String.valueOf(user_ID));
         Query q = getSession().createQuery("from History where User_ID = :user_ID");
         q.setParameter("user_ID", user_ID);
         
         List<History> historyList = q.list();
         logger.info(String.valueOf(historyList.size()));
        	 commit();
    		 close();
    		 return historyList;
        }catch(HibernateException e) {
        	 	rollback();
        }
		return null;
	}
}
