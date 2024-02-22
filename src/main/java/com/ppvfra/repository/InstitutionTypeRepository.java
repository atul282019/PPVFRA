package com.ppvfra.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ppvfra.entity.InstitutionTypes;
public interface InstitutionTypeRepository extends JpaRepository<InstitutionTypes, Long>{

	@Query("select itypes from InstitutionTypes itypes order by itypes.id")
	List<InstitutionTypes> instype();
}
