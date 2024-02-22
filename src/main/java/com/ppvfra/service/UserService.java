package com.ppvfra.service;
import com.ppvfra.repository.UserServiceRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.Role;

@Service
@Transactional
public class UserService 
{
	 @Autowired
	 UserServiceRepository userrepository;
	 
	  public List<Role> listall() {
		  return userrepository.findAll(); 
		  }
	  
	 public void save (Role userrole) {
		 try {
			userrepository.save(userrole);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	 
}
