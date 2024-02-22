package com.ppvfra.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ppvfra.entity.Fees;
import com.ppvfra.repository.FeesRepository;

public class FeesService {
	@Autowired
	FeesRepository feesrepository;
	public Optional<Fees> getByd(Integer id)
	{
		return feesrepository.findById(id);
	}

}
