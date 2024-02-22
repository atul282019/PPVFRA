package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.EditCompanyRegistration;
import com.ppvfra.repository.EditCompanyRegistrationRepository;

@Service
@Transactional
public class EditCompanyRegistrationService {
	
	@Autowired
	EditCompanyRegistrationRepository editcompanyregistrationrepository;
	
	public List<EditCompanyRegistration> listall(){
		return editcompanyregistrationrepository.findAll();
	}
	public void save (EditCompanyRegistration companyregistration){
		try {
			editcompanyregistrationrepository.save(companyregistration);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public EditCompanyRegistration get(Long id) {
		return editcompanyregistrationrepository.findById(id).get();
	}
	public void delete(Long id) {
		editcompanyregistrationrepository.deleteById(id);
	}
	public  EditCompanyRegistration saveAndFlush(EditCompanyRegistration companyregistration) {
		return editcompanyregistrationrepository.saveAndFlush(companyregistration);
	}
}
