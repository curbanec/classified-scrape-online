package com.concurrent;

import com.crawler.CraigslistAlert;

public class Alert implements Runnable {

	String region;
	String query;
	String queryId;
	String submissionTimeDate;
	String notifyAddress;
	
	public Alert(String region, String query, String queryId, String submissionTimeDate, String notifyAddress){
		
		this.region = region;
		this.query = query;
		this.queryId = queryId;
		this.submissionTimeDate = submissionTimeDate;
		this.notifyAddress = notifyAddress;
	}
	
	@Override
	public void run() {
		
		CraigslistAlert alert = new CraigslistAlert(); 
		alert.runPrimary(region, query, queryId, submissionTimeDate, notifyAddress); 
		Thread.currentThread().setName(queryId); 
		System.out.println(Thread.currentThread().getName());
	}
}