package com.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "alerts")
public class AlertRecord {
	
	public AlertRecord(UserRecord userRecord, String area, String submissionTimeDate, 
			String queryName, String queryId, String notifyAddress){
		this.userRecord=userRecord;
		this.area=area;
		this.submissionTimeDate=submissionTimeDate;
		this.queryName=queryName;
		this.queryId=queryId;
		this.notifyAddress=notifyAddress;
	}
	
	public AlertRecord(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public int id;
	
	@ManyToOne()
	@JoinColumn(name="USER_ID")
	UserRecord userRecord;
	
	@NotNull
	@Column(name = "AREA")
	public String area;
	
	@NotNull
	@Column(name = "SUBMISSION_TIME_DATE")
	public String submissionTimeDate;
	
	@NotNull
	@Column(name = "QUERY_NAME")
	public String queryName;
	
	@NotNull
	@Column(name = "QUERY_ID")
	public String queryId;
	
	@NotNull
	@Column(name = "NOTIFY_ADDRESS")
	public String notifyAddress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserRecord getUserRecord() {
		return userRecord;
	}

	public void setUserRecord(UserRecord userRecord) {
		this.userRecord = userRecord;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSubmissionTimeDate() {
		return submissionTimeDate;
	}

	public void setSubmissionTimeDate(String submissionTimeDate) {
		this.submissionTimeDate = submissionTimeDate;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getNotifyAddress() {
		return notifyAddress;
	}

	public void setNotifyAddress(String notifyAddress) {
		this.notifyAddress = notifyAddress;
	}
}
