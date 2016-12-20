package com.rest;

import javax.ws.rs.core.Response;

public interface MainService {
	
	public Response initiateCrawler(String query, String pages);

}
