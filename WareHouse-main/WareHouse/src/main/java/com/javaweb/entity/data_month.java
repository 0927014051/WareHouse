package com.javaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_month")
public class data_month {
	
	@Id
	@Column
	private int month;
	
	@Column
	private int season;
	
	@Column
	private int special_event;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getSpecial_event() {
		return special_event;
	}

	public void setSpecial_event(int special_event) {
		this.special_event = special_event;
	}
	
	

}
