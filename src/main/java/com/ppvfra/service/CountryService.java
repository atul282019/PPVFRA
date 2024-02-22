package com.ppvfra.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ppvfra.entity.Country;
import com.ppvfra.repository.CountryRepository;

@Service
@Transactional

public class CountryService {

	
	@Autowired
	CountryRepository countryrep;
	
	public List<Country> listall()
	{
		return countryrep.findAll();
	}	
	
}
