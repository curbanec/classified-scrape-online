package com.rest;

import javax.ws.rs.core.Response;

public interface MainService {

	public Response initiateCrawler(String region, String query, String pages, boolean maxDepth);

	public Response initiateStateCrawler(CRequest request);

}
