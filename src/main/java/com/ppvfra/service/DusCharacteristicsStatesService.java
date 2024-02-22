package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.DusCharacteristicsStates;
import com.ppvfra.repository.DusCharacteristicsStatesRepository;
@Service
@Transactional
public class DusCharacteristicsStatesService {
	@Autowired
	DusCharacteristicsStatesRepository duscharacteristicsstatesrepository;

	public List<DusCharacteristicsStates> listall(){
		return duscharacteristicsstatesrepository.findAll();
	}
	public void save (DusCharacteristicsStates addduscharacteristics){
		try {
			duscharacteristicsstatesrepository.save(addduscharacteristics);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DusCharacteristicsStates get(Integer id) {
		return duscharacteristicsstatesrepository.findById(id).get();
	}
	public void delete(Integer id) {
		duscharacteristicsstatesrepository.deleteById(id);
	}
}
