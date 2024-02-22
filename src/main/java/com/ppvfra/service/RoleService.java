package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.repository.RoleRepository;
import com.ppvfra.entity.DusTestCenter;
import com.ppvfra.entity.Role;

@Service
@Transactional
public class RoleService{
	
	@Autowired
	RoleRepository rolerepository;
	
	public void save (Role addrole)
	{
		rolerepository.save(addrole);
	}
	
	List<Role> getallfeilds()
	{
		return rolerepository.findAll();
	}
  
	public List<Role> listall()
	{
		return rolerepository.findAll();
	}
	
	public Role get(Long id) {
		return rolerepository.findById(id).get();
	}
	public void delete(Long id) {
		rolerepository.deleteById(id);
	}
}