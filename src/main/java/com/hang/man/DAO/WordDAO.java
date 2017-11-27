package com.hang.man.DAO;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hang.man.pojo.Word;

public class WordDAO extends DAO{
	private static final Logger logger = LoggerFactory.getLogger(WordDAO.class);
	public void addWord(Word word) {
		try {
			
			begin();
			logger.info("You are in wordDAO");
			getSession().save(word);
    			commit();
    			close();
        	 
        } catch (HibernateException e) {
            rollback();
            logger.info("wordDA0 ERR: "+e.getMessage());
        }	
	}
	
	public Word getWord() {
		try {
			
			begin();
			Query q = getSession().createQuery("FROM Word ORDER BY rand()").setMaxResults(1);
			logger.info("You are in wordDAO");
			Word word = (Word) q.uniqueResult();
    			commit();
    			close();
    			return word;
        	 
        } catch (HibernateException e) {
            rollback();
            logger.info("wordDA0 ERR: "+e.getMessage());
        }	
		
		return null;
	}
}
