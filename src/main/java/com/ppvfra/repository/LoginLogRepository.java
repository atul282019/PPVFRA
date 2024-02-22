package com.ppvfra.repository;

 

 

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.LogTable;
 
 

public interface LoginLogRepository extends JpaRepository<LogTable, Integer>{


	@Query("select l from LogTable l where "
			+ "date (l.login_time_stamp) = current_date")
	List<LogTable> logtab();
	
	@Query(value="select l.username,l.logintype,"
			+ "date(l.login_time_stamp) from LogTable l "
			+ "where  date (l.login_time_stamp) between "
			+ "coalesce(to_date(:fromdate,'yyyy-MM-dd'),'2010-01-01') and "
			+ "coalesce(to_date(:todate,'yyyy-MM-dd'),current_date)",
			nativeQuery =true)
	List<Object[]> findReport1(@Param("fromdate") Date fromdate,@Param("todate") Date todate2);
	
	
 }