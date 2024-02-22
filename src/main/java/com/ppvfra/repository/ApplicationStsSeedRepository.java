package com.ppvfra.repository;

 

 

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationStsSeedrecieved;
public interface ApplicationStsSeedRepository extends JpaRepository<ApplicationStsSeedrecieved, Integer>{
 
	@Query("select id,applicationid, candidate_variety_id \r\n" + 
			"from CandidateVarietyDetails \r\n" + 
			"where applicationid =:id and candidate_variety_id=2")
	List<Object[]> if_present(@Param("id") Long id);	 	 

@Query("select ast.id,ast.receiptdate,ast.seed_req_asper_guideline,"
+ "ast.quantityofseed_recd,ast.no_of_packets,ast.moisture_ptg,"
+ "ast.germination_ptg ,ast.purity_ptg,ast.type_line,"
+ "ast.approx_weight,ast.seedorplant,ast.seed_testing_report_name,"
+ "ast.seed_testing_report_path from ApplicationStsSeedrecieved ast "
+ "where applicationid =:id")
List<ApplicationStsSeedrecieved> returnlist(@Param("id") Long id);
	 
	@Query("select ast.id,ast.type_line,ast.applicationid,ast.seed_testing_report_name,ast.seed_testing_report_path from ApplicationStsSeedrecieved ast where ast.id=:id")
    List <Object[]> typeline(@Param("id") int id);
    
    @Query("select ast.id,ast.type_line,ast.applicationid,ast.seed_testing_report_name,ast.seed_testing_report_path from ApplicationStsSeedrecieved ast where ast.applicationid=:id")
    List <Object[]> typeline1(@Param("id") Long id);
    
    @Query(value = "select ast.id,ast.receiptdate,ast.seed_req_asper_guideline,\r\n" + 
    		"ast.quantityofseed_recd,ast.no_of_packets,ast.moisture_ptg,\r\n" + 
    		"ast.germination_ptg ,ast.purity_ptg,ast.type_line,ast.approx_weight,\r\n" + 
    		"ast.seedorplant,ast.seed_testing_report_name,ast.seed_testing_report_path,ast.active  \r\n" + 
    		"from ApplicationStsSeedrecieved ast where ast.applicationid =:applicationid")
    List<Object[]> finddata_set(@Param("applicationid") Long applicationid);
 
    @Modifying
    @Transactional
	@Query("update ApplicationStsSeedrecieved a set a.checkedby =:checkedby ,a.remarks =:remarks where a.applicationid=:applicationid")
	public int updateApplicationStsSeedrecieved( @Param("checkedby") Long checkedby,@Param("remarks") String remarks, @Param("applicationid") long applicationid);
    
    
    @Query(value="select distinct remarks from application_sts_seedrecieved where applicationid=:id",nativeQuery = true)
    List <Object[]> remarks_stsreceived(@Param("id") Long id);
    
    
}
