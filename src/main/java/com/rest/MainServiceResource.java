package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import com.concurrent.AlertManager;
import com.crawler.Crawler;
import com.crawler.CrawlerServiceFactory;
import com.dto.AlertDto;
import com.dto.CRequestDto;

// TODO send type of crawler in request instead of hardcoding it. 
@Path("/main")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class MainServiceResource {

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
	
	@POST
	@Path("/createAlert")
	public Response createAlert(final AlertDto alertDto){
		
		AlertManager.start(alertDto.getArea(), alertDto.getQuery(), alertDto.getQueryId(), alertDto.getSubmissionTimeDate());
		
		return Response.status(200).build();
	}
	
	@GET
	@Path("/cancel")
	public Response cancelAlert(@QueryParam("queryId") String queryId){
			
		AlertManager.stop(queryId);
		
		return Response.status(200).build();	
	}
}