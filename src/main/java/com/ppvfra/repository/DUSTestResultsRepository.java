package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.DUSTestResults;

public interface DUSTestResultsRepository extends JpaRepository<DUSTestResults,Integer>{

	@Query("select t.id,t.status from ApplicationStatus t where t.stageid=8 and t.active= true")
	List<Object[]> finddetails(); 
	
	@Query("select adt.id,adt.applicationid, adt.dus_test_completed, "
			+ "to_char(adt.date_of_completion,'yyyy-MM-dd'),adt.dus_test_report_status, "
			+ "adt.remarks,adt.reportname, adt.reportpath,adt.createdby, "
			+ "adt.createdon from DUSTestResults adt "
			+ "where adt.applicationid =:applicationid")
	List<Object[]> datasaved_dus_test(@Param("applicationid") int id);
}
