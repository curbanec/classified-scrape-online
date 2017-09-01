package com.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.domain.UserRecord;

@Repository
public interface UserRepository extends CrudRepository<UserRecord, Long> {
	
	public UserRecord findByUsername(String username);
}
