package com.ppvfra.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Role_Modules;

public interface Role_ModulesRepository extends JpaRepository<Role_Modules, Integer>{
	
	@Transactional
	@Modifying
	@Query("delete from Role_Modules m where m.roleid=:roleid and m.parentmoduleid=:moduleid")
	void deleteAllByRoleid(@Param("roleid") Integer roleid,@Param("moduleid") Integer moduleid);
	
	@Query("select rm.moduleid||'-'||rm.accessid as data from Role_Modules rm where rm.roleid=:roleid and rm.parentmoduleid=:parentmoduleid") 
	public List<Role_Modules> getPrevligedata(@Param("roleid") int role,@Param("parentmoduleid") int parentmoduleid);
	
	@Query("select rm.moduleid||'-'||rm.accessid as data from Role_Modules rm")
	public List<Role_Modules> getPrevligedata_all();
	
	
	/*
	  @Query("select rm.id,rm.roleid,rm.moduleid,rm.accessid,(select \r\n" + 
	  		"mac.accessdesc from AccessTypes mac where rm.accessid= mac.id ) from Role_Modules rm "
	  + "where roleid in ( select ur.role_id from InternalUser u left join "
	  + "User_Role ur on u.id = user_id  where u.id=:userid ) and "
	  + "rm.moduleid in( select mm.id from Modules mm where "
	  + "mm.linkname=:linkname ) order by rm.roleid,rm.moduleid,"
	  + "rm.accessid") 
	  public List<Object[]> getPrevligedata_accesstypes(@Param("userid") int userid,@Param("linkname") String linkname);
	 */

	 @Query("select distinct (select mac.accessdesc from AccessTypes mac where rm.accessid= mac.id ) from Role_Modules rm "
		  + "where roleid in ( select ur.role_id from InternalUser u left join "
		  + "User_Role ur on u.id = user_id  where u.id=:userid ) and "
		  + "rm.moduleid in( select mm.id from Modules mm where "
		  + "mm.linkname=:linkname )") 
		  public List<Object[]> getPrevligedata_accesstypes(@Param("userid") int userid,@Param("linkname") String linkname);
		 
		  
		  @Query("select distinct (select mac.accessdesc from AccessTypes mac where rm.accessid= mac.id ) from Role_Modules rm "
				  + "where roleid in ( select ur.role_id from InternalUser u left join "
				  + "User_Role ur on u.id = user_id  where u.id=:userid ) and "
				  + "rm.moduleid in( select mm.id from Modules mm where "
				  + "mm.modulename=:mname )") 
				  public List<Object[]> getAccesstypes_Processdata(@Param("userid") int userid,@Param("mname") String mname);
				  
		
		//@Query(value="select distinct m.modulename,mac.accessdesc "
		  @Query(value="select distinct m.modulename"
		    + " from m_modules m  "
			+  "inner join Role_Modules rm on m.id=rm.moduleid  "
			+  "inner join user_role ur on rm.roleid = ur.role_id"
			+  " inner join users u on ur.user_id= u.id and u.id=:userid "
			+  "inner join m_AccessTypes mac on rm.accessid = mac.id "
			+  "where m.parentid=40 and m.moduletypeid=5 and "
			+ "m.linkname is null",nativeQuery = true)
		public List<Object[]> getProcessnamesviauserid(@Param("userid") Integer userid);
		
/* 
  Umesh Adding here on 28-12-2019 ------
* Added here for getting button prevliges according to 
* module name 
*/
		@Query(value="select distinct mac.accessdesc"
			    + " from m_modules m  "
				+  "inner join Role_Modules rm on m.id=rm.moduleid  "
				+  "inner join user_role ur on rm.roleid = ur.role_id"
				+  " inner join users u on ur.user_id= u.id and u.id=:userid "
				+  "inner join m_AccessTypes mac on rm.accessid = mac.id "
				+  "where m.parentid=40 and m.moduletypeid=5 and "
				+ "m.linkname is null and m.modulename=:modulename ",nativeQuery = true)
			public List<Object[]> getbuttonviauserid_module(@Param("userid") Integer userid ,@Param("modulename") String modulename);
			
@Transactional
@Modifying
@Query(value="delete from role_modules where roleid=:role and  parentmoduleid=:module",nativeQuery = true)
int remove_by_role_module_id(@Param("role") Integer role, @Param("module") Integer module); 



   @Query(value="select mr.name from m_roles mr "
  		+ "inner join user_role ur on mr.id = "
  		+ "ur.role_id inner join users u on "
  		+ "ur.user_id=u.id and u.id=:userid",
  		nativeQuery=true)
 public List<Object[]> get_logged_in_role(@Param("userid") int userid);
				 
}
