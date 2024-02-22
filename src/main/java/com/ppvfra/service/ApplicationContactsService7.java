package com.ppvfra.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.ApplicationContacts;
import com.ppvfra.entity.ApplicationContacts7;
import com.ppvfra.entity.Applications;
import com.ppvfra.repository.ApplicationContactsRepository;
import com.ppvfra.repository.ApplicationContactsRepository7;

@Service
@Transactional
public class ApplicationContactsService7 {
	
	@Autowired
	ApplicationContactsRepository7 applicationcontactrepository7;
	
	public Boolean save(ApplicationContacts7 applicationcontacts)
	{
		  try {
			applicationcontactrepository7.save(applicationcontacts);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public  List<ApplicationContacts7> listall()
	{
		return applicationcontactrepository7.findAll();
	}
	
	public ApplicationContacts7 getById(int id)
	{
		return applicationcontactrepository7.findById(id).get();
	}
}
