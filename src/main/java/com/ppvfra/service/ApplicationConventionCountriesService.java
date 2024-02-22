package com.ppvfra.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.ApplicationContacts;
import com.ppvfra.entity.ApplicationConventionCountries;
import com.ppvfra.repository.ApplicationConventionCountriesRepository;


@Service
@Transactional
public class ApplicationConventionCountriesService {

	@Autowired
	ApplicationConventionCountriesRepository applicationconventioncontriesrepository;
	public Boolean save(ApplicationConventionCountries applicationconvention)
	{
		  try {
			applicationconventioncontriesrepository.save(applicationconvention);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public  List<ApplicationConventionCountries> listall()
	{
		return applicationconventioncontriesrepository.findAll();
	}
	
	public ApplicationConventionCountries getById(int id)
	{
		return applicationconventioncontriesrepository.findById(id).get();
	}
}
