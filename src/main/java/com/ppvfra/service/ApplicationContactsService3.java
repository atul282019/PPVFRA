package com.ppvfra.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.ApplicationContacts;
import com.ppvfra.entity.ApplicationContacts3;
import com.ppvfra.entity.Applications;
import com.ppvfra.repository.ApplicationContactsRepository;
import com.ppvfra.repository.ApplicationContactsRepository3;

@Service
@Transactional
public class ApplicationContactsService3 {
	
	@Autowired
	ApplicationContactsRepository3 applicationcontactrepository3;
	
	public Boolean save(ApplicationContacts3 applicationcontacts)
	{
		  try {
			  applicationcontactrepository3.save(applicationcontacts);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public  List<ApplicationContacts3> listall()
	{
		return applicationcontactrepository3.findAll();
	}
	
	public ApplicationContacts3 getById(int id)
	{
		return applicationcontactrepository3.findById(id).get();
	}
}
