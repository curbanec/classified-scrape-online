package com.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class AlertManager {
	
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private static ConcurrentMap<String,ScheduledFuture<?>> futures = new ConcurrentHashMap<String, ScheduledFuture<?>>();
	
	public static void start(final String region, final String query, final String queryId, final String submissionTimeDate) {

	    String key = queryId;
	    
	    futures.put(key, scheduler.scheduleAtFixedRate(new Alert(region, query, queryId, submissionTimeDate), 
	    		10, 10, java.util.concurrent.TimeUnit.SECONDS));
	}
	
	public static void stop(final String queryId) {
		   futures.get(queryId).cancel(true);
		   System.out.println("Trying to stop: " + queryId);
		}
}