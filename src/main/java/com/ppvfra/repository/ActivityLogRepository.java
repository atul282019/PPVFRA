package com.ppvfra.repository;

 

 

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ActivityLogTable;
 
 
 
 

public interface ActivityLogRepository extends JpaRepository<ActivityLogTable, Integer>{

	@Query("select a from ActivityLogTable a where "
			+ "date (a.login_time_stamp) = current_date")
	List<ActivityLogTable> activitytab();
	
	@Query(value="select l.activityby,l.activity,l.url,"
	+ "date(l.login_time_stamp) from activitylogtable l "
	+ "where  date (l.login_time_stamp) between "
+ "coalesce(to_date(:fromdate,'yyyy-MM-dd'),'2010-01-01')"
+ " and coalesce(to_date(:todate,'yyyy-MM-dd'),"
	+ "current_date)",nativeQuery =true)
	List<Object[]> findReport1(@Param("fromdate") Date fromdate,@Param("todate") Date todate2);  
	
 }