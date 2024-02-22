package com.ppvfra.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.ppvfra.entity.Applications;
import com.ppvfra.entity.CropGroup;
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.ReportTen;
import com.ppvfra.repository.AddCropsRepository;
import com.ppvfra.repository.ApplicationsRepository;
/*
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;*/

@Service
@Transactional
public class ApplicationsService {
	
	@Autowired
	ApplicationsRepository applicationsrepository;
	@Autowired
	AddCropsRepository cropsrepository;
	@Autowired
	Environment env;
	
	
	public Boolean save(Applications application) {
		 try {
			applicationsrepository.save(application);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Applications> listall()
	{
		return applicationsrepository.findAll();
	}	
	public Applications getById(int id)
	{
		return applicationsrepository.findById(id).get();
	}

}
