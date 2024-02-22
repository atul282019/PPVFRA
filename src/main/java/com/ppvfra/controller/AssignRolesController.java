package com.ppvfra.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.InternalUser;
import com.ppvfra.entity.Role;
import com.ppvfra.entity.User;
import com.ppvfra.entity.User_Role;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;

@Controller
public class AssignRolesController {
	
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	RoleRepository rolerepository;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogRepository activitylogrepository;

	@Autowired
	ActivityLogService activitylogservice;
	
	@RequestMapping(value = "/assignrole", method = RequestMethod.GET)
	public String getAssignRole(Model model,HttpServletRequest request) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				//System.out.println("Priting Loggin userdetail: " + userdeail.getId());
					} else {
				String username = principal.toString();
				//System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
		 List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	 
		User_Role saverole_para = new User_Role();
		model.addAttribute("saverole_para", saverole_para);
		//Umesh adding here on 20-12-2019 for showing active roles only
		//List<Role> certificates = rolerepository.findAll();
		List<Role> certificates = rolerepository.getroleslist();
		model.addAttribute("certificates", certificates);
		
		/*
		Umesh adding Comment here for showing only internal
		Users only Dated: 20-12-2019.
		*/
		// List<InternalUser> userbinding = adduserrep.findAll();
		//List<User> userbinding = userrepository.findAll();
		
		List<InternalUser> userbinding = userrepository.getinternalusers();
		model.addAttribute("userbinding", userbinding);
		
		List<Role> rolesbindingval = rolerepository.getRoledescription();
		model.addAttribute("rolesbindingval",rolesbindingval);
		
		
		 StringBuffer url=request.getRequestURL();
			String val=url.substring(url.lastIndexOf("/"),url.length());
			//System.out.println("PPPPP==="+val);
			  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
			 if(actd !=null)
			 {
				 Object actd_viewtrue=null;
				 Object actd_addtrue=null;
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
				act.setUrl("/assignrole");
				
				activitylogservice.save(act);
			
			   
			 //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
					List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
					model.addAttribute("rolespresent",rolespresent);
				//Umesh Adding ends here  
					
					
		return "admin/assign_role";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/addrolefromadmin", method=RequestMethod.POST)
	public String addroleforadmind(@Valid @ModelAttribute(value="saverole_para") User_Role adduser , 
	BindingResult bindingResult,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) 
	{
	//System.out.println("TRACE==="+adduser.toString());
	String username ="";
	User userdeail = null;

	 if (bindingResult.hasErrors()) 
	{/// Getting Logged in user
			int userid = 0;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				 username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
				 } else {
				 username = principal.toString();
				//System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
			//System.out.println("Priting Loggin user id: " + userid);
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	 
	 //Umesh Adding here on 20-12-2019 for active roles only 
	//List<Role> certificates =rolerepository.findAll();
	List<Role> certificates = rolerepository.getroleslist();
	model.addAttribute("certificates",certificates);

	List<User> userbinding = userrepository.findAll();
	model.addAttribute("userbinding",userbinding);
	
	String val="/assignrole";
	System.out.println("PPPPP==="+val);
	  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
	 if(actd !=null)
	 {
		 Object actd_viewtrue=null;
		 Object actd_addtrue=null;
		 model.addAttribute("actd",actd);
		 
	
		 if(actd.contains("View"))
			 {
			 actd_viewtrue ="valpresent";
	
			model.addAttribute("actd_viewtrue",actd_viewtrue);
			 
			 String val1 ="Assign Roles";
			 List<Object[]> accesstypeprocess_data = rolemodulerepository.getAccesstypes_Processdata(userid,val1);
		
				if(accesstypeprocess_data.contains("Add"))
				{
					actd_addtrue ="addpresent";
					model.addAttribute("actd_addtrue",actd_addtrue);	
				}
			
			 }
		 
	 }
	return "admin/assign_role";
	    
	   }else 
	   {
	   	//adduser.setRole_id(role_id);
	   
	   	


	//System.out.println("USER ID PRINT----"+adduser.getUser_id());
	
	String roleid[] = request.getParameterValues("role_id");

	//System.out.println("Printing useid="+roleid.length);


	HashSet s = new HashSet();

	 for (int j=0;j<roleid.length;j++) 
	 {	
	 Object principal =SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
	  
	 if(principal instanceof UserDetails) 
	 username = ((UserDetails)principal).getUsername(); 
	 Date date = new Date(); 
	 Timestamp ts = new Timestamp(date.getTime());
	 
	User userdetails = userrepository.getUserDetail(username);


	 User_Role ur = new User_Role();
	 ur.setRole_id(Integer.parseInt(roleid[j]));
	 ur.setUser_id(Integer.parseInt(request.getParameter("user_id")));
	 ur.setCreatedby(userdetails.getId()); 
	 ur.setCreatedon(ts);
	  s.add(ur);
	 
	
	 }
	 roleassignrepository.deleteRole(adduser.getUser_id());
	 List a= roleassignrepository.saveAll(s);
	 
	 if(a.size()>=1)
	 { 
		 System.out.println("aaaa===="+a.size());
		 redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
	 }
	 else
		{
			redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
		}
	   }
	 
	 
	 int userid = 0;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			 username = ((UserDetails) principal).getUsername();
			  userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId();
			// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
			 }
		
	 //System.out.println("GETTING USER NAME AS 255 = "+userdeail.getUsername());
	 ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is ="+dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("save");
		act.setUrl("/assignrole");
		
		activitylogservice.save(act);
	   
	return "redirect:/assignrole";



	}
}
