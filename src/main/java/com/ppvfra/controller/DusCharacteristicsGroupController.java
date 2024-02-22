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
import com.ppvfra.entity.DusCharacteristicGroup;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.DusCharacteristicGroupService;

@Controller
public class DusCharacteristicsGroupController {
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	DusCharacteristicGroupService duscharacteristicgroupservice;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	 @Autowired
	 RoleAssignRepository roleassignrepository;
	
	//comments
	//this method is used to get the view of duscharacteristics group and show the saved data
	@RequestMapping(value="/duscharacteristicsgroup", method=RequestMethod.GET)
    public String dusCharacteristicsGroup(Model model,HttpServletRequest request) {
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

    		  List<DusCharacteristicGroup> duscharacteristicsgrouplist = duscharacteristicgroupservice.listall();
    		  model.addAttribute("duscharacteristicsgrouplist", duscharacteristicsgrouplist);
    		  
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
			act.setUrl("/duscharacteristicsgroup");
			
			activitylogservice.save(act);
		   
		   //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
		   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
			//Umesh Adding ends here 
				
		   
    		  return "admin/master_dus_characteristics_group";
    
    }    
    
	//comments
	//this method is used to get the form for adding duscharacteristics group
    @RequestMapping(value="/addduscharacteristicsgroup",method=RequestMethod.GET)
    public String addDusCharactyeristicsGroup(Model model)
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
      
	  DusCharacteristicGroup duscharacteristicgroup= new DusCharacteristicGroup();
	  model.addAttribute("duscharacteristicgroup",duscharacteristicgroup);
	  
	  //Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
		//Umesh Adding ends here 
	 
	 
    	return "admin/master_add_dus_characteristics_group";
    } 
    
    //comments
    //this method is used for saving duscharacteristics group
    @RequestMapping(value = "/saveduscharacteristicsgroup", method = RequestMethod.POST)
	public String saveDusCharacteristicsGroup(@Valid @ModelAttribute(value = "duscharacteristicgroup") DusCharacteristicGroup duscharacteristicgroup,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username="";
		//System.out.println("TRACE===");
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
		  
			return "admin/master_add_dus_characteristics_group";
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
			
			duscharacteristicgroup.setCreatedby(userdetails.getId());
			duscharacteristicgroup.setCreatedon(ts);
			
			duscharacteristicgroup.setCreatedbyip(request.getRemoteAddr());

			
			
			
			Boolean success=duscharacteristicgroupservice.save(duscharacteristicgroup);
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
			act.setUrl("/saveduscharacteristicsgroup");
			
			activitylogservice.save(act);
		
		}
		return "redirect:/duscharacteristicsgroup";
	}    
 
    //comments
    //this method is used edit a particular record via its id
	@RequestMapping(value = "/editduscharacteristicsgroup/{id}", method = RequestMethod.GET)
	public ModelAndView editByDusCharacteristicsGroup(@PathVariable(name = "id") Integer id,HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("admin/edit_master_dus_characteristics_group");
		
		
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
	 

	  DusCharacteristicGroup duscharacteristicgroup = duscharacteristicgroupservice.get(id);
		mav.addObject("duscharacteristicgroup", duscharacteristicgroup);
		
	  String val="/duscharacteristicsgroup";
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
				act.setUrl("/editduscharacteristics");
				
				activitylogservice.save(act);
		
		mav.setViewName("admin/edit_master_dus_characteristics_group");
		return mav;
	}

}
