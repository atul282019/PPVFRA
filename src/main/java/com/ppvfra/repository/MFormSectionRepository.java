package com.ppvfra.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 

 
import com.ppvfra.entity.MFormSection;
 
 

public interface MFormSectionRepository extends JpaRepository<MFormSection, Integer>{

	 @Query("Select mfs from MFormSection mfs where mfs.form_id =1 order by id asc")
		public List<MFormSection> getMFormSectiondata();
	 
	 @Query("Select mfs from MFormSection mfs where mfs.form_id =1 order by id asc")
		public List<Object[]> getMFormSection_f1();
		
		 @Query("Select mfs from MFormSection mfs where mfs.form_id =2 order by id asc")
			public List<Object[]> getMFormSection_f2();
			
			 @Query("Select mfs from MFormSection mfs where mfs.form_id =3 order by id asc")
				public List<Object[]> getMFormSection_f3();
		
		 @Query("Select mfs from MFormSection mfs where mfs.form_id =3 order by id asc")
			public List<MFormSection> getMFormSectiondata_f3();
		 
		 @Query("Select mfs from MFormSection mfs where mfs.form_id =2 order by id asc")
			public List<MFormSection> getMFormSectiondata_f2();
		 
		 @Query("Select mfs from MFormSection mfs  order by mfs.id asc")
			public List<MFormSection> getalldata();
		
 }