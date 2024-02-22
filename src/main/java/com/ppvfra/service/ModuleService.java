package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.Districts;
import com.ppvfra.entity.Modules;
import com.ppvfra.repository.ModulesRepository;

@Service
@Transactional
public class ModuleService {
	
	@Autowired
	ModulesRepository modulesrepository;
	
	public List<Modules> listall()
	{
		return modulesrepository.findAll(new Sort(Sort.Direction.ASC, "orderofappearance"));
	}

}
