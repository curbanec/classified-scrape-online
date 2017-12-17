package com.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;import com.domain.UserRecord;
import com.dto.NewUserDto;

@Component
public class RegisterUserServiceImpl {
	
	@PersistenceContext
	public EntityManager entityManager;
	
	public void registerNewUser(NewUserDto newUserDto) {
		
		UserRecord userRecord = new UserRecord(newUserDto.getUsername(), newUserDto.getPassword());
		
		entityManager.persist(userRecord);	
	}
}