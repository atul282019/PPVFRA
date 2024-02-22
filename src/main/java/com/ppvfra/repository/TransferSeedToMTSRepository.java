package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.TransferSeedToMTS;

public interface TransferSeedToMTSRepository extends JpaRepository<TransferSeedToMTS,Integer>{


	@Query("select mts.id,mts.applicationid,mts.seed_transfer_date,mts.cell_location,mts.transfer_by,mts.seed_status,t.code,mts.moisture_ptg,mts.germination_ptg,mts.purity_ptg\r\n" + 
			"from TransferSeedToMTS mts join TypeLine t on mts.type_line=t.id where mts.applicationid=:id")
	 List<Object[]> getTransferSeedToMTSDetails(@Param("id") Long id);
	 
	 @Query("select atm.id,atm.applicationid,atm.moisture_ptg,atm.germination_ptg,\r\n" + 
	 		"atm.purity_ptg,atm.seed_transfer_date,atm.cell_location,atm.transfer_by,\r\n" + 
	 		"atm.seed_status,atm.remarks,atm.type_line\r\n" + 
	 		"from TransferSeedToMTS atm where atm.applicationid=:applicationid")
	 List<TransferSeedToMTS> transfermtsdetails(@Param("applicationid") Long applicationid);
	
}
