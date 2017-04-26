package com.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.domain.ClickRecord;

@Path("/dashboardPopulate")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class DashboardPopulateServiceImpl {
	
	@Autowired
	PopulateServiceImpl populateServiceImpl;

	@GET
	@Path("/applicationActivites")
	public List<ClickRecord> populateDashboard(@QueryParam("from") String from, @QueryParam("to") String to) {
		
		return populateServiceImpl.getRecordsByDate(from, to);
	}
}
