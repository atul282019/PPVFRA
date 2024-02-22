package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.CandidateVarietyDetails;


public interface CandidateVarietyDetailsRepository extends JpaRepository<CandidateVarietyDetails ,Integer>{

  @Query("select cvd.candidate_variety_id,cvd.candidate_variety_other from CandidateVarietyDetails cvd where cvd.applicationid=:applicationid")
  List<CandidateVarietyDetails> searchforvariety(@Param("applicationid") Long applicationid);
  
  @Query(value="select 1 from application_candidate_variety_details cvd where cvd.applicationid=:applicationid and candidate_variety_id = 2",nativeQuery = true)
  public String hibridVarietyCount(@Param("applicationid") int applicationid);
	/*
	 * @Query("select cvd.candidate_variety_id,cvd.candidate_variety_other from CandidateVarietyDetails cvd where cvd.applicationid=:applicationid and cvd.candidate_variety_id=:2"
	 * ) String varietyValuebyApplicationId(@Param("applicationid") Long
	 * applicationid);
	 */
	/*
	 * @Query("select cvd.candidate_variety_id,cvd.candidate_variety_other from CandidateVarietyDetails cvd where cvd.applicationid=:applicationid and cvd.candidate_variety_id=:2"
	 * ) String varietyValuebyApplicationIdAndVarietyid(@Param("applicationid") Long
	 * applicationid);
	 */
 
  @Query(value="select candidate_variety_id from application_candidate_variety_details where applicationid =:appid",nativeQuery = true)
  List<Object[]> getdetails_withapplicationid(@Param("appid") int appid );
  
  @Query(value="select string_agg(mc.candidate_variety,',') ctype from application_candidate_variety_details acvd \r\n" + 
  		"inner join m_candidatevariety mc on acvd.candidate_variety_id = mc.id\r\n" + 
  		"where applicationid =:appid",nativeQuery= true)
  List<Object[]> can_var_type(@Param("appid") int appid);
}
