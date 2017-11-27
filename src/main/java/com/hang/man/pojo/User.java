package com.hang.man.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;


@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	@Column(name="User_ID" ,unique=true, nullable = false)
	private int User_ID;
	
	@Column(name="User_Name")
	private String User_Name;
	
	@Column(name="Password")
	private String Password;

	//@JsonManagedReference
	@OneToMany( mappedBy = "user")
	private Set<History> historylist = new HashSet<>();
	
	
	public User() {
		super();
	}

	public User( String User_Name,String Password) {
		this.User_Name = User_Name;
		this.Password = Password;
	}
	
	public int getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Set<History> getHistorylist() {
		return historylist;
	}

	public void setHistorylist(Set<History> historylist) {
		this.historylist = historylist;
	}


	
	
}
