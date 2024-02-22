package com.ppvfra.repository;

 

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Application;
public interface ApplicationRepository extends JpaRepository<Application, Integer>{
 
	@Query("select a.applicantid,to_char(a.createdon,'yyyy-MM-dd'),"
+ "coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname),u.firstname) ,\r\n" + 
"a.denomination,cc.crop_common_name,cs.crop_botanical_name,a.id,a.formtype "
+ "from Application a left join ApplicantRegistration ap on a.applicantid = ap.id "
+ "left join CompanyRegistration c on ap.companyid = c.id left join InstitutionRegistration i on "
+ "ap.institutionid = i.id left join Crops cc on a.cropid = cc.id left "
+ "join CropSpecies cs on a.cropspeciesid =cs.id left join InternalUser u on "
+ "ap.id = u.applicantid  where u.id =:id and a.application_current_status < 4 order by a.id desc")
	List<Object[]> find_details_of_application(@Param("id") int id);
	
	

@Query(value="select a.applicantid,to_char(a.createdon,'yyyy-MM-dd'),"
			+ "coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname),u.firstname) ,\r\n" + 
			"a.denomination,cc.crop_common_name,cs.crop_botanical_name,a.id,apst.stage,aps.status, (select acvd.id from \r\n" + 
			"application_candidate_variety_details acvd \r\n" + 
			" where acvd.candidate_variety_id = 2 and a.id=acvd.applicationid) as acvdid,a.cropid, "
			+ "(select mv.varietytype from m_VarietyType mv where mv.id= a.varirtytypeid),\r\n" + 
			"(select sv.subvarietytype from m_SubVarietyType sv,m_VarietyType mvr \r\n" + 
			"where a.subvarietytypeid = sv.id and mvr.id= sv.varietyid),a.pvpno,a.application_current_status,a.formtype, "
			+ "(select tmp.dateof_submission from (select dateof_submission ,dense_rank() over( "
			+ "partition by appscr.applicationid order by id asc) rnk "
			+ "from Application_Scrutiny appscr where  appscr.applicationid "
			+ "=a.id) tmp where rnk=1) as dtdate,current_date"
			+ " from Applications a left join Applicants ap on a.applicantid = ap.id "
			+ "left join m_Company c on ap.companyid = c.id left join m_Institution i on "
			+ "ap.institutionid = i.id left join m_Crops cc on a.cropid = cc.id left "
			+ "join m_CropSpecies cs on a.cropspeciesid =cs.id left join Users u on "
			+ "ap.id = u.applicantid left join m_ApplicationStatus aps on a.application_current_status = aps.id "
			+ "left join m_Stages apst on aps.status = apst.status "
			+ "where u.id =:id and a.application_current_status >= 4 order by a.id desc ", nativeQuery= true)
				List<Object[]> application_statuswise(@Param("id") int id);			
				
				
				@Query("select a.applicantid,to_char(a.createdon,'yyyy-MM-dd'),"
						+ "coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname),u.firstname) ,\r\n" + 
						"a.c_denomination,cc.crop_common_name,cs.crop_botanical_name,a.id,apst.stage,aps.status, (select acvd.id from \r\n" + 
						"CandidateVarietyDetails acvd \r\n" + 
						" where acvd.candidate_variety_id = 2 and a.id=acvd.applicationid),a.cropid, "
						+ "(select mv.varietytype from VarietyType mv where mv.id= a.varirtytypeid),\r\n" + 
						"(select sv.subvarietytype from SubVarietyType sv,VarietyType mvr \r\n" + 
						"where a.subvarietytypeid = sv.id and mvr.id= "
						+ "sv.varietyid)"
						+ " from Application a left join ApplicantRegistration ap on a.applicantid = ap.id "
						+ "left join CompanyRegistration c on ap.companyid = c.id left join InstitutionRegistration i on "
						+ "ap.institutionid = i.id left join Crops cc on a.cropid = cc.id left "
						+ "join CropSpecies cs on a.cropspeciesid =cs.id left join InternalUser u on "
						+ "ap.id = u.applicantid left join ApplicationStatus aps on a.application_current_status = aps.id "
						+ "left join Application_Stages apst on aps.status = apst.status where a.application_current_status >= 4 order by a.id desc ")
							List<Object[]> application_statuswise_all();
						
	@Query("select a.id,a.applicantid,coalesce(c.companyname,i.InstituteName,u.firstname),ac.id,"
	+ "ac.name,ac.address,cc.Country,s.statename_inenglish,ct.type,c.registered_address,c.Year_of_Incorporation,d.districtname_inenglish,"
	+ "ac.pincode,ac.city,ut.type,c.Author_Countryid,att.type ,ac.telephone_number,ac.fax_number,ac.emailid,a.exotic_germplasm,"
	+ "a.principalplace,ss.statename_inenglish,dd.districtname_inenglish,a.expected_performance,"
	+ "a.cropdetail,cr.crop_common_name,cs.crop_botanical_name,cg.crop_group,a.denomination"
	+ ",vt.varietytype, svt.subvarietytype,a.date_notification,a.notification_number,"
	+ "a.copy_notiifcation_name,a.copy_notiifcation_path,a.candidatevariety,a.dus_features,"
	+ "a.is_commercialised,a.first_sale_date,a.issue_first_sale_name,"
	+ "a.issue_first_sale_path, a.protection_countries,a.c_denomination,"
	+ "a.trademark,a.variation,a.application_signed_date,"
	+ "a.application_declare_place "
	+ ",a.release_proposal_name,a.release_proposal_path, "
	+ "a.variation_name,a.planning_benefit_sharing,a.transgenic_bioname,"
	+ "a.transgenic_biopath,a.participation_in_capital,a.initialvariety,a.genetic_engineering,cs.Family"
	+ " from Applications a "
	+ "left join ApplicantRegistration ap on a.applicantid = ap.id "
	+ "left join CompanyRegistration c on ap.companyid = c.id "
	+ "left join InstitutionRegistration i on ap.institutionid = i.id "
	+ "left join InternalUser u on ap.id = u.applicantid "
	+ "left join ApplicationContacts ac on a.id=ac.applicationid "
	+ "left join ContactTypes ct on ac.contacttype = ct.id "
	+ "left join Country cc on ac.countryid  = cc.id "
	+ "left join States s on ac.stateid = s.statecode "
	+ "left join Districts d on ac.districtid = d.districtcode "
	+ "left join UserTypes ut on ap.applicanttype = ut.id "
	+ "left join ApplicantTypes att on a.applicanttypeid = att.id "
	+ "left join States ss on a.domicile_statecode = ss.statecode "
	+ "left join Districts dd on a.domicile_districtcode  =dd.districtcode "
	+ "left join Crops cr on a.cropid = cr.id "
	+ "left join CropSpecies cs on a.cropspeciesid  =cs.id "
	+ "left join CropGroup cg on cr.cropgroupid = cg.id "
	+ "left join VarietyType vt on a.varirtytypeid = vt.id "
	+ "left join SubVarietyType svt on a.subvarietytypeid = svt.id "
	+ "where a.id=:id")
	List<Object[]> details_portion1(@Param ("id") int id);	
	
	
	/*
	 
	@Query( "select a.id,ac.id,ac.denomination,ac.nature,ac.fillingdate,"
			+ "c.Country,ac.authority,ac.application_number,"
			//ast.applicationstatus,"
			+ "cc.Country,ac.ondateofapplication from Applications a "
			+ "left join ApplicationConventionCountries ac on a.id= "
			+ "ac.applicationid left join Country c on ac.country = c.id "
			//  + "left join ApplicationStatus ast on  ac.applicationstatus = " +
			//  "cast (ast.id as char) "
			+ "left join Country cc on ac.incountries = cc.id "
			+ "where a.id =:id")
	 */
	@Query( "select a.id,ac.id,ac.denomination,ac.nature,ac.fillingdate,"
			+ "c.Country,ac.authority,ac.application_number,ac.applicationstatus,"
			+ "cc.Country,ac.ondateofapplication,a.is_convention_applicable from Applications a "
			+ "left join ApplicationConventionCountries ac on a.id= "
			+ "ac.applicationid left join Country c on ac.country = c.id "
			+ "left join Country cc on ac.incountries = cc.id "
			+ "where a.id =:id")
			List<Object[]> details_portion2(@Param ("id") int id);	
			
			
			@Query( "select a.id,apl.id,apl.parental_line,apl.denominations, "
			+ "apl.source,apl.authorised_letter_obtained,tl.code,a.is_parental_required from "
			+ "Applications a  left join ApplicationParentalline apl on "
			+ "a.id = apl.applicationid "
			+ "inner join TypeLine tl on apl.typeline = tl.id "
			+ "where a.id=:id")
			List<Object[]> details_portion3(@Param ("id") int id);	
					
			@Query( "select a.id ,a.denominationsx, a.geographical_source,\r\n" + 
					"a.attribution,a.owners,a.is_convention_applicable,a.exotic_germplasm,"
					+ "is_farmer_variety_required,a.expected_performance,a.is_parental_required \r\n" + 
					"from Applications a  \r\n" + 
					 "  where a.id=:id")
			List<Object[]> details_portion4(@Param ("id") int id);

			@Query( "select a.essentially_derived_variety_denomination,a.essentially_derived_variety_geographical_source,\r\n" + 
					"a.essentially_derived_variety_owner,a.essentially_derived_variety from Applications a where a.id =:id")
			List<Object[]> details_portionapplication2(@Param ("id") int id);
			
			@Modifying
			@Transactional
			@Query("update Application set acknowledgementno =:ackno ,fileno =:fno, acknowledgement_date=:datet, acknowledge_doc_path=:path where id=:id ")
			void updateApplicationrep(@Param("ackno") String ackno,@Param("fno") String fno,@Param("id") int id,@Param("datet") Date nn, @Param("path") String path);
			
			
			@Query(value="select a.id,a.acknowledgementno ,a.varirtytypeid,mvt.varietycd,u.email, \r\n" + 
					"	coalesce(u.fullname,u.firstname) ,ap.address,ap.city, \r\n" + 
					"	s.statename_inenglish,a.denomination,cs.crop_botanical_name,\r\n" + 
					"	a.acknowledgement_date,cm.crop_common_name from Applications a \r\n" + 
					"	inner join Users u on a.applicantid = u.applicantid \r\n" + 
					"	left join Applicants ap on a.applicantid = ap.id \r\n" + 
					"	left join m_States s on ap.stateid = s.statecode\r\n" + 
					"	left join m_CropSpecies cs on a.cropspeciesid = cs.id "
					+ "inner join m_crops cm on cs.cropid= cm.id  \r\n" + 
					"	inner join m_varietytype mvt on a.varirtytypeid = mvt.id\r\n" + 
					"	where a.id =:id",nativeQuery = true)
					List<Object[]> getackdetails(@Param("id") int id);

			@Query(value="select cast(extract(year from current_date) as char(4))",nativeQuery =true )
			List<Object> getdate_data();
			
			@Query(value="select coalesce(max(id)+1,1) as id From seq_no where fyear=:fyear",nativeQuery = true)
			List<Object> getseq_no(@Param("fyear") String fyear);
			
			@Query(value="SELECT coalesce(max(id)+1,1) as id FROM variety_new_seq_no",nativeQuery = true)
			List<Object> getnew_var_seq_no();	
			
			@Query(value="SELECT coalesce(max(id)+1,1) as id FROM variety_extant_seq_no",nativeQuery = true)
			List<Object> getextent_var_seq_no();


			@Query(value="SELECT coalesce(max(id)+1,1) as id FROM botname_seq_no",nativeQuery = true)
			List<Object> getbotname_seq_no();


			@Query(value="SELECT coalesce(max(id)+1,1) as id FROM financial_variety",nativeQuery = true)
			List<Object> getfinance_seq_no();
			
			@Query(value="select cast(extract(year from current_date) as char(4))",nativeQuery =true )
			String getdate_data_string();

			@Query(value="select coalesce(a.company_name,mat.type) as compname,to_char("
					+ "a.createdon,'yyyy-MM-dd'),a.pvpno,coalesce("
					+ "mt.varietycd,a.candidatevariety_other) as vtname,"
					+ "c.crop_common_name,a.denomination,a.varirtytypeid "
					+ "from applications a left join m_varietytype mt on "
					+ "a.varirtytypeid = mt.id left join m_crops c on "
					+ "a.cropid = c.id  left join m_applicanttypes mat on "
					+ "mat.id = a.applicanttypeid where a.id=:id",
					nativeQuery = true)
			List<Object[]> fetch_headerdata(@Param("id") int id);
			
			@Query(value="select distinct dateof_submission,remarks_doc_scrutiny,complaince \r\n" + 
					"	from application_scrutiny where applicationid =:id and dateof_submission is not null",nativeQuery = true)
			List<Object[]> saved_data_scrutiny(@Param("id") int id);
			
			
			@Query(value="select distinct remarks_doc_scrutiny \r\n" + 
					"	from application_scrutiny where applicationid =:id",nativeQuery = true)
			String saved_data_scrutiny_remarks(@Param("id") int id);
			
			@Query(value="select distinct dateof_submission \r\n" + 
					"	from application_scrutiny where applicationid =:id",nativeQuery = true)
			Date saved_data_scrutiny_lastdate(@Param("id") int id);

@Query(value="with temp12 as (select distinct ms.id, \r\n" + 
		"substring(initcap(regexp_split_to_table(ms.crop_botanical_name,' ')),1,1) \r\n" + 
		"as botcaps, ms.crop_botanical_name  ,ms.initials \r\n" + 
		"from m_cropspecies ms inner join applications ap on \r\n" + 
		"ap.cropspeciesid = ms.id ) \r\n" + 
		"(select a.id ,a.varirtytypeid,temp12.crop_botanical_name,\r\n" + 
		" string_agg(temp12.BOTCAPS,' '),vt.varietycd , \r\n" + 
		" substring(vt.varietycd,1,1) as vartype_initial,temp12.initials from \r\n" + 
		" applications a  left join temp12 on a.cropspeciesid = temp12.id \r\n" + 
		" left join m_varietytype vt  on a.varirtytypeid =  vt.id \r\n" + 
		" where a.id=:id \r\n" + 
		" group by a.id ,a.varirtytypeid,temp12.crop_botanical_name,\r\n" + 
		" vt.varietycd,temp12.initials)",nativeQuery=true)
		List<Object[]> getfiledata(@Param("id") int id);
		
		
@Query(value="select coalesce((max(s_id)+1),1)  from file_no fn "
		+ "where fn.botanical_initial=:btname and variety_type "
		+ "=:vartype and f_year =:fyear",nativeQuery=true)
		List<Object> getdata_for_filenogeneration(@Param("btname") 
		String btname,@Param("vartype") String vartype,@Param("fyear")
		int fyear);

@Query(value="select cast(extract(year from current_date) as int)",nativeQuery =true )
List<Object> getdate_data_data();

@Query(value="select fileno,acknowledgementno,acknowledgement_date "
		+ "from applications where id=:id and acknowledgementno is not null",nativeQuery=true)
List<Object[]> getfile_ack_data(@Param("id") int id);

@Query(value="select a.id,a.applicantid,a.fileno,a.acknowledgementno,\r\n" + 
		"a.acknowledgement_date ,coalesce(u.fullname,u.firstname) ,\r\n" + 
		"a.pvpno,u.email,u.firstname from applications a,users u where a.id=:id \r\n" + 
		"and a.applicantid = u.applicantid ",nativeQuery = true)
List<Object[]> getmail_data(@Param("id") int id);

@Query(value="select a.id,a.varirtytypeid,v.varietycd,\r\n" + 
		"a.cropdetail \r\n" + 
		"from applications a left join m_varietytype v on a.varirtytypeid = v.id\r\n" + 
		"where a.id=:id",nativeQuery= true)
List<Object[]> getissue_cert_data(@Param("id") int id);

 

@Query("select a.applicantid,coalesce(to_char(a.createdon,'yyyy-MM-dd'),to_char(filing_date,'yyyy-MM-dd')) as dt1, \r\n" + 
		"coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname),\r\n" + 
		"u.firstname) ,a.c_denomination,cc.crop_common_name,cs.crop_botanical_name,\r\n" + 
		"a.id,aps.status,a.cropid, \r\n" + 
		"(select mv.varietytype from VarietyType mv where mv.id= a.varirtytypeid),\r\n" + 
		"(select sv.subvarietytype from SubVarietyType sv,VarietyType mvr \r\n" + 
		" where a.subvarietytypeid = sv.id and mvr.id= sv.varietyid),a.formtype,mcg.crop_group \r\n" + 
		" from Applications a left join ApplicantRegistration ap on \r\n" + 
		" a.applicantid = ap.id left join CompanyRegistration c on ap.companyid = c.id \r\n" + 
		" left join InstitutionRegistration i on ap.institutionid = i.id \r\n" + 
		" left join Crops cc on a.cropid = cc.id left join CropSpecies cs \r\n" + 
		" on a.cropspeciesid =cs.id left join InternalUser u on ap.id = u.applicantid \r\n" + 
		" left join ApplicationStatus aps on a.application_current_status = aps.id "
		+ "left join CropGroup mcg on cc.cropgroupid = mcg.id order by a.id desc")
       List<Object[]> getapplication_adminview();


 @Query(value="select a.acknowledge_doc_path from "
   + "applications a where a.id=:id and a.acknowledge_doc_path is  not null",nativeQuery = true) 
 List<Object[]> getack_path_viaid(@Param("id")int id);

 @Query(value="select a.applicantid,coalesce(to_char(a.createdon,'yyyy-MM-dd'),to_char(a.filing_date,'yyyy-MM-dd')) as app_date, \r\n" + 
		 "coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname),\r\n" + 
		 "u.firstname) ,a.c_denomination,cc.crop_common_name,cs.crop_botanical_name,\r\n" + 
		 "a.id,aps.status,a.cropid, (select mv.varietytype from m_VarietyType mv \r\n" + 
		 "where mv.id= a.varirtytypeid), (select sv.subvarietytype from \r\n" + 
		 "m_SubVarietyType sv,m_VarietyType mvr where a.subvarietytypeid = sv.id \r\n" + 
		 "and mvr.id= sv.varietyid),a.formtype,mcg.crop_group from Applications a \r\n" + 
		 "left join Applicants ap on \r\n" + 
		 "a.applicantid = ap.id left join m_Company c on \r\n" + 
		 "ap.companyid = c.id left join m_Institution i on \r\n" + 
		 "ap.institutionid = i.id left join m_Crops cc on a.cropid = cc.id \r\n" + 
		 "left join m_CropSpecies cs  on a.cropspeciesid =cs.id left join m_cropgroups mcg on cc.cropgroupid = mcg.id \r\n" + 
		 "left join Users u on ap.id = u.applicantid \r\n" + 
		 "inner join m_ApplicationStatus aps on a.application_current_status = aps.id\r\n" + 
		 "and aps.status=:status order by a.id desc",nativeQuery = true)
		 List<Object[]> getapplication_datastatuswise(@Param("status") String status);


		 
@Query(value="select coalesce(max(id)+1,1) as id From seq_no_pvp where fyear=:fyear",nativeQuery = true)
List<Object> getpvpseq_no(@Param("fyear") String fyear);
			
		 
		 
@Query(value="select coalesce((select distinct("
+ "mo.office_code) from m_office mo inner join "
+ "m_office_states ms on mo.id=ms.officeid inner "
+ "join applications a on ms.stateid = "
+ "a.domicile_statecode and a.id=:applicationid), "
+ "(select distinct t1.office_code from m_office t1 "
+ "where t1.location='New Delhi'))",nativeQuery = true)
List<Object> getoffice_code(@Param("applicationid") int applicationid);

@Query(value="select acvd.candidate_variety_other from application_candidate_variety_details acvd \r\n" + 
		"inner join m_candidatevariety mc on acvd.candidate_variety_id = mc.id \r\n" + 
		"where acvd.applicationid =:applicationid \r\n" + 
		"and acvd.candidate_variety_other is not null", nativeQuery= true)
List<Object[]> details_portion5(@Param("applicationid") int applicationid);


@Query(value="select id,company_name,company_address,company_incorporation,participation_in_capital,company_nationality\r\n" + 
		"from applications where id=:id",nativeQuery=true)
List<Object[]> secondscreen_data(@Param("id") int id);


@Query("Select a from Applications a where a.denomination=:denomination")
List<Application> denocheck(@Param("denomination")  String denomination);

@Query(value="select 1,acvd.candidate_variety_other from application_candidate_variety_details acvd \r\n" + 
		"inner join m_candidatevariety mc on acvd.candidate_variety_id = mc.id \r\n" + 
		"where acvd.applicationid =:applicationid \r\n" + 
		"and acvd.candidate_variety_other is not null", nativeQuery= true)
List<Object[]> details_portion_newcase(@Param("applicationid") int applicationid);



@Query(value="select a.varirtytypeid,a.subvarietytypeid,a.release_proposal_name,a.genetic_engineering from applications a where a.id=:applicationid", nativeQuery= true)
List<Object[]> details_portion5thsecond(@Param("applicationid") int applicationid);


@Modifying  
@Transactional
@Query(value="update Applications  set principalplace =:principaladdress,domicile_statecode=null,domicile_districtcode=null where id=:applicationid",nativeQuery=true)
public int updateApplication_f3(@Param("principaladdress") String principaladdress,@Param("applicationid") int applicationid);


@Query(value="select id,applicanttypeid from "
		+ "applications where id=:id",nativeQuery=true)
List<Object[]> fetchapplicanttypeid(@Param("id") int id);




@Query(value="select a.applicantid,to_char(a.createdon,'yyyy-MM-dd'),coalesce(c.companyname,i.InstituteName,(u.firstname||' '||u.lastname),u.firstname) ,\r\n" + 
		"a.c_denomination,cc.crop_common_name,cs.crop_botanical_name,a.id,apst.stage,aps.status, (select acvd.id from application_candidate_variety_details acvd \r\n" + 
		" where acvd.candidate_variety_id = 2 and a.id=acvd.applicationid) as acvdid,a.cropid,  (select mv.varietytype from m_VarietyType mv where mv.id= a.varirtytypeid),\r\n" + 
		" (select sv.subvarietytype from m_SubVarietyType sv,m_VarietyType mvr   where a.subvarietytypeid = sv.id and mvr.id= sv.varietyid),a.pvpno,a.application_current_status,a.formtype, \r\n" + 
		"  (select tmp.dateof_submission from (select dateof_submission ,	dense_rank() over( partition by appscr.applicationid order by id asc) rnk from \r\n" + 
		"	Application_Scrutiny appscr where  appscr.applicationid =a.id) tmp where rnk=1) as dtdate,\r\n" + 
		"	current_date from Applications a left join Applicants ap on a.applicantid = ap.id 	left join m_Company c on ap.companyid = c.id left join m_Institution i on ap.institutionid = i.id \r\n" + 
		"	left join m_Crops cc on a.cropid = cc.id left join m_CropSpecies cs on a.cropspeciesid=cs.id  left join Users u on ap.id = u.applicantid left join m_ApplicationStatus aps \r\n" + 
		"	on a.application_current_status = aps.id left join m_Stages apst on aps.status \r\n" + 
		"	= apst.status where ap.id =:applicantid  order by a.id desc", nativeQuery= true)
			List<Object[]> total_applications_applicantwise(@Param("applicantid") int applicantid);			


 }
