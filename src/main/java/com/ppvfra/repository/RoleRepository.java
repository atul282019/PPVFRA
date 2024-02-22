package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.AccessTypes;
import com.ppvfra.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long>
{
	 @Query("Select a.id,a.description from Role a where "
	 		+ "a.name not in ('Company','Institution','Applicant')")
		public List<Role> getRoledescription();
	 
	 @Query("Select u.id from Role u where name=:name")
		int getIdByName(@Param("name")  String name);
	 
	 @Query("select e from Role e where e.status='Active' and e.name not in ('Company','Institution','Applicant')")
	 List<Role> getroleslist();
	 
	 @Query("select e from Role e where e.status='Active'")
	 List<Role> getactive_roleslist();
	 
	 @Query("select e from Role e where e.name not in ('Company','Institution','Applicant')")
	 List<Role> getroleslist_bystatus();
}