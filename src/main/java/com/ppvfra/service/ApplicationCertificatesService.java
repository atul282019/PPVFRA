package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.Application;
import com.ppvfra.entity.ApplicationCertificates;
import com.ppvfra.repository.ApplicationCertificatesRepository;


@Service
@Transactional
public class ApplicationCertificatesService {

	@Autowired
	ApplicationCertificatesRepository applicationcertificaterepository;
	
	public List<ApplicationCertificates> listall()
	{
		return applicationcertificaterepository.findAll();
	}
	
	public ApplicationCertificates save(ApplicationCertificates applicationservice)
	{
			return applicationcertificaterepository.save(applicationservice);
		
	}
	
	public void saveApplicationCertificate(ApplicationCertificates applicationrepository) {
		applicationcertificaterepository.saveAndFlush(applicationrepository);
	}
	
	
	
	
}
