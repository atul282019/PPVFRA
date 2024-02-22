package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ObservationCategory;
import com.ppvfra.repository.ObservationCategoryRepository;

@Service
@Transactional
public class ObservationCategoryService {
   
   @Autowired 
   ObservationCategoryRepository observationcategoryrepository;
   
   public List<ObservationCategory> listall()
   {
	   return observationcategoryrepository.findAll();
   }
   
   public Boolean save(ObservationCategory observationcategory)
   {   try {
	   observationcategoryrepository.save(observationcategory);
       return true;
   }
   catch(Exception e)
	{ e.printStackTrace();
		return false;
	}
   }
   
   public ObservationCategory get(Integer id)
   {
	   return observationcategoryrepository.findById(id).get();
   }
   
   public void delete(Integer id)
   {
	   observationcategoryrepository.deleteById(id);
   }
}
	

