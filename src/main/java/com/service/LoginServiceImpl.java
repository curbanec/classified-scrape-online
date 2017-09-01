package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.dao.UserRepository;
import com.domain.UserRecord;

@Component
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) {
		String hi = "hi";
		System.out.println(hi);
		UserRecord user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);

		}
		return new MyUserPrincipal(user);
	}

	public void hi() {

		String hi = "hi";
		System.out.println(hi);
	}
}