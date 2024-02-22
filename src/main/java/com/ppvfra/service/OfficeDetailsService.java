package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.OfficeDetails;
import com.ppvfra.repository.OfficeDetailsRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OfficeDetailsService {
	
@Autowired
OfficeDetailsRepository officedetailrepository;
public Boolean save(OfficeDetails officedetails)
{ try {
	officedetailrepository.save(officedetails);	
    return true;
}

catch(Exception e)
{ e.printStackTrace();
return false;
}
}
public List<OfficeDetails> listall(){
	return officedetailrepository.findAll();
	
}
public OfficeDetails get(Long id) {
	return officedetailrepository.findById(id).get();
}
public void delete(Long id) {
	officedetailrepository.deleteById(id);
}
}
