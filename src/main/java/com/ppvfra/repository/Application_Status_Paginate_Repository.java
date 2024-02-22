package com.ppvfra.repository;

 

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 

import com.ppvfra.entity.Application_Status_Paginate;
 
 

public interface Application_Status_Paginate_Repository extends JpaRepository<Application_Status_Paginate, Integer>{

	/*@Query("select pag from Application_Status_Paginate pag")
	      List<Application_Status_Paginate> getapplication_adminview_p(Pageable pageable);
	      */
 }