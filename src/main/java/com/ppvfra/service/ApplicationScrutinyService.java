package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ApplicationScrutiny;
import com.ppvfra.repository.ApplicationScrutinyRepository;

@Service
@Transactional
public class ApplicationScrutinyService {
	
	@Autowired
	ApplicationScrutinyRepository applicationscrutinyrepository;

	public List<ApplicationScrutiny> listall(){
		return applicationscrutinyrepository.findAll();
	}
	
	public Boolean save (ApplicationScrutiny applicationscrutiny)
	{   
		try 
		{
			applicationscrutinyrepository.save(applicationscrutiny);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public ApplicationScrutiny get(Integer id) {
		return applicationscrutinyrepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		applicationscrutinyrepository.deleteById(id);
	}
}
