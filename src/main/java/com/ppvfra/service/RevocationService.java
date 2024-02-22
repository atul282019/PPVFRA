package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ppvfra.entity.Revocation;
import com.ppvfra.repository.RevocationRepository;

@Service
@Transactional
public class RevocationService {
	
	@Autowired
	RevocationRepository revocationrepository;
	
	public List<Revocation> listall(){
		return revocationrepository.findAll();
	}
	
	public Boolean save (Revocation revocation)
	{   
		try 
		{
			revocationrepository.save(revocation);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public Revocation get(Integer id) {
		return revocationrepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		revocationrepository.deleteById(id);
	}

}
