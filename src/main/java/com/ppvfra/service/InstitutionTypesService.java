package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.InstitutionTypes;
import com.ppvfra.repository.InstitutionTypeRepository;
@Service
@Transactional
public class InstitutionTypesService {

	@Autowired
	InstitutionTypeRepository instyprep;
	
	public List<InstitutionTypes> listall()
	{
		return instyprep.findAll();
	}
}
