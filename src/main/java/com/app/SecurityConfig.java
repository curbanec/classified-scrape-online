package com.app;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.LoginServiceImpl;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.httpBasic().and().authorizeRequests()
          .antMatchers("/index.html", "/home.html", "/login.html", "/", "/vendors/**", "/images/**", "/build/**", "/js/**", "/src/**", "/css/**").permitAll().anyRequest().authenticated().and()
          .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
	
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("secret1").roles("USER")
                .and()
                .withUser("user2").password("secret2").roles("USER");
    }*/ 

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(loginServiceImpl);
		return authProvider;
	}























}
	
	
	
	
	
	
	
	





















































/*package com.app;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.LoginServiceImpl;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginServiceImpl loginServiceImpl;
	
	 @RequestMapping("/user")
	  public Principal user(Principal user) {
		 loginServiceImpl.hi(); 
	    return user;
	  }
	 
	 protected void configure(HttpSecurity http) throws Exception {
	      http.httpBasic().and().authorizeRequests()
	          .antMatchers("/index.html", "/home.html", "/login.html", "/", "/vendors/**", "/images/**", "/build/**", "/js/**", "/src/**", "/css/**").permitAll().anyRequest().authenticated().and()
	          .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	    }
	    	    
	    protected void configure(AuthenticationManagerBuilder auth)
	      throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    } 
	    
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider
	          = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(loginServiceImpl);
	        return authProvider;
	    }
}*/