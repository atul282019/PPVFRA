package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ApplicationTechnicalQuestionnaire;
import com.ppvfra.entity.Applications;
import com.ppvfra.repository.ApplicationTechnicalQuestionnaireRepository;


@Service
@Transactional
public class ApplicationTechnicalQuestionnaireService {

	@Autowired
	ApplicationTechnicalQuestionnaireRepository applicationquestionnaireepository;
	
	public List<ApplicationTechnicalQuestionnaire> listall()
	{
		return applicationquestionnaireepository.findAll();
	}
	
	public Boolean save(ApplicationTechnicalQuestionnaire applicationquestionnair)
	{
	    try {
			applicationquestionnaireepository.save(applicationquestionnair);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public ApplicationTechnicalQuestionnaire getById(int id)
	{
		return applicationquestionnaireepository.findById(id).get();
	}

	
}
