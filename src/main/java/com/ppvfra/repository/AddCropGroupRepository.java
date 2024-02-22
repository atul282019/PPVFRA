package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.CropGroup;


import java.util.Optional;

public interface AddCropGroupRepository extends JpaRepository<CropGroup, Long> {


	  @Query("Select a from CropGroup a where crop_group=:crop_group")
	  List<CropGroup> crop_groupcheck(@Param("crop_group") String crop_group);

	
	  
	 
	 @Query("select a from CropGroup a where a.status='active' order by a.crop_group") 
	 List<CropGroup> cropgroup_activedata();
	 
	 
}


