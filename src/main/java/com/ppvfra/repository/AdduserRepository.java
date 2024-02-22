package com.ppvfra.repository;

import java.util.List;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.InternalUser;


public interface AdduserRepository extends JpaRepository<InternalUser, Integer>
{
	@Query("Select a from InternalUser a where email=:email")
	List<InternalUser> emailcheck(@Param("email")  String email);
	
	@Query("Select a from InternalUser a where contactno=:mobileno")
	List<InternalUser> mobilecheck(@Param("mobileno")  String mobileno);
	
	@Query("Select a from InternalUser a where username=:username")
	List<InternalUser> usernamecheck(@Param("username")  String username);
	
	
	  @Query("Select a from InternalUser a where username=:username")
	  List<InternalUser> findcompanyidviauserid(@Param("username")  String username);
	  
	  
	  @Query("select u from InternalUser u where u.usertypeid "
	    		+ "in ( select mt.id from UserTypes mt where "
	    		+ "mt.type='Internal' ) order by u.username")
	    List<InternalUser> getactive_internalusers();
	 
 
}