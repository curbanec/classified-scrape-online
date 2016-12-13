package com.app;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/main")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class MainService {
	
	@Autowired
	CraigsListCrawlerClass crawlerClass;
	
	@GET
	@Path("/initiateCrawler/{query}/{pages}")
	public void initiateCrawler(@PathParam("query") String query, @PathParam("pages") String pages){
		System.out.println("GET Called");
		try{
			crawlerClass.run(query);
			for (int i = 1; i < Integer.valueOf(pages); i++){
				crawlerClass.run(query, String.valueOf(i));
			}
				}catch(Exception e){e.printStackTrace();}
	}
}
