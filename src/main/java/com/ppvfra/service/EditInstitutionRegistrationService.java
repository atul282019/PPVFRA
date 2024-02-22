package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.EditInstitutionRegistration;

import com.ppvfra.repository.EditInstitutionRegistrationRepository;
@Service
@Transactional
public class EditInstitutionRegistrationService {
	
	@Autowired
	EditInstitutionRegistrationRepository editinstitutionregistrationrepository;
	
	public List<EditInstitutionRegistration> listall(){
		return editinstitutionregistrationrepository.findAll();
	}
	public EditInstitutionRegistration save (EditInstitutionRegistration institutionregistration){
		return editinstitutionregistrationrepository.save(institutionregistration);
	}
	
	
	public EditInstitutionRegistration get(Integer id) {
		return editinstitutionregistrationrepository.findById(id).get();
	}
	public void delete(Integer id) {
		editinstitutionregistrationrepository.deleteById(id);
	}

}
