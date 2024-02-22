package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicantRegistration;
@Transactional
public interface ApplicantRegistrationRepository extends JpaRepository<ApplicantRegistration, Integer>
{
 
	@Query("select a.id,apt.applicantrole ,Coalesce(c.companyname,i.InstituteName,''), "
	+ "u.username,u.email,u.mobile_number,u.designation, a.address,cc.Country, "
	+ "ss.statename_inenglish,dd.districtname_inenglish,a.city,a.pincode,a.verificationstatus,a.verifierremarks from "
	+ "ApplicantRegistration a left join ApplicantRole apt on a.applicanttype = apt.id "
	+ "left join CompanyRegistration c on a.companyid = c.id left join InstitutionRegistration i on "
	+ "a.institutionid = i.id left join Country cc on a.countryid = cc.id "
	+ "left join InternalUser u on a.id = u.applicantid left join States ss "
	+ "on a.stateid = ss.statecode left join Districts dd on "
	+ "a.districtid = dd.districtcode order by a.id asc")
	List<Object[]> findAlldata();
	
	@Modifying
	@Query("update ApplicantRegistration a set a.verifierremarks =:remarks ,a.verificationstatus =:statustobeset,a.modifiedbyip =:mip,a.verifiedby =:verifierid,a.verifiedbydesignation =:verifierdesignation,a.active='true'  where a.id=:id")
	int dataadding(@Param("statustobeset")String statustobeset, @Param("remarks")String remarks, @Param("id") int id , @Param("mip") String mip,@Param("verifierid") int verifierid, @Param("verifierdesignation") String verifierdesignation );
    
	
	@Query("select a.id,apt.applicantrole ,Coalesce(c.companyname,i.InstituteName,''), \r\n" + 
	"u.username,u.email,u.mobile_number,u.designation, a.address,cc.Country,\r\n" + 
	"ss.statename_inenglish,dd.districtname_inenglish,a.city,a.pincode,a.verificationstatus,a.verifierremarks,a.applicantname,u.firstname,u.contactno from \r\n" + 
	"ApplicantRegistration a left join ApplicantRole apt on a.applicanttype = apt.id \r\n" + 
	"left join CompanyRegistration c on a.companyid = c.id left join InstitutionRegistration i on \r\n" + 
	"a.institutionid = i.id left join Country cc on a.countryid = cc.id \r\n" + 
	"left join InternalUser u on a.id = u.applicantid left join States ss \r\n" + 
	"on a.stateid = ss.statecode left join Districts dd on \r\n" + 
	"a.districtid = dd.districtcode and u.usertypeid = 4 where a.id=:id")
	List<Object[]> findDetails(@Param("id") Integer id);

	@Modifying
	@Query("update InternalUser set isactive=true  where usertypeid=4 and applicantid=:id")
	void dataupdate(@Param("id") Integer id );
	

	

	@Query("select count(*) from ApplicantRegistration a inner join "
	+ "ApplicantRole ar on a.applicanttype = ar.id and "
	+ "ar.applicantrole='Company' left join InternalUser u on "
	+ "a.id = u.applicantid and u.isactive= 'true' inner join "
	+ "CompanyRegistration c on a.companyid = c.id and "
	+ "a.verificationstatus='Accepted' and c.companyname=:companyname")
	Long getcount_of_Accepted_records(@Param("companyname") String companyname);
	
	
	@Query("select count(*) from ApplicantRegistration a  inner join "
	+ "ApplicantRole ar on a.applicanttype = ar.id and "
	+ "ar.applicantrole='Company' left join InternalUser u on "
	+ "a.id = u.applicantid and u.isactive= 'false' inner join "
	+ "CompanyRegistration c on a.companyid = c.id and "
	+ "a.verificationstatus='Rejected' and c.companyname=:companyname")
	Long getcount_of_Rejected_records(@Param("companyname") String companyname);
	
	
	@Query("select count(*) from ApplicantRegistration a inner join "
	+ "ApplicantRole ar on a.applicanttype = ar.id  and "
	+ "ar.applicantrole='Company' left join InternalUser u on "
	+ "a.id = u.applicantid and u.isactive= 'false' inner join "
	+ "CompanyRegistration c on a.companyid = c.id and "
	+ "a.verificationstatus is null and c.companyname=:companyname")
	Long getcount_of_New_records(@Param("companyname") String companyname);
	
	 
	@Query("select count(*) from ApplicantRegistration a inner join ApplicantRole ar on a.applicanttype = ar.id \r\n" + 
	" and ar.applicantrole='Company' left join InternalUser u on a.companyid = u.companyid "
	+ " and u.isactive= 'true' inner join CompanyRegistration c on a.companyid = c.id and "
	+ "c.verificationstatus='Accepted' and c.companyname=:companyname "
	+ "where a.verificationstatus='Accepted'")
	Long getcount_of_Active_records(@Param("companyname") String companyname);
	 
	
	
	@Query(value=" select a.id,c.companyname,u.email,u.mobile_number,"
	+ "u.designation, a.address,cc.Country, ss.statename_inenglish,"
	+ "dd.districtname_inenglish,a.city,a.pincode, a.verificationstatus,"
	+ "a.verifierremarks,tmp.tmptotal from Applicants a left join (select app.applicantid, "
	+ "count(*) as tmptotal from applications app \r\n" + 
	"	group by app.applicantid ) tmp on a.id = tmp.applicantid "
	+ "inner join m_ApplicantRole apt "
	+ "on a.applicanttype = apt.id inner join m_Company c on a.companyid = "
	+ "c.id and c.verificationstatus=:status and c.companyname=:companyname "
	+ "left join m_Country cc on a.countryid = cc.id  left join Users u on "
	+ "a.id = u.applicantid and u.isactive= 'true' left join m_States ss on "
	+ "a.stateid = ss.statecode left join m_Districts dd "
	+ "on a.districtid = dd.districtcode where a.verificationstatus=:status "
	+ "order by a.id asc",nativeQuery=true)
	List<Object[]> getDatavia_accepted_name(@Param("companyname") String companyname,@Param("status") String status);
	
	
	@Modifying
	@Query("update InternalUser set isactive=false  where usertypeid=4 and applicantid=:id")
	void dataupdate1(@Param("id") Integer id );
	
	@Query(value="select a.id,c.companyname,u.email,u.mobile_number,u.designation, "
			+ "a.address,cc.Country, ss.statename_inenglish,"
			+ "dd.districtname_inenglish,a.city,a.pincode, a.verificationstatus,"
			+ "a.verifierremarks,tmp.tmptotal from Applicants a left join "
			+ "(select app.applicantid,count(*) as tmptotal from applications app \r\n" + 
			"	group by app.applicantid ) tmp on a.id = tmp.applicantid inner join "
			+ "m_ApplicantRole apt on a.applicanttype = apt.id inner join "
			+ "m_Company c on a.companyid = c.id and a.verificationstatus = "
			+ ":status and c.companyname=:companyname left join m_Country cc on "
			+ "a.countryid = cc.id  left join Users u on a.id = u.applicantid "
			+ "and u.isactive= 'false' left join m_States ss on a.stateid = "
			+ "ss.statecode left join m_Districts dd on a.districtid = "
			+ "dd.districtcode  order by a.id asc",nativeQuery=true)
	List<Object[]> getDatavia_rejected_name(@Param("companyname") String companyname,@Param("status") String status);
	
	@Query(value=" select a.id,c.companyname,u.email,u.mobile_number,"
	+ "u.designation, a.address,cc.Country, "
	+ "ss.statename_inenglish,dd.districtname_inenglish,a.city,a.pincode, "
	+ "a.verificationstatus,a.verifierremarks,tmp.tmptotal from Applicants a "
	+ " left join (select app.applicantid,count(*) as tmptotal from applications "
	+ "app group by app.applicantid ) tmp on a.id = tmp.applicantid inner "
	+ "join m_ApplicantRole apt on a.applicanttype = apt.id inner join "
	+ "m_Company c on a.companyid = c.id and c.companyname=:companyname "
	+ "left join m_Country cc on a.countryid = cc.id  "
	+ "left join Users u on a.id = u.applicantid and u.isactive= 'false' "
	+ "left join m_States ss on a.stateid = ss.statecode left join m_Districts "
	+ "dd on a.districtid = dd.districtcode where a.verificationstatus is "
	+ "null order by a.id asc",nativeQuery=true)
	List<Object[]> getDatavia_new_name(@Param("companyname") String companyname);
	
	
	@Modifying
	@Query("update ApplicantRegistration a set a.verifierremarks =:remarks ,a.verificationstatus =:statustobeset,a.modifiedbyip =:mip,a.verifiedby =:verifierid,a.verifiedbydesignation =:verifierdesignation,a.active='false'  where a.id=:id")
	int dataadding1(@Param("statustobeset")String statustobeset, @Param("remarks")String remarks, @Param("id") int id , @Param("mip") String mip,@Param("verifierid") int verifierid, @Param("verifierdesignation") String verifierdesignation );
    
	
	@Query("select a from ApplicantRegistration a \r\n" + 
			 "where a.verificationstatus =:applicant_status ")
	List<ApplicantRegistration> find_statuswise(@Param("applicant_status") String applicant_status);
	
	@Query("select a from ApplicantRegistration a \r\n" + 
			 "where a.verificationstatus is null ")
	List<ApplicantRegistration> find_fornew();
	
	
//Umesh Adding here For Pie Chart Data
	@Query("select a.id,apt.applicantrole ,Coalesce(c.companyname,i.InstituteName,''), "
	+ "u.username,u.email,u.mobile_number,u.designation, a.address,cc.Country, "
	+ "ss.statename_inenglish,dd.districtname_inenglish,a.city,a.pincode,a.verificationstatus,a.verifierremarks from "
	+ "ApplicantRegistration a left join ApplicantRole apt on a.applicanttype = apt.id "
	+ "left join CompanyRegistration c on a.companyid = c.id left join InstitutionRegistration i on "
	+ "a.institutionid = i.id left join Country cc on a.countryid = cc.id "
	+ "left join InternalUser u on a.id = u.applicantid left join States ss "
	+ "on a.stateid = ss.statecode left join Districts dd on "
	+ "a.districtid = dd.districtcode where a.verificationstatus is null order by a.id asc")
	List<Object[]> findapplicant_new_pidata();
	
	@Query("select a.id,apt.applicantrole ,Coalesce(c.companyname,i.InstituteName,''), "
	+ "u.username,u.email,u.mobile_number,u.designation, a.address,cc.Country, "
	+ "ss.statename_inenglish,dd.districtname_inenglish,a.city,a.pincode,a.verificationstatus,a.verifierremarks from "
	+ "ApplicantRegistration a left join ApplicantRole apt on a.applicanttype = apt.id "
	+ "left join CompanyRegistration c on a.companyid = c.id left join InstitutionRegistration i on "
	+ "a.institutionid = i.id left join Country cc on a.countryid = cc.id "
	+ "left join InternalUser u on a.id = u.applicantid left join States ss "
	+ "on a.stateid = ss.statecode left join Districts dd on "
	+ "a.districtid = dd.districtcode where a.verificationstatus=:status order by a.id asc")
	List<Object[]> findapplicant_statuswise_pidata(@Param("status") String status);

							
							
//Umesh adding here on 08-01-2020
	
	@Query("select count(*) from ApplicantRegistration a "
			+ "inner join ApplicantRole ar on a.applicanttype = "
			+ "ar.id and ar.applicantrole='Institution' left join "
			+ "InternalUser u on a.id = u.instituteid and "
			+ "u.isactive= 'true' inner join InstitutionRegistration i"
			+ " on a.institutionid = i.id and a.verificationstatus="
			+ "'Accepted' and i.InstituteName=:instname")
	Long getinst_accepted_records(@Param("instname") String instname);
	
	@Query("select count(*) from ApplicantRegistration a "
			+ "inner join ApplicantRole ar on a.applicanttype = "
			+ "ar.id and ar.applicantrole='Institution' left join "
			+ "InternalUser u on a.id = u.instituteid and "
			+ "u.isactive= 'false' inner join InstitutionRegistration i"
			+ " on a.institutionid = i.id and a.verificationstatus="
			+ "'Rejected' and i.InstituteName=:instname")
	Long getinst_rejected_records(@Param("instname") String instname);
	
	
	@Query("select count(*) from ApplicantRegistration a "
			+ "inner join ApplicantRole ar on a.applicanttype = "
			+ "ar.id and ar.applicantrole='Institution' left join "
			+ "InternalUser u on a.id = u.instituteid and "
			+ "u.isactive= 'false' inner join InstitutionRegistration i"
			+ " on a.institutionid = i.id and a.verificationstatus is null "
			+ "and i.InstituteName=:instname")
	Long getinst_new_records(@Param("instname") String instname);
	
							
	@Query(value="select a.id,i.InstituteName,u.email,u.mobile_number,u.designation, "
	+ "a.address,cc.Country,ss.statename_inenglish,dd.districtname_inenglish,"
	+ "a.city,a.pincode,a.verificationstatus,a.verifierremarks,tmp.tmptotal "
	+ "from Applicants a "
	+ "left join (select app.applicantid,count(*) as tmptotal from applications "
	+ " app group by app.applicantid ) tmp on a.id = tmp.applicantid "
	+ "inner join m_ApplicantRole apt on a.applicanttype = "
	+ "apt.id inner join m_Institution i on  a.institutionid = i.id and"
	+ "	a.verificationstatus=:status and i.InstituteName=:instname "
	+ " left join m_Country cc on a.countryid = cc.id  left join Users u "
	+ "on a.id = u.instituteid and u.isactive= 'true' "
	+ "left join m_States ss on a.stateid = ss.statecode left join "
	+ "m_Districts dd on a.districtid = dd.districtcode "
	+ "where a.verificationstatus=:status order by a.id asc",nativeQuery=true)
	List<Object[]> getaccepted_institution(@Param("instname") String instname,@Param("status") String status);
	
	
	@Query(value="select a.id,i.InstituteName,u.email,u.mobile_number,u.designation, "
	+ "a.address,cc.Country,ss.statename_inenglish,dd.districtname_inenglish,"
	+ "a.city,a.pincode,a.verificationstatus,a.verifierremarks,tmp.tmptotal "
	+ "from Applicants a "
	+ "left join (select app.applicantid,count(*) as tmptotal from applications app "
	+ "group by app.applicantid ) tmp on a.id = tmp.applicantid "
	+ "inner join m_ApplicantRole apt on a.applicanttype = "
	+ "apt.id inner join m_Institution i on  a.institutionid = i.id and"
	+ "	a.verificationstatus=:status and i.InstituteName=:instname "
	+ " left join m_Country cc on a.countryid = cc.id  "
	+ "left join Users u on a.id = u.instituteid and u.isactive= 'false' "
	+ "left join m_States ss on a.stateid = ss.statecode left join "
	+ "m_Districts dd on a.districtid = dd.districtcode "
	+ "where a.verificationstatus=:status order by a.id asc",nativeQuery= true)
	List<Object[]> getrejected_institution(@Param("instname") String companyname,@Param("status") String status);

	@Query(value="select a.id,i.InstituteName,u.email,u.mobile_number,u.designation, "
	+ "a.address,cc.Country,ss.statename_inenglish,dd.districtname_inenglish,"
	+ "a.city,a.pincode,a.verificationstatus,a.verifierremarks,tmp.tmptotal "
	+ "from Applicants a left join (select app.applicantid,count(*) as "
	+ "tmptotal from applications app"
	+ " group by app.applicantid ) tmp on a.id = tmp.applicantid inner join "
	+ "m_ApplicantRole apt on "
	+ "a.applicanttype = apt.id inner join m_Institution i on "
	+ " a.institutionid = i.id and a.verificationstatus is null "
	+ "and i.InstituteName=:instname left join "
	+ "m_Country cc on a.countryid = cc.id  left join Users u "
	+ "on a.id = u.instituteid and u.isactive= 'false' "
	+ "left join m_States ss on a.stateid = ss.statecode left join "
	+ "m_Districts dd on a.districtid = dd.districtcode "
	+ "where a.verificationstatus is null order by a.id asc",nativeQuery=true)
	List<Object[]> getnew_institution(@Param("instname") String companyname);							
	
//Adding here on 14-04-2020
	
	
	@Query("select count(*) from Application a where a.createdby =:id  "
			+ "and a.application_current_status<4 ")
	Long getcount_of_applicantsaved(@Param("id") int id);
	
	@Query("select count(*) from Application a where a.createdby =:id  "
			+ "and a.application_current_status>=4 ")
	Long getcount_of_applicantsubbmitted(@Param("id") int id);
	
	@Query("select u.id from CompanyRegistration c inner join "
			+ "ApplicantRegistration ap on c.id = "
			+ "ap.companyid and ap.id = :applicantid inner join "
			+ "InternalUser u on c.id= "
			+ "u.companyid and u.id=:id")
	List<Object[]>getdata_forcompany_admin(@Param("id") int id,
			@Param("applicantid") int applicantid);
	
	
	@Query("select u.id from InstitutionRegistration c inner join "
			+ "ApplicantRegistration ap on c.id = "
			+ "ap.institutionid and ap.id = :applicantid inner join "
			+ "InternalUser u on c.id= "
			+ "u.instituteid and u.id=:id")
	List<Object[]>getdata_forinstitute_admin(@Param("id") int id,
			@Param("applicantid") int applicantid);
	
	
}
