package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.NationalRegister;

public interface NationalRegisterRepository extends JpaRepository<NationalRegister,Integer>{

	@Query(value="select an.id,an.applicationid,an.entered_in_national_register,\r\n" + 
			"an.date_of_entry,an.register_number, an.page_number, an.createdby \r\n" + 
			"from application_nationalregister an where an.applicationid =:applicationid",nativeQuery= true)
	List<Object[]> getnational_data(@Param("applicationid") int applicationid);
}
