package com.ppvfra.controller;

import java.sql.Timestamp;
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
import com.ppvfra.entity.CropGroup;
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.DusTestCenter;
import com.ppvfra.entity.States;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AddCropGroupRepository;
import com.ppvfra.repository.AddCropsRepository;
import com.ppvfra.repository.AddDusTestCenterRepository;
import com.ppvfra.repository.DistrictRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.StateRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.AddCropGroupService;
import com.ppvfra.service.AddDusTestCenterService;
import com.ppvfra.service.AddcropService;
import com.ppvfra.service.AdduserService;
import com.ppvfra.service.CountryService;
import com.ppvfra.service.DistrictService;
import com.ppvfra.service.StateService;
import com.ppvfra.service.UserService;


@Controller
public class DusTestCenterController {
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	AddDusTestCenterRepository dustestcenterrepository;
	
	@Autowired
	UserService userservice;

	@Autowired
	AdduserService addusrservice;

	@Autowired
	StateRepository staterep;

	@Autowired
	AddcropService addcropservice;

	@Autowired
	AddCropGroupService addcropgroupservice;

	@Autowired
	AddDusTestCenterService adddustestcenterservice;

	@Autowired
	StateService stateservice;

	@Autowired
	CountryService countryservice;
	
	@Autowired		   
	DistrictService districtservice;

	@Autowired
	DistrictRepository districtrepository;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	AddCropGroupRepository addcropgrouprepository;
	
	@Autowired
	AddCropsRepository addcroprepository;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	//comments
	//this method is used to get the view of dus test center and show the saved data  
	@RequestMapping(value = "/getdustestcenter", method = RequestMethod.GET)
	public String getDusTestCenter(Model model,HttpServletRequest request) {
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
	 
		/*
		 * List<DusTestCenter> addlist = adddustestcenterservice.listall();
		 * model.addAttribute("addlist", addlist);
		 */
		 
		List <Object[]> addlist = dustestcenterrepository.findAllDusTestCenter();
		  model.addAttribute("addlist", addlist);
		
		  
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
				act.setUrl("/getdustestcenter");
				
				activitylogservice.save(act);
			  
			   
			   //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
					List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
					model.addAttribute("rolespresent",rolespresent);
				//Umesh Adding ends here 
					
		  return "admin/master_dus_test_center";
	}

	//comments
	//this method is used to call the form for saving dustestcenter
	@RequestMapping(value = "/adddustestcenter", method = RequestMethod.GET)
	public String addDusTestCenter(Model model,HttpServletRequest request) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail =  null;
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
	 

		//Umesh Adding here on 20-12-2019 
	   // Adding For Active clause
	  // List<CropGroup> Crop_Group = addcropgroupservice.listall();
	    List<CropGroup> Crop_Group = addcropgrouprepository.cropgroup_activedata();
		model.addAttribute("Crop_Group", Crop_Group);
		
	  //Umesh Adding here on 20-12-2019 for active crops only
	  //List<Crops> Crop = addcropservice.listall();
	  List<Crops> Crop = addcroprepository.getAllActiveCrops();
	  model.addAttribute("Crop", Crop);
		 
		//List<States> State = stateservice.listall();
	  	List<States> State=staterep.getstates();
		model.addAttribute("State", State);
		
		//List<Districts> District = districtservice.listall();
		List<Districts> District = districtrepository.getalldistricts();
		model.addAttribute("District", District);
		DusTestCenter adddustestcenter = new DusTestCenter();
		model.addAttribute("adddustestcenter", adddustestcenter);
		
		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is ="+dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("add");
		act.setUrl("/add_dustestcenter");
		
		activitylogservice.save(act);
	   
		 //Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
	   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
		//Umesh Adding ends here  
	   
		return "admin/master_add_dus_test_center";
	}

	//comments
	//this method is used to save dustestcenter
	@RequestMapping(value = "/adddustestcentermethod", method = RequestMethod.POST)
	public String addDusTestCenterMethod(
			@Valid @ModelAttribute(value = "adddustestcenter") DusTestCenter adddustestcenter,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username="";
		//System.out.println("Trace=======");
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
					
				}
				/// end Getting Logged in user
				
				
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  model.addAttribute("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  model.addAttribute("modulelist2", modulelist2);
		 
		  	//Umesh Adding here on 24-12-2019
			//List<CropGroup> Crop_Group = addcropgroupservice.listall();
		      List<CropGroup> Crop_Group = addcropgrouprepository.cropgroup_activedata();
			   model.addAttribute("Crop_Group", Crop_Group);
			
			
			List<Crops> Crop = addcropservice.listall();
			model.addAttribute("Crop", Crop);
			
			//List<States> State = stateservice.listall();
			 List<States> State=staterep.getstates();
			model.addAttribute("State", State);
			
			//List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);
			return "admin/master_add_dus_test_center";
		} else {
			
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
			
			 if (principal instanceof UserDetails)
				username = ((UserDetails) principal).getUsername();
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			User userdetails = userrepository.getUserDetail(username);
			adddustestcenter.setCreatedby(userdetails.getId());
			adddustestcenter.setCreatedon(ts);
			
			adddustestcenter.setCreatedbyip(request.getRemoteAddr());
			
			
			//Umesh Commenting here on 24-12-2019
			//List<CropGroup> Crop_Group = addcropgroupservice.listall();
			  List<CropGroup> Crop_Group = addcropgrouprepository.cropgroup_activedata();
			   model.addAttribute("Crop_Group", Crop_Group);
			   
			List<Crops> Crop = addcropservice.listall();
			model.addAttribute("Crop", Crop);
			//List<States> State = stateservice.listall();
		List<States> State=staterep.getstates();
			model.addAttribute("State", State);
			
			//List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);
			adddustestcenterservice.toString();
			boolean success=adddustestcenterservice.save(adddustestcenter);
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
			act.setUrl("/add_dustestcenter");
			
			activitylogservice.save(act);
			}
		
		
	   
		return "redirect:/getdustestcenter";
	}
    
	//comments
	//this method is used to edit a particular record vis its id
	@RequestMapping(value = "/editdustestcenter/{id}", method = RequestMethod.GET)
	public ModelAndView editByAddDusTestCenterId(@PathVariable(name = "id") Long id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/edit_master_dus_test_center");
		

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
	  	 
	     // Umesh Adding here on 24-12-2019
		//List<CropGroup> Crop_Group = addcropgroupservice.listall();
	    List<CropGroup> Crop_Group = addcropgrouprepository.cropgroup_activedata();
	    mav.addObject("Crop_Group", Crop_Group);
	    
		List<Crops> Crop = addcropservice.listall();
		mav.addObject("Crop", Crop);

		//List<States> State = stateservice.listall();
		List<States> State=staterep.getstates();
		mav.addObject("State", State);
		
		//List<Districts> District = districtservice.listall();
		List<Districts> District = districtrepository.getalldistricts();
		mav.addObject("District", District);
		DusTestCenter adddustestcenter = adddustestcenterservice.get(id);
		mav.addObject("adddustestcenter", adddustestcenter);
		
		 
			String val="/getdustestcenter";
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
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("view");
				act.setUrl("/editdustestcenter");
				
				activitylogservice.save(act);
			 
		mav.setViewName("admin/edit_master_dus_test_center");
		return mav;
	}

	@RequestMapping(value = "/deletedustestcenter/{id}")
	public String deleteByAddDusTestCenterId(@PathVariable(name = "id") Long id,HttpServletRequest request) {
		adddustestcenterservice.delete(id);
		
		int userid = 0;
		User userdeail = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			  userdeail = userrepository.getUserDetail(username);
			 
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
		act.setUrl("/deletedustestcenter");
		
		activitylogservice.save(act);
	   
		return "redirect:/getdustestcenter";
	}

}
