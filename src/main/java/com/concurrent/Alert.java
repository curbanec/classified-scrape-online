package com.concurrent;

import com.crawler.ThreadSafeAlert;

public class Alert implements Runnable {

	String region;
	String query;
	
	public Alert(String region, String query){
		this.region = region;
		this.query = query;
	}
	
	@Override
	public void run() {
		
		ThreadSafeAlert alert = new ThreadSafeAlert();
		alert.runPrimary(region, query);
	}
	
	// instantiate Alert and pass it to a worker
	
	
	
	

}
