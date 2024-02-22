package com.ppvfra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ApplicationDusVariety;
import com.ppvfra.repository.ApplicationDusVarietyRepository;

@Service
@Transactional
public class ApplicationDusVarietyService {
	@Autowired
	ApplicationDusVarietyRepository applicationdusvarietyrepository;
	public Boolean save(ApplicationDusVariety applicationdusvariety) {
		 try {
			 applicationdusvarietyrepository.save(applicationdusvariety);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public ApplicationDusVariety getById(int id)
	{
		return applicationdusvarietyrepository.findById(id).get();
	}
}
