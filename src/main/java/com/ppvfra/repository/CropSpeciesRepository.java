package com.ppvfra.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.CropSpecies;
import com.ppvfra.entity.Crops;


public interface CropSpeciesRepository extends  JpaRepository<CropSpecies,Integer>{
 
	
	@Query("select c.id,c.crop_common_name,cs.cropid,cs.crop_botanical_name,cs.Family,cs.initials,cs.id\r\n" + 
			"from Crops c join CropSpecies cs on c.id=cs.cropid and cs.cropid=:cropid and cs.cscount=1")
	List<Object[]> findAllBotanicalName(@Param("cropid") Integer cropid);
	
	
	
	@Transactional
	@Modifying
	@Query("delete from CropSpecies m where m.cropid=:cropid")
	//@Query("update CropSpecies set crop_species_status=2 where cropid=:cropid")
	void removeCropIdDetails(@Param("cropid") Integer cropid);
	
	@Query("select cs from CropSpecies cs order by cs.crop_botanical_name")
	 public List<CropSpecies> getAllbotanicalNameOrderByName();
	
	@Query("select cs.id,cs.crop_botanical_name from CropSpecies cs where cs.cropid=:cropid")
	 public List<CropSpecies> getAllbotanicalNameByCropName(@Param("cropid") Integer cropid);
	
	@Query("select cs.id,cs.Family from CropSpecies cs where cs.crop_botanical_name=:crop_botanical_name")
	 public List<CropSpecies> getAllFamilyNameByCropName(@Param("crop_botanical_name") String crop_botanical_name);
	
	@Query("select cs.id,c.id,c.crop_common_name,cs.cropid,cs.crop_botanical_name,cs.Family,cs.initials\r\n" + 
			"from Crops c join CropSpecies cs on c.id=cs.cropid and cs.cropid=:cropid")
	List<Object[]> findComp_data(@Param("cropid") Integer cropid);
	
	@Query("select cs.id\r\n" + 
			"from Crops c join CropSpecies cs on c.id=cs.cropid and cs.cropid=:cropid")
	List<Object[]> findComp_data_map(@Param("cropid") Integer cropid);
	
	
	@Query("select cs.crop_botanical_name from CropSpecies cs where cs.id=:id")
	 public String getbotanicalNameviaid(@Param("id") Integer id);
	
	@Query("select cs.Family from CropSpecies cs where cs.id=:id")
	 public String getFamilyNameviaid(@Param("id") Integer id);
	
	@Query("select cs.initials from CropSpecies cs where cs.id=:id")
	 public String getInitialsviaid(@Param("id") Integer id);
	
	@Transactional
	@Modifying
	@Query("update CropSpecies set cscount=2 where cropid=:cropid")
	void enhancecropdetails(@Param("cropid") Integer cropid);
	
	@Transactional
	@Modifying
	@Query("update CropSpecies set cscount=1 where id=:id")
	void enhancecropdetailscscount(@Param("id") Integer id);
	
	@Transactional
	@Modifying
	@Query("update CropSpecies set cropid=:cropid where id=:id")
	void enhancecropdetailscropid(@Param("id") Integer id,@Param("cropid") Integer cropid);
	
}
