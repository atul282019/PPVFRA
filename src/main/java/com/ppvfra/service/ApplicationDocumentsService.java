package com.ppvfra.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.ApplicationDocuments;
import com.ppvfra.entity.ApplicationPayments;
import com.ppvfra.repository.ApplicationDocumentsRepository;


@Service
@Transactional
public class ApplicationDocumentsService {
	
	@Autowired
	ApplicationDocumentsRepository applicantiondocumentrepository;
	
	public List<ApplicationDocuments> listall(){
		return applicantiondocumentrepository.findAll();
	}
	
	public Boolean save(ApplicationDocuments applicationdocument)
	{
		try {
		applicantiondocumentrepository.save(applicationdocument);
		return true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}
}
