package com.ppvfra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ppvfra.entity.ApplicantTypes;


public interface ApplicationTypesRepository  extends JpaRepository<ApplicantTypes, Integer> {
	
	@Query("select a.id,a.type from ApplicantTypes a where id in(1,2,3,4,5,6)")
	List<ApplicantTypes> applicanttype();
	
	@Query("select a.id,a.type from ApplicantTypes a where id in(1,2,3,6)")
	List<ApplicantTypes> applicanttypeform2();
	
	@Query("select a.id,a.type from ApplicantTypes a where id in(7,8,9)")
	List<ApplicantTypes> applicanttypeform3();
		
}
