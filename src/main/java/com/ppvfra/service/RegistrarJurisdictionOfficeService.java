package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.RegistrarJurisdictionCrops;
import com.ppvfra.entity.RegistrarJurisdictionOffice;
import com.ppvfra.repository.RegistrarJurisdictionOfficeRepository;
import com.ppvfra.repository.RegistrarJurisdictionCropsRepository;


@Service
@Transactional
public class RegistrarJurisdictionOfficeService {

	@Autowired
	RegistrarJurisdictionOfficeRepository registrarjurisdictionofficerepository;

    public List<RegistrarJurisdictionOffice> listall()
    {
	  return registrarjurisdictionofficerepository.findAll();
    }
    
    public void save (RegistrarJurisdictionOffice registrarjurisdiction)
    { 
    	try {
			registrarjurisdictionofficerepository.save(registrarjurisdiction);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		
    }
    

    public RegistrarJurisdictionOffice get(Integer id)
    {
      return registrarjurisdictionofficerepository.findById(id).get();
    }
    
    public void delete(Integer id) 
    {
    	registrarjurisdictionofficerepository.deleteById(id);
    }

}
