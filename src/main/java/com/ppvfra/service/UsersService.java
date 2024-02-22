package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.repository.UserRepository;

import com.ppvfra.entity.User;

@Service
@Transactional

public class UsersService {
 
	@Autowired
	UserRepository userrep;


	public void save (User user)
	{
		try {
			userrep.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<User> listall(){
		return userrep.findAll();
	}

	public User get(Integer id) {
		return userrep.findById(id).get();
	}
	public void delete(Integer id) {
		userrep.deleteById(id);
	}


}
