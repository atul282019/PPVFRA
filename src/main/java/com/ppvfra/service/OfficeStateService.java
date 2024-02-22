package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ppvfra.entity.OfficeStates;
import com.ppvfra.repository.OfficeStateRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OfficeStateService {
	@Autowired
	OfficeStateRepository officestaterepository;

	public List<OfficeStates> listall(){
		return officestaterepository.findAll();
	}
	public void save (OfficeStates officestates){
		try {
			officestaterepository.save(officestates);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public OfficeStates get(Long id) {
		return officestaterepository.findById(id).get();
	}
	public void delete(Long id) {
		try {
			officestaterepository.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
