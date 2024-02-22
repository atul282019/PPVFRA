package com.ppvfra.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppvfra.common.MailCommons;
import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.ApplicantTypes;
import com.ppvfra.entity.ApplicationContacts;
import com.ppvfra.entity.ApplicationContacts3;
import com.ppvfra.entity.ApplicationContacts7;
import com.ppvfra.entity.ApplicationConventionCountries;
import com.ppvfra.entity.ApplicationDocuments;
import com.ppvfra.entity.ApplicationParentalline;
import com.ppvfra.entity.ApplicationParentallineOthers;
import com.ppvfra.entity.ApplicationPayments;
import com.ppvfra.entity.ApplicationTechnicalQuestionnaire;
import com.ppvfra.entity.ApplicationTechnicalQuestionnaireReference;
import com.ppvfra.entity.Applications;
import com.ppvfra.entity.Country;
import com.ppvfra.entity.CropSpecies;
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.InternalUser;
import com.ppvfra.entity.LogTable;
import com.ppvfra.entity.Nationality;
import com.ppvfra.entity.States;
import com.ppvfra.entity.User;
import com.ppvfra.repository.AccessTypesRepository;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AddCropsRepository;
import com.ppvfra.repository.AdduserRepository;
import com.ppvfra.repository.ApplicantRegistrationRepository;
import com.ppvfra.repository.ApplicationProcessingRepository;
import com.ppvfra.repository.ApplicationRepository;
import com.ppvfra.repository.ApplicationTypesRepository;
import com.ppvfra.repository.CompanyRegistrationRepository;
import com.ppvfra.repository.CountryRepository;
import com.ppvfra.repository.CropSpeciesRepository;
import com.ppvfra.repository.DistrictRepository;
import com.ppvfra.repository.DocumentChecklistRepository;
import com.ppvfra.repository.InstitutionRegistrationRepository;
import com.ppvfra.repository.LoginLogRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.StateRepository;
import com.ppvfra.repository.UserAddRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.repository.UserTypesRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.AddCropGroupService;
import com.ppvfra.service.AddDusTestCenterService;
import com.ppvfra.service.AddcropService;
import com.ppvfra.service.AdduserService;
import com.ppvfra.service.ApplicationDocumentsService;
import com.ppvfra.service.ApplicationsService;
import com.ppvfra.service.CountryService;
import com.ppvfra.service.CropSpeciesService;
import com.ppvfra.service.DashboardService;
import com.ppvfra.service.DistrictService;
import com.ppvfra.service.ModuleService;
import com.ppvfra.service.NationalityService;
import com.ppvfra.service.RoleService;
import com.ppvfra.service.StateService;
import com.ppvfra.service.UserAddService;
import com.ppvfra.service.UserService;
import com.ppvfra.service.UsersService;

@Controller
public class DashboardController {

	@Autowired
	UserService userservice;

	@Autowired
	AdduserService addusrservice;

	@Autowired
	StateRepository staterep;
	@Autowired
	CountryRepository countryrep;

	@Autowired
	RoleRepository addrolerep;

	@Autowired
	AddcropService addcropservice;
	@Autowired
	AddCropsRepository addcroprepository;
	@Autowired
	AddCropGroupService addcropgroupservice;
	@Autowired
	CropSpeciesRepository cropspeciesrepository;
	@Autowired
	AddDusTestCenterService adddustestcenterservice;

	@Autowired
	StateService stateservice;

	@Autowired
	CountryService countryservice;
	
	@Autowired		   
	DistrictService districtservice;

	@Autowired
	AdduserRepository adduserrep;

	@Autowired
	UserRepository userrepository;

	@Autowired
	RoleAssignRepository roleassignrepository;

	@Autowired
	RoleRepository rolerepository;

	@Autowired
	RoleService roleservice;

	@Autowired
	UsersService usersservice;

	@Autowired
	AddCropsRepository addcroprep;

	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	ModuleService moduleservice;
	
	@Autowired
	ModulesRepository modulerepository;

	@Autowired
	AccessTypesRepository accesstyperepository;
	
	@Autowired
	CropSpeciesService cropspeciesservice;

	@Autowired
	Role_ModulesRepository rolemodulerepository;

	@Autowired
	Environment env;

	@Autowired
	ApplicantRegistrationRepository applicant_registration_rep;
	
	@Autowired
	UserAddService useraddservice;
	
	@Autowired
	UserTypesRepository usertypesrepository;
	
	@Autowired
	ApplicationRepository applicationrepository;
	
	@Autowired
	ApplicationTypesRepository applicanttypes;								   
	
	@Autowired
	DocumentChecklistRepository documentchecklistrepository;
	
	@Autowired
	ApplicationDocumentsService applicationdocumentsservice;
	
	@Autowired
	ApplicationsService applicationservice;
	
	@Autowired
	NationalityService nationalityservice;
	
	@Autowired
	LoginLogRepository loginlogrepository;
	
	@Autowired
	ActivityLogRepository activitylogrepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	CompanyRegistrationRepository company_registration_repository;
	
	@Autowired
	InstitutionRegistrationRepository instition_registration_repository;
	
	@Autowired
	DistrictRepository districtrepository;
	
	@Autowired
	UserAddRepository useraddrepository;
	
	@Autowired
	ApplicationProcessingRepository applicationprocessrepository;
	
	@Autowired
	DashboardService dashboardservice;
	
	
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 50;
	 
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdminDashboard(Model model,HttpServletRequest request) {

		/// Getting Logged in user
			int userid = 0;
			String username ="";
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					  username = ((UserDetails) principal).getUsername();
					User userdeail = userrepository.getUserDetail(username);
					userid = userdeail.getId();
					//System.out.println("Priting Loggin userdetail: " + userdeail.getId());
					
					} else {
					  username = principal.toString();
					}
				/// end Getting Logged in user
			 List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  model.addAttribute("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  model.addAttribute("modulelist2", modulelist2);
		  
		  User userdeail = userrepository.getUserDetail(username);
		  userid = userdeail.getId();
		  //System.out.println("Trace Printing Role Of User="+userdeail.getRoles().toString());
		  
		  List<Object[]> usertypeid_role= userrepository.getUsertypeviauserid(userid);
		  if(usertypeid_role !=null)
		  {
		  System.out.println("USERTYPE ID NOT NULL === "+usertypeid_role);
		  model.addAttribute("usertypeid_role",usertypeid_role);
		  }
		  
		  System.out.println("Getting the userid 298 = "+userid);
		  String companyname= userrepository.getCompanyName(userid);
		  String institutename= userrepository.getInstitution_name(userid);
		  String applicantname= userrepository.getApplicant_name(userid);
		  
		if( companyname!=null)
		{
		Long count_accepted =  applicant_registration_rep.getcount_of_Accepted_records(companyname);
		model.addAttribute("count_accepted", count_accepted);
		
		Long count_rejected =  applicant_registration_rep.getcount_of_Rejected_records(companyname);
		model.addAttribute("count_rejected",count_rejected);
		
		Long count_new      =  applicant_registration_rep.getcount_of_New_records(companyname);
		model.addAttribute("count_new",count_new);
		 
		 Long count_active   =  applicant_registration_rep.getcount_of_Active_records(companyname);
		 model.addAttribute("count_active",count_active);	
		}
		
		System.out.println("INSTITUTE NAME="+institutename);
		if( institutename!=null)
		{
		Long ins_count_accepted =  applicant_registration_rep.getinst_accepted_records(institutename);
		model.addAttribute("ins_count_accepted", ins_count_accepted);
		
		Long ins_count_rejected =  applicant_registration_rep.getinst_rejected_records(institutename);
		model.addAttribute("ins_count_rejected",ins_count_rejected);
		
		Long ins_count_new      =  applicant_registration_rep.getinst_new_records(institutename);
		model.addAttribute("ins_count_new",ins_count_new);
		Long ins_count_active   =  applicant_registration_rep.getcount_of_Active_records(institutename);
		model.addAttribute("ins_count_active",ins_count_active);	
		}
		
		
		
		//-------Dashboard KPI start------------
		Long company_total_count =  company_registration_repository.get_company_total();
		model.addAttribute("company_total_count", company_total_count);
		
		Long institute_total_count =  company_registration_repository.get_institution_total();
		model.addAttribute("institute_total_count", institute_total_count);
		
		Long applicant_total_count =  company_registration_repository.get_applicant_total();
		model.addAttribute("applicant_total_count", applicant_total_count);
		
		Long company_count_accepted =  company_registration_repository.get_company_count("Accepted");
		if(company_count_accepted==null) {
			company_count_accepted=Long.parseLong(""+0);
		}
		model.addAttribute("company_count_accepted", company_count_accepted);
		
		Long company_count_rejected =  company_registration_repository.get_company_count("Rejected");
		if(company_count_rejected==null) {
			company_count_rejected=Long.parseLong(""+0);
		}
		model.addAttribute("company_count_rejected", company_count_rejected);
		
		Long company_count_new =  company_registration_repository.get_company_count_new();
		if(company_count_new==null) {
			company_count_new=Long.parseLong(""+0);
		}
		model.addAttribute("company_count_new", company_count_new);
		
		
		Long institution_count_accepted =  company_registration_repository.get_institution_count("Accepted");
		if(institution_count_accepted==null) {
			institution_count_accepted=Long.parseLong(""+0);
		}
		model.addAttribute("institution_count_accepted", institution_count_accepted);
		
		Long institution_count_rejected =  company_registration_repository.get_institution_count("Rejected");
		if(institution_count_rejected==null) {
			institution_count_rejected=Long.parseLong(""+0);
		}
		model.addAttribute("institution_count_rejected", institution_count_rejected);
		
		Long institution_count_new =  company_registration_repository.get_institution_count_new();
		if(institution_count_new==null) {
			institution_count_new=Long.parseLong(""+0);
		}
		model.addAttribute("institution_count_new", institution_count_new);
		
		
		Long applicant_count_accepted =  company_registration_repository.get_applicant_count("Accepted");
		if(applicant_count_accepted==null) {
			applicant_count_accepted=Long.parseLong(""+0);
		}
		model.addAttribute("applicant_count_accepted", applicant_count_accepted);
		
		Long applicant_count_rejected =  company_registration_repository.get_applicant_count("Rejected");
		if(applicant_count_rejected==null) {
			applicant_count_rejected=Long.parseLong(""+0);
		}
		model.addAttribute("applicant_count_rejected", applicant_count_rejected);
		
		Long applicant_count_new =  company_registration_repository.get_applicant_count_new();
		if(applicant_count_new==null) {
			applicant_count_new=Long.parseLong(""+0);
		}
		model.addAttribute("applicant_count_new", applicant_count_new);
		
		Long application_count_new =  company_registration_repository.get_application_count(1); //0 = new application(need to confirm the status code before going live)
		if(application_count_new==null) {
			application_count_new=Long.parseLong(""+0);
		}
		model.addAttribute("application_count_new", application_count_new);
		
		Long application_count_accepted =  company_registration_repository.get_application_count(2); //2 = Accepted
		if(application_count_accepted==null) {
			application_count_accepted=Long.parseLong(""+0);
		}
		model.addAttribute("application_count_accepted", application_count_accepted);
		
		Long application_count_rejected =  company_registration_repository.get_application_count(3); //3 = Rejected
		if(application_count_rejected==null) {
			application_count_rejected=Long.parseLong(""+0);
		}
		model.addAttribute("application_count_rejected", application_count_rejected);
		
		Long application_count_submitted =  company_registration_repository.get_application_count(4); //3 = Submitted
		if(application_count_submitted==null) {
			application_count_submitted=Long.parseLong(""+0);
		}
		model.addAttribute("application_count_submitted", application_count_submitted);
		
		Long application_count_closed =  company_registration_repository.get_application_count(5); //5 = Application Closed
		if(application_count_closed==null) {
			application_count_closed=Long.parseLong(""+0);
		}
		model.addAttribute("application_count_closed", application_count_closed);
		
		Long application_count_withdrawn =  company_registration_repository.get_application_count(6); //6 = Application Withdrawn
		if(application_count_withdrawn==null) {
			application_count_withdrawn=Long.parseLong(""+0);
		}
		model.addAttribute("application_count_withdrawn", application_count_withdrawn);
		
		Long application_count_cert_issued =  company_registration_repository.get_application_count(7); //7 = Certificate to be issued
		if(application_count_cert_issued==null) {
			application_count_cert_issued=Long.parseLong(""+0);
		}
		model.addAttribute("application_count_cert_issued", application_count_cert_issued);
	
		
		//System.out.println("application Is ="+application_count_new);
		//-------Dashboard KPI end------------
		
		
//Umesh Finding the role of logged in user:---04-01-2020
		/*
		  StringBuffer url=request.getRequestURL();
			String val=url.substring(url.lastIndexOf("/")-19,url.lastIndexOf("/"));
			System.out.println("PPPPP==="+val);
			List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
			 if(actd !=null)
			 {
				 Object actd_viewtrue=null;
				 Object actd_addtrue=null;
				 model.addAttribute("actd",actd);
				 
				 // System.out.println("PRINTING contains IN INST " +actd.contains("View")); 
				 
				 
				 if(actd.contains("View"))
					 {
					 actd_viewtrue ="valpresent";
					// System.out.println("PRINING IN INST."  +actd_viewtrue); 
					
					model.addAttribute("actd_viewtrue",actd_viewtrue);
					 
					 String val1 ="Accept_Applicant";
					 List<Object[]> accesstypeprocess_data = rolemodulerepository.getAccesstypes_Processdata(userid,val1);
					//	System.out.println("PRINTING IN INST="+accesstypeprocess_data.contains("Add"));
						if(accesstypeprocess_data.contains("Add"))
						{
							actd_addtrue ="addpresent";
							model.addAttribute("actd_addtrue",actd_addtrue);	
						}
					
					 }
		*/
//Umesh Adding ends here ------04-01-2020		
		LogTable lt = new LogTable();
		lt.setIpaddress(request.getRemoteAddr());
		lt.setUsername(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is ="+dt);
		lt.setLogin_time_stamp(dt);
		lt.setLogintype("Success");
	 
	 
		loginlogrepository.save(lt);
		
	//Umesh Adding here on 14-01-2020 -------
	//Added Here For Name And Role Showing in header
		
		List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		
		
		if( applicantname!=null)
		{
		Long count_saved_app      =  applicant_registration_rep.getcount_of_applicantsaved(userid);
		model.addAttribute("count_saved_app",count_saved_app);
		 
		 Long count_submitted_app   =  applicant_registration_rep.getcount_of_applicantsubbmitted(userid);
		 model.addAttribute("count_submitted_app",count_submitted_app);	
		}
		
		
		Long app_process_rep = applicationprocessrepository.presentcountval1();
		
		if(app_process_rep !=null)
			model.addAttribute("app_process_rep",app_process_rep);
		
		System.out.println("User IDD= "+userid);
		Long app_process_inbox = applicationprocessrepository.presentcountval2(userid);
		
		if(app_process_inbox !=null)
			model.addAttribute("app_process_inbox",app_process_inbox);
		
		
		Long app_process_outbox = applicationprocessrepository.presentcountval3(userid);
		
		if(app_process_outbox !=null)
			model.addAttribute("app_process_outbox",app_process_outbox);
		
		model.addAttribute("userid_app",userid);
		
	//Umesh Adding ends here  
		return "admin/admindashboard";
	}

	@RequestMapping(value = "/getmenu", method = RequestMethod.GET)
	public void getMenuDashboard(Model model) {

		/// Getting Logged in user
		int userid = 0;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				User userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				//System.out.println("Priting Loggin userdetail: " + userdeail.getId());
				} else {
				String username = principal.toString();
				}
			/// end Getting Logged in user
			//System.out.println("Priting Loggin user id: " + userid);
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	 
	}

	@RequestMapping(value = "/getinformative", method = RequestMethod.GET)
	public String getInformativDashboard(Model model) {
		/// Getting Logged in user
		int userid = 0;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				User userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				//System.out.println("Priting Loggin userdetail: " + userdeail.getId());
				} else {
				String username = principal.toString();
				}
			/// end Getting Logged in user
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	 
		return "admin/informatic_dashboard";
	}

	// sargam--- code started
	@RequestMapping(value = "/applicationstatusPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String applicationStatusPage(@RequestBody Map<String, Integer> data, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addAttribute("max", data.get("max"));
		redirectAttributes.addAttribute("offset", data.get("offset"));
		return "redirect:/applicationstatus";
	}
    //sargam ---code ends 
	
	@RequestMapping(value = "/applicationstatus", method = RequestMethod.GET)
	public String applicationStatus(Model model, HttpServletRequest request, Optional<Integer> max,
			Optional<Integer> offset) {
		/// Getting Logged in user
		
		int userid = 0;
		User userdeail = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId();
			// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
			// System.out.println("Priting Loggin user: " + username);
		} else {
			String username = principal.toString();
		}
		/// end Getting Logged in user

		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		model.addAttribute("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		model.addAttribute("modulelist2", modulelist2);

		int pageitem = max.orElse(INITIAL_PAGE_SIZE);
		int pageNumber = (offset.orElse(0) < 1) ? INITIAL_PAGE : offset.get() - 1;

		int srno = pageitem * pageNumber;
		List<Object[]> admin_viewapplicationpage = applicationrepository.getapplication_adminview();
		
		// code to add here --sargam
		PagedListHolder<Object> page = new PagedListHolder(admin_viewapplicationpage);
		page.setPageSize(pageitem); // number of items per page
		page.setPage(pageNumber); // set to first page

		int totalPages = page.getPageCount();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("srno", srno);
		// Retrieval
		List<Object> admin_viewapplication = page.getPageList(); // a List which represents the current page

		// code ends here

		if (admin_viewapplication != null) {
			model.addAttribute("admin_viewapplication", admin_viewapplication);
		}

		StringBuffer url = request.getRequestURL();
		String val = url.substring(url.lastIndexOf("/"), url.length());
		// System.out.println("PPPPP==="+val);

		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid, val);
		if (actd != null) {
			Object actd_viewtrue = null;

			model.addAttribute("actd", actd);

			// System.out.println("PRINTING contains"+actd.contains("View"));

			if (actd.contains("View")) {
				actd_viewtrue = "valpresent";
				// System.out.println("PRINING TRUEEEEE"+actd_viewtrue);

				model.addAttribute("actd_viewtrue", actd_viewtrue);

			}

		}

		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is =" + dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("view");
		act.setUrl(val);

		activitylogservice.save(act);

		// Umesh Adding here on 14-01-2020 -------
		// Added Here For Name And Role Showing in header
		List<Object[]> usrname_header_val = userrepository.getUser_Nameviaid(userdeail.getId());
		model.addAttribute("usrname_header_val", usrname_header_val);
		List<Object[]> rolespresent = roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent", rolespresent);
		// Umesh Adding ends here

		return "admin/application_status";
	}

	@RequestMapping(value = "/getapplicationlist", method = RequestMethod.GET)
	public String getApplicationList(Model model) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail=null;
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
			 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	//Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
	  	List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		//Umesh Adding ends here  
	 
		return "admin/application_list";
	}
	
 //code for excel extraction
	
  	@RequestMapping(value = "/getexcelappsta", method = RequestMethod.GET)
  	public void getApplicantdetails_excel(HttpServletResponse response) throws IOException {
  		response.setContentType("application/octet-stream");
  		String headerKey = "Content-Disposition";
  		DateFormat  dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
  		String currentDateTime = dateformatter.format(new Date());
  		String fileName = "applicantion_status" + currentDateTime + ".xlsx";
  		String headerValue = "attachment; filename=" + fileName;
  		
  		response.setHeader(headerKey, headerValue);
  		List<Object[]> admin_viewapplication = applicationrepository.getapplication_adminview();
		
  		List<States> stateselections = staterep.findAll();
  		List<Districts> districtselection = districtrepository.findAll();
  		
  		dashboardservice.export(response,admin_viewapplication,stateselections,districtselection);
  	}
  	
  	//code for excel extraction

	@RequestMapping(value = "/getnew_extend_edv_english", method = RequestMethod.GET)
	public String getNewExtended(Model model) {
		
		Applications applications = new Applications();
		model.addAttribute("applications", applications);
		
		ApplicationTechnicalQuestionnaire technicalquestion = new ApplicationTechnicalQuestionnaire();
		model.addAttribute("technicalquestion", technicalquestion);
		
		ApplicationContacts contacts = new ApplicationContacts();
		model.addAttribute("contacts", contacts);
		
		ApplicationContacts3 contacts3 = new ApplicationContacts3();
	    model.addAttribute("contact3",contacts3);
		  
		  ApplicationContacts7 contacts7 = new ApplicationContacts7();
		  model.addAttribute("contact7",contacts7);
		
		  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
		  model.addAttribute("applicationdocuments",applicationdocuments);
		
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
				 }
			/// end Getting Logged in user
			List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			model.addAttribute("userapplicant", userapplicant);
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  //List<Country> Country = countryservice.listall();
	  List<Country> Country = countryrep.getConutry();
	    model.addAttribute("Country",Country);  
	    
	  //List<States> State = stateservice.listall();
	  List<States> State=staterep.getstates();
	    model.addAttribute("State", State);
	  
	    //  List<Districts> District = districtservice.listall();
	    List<Districts> District = districtrepository.getalldistricts();
	    model.addAttribute("District", District);
	    
	      //List <Crops> addcroplist = addcropservice.listall();
	      List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		  model.addAttribute("addcroplist", addcroplist);
		  
		  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
		  model.addAttribute("cropspecieslist", cropspecieslist);
		  
		 List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
		 model.addAttribute("applicanttypelist", applicanttypelist);
		 
		 List<Nationality> nationality = nationalityservice.listall();
		  model.addAttribute("nationality",nationality); 
		 
		  ApplicationContacts contact = new ApplicationContacts();
		  model.addAttribute("contact",contact);
		  
		  ApplicationConventionCountries convention = new ApplicationConventionCountries();
		  model.addAttribute("convention",convention);
		  
		  ApplicationParentalline parental = new ApplicationParentalline();
		  model.addAttribute("parental",parental);
		  
		  ApplicationPayments payment = new ApplicationPayments();
		  model.addAttribute("payment",payment);
			 
		  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
		  model.addAttribute("parentalother",parentalother);
		  
		  ApplicationTechnicalQuestionnaireReference tqreference = new ApplicationTechnicalQuestionnaireReference();
		  model.addAttribute("tqreference",tqreference);
		  
		 model.addAttribute("editmode", 0);
		 List<Object[]> document_checklistdata=documentchecklistrepository.getformdetails();
		 model.addAttribute("document_checklistdata", document_checklistdata);
		//Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
		 	List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
			
			//This Below Line Is Compulsory
			//Do not Delete For New Case
			model.addAttribute("f1_id",0);
			//Ends 
			//Ends
			
		//Umesh Adding ends here 
		return "admin/new_extend_edv_english_edit";
	}
	
	@RequestMapping(value = "/getnew_extend_edv_english2", method = RequestMethod.GET)
	public String getNewExtendedForm2(Model model) {
		/// Getting Logged in user
		
		
		Applications applications = new Applications();
		model.addAttribute("applications", applications);
		
		ApplicationTechnicalQuestionnaire technicalquestion = new ApplicationTechnicalQuestionnaire();
		model.addAttribute("technicalquestion", technicalquestion);
		
		ApplicationContacts contacts = new ApplicationContacts();
		model.addAttribute("contacts", contacts);
		
		ApplicationContacts3 contacts3 = new ApplicationContacts3();
	    model.addAttribute("contact3",contacts3);
		  
		  ApplicationContacts7 contacts7 = new ApplicationContacts7();
		  model.addAttribute("contact7",contacts7);
		
		  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
		  model.addAttribute("applicationdocuments",applicationdocuments);
		
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
				 }
			/// end Getting Logged in user
			List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			model.addAttribute("userapplicant", userapplicant);
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  //List<Country> Country = countryservice.listall();
	  List<Country> Country = countryrep.getConutry();
	    model.addAttribute("Country",Country);  
	    
	  //List<States> State = stateservice.listall();
	  List<States> State=staterep.getstates();
	    model.addAttribute("State", State);
	  
	    //  List<Districts> District = districtservice.listall();
	    List<Districts> District = districtrepository.getalldistricts();
	    model.addAttribute("District", District);
	    
	      //List <Crops> addcroplist = addcropservice.listall();
	      List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		  model.addAttribute("addcroplist", addcroplist);
		  
		  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
		  model.addAttribute("cropspecieslist", cropspecieslist);
		  
		 List<ApplicantTypes> applicanttypelist2 =  applicanttypes.applicanttypeform2();
		 model.addAttribute("applicanttypelist2", applicanttypelist2);
		 
		 List<Nationality> nationality = nationalityservice.listall();
		  model.addAttribute("nationality",nationality); 
		 
		  ApplicationContacts contact = new ApplicationContacts();
		  model.addAttribute("contact",contact);
		  
		  ApplicationConventionCountries convention = new ApplicationConventionCountries();
		  model.addAttribute("convention",convention);
		  
		  ApplicationParentalline parental = new ApplicationParentalline();
		  model.addAttribute("parental",parental);
		  
		  ApplicationPayments payment = new ApplicationPayments();
		  model.addAttribute("payment",payment);
			 
		  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
		  model.addAttribute("parentalother",parentalother);
		  
		  ApplicationTechnicalQuestionnaireReference tqreference = new ApplicationTechnicalQuestionnaireReference();
		  model.addAttribute("tqreference",tqreference);
		  
		 model.addAttribute("editmode", 0);
		 List<Object[]> document_checklistdata=documentchecklistrepository.getform2details();
		 model.addAttribute("document_checklistdata", document_checklistdata);
		//Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
		 	List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
			
			//This Below Line Is Compulsory
			//Do not Delete For New Case
			model.addAttribute("f1_id",0);
			//Ends 
			//Ends
			
		//Umesh Adding ends here 
		return "admin/new_extend_edv_english2_edit";
	}

	@RequestMapping(value = "/getnew_extend_edv_english3", method = RequestMethod.GET)
	public String getNewExtendedForm3(Model model) {
		
		
		Applications applications = new Applications();
		model.addAttribute("applications", applications);
		
		ApplicationTechnicalQuestionnaire technicalquestion = new ApplicationTechnicalQuestionnaire();
		model.addAttribute("technicalquestion", technicalquestion);
		
		ApplicationContacts contacts = new ApplicationContacts();
		model.addAttribute("contacts", contacts);
		
		ApplicationContacts3 contacts3 = new ApplicationContacts3();
	    model.addAttribute("contact3",contacts3);
		  
		  ApplicationContacts7 contacts7 = new ApplicationContacts7();
		  model.addAttribute("contact7",contacts7);
		
		  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
		  model.addAttribute("applicationdocuments",applicationdocuments);
		
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
				 }
			/// end Getting Logged in user
			List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			model.addAttribute("userapplicant", userapplicant);
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  //List<Country> Country = countryservice.listall();
	  List<Country> Country = countryrep.getConutry();
	    model.addAttribute("Country",Country);  
	    
	  //List<States> State = stateservice.listall();
	  List<States> State=staterep.getstates();
	    model.addAttribute("State", State);
	  
	    //  List<Districts> District = districtservice.listall();
	    List<Districts> District = districtrepository.getalldistricts();
	    model.addAttribute("District", District);
	    
	      //List <Crops> addcroplist = addcropservice.listall();
	      List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		  model.addAttribute("addcroplist", addcroplist);
		  
		  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
		  model.addAttribute("cropspecieslist", cropspecieslist);
		  
		 List<ApplicantTypes> applicanttypelist3 =  applicanttypes.applicanttypeform3();
		 model.addAttribute("applicanttypelist3", applicanttypelist3);
		 
		 List<Nationality> nationality = nationalityservice.listall();
		  model.addAttribute("nationality",nationality); 
		 
		  ApplicationContacts contact = new ApplicationContacts();
		  model.addAttribute("contact",contact);
		  
		  ApplicationConventionCountries convention = new ApplicationConventionCountries();
		  model.addAttribute("convention",convention);
		  
		  ApplicationParentalline parental = new ApplicationParentalline();
		  model.addAttribute("parental",parental);
		  
		  ApplicationPayments payment = new ApplicationPayments();
		  model.addAttribute("payment",payment);
			 
		  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
		  model.addAttribute("parentalother",parentalother);
		  
		  ApplicationTechnicalQuestionnaireReference tqreference = new ApplicationTechnicalQuestionnaireReference();
		  model.addAttribute("tqreference",tqreference);
		  
		 model.addAttribute("editmode", 0);
		 List<Object[]> document_checklistdata=documentchecklistrepository.getformdetailsf3();
		 model.addAttribute("document_checklistdata", document_checklistdata);
		//Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
		 	List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
			
			//This Below Line Is Compulsory
			//Do not Delete For New Case
			model.addAttribute("f1_id",0);
			//Ends 
			//Ends
			
		//Umesh Adding ends here 
		return "admin/new_extend_edv_english3_edit";
	}
	
	@RequestMapping(value = "/applicant_application_status", method = RequestMethod.GET)
	public String getApplicantApplicationStatus(Model model) {
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
			 	}
			/// end Getting Logged in user
			 		
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  model.addAttribute("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  model.addAttribute("modulelist2", modulelist2);
		//Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
		  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
		//Umesh Adding ends here 
		return "admin/applicant_application_status";
	}

	
	 
	
	@RequestMapping(value = "/getloginlog", method = RequestMethod.GET)
	public String getLoginLog(Model model,HttpServletRequest request) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail= null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
				 } else {
				String username = principal.toString();
			 	}
			/// end Getting Logged in user
		 	
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	 
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
	 System.out.println("PRINING TRUEEEEE"+actd_viewtrue); 
				
				model.addAttribute("actd_viewtrue",actd_viewtrue);
			    }
			 
		 }
		
		 //Umesh Commenting it on 21-12-2019 fetching the current login log
		 //List<LogTable> logdata=  loginlogrepository.findAll();
		 List<LogTable> logdata=  loginlogrepository.logtab();
		
			
			if(logdata !=null)
				model.addAttribute("logdata",logdata);
			
			 //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
			//Umesh Adding ends here  
			
			
		return "admin/login_log";
	}

	@RequestMapping(value = "/getactivitylog", method = RequestMethod.GET)
	public String getActivityLog(Model model,HttpServletRequest request) {
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
				 }
			/// end Getting Logged in user
			 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	 // List<ActivityLogTable> activitylog = activitylogrepository.findAll();
	   List<ActivityLogTable> activitylog = activitylogrepository.activitytab();
	  if(activitylog!= null)
		  model.addAttribute("activitylog",activitylog);
	 
		/* User_Role user_role= new User_Role(); */
	  
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
	   
	   //Umesh Adding here on 14-01-2020 -------
		//Added Here For Name And Role Showing in header
			
	   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
		//Umesh Adding ends here  
		
		
		
	 
		return "admin/activity_log";
	}

	@RequestMapping(value = "/activity_log", method = RequestMethod.GET)
	public String activity_role(Model addrole,HttpServletRequest request) {
		/// Getting Logged in user
		int userid = 0;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				User    userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
			 	} else {
				String username = principal.toString();
				//System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
		 	
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  addrole.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  addrole.addAttribute("modulelist2", modulelist2);
	  
	  Object actlog = null;
		addrole.addAttribute("actlog", actlog);
	 
		return "activity_log";
	}

	@RequestMapping(value = "/add_user", method = RequestMethod.GET)
	public String add_user(Model addrole) {
		/// Getting Logged in user
		int userid = 0;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				User userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
			 } else {
				String username = principal.toString();
			 }
			/// end Getting Logged in user
			 	
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  addrole.addAttribute("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  addrole.addAttribute("modulelist2", modulelist2);
		 

			InternalUser adduser = new InternalUser();
	
			addrole.addAttribute("adduser", adduser);
			return "add_user";
	}

	@RequestMapping(value = { "/ppvfra/check_cropname" }, method = RequestMethod.POST)
	@ResponseBody
	public String crop_nameavailabilitycheck(@RequestBody String crop_name, HttpServletRequest request) {
		String value = "";
		System.out.println("Trace Printing Crop_Name Check=" + crop_name);

		List<Crops> crop_namechk = addcroprep.crop_namecheck(crop_name);
		if (crop_namechk.size() >= 1) {
			value = "taken";
		} else {
			value = "not taken";
		}

		return value;
 
	} 

			@RequestMapping(value = "/view_details_applicants/{status}", method = RequestMethod.GET)
			public String getdata_view_applicant(Model model,@PathVariable String status) {

				/// Getting Logged in user
					int userid = 0;
					String username ="";
						Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
						if (principal instanceof UserDetails) {
							  username = ((UserDetails) principal).getUsername();
							User userdeail = userrepository.getUserDetail(username);
							userid = userdeail.getId();
							//System.out.println("Priting Loggin userdetail: " + userdeail.getId());
							
							} else {
							  username = principal.toString();
							}
						/// end Getting Logged in user
					 List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
				  model.addAttribute("modulelist", modulelist);
				
				  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
				  model.addAttribute("modulelist2", modulelist2);
				  
				  User userdeail = userrepository.getUserDetail(username);
				  userid = userdeail.getId();
				  String companyname= userrepository.getCompanyName(userid);
				if( companyname!=null)
				{
					if(status.equals("Accepted")) {
				//System.out.println("Trace in NEW PATH ACCEPTED");
				List<Object[]> data_viewing =  applicant_registration_rep.getDatavia_accepted_name(companyname,status);
				model.addAttribute("data_viewing", data_viewing);
				}else if(status.equals("Rejected"))
					{
					//System.out.println("Trace in NEW PATH REJECTED");
					List<Object[]> data_viewing =  applicant_registration_rep.getDatavia_rejected_name(companyname,status);
						model.addAttribute("data_viewing", data_viewing);
					}else if(status.equals("New"))
					{
						//System.out.println("Trace in NEW PATH NEW");
						List<Object[]> data_viewing =  applicant_registration_rep.getDatavia_new_name(companyname);
						model.addAttribute("data_viewing", data_viewing);
					}
					
					
					 //Umesh Adding here on 14-01-2020 -------
					//Added Here For Name And Role Showing in header
						
				   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				   model.addAttribute("usrname_header_val",usrname_header_val);
						List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
						model.addAttribute("rolespresent",rolespresent);
					//Umesh Adding ends here 
				//model.addAttribute("data_viewing", data_viewing);
				
			String mainp="view_details_applicants";		
			model.addAttribute("mainp",mainp);
			model.addAttribute("subp",status);
			
				}			
			return "/applicant_view_statuswise";
		}
			
	//Umesh adding here for institution on  08-01-2020
			
			@RequestMapping(value = "/view_details_institution/{status}", method = RequestMethod.GET)
			public String getdata_view_institution(Model model,@PathVariable String status) {

				/// Getting Logged in user
					int userid = 0;
					String username ="";
						Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
						if (principal instanceof UserDetails) {
							  username = ((UserDetails) principal).getUsername();
							User userdeail = userrepository.getUserDetail(username);
							userid = userdeail.getId();
							//System.out.println("Priting Loggin userdetail: " + userdeail.getId());
							
							} else {
							  username = principal.toString();
							}
						/// end Getting Logged in user
					 List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
				  model.addAttribute("modulelist", modulelist);
				
				  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
				  model.addAttribute("modulelist2", modulelist2);
				  
				  User userdeail = userrepository.getUserDetail(username);
				  userid = userdeail.getId();
				  String institutename= userrepository.getInstitution_name(userid);
				if( institutename!=null)
				{
					if(status.equals("Accepted")) {
				//System.out.println("Trace in NEW PATH ACCEPTED");
				List<Object[]> data_viewing =  applicant_registration_rep.getaccepted_institution(institutename,status);
				model.addAttribute("data_viewing", data_viewing);
				}else if(status.equals("Rejected"))
					{
					//System.out.println("Trace in NEW PATH REJECTED");
					List<Object[]> data_viewing =  applicant_registration_rep.getrejected_institution(institutename,status);
						model.addAttribute("data_viewing", data_viewing);
					}else if(status.equals("New"))
					{
						//System.out.println("Trace in NEW PATH NEW");
						List<Object[]> data_viewing =  applicant_registration_rep.getnew_institution(institutename);
						model.addAttribute("data_viewing", data_viewing);
					}
				//model.addAttribute("data_viewing", data_viewing);
				}
				
			//Added Here For Name And Role Showing in header
					
			 List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
					List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
					model.addAttribute("rolespresent",rolespresent);
				//Umesh Adding ends here  
	
			model.addAttribute("mainp","view_details_institution");
			model.addAttribute("subp",status);
			
			return "/applicant_view_statuswise_institution";
		}
		
			
//Umesh Adding ends Here			
		@RequestMapping(value = "/getapplication", method = RequestMethod.GET)
			public String getNewApplication(Model model,HttpServletRequest request) {
				/// Getting Logged in user
				int userid = 0;
				User userdeail=null;
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if (principal instanceof UserDetails) {
						String username = ((UserDetails) principal).getUsername();
						  userdeail = userrepository.getUserDetail(username);
						userid = userdeail.getId();
						// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
						//System.out.println("Priting Loggin user: " + username);
					} else {
						String username = principal.toString();
						}
					/// end Getting Logged in user
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addAttribute("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addAttribute("modulelist2", modulelist2);	 
			      //List<Country> Country = countryservice.listall();
			      List<Country> Country = countryrep.getConutry();
				  model.addAttribute("Country",Country);
				
				  
		//List<States> State = stateservice.listall();
	  List<States> State=staterep.getstates();
	  model.addAttribute("State", State);
	  
				 // List<Districts> District = districtservice.listall();
	  List<Districts> District = districtrepository.getalldistricts();
				  model.addAttribute("District", District);
				  
				  //List <Crops> addcroplist = addcropservice.listall();
				  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
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
						
					 		
							 }
						 
					 }
					 
					 
					//Umesh Adding here on 14-01-2020 -------
						//Added Here For Name And Role Showing in header
							
					 List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
						model.addAttribute("usrname_header_val",usrname_header_val);
							List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
							model.addAttribute("rolespresent",rolespresent);
						//Umesh Adding ends here  
							
							
				  return "admin/application";
			}
			
//Umesh Adding here -----on 04-01-2020
			
 @RequestMapping(value = "/applicationstatus/{status}", method = RequestMethod.GET)
 public String application_statuschart(@PathVariable("status") String status,Model model,HttpServletRequest request) {
 	/// Getting Logged in user
	 	int userid = 0;
		 User userdeail = null;
			 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 if (principal instanceof UserDetails) {
				 String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
					 userid = userdeail.getId();
						// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
						//System.out.println("Priting Loggin user: " + username);
					} else {
						String username = principal.toString();
					}
					/// end Getting Logged in user
							
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addAttribute("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addAttribute("modulelist2", modulelist2);
			  
			   if(status.equals("ApplicationClosed"))
			   {
				   status="Application Closed";
			   }else if(status.equals("ApplicationWithdrawn")){
				   status="Application Withdrawn";
			   }else if(status.equals("Certificatetobeissued")) {
				   status="Certificate to be issued";
			   } 
			   
			  List<Object[]> admin_viewapplication=applicationrepository.getapplication_datastatuswise(status);
				if(admin_viewapplication!= null)
				{
				  model.addAttribute("admin_viewapplication",admin_viewapplication);
				}
			  
			  StringBuffer url=request.getRequestURL();
				String val=url.substring(url.lastIndexOf("/")-18,url.lastIndexOf("/"));
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
					act.setUrl(val);
					
					activitylogservice.save(act);
			 
				 //Umesh Adding here on 14-01-2020 -------
					//Added Here For Name And Role Showing in header
						
				   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
					model.addAttribute("usrname_header_val",usrname_header_val);
						List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
						model.addAttribute("rolespresent",rolespresent);
					//Umesh Adding ends here
						
				return "admin/application_status";
			}
			
 
 
 
	@RequestMapping(value = "/ppvfra/forgetpassword", method = RequestMethod.POST)
	public String forgetPassword(@RequestParam("username") String username,HttpServletRequest request, RedirectAttributes redirectAttributes)
 {
		
	 System.out.println("TRACE INSIDE FORGET PASSWORD CONTROLLER ______1614"+username);
	 String emailId="";
	 emailId=username;
	 
	 int user = userrepository.getdetailvia_mail(emailId);
		if(user!=0){
		new Thread(new Runnable() {
			@Override
			public void run() {
	String rawpassword="ppvfra_2020";
	String newpass="";
	int uid=0;
	int t=0;
	uid=user;
	
	System.out.println("FORGOT USER VALUE="+user);
	BCryptPasswordEncoder passwdchange = new BCryptPasswordEncoder(12);
	newpass= passwdchange.encode(rawpassword);
	t=useraddrepository.update_forgetpass(newpass, uid); 
	 if(t==1)
	 {
	try {
		sendchangePasswordMail(rawpassword,username);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	}
	}).start();
	redirectAttributes.addFlashAttribute("message", "EMail To Your Mail Address "+username+" Sent Successfully.Please Check Your Mail");
	return "redirect:/login";
	} 
else{ redirectAttributes.addFlashAttribute("message",
"Email Id entered is not registered or Not Active. Please sign up first."); }
return "redirect:/login";
	}
 
 
  boolean sendchangePasswordMail(String pass,String mailadd) throws IOException {
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
						sb.append("Dear User You Initiated Your Forgot Password Request.");
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
  
//Adding ends -----			
}