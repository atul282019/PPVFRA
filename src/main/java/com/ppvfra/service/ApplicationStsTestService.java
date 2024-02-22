package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ApplicationStsTest;
import com.ppvfra.entity.CompanyRegistration;
import com.ppvfra.repository.ApplicationStsTestRepository;

@Service
@Transactional
public class ApplicationStsTestService {
	@Autowired
	ApplicationStsTestRepository applicationststestrepository;
	
	public List<ApplicationStsTest> listall(){
		return applicationststestrepository.findAll();
	}
	
/*	public Boolean save (ApplicationStsTest applicationststest)
	{   
	try 
	{	applicationststestrepository.save(applicationststest);
			return true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return false;
	}
	}
	*/
	public ApplicationStsTest get(Integer id) {
		return applicationststestrepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		applicationststestrepository.deleteById(id);
	}
	
	public  ApplicationStsTest save(ApplicationStsTest applicationststest) {
		return applicationststestrepository.saveAndFlush(applicationststest);
	}
}
