package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.DusCharacteristics;
import com.ppvfra.repository.DusCharacteristicsRepository;
@Service
@Transactional
public class DusCharacteristicsService {

	@Autowired
	DusCharacteristicsRepository duscharacteristicsrepository;

	public List<DusCharacteristics> listall(){
		return duscharacteristicsrepository.findAll();
	}
	public Boolean save (DusCharacteristics addduscharacteristics){
		try{
			duscharacteristicsrepository.save(addduscharacteristics);
		return true;
		}
		catch(Exception e)
		{ e.printStackTrace();
			return false;
		}
				
	}
	public DusCharacteristics get(Integer id) {
		return duscharacteristicsrepository.findById(id).get();
	}
	public void delete(Integer id) {
		duscharacteristicsrepository.deleteById(id);
	}
}
