package com.app;

import javax.ws.rs.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private CustomAuthenticationProvider authProvider;
	
	// definition of the "security policy"
	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.httpBasic().and().authorizeRequests()
          .antMatchers(
        		  "/signup.html",
        		  "/index.html",
        		  "/home.html", 
        		  "/login.html", 
        		  "/", 
        		  "/vendors/**", 
        		  "/images/**", 
        		  "/build/**", 
        		  "/js/**", 
        		  "/src/**", 
        		  "/css/**")
          .permitAll()
          .antMatchers(HttpMethod.POST, "/api/registration/userSignup").permitAll()
          .anyRequest()
          .authenticated()
          .and()
          .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
		System.out.println("configure method");
	}
	
	 /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("secret1").roles("USER")
                .and()
                .withUser("user2").password("secret2").roles("USER");
    }*/ 

	/*
	 * 
	 * UserDetailsService RIP
	 * 
	 * protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(loginServiceImpl); (loginServiceImpl used to implement UserDetailsService)
		return authProvider;
	}
*/
}