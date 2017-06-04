package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dao.UserRepository;
import com.domain.UserRecord;

@Component
public class LoginServiceImpl {
	
	@Autowired
	UserRepository userRepository;
	
	public boolean login(String username, String password){
		
		UserRecord userRecord = userRepository.findByUsernameAndPassword(username, password);
		
		if(userRecord == null){
			return false;
		}
		
		return true;
	}
}
