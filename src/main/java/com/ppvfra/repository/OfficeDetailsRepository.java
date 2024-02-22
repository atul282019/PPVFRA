package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ppvfra.entity.OfficeDetails;
import com.ppvfra.entity.States;

public interface OfficeDetailsRepository extends JpaRepository<OfficeDetails, Long> {

	@Query("select distinct ms.statecode,ms.statename_inenglish \r\n" + 
"from OfficeDetails mo left join States ms on mo.state = ms.statecode where mo.active= true")
	List<Object[]> find_distinct_states();
	
	@Query("select o from OfficeDetails o where o.active=true ")
	List<OfficeDetails> officedetails();
}
