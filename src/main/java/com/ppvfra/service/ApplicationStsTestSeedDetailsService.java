package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ApplicationStsTestSeedDetails;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ApplicationStsTestSeedDetailsRepository;

@Service
@Transactional
public class ApplicationStsTestSeedDetailsService {
	@Autowired
	ApplicationStsTestSeedDetailsRepository applicationststestseeddetailsrepository;
	
	public List<ApplicationStsTestSeedDetails> listall(){
		return applicationststestseeddetailsrepository.findAll();
	}
	
	public Boolean save (ApplicationStsTestSeedDetails applicationststestseeddetails)
	{   
		 
		try 
		{
			applicationststestseeddetailsrepository.save(applicationststestseeddetails);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public ApplicationStsTestSeedDetails get(Integer id) {
		return applicationststestseeddetailsrepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		applicationststestseeddetailsrepository.deleteById(id);
	}

}