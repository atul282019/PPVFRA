package com.ppvfra.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ppvfra.entity.Crops;
import com.ppvfra.repository.AddCropsRepository;


@Service
@Transactional
public class AddcropService {
	@Autowired
	AddCropsRepository addcroprep;
	
	public List<Crops> listall(){
		return addcroprep.findAll();
	}
	public Boolean save (Crops addcrops){
	    try{
	    	addcroprep.save(addcrops);
	        return true;
	    }
	    catch(Exception e)
		{ e.printStackTrace();
			return false;
		}
	}
	public Crops get(Long id) {
		return addcroprep.findById(id).get();
	}
	public void delete(Long id) {
		addcroprep.deleteById(id);
	}
	
	public void saveAll(List<Crops> cropspec) {
		// TODO Auto-generated method stub
		
	}
}
