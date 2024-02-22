package com.ppvfra.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.MediumTermStorage;

public interface MediumTermStorageRepository extends JpaRepository<MediumTermStorage,Integer>{

	@Modifying
    @Transactional
	@Query("update MediumTermStorage m set m.postregistration=:postregistration,m.handedoverto=:handedoverto,m.no_of_packets=:no_of_packets,m.handingoverdate =:date1 ,m.handingover_remarks =:handingover_remarks where m.applicationid=:applicationid")
	public int updateMediumTermStorage(@Param("postregistration") String postregistration,@Param("handedoverto") String handedoverto,@Param("no_of_packets") int no_of_packets, @Param("date1") Date date1,@Param("handingover_remarks") String handingover_remarks, @Param("applicationid") long applicationid);


	@Query("select md.id,md.applicationid,md.moisture_tobe_checked_on,t.code,md.viability,md.moisture,md.weight,md.seeds_discarded,md.date_of_discard,md.reasons,md.checked_by\r\n" + 
			"from MediumTermStorage md join TypeLine t on md.type_line=t.id where md.applicationid=:id")
	    List<Object[]> getMediumTermStorageDetails(@Param("id") Long id);
	    
	    @Query("select am.id,am.applicationid,am.moisture_tobe_checked_on,\r\n" + 
	    		"am.viability,am.moisture,am.weight,am.seeds_discarded,am.date_of_discard,\r\n" + 
	    		"am.reasons,am.checked_by,am.postregistration,am.handedoverto,am.no_of_packets\r\n" + 
	    		",am.handingoverdate,am.type_line \r\n" + 
	    		"from MediumTermStorage am where applicationid =:applicationid")
	    List<MediumTermStorage> getmtsdata(@Param("applicationid") Long applicationid);

}
