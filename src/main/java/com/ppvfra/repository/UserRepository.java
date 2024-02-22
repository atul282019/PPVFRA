package com.ppvfra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.InternalUser;
import com.ppvfra.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>
{
    //Optional<User> findByEmail(@Param("email") String email);
	
	 @Query("Select u from User u where username=:username and isactive=true")
	 Optional<User>findByEmail(@Param("username") String username);

	/*
	 * @Query("Select u from User u where username=:email") public User
	 * getUserDetail(@Param("email") String email);
	 */
    @Query("Select u from User u where username=:username")
	public User getUserDetail(@Param("username") String username);
    
    @Query("Select u.applicantid from InternalUser u where u.id=:userid")
    List<Object[]> getApplicantIdByUserId(@Param("userid") int userid);
    
    @Query("Select u.designation from InternalUser u where u.id=:id")
	public String getAllDetailsOfUser(@Param("id") int id);
    
    @Query("select c.companyname  from InternalUser u left join CompanyRegistration c on u.companyid = c.id where u.id=:id ")
    public String getCompanyName(@Param("id") int id);
    
   // @Query("select ut.type from InternalUser u left join  UserTypes ut on u.usertypeid = ut.id where u.id =:userid")
   @Query("select mr.name from InternalUser u \r\n" + 
   		"left join User_Role ur on u.id= ur.user_id  \r\n" + 
   		"inner join Role mr on ur.role_id = mr.id \r\n" + 
   		"where u.id =:userid") 
	List<Object[]> getUsertypeviauserid(@Param("userid") int userid);
	
	@Query("select u.username from InternalUser u where u.instituteid=:id")
    public String getUser_Name(@Param("id") int id);
    
    @Query("select u.id from InternalUser u where u.usertypeid = 3 and u.instituteid=:id")
    public String getUseridvia_Instituteid(@Param("id") int id);
    
    @Query("select u.username from InternalUser u where u.companyid=:id")
    public String getUserName(@Param("id") int id);
    
    @Query("select u.id from InternalUser u where u.usertypeid = 2 and u.companyid=:id")
    public String getUseridvia_Companyid(@Param("id") Integer id);
    
    @Query("select u.id from InternalUser u where u.usertypeid = 4 and u.applicantid=:id")
    public String getUseridvia_Applicantid(@Param("id") Integer id);
    
    @Query("select u.designation from InternalUser u where u.id=:id")
    public String getDesignation_viaid(@Param("id") int id);
    
    @Query("select coalesce(u.office_id,0) from InternalUser u where u.id=:id")
    public int getOffice_id(@Param("id") int id);
    
    
    
    @Query("select u from InternalUser u where u.usertypeid "
    		+ "in ( select mt.id from UserTypes mt where "
    		+ "mt.type='Internal' ) order by u.username")
    List<InternalUser> getinternalusers();
    
    @Query(value="select distinct u.username,u.id   from user_role ur \r\n" + 
    		"inner join users u on ur.user_id= u.id  and u.username is not null \r\n" + 
    		"where ur.role_id=:role_id",nativeQuery = true)
    List<Object[]> getuserviaroleid(@Param("role_id") Integer role_id);
  
    

    
//Adding For Institution ------ 08-01-2020
    
 @Query("select i.InstituteName from  InstitutionRegistration i "
 + "where i.id in (select u.instituteid from InternalUser u where u.id=:id)")
    public String getInstitution_name(@Param("id") int id);

//Adding For Applicant ----------14-04-2020
 @Query("select u.username  from InternalUser u left join "
 + "ApplicantRegistration a on u.applicantid = a.id where u.id=:id ")
 public String getApplicant_name(@Param("id") int id);
 
@Query(value="select u.username,u.firstname,u.email,u.mobile_number,u.designation from users u where u.id=:userid",nativeQuery = true)
List<Object[]> getprofiledata(@Param("userid") int userid);


@Query(value="select u.username,u.firstname,u.email,u.mobile_number,u.designation,u.id from users u inner join applications a on u.applicantid =a.applicantid and a.id=:appid",nativeQuery = true)
List<Object[]> getprofiledata_applicant(@Param("appid") int appid);

//Umesh Commenting the below query on 18-01-2020
//FOR HEADER SHOWING COMPANY/INSTITUTION NAME
//@Query("select u.firstname from InternalUser u where u.id=:id")
@Query(value="select u.id,coalesce(c.companyname,i.institutename,\r\n" + 
		"(select coalesce(cc.companyname,mi.institutename) from users uu \r\n" + 
		"inner join applicants a on uu.applicantid =a.id \r\n" + 
		"left join m_company cc on a.companyid = cc.id\r\n" + 
		"left join m_institution mi on a.institutionid = mi.id where uu.id=:id) \r\n" + 
		" ,u.username) app_name\r\n" + 
		",coalesce(c.author_name,i.author_name,u.username) applicant_name ,u.usertypeid\r\n" + 
		"from users u left join m_company c on u.companyid =c.id \r\n" + 
		"left join m_institution i on u.instituteid = i.id\r\n" + 
		"left join applicants a on u.applicantid = a.id\r\n" + 
		"where u.id=:id",nativeQuery=true)
public List<Object[]> getUser_Nameviaid(@Param("id") int id);

@Query(value="select coalesce((select u.id from Users u "
+ "where u.email=:mail and u.email is not null and"
+ " u.isactive= true),0)",nativeQuery = true)
public int getdetailvia_mail(@Param("mail") String mail);

//Adding Ends Here ------ 

 
}