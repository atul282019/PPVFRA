package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.MediumTermStorage;
import com.ppvfra.repository.MediumTermStorageRepository;

@Service
@Transactional
public class MediumTermStorageService {
	
	@Autowired
	MediumTermStorageRepository mediumtermstoragerepository;

	public List<MediumTermStorage> listall(){
		return mediumtermstoragerepository.findAll();
	}
	
	public Boolean save (MediumTermStorage mediumtermstorage)
	{   
		try 
		{
			mediumtermstoragerepository.save(mediumtermstorage);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public MediumTermStorage get(Integer id) {
		return mediumtermstoragerepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		mediumtermstoragerepository.deleteById(id);
	}
}
