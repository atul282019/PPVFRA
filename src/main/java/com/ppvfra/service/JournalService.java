package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.Journal;
import com.ppvfra.repository.JournalRepository;

@Service
@Transactional
public class JournalService {

	@Autowired
	JournalRepository journalrepository;
	
	public List<Journal> listall(){
		return journalrepository.findAll();
	}
	
	public Boolean save (Journal journal)
	{   
		try 
		{
			journalrepository.save(journal);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public Journal get(Integer id) {
		return journalrepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		journalrepository.deleteById(id);
	}
}
