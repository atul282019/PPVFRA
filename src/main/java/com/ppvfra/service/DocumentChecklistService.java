package com.ppvfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.DocumentChecklist;

import com.ppvfra.repository.DocumentChecklistRepository;

@Service
@Transactional

public class DocumentChecklistService {
	@Autowired 
	   DocumentChecklistRepository documentchecklistrepository;
	   
	   public List<DocumentChecklist> listall()
	   {
		   return documentchecklistrepository.findAll();
	   }
	   
	   public Boolean save(DocumentChecklist documentchecklist)
	   {
		   try {
		   
			   documentchecklistrepository.save(documentchecklist);
			   return true;
	   }
		   catch(Exception e)
			{ e.printStackTrace();
				return false;
			}
		   }
	   
	   public DocumentChecklist get(Integer id)
	   {
		   return documentchecklistrepository.findById(id).get();
	   }
	   
	   public void delete(Integer id)
	   {
		   documentchecklistrepository.deleteById(id);
	   }
}
