package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Fees;

public interface FeesRepository extends  JpaRepository<Fees, Integer>{
	
	@Query("select fees from Fees where varietyid=:variety and subvarietyid=:subvariety and applicantcategory=:category and paymenttype=:type")
	List<Fees> getFeesByVarietyAndSubvarietytype(@Param("variety") int variety,@Param("subvariety") int subvariety,@Param("category") String category,@Param("type") String type);

	@Query("select fees from Fees where varietyid=:variety and applicantcategory=:individual and paymenttype=:registration")
	List<Fees> getFeesByVarietyAndSubvarietytype(@Param("variety") int variety,@Param("individual") String individual,@Param("registration") String registration);

	@Query("select fees from Fees where varietyid=:variety and applicantcategory=:category and paymenttype=:type")
	List<Fees> getFeesByVarietytype(@Param("variety") int variety,@Param("category") String category,@Param("type") String type);
	
	@Query(value="select m.id,m.varietyid,m.subvarietyid,m.paymenttype,m.fees," + 
			"m.salesvalue_percent,m.royalty_percent,vt.varietytype " + 
			"from m_fee m inner join m_varietytype vt on m.varietyid= vt.id " + 
			"where m.paymenttype='Annual' " + 
			"and m.varietyid in ( select  a.varirtytypeid from applications a "+
			"where a.id=:appid)",nativeQuery= true)
	List<Object[]> getannualfee_pop(@Param("appid") int appid);
}
