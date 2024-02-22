package com.ppvfra.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppvfra.common.MailCommons;
import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.CompanyRegistration;
import com.ppvfra.entity.Country;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.EditInstitutionRegistration;
import com.ppvfra.entity.InstitutionRegistration;
import com.ppvfra.entity.InstitutionTypes;
import com.ppvfra.entity.InternalUser;
import com.ppvfra.entity.States;
import com.ppvfra.entity.User;
import com.ppvfra.entity.User_Role;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AdduserRepository;
import com.ppvfra.repository.ApplicantRegistrationRepository;
import com.ppvfra.repository.ApplicantRoleRepository;
import com.ppvfra.repository.CompanyRegistrationRepository;
import com.ppvfra.repository.DistrictRepository;
import com.ppvfra.repository.InstitutionRegistrationRepository;
import com.ppvfra.repository.InstitutionTypeRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.StateRepository;
import com.ppvfra.repository.UserAddRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.repository.CountryRepository;
import com.ppvfra.repository.UserTypesRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.ApplicantRegistrationService;
import com.ppvfra.service.CompanyRegistrationService;
import com.ppvfra.service.CountryService;
import com.ppvfra.service.DistrictService;
import com.ppvfra.service.EditApplicantRegistrationService;
import com.ppvfra.service.EditCompanyRegistrationService;
import com.ppvfra.service.EditInstitutionRegistrationService;
import com.ppvfra.service.InstitutionRegistrationService;
import com.ppvfra.service.InstitutionTypesService;
import com.ppvfra.service.StateService;

@Controller
public class InstitutionRegistrationController {
	
	@Autowired
	ApplicantRegistrationService applicantregistrationservice;
	
	@Autowired
	StateService stateservice;
	
	@Autowired
	DistrictService districtservice;
	
	@Autowired
	InstitutionRegistrationService institutionregistrationservice;
	
	@Autowired
	CountryService countryservice;
	
	@Autowired
	AdduserRepository adduserrepository ;
	@Autowired
	CountryRepository countryrep;
	@Autowired
	InstitutionTypesService institutiontypesservice;
	
	@Autowired
	UserTypesRepository usertypesrepository;
	
	@Autowired
	RoleAssignRepository roleassignrepository;

	@Autowired
	RoleRepository rolerepository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	InstitutionRegistrationRepository institutionregistrationrepository;
	
	@Autowired
	EditInstitutionRegistrationService editinstitutionregistrationservice;
	
	@Autowired
	StateRepository staterep;	
	@Autowired
	DistrictRepository districtrepository;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	InstitutionTypeRepository institutiontyperepository;
	@Autowired
	UserAddRepository useraddrepository;
	
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 10;

	@RequestMapping(value="/InstitutionRegistration", method=RequestMethod.GET)
    public String addInstitutionRegistration(Model model)
    {
    	InstitutionRegistration institutionregistration = new InstitutionRegistration();
    	List<InstitutionTypes> InstitutionType = institutiontypesservice.listall();
        model.addAttribute("InstitutionType",InstitutionType);
    	//List<Country> Country = countryservice.listall();
        List<Country> Country = countryrep.getConutry();
	    model.addAttribute("Country",Country);
		
	    //List<States> State = stateservice.listall();
	    List<States> State=staterep.getstates();
	    model.addAttribute("State", State);
	    
	    //List<Districts> District = districtservice.listall();
	    List<Districts> District = districtrepository.getalldistricts();
	    model.addAttribute("District", District);
	    
	    model.addAttribute("institutionregistration", institutionregistration);
	    return "institution_registration";
    }
    
    @RequestMapping(value="/newinstitutionregistration", method=RequestMethod.POST)
	public String InstitutionRegistrationMethod(@Valid @ModelAttribute(value="institutionregistration") InstitutionRegistration institutionregistration ,BindingResult bindingResult,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes)
    {  
		if(bindingResult.hasErrors())
		{  	
			List<InstitutionTypes> InstitutionType = institutiontypesservice.listall();
            model.addAttribute("InstitutionType",InstitutionType);		
			//List<Country> Country = countryservice.listall();
            List<Country> Country = countryrep.getConutry();
		    model.addAttribute("Country",Country);
		    
		    //List<States> State = stateservice.listall();
		    List<States> State=staterep.getstates();
		    model.addAttribute("State", State);
		    
		    //List<Districts> District = districtservice.listall();
		    List<Districts> District = districtrepository.getalldistricts();
		    model.addAttribute("District", District);
		    System.out.println("binding error");
			return "institution_registration";
		} 
		else 
	    { 
				Date date = new Date(); 
				Timestamp ts = new Timestamp(date.getTime());
				List<InstitutionTypes> InstitutionType = institutiontypesservice.listall();
	            model.addAttribute("InstitutionType",InstitutionType);
				//List<Country> Country = countryservice.listall();
	            List<Country> Country = countryrep.getConutry();
			    model.addAttribute("Country",Country);			    
			   // List<States> State = stateservice.listall();
			    List<States> State=staterep.getstates();
			    model.addAttribute("State", State);
			    
			    //List<Districts> District = districtservice.listall();
			    List<Districts> District = districtrepository.getalldistricts();
			    model.addAttribute("District", District);
			    institutionregistrationservice.toString();
			    institutionregistration.setAuthor_Countryid(Integer.parseInt(request.getParameter("Author_Countryid")));
			    if(Integer.parseInt(request.getParameter("Author_Countryid")) ==1)
			    {
			    	institutionregistration.setAuthor_statecode(Integer.parseInt(request.getParameter("author_statecode")));
			    	institutionregistration.setAuthor_districtcode(Integer.parseInt(request.getParameter("author_districtcode")));
			    }
			    institutionregistration.setCreatedon(ts);
			    institutionregistration.setCreatedbyip(request.getRemoteAddr());
			    institutionregistration.setActive(false);
			    institutionregistration = institutionregistrationservice.save(institutionregistration);
			    if(institutionregistration.getId() !=0 )
				 {	 
			    	    redirectAttributes.addFlashAttribute("message", "Thank You for Registration. Your Account is currently inactive. You will be notified via SMS and Email once your request has been accepted by admin.");
				    	
			    	    int institutionid = institutionregistration.getId();
				    	int typeid = usertypesrepository.getIdByType("Institution");
				    	int roleid = rolerepository.getIdByName("Institution");
				    	InternalUser internaluser = new InternalUser();
				    	internaluser.setInstituteid(institutionid);
				    	internaluser.setName(request.getParameter("Author_Name").trim());
				    	internaluser.setFullname(request.getParameter("Author_Name").trim());
				    	internaluser.setEmail(request.getParameter("Author_Email").trim());
				    	internaluser.setUsername(request.getParameter("username").trim());
				    	internaluser.setDesignation(request.getParameter("Author_Designation").trim());
				    	internaluser.setContactno(request.getParameter("Author_Mobile").trim());
				    	internaluser.setMobile_number(request.getParameter("Author_Mobile").trim());
				    	internaluser.setUsertypeid(typeid);
				    	internaluser.setRole(""+roleid);
				    	internaluser.setFirstname(request.getParameter("Author_Name").trim());
				    	//internaluser.setCreatedon(ts);
				    	//internaluser.setCreatedby(internaluser.getId());
				    	internaluser = adduserrepository.save(internaluser);
				    	
					    	 if(internaluser.getId() !=0 )
					    	 {
					    		 User_Role userrole = new User_Role();
					    		 userrole.setUser_id(internaluser.getId());
					    		 userrole.setRole_id(roleid);
					    		 userrole.setCreatedon(ts);
					    		 userrole.setCreatedby(internaluser.getId());
					    		 userrole =  roleassignrepository.save(userrole);
					    		 }
					    internaluser.setIsactive(false);
				 }
			    else
				{
					redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
				}
	    }	
		 return "redirect:/login";
	  }
    
    // sargam--- code started
 	@RequestMapping(value = "/getinstitutionPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
 	public String getInstitutionPage(@RequestBody Map<String, Integer> data, Model model, HttpServletRequest request,
 			RedirectAttributes redirectAttributes) {

 		redirectAttributes.addAttribute("max", data.get("max"));
 		redirectAttributes.addAttribute("offset", data.get("offset"));
 		return "redirect:/getinstitution";
 	}
     //sargam ---code ends
    
    @RequestMapping(value = "/getinstitution", method = RequestMethod.GET)
	public String getInstitution(Model model,HttpServletRequest request, Optional<Integer> max,
			Optional<Integer> offset) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail = null;
		
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				//System.out.println("Priting Loggin user: " + username);
			} else {
				String username = principal.toString();
				//System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
				
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	 
	  List<InstitutionRegistration> institutereg = institutionregistrationrepository.findAll();
	  model.addAttribute("institutereg",institutereg);
	  //sargam code start
	  
	  int pageitem = max.orElse(INITIAL_PAGE_SIZE);
	  int pageNumber = (offset.orElse(0) < 1) ? INITIAL_PAGE : offset.get() - 1;
	  int srno = pageitem * pageNumber;
		
		
		PagedListHolder<Object> page = new PagedListHolder(institutereg);
		page.setPageSize(pageitem); // number of items per page
		page.setPage(pageNumber); // set to first page

		int totalPages = page.getPageCount();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("srno", srno);
		// Retrieval
		List<Object> instituteregList = page.getPageList(); // a List which represents the current page

		model.addAttribute("instituteregList", instituteregList);	  
	  // sargam code end
	  
	  
	  //Adding Here For Institution Name-- 27/12/2019
	  List<InstitutionTypes> instype = institutiontyperepository.instype();
	  model.addAttribute("instype",instype);
	  //Adding ends here ---- 
	  
	  List<States> stateselections = staterep.findAll();
	  model.addAttribute("stateselections",stateselections);
	  
	  List<Districts> districtselection =districtrepository.findAll();
	  model.addAttribute("distsel",districtselection);
	  
	  InstitutionRegistration instreg = new InstitutionRegistration();
	  model.addAttribute("instreg",instreg);
	  
		StringBuffer url=request.getRequestURL();
		String val=url.substring(url.lastIndexOf("/"),url.length());
	//	System.out.println("PPPPP==="+val);
		  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
		 if(actd !=null)
		 {
			 Object actd_viewtrue=null;
			 Object actd_addtrue=null;
			 model.addAttribute("actd",actd);
			 
		//	  System.out.println("PRINTING contains IN INST " +actd.contains("View")); 
			 
			 
			 if(actd.contains("View"))
				 {
				 actd_viewtrue ="valpresent";
		//		 System.out.println("PRINING IN INST."  +actd_viewtrue); 
				
				model.addAttribute("actd_viewtrue",actd_viewtrue);
				 
				 String val1 ="Accept_Institution";
				 List<Object[]> accesstypeprocess_data = rolemodulerepository.getAccesstypes_Processdata(userid,val1);
		//			System.out.println("PRINTING IN INST="+accesstypeprocess_data.contains("Add"));
					if(accesstypeprocess_data.contains("Add"))
					{
						actd_addtrue ="addpresent";
						model.addAttribute("actd_addtrue",actd_addtrue);	
					}
				
				 }
			 
		 }
		 
		 ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("view");
			act.setUrl("/getinstitution");
			
			activitylogservice.save(act);
		   
		   //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
		   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
			//Umesh Adding ends here 
				
		   
		return "admin/institutions";
	}
    

  //code for excel extraction
	
  	@RequestMapping(value = "/getexcelins", method = RequestMethod.GET)
  	public void getInstitutedetails_excel(HttpServletResponse response) throws IOException {
  		response.setContentType("application/octet-stream");
  		String headerKey = "Content-Disposition";
  		DateFormat  dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
  		String currentDateTime = dateformatter.format(new Date());
  		String fileName = "institute_" + currentDateTime + ".xlsx";
  		String headerValue = "attachment; filename=" + fileName;
  		
  		response.setHeader(headerKey, headerValue);
  		List<InstitutionRegistration> institutereg = institutionregistrationrepository.findAll();
  		List<InstitutionTypes> instype = institutiontyperepository.instype();
  		List<States> stateselections = staterep.findAll();
  		List<Districts> districtselection = districtrepository.findAll();
  		
  		institutionregistrationservice.export(response,institutereg,instype,stateselections,districtselection);
  	}
  	
  	//code for excel extraction
    
    @RequestMapping(value = "/view_institutionsdetails/{id}", method = RequestMethod.GET)
	public String viewbyid(@PathVariable(name = "id") Integer id,Model mav,HttpServletRequest req) {

		 int userid = 0;
		 User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				  } else {
				String username = principal.toString();
				//System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
			//System.out.println("Priting Loggin user id: " + userid);
			 //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
			   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			   mav.addAttribute("usrname_header_val",usrname_header_val);
			   List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			   mav.addAttribute("rolespresent",rolespresent);
			//Umesh Adding ends here 
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  mav.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  mav.addAttribute("modulelist2", modulelist2);
	   
	  List<States> office_state = staterep.findAll();
	  mav.addAttribute("office_state",office_state);
	
	  InstitutionRegistration institutionregistration= institutionregistrationservice.get(id);
		mav.addAttribute("institutionregistration",institutionregistration);
		
	List<Object[]> details = institutionregistrationrepository.findDetails(id);
	mav.addAttribute("details",details);
	
	ActivityLogTable act = new ActivityLogTable();
	act.setIpaddress(req.getRemoteAddr());
	act.setActivityby(userdeail.getUsername());
	Date dt = new Date();
	System.out.println("Current date Is ="+dt);
	act.setLogin_time_stamp(dt);
	act.setActivity("view");
	act.setUrl("/view_institutiondetails");
	
	activitylogservice.save(act);
   
		return "view_institution_registration";
	}
 


@RequestMapping(value = "/editinstitution/{id}", method = RequestMethod.GET)
	public ModelAndView editById(@PathVariable(name = "id") Integer id,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("admin/edit_institution_registration");
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
						System.out.println("Priting else Loggin user: " + username);
					}
					/// end Getting Logged in user
					System.out.println("Priting Loggin user id: " + userid);
					
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  mav.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  mav.addObject("modulelist2", modulelist2);
			 
		List<InstitutionTypes> InstitutionType = institutiontypesservice.listall();
		mav.addObject("InstitutionType",InstitutionType);
		//List<Country> Country = countryservice.listall();
		List<Country> Country = countryrep.getConutry();
		mav.addObject("Country",Country);			    
	   // List<States> State = stateservice.listall();
		List<States> State=staterep.getstates();
	    mav.addObject("State", State);
	   
	    // List<Districts> District = districtservice.listall();
	    List<Districts> District = districtrepository.getalldistricts();
	    mav.addObject("District", District);
		EditInstitutionRegistration institutionregistration = editinstitutionregistrationservice.get(id);
		String username11 = userrepository.getUser_Name(id);
		if(username11 !=null)
			{
			System.out.println("trace=="+username11);
			mav.addObject("username11",username11);
			
			}
		mav.addObject("institutionregistration", institutionregistration);
		
		
	 
		String val="/getinstitution";
	//	System.out.println("PPPPP==="+val);
		  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
		 if(actd !=null)
		 {
			 Object actd_viewtrue=null;
			 
			 mav.addObject("actd",actd);
			 
		//	  System.out.println("PRINTING contains IN INST " +actd.contains("View")); 
			 
			 
			 if(actd.contains("View"))
				 {
				 actd_viewtrue ="valpresent";
		//		 System.out.println("PRINING IN INST."  +actd_viewtrue); 
				
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
			act.setUrl("/edit_institutiondetails");
		
			 //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				mav.addObject("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				mav.addObject("rolespresent",rolespresent);
			//Umesh Adding ends here 
				
		mav.setViewName("admin/edit_institution_registration");
		
		
		return mav;
	} 
    
	@RequestMapping(value="/editinstitutionregistration", method=RequestMethod.POST)
	public String editInstitutionRegistrationMethod(@Valid @ModelAttribute(value="institutionregistration") EditInstitutionRegistration institutionregistration ,BindingResult bindingResult,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes)
    {  
		if(bindingResult.hasErrors())
		{  	
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
				System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
			System.out.println("Priting Loggin user id: " + userid);
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
			List<InstitutionTypes> InstitutionType = institutiontypesservice.listall();
            model.addAttribute("InstitutionType",InstitutionType);		
			//List<Country> Country = countryservice.listall();
            List<Country> Country = countryrep.getConutry();
		    model.addAttribute("Country",Country);
		    
		    //List<States> State = stateservice.listall();
		    List<States> State=staterep.getstates();
		    model.addAttribute("State", State);
		  //  List<Districts> District = districtservice.listall();
		    List<Districts> District = districtrepository.getalldistricts();
		    model.addAttribute("District", District);
		    System.out.println("binding error");
		   
		    String val="/getinstitution";
			System.out.println("PPPPP==="+val);
			  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
			 if(actd !=null)
			 {
				 Object actd_viewtrue=null;
				 Object actd_addtrue=null;
				 model.addAttribute("actd",actd);
				 
			
				 if(actd.contains("View"))
					 {
					 actd_viewtrue ="valpresent";
			
					model.addAttribute("actd_viewtrue",actd_viewtrue);
					 
					 String val1 ="Accept_Company";
					 List<Object[]> accesstypeprocess_data = rolemodulerepository.getAccesstypes_Processdata(userid,val1);
				
						if(accesstypeprocess_data.contains("Add"))
						{
							actd_addtrue ="addpresent";
							model.addAttribute("actd_addtrue",actd_addtrue);	
						}
					
					 }
				 
			 }
		    
			 ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("editinstitution-error");
				act.setUrl("/edit_institutiondetails");
				activitylogservice.save(act);
				 
		    return "admin/edit_institution_registration";
		} 
		else 
	    { 		
			 
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
			 
				// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
				 } else {
				String username = principal.toString();
				System.out.println("Priting else Loggin user: " + username);
			}
			
			
				Date date = new Date(); 
				Timestamp ts = new Timestamp(date.getTime());
				List<InstitutionTypes> InstitutionType = institutiontypesservice.listall();
	            model.addAttribute("InstitutionType",InstitutionType);
				//List<Country> Country = countryservice.listall();
	            List<Country> Country = countryrep.getConutry();
			    model.addAttribute("Country",Country);			    
			  
			    //  List<States> State = stateservice.listall();
			    List<States> State=staterep.getstates();
			    model.addAttribute("State", State);
			    
			   // List<Districts> District = districtservice.listall();
			    List<Districts> District = districtrepository.getalldistricts();
			    model.addAttribute("District", District);
			    editinstitutionregistrationservice.toString();
			    
			    EditInstitutionRegistration institutionregistration11 = editinstitutionregistrationservice.get(institutionregistration.getId());
				if(institutionregistration11 !=null)
				{
					if(institutionregistration11.getActive()!=null) {
					institutionregistration.setActive(institutionregistration11.getActive());
					}
					
					if(institutionregistration11.getVerificationstatus()!=null) {
					institutionregistration.setVerificationstatus(institutionregistration11.getVerificationstatus());
				      }
					
					if(institutionregistration11.getVerificationremarks()!=null) {
						institutionregistration.setVerificationremarks(institutionregistration11.getVerificationremarks());
					      }
				}
			    
			    institutionregistration.setCreatedon(ts);
			    institutionregistration.setCreatedbyip(request.getRemoteAddr());
			    
			    institutionregistration = editinstitutionregistrationservice.save(institutionregistration);
			   
			    if(institutionregistration.getId() !=0 )
				 {	 redirectAttributes.addFlashAttribute("message", "Data saved successfully.");
				    	int institutionid = institutionregistration.getId();
				    	int typeid = usertypesrepository.getIdByType("Institution");
				    	int roleid = rolerepository.getIdByName("Institution");
				    	System.out.println("trace check 1: "+institutionid);
				    	InternalUser internaluser = new InternalUser();
				    	String userid = userrepository.getUseridvia_Instituteid(institutionid);
				    	if(userid !=null && (Integer.parseInt(userid)) != 0)
				    	{
				    		internaluser.setId(Integer.parseInt(userid));
				    		
				    	}
				    	internaluser.setInstituteid(institutionid);
				    	internaluser.setName(request.getParameter("Author_Name"));
				    	internaluser.setFullname(request.getParameter("Author_Name"));
				    	internaluser.setEmail(request.getParameter("Author_Email"));
				    	internaluser.setUsername(request.getParameter("username"));
				    	internaluser.setDesignation(request.getParameter("Author_Designation"));
				    	internaluser.setContactno(request.getParameter("Author_Mobile"));
				    	internaluser.setMobile_number(request.getParameter("Author_Mobile"));
				    	internaluser.setUsertypeid(typeid);
				    	internaluser.setRole(""+roleid);
				    	internaluser.setFirstname(request.getParameter("Author_Name"));
				    	//internaluser.setCreatedon(ts);
				    	//internaluser.setCreatedby(internaluser.getId());
				    	 String userrole_id="";
				    	 System.out.println("trace Internal user id= "+internaluser);
				         if( internaluser.getId() !=0 )
					    	 {
					    		userrole_id = roleassignrepository.getUserrolevia_Userid(internaluser.getId());
					    	    
					    	 } 
				    	HashSet s = new HashSet();
				    	User_Role s1 = new User_Role();
				    	s1.setId(Long.parseLong(userrole_id));
				    	s1.setUser_id(internaluser.getId());
				    	s1.setRole_id(roleid);
				    	s1.setCreatedby(internaluser.getId());
				    	s1.setCreatedon(ts);
				    	s.add(s1);
				    	internaluser.setUserrole(s);
				    	
				    	
				    	if (institutionregistration11.getVerificationstatus().equals("Accepted"))
						{		
				internaluser.setIsactive(true);
						}else if(institutionregistration11.getVerificationstatus().equals("Rejected"))
								{
							internaluser.setIsactive(false);
									}else {internaluser.setIsactive(null);}

				    	
				    	internaluser = adduserrepository.save(internaluser);
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
				act.setUrl("/edit_institutiondetails");
				activitylogservice.save(act);
			    
	    }
		
		
		 return "redirect:/getinstitution";
	}   
    
    
	@RequestMapping(value = "/acceptrejectinstitute/{id}", method = RequestMethod.POST)
	public String accept_or_reject(@ModelAttribute(value = "instreg") InstitutionRegistration instreg,Model model,HttpServletRequest req,@PathVariable(name = "id") Integer id) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				//System.out.println("Priting Loggin user: " + username);
			} else {
				String username = principal.toString();
				//System.out.println("Priting else Loggin user222222222: " + username);
			}
			/// end Getting Logged in user
				
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	   if(!(req.getParameter("institutestatus").equals("null")) && !(req.getParameter("institutestatus").equals("")))
	   { 
		String statustobeset=req.getParameter("institutestatus");
		String remarks= req.getParameter("remarks");
		//System.out.println("trace=="+statustobeset);
		//System.out.println("trace=="+remarks);
			  
		   //System.out.println("TRACE INSIDE -----NOT NULL");
		   String mip=req.getRemoteAddr();
		   int verifierid=0;
		   String verifierdesignation="";
		   if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				 verifierid= userdeail.getId();
				 String u = userrepository.getAllDetailsOfUser(verifierid);
				 verifierid= userdeail.getId();
				 verifierdesignation = u;
				//System.out.println("Priting Loggin user: " + username);
			}
		   int institute_accepted =0;
		   if(statustobeset.equals("Accepted"))
		   {
		   institute_accepted =institutionregistrationrepository.dataadding(statustobeset,remarks,id,mip,verifierid,verifierdesignation);
//Umesh Adding here on 07-01-2020
		   
		   String resetpass="ppvfra@2020";
		     String mailaddress="";
		     String mailusername="";
			 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12
			 String encodedPassword = encoder.encode(resetpass);
			int a= useraddrepository.update_inst(encodedPassword, id.intValue());
			
			if(a==1)
			{
				List<Object[]> mail= institutionregistrationrepository.institution_email(id.intValue());
				if(mail !=null)
				{
					Object[] r= (Object[]) mail.get(0);
					System.out.println("Printing the object val="+String.valueOf(r[0])+""+String.valueOf(r[1]));
					mailaddress= String.valueOf(r[1]);
					mailusername= String.valueOf(r[2]);
					System.out.println("Printing the mailaddress="+mailaddress);
				}
			try{
				send_accepted_mail(resetpass,mailaddress,mailusername);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		   }else {
			 
			   institute_accepted =institutionregistrationrepository.dataadding1(statustobeset,remarks,id,mip,verifierid,verifierdesignation);
			   
			   String mailaddress1="";
			   String mailusername1="";
			   List<Object[]> mail= institutionregistrationrepository.institution_email(id.intValue());
				{
					Object[] r= (Object[]) mail.get(0);
					System.out.println("Printing the object val="+String.valueOf(r[0])+""+String.valueOf(r[1]));
					mailaddress1= String.valueOf(r[1]);
					mailusername1= String.valueOf(r[2]);
					System.out.println("Printing the mailaddress="+mailaddress1);
				}
				try{
					send_reject_mail(mailaddress1,mailusername1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			 }
		   if(institute_accepted == 1)
		   {
			  if(statustobeset.equals("Accepted"))
				  {institutionregistrationrepository.dataupdate(id);
				  
				  }else {
					  institutionregistrationrepository.dataupdate1(id);
				  }
		   }
	   }
	   
	   ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(req.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is ="+dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("save");
		act.setUrl("/accept_rejectinstitute");
		activitylogservice.save(act);
		 
		return "redirect:/getinstitution";
	}
 
	
//Umesh Adding here on 04-01-2020 .....
	boolean send_accepted_mail(String pass,String mailadd,String username ) throws IOException {
		boolean valreturn = false;

		try {
			new Thread(new Runnable() {
			
				public void run() {
					try {
						System.out.println("Printing MIME RESET PASSWORD");
						MimeMessage mimeMessage = new MailCommons().mimeMessage();
						mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailadd));
						System.out.println("Printing mime message 2");
						mimeMessage.setSubject("Default-Accepted Password");

						StringBuilder sb = new StringBuilder();
						System.out.println("Printing mime message3");
						sb.append("Dear " + username + ", Congrats");
						//sb.append("<br/><br/>Ms " + orgname
						sb.append("<br/><br/>Your Default Password is:  " +pass+ " You May Change It Via Accessing Your Profile Section and then change Password");
						sb.append("<br/><br/>Thank You<br/>Team PPVFRA");

						mimeMessage.setContent(sb.toString(), "text/html; charset=utf-8");
						
						Transport.send(mimeMessage);
						System.out.println("Printing mime message7");
						System.out.println("Printing at last =="+mimeMessage);

					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}

			}).start();

		} catch (Exception e) {
			valreturn = false;
		}
		return valreturn;
	}
   
   boolean send_reject_mail( String mailadd,String username ) throws IOException {
		boolean valreturn = false;

		try {
			new Thread(new Runnable() {
			
				public void run() {
					try {
						System.out.println("Printing MIME RESET PASSWORD");
						MimeMessage mimeMessage = new MailCommons().mimeMessage();
						mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailadd));
						System.out.println("Printing mime message 2");
						mimeMessage.setSubject("Application Not Accepted");

						StringBuilder sb = new StringBuilder();
						System.out.println("Printing mime message3");
						sb.append("Dear " + username + ",");
						//sb.append("<br/><br/>Ms " + orgname
						sb.append("<br/><br/>Your Application is not accepted .");
						sb.append("<br/><br/>Thank You<br/>Team PPVFRA");

						mimeMessage.setContent(sb.toString(), "text/html; charset=utf-8");
						
						Transport.send(mimeMessage);
						System.out.println("Printing mime message7");
						System.out.println("Printing at last =="+mimeMessage);

					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}

			}).start();

		} catch (Exception e) {
			valreturn = false;
		}
		return valreturn;
	}
   
//Adding ends here -----   
	 @RequestMapping(value = "/getinstitution/{status}", method = RequestMethod.GET)
		public String getinstitute_data(@PathVariable("status") String status ,Model model,HttpServletRequest request) {
			/// Getting Logged in user
			int userid = 0;
			User userdeail = null;
			
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					String username = ((UserDetails) principal).getUsername();
					  userdeail = userrepository.getUserDetail(username);
					userid = userdeail.getId();
					//System.out.println("Priting Loggin user: " + username);
				} else {
					String username = principal.toString();
					//System.out.println("Priting else Loggin user: " + username);
				}
				/// end Getting Logged in user
					
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  model.addAttribute("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  model.addAttribute("modulelist2", modulelist2);
		 
		  if(status.equals("New"))
		  {
			  List<InstitutionRegistration> institutereg = institutionregistrationrepository.get_institution_count_new();
			  model.addAttribute("institutereg",institutereg);
		  }
		  else{
			  List<InstitutionRegistration> institutereg = institutionregistrationrepository.getinsitute_data(status);
			  model.addAttribute("institutereg",institutereg);
			  } 
		  //Adding Here For Institution Name-- 27/12/2019
		  List<InstitutionTypes> instype = institutiontyperepository.instype();
		  model.addAttribute("instype",instype);
		  //Adding ends here ---- 
		  
		  List<States> stateselections = staterep.findAll();
		  model.addAttribute("stateselections",stateselections);
		  
		  List<Districts> districtselection =districtrepository.findAll();
		  model.addAttribute("distsel",districtselection);
		  
		  InstitutionRegistration instreg = new InstitutionRegistration();
		  model.addAttribute("instreg",instreg);
		  
			StringBuffer url=request.getRequestURL();
			String val=url.substring(url.lastIndexOf("/")-15,url.lastIndexOf("/"));
			System.out.println("PPPPP==="+val);
			  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
			 if(actd !=null)
			 {
				 Object actd_viewtrue=null;
				 Object actd_addtrue=null;
				 model.addAttribute("actd",actd);
				 
			//	  System.out.println("PRINTING contains IN INST " +actd.contains("View")); 
				 
				 
				 if(actd.contains("View"))
					 {
					 actd_viewtrue ="valpresent";
			//		 System.out.println("PRINING IN INST."  +actd_viewtrue); 
					
					model.addAttribute("actd_viewtrue",actd_viewtrue);
					 
					 String val1 ="Accept_Institution";
					 List<Object[]> accesstypeprocess_data = rolemodulerepository.getAccesstypes_Processdata(userid,val1);
			//			System.out.println("PRINTING IN INST="+accesstypeprocess_data.contains("Add"));
						if(accesstypeprocess_data.contains("Add"))
						{
							actd_addtrue ="addpresent";
							model.addAttribute("actd_addtrue",actd_addtrue);	
						}
					
					 }
				 
			 }
			 
			 ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("view");
				act.setUrl("/getinstitution");
				
				activitylogservice.save(act);
			   
			   //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
					List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
					model.addAttribute("rolespresent",rolespresent);
				//Umesh Adding ends here 
					
			return "admin/institutions";
		}

	
}
