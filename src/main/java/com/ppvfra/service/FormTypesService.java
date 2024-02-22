package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.FormTypes;
import com.ppvfra.repository.FormTypesRepository;
@Service
@Transactional
public class FormTypesService {
	@Autowired
	FormTypesRepository formtypesrepository;

	public List<FormTypes> listall(){
		return formtypesrepository.findAll();
	}
	public void save (FormTypes formtypes){
		try {
			formtypesrepository.save(formtypes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FormTypes get(Integer id) {
		return formtypesrepository.findById(id).get();
	}
	public void delete(Integer id) {
		formtypesrepository.deleteById(id);
	}
}
