package com.ppvfra.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.DusTestCenter;

public interface AddDusTestCenterRepository extends JpaRepository<DusTestCenter, Long>{
	
	@Query("select dc.id, cg.crop_group,c.crop_common_name,"
			+ "s.statename_inenglish ,d.districtname_inenglish,"
			+ "dc.dus_test_center from CropGroup cg join Crops c "
			+ "on cg.id=c.cropgroupid join DusTestCenter dc on c.id "
			+ "= dc.crop_id left join States s on dc.state_id = "
			+ "s.statecode left join Districts d on dc.district_id = "
			+ "d.districtcode")
	List<Object[]> findAllDusTestCenter();
	
	@Query("select mdt.id, mdt.dus_test_center \r\n" + 
			"from DusTestCenter mdt inner join Applications a on a.cropid=mdt.crop_id and a.id=:id \r\n" + 
			"where mdt.centertype='CNC' and mdt.isactive=true")
	List<Object[]> findcnc_center(@Param("id") int id);
	
	@Query("select mdt.id, mdt.dus_test_center \r\n" + 
			"from DusTestCenter mdt inner join Applications a on a.cropid=mdt.crop_id and a.id=:id \r\n" + 
			"where mdt.centertype='LC' and mdt.isactive=true")
	List<Object[]> findlc_center(@Param("id") int id);
}
