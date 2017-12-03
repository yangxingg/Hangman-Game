package com.hang.man.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name="history")
public class History {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="His_ID",unique=true, nullable=false) 
	private int hisId;
	
	@Column(name="His_Res")
	private int hisRes;
	
	@Column(name="His_Time")
	private String hisTime;
	
	//@JsonBackReference
	@JoinColumn(name="User_ID", nullable=false)
	@ManyToOne()
	private User user;
	
	public History() {
		super();
	}

	public History(int His_Res, String His_Time, User user) {
		hisRes = His_Res;
		hisTime = His_Time;
		this.user = user;
	}

	public int getHisId() {
		return hisId;
	}

	public void setHisId(int hisId) {
		this.hisId = hisId;
	}

	public int getHisRes() {
		return hisRes;
	}

	public void setHisRes(int hisRes) {
		this.hisRes = hisRes;
	}

	public String getHisTime() {
		return hisTime;
	}

	public void setHisTime(String hisTime) {
		this.hisTime = hisTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
