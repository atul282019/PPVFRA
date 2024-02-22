package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.Remarks;
import com.ppvfra.repository.RemarksRepository;

@Service
@Transactional
public class RemarksService {
	@Autowired
	RemarksRepository remarksrepository;

	public List<Remarks> listall(){
		return remarksrepository.findAll();
	}
	
	public Boolean save (Remarks addremarks)
	{   
		try 
		{
			remarksrepository.save(addremarks);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public Remarks get(Integer id) {
		return remarksrepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		remarksrepository.deleteById(id);
	}
}
