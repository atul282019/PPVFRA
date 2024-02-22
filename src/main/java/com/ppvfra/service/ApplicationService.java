package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 
import com.ppvfra.entity.Application;
 
import com.ppvfra.repository.ApplicationRepository;
@Service
@Transactional
public class ApplicationService {
	 
	@Autowired
	ApplicationRepository applicationrepository;
	
	
	public List<Application> listall(){
		return applicationrepository.findAll();
	}

}
