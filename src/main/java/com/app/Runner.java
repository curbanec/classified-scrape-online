package com.app;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*import java.security.Principal;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@ComponentScan(basePackages={"com.app", "com.crawler", "com.rest", "com.domain", "com.dao", "com.email", "com.service"})
@EnableJpaRepositories("com.dao")
@EntityScan(basePackages = "com.domain")
@EnableAutoConfiguration
@Configuration
@SpringBootApplication
public class Runner implements CommandLineRunner{
	
	public static ConcurrentHashMap<String, String> AlertsDatabase = new ConcurrentHashMap<String, String>();
		
	public static void main(String[] args){

		SpringApplication.run(Runner.class, args);
		}
	
	public void run(String... strings) throws Exception {}
	
	public ConcurrentHashMap<String, String> getAlertsDatabase(){
		return AlertsDatabase;
	}
	
	@RequestMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  }
	
	 @Configuration
	 @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	 protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	   @Override
	   protected void configure(HttpSecurity http) throws Exception {
	     http.httpBasic().and().authorizeRequests()
	         .antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll().anyRequest().authenticated().and()
	         .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());;
	   }
	   	    
	   @Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	       auth.inMemoryAuthentication()
	               .withUser("user1").password("secret1").roles("USER")
	               .and()
	               .withUser("user2").password("secret2").roles("USER");
	   } 
	 }  
}*/

@ComponentScan(basePackages={"com.app", "com.crawler", "com.rest", "com.domain", "com.dao", "com.email", "com.service"})
@EnableJpaRepositories("com.dao")
@EntityScan(basePackages = "com.domain")
@EnableAutoConfiguration
@SpringBootApplication
@RestController
public class Runner implements CommandLineRunner {
	
	public static ConcurrentHashMap<String, String> AlertsDatabase = new ConcurrentHashMap<String, String>();
	 
	public static void main(String[] args) {
		    SpringApplication.run(Runner.class, args);
		  }
	
	public void run(String... strings) throws Exception {}
	 @RequestMapping("/resource")
	  public Map<String,Object> home() {
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("content", "Hello World");
	    return model;
	  }
	
	 @RequestMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  }
	 
	  @Configuration
	  @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	      http.httpBasic().and().authorizeRequests()
	          .antMatchers("/index.html", "/home.html", "/login.html", "/", "/vendors/**", "/images/**", "/build/**", "/js/**", "/src/**", "/css/**").permitAll().anyRequest().authenticated().and()
	          .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	    }
	    	    
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication()
	                .withUser("user1").password("secret1").roles("USER")
	                .and()
	                .withUser("user2").password("secret2").roles("USER");
	    } 
	  }  
	  
	  public ConcurrentHashMap<String, String> getAlertsDatabase(){
			return AlertsDatabase;
		}
}








