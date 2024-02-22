package com.ppvfra.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationSubmission;
import com.ppvfra.entity.MFormSection;
 
 

public interface ApplicationSubmissionRepository extends JpaRepository<ApplicationSubmission, Integer>
{


@Query("SELECT form_section_id FROM ApplicationSubmission where application_id=:appid")
public List<ApplicationSubmission> getMFormSectiondata(@Param("appid") int appid);
	
@Query(value="select coalesce( (select id from "
+ "application_submission where application_id=:appid"
+ " and form_section_id=:fsec ORDER BY id DESC LIMIT 1),0)",nativeQuery=true)
public List<Object[]> get_id_details(@Param("appid") int appid,@Param("fsec") int fsec);


 }