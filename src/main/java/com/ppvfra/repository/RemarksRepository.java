package com.ppvfra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Remarks;

public interface RemarksRepository extends JpaRepository<Remarks,Integer> {
	
	@Query(value="select id,applicationid,remarks,status  from application_remarks where applicationid=:id",nativeQuery= true)
	List<Object[]> getremarks_applicationid(@Param("id") int id);
	
	
	@Query(value="select ar.id,ar.applicationid,ar.remarks,"
	+ "ar.status,u.firstname,ar.createdby,ar.createdon "
	+ "from application_remarks ar left join  users u on "
	+ "ar.createdby = u.id where ar.applicationid=:id order by ar.id",
	nativeQuery= true)
	List<Object[]> getremarks_datafortable(@Param("id") int id);

}
