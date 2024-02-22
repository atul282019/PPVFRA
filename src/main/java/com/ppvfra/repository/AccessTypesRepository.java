package com.ppvfra.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.AccessTypes;
import com.ppvfra.entity.User;
 

public interface AccessTypesRepository extends JpaRepository<AccessTypes, Integer>{

	 @Query("Select a.id,a.accessdesc from AccessTypes a")
		public List<AccessTypes> getAccessDetail();
 }