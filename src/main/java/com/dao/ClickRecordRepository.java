package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.domain.ClickRecord;

@Repository
public interface ClickRecordRepository extends CrudRepository<ClickRecord, Long> {
	
	// SELECT * FROM CrawlerDB.CLICKS WHERE TODAY_DATE BETWEEN '2017-04-19' AND '2017-04-29';
	@Query("from ClickRecord c WHERE c.date BETWEEN ?1 AND ?2")
	public List<ClickRecord> findByDate(String startDate, String beginDate);
}