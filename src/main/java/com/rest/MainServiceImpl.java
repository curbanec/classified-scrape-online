package com.rest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import com.crawler.Crawler;
import com.crawler.CrawlerServiceFactory;
import com.crawler.AutomatedAlert;
import com.dto.AlertDto;
import com.dto.CRequestDto;

// TODO send type of crawler in request instead of hardcoding it. 
@Path("/main")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class MainServiceImpl implements MainService {

	@Autowired
	CrawlerServiceFactory crawlerServiceFactory;
 
	// TODO this method isn't being used.
	@GET
	@Path("/initiateCrawler/{region}/{query}/{pages}")
	public Response initiateCrawler(@PathParam("region") String region, @PathParam("query") String query, @PathParam("pages") String pages, @PathParam("maxDepth") boolean maxDepth) {

		Crawler crawler = crawlerServiceFactory.retrieveCrawler("craigsList");
		crawler.execute(region, query, pages, maxDepth);

		return crawler.getStatus();
	}

	@POST
	@Path("/initiateStateCrawler")
	public Response initiateStateCrawler(CRequestDto request) {

		Crawler crawler = crawlerServiceFactory.retrieveCrawler(request.getTypeOf());
		
		crawler.execute(request.getRegions(), request.getSearchQuery(), request.getPages(), request.isMaxDepth());

		return crawler.getStatus();
	}
	
	@SuppressWarnings({ "unused"})
	@POST
	@Path("/createAlert")
	public Response createAlert(AlertDto alertDto){
		
		final String region = alertDto.getArea();
		final String query = alertDto.getQuery();
		final String queryId = alertDto.getQueryId();
		final String submissionTimeDate = alertDto.getSubmissionTimeDate();

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	    Runnable svc = new Runnable(){
	    	public void run() {
			
	    		AutomatedAlert alert = new AutomatedAlert(); 
	    		alert.runPrimary(region, query, queryId, submissionTimeDate); 
	    		Thread.currentThread().setName(queryId); 
	    		System.out.println(Thread.currentThread().getName());
				}
		};
		
		final ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(svc, 10, 10, java.util.concurrent.TimeUnit.SECONDS);
		
		return Response.status(200).build();
	}
	
	@SuppressWarnings("deprecation")
	@GET
	@Path("/cancel")
	public Response cancelAlert(@QueryParam("queryId") String queryId){
		
		ThreadGroup threadGroup = Thread.currentThread( ).getThreadGroup( );
		Thread[] threads = new Thread[ threadGroup.activeCount()];
		threadGroup.enumerate(threads);
		for(int i=0;i<threads.length;i++){
			if(queryId.equals(threads[i].getName())){
				(threads[i]).stop();
				System.out.println("stopping: " + threads[i].getName());
			}
		}
		return Response.status(200).build();	
	}
}