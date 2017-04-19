package com.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/dashboardPopulate")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class DashboardPopulateServiceImpl {
	
	@GET
	@Path("/applicationActivites")
	public int[][] populateDashboard(){
		
		int[][] array = new int[17][2];
		for (int i=0; i<18; i++){
			for (int j=0; j<3; j++){
				array[i][j]=1;		
			}
		}
		
		
		return new int[17][2];
		
	}
}
