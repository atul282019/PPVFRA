package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ppvfra.entity.Modules;

public interface ModulesRepository extends JpaRepository<Modules, Integer> {

	@Query("Select a from Modules a where a.moduletypeid=1")
	List<Modules> moduledatafetchmain();

	@Query("Select a.id, a.modulename from Modules a where a.moduletypeid=2  and a.parentid=:modulename")
	List<Modules> moduledatafetch(@Param("modulename") Integer modulename);

	@Query("Select a.id, a.modulename from Modules a where (a.moduletypeid=2 or a.moduletypeid=5) and a.parentid=:modulename")
	List<Modules> moduledatafetchwithProcess(@Param("modulename") Integer modulename);

	
	@Query("select a.id,a.modulename from Modules a where a.parentid<>0")
	List<Modules> moduledatafetchallfeilds();

	@Query("Select m from Modules m where parentid=:parentid")
	// public Modules getModulesList(@Param("parentid") int parentid);
	List<Modules> findAllByCourseId(@Param("parentid") int parentid);

	
	/*
	 * @Query("select distinct m.id,m.modulename,m.moduletypeid,m.parentid,m.linkname,m.orderofappearance,m.isparent,m.isdisplay,m.imagepath,m.active,m.module_icon\r\n"
	 * + "from Modules m left join ModuleTypes mt\r\n" +
	 * "on m.moduletypeid=mt.id and mt.id in (1,2)\r\n" +
	 * //"and m.active=true and mt.active=true\r\n" +
	 * "left join Role_Modules rm on m.id=rm.moduleid\r\n" +
	 * //"and rm.active=true\r\n" +
	 * "left join AccessTypes act on act.id=rm.accessid\r\n" +
	 * "left join Role r on r.id=rm.roleid\r\n" +
	 * //"and coalesce(r.status,'Active')='Active'\r\n" +
	 * "left join User_Role ur on ur.role_id=r.id\r\n" +
	 * "left join Users u on ur.user_id=u.id\r\n" + "where u.id=:userid")
	 * List<Object[]> findAllModulesByUserId(@Param("userid") int userid);
	 */
	 
	
	@Query("select distinct m.id,m.modulename,m.moduletypeid,m.parentid,m.linkname,m.orderofappearance,m.isparent,m.isdisplay,m.imagepath,m.active,m.module_icon\r\n" + 
			"	  from Modules m inner join ModuleTypes mt\r\n" + 
			"	  on m.moduletypeid=mt.id and mt.id in (1,2)\r\n" + 
			"	  left join Role_Modules rm on m.id=rm.moduleid\r\n" + 
			"	  left join AccessTypes act on act.id=rm.accessid\r\n" + 
			"	  left join Role r on r.id=rm.roleid\r\n" + 
			"	  left join User_Role ur on ur.role_id=r.id\r\n" + 
			"	  left join User u on ur.user_id = u.id where u.id=:userid")
	List<Object[]> findAllModulesByUserId(@Param("userid") int userid);
	
	
	@Query(" select  distinct m.id,m.modulename,m.moduletypeid,m.parentid,m.linkname,m.orderofappearance,m.isparent,m.isdisplay,m.imagepath,m.active,m.module_icon \r\n" + 
			" from Modules m where id in(  \r\n" + 
			"						select distinct p.parentid  from Modules p where id in (\r\n" + 
			"							select distinct  rm.moduleid from Role_Modules rm where roleid in(select distinct  ur.role_id from User_Role ur where user_id =:userid ))) order by m.orderofappearance")
	List<Object[]> findParentModulesByUserId(@Param("userid") int userid);
	
	
	

}
