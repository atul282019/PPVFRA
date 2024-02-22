package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Crops;
import com.ppvfra.entity.Districts;

public interface AddCropsRepository extends JpaRepository<Crops, Long>{

	  @Query("Select a from Crops a where crop_name=:crop_name")
	  List<Crops> crop_namecheck(@Param("crop_name") String crop_name);

	  
	  @Query("SELECT c.id, c.crop_common_name FROM Crops c where c.cropgroupid=:cropgroupid")
	  public List<Crops> getAllCropsByCropGroup(@Param("cropgroupid") Integer cropgroupid);
	  
	  @Query("SELECT c FROM Crops c order by c.crop_common_name")
	  public List<Crops> getAllCropsByOrderbyCommonName();
	  
	  @Query("SELECT c.id,cg.crop_group,c.crop_common_name,"
	 + "cs.crop_botanical_name from CropGroup cg join Crops c"
	 + " on cg.id=c.cropgroupid  "
	 + "join CropSpecies cs on c.id = cs.cropid and cs.cscount=1 ")
	  List<Object[]> findAllCrops();
	  
	  @Query("SELECT c  FROM Crops c where c.active=true order by c.crop_common_name")
	  public List<Crops> getAllActiveCrops();
	  
	  
	  @Query("SELECT c.id, c.crop_common_name FROM Crops c where c.cropgroupid=:cropgroupid order by crop_common_name")
	  public List<Crops> getCropsByCropGroup(@Param("cropgroupid") Integer cropgroupid);
	  
	  
	  
}
