package com.dao;

import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.domain.AlertRecord;
import com.domain.UserRecord;

@Repository
public interface AlertRepository extends CrudRepository<AlertRecord, Long> {

	public ArrayList<AlertRecord> findByUserRecord(UserRecord userRecord); 

	/*Spring-data-jpa supports update operation.
	You have to define the method in Repository interface.and annotated it with @Query and @Modifying*/
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE AlertRecord a SET a.isActiveIndicator =:isActiveIndicator WHERE a.queryId =:queryId")
	@Transactional 
	public void updateAlertStatus(@Param("isActiveIndicator") boolean isActiveIndicator, @Param("queryId") String queryId);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE AlertRecord a SET a.isActiveIndicator =false WHERE a.queryId =:queryId")
	@Transactional 
	public void updateAlertStatusClosed(@Param("queryId") String queryId);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE AlertRecord a SET a.isActiveIndicator =true WHERE a.queryId =:queryId")
	@Transactional 
	public void updateAlertStatusOpen(@Param("queryId") String queryId);
	
	
	// 916468
	//@Transactional 
	//@Modifying(clearAutomatically = true)
	//@Query("UPDATE AlertRecord a SET a.queryName =:queryName WHERE a.queryId =:queryId")
	//public void updateName(@Param("queryName") String isActiveIndicator, @Param("queryId") String queryId);
	
	
	/*public void add();
	
	public List<AlertRecord> retrieve();
	
	public void delete();*/

}