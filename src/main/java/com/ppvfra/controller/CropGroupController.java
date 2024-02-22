package com.ppvfra.controller;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.CropGroup;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AddCropGroupRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.AddCropGroupService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CropGroupController {


	@Autowired
	AddCropGroupService addcropgroupservice;

	@Autowired
	UserRepository userrepository;

	@Autowired
	AddCropGroupRepository addcropgrprep;

	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogRepository activitylogrepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	 @Autowired
		RoleAssignRepository roleassignrepository;
    
	//comment
	// this method is used to get the view of crop group
	@RequestMapping(value = "/getcropgroup", method = RequestMethod.GET)
	public String getCropGroup(Model model,HttpServletRequest request) {
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
	 
	    /* here we are using listall method to get the 
	    data saved in database
	    */
		//Umesh Commenting to show active cropgroup only
	   // List<CropGroup> addcropgrouplist = addcropgroupservice.listall();
	    List<CropGroup> addcropgrouplist = addcropgrprep.cropgroup_activedata();
		model.addAttribute("addcropgrouplist", addcropgrouplist);
		
		 StringBuffer url=request.getRequestURL();
			String val=url.substring(url.lastIndexOf("/"),url.length());
			System.out.println("PPPPP==="+val);
			List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
			 if(actd !=null)
			 {
				 Object actd_viewtrue=null;
				 
				 model.addAttribute("actd",actd);
				 
				  if(actd.contains("View"))
					 {
					 actd_viewtrue ="valpresent";
					// System.out.println("PRINING IN INST."  +actd_viewtrue); 
					
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
				act.setUrl("/getcropgroup");
				activitylogservice.save(act);
				 
				 
				//Umesh Adding here on 14-01-2020 -------
					//Added Here For Name And Role Showing in header
						
				 List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
					model.addAttribute("usrname_header_val",usrname_header_val);
						List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
						model.addAttribute("rolespresent",rolespresent);
					//Umesh Adding ends here  
						
		return "admin/master_crop_group";
	}

	// comments
	// this method is used to call the form for adding crop group
	@RequestMapping(value = "/addcropgroup", method = RequestMethod.GET)
	public String addCropGroup(Model model,HttpServletRequest request)

	{   
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
			//System.out.println("Priting Loggin user id: " + userid);
			
		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		model.addAttribute("modulelist", modulelist);
		
		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		model.addAttribute("modulelist2", modulelist2);
	
		CropGroup addcropgroup = new CropGroup();
		model.addAttribute("addcropgroup", addcropgroup);
		
		 ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("add");
			act.setUrl("/addcropgroup");
			activitylogservice.save(act);
			 
			 //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			 List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
					List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
					model.addAttribute("rolespresent",rolespresent);
				//Umesh Adding ends here  
				
			 
		return "admin/master_add_crop_group";
	}

	
    // comments
	// this method is to add crop group and save it in database
	@RequestMapping(value = "/addcropgroupmethod", method = RequestMethod.POST)
	public String addcropgroupmethod(@Valid @ModelAttribute(value = "addcropgroup") CropGroup addcropgroup,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username="";
		System.out.println("TRACE===");
		if (bindingResult.hasErrors()) {
			/// Getting Logged in user
			int userid = 0;
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					 username = ((UserDetails) principal).getUsername();
					User userdeail = userrepository.getUserDetail(username);
					userid = userdeail.getId();
					
			 } else {
					 username = principal.toString();
					 //System.out.println("Priting else Loggin user: " + username);
				}
				/// end Getting Logged in user
				
				
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  model.addAttribute("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  model.addAttribute("modulelist2", modulelist2);
		 
          return "admin/master_add_crop_group";
          
		} 
		else {
			
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
			 
				
			 } else {
				  username = principal.toString();
				
			}
			
			 if (principal instanceof UserDetails)
				username = ((UserDetails) principal).getUsername();
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			User userdetails = userrepository.getUserDetail(username);
			addcropgroup.setCreatedby(userdetails.getId());
			addcropgroup.setCreatedon(ts);
			addcropgroup.setCreatedbyip(request.getRemoteAddr());
            
			Boolean savedsuccess = addcropgroupservice.save(addcropgroup);
			if (savedsuccess)
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
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/addcropgroupmethod");
			activitylogservice.save(act);
		}
		
		return "redirect:/getcropgroup";
	}

	//comments
	//this method used for editing the crop group via id
	@RequestMapping(value = "/editcropgroup/{id}", method = RequestMethod.GET)
	public ModelAndView editByCrop_GroupId(@PathVariable(name = "id") Long id,HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("admin/edit_master_crop_group");
		
		
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
	 

		CropGroup addcropgroup = addcropgroupservice.get(id);
		mav.addObject("addcropgroup", addcropgroup);
		
		 
			String val="/getcropgroup";
			System.out.println("PPPPP==="+val);
			List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
			 if(actd !=null)
			 {
				 Object actd_viewtrue=null;
				 
				 mav.addObject("actd",actd);
				 
				  if(actd.contains("View"))
					 {
					 actd_viewtrue ="valpresent";
					// System.out.println("PRINING IN INST."  +actd_viewtrue); 
					
					 mav.addObject("actd_viewtrue",actd_viewtrue);
					  }
				 
			 }
			 
			 ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("save");
				act.setUrl("/addcropgroupmethod");
				activitylogservice.save(act);
				 
		mav.setViewName("admin/edit_master_crop_group");
		return mav;
	}

	
	@RequestMapping(value = "/deletecropgroup/{id}")
	public String deleteByCrop_GroupId(@PathVariable(name = "id") Long id,HttpServletRequest request) {
		addcropgroupservice.delete(id);
		
		
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
		System.out.println("Current date Is ="+dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("save");
		act.setUrl("/deletecropgroup");
		
		activitylogservice.save(act);
		 
		return "redirect:/getcropgroup";
	}

	//comments
	// this method is used to check the duplicacy
	// if crop group group is already present in database this method will not allow that particular name to be saved again
	@RequestMapping(value = { "/ppvfra/check_cropgroup" }, method = RequestMethod.POST)
	@ResponseBody
	public String crop_groupavailabilitycheck(@RequestBody String crop_group, HttpServletRequest request) {
		String value = "";
		//System.out.println("Trace Printing Crop_Group  Check=" + crop_group);

		List<CropGroup> crop_groupchk = addcropgrprep.crop_groupcheck(crop_group);
		if (crop_groupchk.size() >= 1) {
			value = "taken";
		} else {
			value = "not taken";
		}

		return value;
	}
}
