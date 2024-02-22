package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.Districts;
import com.ppvfra.entity.TypeLine;
import com.ppvfra.repository.DistrictRepository;
import com.ppvfra.repository.TypeLineRepository;

@Service
@Transactional
public class TypeLineService {

	@Autowired
	TypeLineRepository typelinerepository;
	
	public List<TypeLine> listall()
	{
		return typelinerepository.findAll();
	}
}
