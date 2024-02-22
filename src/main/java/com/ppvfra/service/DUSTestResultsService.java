package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.DUSTestResults;
import com.ppvfra.repository.DUSTestResultsRepository;

@Service
@Transactional
public class DUSTestResultsService {
	
	@Autowired
	DUSTestResultsRepository dustestresultsrepository;
	
	public List<DUSTestResults> listall(){
		return dustestresultsrepository.findAll();
	}
	
	public Boolean save (DUSTestResults dustestresults)
	{   
		try 
		{
			dustestresultsrepository.save(dustestresults);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public DUSTestResults get(Integer id) {
		return dustestresultsrepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		dustestresultsrepository.deleteById(id);
	}

}
