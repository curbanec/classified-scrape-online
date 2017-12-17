package com.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import com.domain.UserRecord;
import com.dto.NewUserDto;

@Component
public class RegisterUserServiceImpl {

	@PersistenceContext
	public EntityManager entityManager;

	@Transactional
	public boolean registerNewUser(NewUserDto newUserDto) {

		boolean userSuccess;

		UserRecord userRecord = new UserRecord(newUserDto.getUsername(), newUserDto.getPassword());

		try {

			entityManager.persist(userRecord);

			userSuccess = true;

		} catch (Exception e) {

			userSuccess = false;

		}
		
		return userSuccess;

	}
}