package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.DusTqGroup;

public interface DusTqGroupRepository extends JpaRepository<DusTqGroup,Integer> {

	@Query("select tq.id,tq.dustqserialnumber,tq.dustqgroup from DusTqGroup tq where tq.id=:serialnumber")
	public List<DusTqGroup> getTqGroupBySerialNumber(@Param("serialnumber") int serialnumber);

	 
}
