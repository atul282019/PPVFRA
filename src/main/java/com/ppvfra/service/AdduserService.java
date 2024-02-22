package com.ppvfra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.repository.AdduserRepository;
 
import com.ppvfra.entity.InternalUser;

@Service
@Transactional
public class AdduserService{
	
	@Autowired
	AdduserRepository addusrrep;
	
	public void save (InternalUser adduser)
	{
		addusrrep.save(adduser);
	}
	
	 public InternalUser get(int id) {
		return addusrrep.findById(id).get();
	}
	public void delete(int id) {
		addusrrep.deleteById(id);
	}
	
}