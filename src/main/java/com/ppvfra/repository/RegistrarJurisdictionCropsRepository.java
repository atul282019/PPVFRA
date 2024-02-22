package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.RegistrarJurisdictionCrops;

public interface RegistrarJurisdictionCropsRepository extends JpaRepository<RegistrarJurisdictionCrops, Integer>{

	
	  @Query("select rg.crops from RegistrarJurisdictionCrops rg where rg.userid=:userid and rg.id=:id")
	  List<RegistrarJurisdictionCrops> searchfor_crops(@Param("userid") Integer userid,@Param("id") Integer id);
	  
	 /* 
	  @Query("select rg.office from RegistrarJurisdiction rg where rg.userid=:userid and rg.id=:id") 
	  List<RegistrarJurisdictionCrops> searchfor_office(@Param("userid") Integer userid,@Param("id") Integer id);
	 */

     @Query("select rjc.id,u.username,r.name,c.crop_common_name,u.id,r.id \r\n" + 
     		"from InternalUser u join RegistrarJurisdictionCrops rjc on u.id=rjc.userid\r\n" + 
     		"join Role r on rjc.roleid=r.id\r\n" + 
     		"join Crops c on rjc.crops=c.id where userid=:userid")
     List<RegistrarJurisdictionCrops> getAll_Details(@Param("userid") Integer userid);

     @Query(" select rg.crops from RegistrarJurisdictionCrops rg where rg.userid=:userid ")
     List<RegistrarJurisdictionCrops> getcropdetails(@Param("userid") Integer userid);


     @Transactional
     @Modifying
     @Query("delete from RegistrarJurisdictionCrops rjc where rjc.userid=:userid")
     void removeCropDetails(@Param("userid") Integer userid);
     
	
	/*
	 * @Query(
	 * value="select  distinct ro.userid,string_agg(c.crop_common_name,',') as cname from registrarjurisdiction_crops ro ,m_crops c\r\n"
	 * + "where ro.crops = c.id  group by ro.userid", nativeQuery = true)
	 * List<Object[]> findAllCrops();
	 */
     
     
     @Query(value="with temp1 as (select  distinct ro.userid,string_agg(c.crop_common_name,',') as\r\n" + 
     		"  cname from registrarjurisdiction_crops ro\r\n" + 
     		"  left join m_crops c on  ro.crops = c.id\r\n" + 
     		" group by ro.userid),\r\n" + 
     		"temp2 as (\r\n" + 
     		"select distinct rjo.userid, string_agg( mo.location,',') as office  \r\n" + 
     		"from registrarjurisdiction_office rjo\r\n" + 
     		"left join m_office mo on rjo.office = mo.id group by rjo.userid\r\n" + 
     		"),\r\n" + 
     		"temp3 as ( select coalesce(tt.userid,tt2.userid) userid,tt.cname,tt2.office from temp1 tt\r\n" + 
     		"full outer join temp2 tt2 on tt.userid = tt2.userid)\r\n" + 
     		"select ttt.userid,ttt.cname,ttt.office ,coalesce(username,firstname)\r\n" + 
     		"from  temp3 ttt inner join users u on ttt.userid= u.id\r\n" + 
     		"", nativeQuery = true)
     List<Object[]> findAllCrops();
	 
} 