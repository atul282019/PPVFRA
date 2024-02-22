package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.TransferSeedToMTS;
import com.ppvfra.repository.TransferSeedToMTSRepository;

@Service
@Transactional
public class TransferSeedToMTSService {
	
	@Autowired
	TransferSeedToMTSRepository transferseedtomtsrepository;
	
	public List<TransferSeedToMTS> listall(){
		return transferseedtomtsrepository.findAll();
	}
	
	public Boolean save (TransferSeedToMTS transferseedtomts)
	{   
		try 
		{
			transferseedtomtsrepository.save(transferseedtomts);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public TransferSeedToMTS get(Integer id) {
		return transferseedtomtsrepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		transferseedtomtsrepository.deleteById(id);
	}
	
	

}
