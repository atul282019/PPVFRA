package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.PublishToPVJ;
import com.ppvfra.repository.PublishToPVJRepository;

@Service
@Transactional
public class PublishToPVJService {

	@Autowired
	PublishToPVJRepository publishtopvjrepository;
	
	public List<PublishToPVJ> listall(){
		return publishtopvjrepository.findAll();
	}
	
	public Boolean save (PublishToPVJ publishtopvj)
	{   
		try 
		{
			publishtopvjrepository.save(publishtopvj);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public PublishToPVJ get(Integer id) {
		return publishtopvjrepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		publishtopvjrepository.deleteById(id);
	}
}
