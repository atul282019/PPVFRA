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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.Modules;
import com.ppvfra.entity.OfficeDetails;
import com.ppvfra.entity.OfficeStates;
import com.ppvfra.entity.States;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.DistrictRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.OfficeDetailsRepository;
import com.ppvfra.repository.OfficeStateRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.StateRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.DistrictService;
import com.ppvfra.service.ModuleService;
import com.ppvfra.service.OfficeDetailsService;
import com.ppvfra.service.OfficeStateService;
import com.ppvfra.service.StateService;

@Controller
public class AdminMasterController {

	@Autowired
	StateRepository staterep;

	@Autowired
	OfficeDetailsService officedetailservice;

	@Autowired
	StateService stateservice;

	@Autowired
	DistrictService districtservice;

	@Autowired
	OfficeStateService officestateservice;

	@Autowired
	ModuleService moduleservice;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	OfficeStateRepository officestatesrepository;
	
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	OfficeDetailsRepository officedetailsrepository;
	
	@Autowired
	DistrictRepository districtrepository;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	
    //comments
	//this method is used to get the view of office and show the saved data
	@RequestMapping(value = "/addnewoffice", method = RequestMethod.GET)
	public String addOffice(Model model) {
		
		
		/// Getting Logged in user
				int userid = 0;
				User userdeail=null;
				
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if (principal instanceof UserDetails) {
						String username = ((UserDetails) principal).getUsername();
						  userdeail = userrepository.getUserDetail(username);
						userid = userdeail.getId();
					} else {
						String username = principal.toString();
						
					}
					/// end Getting Logged in user
					
					
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addAttribute("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addAttribute("modulelist2", modulelist2);
			
		
		//List<States> State = stateservice.listall();
		List<States> State=staterep.getstates();
		model.addAttribute("State", State);
	
		//List<Districts> District = districtservice.listall();
		List<Districts> District = districtrepository.getalldistricts();
		model.addAttribute("District", District);
		OfficeDetails officedetail = new OfficeDetails();
		model.addAttribute("officedetail", officedetail);
		
		//Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
		List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
		//Umesh Adding ends here 
			
		return "admin/master_add_office";
	}

	//comments
	//this method is used to get the form for adding new office
	@RequestMapping(value = "/getoffice", method = RequestMethod.GET)
	public String getOffice(Model model, HttpServletRequest request) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail =null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
			} else {
				String username = principal.toString();
			}
			/// end Getting Logged in user
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	

		//Umesh Adding here for finding active list of offices
	  List<OfficeDetails> officedetailslist = officedetailservice.listall();
	  //List<OfficeDetails> officedetailslist = officedetailsrepository.officedetails();
		model.addAttribute("officedetailslist", officedetailslist);
		
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
				act.setUrl("/getOffice");
				
				activitylogservice.save(act);
			   
				 //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
					List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
					model.addAttribute("rolespresent",rolespresent);
				//Umesh Adding ends here  
			   
			 return "admin/master_office";
	}

	//comments
	//this method is used to save office
	@RequestMapping(value = "/addoffice", method = RequestMethod.POST)
	public String addOffice(@Valid @ModelAttribute(value = "officedetail") OfficeDetails officedetail,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username="";
		
		if (bindingResult.hasErrors()) {
			/// Getting Logged in user
			int userid = 0;
			User userdeail = null;
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					 username = ((UserDetails) principal).getUsername();
					  userdeail = userrepository.getUserDetail(username);
					userid = userdeail.getId();
				} else {
					 username = principal.toString();
				}
				/// end Getting Logged in user
				
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  model.addAttribute("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  model.addAttribute("modulelist2", modulelist2);
		
		  	
			//List<States> State = stateservice.listall();
		  List<States> State=staterep.getstates();
			model.addAttribute("State", State);
			
			//List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);
			
			 ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("addOffice-error");
				act.setUrl("/addOffice");
				
				activitylogservice.save(act);
			   
			return "admin/master_add_office";
		} else {

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails)
				username = ((UserDetails) principal).getUsername();
			
			User   userdeail = userrepository.getUserDetail(username);
			
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			User userdetails = userrepository.getUserDetail(username);
			officedetail.setCreatedby(userdetails.getId());
			officedetail.setCreatedon(ts);
			officedetail.setCreatedbyip(request.getRemoteAddr());

			
			//List<States> State = stateservice.listall();
			 List<States> State=staterep.getstates();
			model.addAttribute("State", State);
			
			//List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);
			officedetailservice.toString();
		Boolean success = officedetailservice.save(officedetail);
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
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			//System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/addOffice");
			activitylogservice.save(act);
		}
		return "redirect:/getoffice";
	}

	//comments 
	//this method is used to edit a particular record via its id
	@RequestMapping(value = "/editoffice/{id}", method = RequestMethod.GET)
	public ModelAndView editByOfficeId(@PathVariable(name = "id") Long id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/edit_master_office");
		/// Getting Logged in user
		int userid = 0;
		User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
			} else {
				String username = principal.toString();
			}
			/// end Getting Logged in user
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  mav.addObject("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  mav.addObject("modulelist2", modulelist2);
	

		//List<States> State = stateservice.listall();
	  List<States> State=staterep.getstates();
		mav.addObject("State", State);
		
		//List<Districts> District = districtservice.listall();
		List<Districts> District = districtrepository.getalldistricts();
		mav.addObject("District", District);
		
		OfficeDetails officedetail = officedetailservice.get(id);
		mav.addObject("officedetail", officedetail);
		
		 
		String val="/getoffice";
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
		mav.setViewName("admin/edit_master_office");
		
		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		//System.out.println("Current date Is ="+dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("view");
		act.setUrl("/editOffice");
		activitylogservice.save(act);
		 
		//Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
		 List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			mav.addObject("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				mav.addObject("rolespresent",rolespresent);
			//Umesh Adding ends here 
				
		
		return mav;
	}

	@RequestMapping(value = "/deleteoffice/{id}")
	public String deleteByOfficeId(@PathVariable(name = "id") Long id,HttpServletRequest request) {
		officedetailservice.delete(id);
		
		User userdeail = null;
		String username="";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails)
			  username = ((UserDetails) principal).getUsername();
		
			  userdeail = userrepository.getUserDetail(username);
		
		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		//System.out.println("Current date Is ="+dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("remove");
		act.setUrl("/editOffice");
		activitylogservice.save(act);
		
		return "redirect:/getoffice";
	}

	//comments
	//this method is used to call the form for assigning new states 
	@RequestMapping(value = "/addassignofficestates/{id}", method = RequestMethod.GET)
	public String addassignofficestates(Model model, @PathVariable(name = "id") Long id,HttpServletRequest request) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
			} else {
				String username = principal.toString();
			}
			/// end Getting Logged in user
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	

		OfficeStates officestates = new OfficeStates();
		OfficeDetails officedetailslist = officedetailservice.get(id);
		model.addAttribute("officedetailslist", officedetailslist);
		List<States> bindstates = staterep.findAll();
	    model.addAttribute("bindstates",bindstates);
		model.addAttribute("officestates", officestates);
		
		
		String val="/getoffice";
		//System.out.println("PPPPP==="+val);
		  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
		 if(actd !=null)
		 {
			 Object actd_viewtrue=null;
		 
			 model.addAttribute("actd",actd);	
		     if(actd.contains("View"))
				 {
				 actd_viewtrue ="valpresent";
				 model.addAttribute("actd_viewtrue",actd_viewtrue);
				 }
			 
		 }
		
		 	ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			//System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("add");
			act.setUrl("/assigned_states_edit");
			activitylogservice.save(act);
			
			//Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
			//Umesh Adding ends here  
			
		return "admin/master_office_assign_states";
	}



	//comments 
	//this method is used save the new assingned states to the office
	@RequestMapping(value = "/assignofficestates", method = RequestMethod.POST)
	public String assignOfficeStates(@Valid @ModelAttribute(value = "officestates") OfficeStates officestates,
			BindingResult bindingResult, Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			
			int userid = 0;
			User userdeail = null;
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					String username = ((UserDetails) principal).getUsername();
					  userdeail = userrepository.getUserDetail(username);
					userid = userdeail.getId();
				} else {
					String username = principal.toString();
				}
				
				 ActivityLogTable act = new ActivityLogTable();
					act.setIpaddress(request.getRemoteAddr());
					act.setActivityby(userdeail.getUsername());
					Date dt = new Date();
					//System.out.println("Current date Is ="+dt);
					act.setLogin_time_stamp(dt);
					act.setActivity("assignOfficestate-error");
					act.setUrl("/assignofficestates");
					activitylogservice.save(act);
				
			return "admin/master_add_office";
		} else {

			if(!(request.getParameter("officeidhidden").equals("null")) &&
					!(request.getParameter("officeidhidden").equals("")))
					{int oid=Integer.parseInt(request.getParameter("officeidhidden"));
				officestatesrepository.removeOfficeIdDetails(oid);
					}
			String[] stateid =request.getParameterValues("stateid");
			String username="";
			HashSet s = new HashSet();
			for(int k=0; k<stateid.length;k++)
			{
				OfficeStates o = new OfficeStates();
				o.setOfficeid(Integer.parseInt(request.getParameter("officeidhidden")));
				o.setStateid(Integer.parseInt(stateid[k]));
				//System.out.println("trace=="+stateid[k]);
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				if (principal instanceof UserDetails)
					username = ((UserDetails) principal).getUsername();
				Date date = new Date();
				Timestamp ts = new Timestamp(date.getTime());
				User userdetails = userrepository.getUserDetail(username);
				o.setCreatedby(userdetails.getId());
				o.setCreatedon(ts);
				o.setCreatedbyip(request.getRemoteAddr());
				s.add(o);
			}
			  
			
			List a=officestatesrepository.saveAll(s);
			if(a.size()>=1)
			 { 
				// System.out.println("aaaa===="+a.size());
				 redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
			 }
			 else
				{
					redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
				}
            List<States> bindstates = staterep.findAll();
			model.addAttribute("bindstates", bindstates);
			/* officestateservice.save(officestates); */
		}
		
		int userid = 0;
		User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
			} else {
				String username = principal.toString();
			}
			
			 ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				//System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("save");
				act.setUrl("/assignofficestates");
				activitylogservice.save(act);
			
		return "redirect:/getoffice";
	}
 
}
