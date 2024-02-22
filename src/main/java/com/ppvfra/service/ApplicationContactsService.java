package com.ppvfra.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.ApplicationContacts;
import com.ppvfra.entity.Applications;
import com.ppvfra.repository.ApplicationContactsRepository;

@Service
@Transactional
public class ApplicationContactsService {
	
	@Autowired
	ApplicationContactsRepository applicationcontactrepository;
	
	public Boolean save(ApplicationContacts applicationcontacts)
	{
		  try {
			applicationcontactrepository.save(applicationcontacts);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public  List<ApplicationContacts> listall()
	{
		return applicationcontactrepository.findAll();
	}
	
	public ApplicationContacts getById(int id)
	{
		return applicationcontactrepository.findById(id).get();
	}
}
