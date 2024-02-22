package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.DusTestFee;
import com.ppvfra.entity.Fees;

public interface DusTestPayRepository extends  JpaRepository<DusTestFee, Long>{
	@Query("SELECT coalesce(dustestfee ,0) FROM "
			+ "DusTestFee where cropid = :cropid \r\n" + 
			"and '2015-08-01' between "
			+ "coalesce(dustestfee_validfrom,'1900-01-01') \r\n" + 
			"and  coalesce(dustestfee_validupto,current_date)")
	List<DusTestFee> getfeedetails(@Param("cropid") Integer cropid);
	
	/*
	@Query(value="select coalesce(dustestfee ,0) FROM dustestfee \r\n" + 
			"where cropid in (select a.cropid from applications a where a.id=:id )\r\n" + 
			"and dustestfee_validfrom <= current_date \r\n" + 
			"and dustestfee_validupto is null ",nativeQuery = true)
	*/
	@Query(value="select coalesce( (select dustestfee FROM dustestfee \r\n" + 
			"where cropid in (select a.cropid from applications a where a.id=:id)\r\n" + 
			"and dustestfee_validfrom <= current_date \r\n" + 
			"and dustestfee_validupto is null),0)",nativeQuery=true)
	long getlatestfee(@Param("id") Integer id);

}
