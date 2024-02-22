package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.DocumentChecklist;

public interface DocumentChecklistRepository extends JpaRepository<DocumentChecklist,Integer>{

 public static boolean filesizechking(String docname,long filesizeofdoc) {
		boolean value = false; 
			long sizetocheck = 500;
		//	int idchk = Integer.parseInt(docname);
	
long actualsize= filesizeofdoc/1024;
			System.out.println("Actualsize chking == "+actualsize);
			if(sizetocheck< actualsize) {
			value=false;
			System.out.println("Inside True----YAAH---");
		}
		else 
		{
			if(sizetocheck>= actualsize)
					value=true;
			System.out.println("Inside False---YAAH----");
		}
		//}//return query.getSingleResult();
	System.out.println("Printing the value comming  --- "+value);
		return value;
		/*	} */
		/*catch (Exception e) {
	System.out.println("Inside Excption for docchk.......Script");
		return true;
}*/
}
	 
	 
	 @Query("select d.id,d.formtypeid,d.document_desc,"
	 		+ "d.attachment_maximum_size_in_kb,f.formtypename,"
	 		+ "d.is_mandatory,d.attachment_allow_extension  "
	 		+ "from DocumentChecklist d left join FormTypes f "
	 		+ "on d.formtypeid = f.id where formtypeid=1")
	 List<Object[]> getformdetails();
	 
	 @Query("select d.id,d.formtypeid,d.document_desc,"
		 		+ "d.attachment_maximum_size_in_kb,f.formtypename,"
		 		+ "d.is_mandatory,d.attachment_allow_extension  "
		 		+ "from DocumentChecklist d left join FormTypes f "
		 		+ "on d.formtypeid = f.id where formtypeid=2")
		 List<Object[]> getform2details();
	 
	 @Query("select d.id,d.formtypeid,d.document_desc, \r\n" + 
	 		"d.attachment_maximum_size_in_kb,f.formtypename,\r\n" + 
	 		"d.is_mandatory,d.attachment_allow_extension,ad.documentname,ad.documentpath  \r\n" + 
	 		"from DocumentChecklist d left join FormTypes f \r\n" + 
	 		"on d.formtypeid = f.id \r\n" + 
	 		"left join ApplicationDocuments ad on d.id= ad.documenttypeid \r\n" + 
	 		"and ad.applicationid=:applicationid \r\n" + 
	 		" where d.formtypeid=1")
		 List<Object[]> getdetails_applicationid(@Param("applicationid") int applicationid);	 
		 
		 @Query("select d.id,d.formtypeid,d.document_desc, \r\n" + 
			 		"d.attachment_maximum_size_in_kb,f.formtypename,\r\n" + 
			 		"d.is_mandatory,d.attachment_allow_extension,ad.documentname,ad.documentpath  \r\n" + 
			 		"from DocumentChecklist d left join FormTypes f \r\n" + 
			 		"on d.formtypeid = f.id \r\n" + 
			 		"left join ApplicationDocuments ad on d.id= ad.documenttypeid \r\n" + 
			 		"and ad.applicationid=:applicationid \r\n" + 
			 		" where d.formtypeid=2")
				 List<Object[]> getdetails_applicationidform2(@Param("applicationid") int applicationid);	 
			 
		 
		 @Query("select d.id,d.formtypeid,d.document_desc,"
			 		+ "d.attachment_maximum_size_in_kb,f.formtypename,"
			 		+ "d.is_mandatory,d.attachment_allow_extension  "
			 		+ "from DocumentChecklist d left join FormTypes f "
			 		+ "on d.formtypeid = f.id where formtypeid=3")
			 List<Object[]> getformdetailsf3();
			 
			 
			 @Query("select d.id,d.formtypeid,d.document_desc, \r\n" + 
				 		"d.attachment_maximum_size_in_kb,f.formtypename,\r\n" + 
				 		"d.is_mandatory,d.attachment_allow_extension,ad.documentname,ad.documentpath  \r\n" + 
				 		"from DocumentChecklist d left join FormTypes f \r\n" + 
				 		"on d.formtypeid = f.id \r\n" + 
				 		"left join ApplicationDocuments ad on d.id= ad.documenttypeid \r\n" + 
				 		"and ad.applicationid=:applicationid \r\n" + 
				 		" where d.formtypeid=3")
					 List<Object[]> getdetails_applicationidf3(@Param("applicationid") int applicationid);	 
				 
	
}
