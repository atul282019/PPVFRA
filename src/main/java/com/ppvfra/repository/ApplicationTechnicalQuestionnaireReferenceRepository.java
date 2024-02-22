package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationTechnicalQuestionnaireReference;

public interface ApplicationTechnicalQuestionnaireReferenceRepository extends JpaRepository<ApplicationTechnicalQuestionnaireReference, Integer>{

	

	@Query(value="select id,applicationid,applicationtqid,technical_questionnaire_reference from application_technical_questionnaire_references where applicationid =:id", nativeQuery = true)
	List<Object[]> tqReferenceByApplicationId(@Param("id") int id);
	
	@Query("select technical_questionnaire_reference from  ApplicationTechnicalQuestionnaireReference where applicationid =:id")
	List<ApplicationTechnicalQuestionnaireReference> refrence_of_tq(@Param ("id") int id);
	
	
	@Query("select id,technical_questionnaire_reference from  ApplicationTechnicalQuestionnaireReference where applicationid =:id")
	List<Object[]> refrence_of_tq_forpdf(@Param ("id") int id);
	
}
