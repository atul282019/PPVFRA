package com.ppvfra.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.repository.ActivityLogRepository;


@Service
@Transactional
public class ActivityLogService {
	
	@Autowired
	ActivityLogRepository activitylogtablerepository;
	

	public List<ActivityLogTable> listall(){
		return activitylogtablerepository.findAll();
	}
	
	public Boolean save (ActivityLogTable activitylogtable)
	{   
		try 
		{
			activitylogtablerepository.save(activitylogtable);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
