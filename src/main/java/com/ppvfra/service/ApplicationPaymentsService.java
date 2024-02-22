package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ApplicationPayments;
import com.ppvfra.entity.Applications;
import com.ppvfra.repository.ApplicationPaymentsRepository;


@Service
@Transactional
public class ApplicationPaymentsService {

	@Autowired
	ApplicationPaymentsRepository applicationpaymentsreppository;
	
	public Boolean save(ApplicationPayments application) {
		 try {
			 applicationpaymentsreppository.save(application);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public ApplicationPayments getById(int id)
	{
		return applicationpaymentsreppository.findById(id).get();
	}

}
