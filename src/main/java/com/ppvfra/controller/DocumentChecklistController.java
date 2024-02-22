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
import com.ppvfra.entity.DocumentChecklist;
import com.ppvfra.entity.FormTypes;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.FormTypesRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.DocumentChecklistService;
import com.ppvfra.service.FormTypesService;
import com.ppvfra.service.ObservationCategoryService;

@Controller
public class DocumentChecklistController {
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	ObservationCategoryService observationcategoryservice;
	
	@Autowired
	DocumentChecklistService documentchecklistservice;
	
	@Autowired
	FormTypesService formtypesservice;
	
	@Autowired
	FormTypesRepository formtypesrepository;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	//comments
	//this method is used to get the view of document checklist and show the data saved 
	 @RequestMapping(value="/documentchecklist", method=RequestMethod.GET)
	    public String documentChecklist(Model model ,HttpServletRequest request) {
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
	    		  
	    		  List<FormTypes> bindformtype = formtypesrepository.findAll();
	    		    model.addAttribute("bindformtype",bindformtype);
	    		  
	    		  List<DocumentChecklist> documentchecklistlist = documentchecklistservice.listall();
	    		  model.addAttribute("documentchecklistlist", documentchecklistlist);
	             
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
	  			act.setUrl("/documentchecklist");
	  			
	  			activitylogservice.save(act);
	  		   
	  		//Umesh Adding here on 14-01-2020 -------
	  			//Added Here For Name And Role Showing in header
	  				
	  		 List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
	  				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
	  				model.addAttribute("rolespresent",rolespresent);
	  			//Umesh Adding ends here  
	  		   
	    		  return "admin/master_document_checklist";
	    
	    }
	 
	    //comments
	    //this method is used to get the form for adding document checklist
	    @RequestMapping(value="/adddocument",method=RequestMethod.GET)
	    public String addDocument(Model model,HttpServletRequest request)
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
	      
		  DocumentChecklist documentchecklist = new DocumentChecklist();
		  model.addAttribute("documentchecklist",documentchecklist);
		  
		  List<FormTypes> formtypes = formtypesservice.listall();
		  model.addAttribute("formtypes", formtypes);
		  
		  ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("add");
			act.setUrl("/add_documentchecklist");
			
			activitylogservice.save(act);
		   
			 //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
		   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
			//Umesh Adding ends here  
	    	return "admin/master_add_document_checklist";
	    }
	   
	    //comments
	    //this method is used for saving document checklist
	    @RequestMapping(value = "/adddocumentchecklist", method = RequestMethod.POST)
		public String addadddocumentChecklist(@Valid @ModelAttribute(value = "documentchecklist") DocumentChecklist documentchecklist,
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
			  
			  List<FormTypes> formtypes = formtypesservice.listall();
			  model.addAttribute("formtypes", formtypes);

				return "admin/master_add_document_checklist";
			} else {
				
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				if (principal instanceof UserDetails)
					username = ((UserDetails) principal).getUsername();
				User userdeail = userrepository.getUserDetail(username);
				
				
				Date date = new Date();
				Timestamp ts = new Timestamp(date.getTime());
				User userdetails = userrepository.getUserDetail(username);
				documentchecklist.setCreatedby(userdetails.getId());
				documentchecklist.setCreatedon(ts);
				documentchecklist.setCreatedbyip(request.getRemoteAddr());
                
				
				Boolean success =documentchecklistservice.save(documentchecklist);
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
					act.setUrl("/add_documentchecklist");
					
					activitylogservice.save(act);
				   
			}
			return "redirect:/documentchecklist";
		}
	    
	    //comments
	    //this method is used to edit a particular record via its id
	    @RequestMapping(value = "/editdocumentchecklist/{id}", method = RequestMethod.GET)
		public ModelAndView editByDocumentChecklist(@PathVariable(name = "id") Integer id,HttpServletRequest request) {

			ModelAndView mav = new ModelAndView("admin/edit_master_document_checklist");
			
			
			/// Getting Logged in user
			int userid = 0;
			User userdeail = null;
			
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					String username = ((UserDetails) principal).getUsername();
					  userdeail = userrepository.getUserDetail(username);
					userid = userdeail.getId();
					} 
				else {
					String username = principal.toString();
				}
				/// end Getting Logged in user
				
				
			List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			mav.addObject("modulelist", modulelist);
			
			List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			mav.addObject("modulelist2", modulelist2);
			  
            DocumentChecklist documentchecklist =documentchecklistservice.get(id);
			List<FormTypes> formtypes = formtypesservice.listall();
			mav.addObject("formtypes", formtypes);

			mav.addObject("documentchecklist", documentchecklist);
			
			 
	  			String val="/documentchecklist";
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
					act.setUrl("/edit_documentchecklist");
					
					activitylogservice.save(act);
				   
			mav.setViewName("admin/edit_master_document_checklist");
			return mav;
		}
}
