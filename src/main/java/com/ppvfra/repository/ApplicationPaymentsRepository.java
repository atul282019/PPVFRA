package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationPayments;
import com.ppvfra.entity.Payment;

public interface ApplicationPaymentsRepository extends JpaRepository<ApplicationPayments, Integer> {


	
	/*
	 * @Query("SELECT b.paymentmode\r\n" + "FROM ApplicationPayments b \r\n" +
	 * "WHERE EXISTS (SELECT a.id FROM ApplicationPayments a WHERE a.applicationid =:id)"
	 * ) String getPaymentByApplicationid(@Param("id") int id);
	 */
	//Umesh Commenting below line on 06-02-2020
	//@Query("SELECT b.paymentmode FROM ApplicationPayments b WHERE b.id in (SELECT a.id FROM ApplicationPayments a WHERE a.applicationid =:id)")
	//@Query("SELECT b FROM ApplicationPayments b "
	//		+ "WHERE b.applicationid =:id")
	@Query("SELECT b.paymentmode\r\n" + "FROM ApplicationPayments b \r\n" +
			 "WHERE EXISTS (SELECT a.id FROM ApplicationPayments a WHERE a.applicationid =:id)"
			 )
	List<ApplicationPayments> getPaymentByApplicationid(@Param("id") int id);
	
	@Query("select a.id from ApplicationPayments a where a.applicationid=:id and paymenttype ='Application Registration'")
	int getPaymentId(@Param("id") int id);
	
	@Query("select ap.id,ap.applicationid,ap.paymentmode,"
			+ "ap.paymenttype,ap.amount,ap.ddnumber,ap.dddate,"
			+ "ap.documentname,ap.documentpath,ap.bankname,"
			+ "ap.branchname,ap.transactionid,ap.paymentstatus,"
			+ "ap.payment_category from ApplicationPayments ap "
			+ "where applicationid=:applicationid and paymenttype='DusTestFee' ")
			/*+ "and paymenttype='DusTestFee'")*/
	List<ApplicationPayments> getdustestfeedata(@Param("applicationid") Integer applicationid);
	
	 
	@Query(value="select m.fees,coalesce(m.salesvalue_percent,0) sval,"
			+ "coalesce(m.royalty_percent,0) rval,round(((coalesce(m.salesvalue_percent,0)/100)*m.fees),2)totalsale"
			+ ",round(((coalesce(m.royalty_percent,0)/100)*m.fees),2)totalroyality,"
			+ "m.fees+round(((coalesce(m.salesvalue_percent,0)/100)*m.fees),2)totalsaleamount,"
			+ "m.fees+round(((coalesce(m.royalty_percent,0)/100)*m.fees),2)totalroyalamount,"
			+ "m.varietyid,m.subvarietyid,m.id ,(round(((coalesce(m.salesvalue_percent,0)/100)*m.fees),2) \r\n" + 
			"+ round(((coalesce(m.royalty_percent,0)/100)*m.fees),2) +m.fees)totalamoount\r\n" + 
			"from m_fee m ,applications a"
			+ "	where m.paymenttype=:type and m.varietyid = a.varirtytypeid "
			+ "and a.id=:applicationid and coalesce(m.subvarietyid,0) in (select "
			+ "coalesce(aa.subvarietytypeid,0) from applications aa where "
			+ "aa.id=:applicationid)",nativeQuery=true)
	  List<Object[]> getannualrenewalfee(@Param("applicationid") Integer applicationid,@Param("type") String type);
	  
	  @Query("SELECT b FROM ApplicationPayments b WHERE b.id in (SELECT a.id FROM ApplicationPayments a WHERE a.applicationid =:id)")
	/*
	 * @Query("SELECT b FROM ApplicationPayments b " + "WHERE b.applicationid =:id")
	 */
		List<ApplicationPayments> getpaymentdetails_viaapplicationid(@Param("id") int id);
	  
	  @Query("select ap.id,ap.applicationid,ap.paymentmode,"
				+ "ap.paymenttype,ap.amount,ap.ddnumber,ap.dddate,"
				+ "ap.documentname,ap.documentpath,ap.bankname,"
				+ "ap.branchname,ap.transactionid,ap.paymentstatus,"
				+ "ap.payment_category from ApplicationPayments ap "
				+ "where applicationid=:applicationid and paymenttype in ('Annual')")
		List<ApplicationPayments> getannualrenewalfeedata(@Param("applicationid") Integer applicationid);
	
	  
	  @Query("select ap.id,ap.applicationid,ap.paymentmode,"
				+ "ap.paymenttype,ap.amount,ap.ddnumber,ap.dddate,"
				+ "ap.documentname,ap.documentpath,ap.bankname,"
				+ "ap.branchname,ap.transactionid,ap.paymentstatus,"
				+ "ap.payment_category from ApplicationPayments ap "
				+ "where applicationid=:applicationid and paymenttype in ('Renewal')")
		List<ApplicationPayments> getrenewalfeedata(@Param("applicationid") Integer applicationid);
	  
	  
		@Query("select a.documentname from ApplicationPayments a where "
				+ "a.id=:id and paymenttype "
				+ "='Application Registration'")
		public String getapp_pay_via_id(@Param("id") int id);
}
