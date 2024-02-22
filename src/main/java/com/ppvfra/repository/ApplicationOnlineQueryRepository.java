package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationOnlineQuery;
import com.ppvfra.entity.Revocation;

public interface ApplicationOnlineQueryRepository 
extends JpaRepository<ApplicationOnlineQuery,Integer>
{
	@Query("select ao.id,ao.application_id,ao.sender_id,ao.reportpath,"
	+ "ao.remarks,ao.createdby,ao.createdon,ao.reportname,u.firstname,"
	+ "u.username,u.designation "
	+ "from ApplicationOnlineQuery ao inner join InternalUser "
	+ "u on ao.sender_id= u.id  where ao.application_id=:id")
	List<Object[]> apponlinedata(@Param("id") int id); 
	
	@Query("select ao.id from ApplicationOnlineQuery ao where ao.id=:id")
	List<Object[]> getapponlinequerid(@Param("id") int id);
	
	@Query(value="select 1, max(ao.id)  from application_onlinequery ao "
			+ "where ao.application_id=:applicationid"
			,nativeQuery=true)
	List<Object[]> getidfetched(@Param("applicationid") int applicationid);
	
	
	@Query("select ao.id,ao.application_id,ao.sender_id,ao.reportpath,ao.remarks,ao.createdby,ao.createdon,ao.reportname from ApplicationOnlineQuery ao where ao.id=:id")
	List<Object[]> getmaildata(@Param("id") int id); 

}
