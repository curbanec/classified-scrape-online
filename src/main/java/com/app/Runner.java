package com.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages="com.app")
@EnableAutoConfiguration
@Configuration

public class Runner implements CommandLineRunner{
		
	public static void main(String[] args){
		SpringApplication.run(Runner.class, args);
		}
	
	//TODO future startup functionality 
	public void run(String... strings) throws Exception {}
}

