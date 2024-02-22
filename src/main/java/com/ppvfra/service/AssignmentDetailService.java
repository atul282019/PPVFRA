package com.ppvfra.service;

import com.ppvfra.repository.AssingmentDetailsRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ppvfra.entity.Assignment_Details;


@Service
@Transactional
public class AssignmentDetailService 
{
	@Autowired
	AssingmentDetailsRepository assignmentdetailsrepository;
     public List<Assignment_Details> listall()
     {
		 return assignmentdetailsrepository.findAll(); 
	 }
	  
     public Boolean save (Assignment_Details assignmentdetails){
 		try
 		{
 			assignmentdetailsrepository.save(assignmentdetails);
 	        return true;
 		}
 		catch(Exception e)
 		{ e.printStackTrace();
 			return false;
 		}
 	}
 	

}