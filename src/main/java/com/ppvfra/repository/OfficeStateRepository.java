package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.OfficeStates;

public interface OfficeStateRepository extends JpaRepository<OfficeStates, Long> {

	@Query("select os.stateid from OfficeStates os where os.officeid=:officeid")
	List<OfficeStates> searchfor_states(@Param("officeid") Integer officeid);
 

@Transactional
@Modifying
@Query("delete from OfficeStates m where m.officeid=:officeid")
void removeOfficeIdDetails(@Param("officeid") Integer officeid);

}
