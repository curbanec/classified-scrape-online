package com.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import com.domain.AlertRecord;
import com.domain.ClickRecord;
import com.service.AlertServiceImpl;
import com.service.PopulateAppActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/dashboardPopulate")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class DashboardPopulateResource {
	
	@Autowired
	PopulateAppActivityServiceImpl populateServiceImpl;
	
	/*@Autowired
	AlertServiceImpl alertServiceImpl;
*/
	@GET
	@Path("/applicationActivites")
	public List<ClickRecord> populateDashboard(@QueryParam("from") String from, @QueryParam("to") String to) {
		
		return populateServiceImpl.getRecordsByDate(from, to);
	}
	
	/*@GET
	@Path("/alerts")
	public List<AlertRecord> populateAlertTable() {
		
		return alertServiceImpl.retrieveAlerts();
	}*/
}
