package com.ppvfra.repository;

 



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Certificatenoissued;
 

 

public interface CertificateRepository extends JpaRepository<Certificatenoissued, Integer>{

	
	@Query(value="select cast(coalesce((max(id)+1),1) as int) from cerificate_no cn "
			+ "where cn.fyear =:fyear",nativeQuery=true)
			List<Object> getdata_for_certnum(@Param("fyear") String fyear);
	
	@Query(value="select cast(extract(year from current_date) as char(4))",nativeQuery =true )
	List<Object> getdate_data_certificate();
	
 }