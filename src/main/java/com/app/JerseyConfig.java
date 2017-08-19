package com.app;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import com.rest.MainServiceResource;
import com.rest.LoginResource;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig{
	public JerseyConfig(){
		super();
		this.registerEndpoints();	
	}
	
	private void registerEndpoints(){
		System.out.println("registerEndpoints() called");
		this.register(MainServiceResource.class);
		this.register(LoginResource.class);
	}
}