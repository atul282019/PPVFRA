package com.ppvfra.service;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
 
import com.ppvfra.entity.User_Role;

import javax.persistence.EntityManager;

@Service
@Transactional
public class RoleAssignService{
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	 private EntityManager entityManager;
	 
	
	public void save(User_Role addrole)
	{
		try {
			roleassignrepository.save(addrole);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<User_Role> getallfeilds()
	{
		return roleassignrepository.findAll();
	}
	
	 
	
}