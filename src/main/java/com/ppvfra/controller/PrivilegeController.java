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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppvfra.entity.AccessTypes;
import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.Modules;
import com.ppvfra.entity.Role;
import com.ppvfra.entity.Role_Modules;
import com.ppvfra.entity.User;
import com.ppvfra.repository.AccessTypesRepository;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;

@Controller
public class PrivilegeController {
	
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	UserRepository userrepository;
	
	

	@Autowired
	AccessTypesRepository accesstyperepository;
	
	@Autowired
	RoleRepository addrolerep;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	@RequestMapping(value = "/roleprivilege", method = RequestMethod.GET)
	public String getPrivilege(Model model,HttpServletRequest request) {
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
	 
	  //Umesh Adding Here On 20-12-2019 for active roles only
	  //List<Role> rolefinding = addrolerep.findAll();
	    List<Role> rolefinding = addrolerep.getactive_roleslist();
		model.addAttribute("rolefinding", rolefinding);

		// List<Modules> modulecontrol = modulerepository.findAll();
		List<Modules> modulecontrol = modulesrepository.moduledatafetchmain();
		model.addAttribute("modulecontrol", modulecontrol);

		List<AccessTypes> accesstypes = accesstyperepository.getAccessDetail();
		model.addAttribute("accesstypes", accesstypes);
		
		/*
		 * int a=1; List<Role_Modules> roleprev =
		 * rolemodulerepository.getPrevligedata(a);
		 * model.addAttribute("roleprev",roleprev);
		 */
		
		 StringBuffer url=request.getRequestURL();
			String val=url.substring(url.lastIndexOf("/"),url.length());
			//System.out.println("PPPPP==="+val);
			  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
			 if(actd !=null)
			 {
				 Object actd_viewtrue=null;
				 
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
				act.setUrl("/roleprivelege");
				
				activitylogservice.save(act);
			   
			 //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
					List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
					model.addAttribute("rolespresent",rolespresent);
				//Umesh Adding ends here  

		return "admin/role_privilege";
	}
	
	
	//Umesh Commenting this method on 03-01-2020
	//public String addprevligesmethod(@Valid @ModelAttribute(value="addprevliges") Role_Modules addprivlege,HttpServletRequest request,RedirectAttributes redirectAttributes,@RequestParam("viewchkb") List<String> idrates )
	@RequestMapping(value="/saveprevligescheked", method=RequestMethod.POST)
	public String addprevligesmethod(@Valid @ModelAttribute(value="addprevliges") Role_Modules addprivlege,HttpServletRequest request,RedirectAttributes redirectAttributes)
	{ 
		/// Getting Logged in user
		int roleid = Integer.parseInt(request.getParameter("rolename"));
		int moduleid = Integer.parseInt(request.getParameter("modulename"));
		//System.out.println("printing role id: "+roleid);
		int userid = 0;
		User userdeail = null;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			  userdeail = userrepository.getUserDetail(username);

			// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
		    userid =  userdeail.getId();
			String name = userdeail.getName();
			String useremail = userdeail.getName();
			//String userrole =  userdeail.getRoles();
		} else {
			String username = principal.toString();
			//System.out.println("Priting else Loggin user: " + username);
		}
		/// end Getting Logged in user
		 Date date = new Date(); 
		 Timestamp ts = new Timestamp(date.getTime());
		 
    		HashSet<Role_Modules> hset = new HashSet();
			if(request.getParameterValues("viewchkb")!=null)
			{
				String viewcheckbox[] =request.getParameterValues("viewchkb");
				for (int view= 0 ; view<viewcheckbox.length; view++)
				{
					String[] s1=viewcheckbox[view].split("-"); 
					Role_Modules modules = new Role_Modules();
					modules.setRoleid(Integer.parseInt(request.getParameter("rolename")));
					modules.setParentmoduleid(Integer.parseInt(request.getParameter("modulename")));
					modules.setAccessid(Integer.parseInt(s1[1]));
					modules.setModuleid(Integer.parseInt(s1[0]));
					modules.setCreatedby(userid);
					modules.setCreatedon(ts);
					modules.setCreatedbyip(request.getRemoteAddr());
					hset.add(modules);
			
				}
				
			}
			
			  if(request.getParameterValues("addchkb")!=null)
			  {
				  String addcheckbox[] =  request.getParameterValues("addchkb"); 
				  for (int add= 0 ;add<addcheckbox.length; add++) 
				  { 
					    String[] s1=addcheckbox[add].split("-");
					    Role_Modules modules = new Role_Modules();
						modules.setRoleid(Integer.parseInt(request.getParameter("rolename")));
						modules.setParentmoduleid(Integer.parseInt(request.getParameter("modulename")));
						modules.setAccessid(Integer.parseInt(s1[1]));
						modules.setModuleid(Integer.parseInt(s1[0]));
						modules.setCreatedby(userid);
						modules.setCreatedon(ts);
						modules.setCreatedbyip(request.getRemoteAddr());
						hset.add(modules);
					  }
			  
			  }
			  
			 if(request.getParameterValues("editchkb")!=null) 
			 { 
				 String editcheckbox[] =  request.getParameterValues("editchkb"); 
				 for (int edit= 0 ;edit<editcheckbox.length; edit++) 
				 {
					 
					  	String[] s1=editcheckbox[edit].split("-");
					  	Role_Modules modules = new Role_Modules();
						modules.setRoleid(Integer.parseInt(request.getParameter("rolename")));
						modules.setParentmoduleid(Integer.parseInt(request.getParameter("modulename")));
						modules.setAccessid(Integer.parseInt(s1[1]));
						modules.setModuleid(Integer.parseInt(s1[0]));
						modules.setCreatedby(userid);
						modules.setCreatedon(ts);
						modules.setCreatedbyip(request.getRemoteAddr());
						hset.add(modules);
					  
				 }
			}
			  
			  if(request.getParameterValues("deletechkb")!=null)
			  { 
				  String deletecheckbox[] = request.getParameterValues("deletechkb"); 
				  for (int delete= 0 ; delete<deletecheckbox.length; delete++) 
				  {
				  
						String[] s1=deletecheckbox[delete].split("-");
					    Role_Modules modules = new Role_Modules();
						modules.setRoleid(Integer.parseInt(request.getParameter("rolename")));
						modules.setParentmoduleid(Integer.parseInt(request.getParameter("modulename")));
						modules.setAccessid(Integer.parseInt(s1[1]));
						modules.setModuleid(Integer.parseInt(s1[0]));
						modules.setCreatedby(userid);
						modules.setCreatedon(ts);
						modules.setCreatedbyip(request.getRemoteAddr());
						hset.add(modules);
				  }
			 }
			  rolemodulerepository.deleteAllByRoleid(roleid,moduleid);
			  List a =rolemodulerepository.saveAll(hset);
			  if(a.size()>=1)
				 { 
					 System.out.println("aaaa===="+a.size());
					 redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
				 }
				 else
					{
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
			  
			  ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("save");
				act.setUrl("/saveprivelegechecked");
				
				activitylogservice.save(act);
			   
			  return "redirect:/roleprivilege";
	}


	   
		
		  
		    
			
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
