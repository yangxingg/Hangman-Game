package com.hang.man.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="word")
public class Word {
	
	@Id
	@GeneratedValue
	@Column(name="Word_ID" ,unique=true, nullable = false)
	int wordId;
	
	@Column(name="Word_Content")
	String wordContent;

	public int getWordId() {
		return wordId;
	}

	public void setWordId(int wordId) {
		this.wordId = wordId;
	}

	public String getWordContent() {
		return wordContent;
	}

	public void setWordContent(String wordContent) {
		this.wordContent = wordContent;
	}

	public Word(String wordContent) {
		super();
		this.wordContent = wordContent;
	}
	
	public Word() {
		super();
	}
	
}
