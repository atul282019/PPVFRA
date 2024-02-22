package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.Rejuvenation;
import com.ppvfra.repository.RejuvenationRepository;

@Service
@Transactional
public class RejuvenationService {
	
	@Autowired
	RejuvenationRepository rejuvenationrepository;
	
	public List<Rejuvenation> listall(){
		return rejuvenationrepository.findAll();
	}
	
	public Boolean save (Rejuvenation rejuvenation)
	{   
		try 
		{
			rejuvenationrepository.save(rejuvenation);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public Rejuvenation get(Integer id) {
		return rejuvenationrepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		rejuvenationrepository.deleteById(id);
	}

}
