package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ApplicantRole;
import com.ppvfra.repository.ApplicantRoleRepository;
@Service
@Transactional
public class ApplicantRoleService {
	 
	@Autowired
	ApplicantRoleRepository applicantrolerepository;
	
	
	public List<ApplicantRole> listall(){
		return applicantrolerepository.findAll();
	}

}
