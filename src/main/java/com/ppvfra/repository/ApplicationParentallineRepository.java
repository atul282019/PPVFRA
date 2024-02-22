package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationParentalline;

public interface ApplicationParentallineRepository extends JpaRepository<ApplicationParentalline, Integer> {

	@Query(value="select a.id,a.applicationid,a.parental_line,upper(a.denominations),a.source,a.authorised_letter_obtained,m.code from \r\n" + 
			"application_parentalline a\r\n" + 
			"inner join m_typeline m\r\n" + 
			"on m.id = a.typeline\r\n" + 
			"where applicationid=:applicationid",nativeQuery = true)
	List<Object[]> getParentaLineById(@Param("applicationid") Integer applicationid);

	
	@Query(value="select a.id,a.applicationid,"
	+ "a.typeline,m.code from application_parentalline "
	+ "a inner join m_typeline m on m.id = "
	+ "a.typeline where applicationid=:applicationid",
	nativeQuery = true)
	List<Object[]> getParentalLineData(@Param("applicationid") Integer applicationid);
	
	@Modifying
	@Transactional
	@Query("delete from ApplicationParentalline a where a.applicationid =:applicationid")
	public int updatedapplicationparentaline(@Param("applicationid") int applicationid); 

}
