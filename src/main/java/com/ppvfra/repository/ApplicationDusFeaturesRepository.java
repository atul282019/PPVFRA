package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationDusFeatures;

public interface ApplicationDusFeaturesRepository extends JpaRepository<ApplicationDusFeatures, Integer>{
	
	@Modifying
	@Transactional
	@Query("delete from ApplicationDusFeatures where dustvarietyid=:id")
	public void deleteApplicationDusFeaturesByVarietyId(@Param("id")Integer id );
	

	@Query(value="select adf.applicationid,adf.id,adf.dustvarietyid,adf.duscharacteristicsid,adf.duscharacteristicsstatesid from application_dusfeatures adf\r\n" + 
			"where applicationid=:applicationid and dustqgroupid =:dustqgroupid and dustvarietyid =:dusvarietyid ", nativeQuery = true)
	List<Object[]> getCharacteristicsEditData(@Param("applicationid")Integer applicationid,@Param("dustqgroupid")Integer dustqgroupid,@Param("dusvarietyid")Integer dusvarietyid);
	
	
}
