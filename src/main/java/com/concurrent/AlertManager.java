package com.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dao.AlertRepository;

@Component
public class AlertManager {
	
	private static AlertRepository alertRepository;
	
	@Autowired
	public AlertManager(AlertRepository alertRepository) {
		AlertManager.alertRepository = alertRepository;
	}
	
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private static ConcurrentMap<String,ScheduledFuture<?>> futures = new ConcurrentHashMap<String, ScheduledFuture<?>>();
	
	public static void start(final String region, final String query, final String queryId, final String submissionTimeDate) {

	    String key = queryId;
	    
	    futures.put(key, scheduler.scheduleAtFixedRate(new Alert(region, query, queryId, submissionTimeDate), 
	    		10, 10, java.util.concurrent.TimeUnit.SECONDS));
	}
	
	public static void stop(final String queryId) {
		   futures.get(queryId).cancel(true);
		   alertRepository.updateAlertStatus(false, queryId);
		   System.out.println("Trying to stop: " + queryId);
		}
	
	public static boolean isRunning(String queryId){
		if( null != futures.get(queryId)) {
			return true;
		}
		return false;
	}
}

/*
 * I have a story about the below code.... 
 * 
 * 
 * 
 * 
private static AlertServiceImpl alertServiceImpl;

private static AlertRepository alertRepository;

@Autowired
public AlertManager(AlertServiceImpl alertServiceImpl, AlertRepository alertRepository) {
	AlertManager.alertServiceImpl = alertServiceImpl;
	AlertManager.alertRepository = alertRepository;
	alertServiceImpl.setAlertRepository(alertRepository);
}*/