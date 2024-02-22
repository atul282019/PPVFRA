package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.ApplicationDocuments;

public interface ApplicationDocumentsRepository extends JpaRepository<ApplicationDocuments, Integer>
{
	@Query("select apd.id,apd.documenttypeid,apd.documentname,apd.documentpath ,dc.id,dc.document_desc\r\n" + 
			"from ApplicationDocuments apd ,DocumentChecklist dc\r\n" + 
			"where dc.id = apd.documenttypeid and dc.formtypeid=1 and apd.applicationid=:id")
	 List<Object[]> getattachmentdetails(@Param("id") int id);
	 
	 @Query("select apd.id,apd.documenttypeid,apd.documentname,apd.documentpath ,dc.id,dc.document_desc\r\n" + 
				"from ApplicationDocuments apd ,DocumentChecklist dc\r\n" + 
				"where dc.id = apd.documenttypeid and dc.formtypeid=2 and apd.applicationid=:id")
		 List<Object[]> getattachmentdetailsform2(@Param("id") int id);
	 
	 @Modifying
	 @Transactional
	 @Query(value="delete from application_documents where applicationid=:id",nativeQuery= true)
	 void updatedocuments(@Param("id") int id);
	 
	 @Modifying
	 @Transactional
	 @Query(value="delete  from application_candidate_variety_details where applicationid=:id",nativeQuery= true)
	 void updatecheckboxval(@Param("id") int id);
	 
	 
	 @Query("select apd.id,apd.documenttypeid,apd.documentname,apd.documentpath ,dc.id,dc.document_desc\r\n" + 
				"from ApplicationDocuments apd ,DocumentChecklist dc\r\n" + 
				"where dc.id = apd.documenttypeid and dc.formtypeid=3 and apd.applicationid=:id")
		 List<Object[]> f3_getattachmentdetails(@Param("id") int id);
		 
		 
	 

}
