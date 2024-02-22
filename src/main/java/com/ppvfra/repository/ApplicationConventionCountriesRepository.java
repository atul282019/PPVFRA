package com.ppvfra.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationConventionCountries;

public interface ApplicationConventionCountriesRepository extends JpaRepository<ApplicationConventionCountries, Integer> {

	
    @Query("select a.id,a.applicationid,a.denomination,a.nature,a.fillingdate,c1.Country,a.authority,a.application_number,a.applicationstatus\r\n" + 
    		",c.Country,to_char(a.ondateofapplication,'dd-MM-yyyy'), to_char(a.fillingdate,'dd-MM-yyyy') \r\n" + 
    		"from ApplicationConventionCountries a\r\n" + 
    		"inner join Country  c\r\n" + 
    		"on a.incountries = c.id\r\n" + 
    		"inner join Country  c1\r\n" + 
    		"on a.country = c1.id\r\n" + 
    		"\r\n" + 
    		"where applicationid=:applicationid")
    List<Object[]> getConventionCountriesDetailById(@Param("applicationid") int applicationid);
    
    @Modifying
    @Transactional
	@Query("delete from  ApplicationConventionCountries a  where a.applicationid=:id")
	public int updateApplicationConventionByApplicationid(@Param("id") int id);
	 
}
