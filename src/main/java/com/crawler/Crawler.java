package com.crawler;

import javax.ws.rs.core.Response;

public interface Crawler {
	
	public void execute(String customSearchQuery, String pagesToQuery);
	
	public Response getStatus();
}
