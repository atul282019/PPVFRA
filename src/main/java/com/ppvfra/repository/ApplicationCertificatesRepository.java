package com.ppvfra.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationCertificates;


public interface ApplicationCertificatesRepository extends JpaRepository<ApplicationCertificates, Integer> {

	
	@Query(value="select ac.id,ac.applicationid,ac.certificate_issued,\r\n" + 
			"ac.denomination_registered,ac.registration_number ,ac.certificate_serial_no\r\n" + 
			",ac.cert_issue_date,ac.period_of_protection, ac.crop_id, ac.category,\r\n" + 
			"ac.type,ac.protection_expiry_date\r\n" + 
			"from application_certificates ac where ac.applicationid=:applicationid",nativeQuery= true)
	List<Object[]> applicationcertificate(@Param("applicationid") int applicationid);
	
	@Query(value="SELECT current_date, date(CURRENT_DATE + INTERVAL '18 years') as frwd_date",nativeQuery= true)
	List<Object[]> date_data();
	
	@Query(value="SELECT current_date, date(CURRENT_DATE + INTERVAL '15 years') as frwd_date",nativeQuery= true)
	List<Object[]> date_data_others();
	
	@Modifying
	@Transactional
	@Query(value="update application_certificates set certificate_issued =:cissue ,certificate_serial_no =:cno,active=true where applicationid=:id",nativeQuery = true)
	void issuecertificateno(@Param("cissue") String cissue,@Param("cno") String cno,@Param("id") int id);
	
	
	@Query(value="select ac.id,ac.certificate_issued,ac.certificate_serial_no ,\r\n" + 
			" ac.cert_issue_date,cast(extract(year from ac.cert_issue_date) as int) issue_year,\r\n" + 
			"cast(extract(month from ac.cert_issue_date) as int) issue_month,\r\n" + 
			"cast(extract(day from ac.cert_issue_date)as int) issue_day,ac.protection_expiry_date,\r\n" + 
			"cast(extract(year from ac.protection_expiry_date) as int) last_year,\r\n" + 
			"cast(extract(month from ac.protection_expiry_date) as int) last_month,\r\n" + 
			"cast(extract(day from ac.protection_expiry_date) as int) last_day,\r\n" + 
			"cast(extract(year from ac.protection_expiry_date)-extract(year from ac.cert_issue_date) as int) totalyear\r\n" + 
			",mvt.varietycd,a.company_name,current_date,cast(extract(year from current_date) as int) currentdate,cast(extract(month from current_date) as int) currentmonth,cast(extract(day from current_date) as int) currentday \r\n" + 
			" from application_certificates ac \r\n" + 
			" inner join applications a on ac.applicationid = a.id\r\n" + 
			" inner join m_varietytype mvt on a.varirtytypeid = mvt.id where ac.applicationid=:applicationid",nativeQuery = true)
	List<Object[]> cert_issue_show(@Param("applicationid") int applicationid);
	
	
	@Query(value="select coalesce((\r\n" + 
			"select certificate_serial_no from application_certificates\r\n" + 
			"where applicationid=:applicationid),'0')",nativeQuery=true)
	String whether_cert_generated(@Param("applicationid") Long applicationid);
}
