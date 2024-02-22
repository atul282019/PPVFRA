package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.NationalRegister;
import com.ppvfra.repository.NationalRegisterRepository;

@Service
@Transactional
public class NationalRegisterService {
	
	@Autowired
	NationalRegisterRepository nationalregisterrepository;
	
	public List<NationalRegister> listall(){
		return nationalregisterrepository.findAll();
	}
	
	public Boolean save (NationalRegister nationalregister)
	{   
		try 
		{
			nationalregisterrepository.save(nationalregister);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public NationalRegister get(Integer id) {
		return nationalregisterrepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		nationalregisterrepository.deleteById(id);
	}


}
