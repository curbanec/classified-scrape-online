package com.concurrent;

import com.crawler.CraigslistAlert;

public class Alert implements Runnable {

	String region;
	String query;
	String queryId;
	String submissionTimeDate;
	
	public Alert(String region, String query, String queryId, String submissionTimeDate){
		
		this.region = region;
		this.query = query;
		this.queryId = queryId;
		this.submissionTimeDate = submissionTimeDate;
	}
	
	@Override
	public void run() {
		
		CraigslistAlert alert = new CraigslistAlert(); 
		alert.runPrimary(region, query, queryId, submissionTimeDate); 
		Thread.currentThread().setName(queryId); 
		System.out.println(Thread.currentThread().getName());
	}
}