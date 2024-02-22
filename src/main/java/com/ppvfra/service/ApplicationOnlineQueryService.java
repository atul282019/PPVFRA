package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ApplicationOnlineQuery;
 
import com.ppvfra.repository.ApplicationOnlineQueryRepository;
 

@Service
@Transactional
public class ApplicationOnlineQueryService {
	
	@Autowired
	ApplicationOnlineQueryRepository apponlinerep;
	
	public List<ApplicationOnlineQuery> listall(){
		return apponlinerep.findAll();
	}
	
	public Boolean save (ApplicationOnlineQuery sendquery)
	{   
		try 
		{
			apponlinerep.save(sendquery);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public ApplicationOnlineQuery get(Integer id) {
		return apponlinerep.findById(id).get();
	}
	
	public void delete(Integer id) {
		apponlinerep.deleteById(id);
	}

}
