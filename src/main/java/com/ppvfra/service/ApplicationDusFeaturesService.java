package com.ppvfra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ApplicationDusFeatures;
import com.ppvfra.repository.ApplicationDusFeaturesRepository;

@Service
@Transactional
public class ApplicationDusFeaturesService {
	@Autowired
	ApplicationDusFeaturesRepository applicationdusfeaturerepository;
	public Boolean save(ApplicationDusFeatures applicationdusfeature) {
		 try {
			 applicationdusfeaturerepository.save(applicationdusfeature);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
