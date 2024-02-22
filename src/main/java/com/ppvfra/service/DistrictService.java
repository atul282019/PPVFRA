package com.ppvfra.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.Districts;

import com.ppvfra.repository.DistrictRepository;


@Service
@Transactional
public class DistrictService {

	@Autowired
	DistrictRepository districtrep;
	
	public List<Districts> listall()
	{
		return districtrep.findAll();
	}

	
	
}
