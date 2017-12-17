package com.app;

import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.domain.UserRecord;
import com.service.LoginServiceImpl;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserRecord user = loginServiceImpl.loadUser(username, password);
		
		if(null != user) { 
					
			Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
		    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, authorities);
		    return authenticationToken;
		    
		}
		
		return null;
		
	}
		
	@Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}