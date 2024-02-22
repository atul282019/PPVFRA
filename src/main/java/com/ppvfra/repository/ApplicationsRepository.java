package com.ppvfra.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Applications;
import com.ppvfra.entity.ReportTen;
import com.ppvfra.entity.ViewDusTestReport;
import com.ppvfra.entity.ViewStateWiseReport;
import com.ppvfra.entity.ViewStateWiseReport7;

public interface ApplicationsRepository extends JpaRepository<Applications, Integer>{

	@Query("select a.id,a.applicantid, a.applicanttypeid, apt.type from Applications a \r\n" + 
			"left join ApplicantTypes apt on a.applicanttypeid = apt.id\r\n" + 
			"where a.id=:id")
	List<Object[]> bind_values(@Param("id") int id);
	
	@Query("select a.id,a.varirtytypeid, a.subvarietytypeid,vt.varietytype,svt.subvarietytype from Applications a\r\n" + 
			"inner join VarietyType vt \r\n" + 
			"on vt.id = a.varirtytypeid\r\n" + 
			"left join SubVarietyType svt\r\n" + 
			"on  a.subvarietytypeid = svt.id\r\n" + 
			"where a.id=:id")
	List<Object[]> getVarietyById(@Param("id") int id);
	
	@Modifying
    @Transactional
	@Query("update Applications a set a.cropid =:cropid ,a.cropspeciesid =:cropspeciesid,a.denomination =:denomination,a.cropdetail =:cropdetail where a.id=:id")
	public int updateApplicationByFormIV( @Param("cropid") int cropid,@Param("cropspeciesid") int cropspeciesid, @Param("denomination") String denomination,@Param("cropdetail") String cropdetail, @Param("id") int id);
	
	@Modifying
    @Transactional
	@Query("update Applications a set a.varirtytypeid =:varirtytypeid where a.id=:id")
	public int updateApplicationByFormVarity(@Param("varirtytypeid") int varirtytypeid,@Param("id") int id);
	
	@Modifying
    @Transactional
	@Query("update Applications a set a.varirtytypeid =:varirtytypeid,a.subvarietytypeid =:subvarietytypeid where a.id=:id")
	public int updateApplicationFormVBySubVarity(@Param("varirtytypeid") int varirtytypeid,@Param("subvarietytypeid") int subvarietytypeid,@Param("id") int id);

	@Modifying
    @Transactional
	@Query("update Applications a set a.varirtytypeid =:varirtytypeid ,a.subvarietytypeid =:subvarietytypeid,a.notification_number=:number,a.copy_notiifcation_name=:notificationname,a.copy_notiifcation_path=:path,a.date_notification=:date,a.release_proposal_name=:proname,a.release_proposal_path=:propath where a.id=:id")
	public int updateApplicationByFormV(@Param("varirtytypeid") int varirtytypeid,@Param("subvarietytypeid") int subvarietytypeid,@Param("number") String number, @Param("notificationname") String notificationname,@Param("path") String path,@Param("date") Date date,@Param("proname") String proname,@Param("propath") String propath,@Param("id") int id);
	
	@Modifying
    @Transactional
	@Query("update Applications a set a.varirtytypeid =:varirtytypeid ,a.subvarietytypeid =:subvarietytypeid,a.release_proposal_name=:proname,a.release_proposal_path=:propath,a.genetic_engineering=:engineering where a.id=:id")
	public int updateApplication2ByFormV(@Param("varirtytypeid") int varirtytypeid,@Param("subvarietytypeid") int subvarietytypeid,@Param("proname") String proname,@Param("propath") String propath,@Param("engineering") String engineering, @Param("id") int id);
	
	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query("update Applications a set a.varirtytypeid =:varirtytypeid ,a.release_proposal_name=:proname,a.release_proposal_path=:propath,a.genetic_engineering=:engineering where a.id=:id"
	 * ) public int updateApplication2ByFormV(@Param("varirtytypeid") int
	 * varirtytypeid,@Param("proname") String proname,@Param("propath") String
	 * propath,@Param("engineering") String engineering,@Param("id") int id);
	 */
	
	@Modifying
    @Transactional
	@Query("update Applications a set a.candidatevariety =:candidatevariety ,a.candidatevariety_other =:candidatevarietyother,a.dus_features=:feature where a.id=:id")
	public int updateApplicationByFormVI(@Param("candidatevariety") String candidatevariety,@Param("candidatevarietyother") String candidatevarietyother,@Param("feature") String feature,@Param("id") int id);
	
	@Modifying
    @Transactional
	@Query("update Applications a set a.candidatevariety =:candidatevariety ,a.candidatevariety_other =:candidatevarietyother,a.dus_features=:feature,a.initialvariety=:initialvarietyval where a.id=:id")
	public int updateApplication2ByFormVI(@Param("candidatevariety") String candidatevariety,@Param("candidatevarietyother") String candidatevarietyother,@Param("feature") String feature,@Param("initialvarietyval") int initialvarietyval,@Param("id") int id);

	@Modifying
    @Transactional
	@Query("update Applications a set a.applicantid =:applicantid ,a.applicanttypeid =:applicanttypeid where a.id=:id")
	public int updateApplicationByFormVII(@Param("applicantid") int applicantid,@Param("applicanttypeid") int applicanttypeid,@Param("id") int id);

	@Modifying
    @Transactional
	@Query("update Applications a set a.principalplace =:principaladdress,a.domicile_statecode=:domicilestatecode,a.domicile_districtcode=:districtstatecode where a.id=:applicationid")
	public int updateApplicationByFormIII(@Param("principaladdress") String principaladdress,@Param("domicilestatecode") int domicilestatecode,@Param("districtstatecode") int districtstatecode,@Param("applicationid") int applicationid);
	
	@Modifying
    @Transactional
	@Query("update Applications a set a.is_convention_applicable =:conventionapplied where a.id=:id")
	public int updateApplicationByFormVIII(@Param("conventionapplied") Boolean conventionapplied,@Param("id") int id);
	 
	@Modifying
    @Transactional
	@Query("update Applications a set a.is_commercialised =:iscommercialised ,a.issue_first_sale_name =:firstsaleproof,a.issue_first_sale_path =:firstsalepath,a.protection_countries=:protection,a.c_denomination=:demonimation,a.trademark=:trademark,a.variation=:variationtrait,a.variation_name=:originalName2,a.applicantid =:applicantid ,a.applicanttypeid =:applicanttypeid,a.first_sale_date=:firstsaledate where a.id=:id")
	public int updateApplicationByFormIX(@Param("iscommercialised") Boolean iscommercialised, @Param("firstsaleproof") String firstsalproof,@Param("firstsalepath") String firstsalepath,@Param("protection") String protection,@Param("demonimation") String demonimation,@Param("trademark") String trademark,@Param("variationtrait") String variationtrait,@Param("firstsaledate") Date firstsaledate,@Param("applicantid") int applicantid,@Param("applicanttypeid") int applicanttypeid,@Param("id") int id,@Param("originalName2") String originalName2);
	 
	@Modifying
    @Transactional
	@Query("update Applications a set a.is_commercialised =:iscommercialised ,a.issue_first_sale_name =:firstsaleproof,a.issue_first_sale_path =:firstsalepath,a.protection_countries=:protection,a.c_denomination=:demonimation,a.trademark=:trademark,a.applicantid =:applicantid ,a.applicanttypeid =:applicanttypeid,a.first_sale_date=:firstsaledate where a.id=:id")
	public int updateApplication2ByFormIX(@Param("iscommercialised") Boolean iscommercialised, @Param("firstsaleproof") String firstsalproof,@Param("firstsalepath") String firstsalepath,@Param("protection") String protection,@Param("demonimation") String demonimation,@Param("trademark") String trademark,@Param("firstsaledate") Date firstsaledate,@Param("applicantid") int applicantid,@Param("applicanttypeid") int applicanttypeid,@Param("id") int id);
	 
	
	 @Modifying
	 @Transactional
	 @Query("update Applications a set a.denominationsx = :denominationsx,a.geographical_source = :source,a.attribution = :attribution,a.owners = :owners,a.planning_benefit_sharing=:sharing,a.is_farmer_variety_required=:is_farmer_variety_required,a.is_parental_required=:parentalrequired where a.id=:id")
	 public int updateApplicationByFormX(@Param("denominationsx") String denominationsx,@Param("source") String source,@Param("attribution") String attribution,@Param("owners") String owners,@Param("sharing") String sharing,@Param("is_farmer_variety_required") Boolean is_farmer_variety_required,@Param("parentalrequired") String parentalrequired,  @Param("id") int id);

	 @Modifying
	 @Transactional
	 @Query("update Applications a set a.denominationsx = :denominationsx,a.geographical_source = :source,a.attribution = :attribution,a.owners = :owners,a.planning_benefit_sharing=:sharing,a.is_farmer_variety_required=:is_farmer_variety_required,a.essentially_derived_variety=:cvariety,a.essentially_derived_variety_denomination=:cdenomination,a.essentially_derived_variety_geographical_source=:csource,a.essentially_derived_variety_owner=:cowner,a.is_parental_required=:parentalrequired where a.id=:id")
	 public int updateApplication2ByFormX(@Param("denominationsx") String denominationsx,@Param("source") String source,@Param("attribution") String attribution,@Param("owners") String owners,@Param("sharing") String sharing,@Param("is_farmer_variety_required") Boolean is_farmer_variety_required,@Param("cvariety") String cvariety,@Param("cdenomination") String cdenomination,@Param("csource") String csource,@Param("cowner") String cowner,@Param("parentalrequired") String parentalrequired, @Param("id") int id);

	 @Modifying
	 @Transactional
	 @Query("update Applications a set a.planning_benefit_sharing=:sharing where a.id=:id")
	 public int updateApplicationByFormXBlockC(@Param("sharing") String sharing, @Param("id") int id);

	 
	 @Modifying
	 @Transactional
	 @Query("update Applications a set a.is_parental_required=:parentalrequired where a.id=:id")
	 public int updateApplicationByParentalCase(@Param("parentalrequired") String parentalrequired, @Param("id") int id);

	 
	@Modifying
    @Transactional
	@Query("update Applications a set a.exotic_germplasm=:exoticgermplasm,a.applicantid =:applicantid ,a.applicanttypeid =:applicanttypeid where a.id=:id")
	public int updateApplicationByFormXI(@Param("exoticgermplasm") String exoticgermplasm,@Param("applicantid") int applicantid,@Param("applicanttypeid") int applicanttypeid,@Param("id") int id);
 
	@Modifying
    @Transactional
	@Query("update Applications a set a.expected_performance =:expectedperformance,a.applicantid =:applicantid ,a.applicanttypeid =:applicanttypeid where a.id=:id")
	public int updateApplicationByFormXII(@Param("expectedperformance") String expectedperformance,@Param("applicantid") int applicantid,@Param("applicanttypeid") int applicanttypeid,@Param("id") int id);
 
	@Modifying
	@Transactional
	@Query("update Applications a set a.is_agreed_clause =:is_agree ,a.application_declare_place =:place  ,a.application_signed_date =:dategiven  where a.id=:applicationid")
	public int updateDeclaration_Form1(@Param("applicationid") int applicationid,@Param("is_agree") String is_agree,@Param("place") String place,@Param("dategiven") Date dategiven);
 
	@Transactional
	@Query("select a.id,coalesce(a.companyid,a.institutionid),u.id,c.companyname,c.acronym,\r\n" + 
			"c.Year_of_Incorporation,c.Author_Address,u.email,u.contactno from ApplicantRegistration a \r\n" + 
			"inner join InternalUser u on a.id = u.applicantid\r\n" + 
			"left join CompanyRegistration c on a.companyid =c.id  where u.id=:id")
	List<Object[]> getDetailofCompanyorInstitutionTypeByUserId(@Param("id") int id);
	
	@Modifying
    @Transactional
	@Query("update Applications a set a.participation_in_capital=:radiovalue,a.company_name=:companyname,a.company_address=:establishmentaddress,a.company_incorporation=:incorporationyear,a.company_nationality=:nationality where a.id=:id")
	public int updateApplicationByCompanyorInstitution(@Param("radiovalue") Boolean radiovalue,@Param("companyname") String companyname,@Param("establishmentaddress") String establishmentaddress,@Param("incorporationyear") String incorporationyear, @Param("nationality") int nationality,@Param("id") int id);
	
	
	@Query(value="select mt.varietycd from applications a inner join m_varietytype  mt \r\n" + 
			"on a.varirtytypeid = mt.id where a.cropid =:cropid and a.id=:applicationid",nativeQuery =true)
	List<Object> variety_name(@Param("cropid") int cropid,@Param("applicationid") int applicationid);
	
	@Query(value="select count(*), m.status,m.id from applications a \r\n" + 
			"inner join m_applicationstatus m on \r\n" + 
			"m.id = a.application_current_status\r\n" + 
			"where a.createdon between :fromdate and :todate\r\n" + 
			"group by m.status,m.id order by m.status",nativeQuery =true)
	List<Object[]> findReport1(@Param("fromdate") Date fromdate,@Param("todate") Date todate2);
	
	@Query(value="select a.id,a.createdon,u.firstname,mg.crop_group,mc.crop_common_name,a.denomination,m.stage\r\n" + 
			"from applications a\r\n" + 
			"inner join m_cropgroups mg\r\n" + 
			"on mg.id = a.cropid\r\n" + 
			"inner join m_crops mc\r\n" + 
			"on mc.id = a.cropid\r\n" + 
			"left join m_stages m\r\n" + 
			"on m.id = a.application_current_status\r\n" + 
			"inner join users u\r\n" + 
			"on u.id = a.createdby\r\n" + 
			"where a.createdon between :fromdate and :todate and u.firstname = :applicantname \r\n" + 
			"and trim(mg.crop_group)=:cropgroup \r\n" + 
			"and trim(mc.crop_common_name)=:crop\r\n" + 
			"and trim(a.denomination)=:denomination",nativeQuery = true)
	List<Object[]> findReport2(@Param("fromdate") Date fromdate,@Param("todate") Date todate2,@Param("applicantname") String applicantname,@Param("cropgroup") String cropgroup,@Param("crop") String crop,@Param("denomination") String denomination);
	@Query(value="select a.id,to_char(a.createdon,'YYYY-MM-DD') as createdon,coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname), u.firstname) as firstname,mg.crop_group,\r\n" + 
			"mc.crop_common_name,a.denomination,m.status,ar.remarks\r\n" + 
			"from applications a\r\n" + 
			"left join Applicants ap on\r\n" + 
			"	a.applicantid = ap.id left join m_Company c on \r\n" + 
			"	ap.companyid = c.id left join m_Institution i on \r\n" + 
			"	ap.institutionid = i.id left join (select *,dense_rank() over(partition by applicationid order by id desc) rnk from\r\n" + 
			"application_remarks )ar\r\n" + 
			"on ar.applicationid = a.id and ar.rnk=1 --ar.status = a.application_current_status\r\n" + 
			"inner join m_crops mc on mc.id = a.cropid\r\n" + 
			"inner join m_cropgroups mg on mg.id = mc.cropgroupid\r\n" + 
			"left join m_applicationstatus m  on m.id = a.application_current_status\r\n" + 
			"left join users u  on u.id = a.createdby\r\n" + 
			"where a.createdon between :fromdate and :todate\r\n" + 
			"and case when :applicantname = '' then 1=1 else trim(firstname)=:applicantname end\r\n" + 
			"and case when :cropgroup = -1 then 1=1 else  mg.id=:cropgroup end \r\n" + 
			"and case when :crop =-1 then 1=1 else mc.id=:crop end \r\n" + 
			"and case when :denomination =''  then 1=1 else trim(a.denomination)=:denomination end",nativeQuery=true)
	List<Object[]> findReport2ByFromdataandToDate(@Param("fromdate") Date fromdate,@Param("todate") Date todate,@Param("applicantname") String applicantname,@Param("cropgroup") int cropgroup,@Param("crop") int crop,@Param("denomination") String denomination);
	
	@Query(value="select * from m_applicationstatus order by status",nativeQuery = true)
	List<Object[]> getstatus();
	
	
	@Query(value="select a.id,to_char(a.createdon,'YYYY-MM-DD') as createdon,coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname), u.firstname) as firstname,mg.crop_group,\r\n" + 
			"mc.crop_common_name,a.denomination,m.status,ar.remarks\r\n" + 
			"from applications a\r\n" + 
			"left join Applicants ap on\r\n" + 
			"	a.applicantid = ap.id left join m_Company c on \r\n" + 
			"	ap.companyid = c.id left join m_Institution i on \r\n" + 
			"	ap.institutionid = i.id "
			+ "left join (select *,dense_rank() over(partition by applicationid order by id desc) rnk from\r\n" + 
			"application_remarks )ar\r\n" + 
			"on ar.applicationid = a.id and ar.rnk=1 --ar.status = a.application_current_status\r\n" + 
			"inner join m_crops mc on mc.id = a.cropid\r\n" + 
			"inner join m_cropgroups mg on mg.id = mc.cropgroupid\r\n" + 
			"left join m_applicationstatus m  on m.id = a.application_current_status\r\n" + 
			"left join users u  on u.id = a.createdby\r\n" + 
			"where a.createdon between :fromdate and :todate \r\n" + 
			"and case when :applicantname = '' then 1=1 else trim(firstname)=:applicantname end  \r\n" + 
			"and case when :cropgroup = -1 then 1=1 else mg.id=:cropgroup end \r\n" + 
			"and case when :crop =-1 then 1=1  else mc.id=:crop end \r\n" +
			"and case when :denomination =''  then 1=1 else trim(a.denomination)=:denomination end\r\n" + 
			"and a.application_current_status in (5,6,3) ",nativeQuery = true)
	List<Object[]> findReport3(@Param("fromdate") Date fromdate,@Param("todate") Date todate2,@Param("applicantname") String applicantname,@Param("cropgroup") int cropgroup,@Param("crop") int crop,@Param("denomination") String denomination );
	
@Query(value="select a.id,to_char(a.createdon,'YYYY-MM-DD') as createdon,"
	+ "coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname), u.firstname) as firstname,mg.crop_group,mc.crop_common_name,a.denomination,"
	+ "m.status,ar.remarks from applications a\r\n" + 
	"left join Applicants ap on\r\n" + 
	"a.applicantid = ap.id left join m_Company c on \r\n" + 
	"ap.companyid = c.id left join m_Institution i on \r\n" + 
	"	ap.institutionid = i.id"
	+ " left join (select *,dense_rank() over(partition by "
	+ "applicationid order by id desc) rnk from "
	+ "application_remarks ) ar \r\n" + 
	" on ar.applicationid = a.id and ar.rnk=1 inner join m_crops "
	+ "mc on mc.id = a.cropid inner join m_cropgroups mg on mg.id "
	+ "= mc.cropgroupid left join m_applicationstatus m  on m.id "
	+ "= a.application_current_status left join users u  on u.id "
	+ "= a.createdby where a.createdon between :fromdate and "
	+ ":todate and case when :applicantname = '' then 1=1 else trim(firstname)=:applicantname end "
	+ "and case when :cropgroup=-1 then 1=1 else mg.id=:cropgroup end "
	+ "and case when :crop =-1 then 1=1 else mc.id=:crop "
	+ "end and case when :denomination ='' then 1=1"
	+ "else trim(a.denomination)=:denomination end "
	+ "and case when :status =-1 then 1=1 "
	+ "else a.application_current_status=:status end",nativeQuery = true)
	List<Object[]> findReport4(@Param("fromdate") Date fromdate,@Param("todate") Date todate2,@Param("applicantname") String applicantname,@Param("cropgroup") int cropgroup,@Param("crop") int crop,@Param("denomination") String denomination,@Param("status") int status);
	
		@Query(value="select a.id,coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname), u.firstname) as firstname,to_char(a.createdon,'YYYY-MM-DD')"
		+ " as createdon,mg.crop_group,mc.crop_common_name,a.denomination, "
		+ "m.status,ar.remarks,adt.dus_test_completed,adt.date_of_completion "
		+ " from applications a left join Applicants ap on\r\n" + 
		"	a.applicantid = ap.id left join m_Company c on \r\n" + 
		"	ap.companyid = c.id left join m_Institution i on \r\n" + 
		"	ap.institutionid = i.id left join (select *,dense_rank() over(partition"
		+ " by applicationid order by id desc) rnk from application_remarks ) "
		+ "ar on a.id = ar.applicationid  and ar.rnk=1 left join m_crops mc on"
		+ " mc.id = a.cropid left join m_cropgroups mg on mg.id = "
		+ "mc.cropgroupid left join m_applicationstatus m  on m.id = "
		+ "a.application_current_status left join application_dus_test adt "
		+ "on a.id = adt.applicationid left join users u  on u.id = "
		+ "a.createdby where a.createdon between :fromdate and :todate "
		+ "and case when :applicantname = '' then 1=1 "
		+ " else trim(firstname)=:applicantname end and case when "
		+ ":cropgroup = -1 then 1=1 else mg.id=:cropgroup end and case when "
		+ ":crop =-1 then 1=1 else mc.id=:crop end and case "
		+ "when :denomination =''  then 1=1 else trim(a.denomination)=:denomination "
		+ "end and case when :status =-1  then 1=1"
		+ "else a.application_current_status=:status end",nativeQuery = true)
		List<Object[]> findReport5(@Param("fromdate") Date fromdate,@Param("todate") Date 
				todate2,@Param("applicantname") String applicantname,@Param("cropgroup") int 
				cropgroup,@Param("crop") int crop,@Param("denomination") String denomination,
				@Param("status") int status);
	
		@Query(value="select a.id,coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname), u.firstname)firstname,a.principalplace,s.statename_inenglish,d.districtname_inenglish,to_char(a.createdon,'YYYY-MM-DD') as createdon, \r\n" + 
				"				mg.crop_group,mc.crop_common_name,a.denomination,m.status,\r\n" + 
				"				coalesce(ar.remarks,'') as remarks from applications a \r\n" + 
				"				left join Applicants ap on\r\n" + 
				"				a.applicantid = ap.id left join m_Company c on\r\n" + 
				"				ap.companyid = c.id left join m_Institution i on\r\n" + 
				"				ap.institutionid = i.id\r\n" + 
				"				left join (select *,dense_rank() \r\n" + 
				"				over(partition by\r\n" + 
				"				applicationid order by id desc) rnk from application_remarks ) ar on \r\n" + 
				"				a.id = ar.applicationid and ar.rnk=1 inner join m_crops mc on \r\n" + 
				"				mc.id = a.cropid inner join m_cropgroups mg on mg.id = \r\n" + 
				"				mc.cropgroupid left join m_applicationstatus m  on m.id = \r\n" + 
				"				a.application_current_status left join users u  on u.id = a.createdby \r\n" + 
				"				left join m_states s on s.statecode = a.domicile_statecode left join \r\n" + 
				"				m_districts d on d.districtcode = a.domicile_districtcode where \r\n" + 
				"				a.createdon between :fromdate and :todate and case when :applicantname = '' then 1=1 else trim(firstname)=:applicantname end\r\n" + 
				"				and case when :cropgroup=-1 then 1=1 else mg.id=:cropgroup end \r\n" + 
				"				and case when :crop=-1 then 1=1 else mc.id=:crop end\r\n" + 
				"				and case when :denomination='' then 1=1 else trim(a.denomination) =:denomination end \r\n" + 
				"				and case when :status=-1 then 1=1 else a.application_current_status=:status end \r\n" + 
				"				and case when :districtcode=-1 then 1=1 else d.districtcode=:districtcode end \r\n" + 
				"				and case when :statecode=-1 then 1=1 else s.statecode=:statecode end\r\n" + 
				"				order by s.statename_inenglish,d.districtname_inenglish asc",nativeQuery = true)
		List<Object[]> findReport6(@Param("fromdate") Date fromdate,@Param("todate") Date todate2,@Param("applicantname") String applicantname,@Param("cropgroup") int cropgroup,@Param("crop") int crop,@Param("denomination") String denomination,@Param("status") int status,@Param("statecode") int statecode,@Param("districtcode") int districtcode);

		
	
@Query(value="select a.id,u.name,to_char(a.createdon,'YYYY-MM-DD') as "
	+ "createdon,mg.crop_group,mc.crop_common_name,a.denomination,m.status "
	+ ",ar.remarks from applications a "
	+ "left join (select *,dense_rank() over(partition by applicationid "
	+ "order by id desc) rnk from application_remarks )ar on "
	+ "ar.applicationid = a.id and ar.rnk=1 "
	+ "left join m_crops mc on mc.id = a.cropid left join m_cropgroups "
	+ "mg on mg.id = mc.cropgroupid left join m_applicationstatus m  on "
	+ "m.id = a.application_current_status inner join users u  on u.id = "
	+ "a.createdby where a.createdon between :fromdate and :todate and "
	+ "mg.id= case when :cropgroup = -1 then mg.id else :cropgroup end"
	+ " order by mc.crop_common_name asc", 
	nativeQuery = true) 
List<Object[]> findReport10Html(@Param("fromdate") Date fromdate,@Param("todate") Date todate2,@Param("cropgroup") int cropgroup);
	 
	  @Query(value="SELECT extract(year from date(a.createdon)) AS year,\r\n" + 
	  		"			COUNT(a.id) AS countofapplication\r\n" + 
	  		"			from applications a\r\n" + 
	  		"			left join (select *,dense_rank() over(partition by applicationid order by id desc) rnk from\r\n" + 
	  		"			application_remarks )ar\r\n" + 
	  		"			on ar.applicationid = a.id and ar.rnk=1 --ar.status = a.application_current_status\r\n" + 
	  		"			inner join m_crops mc on mc.id = a.cropid\r\n" + 
	  		"			inner join m_cropgroups mg on mg.id = mc.cropgroupid\r\n" + 
	  		"			left join m_applicationstatus m  on m.id = a.application_current_status\r\n" + 
	  		"			left join users u  on u.id = a.createdby\r\n" + 
	  		"			left join m_states s on s.statecode = a.domicile_statecode\r\n" + 
	  		"			left join m_districts d on d.districtcode = a.domicile_districtcode\r\n" + 
	  		"			WHERE a.createdon BETWEEN :fromdate AND :todate \r\n" + 
	  		"			and case when :applicantname = '' then 1=1 else trim(u.firstname)=:applicantname end\r\n" + 
	  		"			and case when :cropgroup = -1 then 1=1 else mg.id=:cropgroup end\r\n" + 
	  		"			and case when :crop =-1 then 1=1 else mc.id=:crop end\r\n" + 
	  		"			and case when :denomination =''  then 1=1 else trim(a.denomination)=:denomination end\r\n" + 
	  		"			and case when :status =-1  then 1=1 else a.application_current_status=:status end \r\n" + 
	  		"			and case when :districtcode =-1  then 1=1 else d.districtcode=:districtcode end \r\n" + 
	  		"			and case when :statecode =-1  then 1=1 else s.statecode=:statecode end\r\n" + 
	  		"			GROUP BY 1",nativeQuery = true)
	List<Object[]> findReport8(@Param("fromdate") Date fromdate,@Param("todate") Date todate2,@Param("applicantname") String applicantname,@Param("cropgroup") int cropgroup,@Param("crop") int crop,@Param("denomination") String denomination,@Param("status") int status,@Param("statecode") int statecode,@Param("districtcode") int districtcode);

	@Query(value="select extract(year from date(a.createdon)) AS cyear,\r\n" + 
			"COUNT(a.id) AS countofapplication \r\n" + 
			"FROM applications a \r\n" + 
			"WHERE a.createdon BETWEEN :fromdate AND :todate \r\n" + 
			"GROUP BY cyear",nativeQuery = true)
	List<Object[]> findReport9(@Param("fromdate") Date fromdate,@Param("todate") Date todate2);
	
	@Query(value="select a.id,to_char(a.createdon,'YYYY-MM-DD') as createdon,u.firstname,mg.crop_group,mc.crop_common_name,a.denomination,m.status,ar.remarks\r\n" + 
			"\r\n" + 
			"			from applications a\r\n" + 
			"			left join (select *,dense_rank() over(partition by applicationid order by id desc) rnk from\r\n" + 
			"			application_remarks )ar\r\n" + 
			"			on ar.applicationid = a.id and ar.rnk=1 --ar.status = a.application_current_status\r\n" + 
			"			inner join m_crops mc on mc.id = a.cropid\r\n" + 
			"			inner join m_cropgroups mg on mg.id = mc.cropgroupid\r\n" + 
			"			left join m_applicationstatus m  on m.id = a.application_current_status\r\n" + 
			"			inner join users u  on u.id = a.createdby\r\n" + 
			"			where a.createdon between :fromdate and :todate\r\n" + 
			"			and trim(mg.crop_group)= case when :reistrar = '' then trim(mg.crop_group) else :reistrar end",nativeQuery = true)
	List<Object[]> findReport11(@Param("fromdate") Date fromdate,@Param("todate") Date todate2,@Param("reistrar") String registrar);
	
	@Query(value="select a from ViewStateWiseReport a \r\n" + 
			"where a.createdon between :fromdate and :todate\r\n" + 
			" and case when :applicantname='' then 1=1 else a.name=:applicantname end\r\n" + 
			" and case when :cropgroup=-1 then 1=1  else a.cropgroupid=:cropgroup end \r\n" + 
			" and case when :crop=-1 then 1=1 else a.cropid=:crop end\r\n" + 
			" and case when :denomination=''  then 1=1 else a.denomination=:denomination end\r\n" + 
			" and a.statusid in (5,6,3)",nativeQuery = true)
	//List<ViewStateWiseReport> findJasperReport2(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("applicantname") String applicantname,@Param("cropgroup") int cropgroup,@Param("crop") int crop,@Param("denomination") String denomination,@Param("status") int status);
	List<ViewStateWiseReport> findJasperReport2(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("applicantname") String applicantname,@Param("cropgroup") int cropgroup,@Param("crop") int crop,@Param("denomination") String denomination);
	
	@Query("select a from ViewStateWiseReport a where a.createdon between "
			+ " :fromdate and :todate and a.name =case when :applicantname "
			+ " = '' then trim(a.name) else :applicantname end and "
			+ "a.cropgroupid= case when :cropgroup = -1 then a.cropgroupid "
			+ "else :cropgroup end and a.cropid=case when :crop =-1 then "
			+ "a.cropid else :crop end and trim(a.denomination)=case when "
			+ ":denomination =''  then trim(a.denomination) else "
			+ ":denomination end order by crop_common_name asc")
	List<ViewStateWiseReport> findJasperReportWithoutStatus(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("applicantname") String applicantname,@Param("cropgroup") int cropgroup,@Param("crop") int crop,@Param("denomination") String denomination);
	
	
	@Query("select a from ViewDusTestReport a \r\n" + 
			"where a.createdon between :fromdate and :todate\r\n" + 
			" and a.name =case when :applicantname = '' then trim(a.name) else :applicantname end\r\n" + 
			" and a.cropgroupid= case when :cropgroup = -1 then a.cropgroupid else :cropgroup end \r\n" + 
			" and a.cropid=case when :crop =-1 then a.cropid else :crop end\r\n" + 
			" and trim(a.denomination)=case when :denomination =''  then trim(a.denomination) else :denomination end "
		   +" and a.application_current_status = case when :status =-1 then a.application_current_status else :status end ")
	List<ViewDusTestReport> findJasperReportDusTestReport(
			@Param("fromdate") String fromdate,@Param("todate") String 
			todate,@Param("applicantname") String applicantname,
			@Param("cropgroup") int cropgroup,@Param("crop") int crop, 
			@Param("denomination") String denomination , 
			@Param("status") int status);

	@Query("select a from ReportTen a \r\n" + 
			"where a.createdon between :fromdate and :todate\r\n" + 
			"and a.cropgroupid= case when :cropgroup = -1 then "
			+ "a.cropgroupid else :cropgroup end order by a.crop_common_name")
	List<ReportTen> findReport10(@Param("fromdate") String fromdate,@Param("todate") String todate2,@Param("cropgroup") int cropgroup);
	
	@Query("select a from ViewStateWiseReport a where a.createdon between :fromdate "
			+ "and :todate and a.name =case when :applicantname = '' then "
			+ "trim(a.name) else :applicantname end and a.cropgroupid= case "
			+ "when :cropgroup = -1 then a.cropgroupid else :cropgroup end and "
			+ "a.cropid=case when :crop =-1 then  a.cropid else :crop end and "
			+ "trim(a.denomination)=case when :denomination =''  then "
			+ "trim(a.denomination) else :denomination end and a.statusid=case when "
			+ ":status =-1  then  a.statusid else :status end and a.districtcode=case "
			+ "when :districtcode =-1  then a.districtcode else :districtcode end and "
			+ "a.statecode=case when :statecode =-1  then a.statecode else :statecode "
			+ "end order by a.statename_inenglish,a.districtname_inenglish asc")
	List<ViewStateWiseReport> findJasperReportStatwise(@Param("fromdate") String fromdate,@Param("todate") String todate2,@Param("applicantname") String applicantname,@Param("cropgroup") int cropgroup,@Param("crop") int crop,@Param("denomination") String denomination,@Param("status") int status,@Param("statecode") int statecode,@Param("districtcode") int districtcode);

	
	@Modifying
	   @Transactional
	@Query("update Applications a set a.application_current_status =4 where a.id=:id")
	public int updateApplicationByFinalSubmit(@Param("id") int id);

	
	@Modifying
	   @Transactional
	@Query("update Applications a set a.pvpno =:pvpnogen,a.filing_date=:filingdate where a.id=:id")
	public int updateApplicationpvpno(@Param("id") int id,@Param("pvpnogen") String pvpnogen,@Param("filingdate") Date filingdate);

	@Modifying
	@Transactional
	@Query("update Applications a set a.application_current_status =36 where a.id=:applicationid")
	int updateApplicationStatus(@Param("applicationid") int applicationid);
	
	@Query(value="select application_current_status \r\n" + 
			"from applications where id=:id",nativeQuery = true)
	long returnapplicationstat(@Param("id") int id);

	@Modifying
	@Transactional
	@Query("update Applications a set a.transgenic_bioname =:bioname,a.transgenic_biopath =:biopath where a.id=:applicationid")
	public int updateApplicationforVIform(@Param("bioname") String bioname,@Param("biopath") String biopath,@Param("applicationid") int applicationid);
	
	
	@Modifying
	@Transactional
	@Query("update Applications a set a.application_current_status =37 where a.id=:applicationid")
	int deficiencyupdate_status(@Param("applicationid") int applicationid);
	
	@Modifying
	@Transactional
	@Query("update Applications a set a.application_current_status =38 where a.id=:applicationid")
	int deficiencyupdate_status_afterdefiupload(@Param("applicationid") int applicationid);
	
	
	@Query(value="select application_current_status \r\n" + 
			"from applications where id=:id",nativeQuery = true)
	long returnapplication_status(@Param("id") int id);
	
	@Query("select a.formtype from Applications a where a.id=:id")
	String formtype_values(@Param("id") int id);  
	
	@Query("select a.applicantid,to_char(a.createdon,'yyyy-MM-dd'),"
	+ "coalesce(c.companyname,i.InstituteName) ,a.c_denomination,"
	+ "cc.crop_common_name,cs.crop_botanical_name,a.id,a.formtype,a.pvpno from "
	+ "Application a "
	+ "left join ApplicantRegistration ap on a.applicantid = ap.id "
	+ "left join CompanyRegistration c on ap.companyid = c.id "
	+ "left join InstitutionRegistration i on ap.institutionid = i.id "
	+ "left join Crops cc on a.cropid = cc.id "
	+ "left join CropSpecies cs on a.cropspeciesid =cs.id "
	+ "where a.application_current_status =:status_no "
	+ "and a.createdon between :fdate and :tdate order by a.id desc")
	List<Object[]> getstatus_wise_applications_list(@Param("status_no") int 
			status_no,@Param("fdate") Date fdate,@Param("tdate") Date tdate);
	
	@Query(value="select a.applicantid,to_char(a.createdon,'yyyy-MM-dd'),"
	+ "coalesce(c.companyname,i.InstituteName) ,a.c_denomination,"
	+ "cc.crop_common_name,cs.crop_botanical_name,a.id,a.formtype,a.pvpno,"
	+ " iu.firstname,s.statename_inenglish,apc.address,apc.city from "
	+ "Applications a "
	+ "left join Applicants ap on a.applicantid = ap.id "
	+ "left join m_Company c on ap.companyid = c.id "
	+ "left join m_Institution i on ap.institutionid = i.id "
	+ "left join m_Crops cc on a.cropid = cc.id "
	+ "left join m_CropSpecies cs on a.cropspeciesid =cs.id "
	+ "left join Users iu on a.applicantid = iu.applicantid "
	+ "left join m_States s on a.domicile_statecode = s.statecode "
	+ "left join (select *,dense_rank() over(partition by applicationid "
	+ "order by id desc) rnk from Application_Contacts) "
	+ "apc on apc.applicationid = a.id and apc.rnk=1 "
	+ " where a.application_current_status =:status_no and "
	+ "cast(extract(year from a.createdon) as integer) = :year_obtained "
	+ "and a.createdon between :f_date and :t_date "
	+ "order by a.id desc",nativeQuery =true)
	List<Object[]> getstatus_wise_applications_list2(@Param("status_no") 
	int status_no,@Param("year_obtained") int year_obtained,
	@Param("f_date") Date f_date,@Param("t_date") Date t_date );

	
	@Query("select a.applicantid,to_char(a.createdon,'yyyy-MM-dd'),"
			+ "coalesce(c.companyname,i.InstituteName) ,a.c_denomination,"
			+ "cc.crop_common_name,cs.crop_botanical_name,a.id,a.formtype,"
			+ "a.pvpno from Application a "
			+ "left join ApplicantRegistration ap on a.applicantid = ap.id "
			+ "left join CompanyRegistration c on ap.companyid = c.id "
			+ "left join InstitutionRegistration i on ap.institutionid = i.id "
			+ "left join Crops cc on a.cropid = cc.id "
			+ "left join CropSpecies cs on a.cropspeciesid =cs.id "
			+ "where a.createdon between :fdate and :tdate and "
			+ "cast(extract(year from a.createdon) as integer) = :year_val order by "
			+ "a.id desc")
			List<Object[]> getyearonyear_reviewdata(@Param("fdate") Date 
					fdate,@Param("tdate") Date tdate, @Param("year_val") int year_val);

			
			@Modifying
		    @Transactional
			@Query("update Applications a set a.varirtytypeid=4,"
				+ "a.candidatevariety =:candidatevariety ,"
				+ "a.candidatevariety_other =:candidatevarietyother,"
				+ "a.dus_features=:feature where a.id=:id")
			public int updateform3_6(@Param("candidatevariety") String 
					candidatevariety,@Param("candidatevarietyother") 
			String candidatevarietyother,@Param("feature") 
			String feature,@Param("id") int id);
			
			
@Query(value="select a.id,to_char(a.createdon,'YYYY-MM-DD') as createdon,"
	+ "coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname), u.firstname) as firstname,mg.crop_group,mc.crop_common_name,a.denomination,"
	+ "m.status,ar.remarks,ac.period_of_protection,ac.protection_expiry_date"
	+ " from applications a\r\n" + 
	"left join Applicants ap on\r\n" + 
	"	a.applicantid = ap.id left join m_Company c on \r\n" + 
	"	ap.companyid = c.id left join m_Institution i on \r\n" + 
	"	ap.institutionid = i.id left join (select *,dense_rank() over(partition by "
	+ "applicationid order by id desc) rnk from "
	+ "application_remarks ) ar \r\n" + 
	" on ar.applicationid = a.id and ar.rnk=1 left join "
	+ "application_certificates ac on a.id = ac.applicationid "
	+ "inner join m_crops "
	+ "mc on mc.id = a.cropid inner join m_cropgroups mg on mg.id "
	+ "= mc.cropgroupid left join m_applicationstatus m  on m.id "
	+ "= a.application_current_status left join users u  on u.id "
	+ "= a.createdby where a.createdon between :fromdate and "
	+ ":todate and case when :applicantname "
	+ "= '' then 1=1 else trim(firstname)=:applicantname end and "
	+ " case when :cropgroup = -1 then 1=1 else mg.id=:cropgroup "
	+ "end and case when :crop =-1 then 1=1 else mc.id=:crop "
	+ "end and case when :denomination ='' "
	+ "then 1=1 else trim(a.denomination)=:denomination end and "
	+ "case when :status =-1  then 1=1"
	+ "else a.application_current_status=:status end ",nativeQuery = true)
	List<Object[]> findReport7(@Param("fromdate") Date fromdate,
	@Param("todate") Date todate2,@Param("applicantname") 
	String applicantname,@Param("cropgroup") int cropgroup,
	@Param("crop") int crop,@Param("denomination") String 
	denomination,@Param("status") int status);
	
	
	@Query("select a from ViewStateWiseReport7 a \r\n" + 
			"where a.createdon between :fromdate and :todate\r\n" + 
			" and a.name =case when :applicantname = '' then trim(a.name) else :applicantname end\r\n" + 
			" and a.cropgroupid= case when :cropgroup = -1 then a.cropgroupid else :cropgroup end \r\n" + 
			" and a.cropid=case when :crop =-1 then a.cropid else :crop end\r\n" + 
			" and trim(a.denomination)=case when :denomination =''  then trim(a.denomination) else :denomination end\r\n" + 
			" and a.statusid=case when :status =-1  then  a.statusid else :status end" )
	List<ViewStateWiseReport7> findJasperReport7(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("applicantname") String applicantname,@Param("cropgroup") int cropgroup,@Param("crop") int crop,@Param("denomination") String denomination,@Param("status") int status);
	
	
	@Query(value=" select * from m_applicationstatus where "
			+ "status ='Legal issue'",nativeQuery = true)
	List<Object[]> getlegalstatus();
	
	@Query("select a from ViewStateWiseReport a \r\n" + 
			"where a.createdon between :fromdate and :todate\r\n" + 
			" and a.name =case when :applicantname = '' then trim(a.name) else :applicantname end\r\n" + 
			" and a.cropgroupid= case when :cropgroup = -1 then a.cropgroupid else :cropgroup end \r\n" + 
			" and a.cropid=case when :crop =-1 then a.cropid else :crop end\r\n" + 
			" and trim(a.denomination)=case when :denomination =''  then trim(a.denomination) else :denomination end\r\n" + 
			" and a.statusid = case when :status =-1 then a.statusid else :status end" )
 List<ViewStateWiseReport> findlegal_jasperreport(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("applicantname") String applicantname,@Param("cropgroup") int cropgroup,@Param("crop") int crop,@Param("denomination") String denomination,@Param("status") int status);
	
	@Query(value=" select * from m_applicationstatus where "
			+ "status ='Registration Certificate Issued'",nativeQuery = true)
	List<Object[]> getregisterstatus();
	
	@Query(value=" select * from m_applicationstatus where "
			+ "status ='DUS Result Received'",nativeQuery = true)
	List<Object[]> getdus_result_received();	
			
}

