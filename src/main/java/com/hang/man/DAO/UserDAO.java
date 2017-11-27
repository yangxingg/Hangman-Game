package com.hang.man.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hang.man.controller.GameStartController;
import com.hang.man.pojo.History;
import com.hang.man.pojo.User;

public class UserDAO extends DAO{
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	public boolean exist(String username)throws UserException {
        try {
        	 begin();
             Query q = getSession().createQuery("from User where User_Name = :username");
             q.setParameter("username",username);
             List<User> result = q.list();
             logger.info("The "+username+" number is : "+ String.valueOf(result.size()));
             commit();
             close();
             if(result.size() > 0)
            	 	return true;
             else
            	 	return false;
        } catch (HibernateException e) {
            rollback();
            
        }
		return false;
	}
	
	public User isValid(String username, String password) {
		try {
       	 begin();
            Query q = getSession().createQuery("from User where User_Name = :username");
            q.setParameter("username",username);
            q.setMaxResults(1);
            User user = (User) q.uniqueResult();
            if(password.equals(user.getPassword()))
            		return user;
            commit();
            close();
           
            
       } catch (HibernateException e) {
           rollback();
           
       }
		return null;
	}
	
	public User createUser(String userName, String password) {
		try {
       	 	begin();
       	 	User user = new User(userName,password);
       	 	getSession().save(user);
			commit();
			close();
			return user;
       } catch (HibernateException e) {
           rollback();
           
       }
		return null;
		
	}
	
	public void addHistory(History history, User user) {
		try {
			
			begin();
			//user.getHistorylist().add(history); 
			logger.info("begin");
			getSession().save(history);
    			//getSession().update(user);
    			//logger.info(String.valueOf(user.getHistorylist().size()));
    			commit();
    			close();
        	 
        } catch (HibernateException e) {
            rollback();
            logger.info(e.getMessage());
        }	
	}
	
	
}
