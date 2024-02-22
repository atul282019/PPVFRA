package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.CandidateVariety;
import com.ppvfra.repository.CandidateVarietyRepository;

@Service
@Transactional
public class CandidateVarietyService {
	
	@Autowired
	CandidateVarietyRepository candidatevarietyrepository;
	
	public List<CandidateVariety> listall()
	{
		return candidatevarietyrepository.findAll();
	}

	public void save(CandidateVariety candidatevariety)
	{
		try {
			candidatevarietyrepository.save(candidatevariety);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CandidateVariety get(Integer id)
	{
		return candidatevarietyrepository.findById(id).get();
	}
	
	public void delete(Integer id)
	{
		candidatevarietyrepository.deleteById(id);
	}
}
