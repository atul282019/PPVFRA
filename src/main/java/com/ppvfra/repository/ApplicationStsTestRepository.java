package com.ppvfra.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationStsTest;

public interface ApplicationStsTestRepository extends JpaRepository<ApplicationStsTest,Integer>{

	
	@Query("select ats.id,atsd.id,ats.applicationid,"
+ "atsd.applicationid,ats.testtype,ats.year,ats.season,"
+ "ats.lead_center,atsd.seed_sent_to_leadcenter,"
+ "ats.coopcenter,atsd.seed_sent_to_coopcenter,"
+ "atsd.dateof_seadsent,ats.speedpost_no,ats.tracking,"
+ "atsd.additional_seed,ats.no_of_final_packets,"
+ "mtc.dus_test_center,mtcc.dus_test_center from "
+ "ApplicationStsTest ats "
+ "join ApplicationStsTestSeedDetails atsd on ats.id= "
+ "atsd.application_sts_test_id left join "
+ "DusTestCenter mtc on ats.lead_center = mtc.id left "
+ "join DusTestCenter mtcc on ats.coopcenter = mtcc.id "
+ "where ats.applicationid=:id")
List<Object[]> getApplicationStsTestDetails(@Param("id") int id);
	    
	    
	    @Modifying
	    @Transactional
		@Query("update ApplicationStsTest a set a.no_of_final_packets =:no_of_final_packets where a.applicationid=:applicationid")
		public int updateApplicationSts( @Param("no_of_final_packets") Integer no_of_final_packets,  @Param("applicationid") int applicationid);
	    
	@Query("select coalesce(sum(no_of_final_packets),0) from ApplicationStsTest where applicationid=:applicationid")
	List<Object> sumreq(@Param("applicationid") int i);
	

@Query(value="select ast.testtype,ast.year,ast.season,astsd.type_line from application_sts_test ast \r\n" + 
		"inner join application_sts_test_seed_details astsd \r\n" + 
		"on ast.id=astsd.application_sts_test_id \r\n" + 
		"and ast.applicationid = astsd.applicationid where ast.applicationid =:appid",nativeQuery= true)
List<Object[]> applicationsts_saveddata(@Param("appid") int appid);



}
