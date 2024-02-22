package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationDusVariety;

public interface ApplicationDusVarietyRepository extends JpaRepository<ApplicationDusVariety, Integer> {

	
	@Query("select adv.id,adv.applicationid,adv.dustqgroupid,adv.typeline,tl.code,adv.denomination\r\n" + 
			" from ApplicationDusVariety adv\r\n" + 
			" inner join TypeLine tl\r\n" + 
			" on cast(adv.typeline as int) = tl.id\r\n" + 
			" where adv.applicationid = :id and adv.dustqgroupid=:tqgroupid order by  typeline asc")
	public List<Object[]> getAllVarityByApplicationId(@Param("id")Integer id,@Param("tqgroupid")Integer tqgroupid );
	
	@Modifying
	@Transactional
	@Query("delete from ApplicationDusVariety where id=:varietyid and applicationid=:id")
	public void deleteApplicationDusFeaturesByApplicationIdAndVarietyId(@Param("id")Integer id,@Param("varietyid")Integer varietyid );
	
	
	
	@Modifying
	@Transactional
	@Query("select MAX(denomination) from ApplicationDusVariety where applicationid=:id and typeline in ('1','2','3','4')\r\n" + 
			"")
	public List<Object[]> getDenomination(@Param("id")Integer id);
	
	/*
	 @Query(value="SELECT adv.id,adv.dustqgroupid,adf.dustvarietyid,adf.duscharacteristicsstatesid,\r\n" + 
			"dtq.dustqserialnumber,dtq.dustqgroup,dtc.characteristics,tpl.code,adv.denomination\r\n" + 
			"FROM  application_dusvariety adv\r\n" + 
			"left join application_dusfeatures adf\r\n" + 
			"on adv.id = adf.dustvarietyid\r\n" + 
			"left join m_dustqgroups dtq on adv.dustqgroupid = dtq.id\r\n" + 
			"left join m_typeline tpl on cast (adv.typeline as int) = tpl.id\r\n" + 
			"left join m_duscharacteristics dtc on adf.duscharacteristicsid = dtc.id\r\n" + 
			"where adv.applicationid=:id",nativeQuery = true)
	public List<Object[]> getall_data_testing(@Param("id")Integer id);
	
	*/
	
	}
