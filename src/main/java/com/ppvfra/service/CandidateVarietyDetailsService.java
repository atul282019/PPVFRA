package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.CandidateVarietyDetails;
import com.ppvfra.repository.CandidateVarietyDetailsRepository;

@Service
@Transactional
public class CandidateVarietyDetailsService {
	
	@Autowired
	CandidateVarietyDetailsRepository candidatevarietydetailsrepository;
	
	public List<CandidateVarietyDetails> listall()
	{
		return candidatevarietydetailsrepository.findAll();
	}

	public void save(CandidateVarietyDetails candidatevarietydetails)
	{
		try {
			candidatevarietydetailsrepository.save(candidatevarietydetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CandidateVarietyDetails get(Integer id)
	{
		return candidatevarietydetailsrepository.findById(id).get();
	}
	
	public void delete(Integer id)
	{
		candidatevarietydetailsrepository.deleteById(id);
	}
}
