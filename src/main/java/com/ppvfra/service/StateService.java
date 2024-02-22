package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.States;
import com.ppvfra.repository.StateRepository;

@Service
@Transactional
public class StateService {
	
	@Autowired
	StateRepository staterep;
	
	public List<States> listall()
	{
		return staterep.findAll();
	}
}