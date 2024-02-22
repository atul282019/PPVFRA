package com.ppvfra.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.ApplicationParentalline;
import com.ppvfra.entity.ApplicationParentallineOthers;
import com.ppvfra.repository.ApplicationParentallineOthersRepository;

@Service
@Transactional
public class ApplicationParentallineOthersService {
	
	@Autowired
	ApplicationParentallineOthersRepository applicationparentallineotherrepository;
	
	public Boolean save(ApplicationParentallineOthers applicationparentallineother) {
		 try {
			applicationparentallineotherrepository.save(applicationparentallineother);
			return true;
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		}
	
	  public ApplicationParentallineOthers getById(int id) { 
		  return applicationparentallineotherrepository.findById(id).get();
		  }
	 
	
}
