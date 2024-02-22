package com.ppvfra.controller;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import com.ppvfra.entity.CropSpecies;
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AddCropGroupRepository;
import com.ppvfra.repository.AddCropsRepository;
import com.ppvfra.repository.CropSpeciesRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.AddCropGroupService;
import com.ppvfra.service.AddcropService;


@Controller
public class CropsController {
	
	@Autowired
	AddCropGroupService addcropgroupservice;

	@Autowired
	UserRepository userrepository;

	@Autowired
	AddCropGroupRepository addcropgrprep;

	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	AddCropsRepository addcroprep;
	
	@Autowired
	AddcropService addcropservice;
	
	@Autowired
	CropSpeciesRepository cropspeciesrepository;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogRepository activitylogrepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	 
	
	
	//comments
	//this method is used to get the view of crops and show the data saved
	@RequestMapping(value = "/getcrops", method = RequestMethod.GET)
	public String getCrops(Model model,HttpServletRequest request) {
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
		List<Crops> addcroplist = addcropservice.listall();
		model.addAttribute("addcroplist", addcroplist);
		*/
		
		List <Object[]> addcroplist = addcroprep.findAllCrops();
		  model.addAttribute("addcroplist", addcroplist);
		  
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
				
			//ALL BELOW CODE IS FOR ACCEPT/REJECT BUTTON		
				//	 String val1 ="Accept_Company";
				//	 List<Object[]> accesstypeprocess_data = rolemodulerepository.getAccesstypes_Processdata(userid,val1);
				//		System.out.println("PRINTING )))))="+accesstypeprocess_data.contains("Add"));
				
			    /*		if(accesstypeprocess_data.contains("Add"))
						{
							actd_addtrue ="addpresent";
							model.addAttribute("actd_addtrue",actd_addtrue);	
						}
				*/
			//ENDS HERE 		
					 }
				 
			 }
			 
			 ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("view");
				act.setUrl("/getcrops");
				activitylogservice.save(act);
				
				//Umesh Adding here on 14-01-2020 -------
					//Added Here For Name And Role Showing in header
						
				 List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
					model.addAttribute("usrname_header_val",usrname_header_val);
						List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
						model.addAttribute("rolespresent",rolespresent);
					//Umesh Adding ends here  
						
		     return "admin/master_crops";
	}

	//comments
	//this method is used to call the form for adding of crops
	@RequestMapping(value = "/addnewcrops", method = RequestMethod.GET)
	public String addNewCrops(Model model) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail= null;
		
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
	 
	  //Commenting here on 24-12-2019
	  //List<CropGroup> Crop_Group = addcropgroupservice.listall();
		List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
		model.addAttribute("Crop_Group", Crop_Group);
		Crops addcrops = new Crops();
		model.addAttribute("addcrops", addcrops);
		
		//Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
		//Umesh Adding ends here 
			
		return "admin/master_add_crops";
	}

	//comments
	//this method is used to save crops 
	@RequestMapping(value = "/addcrops", method = RequestMethod.POST)
	public String addCrops(@Valid @ModelAttribute(value = "addcrops") Crops crops, BindingResult bindingResult,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) 
	{   String username="";
		String[] botanicalname =request.getParameterValues("crop_botanical_name");
		String[] family =request.getParameterValues("family");
		String[] initials =request.getParameterValues("initials");
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
		 
		  List<CropGroup> Crop_Group = addcropgroupservice.listall();
		  model.addAttribute("Crop_Group", Crop_Group);
			
		  return "admin/master_add_crops";
		} 
		else {
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails)
				username = ((UserDetails) principal).getUsername();
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			User userdetails = userrepository.getUserDetail(username);
			crops.setCreatedby(userdetails.getId());
			crops.setCreatedon(ts);
			crops.setCreatedbyip(request.getRemoteAddr());
			
			HashSet s = new HashSet();
			for(int k=0; k<botanicalname.length; k++) { 
			  CropSpecies species = new CropSpecies();
			  species.setCrop_botanical_name(botanicalname[k]);
			  species.setFamily(family[k]);
			  // Adding below line on 21-11-2019 -----------
			  species.setInitials(initials[k]);
			  System.out.println("Umesh Printing here ====236 ="+Integer.parseInt(request.getParameter("active")));
			  if(Integer.parseInt(request.getParameter("active")) ==1
				&& !((request.getParameter("active")).equals("null"))
				)
			  {
				  species.setActive(true);
			  }
			  else{species.setActive(false);}
			  //Adding ends here ---------
			  
			 //1. Adding here on 12-03-2020
			  species.setCscount(1);
			  species.setCreatedby(userdetails.getId().longValue());
			  species.setCreatedbyip(request.getRemoteAddr());
			  species.setCreatedon(ts);
			 //1. Adding here ends  
		    
			  s.add(species);
			}
			  crops.setCropspecies(s);
			Boolean success=addcropservice.save(crops);
			if (success)
			{
				redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
			}
			else
			{
				redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
			}
			
			List<CropGroup> Crop_Group = addcropgroupservice.listall();
			model.addAttribute("Crop_Group", Crop_Group);
		}
		return "redirect:/getcrops";
	}

	
	//comments
	//this method is used to edit a particular crop via its id 
	@RequestMapping(value = "/editcrop/{id}", method = RequestMethod.GET)
	public ModelAndView editByAddCropId(@PathVariable(name = "id") Long id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/edit_master_crops");
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
	 
      List<Object[]> botanicalname = cropspeciesrepository.findAllBotanicalName(id.intValue());
	  mav.addObject("botanicalname",botanicalname);	
	  
	  List<CropGroup> Crop_Group = addcropgroupservice.listall();
	  mav.addObject("Crop_Group", Crop_Group);
	  
	  Crops addcrops = addcropservice.get(id);
	  mav.addObject("addcrops", addcrops);
		
		
		   
			String val="/getcrops";
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
				act.setUrl("/editcrops");
				activitylogservice.save(act);
				 
				//Umesh Adding here on 14-01-2020 -------
					//Added Here For Name And Role Showing in header
						
				 List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
					mav.addObject("usrname_header_val",usrname_header_val);
						List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
						mav.addObject("rolespresent",rolespresent);
					//Umesh Adding ends here 
			 
			mav.addObject("cropid_hidden", id);
			
			 mav.setViewName("admin/edit_master_crops");
		return mav;
	}

	@RequestMapping(value = "/deletecrop/{id}")
	public String deleteByAddCropId(@PathVariable(name = "id") Long id,HttpServletRequest req) {
		addcropservice.delete(id);
		
		
		User userdeail = null;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			  userdeail = userrepository.getUserDetail(username);
		 
			
			 } else {
			String username = principal.toString();
			
		}
		
		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(req.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is ="+dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("save");
		act.setUrl("/editcrops");
		activitylogservice.save(act);
		 
		return "redirect:/getcrops";
	}
	@RequestMapping(value = "/editcrops", method = RequestMethod.POST)
	public String editCrops(@Valid @ModelAttribute(value = "addcrops") Crops crops, BindingResult bindingResult,Model model,HttpServletRequest request) 
	{   String username="";
		String[] botanicalname =request.getParameterValues("crop_botanical_name");
		String[] family =request.getParameterValues("family");
		String[] initials =request.getParameterValues("initials");
		String[] cropidhiddenspecies=request.getParameterValues("cropidhiddenspecies");
		
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
		 
		  List<CropGroup> Crop_Group = addcropgroupservice.listall();
		  model.addAttribute("Crop_Group", Crop_Group);
			
		  return "admin/master_add_crops";
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
			crops.setCreatedby(userdetails.getId());
			crops.setCreatedon(ts);
			crops.setCreatedbyip(request.getRemoteAddr());
			
			/*
			if(!(request.getParameter("cropidhidden").equals("null")) &&
					!(request.getParameter("cropidhidden").equals("")))
					{int oid=Integer.parseInt(request.getParameter("cropidhidden"));
					cropspeciesrepository.removeCropIdDetails(oid);
					}
			*/
			 
			HashSet s = new HashSet();
			
			/*
			
			Set<Integer> s1 = new HashSet<Integer>();
			
			List<Object[]> comp_data=cropspeciesrepository.findComp_data(Integer.parseInt(request.getParameter("cropidhidden")));
			
			
			for(int k=0; k<comp_data.size(); k++) { 
				 CropSpecies species = new CropSpecies(); 
				  
				    Object[] r= (Object[]) comp_data.get(k);
				     int valuealready = (int)Array.get(r, 0);
				    String botnicaln = (String)Array.get(r, 4);
				    String fammilyn = (String)Array.get(r, 5);
				    String initialn = (String)Array.get(r, 6);
			
			  
			  for(int p=0;p<botanicalname.length;p++)
				{
				 System.out.println("Trace On Line Number 498= "+valuealready);
			    int spcid =Integer.parseInt(cropidhiddenspecies[p]);
			    System.out.println("Printing spcid= "+spcid);
			    if(!s1.contains(valuealready))
				  {
			    	
			    	if(valuealready == spcid || spcid ==0 )
				  {
				  
				  s1.add(valuealready);
				   
				  if(Integer.parseInt(cropidhiddenspecies[p])!=0)
				  {
				  species.setId(Integer.parseInt(cropidhiddenspecies[p]));
				  }
				  species.setCropid(Integer.parseInt(request.getParameter("cropidhidden")));
				  species.setCrop_botanical_name(botanicalname[k]);
				  species.setFamily(family[p]);
				  species.setInitials(initials[p]);
				  System.out.println("Umesh Printing here 521==== ="+Integer.parseInt(request.getParameter("active")));
				  if(Integer.parseInt(request.getParameter("active")) ==1
					&& !((request.getParameter("active")).equals("null"))
					)
				  {
					  species.setActive(true);
				  }
				  else{species.setActive(false);}
				  
				  species.setCscount(1);
				  s.add(species);
				  break;
				  }else {
						
					    System.out.println("Trace On Line Number 531 inside else = "+Integer.parseInt(request.getParameter("cropidhidden")));	 
					   if(!s1.contains(valuealready))
					        {
					        s1.add(valuealready);
					        species.setId(valuealready);
					        species.setCropid(Integer.parseInt(request.getParameter("cropidhidden")));
							species.setCrop_botanical_name(botnicaln); 
							species.setFamily(fammilyn);
							species.setInitials(initialn);
							 System.out.println("Trace on 545 ="+botnicaln+"family="+fammilyn+"initials as="+initials);
							  if(Integer.parseInt(request.getParameter("active")) ==1
								&& !((request.getParameter("active")).equals("null"))
								)
							  {
								  species.setActive(true);
							  }
							  else{species.setActive(false);}
							  species.setCscount(2);
							  System.out.println("Trace Printing in else 550 ="+valuealready);
							  s.add(species);
							  }
						  }
			    	
				 
				  }
				
				
				 
				
				} 
			
			System.out.println("Trace Just Before K as ="+k);
			
			 
		    }
			 
		  System.out.println("Printing the S1 as ="+s1);
		  
		  */
			
			
			 
		 /*
		  //Second Process
		    
			List<Object[]> comp_data=cropspeciesrepository.findComp_data_map(Integer.parseInt(request.getParameter("cropidhidden")));
									 
			 
			Integer[] myArray = new Integer[comp_data.size()];
			comp_data.toArray(myArray);
			//System.out.println("Printing the array as="+myArray.length);
			//System.out.println("Printing the cropid hidden as "+cropidhiddenspecies.length);
			HashMap<Integer, Boolean> map = new HashMap<>();
			for (int i=0;i<myArray.length;i++) {
				//int k=Integer.parseInt(i);
				 CropSpecies species = new CropSpecies();
				 
				 
				for(int j=0 ;j<cropidhiddenspecies.length;j++) {
					
					System.out.println("Printing the j ="+j+"printing speciesid hidden ="+Integer.parseInt(cropidhiddenspecies[j])+"and already value as ="+myArray[i]+"i as ="+i);
					 if ( myArray[i] == Integer.parseInt(cropidhiddenspecies[j])) 
					 {
						 System.out.println("Equal"+Integer.parseInt(cropidhiddenspecies[j]));
						 
						 map.put(i, true);
						 if(Integer.parseInt(cropidhiddenspecies[j])!=0)
						  {
						  species.setId(Integer.parseInt(cropidhiddenspecies[j]));
						  }
						  species.setCropid(Integer.parseInt(request.getParameter("cropidhidden")));
						  species.setCrop_botanical_name(botanicalname[j]);
						  species.setFamily(family[j]);
						  species.setInitials(initials[j]);
						  System.out.println("Umesh Printing here 603==== ="+Integer.parseInt(request.getParameter("active")));
						  if(Integer.parseInt(request.getParameter("active")) ==1
							&& !((request.getParameter("active")).equals("null"))
							)
						  {
							  species.setActive(true);
						  }
						  else{species.setActive(false);}
						  
						  species.setCscount(1);
						  s.add(species);
						  
				            break;
				        }else if (cropidhiddenspecies[j].equals("0")) 
				        {
							 System.out.println("Equal and ZERO ="+Integer.parseInt(cropidhiddenspecies[j]));
							
							 map.put(i, true);
							 species = new CropSpecies();
							  species.setCropid(Integer.parseInt(request.getParameter("cropidhidden")));
							  species.setCrop_botanical_name(botanicalname[j]);
							  species.setFamily(family[j]);
							  species.setInitials(initials[j]);
							  System.out.println("Umesh Printing here 626==== ="+Integer.parseInt(request.getParameter("active")));
							  if(Integer.parseInt(request.getParameter("active")) ==1
								&& !((request.getParameter("active")).equals("null"))
								)
							  {
								  species.setActive(true);
							  }
							  else{species.setActive(false);}
							  
							  species.setCscount(1);
							  s.add(species);
							  break; 
					        } else {
				        	System.out.println("GETTING INSIDE NOT REQ");
				                species.setId(myArray[i]);
						        species.setCropid(Integer.parseInt(request.getParameter("cropidhidden")));
								species.setCrop_botanical_name(cropspeciesrepository.getbotanicalNameviaid(myArray[i])); 
								species.setFamily(cropspeciesrepository.getFamilyNameviaid(myArray[i]));
								species.setInitials(cropspeciesrepository.getInitialsviaid(myArray[i]));
								 if(Integer.parseInt(request.getParameter("active")) ==1
									&& !((request.getParameter("active")).equals("null"))
									)
								  {
									  species.setActive(true);
								  }
								  else{species.setActive(false);}
								  species.setCscount(2);
								  s.add(species);
								 
				        	
				            map.put(i, false);
				        }
				}
				
			}
			
			 
		 // System.out.println("Printing the S1 as ="+s1);
		System.out.println(Arrays.asList(map));
		
		 */
		
			
			 try{
				 cropspeciesrepository.enhancecropdetails(Integer.parseInt(request.getParameter("cropidhidden")));
			 }catch(Exception e) {System.out.println("Trace On 676");}
			 
		    List<Object[]> comp_data=cropspeciesrepository.findComp_data_map(Integer.parseInt(request.getParameter("cropidhidden")));
				 
			   
			 for(int j=0 ;j<cropidhiddenspecies.length;j++) 
			 {
				   CropSpecies species = new CropSpecies();	 
					
					 if ( comp_data.contains(Integer.parseInt(cropidhiddenspecies[j]))) 
					 {
						 System.out.println("YES CONTAINS ="+Integer.parseInt(cropidhiddenspecies[j]));
						 if(Integer.parseInt(cropidhiddenspecies[j])!=0)
						  {
						  species.setId(Integer.parseInt(cropidhiddenspecies[j]));
						  }
						  species.setCropid(Integer.parseInt(request.getParameter("cropidhidden")));
						  species.setCrop_botanical_name(botanicalname[j]);
						  species.setFamily(family[j]);
						  species.setInitials(initials[j]);
						  System.out.println("TRACE ON  696 ==== ="+Integer.parseInt(request.getParameter("active")));
						  if(Integer.parseInt(request.getParameter("active")) ==1
							&& !((request.getParameter("active")).equals("null"))
							)
						  {
							  species.setActive(true);
						  }
						  else{species.setActive(false);}
						  
						  species.setCscount(1);
						  species.setModifiedby(userdeail.getId().longValue());
						  species.setModifiedbyip(request.getRemoteAddr());
						  species.setModifiedon(ts);
						  s.add(species);
						  System.out.println("Before removing for j="+j+" comp_data="+comp_data);
						  comp_data.remove(new Integer(Integer.parseInt(cropidhiddenspecies[j])));
						 System.out.println("After removing for j="+j+"new comp_data="+comp_data);
						  // cropspeciesrepository.enhancecropdetailscscount(Integer.parseInt(cropidhiddenspecies[j]));
					 }else if(cropidhiddenspecies[j].equals("0"))
				        {
							 System.out.println("ZERO and not present= "+Integer.parseInt(cropidhiddenspecies[j]));
							
							  species.setCropid(Integer.parseInt(request.getParameter("cropidhidden")));
							  species.setCrop_botanical_name(botanicalname[j]);
							  species.setFamily(family[j]);
							  species.setInitials(initials[j]);
							  species.setModifiedby(userdeail.getId().longValue());
							  species.setModifiedbyip(request.getRemoteAddr());
							  species.setModifiedon(ts);
							  System.out.println("TRACE 716 ==== ="+Integer.parseInt(request.getParameter("active")));
							  if(Integer.parseInt(request.getParameter("active")) ==1
								&& !((request.getParameter("active")).equals("null"))
								)
							  {
								  species.setActive(true);
							  }
							  else{species.setActive(false);}
							  
							  species.setCscount(1);
							  s.add(species);
							 
					        }
				}
		 
			
			
		 crops.setCropspecies(s); 	
		 addcropservice.save(crops);
		 
		if(comp_data.size()>0)
		{
			
			System.out.println("REMAINING VALUE AFTER REMOVING ="+comp_data);Integer[] myArray = new Integer[comp_data.size()];
			comp_data.toArray(myArray);
			//System.out.println("Printing the array as="+myArray.length);
			//System.out.println("Printing the cropid hidden as "+cropidhiddenspecies.length);
			HashMap<Integer, Boolean> map = new HashMap<>();
			for (int i=0;i<myArray.length;i++) {
			
					/*
					 * Integer r= (Integer) comp_data.get(i).length;
					 * System.out.println("Printing r as ="+r);
					 */
			     //int valuealready = (int)Array.get(r, 0);
				int valuealready = myArray[i];
			     System.out.println("Printing the value as ="+valuealready);
			     cropspeciesrepository.enhancecropdetailscropid(valuealready,Integer.parseInt(request.getParameter("cropidhidden")));
			}
		}
			
		  List<CropGroup> Crop_Group = addcropgroupservice.listall();
			model.addAttribute("Crop_Group", Crop_Group);
			
			
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("edit");
			act.setUrl("/editcrops");
			activitylogservice.save(act);
			 
		}
		
		
		 
		return "redirect:/getcrops";
	}

	
}
