/*package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.dao.AlertRepository;
import com.domain.AlertRecord;

public class AlertServiceImpl {
	
	@Autowired
	AlertRepository alertRepository;
	
	public void addAlert(){
		alertRepository.add();
	}
	
	public List<AlertRecord> retrieveAlerts(){
		
		return alertRepository.retrieve();
	}
	
	public void modifyAlert(){
		
		alertRepository.modify();
	}
	
	public void removeAlert(){
		
		alertRepository.delete();
	}
}
*/