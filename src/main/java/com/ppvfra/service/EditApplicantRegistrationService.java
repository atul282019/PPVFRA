package com.ppvfra.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.EditApplicantRegistration;

import com.ppvfra.repository.EditApplicantRegistrationRepository;

@Service
@Transactional
public class EditApplicantRegistrationService {

	@Autowired
	EditApplicantRegistrationRepository editapplicantregistrationrepository;
	
	 public List<EditApplicantRegistration> listAll(){ return
			 editapplicantregistrationrepository.findAll(); }
	
	
	public @Valid EditApplicantRegistration save(EditApplicantRegistration applicantregistration) { 
		return editapplicantregistrationrepository.save(applicantregistration);
		}
	
	public EditApplicantRegistration get(Integer id) {
		return editapplicantregistrationrepository.findById(id).get();
	}
	public void delete(Integer id) {
		editapplicantregistrationrepository.deleteById(id);
	}
	
	public  EditApplicantRegistration saveAndFlush(EditApplicantRegistration applicantregistration) {
		return editapplicantregistrationrepository.saveAndFlush(applicantregistration);
	}
}
