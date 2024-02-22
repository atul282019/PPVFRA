package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ppvfra.entity.ApplicationTechnicalQuestionnaireReference;
import com.ppvfra.repository.ApplicationTechnicalQuestionnaireReferenceRepository;

@Service
@Transactional
public class ApplicationTechnicalQuestionnaireReferenceService {
	@Autowired
	ApplicationTechnicalQuestionnaireReferenceRepository applicationquestionnairereferencerepository;
	public List<ApplicationTechnicalQuestionnaireReference> listall()
	{
		return applicationquestionnairereferencerepository.findAll();
	}
	
	public Boolean save(ApplicationTechnicalQuestionnaireReference applicationquestionnair)
	{
	    try {
	    	applicationquestionnairereferencerepository.save(applicationquestionnair);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public ApplicationTechnicalQuestionnaireReference getById(int id)
	{
		return applicationquestionnairereferencerepository.findById(id).get();
	}

}
