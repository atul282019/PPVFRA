package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Crops;
import com.ppvfra.entity.DusCharacteristics;

public interface DusCharacteristicsRepository extends  JpaRepository<DusCharacteristics,Integer> {

   @Query("select v.id,v.characteristics,v.state,v.note,v.example_evaluation,\r\n" + 
	  		"v.stages_observation,v.type_assessment,v.cropid,v.is_groupcharacteristics,v.is_required,v.explanation from Dus_Characterstic_View v")
		List<Object[]> get_dus_data();
	
		
	@Query("select v.id,v.cropid,v.characteristics,v.state,v.note,v.example_evaluation,"
			+ "v.stages_observation,v.type_assessment,v.is_groupcharacteristics,v.is_required,v.explanation from Dus_Characterstic_View v where v.cropid=:cropid")
	public List<DusCharacteristics> getAllDusCharacteristics(@Param("cropid")Integer cropid );
	
	@Query("select v.id,v.cropid,v.characteristics,v.characteristics_group,mdg.group_name,v.serial_number from DusCharacteristics v\r\n" + 
			"left join DusCharacteristicGroup mdg\r\n" + 
			"on cast(v.characteristics_group as int) = mdg.id\r\n" + 
			"where v.cropid=:cropid order by v.serial_number")
	public List<Object[]> getAllDusCharacteristicsByApplicationCropid(@Param("cropid")Integer cropid );
	
	@Query("SELECT adv.id,adv.dustqgroupid,adf.dustvarietyid,adf.duscharacteristicsstatesid,\r\n" + 
			"dtq.dustqserialnumber,dtq.dustqgroup,dtc.characteristics,tpl.code,adv.denomination,dts.states\r\n" + 
			"FROM  ApplicationDusVariety adv\r\n" + 
			"left join ApplicationDusFeatures adf\r\n" + 
			"on adv.id = adf.dustvarietyid\r\n" + 
			"left join DusTqGroup dtq on adv.dustqgroupid = dtq.id\r\n" + 
			"left join DusCharacteristicsStates dts on dts.id = adf.duscharacteristicsstatesid\r\n" + 
			"left join TypeLine tpl on cast (adv.typeline as int) = tpl.id\r\n" + 
			"left join DusCharacteristics dtc on adf.duscharacteristicsid = dtc.id\r\n" + 
			"where adv.applicationid = :id and adv.dustqgroupid=:tqgroupid")
	List<Object[]> getAllCharacteristicsByApplicationId(@Param("id")Integer id, @Param("tqgroupid") Integer tqgroupid);
	
	@Query(value="select * from characteristicsstatadata_test where applicationid=:id and groupid=:groupid", nativeQuery = true)
	List<Object[]> getPivotCharacteristics(@Param("id")Integer id,@Param("groupid")Integer groupid);
	
	
	
}
