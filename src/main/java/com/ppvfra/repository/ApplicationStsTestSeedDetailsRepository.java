package com.ppvfra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationStsTestSeedDetails;

public interface ApplicationStsTestSeedDetailsRepository extends JpaRepository<ApplicationStsTestSeedDetails,Integer>{

	@Query(value="select id from application_sts_test_seed_details "
			+ "where  application_sts_test_id =:application_sts_test_id",
			nativeQuery= true)
	int returnid(@Param("application_sts_test_id") int application_sts_test_id );

}
