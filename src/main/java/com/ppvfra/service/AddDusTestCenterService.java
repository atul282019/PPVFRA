package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.DusTestCenter;
import com.ppvfra.repository.AddDusTestCenterRepository;

@Service
@Transactional
public class AddDusTestCenterService {
	@Autowired
	AddDusTestCenterRepository adddustestcenterrep;
	
	public List<DusTestCenter> listall(){
		return adddustestcenterrep.findAll();
	}
	public Boolean save (DusTestCenter adddustestcenter){
		try
		{
			adddustestcenterrep.save(adddustestcenter);
	        return true;
		}
		catch(Exception e)
		{ e.printStackTrace();
			return false;
		}
	}
	public DusTestCenter get(Long id) {
		return adddustestcenterrep.findById(id).get();
	}
	public void delete(Long id) {
		adddustestcenterrep.deleteById(id);
	}
	
}
