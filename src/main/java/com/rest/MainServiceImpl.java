package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;

import com.crawler.Crawler;
import com.crawler.CrawlerServiceFactory;

@Path("/main")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class MainServiceImpl implements MainService{
	
	@Autowired CrawlerServiceFactory crawlerServiceFactory;
	
	@GET
	@Path("/initiateCrawler/{query}/{pages}")
	public void initiateCrawler(@PathParam("query") String query, @PathParam("pages") String pages){
		
		Crawler crawler = crawlerServiceFactory.retrieveCrawler("craigsList");
		crawler.execute(query, pages);
	}
}
