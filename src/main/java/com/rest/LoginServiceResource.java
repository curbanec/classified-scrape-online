package com.rest;

import java.security.Principal;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginServiceResource {
	
	 /*From Spring Security and Angular JS tutorial
	 *This is a useful trick in a Spring Security application. 
	 *If the "/user" resource is reachable then it will return the currently authenticated user (an Authentication), 
	 *and otherwise Spring Security will intercept the request and send a 401 response through an AuthenticationEntryPoint 
	 */
	
	 @RequestMapping("/user")
	 public Principal user(Principal user) {
		 	System.out.println("user");	 
		 return user;
	 }
	 
	 /*@RequestMapping("/userSignup")
	 public Response newUser(String ok) {
		 
		 System.out.println("userSignup");
		 
		 return Response.status(200).build();
		 
	 }*/	 
}