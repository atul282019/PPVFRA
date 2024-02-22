package com.ppvfra.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ppvfra.entity.CropSpecies;
import com.ppvfra.repository.CropSpeciesRepository;

@Service
@Transactional


public class CropSpeciesService {
	@Autowired
	CropSpeciesRepository cropspeciesrep;

	public List<CropSpecies> listall(){
		return cropspeciesrep.findAll();
	}
	public void save (CropSpecies addcropspecies){
		try {
			cropspeciesrep.save(addcropspecies);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public CropSpecies get(Integer id) {
		return cropspeciesrep.findById(id).get();
	}
	public void delete(Integer id) {
		cropspeciesrep.deleteById(id);
	}
}
