package com.dto;

// I may need to include PID attribute from HTML.
public class AlertDto {
    
    public AlertDto(){};
    
    public String submissionTimeDate;
    
    public String area;
    
    public String query;
    
    public String queryId;
    
    public String notifyAddress;

	public String getSubmissionTimeDate() {
		return submissionTimeDate;
	}

	public void setSubmissionTimeDate(String submissionTimeDate) {
		this.submissionTimeDate = submissionTimeDate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getNotifyAddress() {
		return notifyAddress;
	}

	public void setNotifyAddress(String notifyAddress) {
		this.notifyAddress = notifyAddress;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}   
}
