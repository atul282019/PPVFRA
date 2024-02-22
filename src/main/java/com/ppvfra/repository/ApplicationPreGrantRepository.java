package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationCertificates;
import com.ppvfra.entity.ApplicationPreGrantOpposition;


public interface ApplicationPreGrantRepository extends JpaRepository<ApplicationPreGrantOpposition, Integer> {

	@Query(value="select id,status from m_applicationstatus where status in ('Opposition accepted','Opposition rejected')",nativeQuery= true)
	List<Object[]> getdata_val();
	
	@Query(value="select apgo.id,apgo.applicationid,apgo.is_pre_grant_opposition_received,\r\n" + 
			"apgo.opposition_status,apgo.opposition_filed_by\r\n" + 
			",apgo.date_of_deceision  from application_pre_grant_opposition apgo\r\n" + 
			"where apgo.applicationid=:applicationid",nativeQuery= true)
	List<Object[]> get_data_saved(@Param("applicationid") int applicationid);
	
}
