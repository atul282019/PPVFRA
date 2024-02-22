package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationTechnicalQuestionnaire;

public interface ApplicationTechnicalQuestionnaireRepository extends JpaRepository<ApplicationTechnicalQuestionnaire, Integer>{
	
		@Query("SELECT b.name FROM ApplicationTechnicalQuestionnaire b WHERE b.id in (SELECT a.id FROM ApplicationTechnicalQuestionnaire a WHERE a.applicationid =:id)")
		String getquestionIdByApplicationId(@Param("id") int id);
		
		@Query("SELECT count(b.id) as id FROM ApplicationTechnicalQuestionnaire b WHERE b.id in (SELECT a.id FROM ApplicationTechnicalQuestionnaire a WHERE a.applicationid =:id)")
		int getIdByApplicationId(@Param("id") int id);
		
		@Query("select a.id from ApplicationTechnicalQuestionnaire a where a.applicationid=:id")
	    int getTqId(@Param("id") int id);

		@Query("select a from ApplicationTechnicalQuestionnaire a where a.id=:id")
	    List<ApplicationTechnicalQuestionnaire> get_tech_data(@Param("id") int id);
		
		@Query(value="select coalesce ((SELECT a.id FROM application_technical_questionnaire a WHERE a.applicationid =:id),0)",nativeQuery=true)
		int getIdByApplicationId_pdfmethod(@Param("id") int id);
		
}
