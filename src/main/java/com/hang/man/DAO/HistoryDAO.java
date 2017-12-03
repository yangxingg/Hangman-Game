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
        		Query q = getSession().createQuery("from History where User_ID = :user_ID order by His_Time  desc");
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
	
	public long getFailCount(User user) {
		int user_ID = user.getUser_ID();
		int his_res = 0;
		try {
			begin();
        		logger.info("USER ID: "+String.valueOf(user_ID));
        		//get fail count
        		Query q1 = getSession().createQuery("select count(*) from History where User_ID = :user_ID and His_Res=:his_Res");
        		q1.setParameter("user_ID", user_ID);
        		q1.setParameter("his_Res", his_res);
        		long failCount = Long.parseLong(String.valueOf( q1.uniqueResult()));
        		commit();
        		close();
        		return failCount;
        }catch(HibernateException e) {
        	 	rollback();
        }
		return 0;
	}
	
	public long getSucceCount(User user) {
		int user_ID = user.getUser_ID();
		int his_res = 1;
		try {
			begin();
        		logger.info("USER ID: "+String.valueOf(user_ID));
        		//get success count
        		Query q2 = getSession().createQuery("select count(*) from History where User_ID = :user_ID and His_Res=:his_Res");
        		q2.setParameter("user_ID", user_ID);
        		q2.setParameter("his_Res", his_res);
        		long succeCount = Long.parseLong(String.valueOf(q2.uniqueResult()));
        		commit();
        		close();
        		return succeCount;
        }catch(HibernateException e) {
        	 	rollback();
        }
		return 0;
	}
}
