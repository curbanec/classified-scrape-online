package com.app;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages={"com.app", "com.crawler", "com.rest", "com.domain", "com.dao"})
@EnableJpaRepositories("com.dao")
@EntityScan(basePackages = "com.domain")
@EnableAutoConfiguration
@Configuration

public class Runner implements CommandLineRunner{
	
	public static ConcurrentHashMap<String, String> AlertsDatabase = new ConcurrentHashMap<String, String>();
		
	public static void main(String[] args){

		SpringApplication.run(Runner.class, args);
		}
	
	public void run(String... strings) throws Exception {}
	
	public ConcurrentHashMap<String, String> getAlertsDatabase(){
		return AlertsDatabase;
	}
}