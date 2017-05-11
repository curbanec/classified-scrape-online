package com.dto;

// I may need to include PID attribute from HTML.
public class AlertDto {
    
    public AlertDto(){};
    
    public String datePosted;
    
    public String area;
    
    public String query;
    
    public String notifyAddress;

	public String getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
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
}
