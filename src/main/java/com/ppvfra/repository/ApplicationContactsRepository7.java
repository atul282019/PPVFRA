package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationContacts;
import com.ppvfra.entity.ApplicationContacts7;

public interface ApplicationContactsRepository7 extends JpaRepository<ApplicationContacts7, Integer> {

    @Transactional
	@Query("select a.id,a.applicationid,a.name,a.designation,"
			+ "a.nationality,a.address,n.Name,mc.Country from "
			+ "ApplicationContacts a left join Nationality n  "
			+ "on n.id = a.nationality inner join Country mc "
			+ "on a.countryid=mc.id "
			+ "where applicationid =:applicationid and a.contacttype=1")
	List<Object[]> getAllByApplicationId(@Param("applicationid") Integer applicationid);

	 @Transactional
	 //Commenting Here On 07-03-2020
	//@Query("select a.id,a.applicationid,a.name,a.address,a.pincode,a.city from ApplicationContacts a where applicationid =:applicationid and contacttype=2")
	 @Query("select a.id,a.applicationid,a.name,a.address,mc.Country,ms.statename_inenglish,md.districtname_inenglish,a.pincode,a.city from ApplicationContacts a inner join Country mc on a.countryid= mc.id left join States ms on a.stateid = ms.statecode left join Districts md on a.districtid= md.districtcode where applicationid =:applicationid and contacttype=2") 
	 List<Object[]> getAllByApplicationIdNaturalType(@Param("applicationid") Integer applicationid);

	
    @Transactional
	@Query(value="select a.id,a.applicationid,a.name as cname,a.designation,a.nationality,\r\n" + 
			"a.address,a.telephone_number,a.fax_number,a.emailid,n.Name,mc.Country ,ms.statename_inenglish,\r\n" + 
			"md.districtname_inenglish\r\n" + 
			"from application_contacts a left join m_nationality n on n.id = \r\n" + 
			"a.nationality inner join m_country mc on a.countryid= mc.id  \r\n" + 
			"left join m_states ms on a.stateid = ms.statecode \r\n" + 
			" left join m_districts md on a.districtid= md.districtcode where applicationid =:applicationid and contacttype=4",nativeQuery=true)
	List<Object[]> getAllApplicationContactById(@Param("applicationid") int applicationid);
	
	/*
	 * @Transactional
	 * 
	 * @Query("select a.id,a.applicationid,a.name," +
	 * "a.designation,a.nationality,a.address," +
	 * "a.telephone_number,a.fax_number,a.emailid," +
	 * "mc.Country,ms.statename_inenglish," + "md.districtname_inenglish from " +
	 * "ApplicationContacts3 a inner join Country mc on a.countryid= mc.id \r\n" +
	 * " left join States ms on a.stateid = ms.statecode \r\n" +
	 * " left join Districts md on a.districtid= md.districtcode where applicationid "
	 * + "=:applicationid and contacttype=3") List<Object[]>
	 * getAllApplicationContactByIdAndAgent(@Param("applicationid") int
	 * applicationid);
	 */
	
	@Modifying
	@Transactional
	@Query("delete from ApplicationContacts a where a.id =:id")
	public int updatedataform1sub2(@Param("id") int id); 
	
	 @Transactional
	 @Query("select a.id,a.applicationid,a.name,a.designation,a.telephone_stdcode,a.telephone_number,a.mobile_countrycode,\r\n" + 
	 		"	a.mobile_number,a.fax_stdcode,a.fax_number,a.emailid,a.contacttype,a.address,a.countryid,\r\n" + 
	 		"	a.stateid,a.districtid,a.city,a.pincode from ApplicationContacts a where a.contacttype=:contacttype and a.applicationid=:applicationid")
	 public ApplicationContacts getByContactTypeAndApplicationId(@Param("applicationid") int applicationid,@Param("contacttype") int contacttype);
	
	 @Transactional
	 @Query("select a.id from ApplicationContacts a where a.contacttype=:contacttype and a.applicationid=:applicationid")
	 public int  getContactIdByContactTypeAndApplicationId(@Param("applicationid") int applicationid,@Param("contacttype") int contacttype);
		
	 @Transactional
	 @Query("select a.id from ApplicationContacts a where a.contacttype=:contacttype and a.applicationid=:applicationid")
	 public int getContactIdByContactType(@Param("applicationid") int applicationid,@Param("contacttype") int contacttype);
		
}
