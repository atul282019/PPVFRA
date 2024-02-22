package com.ppvfra.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppvfra.common.CommonUtil;
import com.ppvfra.common.EncryptionUtil;
import com.ppvfra.common.MailCommons;
import com.ppvfra.common.OtpSalt;
import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.InternalUser;
import com.ppvfra.entity.Role;
import com.ppvfra.entity.States;
import com.ppvfra.entity.User;
import com.ppvfra.entity.UserAdd;
import com.ppvfra.entity.User_Role;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AdduserRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.OfficeDetailsRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.StateRepository;
import com.ppvfra.repository.UserAddRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.repository.UserTypesRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.AdduserService;
import com.ppvfra.service.UserAddService;
import com.ppvfra.service.UserService;

@Controller
public class InternalUserController {
	
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	UserAddService useraddservice;
	
	@Autowired
	UserTypesRepository usertypesrepository;
	

	@Autowired
	AdduserRepository adduserrep;
	
	@Autowired
	UserService userservice;

	@Autowired
	AdduserService addusrservice;

	@Autowired
	StateRepository staterep;

	@Autowired
	RoleRepository addrolerep;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	OfficeDetailsRepository officedetailsrepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	UserAddRepository useraddrepository;
	
	
	
	@RequestMapping(value = "/getusermanagement", method = RequestMethod.GET)
	public String getUserManagement(Model model,HttpServletRequest request) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail  = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				System.out.println("Priting Loggin userdetail: " + userdeail.getId());
				} else {
				String username = principal.toString();
				System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
			List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	 
		//List<InternalUser> internal = adduserrep.findAll();
	  List<InternalUser> internal = adduserrep.getactive_internalusers();
		model.addAttribute("internal", internal);
		
		StringBuffer url=request.getRequestURL();
		String val=url.substring(url.lastIndexOf("/"),url.length());
		//System.out.println("PPPPP==="+val);
		  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
		 if(actd !=null)
		 {
			 Object actd_viewtrue=null;
			 Object actd_editvaluetrue = null;
			 
			 model.addAttribute("actd",actd);
			 
		//  System.out.println("PRINTING contains"+actd.contains("View")); 
			
		   if(actd.contains("View"))
				 {
				 actd_viewtrue ="valpresent";
	//	System.out.println("PRINING TRUEEEEE"+actd_viewtrue); 
				
				model.addAttribute("actd_viewtrue",actd_viewtrue);
		 		
				 }
		   
		   
			 
		 }
		 
		 ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("view");
			act.setUrl("/usermanagement");
			
			activitylogservice.save(act);
		   
		   //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
		   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
			//Umesh Adding ends here 
				
				
Date date= new Date();
			    
			    long time = date.getTime();
			        System.out.println("Time in Milliseconds: " + time);
			    
			    Timestamp ts = new Timestamp(time);
			    System.out.println("Current Time Stamp: " + ts);
			    
			    model.addAttribute("time_obt1",ts);	
		   
		return "admin/user_management";
	}

	@RequestMapping(value = "/addnewuser", method = RequestMethod.GET)
	public String addNewUser(Model model) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail =null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				System.out.println("Priting Loggin user: " + username);
			} else {
				String username = principal.toString();
				System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
				
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	 
	  UserAdd adduserinternalmethod = new UserAdd();

		model.addAttribute("adduserinternalmethod", adduserinternalmethod);

		//List<Role> certificates = addrolerep.findAll();
		List<Role> certificates = addrolerep.getroleslist();
		model.addAttribute("certificates", certificates);

		// List<States> office_state = staterep.getinfopart();
		/*
		 * List<States> office_state = staterep.findAll();
		 * model.addAttribute("office_state", office_state);
		 */
		
		List<Object[]> office_state = officedetailsrepository.find_distinct_states();
		if(office_state != null)
		model.addAttribute("office_state", office_state);
		
		
		 //Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
		List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
		//Umesh Adding ends here 
		
		return "admin/add_user";
	}
	
	@RequestMapping(value="/addusersadminmethod", method=RequestMethod.POST)
	public String addusersadminmethod(@Valid @ModelAttribute(value="adduserinternalmethod")
	UserAdd adduserinternalmethod , BindingResult bindingResult,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) 
	{
	System.out.println("TRACE==="+adduserinternalmethod.toString());
	String username="";

	 if (bindingResult.hasErrors()) 
	{
	List<Role> certificates =addrolerep.findAll();
	System.out.println("TRACE GETTING IN BINDING RESULT ERROR");
	model.addAttribute("certificates",certificates);
	
	List<Object[]> office_state = officedetailsrepository.find_distinct_states();
	if(office_state != null)
	model.addAttribute("office_state", office_state);
	
	int userid = 0;
	User userdeail =  null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			 username = ((UserDetails) principal).getUsername();
			userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId();
			// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
		 } else {
			 username = principal.toString();
			System.out.println("Priting else Loggin user: line 271 " + username);
		}
		/// end Getting Logged in user
		System.out.println("Priting Loggin user id: line 274 " + userid);
		
  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
  model.addAttribute("modulelist", modulelist);

  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
  model.addAttribute("modulelist2", modulelist2);
  
  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
	model.addAttribute("usrname_header_val",usrname_header_val);
	List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
	model.addAttribute("rolespresent",rolespresent);
	
  
  
	return "admin/add_user";
	    
	   }else 
	   {
	   	List<Role> certificates =addrolerep.findAll();
	model.addAttribute("certificates",certificates);
			/*
			 * List<States> office_state = staterep.findAll();
			 * model.addAttribute("office_state",office_state);
			 */
	
	List<Object[]> office_state = officedetailsrepository.find_distinct_states();
	if(office_state != null)
	model.addAttribute("office_state", office_state);
	   	
	   	addusrservice.toString();
	   	String nn=adduserinternalmethod.getFirstname();
	   	adduserinternalmethod.setName(nn);
	   	
	   	Object principal =SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 

	   	if(principal instanceof UserDetails) 
	username = ((UserDetails)principal).getUsername(); 
	 Date date = new Date(); 
	 Timestamp ts = new Timestamp(date.getTime());
	 User userdetails = userrepository.getUserDetail(username);

	 
//--------------------- ROLE PORTION STARTS ------
	
	 HashSet<User_Role> s2= new HashSet<User_Role>();
	
	 
	 String rol[] = request.getParameterValues("role");
	 
	 System.out.println("TRACE FOR ROLES ---- "+rol.length);
 
	 
	 for(int r= 0 ; r<rol.length; r++)
	 {
		 User_Role ur =new User_Role();
	 //System.out.println("TRACE FOR role printing =="+request.getParameter("id"));
	  {
		 System.out.println("Trace inside NO ID ROLES"); 
		 ur.setRole_id(Integer.parseInt(rol[r]));
		 ur.setCreatedby(userdetails.getId());
		 ur.setCreatedon(ts); 
	 
	 s2.add(ur);
	   }
	 System.out.println("TRACE =============="+s2);
	 adduserinternalmethod.setUserrole(s2);
	 }
	 
	 System.out.println("Trace for adduser==>>"+adduserinternalmethod.toString());
	 
	 int typeid = usertypesrepository.getIdByType("Internal");
	 System.out.println("Setting Type ID ====="+typeid);
	 adduserinternalmethod.setUsertypeid(typeid);

//Umesh Adding here For Password Setting
	 //06-01-2020
	 
	   BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12
	 String encodedPassword = encoder.encode(request.getParameter("password"));
	
	 adduserinternalmethod.setPassword(encodedPassword);
    
     
     //System.out.println("Printing the password encrypted="+encodedPassword);
     
	 
	 
//Umesh Adding ends Here------	 
	Boolean success= useraddservice.save(adduserinternalmethod);
	 //adduserrep.save(adduserinternalmethod);
	if (success)
	{
		redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
	}
	else
	{
		redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
	}
	 
	   }

	return "redirect:/getusermanagement";
	}
	
	@RequestMapping(value = "/edituser/{id}", method = RequestMethod.GET)
	public ModelAndView editByUserId(@PathVariable(name = "id") int id,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("admin/edit_usermanagement");
		// Optional<InternalUser> adduser= adduserrep.findById(id);

		/// Getting Logged in user
				int userid = 0;
				User userdeail =  null;
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if (principal instanceof UserDetails) {
						String username = ((UserDetails) principal).getUsername();
						userdeail = userrepository.getUserDetail(username);
						userid = userdeail.getId();
						// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
					 } else {
						String username = principal.toString();
						System.out.println("Priting else Loggin user: " + username);
					}
					/// end Getting Logged in user
					System.out.println("Priting Loggin user id: " + userid);
					
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  mav.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  mav.addObject("modulelist2", modulelist2);
			 


		UserAdd edituserinternalmethod = useraddservice.get(id);
	 /*
	 //Umesh Adding here on 06-01-2020
		mav.addObject("editpassword",edituserinternalmethod.getPassword());
	//Umesh Adding Ends Here	
		*/
		mav.addObject("edituserinternalmethod",edituserinternalmethod);

		//List<Role> certificates = addrolerep.findAll();
		List<Role> certificates = addrolerep.getroleslist();
		mav.addObject("certificates", certificates);

		/*
		 * List<States> office_state = staterep.findAll(); mav.addObject("office_state",
		 * office_state);
		 */
		
		List<Object[]> office_state = officedetailsrepository.find_distinct_states();
		if(office_state != null)
			mav.addObject("office_state", office_state);
		
		List<User_Role> listofids= roleassignrepository.getroleids(id);
		mav.addObject("listofids",listofids);
		
		
		
		//StringBuffer url=request.getRequestURL();
		String val="/getusermanagement";
		//System.out.println("PPPPP==="+val);
		  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
		 if(actd !=null)
		 {
			 Object actd_viewtrue=null;
			 Object actd_editvaluetrue = null;
			 
			 mav.addObject("actd",actd);
			 
		//  System.out.println("PRINTING contains"+actd.contains("View")); 
			
		   if(actd.contains("View"))
				 {
				 actd_viewtrue ="valpresent";
	//	System.out.println("PRINING TRUEEEEE"+actd_viewtrue); 
				
				 mav.addObject("actd_viewtrue",actd_viewtrue);
		 		
				 }
		   
		   
			 
		 }
		 
		 
			//Added Here For Name And Role Showing in header
			
		   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		   mav.addObject("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				mav.addObject("rolespresent",rolespresent);
			//Umesh Adding ends here 
			
				
		 
		 ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(req.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("view");
			act.setUrl("/edituser");
			
			activitylogservice.save(act);

		mav.setViewName("admin/edit_usermanagement");
		return mav;
	}
	//****User Edit Portion Starts *****
	@RequestMapping(value="/edituseradminmethod", method=RequestMethod.POST)
	public String edituseradminmethod(@Valid @ModelAttribute(value="edituserinternalmethod")
	UserAdd adduserinternalmethod , BindingResult bindingResult,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) 
	{
	System.out.println("TRACE==="+adduserinternalmethod.toString());
	String username="";

	 if (bindingResult.hasErrors()) 
	{
	List<Role> certificates =addrolerep.findAll();
	System.out.println("TRACE GETTING IN BINDING RESULT ERROR");
	model.addAttribute("certificates",certificates);
			/*
			 * List<States> office_state = staterep.findAll();
			 * model.addAttribute("office_state",office_state);
			 */
	
	List<Object[]> office_state = officedetailsrepository.find_distinct_states();
	if(office_state != null)
	model.addAttribute("office_state", office_state);
	
	return "admin/add_user";
	}
else{
		List<Role> certificates =addrolerep.findAll();
		model.addAttribute("certificates",certificates);
			/*
			 * List<States> office_state = staterep.findAll();
			 * model.addAttribute("office_state",office_state);
			 */
		
		List<Object[]> office_state = officedetailsrepository.find_distinct_states();
		if(office_state != null)
		model.addAttribute("office_state", office_state);
		
		addusrservice.toString();
		String nn=adduserinternalmethod.getFirstname();
		adduserinternalmethod.setName(nn);
		Object principal =SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        if(principal instanceof UserDetails) 
		username = ((UserDetails)principal).getUsername(); 
		Date date = new Date(); 
		Timestamp ts = new Timestamp(date.getTime());
		User userdetails = userrepository.getUserDetail(username);

	 
		//--------------------- ROLE PORTION STARTS ------
	
		HashSet s2= new HashSet();
	   String rol[] = request.getParameterValues("role");
	   System.out.println("TRACE FOR ROLES ---- "+rol);
     for(int r= 0;r<rol.length; r++)
     {
		User_Role ur =new User_Role();
		System.out.println("TRACE FOR role printing =="+request.getParameter("id"));
		System.out.println("Trace inside id ID PRESENT FOR ROLES "+request.getParameter("id")); 
		int userid_present =Integer.parseInt(request.getParameter("id")); 
		String userrole_id="";
		 if( adduserinternalmethod.getId() !=0 )
		   {
					/*
					 * if(rol.length == 1) { userrole_id =
					 * roleassignrepository.getUserrolevia_Userid(adduserinternalmethod.getId());
					 * ur.setId(Long.parseLong(userrole_id)); ur.setUser_id(userid_present);
					 * ur.setRole_id(Integer.parseInt(rol[r]));
					 * ur.setCreatedby(userdetails.getId()); ur.setCreatedon(ts); s2.add(ur);
					 * adduserinternalmethod.setUserrole(s2); } else
					 */ 
				 if(r==0)
				 roleassignrepository.deleteRole(userid_present);
				 
			 ur.setUser_id(userid_present);
			 ur.setRole_id(Integer.parseInt(rol[r]));
			 ur.setCreatedby(userdetails.getId());
			 ur.setCreatedon(ts);
			 ur.setModifiedby(userid_present);
			 ur.setModifiedon(ts);
			 s2.add(ur);
			
			 
		}  
	   adduserinternalmethod.setUserrole(s2);
     }
		int typeid = usertypesrepository.getIdByType("Internal");
		adduserinternalmethod.setUsertypeid(typeid);
		Boolean success=useraddservice.save(adduserinternalmethod);
		if (success)
		{
			redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
		}
		else
		{
			redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
		}
		
		 ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
		    act.setActivityby(username);
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/edituser");
			
			activitylogservice.save(act);
		   
}

	return "redirect:/getusermanagement";
	}			

///********Ends Here *********************
	
	
	@RequestMapping(value="/resetpassword/{id}", method = RequestMethod.GET)
	public String resetpassword(@PathVariable("id") int id, HttpServletRequest request) 
	{	
		String username="";
		Object principal =SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        if(principal instanceof UserDetails) 
		username = ((UserDetails)principal).getUsername(); 
		
     String resetpass="ppvfra@2020";
     String mailaddress="";
     String mailusername="";
	 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12
	 String encodedPassword = encoder.encode(resetpass);
	int a= useraddrepository.update(encodedPassword, id);
	if(a==1)
	{
		List<Object[]> mail= useraddrepository.getemail_address(id);
		if(mail !=null)
		{
			Object[] r= (Object[]) mail.get(0);
			System.out.println("Printing the object val="+String.valueOf(r[0])+""+String.valueOf(r[1]));
			mailaddress= String.valueOf(r[1]);
			mailusername= String.valueOf(r[2]);
			System.out.println("Printing the mailaddress="+mailaddress);
		}
	try{
		send_resetpassword_mail(resetpass,mailaddress,mailusername);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
		 ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
		    act.setActivityby(username);
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("reset password");
			act.setUrl("/resetpassword");
			
			activitylogservice.save(act);
		   
 

	return "redirect:/getusermanagement";
	}
	
	boolean send_resetpassword_mail(String pass,String mailadd,String username ) throws IOException {
		boolean valreturn = false;

		try {
			new Thread(new Runnable() {
			
				public void run() {
					try {
						System.out.println("Printing MIME RESET PASSWORD");
						MimeMessage mimeMessage = new MailCommons().mimeMessage();
						mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailadd));
						System.out.println("Printing mime message 2");
						mimeMessage.setSubject("Password Reset");

						StringBuilder sb = new StringBuilder();
						System.out.println("Printing mime message3");
						sb.append("Dear " + username + ",");
						//sb.append("<br/><br/>Ms " + orgname
						sb.append("<br/><br/>Your reset Password is:   "+pass);
						sb.append("<br/><br/>Thank You<br/>Team PPVFRA");

						mimeMessage.setContent(sb.toString(), "text/html; charset=utf-8");
						
						Transport.send(mimeMessage);
						System.out.println("Printing mime message7");
						System.out.println("Printing at last =="+mimeMessage);

					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}

			}).start();

		} catch (Exception e) {
			valreturn = false;
		}
		return valreturn;
	}
}
