package com.ppvfra.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.CompanyRegistration;
import com.ppvfra.entity.InstitutionRegistration;

@Transactional
public interface InstitutionRegistrationRepository extends  JpaRepository<InstitutionRegistration,Integer> {

	@Query("select i.id,i.InstituteName from InstitutionRegistration i where "
			+ "i.verificationstatus=:verificationstatus and i.active=:active order by "
			+ "i.InstituteName asc")
	List<InstitutionRegistration> institutionList(@Param("verificationstatus")  String verificationstatus, @Param("active")  Boolean active);
	
	
	@Modifying
	@Query("update InstitutionRegistration i set i.verificationremarks =:remarks ,i.verificationstatus =:statustobeset,i.modifiedbyip =:mip,i.verifiedby =:verifierid,i.verifiedbydesignation =:verifierdesignation,i.active='true'  where i.id=:id")
	int dataadding(@Param("statustobeset")String statustobeset, @Param("remarks")String remarks, @Param("id") int id , @Param("mip") String mip,@Param("verifierid") int verifierid, @Param("verifierdesignation") String verifierdesignation );

	
	@Query("select i.InstituteName,i.abbreviation,i.place,itp.typename,i.Author_Name,\r\n" + 
			"i.Author_Designation,i.Author_Address ,cc.Country,ss.statename_inenglish,\r\n" + 
			"dd.districtname_inenglish,i.Author_CityVillageTown,i.Author_Pincode,\r\n" + 
			"coalesce((i.author_mobilecountrycode||'-'||i.Author_Mobile),i.Author_Mobile) as mobile,\r\n" + 
			"coalesce((i.author_faxcountrycode ||'-'||i.Author_Fax ),i.Author_Fax) as fax, i.Author_Email,u.username\r\n" + 
			"from InstitutionRegistration i left join InstitutionTypes itp on i.Type = itp.id\r\n" + 
			"left join Country cc on i.Author_Countryid = cc.id\r\n" + 
			"left join States ss on i.author_statecode = ss.statecode\r\n" + 
			"left join Districts dd on i.author_districtcode = dd.districtcode\r\n" + 
			"left join InternalUser u on i.id = u.instituteid\r\n" + 
			"and u.usertypeid = 3 where i.id=:id")
	List<Object[]> findDetails(@Param("id") Integer id);
	
	@Modifying
	@Query("update InternalUser set isactive=true  where usertypeid=3 and instituteid=:id")
	void dataupdate(@Param("id") Integer id );
	
	@Modifying
	@Query("update InstitutionRegistration i set i.verificationremarks =:remarks ,i.verificationstatus =:statustobeset,i.modifiedbyip =:mip,i.verifiedby =:verifierid,i.verifiedbydesignation =:verifierdesignation,i.active='false'  where i.id=:id")
	int dataadding1(@Param("statustobeset")String statustobeset, @Param("remarks")String remarks, @Param("id") int id , @Param("mip") String mip,@Param("verifierid") int verifierid, @Param("verifierdesignation") String verifierdesignation );

	
	@Modifying
	@Query("update InternalUser set isactive=false  where usertypeid=3 and instituteid=:id")
	void dataupdate1(@Param("id") Integer id );
	
	@Query("Select a from InstitutionRegistration a where institutename=:institutename")
	List<InstitutionRegistration> checkInstituteName(@Param("institutename")  String institutename);
	
	@Query("Select a from InstitutionRegistration a where abbreviation=:abbreviation")
	List<InstitutionRegistration> checkAbbreviation(@Param("abbreviation")  String abbreviation);
	
	
	@Query("select a from InstitutionRegistration a \r\n" + 
			 "where a.verificationstatus =:institution_status ")
	List<InstitutionRegistration> getinsitute_data(@Param("institution_status") String institution_status);
	
	@Query("select a from InstitutionRegistration a \r\n" + 
			 "where a.verificationstatus is null ")
	List<InstitutionRegistration> get_institution_count_new();
	
	
	@Query(value="select c.id,c.author_email,c.author_name from m_institution c where c.id=:id",nativeQuery = true)
	List<Object[]> institution_email(@Param("id") int id);
	
} 
