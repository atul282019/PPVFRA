package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.DusCharacteristicGroup;
import com.ppvfra.repository.DusCharacteristicGroupRepository;

@Service
@Transactional
public class DusCharacteristicGroupService {

	@Autowired
	DusCharacteristicGroupRepository duscharacteristicgrouprepository;
	
	public List<DusCharacteristicGroup> listall()
	{
		return duscharacteristicgrouprepository.findAll();
	}
	
	public Boolean save (DusCharacteristicGroup duscharacteristicgroup){
		try{
			duscharacteristicgrouprepository.save(duscharacteristicgroup);
		return true;
		}
		catch(Exception e)
		{ e.printStackTrace();
			return false;
		}
	}
	public DusCharacteristicGroup get(Integer id) {
		return duscharacteristicgrouprepository.findById(id).get();
	}
	public void delete(Integer id) {
		duscharacteristicgrouprepository.deleteById(id);
	}
}
