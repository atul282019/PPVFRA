package com.ppvfra.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ppvfra.entity.CropGroup;
import com.ppvfra.repository.AddCropGroupRepository;


@Service
@Transactional
public class AddCropGroupService {

	 @Autowired
	 AddCropGroupRepository addcropgrprep;
	 
	 public List<CropGroup> listall(){
			return addcropgrprep.findAll();
		}
	 
	
		
		public Boolean save (CropGroup addcropgroup)
		{
			try 
			{
				addcropgrprep.save(addcropgroup);
				return true;
		    }
			catch(Exception e)
			{ e.printStackTrace();
				return false;
			}
		}

	public CropGroup get(Long id) {
	return addcropgrprep.findById(id).get();
}
public void delete(Long id) {
	addcropgrprep.deleteById(id);
}
}
