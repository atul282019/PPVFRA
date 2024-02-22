package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.UserAdd;

@Transactional
public interface UserAddRepository extends JpaRepository<UserAdd, Integer>
{
 @Modifying	
 @Query(value = "update users set password=:pass where id=:id",nativeQuery = true)
 int update(@Param("pass") String pass,@Param("id") int id);
	
	
	@Query(value="select id,email,firstname from users where users.id=:id",nativeQuery = true)
	List<Object[]> getemail_address(@Param("id") int id);
	
@Modifying	
 @Query(value = "update users set password=:pass where companyid=:id",nativeQuery = true)
 int update_comp(@Param("pass") String pass,@Param("id") int id);

@Modifying	
@Query(value = "update users set password=:pass where instituteid=:id",nativeQuery = true)
int update_inst(@Param("pass") String pass,@Param("id") int id);


@Query(value="select u.id,u.email,u.firstname from users u\r\n" + 
		"where u.applicantid in (select aa.id from applicants aa where aa.id=:id)",nativeQuery = true)
List<Object[]> getapplicantemail_address(@Param("id") int id);


@Modifying	
@Query(value = "update users set password=:pass where applicantid=:id",nativeQuery = true)
int update_applicant(@Param("pass") String pass,@Param("id") int id);

@Modifying	
@Query(value = "update users set password=:pass where id=:id",nativeQuery = true)
int update_forgetpass(@Param("pass") String pass,@Param("id") int id);


}