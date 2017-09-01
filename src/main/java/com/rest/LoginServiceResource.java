package com.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.LoginServiceImpl;

@RestController
public class LoginServiceResource {
	
	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	 @RequestMapping("/user")
	  public Principal user(Principal user) {
		 
		 return (Principal) loginServiceImpl.loadUserByUsername(user.getName());
	  }
}