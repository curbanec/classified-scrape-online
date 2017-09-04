package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dao.UserRepository;
import com.domain.UserRecord;

@Component
public class LoginServiceImpl {

	@Autowired
	UserRepository userRepository;

	public UserRecord loadUser(String username, String password) {
		
		return userRepository.findByUsernameAndPassword(username, password);
		
	}
}