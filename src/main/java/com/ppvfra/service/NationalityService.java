package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.Nationality;
import com.ppvfra.repository.NationalityRepository;

@Service
@Transactional
public class NationalityService {

	@Autowired
	NationalityRepository nationalityrepository;
	public List<Nationality> listall()
	{
		return nationalityrepository.findAll();
	}
}
