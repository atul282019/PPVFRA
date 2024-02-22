package com.ppvfra.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Application;
public interface ApplicationProcessingRepository extends JpaRepository<Application, Integer>{
 
	@Query("select a.id,coalesce(a.company_name,mat.type),a.pvpno,"
		+ "to_char(a.createdon,'yyyy-MM-dd'), a.application_submission_date,"
		+ "coalesce(mv.varietycd,a.candidatevariety_other),a.varirtytypeid ,"
		+ "mas.status,a.application_current_status,a.formtype from "
		+ "Application a left join VarietyType mv on a.varirtytypeid = mv.id "
		+ "left join ApplicationStatus mas on a.application_current_status = "
		+ "mas.id inner join ApplicantTypes mat on mat.id = a.applicanttypeid " 
		+ "where a.application_current_status = 4 order by a.id desc")
	List<Object[]> find_applicationprocess_details();
	
	@Query("select a.id,coalesce(a.company_name,mat.type),a.pvpno,to_char(a.createdon,'yyyy-MM-dd'), \r\n" + 
			"a.application_submission_date,coalesce(mv.varietycd,a.candidatevariety_other),a.varirtytypeid\r\n" + 
			",mas.status,a.application_current_status,a.denomination,c.crop_common_name from Application a \r\n" + 
			"left join VarietyType mv on a.varirtytypeid = mv.id\r\n" + 
			"left join ApplicationStatus mas on a.application_current_status = mas.id\r\n" + 
			"left join Crops c on a.cropid  = c.id  "
		  + "left join ApplicantTypes mat on a.applicanttypeid = mat.id "
		  + "where a.application_current_status = 4 and a.id=:id")
	List<Object[]> find_applicationprocess_details_id(@Param("id") int id);
	
	@Query(value= "with tmp1 as  (select a.id applicationid,coalesce(a.company_name,mat.type) as comp_name,\r\n" + 
			"a.pvpno,to_char(a.createdon,'yyyy-MM-dd') created_on, \r\n" + 
			"a.application_submission_date,coalesce(mv.varietycd,a.candidatevariety_other) as vtname,a.varirtytypeid ,\r\n" + 
			"mas.status stat1,a.application_current_status,ad.id processid,\r\n" + 
			"ad.received_by_date,ad.assigned_by_user_id,ad.received_by_user_id,a.formtype \r\n" + 
			"from Applications a left join m_varietytype mv on \r\n" + 
			"a.varirtytypeid = mv.id left join m_applicationstatus mas \r\n" + 
			"on a.application_current_status = mas.id \r\n" + 
			"inner join  Assignment_Details ad on a.id= ad.applicationid \r\n" + 
			"and received_by_user_id=:userid  left join m_applicanttypes mat on a.applicanttypeid = mat.id ),\r\n" + 
			"tmp2 as ( select  applicationid,max(processid) lastpid,\r\n" + 
			"max(received_by_date) forwardingdate from tmp1 group by applicationid),\r\n" + 
			"tmp3 as (select applicationid,max(id) lastpid \r\n" + 
			"from Assignment_Details group by applicationid)  \r\n" + 
			"select distinct a1.applicationid,a1.comp_name,a1.pvpno,\r\n" + 
			"a1.created_on, a1.application_submission_date,a1.vtname,\r\n" + 
			"a1.varirtytypeid ,a1.stat1, a1.application_current_status,\r\n" + 
			"a1.processid,a1.received_by_date, (select coalesce(u.fullname,\r\n" + 
			"u.firstname) from users u where u.id=a1.assigned_by_user_id)\r\n" + 
			"Forwardedby,t2.forwardingdate forwardedon,\r\n" + 
			"a1.received_by_user_id ,AM.status,a1.formtype from tmp2 t2 \r\n" + 
			"inner join tmp1 a1 on a1.applicationid=t2.applicationid and \r\n" + 
			"a1.processid=t2.lastpid left join m_applicationstatus AM \r\n" + 
			"on am.id=a1.application_current_status inner join tmp3 on \r\n" + 
			"t2.applicationid=tmp3.applicationid and t2.lastpid=tmp3.lastpid;",nativeQuery = true)
	List<Object[]> find_applicationprocess_details_inbox(@Param("userid") int userid);
	
	
	@Query(value= "with tmp1 as  (select a.id applicationid,"
			+ "coalesce(a.company_name,mat.type) as comp_name,"
			+ "a.pvpno,to_char(a.createdon,'yyyy-MM-dd') created_on, "
			+ "a.application_submission_date,coalesce(mv.varietycd,a.candidatevariety_other) as vtname,a.varirtytypeid ,"
			+ "mas.status stat1,a.application_current_status,ad.id processid,"
			+ "ad.received_by_date,ad.assigned_by_user_id,"
			+ "ad.received_by_user_id,a.formtype from Applications a left join "
			+ "m_varietytype mv on a.varirtytypeid = mv.id left join "
			+ "m_applicationstatus mas on a.application_current_status "
			+ "= mas.id inner join  Assignment_Details ad on a.id= "
			+ "ad.applicationid and assigned_by_user_id=:userid"
			+ " left join m_applicanttypes mat on a.applicanttypeid = mat.id  ),"
			+ "tmp2 as ( select  applicationid,max(processid) lastpid,"
			+ "max(received_by_date) forwardingdate from tmp1 group by "
			+ "applicationid),"
			+ "tmp3 as (select applicationid,max(id) lastpid "
			+ "from Assignment_Details group by applicationid) "
			+ " select distinct a1.applicationid,a1.comp_name,a1.pvpno,"
			+ "a1.created_on, a1.application_submission_date,a1.vtname, "
			+ "a1.varirtytypeid ,a1.stat1, a1.application_current_status,"
			+ "a1.processid,a1.received_by_date, (select coalesce(u.fullname,"
			+ "u.firstname) from users u where u.id=a1.received_by_user_id)"
			+ "  Forwardedby,t2.forwardingdate forwardedon,"
			+ "a1.received_by_user_id ,AM.status,a1.formtype from tmp2 t2 "
			+ "inner join tmp1 a1 on a1.applicationid=t2.applicationid and "
			+ "a1.processid=t2.lastpid left join m_applicationstatus AM "
			+ "on am.id=a1.application_current_status inner join tmp3 on "
			+ "t2.applicationid=tmp3.applicationid and t2.lastpid=tmp3.lastpid;",nativeQuery = true)
			List<Object[]> find_applicationprocess_details_outbox(@Param("userid") int userid);

//Adding on 01-05-2020
			
			@Query("select coalesce(count(a.id),0) from Application a \r\n" + 
					"left join VarietyType mv on a.varirtytypeid = mv.id\r\n" + 
					"left join ApplicationStatus mas on a.application_current_status = "
					+ "mas.id inner join ApplicantTypes mat on mat.id = a.applicanttypeid "+ 
					"where a.application_current_status = 4")
			Long presentcountval1();
			
			
			@Query(value= "with tmp1 as  (select a.id applicationid,a.company_name,\r\n" + 
					"a.pvpno,to_char(a.createdon,'yyyy-MM-dd') created_on, \r\n" + 
					"a.application_submission_date,mv.varietycd,a.varirtytypeid ,\r\n" + 
					"mas.status stat1,a.application_current_status,ad.id processid,\r\n" + 
					"ad.received_by_date,ad.assigned_by_user_id,ad.received_by_user_id,a.formtype \r\n" + 
					"from Applications a left join m_varietytype mv on \r\n" + 
					"a.varirtytypeid = mv.id left join m_applicationstatus mas \r\n" + 
					"on a.application_current_status = mas.id \r\n" + 
					"inner join  Assignment_Details ad on a.id= ad.applicationid \r\n" + 
					"and received_by_user_id=:userid ),\r\n" + 
					"tmp2 as ( select  applicationid,max(processid) lastpid,\r\n" + 
					"max(received_by_date) forwardingdate from tmp1 group by applicationid),\r\n" + 
					"tmp3 as (select applicationid,max(id) lastpid \r\n" + 
					"from Assignment_Details group by applicationid)  \r\n" + 
					"select coalesce( count(distinct a1.applicationid),0) from "
					+ "tmp2 t2 \r\n" + 
					"inner join tmp1 a1 on a1.applicationid=t2.applicationid and \r\n" + 
					"a1.processid=t2.lastpid left join m_applicationstatus AM \r\n" + 
					"on am.id=a1.application_current_status inner join tmp3 on \r\n" + 
					"t2.applicationid=tmp3.applicationid and t2.lastpid=tmp3.lastpid;",nativeQuery = true)
			Long presentcountval2(@Param("userid") int userid);
			
			
			@Query(value= "with tmp1 as  (select a.id applicationid,a.company_name,"
					+ "a.pvpno,to_char(a.createdon,'yyyy-MM-dd') created_on, "
					+ "a.application_submission_date,mv.varietycd,a.varirtytypeid ,"
					+ "mas.status stat1,a.application_current_status,ad.id processid,"
					+ "ad.received_by_date,ad.assigned_by_user_id,"
					+ "ad.received_by_user_id,a.formtype from Applications a left join "
					+ "m_varietytype mv on a.varirtytypeid = mv.id left join "
					+ "m_applicationstatus mas on a.application_current_status "
					+ "= mas.id inner join  Assignment_Details ad on a.id= "
					+ "ad.applicationid and assigned_by_user_id=:userid ),"
					+ "tmp2 as ( select  applicationid,max(processid) lastpid,"
					+ "max(received_by_date) forwardingdate from tmp1 group by "
					+ "applicationid),"
					+ "tmp3 as (select applicationid,max(id) lastpid "
					+ "from Assignment_Details group by applicationid) "
					+ " select coalesce( count(distinct a1.applicationid),0) from tmp2 t2 "
					+ "inner join tmp1 a1 on a1.applicationid=t2.applicationid and "
					+ "a1.processid=t2.lastpid left join m_applicationstatus AM "
					+ "on am.id=a1.application_current_status inner join tmp3 on "
					+ "t2.applicationid=tmp3.applicationid and t2.lastpid=tmp3.lastpid;",nativeQuery = true)
					Long presentcountval3(@Param("userid") int userid);

			
			
	
			 
}
