package com.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CLICKS")
public class ClickRecord {

	public ClickRecord() {
	}

	public ClickRecord(int numberOfClicks) {
		this.numberOfClicks = numberOfClicks;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public int id;
	
	@NotNull
	@Column(name = "NUMBER_OF_CLICKS")
	public int numberOfClicks;

	@NotNull
	@Column(name = "TODAY_DATE")
	public String date;
	
	public void setNumberOfClicks(int numberOfClicks) {
		this.numberOfClicks = numberOfClicks;
	}

	public int getNumberOfClicks() {
		return this.numberOfClicks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
