package com.ppvfra.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ApplicationSubmission;
import com.ppvfra.repository.ApplicationSubmissionRepository;


@Service
@Transactional
public class ApplicationSubmissionService {
	@Autowired
	ApplicationSubmissionRepository appsubservice;
	
	public List<ApplicationSubmission> listall(){
		return appsubservice.findAll();
	}
	public Boolean save (ApplicationSubmission appsub){
	    try{
	    	appsubservice.save(appsub);
	        return true;
	    }
	    catch(Exception e)
		{ e.printStackTrace();
			return false;
		}
	}
	public ApplicationSubmission get(int id) {
		return appsubservice.findById(id).get();
	}
	public void delete(int id) {
		appsubservice.deleteById(id);
	}
	
	public void saveAll(List<ApplicationSubmission> appsub) {
		// TODO Auto-generated method stub
		
	}
}
