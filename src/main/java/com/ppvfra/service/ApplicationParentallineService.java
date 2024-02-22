package com.ppvfra.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.ApplicationParentalline;
import com.ppvfra.repository.ApplicationParentallineRepository;

@Service
@Transactional
public class ApplicationParentallineService {
	
	@Autowired
	ApplicationParentallineRepository applicationparentallinerepository;
	
	public Boolean save(ApplicationParentalline applicationparentaline) {
	   try {
		applicationparentallinerepository.save(applicationparentaline);
		return true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	  
	}
	
	public ApplicationParentalline getById(int id)
	{
		return applicationparentallinerepository.findById(id).get();
	}
	
	
	}
