package com.ppvfra.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.ppvfra.entity.File_Composite;
import com.ppvfra.entity.File_No;

public interface FileNoRepository extends  JpaRepository<File_No, File_Composite>{
	
 
}
