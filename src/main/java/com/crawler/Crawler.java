package com.crawler;

import java.util.List;

import javax.ws.rs.core.Response;

public interface Crawler {
	
	public void execute(String region, String customSearchQuery, String pagesToQuery, boolean maxDepth);
	
	public void execute(List<String> regions, String customSearchQuery, String pagesToQuery, boolean maxDepth);
	
	public Response getStatus();
}
