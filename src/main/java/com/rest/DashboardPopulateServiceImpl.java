package com.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.domain.ClickRecord;

@Path("/dashboardPopulate")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class DashboardPopulateServiceImpl {

	@GET
	@Path("/applicationActivites")
	public List<ClickRecord> populateDashboard() {

		List<ClickRecord> returnList = new ArrayList<ClickRecord>();
		ClickRecord click1 = new ClickRecord(2);
		ClickRecord click2 = new ClickRecord(8);
		ClickRecord click3 = new ClickRecord(6);
		ClickRecord click4 = new ClickRecord(4);
		ClickRecord click5 = new ClickRecord(3);
		ClickRecord click6 = new ClickRecord(5);
		ClickRecord click7 = new ClickRecord(7);
		ClickRecord click8 = new ClickRecord(6);
		
		returnList.add(click1);
		returnList.add(click2);
		returnList.add(click3);
		returnList.add(click4);
		returnList.add(click5);
		returnList.add(click6);
		returnList.add(click7);
		returnList.add(click8);
		
		return returnList;
	}
}
