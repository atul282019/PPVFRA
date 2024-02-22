package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.RegistrarJurisdictionOffice;

public interface RegistrarJurisdictionOfficeRepository  extends JpaRepository<RegistrarJurisdictionOffice,Integer>{

	@Query("select rg.office from RegistrarJurisdictionOffice rg where rg.userid=:userid")
	List<RegistrarJurisdictionOffice> getofficedetails(@Param("userid") Integer userid);
	
	@Transactional
    @Modifying
    @Query("delete from RegistrarJurisdictionOffice rjo where rjo.userid=:userid")
    void removeOfficeDetails(@Param("userid") Integer userid);

}
