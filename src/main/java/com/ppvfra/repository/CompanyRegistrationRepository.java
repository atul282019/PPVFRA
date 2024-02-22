package com.ppvfra.repository;
 

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.CompanyRegistration;
import com.ppvfra.entity.InternalUser;
 
@Transactional
public interface CompanyRegistrationRepository extends  JpaRepository<CompanyRegistration,Long> {
	
	@Query("select c.id,c.companyname from CompanyRegistration c where "
			+ "c.verificationstatus=:verificationstatus and c.active=:active "
			+ "order by c.companyname asc")
	List<CompanyRegistration> companyList(@Param("verificationstatus")  String verificationstatus, @Param("active")  Boolean active);
	
	@Query("Select a from CompanyRegistration a where a.id=:id")
	CompanyRegistration companydetails(@Param("id") Long id);
	
	@Query("Select a from CompanyRegistration a where cin_number=:cinnumber")
	List<CompanyRegistration> checkCinNumber(@Param("cinnumber")  String cinnumber);
	
	@Query("Select a from CompanyRegistration a where companyname=:companyname")
	List<CompanyRegistration> checkCompanyName(@Param("companyname")  String companyname);
	
	@Query("Select a from CompanyRegistration a where acronym=:acronym")
	List<CompanyRegistration> checkAcronym(@Param("acronym")  String acronym);
	
	
	
	@Modifying
	@Query("update CompanyRegistration c set c.verificationremarks =:remarks ,c.verificationstatus =:statustobeset,c.modifiedbyip =:mip,c.verifiedby =:verifierid,c.verifiedbydesignation =:verifierdesignation,c.active='true'  where c.id=:id")
	int dataadding(@Param("statustobeset")String statustobeset, @Param("remarks")String remarks, @Param("id") long id , @Param("mip") String mip,@Param("verifierid") int verifierid, @Param("verifierdesignation") String verifierdesignation );

	
	@Query("select c.id,c.companyname,c.acronym,c.Year_of_Incorporation,c.cin_number,\r\n" + 
			"cc.Country as seat_Countryid,ss.statename_inenglish as seat_state,\r\n" + 
			"dd.districtname_inenglish as seat_districts,c.seat_CityVillageTown,c.seat_Pincode,\r\n" + 
			"cc1.Country as Registeredoffice_Countryid,\r\n" + 
			"ss1.statename_inenglish as registeredoffice_state,\r\n" + 
			"dd1.districtname_inenglish as registeredoffice_districts,\r\n" + 
			"c.Registeredoffice_CityVillageTown,\r\n" + 
			"c.Registeredoffice_Pincode,c.Author_Name,c.Author_Designation,c.Author_Address,\r\n" + 
			"cc2.Country as Author_Countryid , ss2.statename_inenglish as author_statecode,\r\n" + 
			"dd2.districtname_inenglish,c.Author_CityVillageTown,c.Author_Pincode,\r\n" + 
			"coalesce((c.author_mobilecountrycode||'-'||c.Author_Mobile),c.Author_Mobile) as mobile ,\r\n" + 
			"coalesce((c.author_faxcountrycode ||'-'||c.Author_Fax),c.Author_Fax) as fax,c.Author_Email,u.username,c.seat_address,c.registered_address\r\n" + 
			"from CompanyRegistration c\r\n" + 
			"left join Country cc on c.seat_Countryid = cc.id left join Country cc1 on  c.Registeredoffice_Countryid = cc1.id\r\n" + 
			"left join Country cc2 on  c.Author_Countryid = cc2.id left join States ss on c.seat_state = ss.statecode\r\n" + 
			"left join States ss1 on c.registeredoffice_state = ss1.statecode\r\n" + 
			"left join Districts dd on c.seat_districts = dd.districtcode\r\n" + 
			"left join Districts dd1 on c.registeredoffice_districts = dd1.districtcode\r\n" + 
			"left join States ss2 on c.author_statecode = ss2.statecode\r\n" + 
			"left join Districts dd2 on c.author_districtcode = dd2.districtcode\r\n" + 
			"left join InternalUser u on c.id = u.companyid "
			+ "and u.usertypeid = 2 where c.id=:id")
	List<Object[]> findDetails(@Param("id") Long id);
	
	@Modifying
	@Query("update InternalUser set isactive=true  where usertypeid=2 and companyid=:id")
	void dataupdate(@Param("id") Integer id );
	
	@Modifying
	@Query("update CompanyRegistration c set c.verificationremarks =:remarks ,c.verificationstatus =:statustobeset,c.modifiedbyip =:mip,c.verifiedby =:verifierid,c.verifiedbydesignation =:verifierdesignation,c.active='false'  where c.id=:id")
	int dataadding1(@Param("statustobeset")String statustobeset, @Param("remarks")String remarks, @Param("id") long id , @Param("mip") String mip,@Param("verifierid") int verifierid, @Param("verifierdesignation") String verifierdesignation );

	@Modifying
	@Query("update InternalUser set isactive=false  where usertypeid=2 and companyid=:id")
	void dataupdate1(@Param("id") Integer id );
	

	// ------ Dashboard KPI start--------
	@Query("select count(*) from CompanyRegistration")
	Long get_company_total();
	
	@Query("select count(*) from InstitutionRegistration")
	Long get_institution_total();
	
	@Query("select count(*) from ApplicantRegistration")
	Long get_applicant_total();
	
	@Query("select count(*) from CompanyRegistration a \r\n" + 
			 "where a.verificationstatus =:company_status ")
	Long get_company_count(@Param("company_status") String company_status);
	
	@Query("select count(*) from CompanyRegistration a \r\n" + 
			 "where a.verificationstatus is null ")
	Long get_company_count_new();
	
	@Query("select count(*) from InstitutionRegistration a \r\n" + 
			 "where a.verificationstatus =:institution_status ")
	Long get_institution_count(@Param("institution_status") String institution_status);
	
	@Query("select count(*) from InstitutionRegistration a \r\n" + 
			 "where a.verificationstatus is null ")
	Long get_institution_count_new();
	
	@Query("select count(*) from ApplicantRegistration a \r\n" + 
			 "where a.verificationstatus =:applicant_status ")
	Long get_applicant_count(@Param("applicant_status") String applicant_status);
	
	@Query("select count(*) from ApplicantRegistration a \r\n" + 
			 "where a.verificationstatus is null ")
	Long get_applicant_count_new();
	
	@Query("select count(*) from Applications a \r\n" + 
			 "where a.application_current_status =:application_status ")
	Long get_application_count(@Param("application_status") int application_status);
	
	
	@Query("select a from CompanyRegistration a "
		+ "where a.verificationstatus =:status ")
	List<CompanyRegistration> findvia_status(@Param("status") String status);
	
	
	@Query("select a from CompanyRegistration a "
			+ "where a.verificationstatus is null ")
		List<CompanyRegistration> findvia_status_NewComp();
	//-------- Dashboard KPI end ----------
	
	
	@Query(value="select c.id,c.author_email,c.author_name from m_Company c where c.id=:id",nativeQuery = true)
	List<Object[]> company_email(@Param("id") int id);
	
	Page<CompanyRegistration> findAll(Pageable pageable);

}
