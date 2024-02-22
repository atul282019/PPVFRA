package com.ppvfra.controller;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Arrays;
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
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.InternalUser;
import com.ppvfra.entity.OfficeDetails;
import com.ppvfra.entity.RegistrarJurisdictionCrops;
import com.ppvfra.entity.RegistrarJurisdictionOffice;
import com.ppvfra.entity.Role;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AddCropGroupRepository;
import com.ppvfra.repository.AddCropsRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.OfficeDetailsRepository;
import com.ppvfra.repository.RegistrarJurisdictionCropsRepository;
import com.ppvfra.repository.RegistrarJurisdictionOfficeRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.RegistrarJurisdictionCropsService;
import com.ppvfra.service.RoleService;
import com.ppvfra.service.UsersService;

@Controller
public class RegistrarJurisdictionController {

	@Autowired
	ModulesRepository modulesrepository;

	@Autowired
	UserRepository userrepository;

	@Autowired
	RoleService roleservice;

	@Autowired
	UsersService usersservice;

	@Autowired
	AddCropsRepository addcroprep;

	@Autowired
	AddCropGroupRepository addcropgrprep;

	@Autowired
	OfficeDetailsRepository officedetailsrep;

	@Autowired
	RegistrarJurisdictionCropsRepository registrarjurisdictioncropsrepository;

	@Autowired
	Role_ModulesRepository rolemodulerepository;

	@Autowired
	RegistrarJurisdictionCropsService registrarjurisdictioncropsservice;

	@Autowired
	RegistrarJurisdictionOfficeRepository registrarjurisdictionofficerepository;

	@Autowired
	ActivityLogService activitylogservice;
	

	 @Autowired
	RoleAssignRepository roleassignrepository;
	 
	// comments
	// this method is used to get the view of registrar jurisdiction and for showing
	// saved data
	
	  @RequestMapping(value = "/getregistrar", method = RequestMethod.GET) public
	  String getRegistrar(Model model,HttpServletRequest request)
	  {
		  /// GettingLogged in user 
	 int userid = 0;
	 User userdeail = null;
	 
	  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); if
	  (principal instanceof UserDetails)
	  { 
		  String username = ((UserDetails)principal).getUsername();
		  userdeail =userrepository.getUserDetail(username); userid = userdeail.getId();
	  
	  } 
	  else 
	  { String username = principal.toString();
	  
	  } /// end Getting Logged in user
	  
	  List<Object[]> modulelist =modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	  
	  List<Object[]> modulelist2 =modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	/*  List<RegistrarJurisdictionCrops> registrarjurisdictionlist = registrarjurisdictionservice.listall();
	  model.addAttribute("registrarjurisdictionlist", registrarjurisdictionlist);  */
	  
	 
	 // List<User> userselections = userrepository.findAll();
	  List<InternalUser> userselections = userrepository.getinternalusers();
	  model.addAttribute("userselections",userselections);
	  
	  //Umesh Adding here on 20-12-2019 active clause
	  //List<Crops> cropselections = addcroprep.findAll();
	  List<Crops> cropselections = addcroprep.getAllActiveCrops();
	  model.addAttribute("cropselections",cropselections);
	  
	  //List<OfficeDetails> officeselections = officedetailsrep.findAll();
	  List<OfficeDetails> officeselections = officedetailsrep.officedetails();
	  model.addAttribute("officeselections",officeselections);
	  
	  
	 List<Object[]> registrarjurisdictionlist = registrarjurisdictioncropsrepository.findAllCrops();
	System.out.println("tttt-----"+registrarjurisdictionlist);
	 if(registrarjurisdictionlist != null) 
	 {
	   model.addAttribute("registrarjurisdictionlist",registrarjurisdictionlist);
	 } 
	  StringBuffer url=request.getRequestURL(); String
	  val=url.substring(url.lastIndexOf("/"),url.length());
	 
	  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val); 
	  if(actd !=null)
	  { Object actd_viewtrue=null;
	  
	  model.addAttribute("actd",actd);
	  
	  // System.out.println("PRINTING contains"+actd.contains("View"));
	  
	  if(actd.contains("View")) { actd_viewtrue ="valpresent"; //
	  System.out.println("PRINING TRUEEEEE"+actd_viewtrue);
	  
	  model.addAttribute("actd_viewtrue",actd_viewtrue); }
	  
	  }  
	  
	  //Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
		//Umesh Adding ends here 
			
	  return "admin/master_registrar_jurisdiction"; 
	  
	  } 
	 
 
	// comments
	// this method is used to get the form for adding registrar jurisdiction
	@RequestMapping(value = "/addnewregistrar", method = RequestMethod.GET)
	public String addRegistrar(Model model,HttpServletRequest request) {

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

		RegistrarJurisdictionCrops registrarjurisdiction = new RegistrarJurisdictionCrops();
		model.addAttribute("registrarjurisdiction", registrarjurisdiction);

		RegistrarJurisdictionOffice registrarjurisdictionoffice = new RegistrarJurisdictionOffice();
		model.addAttribute("registrarjurisdictionoffice", registrarjurisdictionoffice);

		List<User> user = usersservice.listall();
		model.addAttribute("userid", user);

		List<Role> roleid = roleservice.listall();
		model.addAttribute("roleid", roleid);

		//List<Crops> bindcrops = addcroprep.findAll();
		List<Crops> bindcrops = addcroprep.getAllActiveCrops();
		model.addAttribute("bindcrops", bindcrops);

		//List<OfficeDetails> bindlocations = officedetailsrep.findAll();
		List<OfficeDetails> bindlocations = officedetailsrep.officedetails();
		model.addAttribute("bindlocations", bindlocations);
		
		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is ="+dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("view");
		act.setUrl("/assignrole");

		activitylogservice.save(act);
		  
		StringBuffer url=request.getRequestURL(); 
		String val=url.substring(url.lastIndexOf("/"),url.length());
		  //System.out.println("PPPPP==="+val);
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
		  if(actd !=null)
		 {
			  Object actd_viewtrue=null;
		      model.addAttribute("actd",actd);
		      if(actd.contains("View")) 
		      { 
		    	  actd_viewtrue ="valpresent"; //
		          model.addAttribute("actd_viewtrue",actd_viewtrue);
		      }
		 }
		  
		//Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
		  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
			//Umesh Adding ends here  
				
		return "admin/master_add_registrar_jurisdiction";
	}

	@RequestMapping(value = "/addregistrarjurisdiction", method = RequestMethod.POST)
	public String addRegistrarJurisdiction(
			/*@Valid */
			@ModelAttribute(value = "registrarjurisdiction") RegistrarJurisdictionCrops registrarjurisdiction,
			/*BindingResult bindingResult,*/ Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		String username = "";
		System.out.println("Trace on 267 ------ "+request.getParameter("userid"));
		int userid_find = Integer.parseInt(request.getParameter("userid"));
		/*
		 if (bindingResult.hasErrors()) {
			System.out.println("Trace on line number 270 --------Inside Error --------------");
			
			int userid = 0;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
				User userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
			} else {
				username = principal.toString();
			}
			
			List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			model.addAttribute("modulelist", modulelist);

			List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			model.addAttribute("modulelist2", modulelist2);

			List<User> user = usersservice.listall();
			model.addAttribute("userid", user);

			List<Role> roleid = roleservice.listall();
			model.addAttribute("roleid", roleid);

			List<Crops> bindcrops = addcroprep.findAll();
			model.addAttribute("bindcrops", bindcrops);
			System.out.println("Trace on line number 297 --------Inside Error --------------");
			return "admin/master_add_registrar_jurisdiction";
		} else*/ {
			
			System.out.println("Trace on line number 301 --------Inside Else --------------");
			String crop[] = request.getParameterValues("crops");
			

			if (!(request.getParameter("userid").equals("null")) && !(request.getParameter("userid").equals(""))) {
				int uid = Integer.parseInt(request.getParameter("userid"));
				registrarjurisdictioncropsrepository.removeCropDetails(uid);
				System.out.println("Trace on line number 307 --------Inside Else --------------");
			}
			
			System.out.println("Trace on line number 311 --------Inside Else --------------"+crop.length);
			HashSet s = new HashSet();
			for (int j = 0; j < crop.length; j++) {
				
				System.out.println("length=" + crop.length);
				RegistrarJurisdictionCrops rjc = new RegistrarJurisdictionCrops();
				rjc.setRoleid(Integer.parseInt(request.getParameter("roleid")));
				rjc.setUserid(Integer.parseInt(request.getParameter("userid")));
				rjc.setCrops(Integer.parseInt(crop[j]));

				s.add(rjc);

			}
			registrarjurisdictioncropsrepository.saveAll(s);

		}
		return "redirect:/editregistrarjurisdictioncrops/" + userid_find;
	}

	@RequestMapping(value = "/registrarjurisdictionoffice", method = RequestMethod.POST)
	public String addRegistrarJurisdictionOffice(
			@Valid @ModelAttribute(value = "registrarjurisdictionoffice") RegistrarJurisdictionOffice registrarjurisdictionoffice,
			BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		String username = "";
		System.out.println("Printing the user value as = "+request.getParameter("userid"));
		int userid_find = Integer.parseInt(request.getParameter("userid"));
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

			List<User> user = usersservice.listall();
			model.addAttribute("userid", user);

			List<Role> roleid = roleservice.listall();
			model.addAttribute("roleid", roleid);

			List<OfficeDetails> bindlocations = officedetailsrep.findAll();
			model.addAttribute("bindlocations", bindlocations);

			return "admin/master_add_registrar_jurisdiction";
		} else {

			String office[] = request.getParameterValues("office");

			if (!(request.getParameter("userid").equals("null")) && !(request.getParameter("userid").equals(""))) {
				int uid = Integer.parseInt(request.getParameter("userid"));
				registrarjurisdictionofficerepository.removeOfficeDetails(uid);
			}

			HashSet s = new HashSet();
			for (int j = 0; j < office.length; j++) {
				RegistrarJurisdictionOffice rjo = new RegistrarJurisdictionOffice();
				rjo.setRoleid(Integer.parseInt(request.getParameter("roleid")));
				rjo.setUserid(Integer.parseInt(request.getParameter("userid")));
				rjo.setOffice(Integer.parseInt(office[j]));

				s.add(rjo);

			}
			registrarjurisdictionofficerepository.saveAll(s);

		}
		// return "redirect:/addnewregistrar";
		return "redirect:/getregistrar";
	}

	@RequestMapping(value = "/editregistrarjurisdictioncrops/{userid_find}", method = RequestMethod.GET)
	public ModelAndView editByRegistrarJurisdictionId(@PathVariable(name = "userid_find") Integer id) {
		ModelAndView model = new ModelAndView("admin/edit_master_registrar_jurisdiction");

		/// Getting Logged in user
		int userid = 0;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			User userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId();

		} else {
			String username = principal.toString();

		}
		/// end Getting Logged in user

		//Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
		List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userid); 
		model.addObject("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addObject("rolespresent",rolespresent);
		//Umesh Adding ends here 
			
		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		model.addObject("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		model.addObject("modulelist2", modulelist2);

		List<User> user = usersservice.listall();
		model.addObject("userid", user);

		List<Role> roleid = roleservice.listall();
		model.addObject("roleid", roleid);

		List<Crops> bindcrops = addcroprep.findAll();
		model.addObject("bindcrops", bindcrops);

		List<RegistrarJurisdictionCrops> registrarjurisdiction1 = registrarjurisdictioncropsrepository
				.getAll_Details(id);
		model.addObject("registrarjurisdiction1", registrarjurisdiction1);

		RegistrarJurisdictionCrops registrarjurisdiction = new RegistrarJurisdictionCrops();
		model.addObject("registrarjurisdiction", registrarjurisdiction);

		List<OfficeDetails> bindlocations = officedetailsrep.findAll();
		model.addObject("bindlocations", bindlocations);

		RegistrarJurisdictionOffice registrarjurisdictionoffice = new RegistrarJurisdictionOffice();
		model.addObject("registrarjurisdictionoffice", registrarjurisdictionoffice);
        
		String val="/getregistrar";
		
		  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
		 if(actd !=null)
		 {
			 Object actd_viewtrue=null;
			 model.addObject("actd",actd);
			 
			 if(actd.contains("View"))
			 {
				 actd_viewtrue ="valpresent";
				 model.addObject("actd_viewtrue",actd_viewtrue);
			 }
			 
		 }
		 
			
		return model;
	}
}