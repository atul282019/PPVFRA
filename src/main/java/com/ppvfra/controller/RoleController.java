package com.ppvfra.controller;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.Role;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.RoleService;

@Controller
public class RoleController {
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	RoleRepository rolerepository;
	
	@Autowired
	RoleService roleservice;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogService activitylogservice;

	@Autowired
	RoleAssignRepository roleassignrepository;
	
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public String getRoleManagement(Model model,HttpServletRequest request) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail =null;
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
	     
	     // Umesh Editing this line on 20-12-2019 ....
		//List<Role> roles = rolerepository.findAll();
		List<Role> roles = rolerepository.getroleslist_bystatus();
		model.addAttribute("roles", roles);
		
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
			    act.setUrl(val);
				
			    activitylogservice.save(act);
			    
			    Date date= new Date();
			    
			    long time = date.getTime();
			        System.out.println("Time in Milliseconds: " + time);
			    
			    Timestamp ts = new Timestamp(time);
			    System.out.println("Current Time Stamp: " + ts);
			    
			    model.addAttribute("time_obt",ts);
			 
			   
			 //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
					List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
					model.addAttribute("rolespresent",rolespresent);
				//Umesh Adding ends here 
					
		return "admin/role_managment";
	}
	@RequestMapping(value = "/addrole", method = RequestMethod.GET)
	public String addRole(Model model,HttpServletRequest req) {
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
	 
		Role urserole = new Role();
		model.addAttribute("urserole", urserole);
		
		 ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(req.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("add");
			act.setUrl("/addrole");
			
			activitylogservice.save(act);
		   
		   //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
		   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
			//Umesh Adding ends here 
				
		return "admin/add_role";
	}
	@RequestMapping(value = "/saverolemethod", method = RequestMethod.POST)
	public String saverolemethod(@Valid @ModelAttribute("urserole") Role urserole, BindingResult bindingResult,
			Model model,RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {

			return "admin/add_role";
		} else {

			 @Valid Role a=  rolerepository.save(urserole);
			 if(a.getId()!=0)
			 { 
				 System.out.println("aaaa===="+a.getId());
				 redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
			 }
			 else
				{
					redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
				}
			return "redirect:/role";

		}
	}
	@RequestMapping(value = "/editrole/{id}", method = RequestMethod.GET)
	public ModelAndView editrolebyid(@PathVariable(name = "id") Long id,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("admin/edit_rolemanagement");
		// Optional<InternalUser> adduser= adduserrep.findById(id);

		/// Getting Logged in user
				int userid = 0;
				User userdeail = null;
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if (principal instanceof UserDetails) {
						String username = ((UserDetails) principal).getUsername();
						  userdeail = userrepository.getUserDetail(username);
						userid = userdeail.getId();
						// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
					 } else {
						String username = principal.toString();
						//System.out.println("Priting else Loggin user: " + username);
					}
					/// end Getting Logged in user
					//System.out.println("Priting Loggin user id: " + userid);
					
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  mav.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  mav.addObject("modulelist2", modulelist2);
			 



		Role urserole = roleservice.get(id);
		mav.addObject("urserole", urserole);

		// model.addAttribute("urserole", urserole);
		
	 
		String val="/role";
		//System.out.println("PPPPP==="+val);
		  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
		 if(actd !=null)
		 {
			 Object actd_viewtrue=null;
	 
			 mav.addObject("actd",actd);
			 
		//  System.out.println("PRINTING contains"+actd.contains("View")); 
			
		   if(actd.contains("View"))
				 {
				 actd_viewtrue ="valpresent";
	//	System.out.println("PRINING TRUEEEEE"+actd_viewtrue); 
				
				 mav.addObject("actd_viewtrue",actd_viewtrue);
		 		
				 }
			 
		 }
		 
		 ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(req.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("edit");
			act.setUrl("/editrole");
			
			activitylogservice.save(act);
		   
		   //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
		   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			mav.addObject("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				mav.addObject("rolespresent",rolespresent);
			//Umesh Adding ends here 
				

		mav.setViewName("admin/edit_rolemanagement");
		return mav;
	}
}
