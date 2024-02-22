package com.ppvfra.service;
import com.ppvfra.repository.AccessTypesRepository;
 

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.AccessTypes;
 

@Service
@Transactional
public class AccessTypeService 
{
	@Autowired
	 AccessTypesRepository accesstypeservice;
     public List<AccessTypes> listall()
     {
		 return accesstypeservice.findAll(); 
	 }
	  

}