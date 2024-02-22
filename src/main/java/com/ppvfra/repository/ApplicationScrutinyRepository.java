package com.ppvfra.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationScrutiny;

public interface ApplicationScrutinyRepository extends JpaRepository<ApplicationScrutiny, Integer>{

	@Modifying
    @Transactional
	@Query("update ApplicationScrutiny a set a.dateof_submission =:date1 ,a.remarks_doc_scrutiny =:remarks_doc_scrutiny where a.applicationid=:applicationid")
	public int updateApplicationScrutiny( @Param("date1") Date date1,@Param("remarks_doc_scrutiny") String remarks_doc_scrutiny, @Param("applicationid") int applicationid);
	
	@Modifying
    @Transactional
	@Query("update ApplicationScrutiny a set a.finalsubmit =:date1  where a.applicationid=:applicationid")
	public int updateApplicationScrutiny_finalsubmit(@Param("date1") Date date1 ,@Param("applicationid") int applicationid);
	
	
	@Query(value="with temp1 as (SELECT max(ad.id),ad.assigned_by_user_id ,ad.received_by_user_id\r\n" + 
			"FROM assignment_details ad where ad.received_by_user_id=:userid\r\n" + 
			"group by ad.assigned_by_user_id,ad.received_by_user_id)\r\n" + 
			"(select temp1.assigned_by_user_id assigned_id,u.email assigned_email,\r\n" + 
			" u.username assigned_username,u.designation assigned_designation\r\n" + 
			" ,temp1.received_by_user_id sender_id,t1.username sender_name,t1.designation sender_designation  \r\n" + 
			"from users u inner join temp1 on u.id = temp1.assigned_by_user_id \r\n" + 
			" inner join users t1 on t1.id= temp1.received_by_user_id )",nativeQuery = true)
	public List<Object[]> getscrdetails(@Param("userid") int userid);
	
	@Query("select asr from ApplicationScrutiny asr where asr.applicationid=:id")
	public List<ApplicationScrutiny> getdataviaid(@Param("id") int id);
	
	@Query("select distinct asr.finalsubmit from ApplicationScrutiny asr where asr.applicationid=:id")
	public List<ApplicationScrutiny> getfinalsubmitdata(@Param("id") int id);
	
	@Query(value="select sc.id,sc.applicationid,sc.category,sc.observation,mob.id as mobid,"
			+ "mob.formsection_id,mob.category as mob_category,sc.complaince,sc.documentname,mfs.section_name " + 
			"from application_scrutiny sc inner join m_observation_category mob on sc.observation =\r\n" + 
			"mob.category  inner join m_form_sections mfs on mob.formsection_id = "
			+ "mfs.id where sc.applicationid =:applicationid",nativeQuery=true)
	public List<Object[]> get_scr_details(@Param("applicationid") int applicationid);
}
 