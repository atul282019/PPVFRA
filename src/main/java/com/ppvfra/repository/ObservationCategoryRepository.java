package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ObservationCategory;

public interface ObservationCategoryRepository extends JpaRepository<ObservationCategory, Integer>{

	
	@Query("SELECT d.id, d.category FROM ObservationCategory d where d.formsection_id=:formcode")
	public List<ObservationCategory> getobservationdata(@Param("formcode") Integer formcode);

	
}
