package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.InternalUser;
import com.ppvfra.entity.User_Role;
import java.sql.Timestamp;

@Transactional
public interface RoleAssignRepository extends JpaRepository<User_Role, Long>
{
	
	@Modifying
	@Query(value = "insert into User_Role (id,user_id,role_id) values (:id, :user, :role)",
	  nativeQuery = true)
	void insertUser(@Param("id") Integer id,@Param("user") Integer name, @Param("role") Integer age);
	
	@Query("select max(id) from User_Role")
	Integer maxidsearch();
	
	@Modifying
	@Query(value = "insert into User_Role (id,user_id,role_id,createdby,createdon) values (:id, :user, :role, :createdby, :createdon)",
	  nativeQuery = true)
	void insertUser1(@Param("id") Integer id,@Param("user") Integer name, @Param("role") Integer age,@Param("createdby") Integer createdby,@Param("createdon") Timestamp createdon);
	
	
	@Query("select ur.role_id from User_Role ur where ur.user_id=:user_id and ur.role_id=:role_id")
	List<User_Role> search_for_uid_rid(@Param("user_id") Integer uid,@Param("role_id") Integer rid);
	
	@Query("select ur.role_id from User_Role ur where ur.user_id=:user_id")
	List<User_Role> search_no_of_roles(@Param("user_id") Integer uid);
	
	
	@Query("select ur.role_id from User_Role ur where ur.user_id=:user_id and ur.role_id=:role_id")
	List<User_Role> search_totalrolesonuserid(@Param("user_id") Integer uid,@Param("role_id") Integer rid);
	
	
	@Query("select ur.role_id from User_Role ur where ur.user_id=:user_id")
	List<User_Role> searchfor_roles(@Param("user_id") Integer uid);
	
	@Modifying
	@Query("delete from User_Role u where u.user_id=:userid")
	void deleteRole(@Param("userid") Integer userid);
	
	@Query("select ur.id from User_Role ur where ur.user_id=:user_id")
	List<User_Role> getroleids(@Param("user_id") Integer uid);
	
	@Query("select ur.id from  User_Role ur where ur.user_id=:id")
	 public String getUserrolevia_Userid(@Param("id") int id);
	
	@Query("select ur.id from  User_Role ur where ur.user_id=:id and ur.role_id=:roleid")
	 public String getUserrolevia_Userid_roleid(@Param("id") int id,@Param("roleid") int roleid);
	
	/*
	@Modifying
	@Query(value = "update User_Role set user_id=:user_id,role_id=:role_id,createdby=:createdby,createdon=:createdon where id=:id ",
	  nativeQuery = true)
	void initializeuserid(@Param("id") Integer id,@Param("user_id") Integer user_id, @Param("role_id") Integer role_id,@Param("createdby") Integer createdby,@Param("createdon") Timestamp createdon);
*/

   @Query("select u.id,u.username,ur.user_id from User_Role ur join InternalUser u on ur.user_id=u.id where ur.role_id=:role_id")
   public List<User_Role> getAllUsers(@Param("role_id") int role_id);
   
   @Query(value="select string_agg(mr.name,',') from User_Role ur inner join m_roles mr \r\n" + 
   		"on ur.role_id = mr.id where ur.user_id =:id ",nativeQuery = true)
   public List<Object[]> getrole_nameviauserid(@Param("id") int id);
}