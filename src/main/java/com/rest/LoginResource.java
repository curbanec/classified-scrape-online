package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import com.service.LoginServiceImpl;

@Path("/login")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class LoginResource {
	
	@Autowired
	LoginServiceImpl loginServiceImpl;
    
    @GET
	@Path("/{user}/{pass}")
    public Response loginUser(@PathParam("user") String username, @PathParam("pass") String password){
        
    	System.out.println(username);
        System.out.println(password);
        
        if(!loginServiceImpl.login(username, password)){
        	return Response.status(500).build();
        }
  
        return Response.status(200).build();
    }
}