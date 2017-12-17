package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import com.dto.NewUserDto;
import com.service.RegisterUserServiceImpl;

@Path("/registration")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class RegisterUserResource {
	
	@Autowired
	RegisterUserServiceImpl registerUserServiceImpl;
	
	@POST
	@Path("/userSignup")
	public Response registerNewUser(NewUserDto newUserDto){
		
		System.out.println("userSignup called");
		
		registerUserServiceImpl.registerNewUser(newUserDto);
		
		return Response.status(500).build();	
	}
}