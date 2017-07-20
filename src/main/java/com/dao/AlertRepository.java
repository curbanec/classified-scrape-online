package com.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.AlertRecord;

@Repository
public interface AlertRepository extends CrudRepository<AlertRecord, Long> {

	/*public List<AlertRecord> retrieve(); */
	

	/*public void add();
	
	public List<AlertRecord> retrieve();
	
	public void modify();
	
	public void delete();*/

}
