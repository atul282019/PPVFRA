package com.ppvfra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 
import com.ppvfra.repository.UserAddRepository;
 
import com.ppvfra.entity.UserAdd;

@Service
@Transactional
public class UserAddService{
	
	@Autowired
	UserAddRepository addusrrep;
	
	public Boolean save (UserAdd adduser)
	{
		try {
		
			addusrrep.save(adduser);
			return true;
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
			return false;
		}
	}
	 public UserAdd get(int id) {
		return addusrrep.findById(id).get();
	}
	public void delete(int id) {
		addusrrep.deleteById(id);
	}
	
}