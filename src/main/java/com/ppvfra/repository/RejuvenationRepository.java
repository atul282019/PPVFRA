package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.CandidateVarietyDetails;
import com.ppvfra.entity.Rejuvenation;

public interface RejuvenationRepository extends JpaRepository<Rejuvenation,Integer>{

	@Query("select ar.id,ar.applicationid,c.candidate_variety,ar.date_rejuvenation,t.code,ar.denomination,ar.quantity,ar.place,ar.findings,ar.no_of_packets_added_mts,ar.total_packets_mts\r\n" + 
			"from Rejuvenation ar join CandidateVariety c on ar.candidatevariety=c.id\r\n" + 
			"join TypeLine t on ar.type_line=t.id where ar.applicationid=:id")
	    List<Object[]> getRejuvenationDetails(@Param("id") Long id);

	    @Query("select r.candidatevariety,r.candidatevariety_other\r\n" + 
	    		"from Rejuvenation r where r.id=:id")
	    List<Rejuvenation> searchforvarietyvalue(@Param("id") int id);
	    
@Query("select ar.id,ar.applicationid,ar.candidatevariety_other, "
+ "ar.date_rejuvenation,ar.denomination,ar.quantity,ar.place,ar.findings, "
+ "ar.no_of_packets_added_mts,ar.total_packets_mts, ar.createdon"
+ " ,ar.type_line,ar.candidatevariety "
+ "from Rejuvenation ar where ar.applicationid=:applicationid")
List<Rejuvenation> returnlist(@Param("applicationid") Long applicationid);

}
