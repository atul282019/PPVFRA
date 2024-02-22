package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Revocation;

public interface RevocationRepository extends JpaRepository<Revocation,Integer>{

	
@Query(value="select ar.id,ar.applicationid,ar.date_revocation,ar.status,"
+ "ar.remarks from application_revocation ar "
+ "where ar.applicationid=:applicationid",nativeQuery=true)
List<Object[]> getrevocation_data(@Param("applicationid") long applicationid);
}
