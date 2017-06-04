package com.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dao.AlertRepository;
import com.domain.AlertRecord;
import com.domain.UserRecord;
import com.dto.AlertDto;

@Component
public class AlertServiceImpl {
	
	@PersistenceContext
	public EntityManager entityManager;

	@Autowired
	AlertRepository alertRepository;
	
	@Transactional
	public void addAlert(AlertDto alertDto){
		
		/*There is no INSERT statement in JPA. 
		You have to insert new entities using an EntityManager. 
		The only statements allowed in JPA are SELECT, UPDATE and DELETE.
		SO #3085716*/
			
		Session session = entityManager.unwrap(Session.class);
		
		UserRecord userRecord = (UserRecord) session.load(UserRecord.class, 1);

		AlertRecord alertRecord = new AlertRecord(userRecord, alertDto.getArea(), alertDto.getSubmissionTimeDate(), 
				alertDto.getQuery(), alertDto.getQueryId(), alertDto.getNotifyAddress());
	
		entityManager.persist(alertRecord);
	}
	
	/*public List<AlertRecord> retrieveAlerts(){
		
		return alertRepository.retrieve();
	}
	
	public void modifyAlert(){
		
		alertRepository.modify();
	}
	
	public void removeAlert(){
		
		alertRepository.delete();
	}*/
}
