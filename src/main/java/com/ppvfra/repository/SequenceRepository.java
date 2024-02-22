package com.ppvfra.repository;

 



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.SeqNo;

 

public interface SequenceRepository extends JpaRepository<SeqNo, Integer>{
	
	@Query(value="select coalesce((select id+1 from seq_no where fyear=:year),1)",nativeQuery=true)
	List<Object[]> fetchsequence(@Param("year") String year);

	
 }