package com.ppvfra.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import com.ppvfra.entity.MFormSection;
import com.ppvfra.entity.ObservationCategory;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.MFormSectionRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.ObservationCategoryService;

@Controller
public class ObservationCategoryController {
	
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	UserRepository userrepository; 
	
	@Autowired
	ObservationCategoryService observationcategoryservice;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	@Autowired
	MFormSectionRepository mformsectionrepository;
	
	//comments
	//this method is used get the view of observation category and show the saved data
	@RequestMapping(value="/observationcategory", method=RequestMethod.GET)
    public String observationCtaegory(Model model,HttpServletRequest request) {
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
    	
    		  List<ObservationCategory> observationcategorylist = observationcategoryservice.listall();
    			model.addAttribute("observationcategorylist", observationcategorylist);
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
    		  			act.setUrl("/observationcategory");
    		  			
    		  			activitylogservice.save(act);
    		  		  
    		  		//Umesh Adding here on 14-01-2020 -------
    					//Added Here For Name And Role Showing in header
    						
    		  		 List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
    					model.addAttribute("usrname_header_val",usrname_header_val);
    						List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
    						model.addAttribute("rolespresent",rolespresent);
    					//Umesh Adding ends here 
    						
    				 return "admin/master_observation_category";
    
    }
 
	//comments
	//this method is used to get the form for adding observation category
    @RequestMapping(value="/addobservation",method=RequestMethod.GET)
    public String addObservation(Model model,HttpServletRequest request)
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
			
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
     
	  ObservationCategory observationcategory = new ObservationCategory();
	  model.addAttribute("observationcategory",observationcategory);
	  
	  ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is ="+dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("add");
		act.setUrl("/add_observationcategory");
		
		activitylogservice.save(act);
	   
	 //Umesh Adding here on 14-01-2020 -------
		
	//Adding here on 16-06-2020 for saving in m_observation table.
		
		//List<MFormSection> formwise_data= mformsectionrepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
		List<MFormSection> formwise_data= mformsectionrepository.getalldata();
		if(formwise_data.size()>0 && formwise_data!=null)
		   {
			model.addAttribute("formwise_data",formwise_data);
			}
	//Ends here 	
		
		
		//Added Here For Name And Role Showing in header
			
	   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
		//Umesh Adding ends here 
			
    	return "admin/master_add_observation_category";
    }
   
    //comments
    //this method is used to save the observation category
	@RequestMapping(value = "/addobservationcategory", method = RequestMethod.POST)
	public String addObservationcategory(@Valid @ModelAttribute(value = "observationcategory") ObservationCategory observationcategory,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username="";
		//System.out.println("TRACE===");
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
		  
		  
		  ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("add_observation-error");
			act.setUrl("/add_observationcategory");
			
			activitylogservice.save(act);
		   

			return "admin/master_add_observation_category";
		} else {
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails)
				username = ((UserDetails) principal).getUsername();
			
			User userdeail = userrepository.getUserDetail(username);
			
			int ftypeval=Integer.parseInt(request.getParameter("form_addcategory"));
			 System.out.println("Trace on 252 ----- val = "+ftypeval);
			 
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			User userdetails = userrepository.getUserDetail(username);
			observationcategory.setCreatedby(userdetails.getId());
			observationcategory.setCreatedon(ts);
			observationcategory.setCreatebyip(request.getRemoteAddr());
			observationcategory.setFormsection_id(ftypeval);
			Boolean success = observationcategoryservice.save(observationcategory);
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
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/add_observationcategory");
			
			activitylogservice.save(act);
		   
		}
		return "redirect:/observationcategory";
	}
	
	//comments
	//this method is used to edit a particular record via its id
	@RequestMapping(value = "/editobservationcategory/{id}", method = RequestMethod.GET)
	public ModelAndView editByObservationCategory(@PathVariable(name = "id") Integer id, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("admin/edit_master_observation_category");
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
	 

		ObservationCategory observationcategory = observationcategoryservice.get(id);
		mav.addObject("observationcategory", observationcategory);
		
		 
			String val="/observationcategory";
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
			 
			 
				//Added Here For Name And Role Showing in header
				
			    List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			    mav.addObject("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				mav.addObject("rolespresent",rolespresent);
				//Umesh Adding ends here
			 
			 
			 
			 
		mav.setViewName("admin/edit_master_observation_category");
		
		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is ="+dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("edit");
		act.setUrl("/edit_observationcategory");
		
		activitylogservice.save(act);
	   
		return mav;
	}

}
