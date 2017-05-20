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
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import com.crawler.Crawler;
import com.crawler.CrawlerServiceFactory;
import com.crawler.AutomatedAlert;
import com.dto.AlertDto;

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
	public Response initiateStateCrawler(CRequest request) {

		Crawler crawler = crawlerServiceFactory.retrieveCrawler(request.getTypeOf());
		
		crawler.execute(request.getRegions(), request.getSearchQuery(), request.getPages(), request.isMaxDepth());

		return crawler.getStatus();
	}
	
	/*@GET 
	@Path("/createAlert/{region}/{query}")
	public Response createAlert(@PathParam("region") final String region, @PathParam("query") final String query){
		
		ThreadSafeAlert alert = new ThreadSafeAlert();
		alert.runPrimary(region, query);
		
		Alert runAlertJob = new Alert(region, query);
		Thread newThread = new Thread(runAlertJob);
		newThread.start();
		
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final Runnable svc = new Runnable(){public void run() { ThreadSafeAlert alert = new ThreadSafeAlert(); alert.runPrimary(region, query);}};
		final ScheduledFuture<?> hmm = scheduler.scheduleAtFixedRate(svc, 10, 10, java.util.concurrent.TimeUnit.SECONDS);
		try {
			hmm.get();
		} catch (Exception e) { e.printStackTrace(); } 
		
		return Response.status(200).build();
		
	}*/
	
	@SuppressWarnings("unused")
	@POST
	@Path("/createAlert")
	public Response createAlert(AlertDto alertDto){
		
		final String region = alertDto.getArea();
		final String query = alertDto.getQuery();
		final String queryId = alertDto.getQueryId();
		final String submissionTimeDate = alertDto.getSubmissionTimeDate();
		
		/*ThreadSafeAlert alert = new ThreadSafeAlert();
		alert.runPrimary(alertDto.getArea(), alertDto.getQuery());
		Alert runAlertJob = new Alert(alertDto.getArea(), query);
		Thread newThread = new Thread(runAlertJob);
		newThread.start();*/
		
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final Runnable svc = new Runnable(){public void run() { AutomatedAlert alert = new AutomatedAlert(); alert.runPrimary(region, query, queryId, submissionTimeDate);}};
		final ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(svc, 10, 10, java.util.concurrent.TimeUnit.SECONDS);
		
		return Response.status(200).build();
	}     
}