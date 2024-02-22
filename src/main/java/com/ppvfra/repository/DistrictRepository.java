package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Districts;

public interface DistrictRepository extends JpaRepository<Districts, Long>{


@Query("SELECT d.districtcode, d.districtname_inenglish FROM Districts d where d.statecode=:statecode")
	public List<Districts> getAllDistrictsByStateCode(@Param("statecode") Integer statecode);


@Query("select d from Districts d order by d.districtname_inenglish")
List<Districts> getalldistricts();
	

}
