package com.ppvfra.service;

import com.ppvfra.entity.CropGroup;
import com.ppvfra.entity.RegistrarJurisdictionCrops;
import com.ppvfra.repository.RegistrarJurisdictionCropsRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional

public class RegistrarJurisdictionCropsService {

			@Autowired
			RegistrarJurisdictionCropsRepository regjudrep;
	
	        public List<RegistrarJurisdictionCrops> listall()
	        {
			  return regjudrep.findAll();
		    }
		    
		    public Boolean save (RegistrarJurisdictionCrops registrarjurisdiction)
		    { 
		    try {
			  regjudrep.save(registrarjurisdiction);
		      return true;
		    }
		    catch(Exception e)
			{ e.printStackTrace();
				return false;
			}
					
		    }
		    

	        public RegistrarJurisdictionCrops get(Integer id)
	        {
	          return regjudrep.findById(id).get();
            }
            
	        public void delete(Integer id) 
            {
	          regjudrep.deleteById(id);
            }
	
	
  }
