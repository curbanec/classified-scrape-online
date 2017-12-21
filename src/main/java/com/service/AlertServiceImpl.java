package com.service;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.dao.AlertRepository;
import com.dao.UserRepository;
import com.domain.AlertRecord;
import com.domain.UserRecord;
import com.dto.AlertDto;

@Component
public class AlertServiceImpl {
	
	@PersistenceContext
	public EntityManager entityManager;

	@Autowired
	AlertRepository alertRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public void addAlert(AlertDto alertDto){
		
		/*There is no INSERT statement in JPA. 
		You have to insert new entities using an EntityManager. 
		The only statements allowed in JPA are SELECT, UPDATE and DELETE.
		SO #3085716*/
			
		/*Session session = entityManager.unwrap(Session.class);
		
		UserRecord userRecord = (UserRecord) session.load(UserRecord.class, 1);*/
	    
		AlertRecord alertRecord = new AlertRecord(findUser(), alertDto.getArea(), alertDto.getSubmissionTimeDate(), 
				alertDto.getQuery(), alertDto.getQueryId(), alertDto.getNotifyAddress(), true);
	
		entityManager.persist(alertRecord);
		
	}
	
	public ArrayList<AlertRecord> retrieveAlerts(){
	
		return alertRepository.findByUserRecord(findUser());
	}
	
	private UserRecord findUser() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    
	    UserRecord userRecord = userRepository.findByUsername(name);
	    
	   // userRecord.setPassword(""); // don't include user password when returning user to front end. 
		
	    return userRecord;
	}
	
	/*public void updateAlert(Boolean isActiveIndicator, String queryId) {
	
		alertRepository.updateAlertStatus(isActiveIndicator, queryId);	
		if (isActiveIndicator) alertRepository.updateAlertStatusOpen(queryId);
		else alertRepository.updateAlertStatusClosed(queryId);
		alertRepository.updateName("dudesearch1", "916468");	
	}*/

	public void setAlertRepository(AlertRepository alertRepository) {
		this.alertRepository = alertRepository;
	}
	
	/*
	public void modifyAlert(){
		
		alertRepository.modify();
	}
	
	public void removeAlert(){
		
		alertRepository.delete();
	}*/
}