package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

@Path("/login")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class LoginResource {
    
    @GET
	@Path("/{user}-{pass}")
    public Response loginUser(@PathParam("user") String user, @PathParam("pass") String pass){
        System.out.println(user);
        System.out.println(pass);
        
        return Response.status(200).build();
    }
}
