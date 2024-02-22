package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ppvfra.entity.ApplicantRole;
public interface ApplicantRoleRepository extends JpaRepository<ApplicantRole, Integer>{
	
	@Query("select  a.id,a.applicantrole from ApplicantRole a")
	List<ApplicantRole> applicantRoleList();
	

}
