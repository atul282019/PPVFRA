package com.ppvfra.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.Application;
import com.ppvfra.entity.ApplicationCertificates;
import com.ppvfra.entity.ApplicationOnlineQuery;
import com.ppvfra.entity.ApplicationPreGrantOpposition;
import com.ppvfra.entity.ApplicationScrutiny;
import com.ppvfra.entity.ApplicationStsSeedrecieved;
import com.ppvfra.entity.ApplicationStsTest;
import com.ppvfra.entity.Applications;
import com.ppvfra.entity.Assignment_Details;
import com.ppvfra.entity.CandidateVariety;
import com.ppvfra.entity.CropGroup;
import com.ppvfra.entity.DUSTestResults;
import com.ppvfra.entity.InternalUser;
import com.ppvfra.entity.Journal;
import com.ppvfra.entity.MediumTermStorage;
import com.ppvfra.entity.NationalRegister;
import com.ppvfra.entity.OfficeStates;
import com.ppvfra.entity.PublishToPVJ;
import com.ppvfra.entity.Rejuvenation;
import com.ppvfra.entity.Remarks;
import com.ppvfra.entity.Revocation;
import com.ppvfra.entity.Role;
import com.ppvfra.entity.States;
import com.ppvfra.entity.TransferSeedToMTS;
import com.ppvfra.entity.TypeLine;
import com.ppvfra.entity.User;
import com.ppvfra.entity.User_Role;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AddDusTestCenterRepository;
import com.ppvfra.repository.ApplicationCertificatesRepository;
import com.ppvfra.repository.ApplicationOnlineQueryRepository;
import com.ppvfra.repository.ApplicationPreGrantRepository;
import com.ppvfra.repository.ApplicationProcessingRepository;
import com.ppvfra.repository.ApplicationRepository;
import com.ppvfra.repository.ApplicationScrutinyRepository;
import com.ppvfra.repository.ApplicationStsSeedRepository;
import com.ppvfra.repository.ApplicationStsTestRepository;
import com.ppvfra.repository.ApplicationsRepository;
import com.ppvfra.repository.AssingmentDetailsRepository;
import com.ppvfra.repository.CandidateVarietyDetailsRepository;
import com.ppvfra.repository.DUSTestResultsRepository;
import com.ppvfra.repository.MFormSectionRepository;
import com.ppvfra.repository.MediumTermStorageRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.NationalRegisterRepository;
import com.ppvfra.repository.RejuvenationRepository;
import com.ppvfra.repository.RemarksRepository;
import com.ppvfra.repository.RevocationRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.SequenceRepository;
import com.ppvfra.repository.TransferSeedToMTSRepository;
import com.ppvfra.repository.TypeLineRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.ApplicationScrutinyService;
import com.ppvfra.service.AssignmentDetailService;
import com.ppvfra.service.CandidateVarietyService;
import com.ppvfra.service.JournalService;
import com.ppvfra.service.RejuvenationService;
import com.ppvfra.service.RoleService;
import com.ppvfra.service.TransferSeedToMTSService;
import com.ppvfra.service.TypeLineService;
import com.ppvfra.service.UsersService;

@Controller
public class ApplicationProcessing {
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	RoleRepository rolerepository;
	
	@Autowired
	RoleService roleservice;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ApplicationProcessingRepository applicationprocessrepository;
	
	@Autowired
	UsersService userservice;
	
	@Autowired
	AssingmentDetailsRepository assignmentdetailsrepository;
	
	@Autowired
	AssignmentDetailService assignmentdetailservice;
	
	@Autowired
	ActivityLogRepository activitylogrepository;
	

	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	ApplicationStsSeedRepository applicationseedrepository;
	
	@Autowired
	ApplicationRepository applicationrepository;
	
	@Autowired
	ApplicationScrutinyService applicationscrutinyservice;
	
	@Autowired
	DUSTestResultsRepository dustestresultsrepository;
	
	@Autowired
	JournalService journalservice;
	
	@Autowired
	RejuvenationService rejuvenationservice;
	
	@Autowired
	TypeLineService typelineservice;
	
	@Autowired
	CandidateVarietyService candidatevarietyservice;
	
	@Autowired
	RejuvenationRepository rejuvenationrepository;
	
	@Autowired
	MediumTermStorageRepository mediumtermstoragerepository;
	
	@Autowired
	TransferSeedToMTSService transferseedtomtsservice;
	
	@Autowired
	TransferSeedToMTSRepository transferseedtomtsrepository;
	
	@Autowired
	ApplicationStsTestRepository applicationststestrepository;
	
	@Autowired
	ApplicationStsSeedRepository applicationstsseedrepository;
	
	@Autowired
	ApplicationScrutinyRepository applicationscrutinyrepository;
	
	@Autowired
	RemarksRepository remarksrepository;
	
	@Autowired
	ApplicationPreGrantRepository applicationpre_grantrepository;
	
	@Autowired
	ApplicationCertificatesRepository applicationcertificaterepository;
	
	@Autowired
	NationalRegisterRepository nationalrepository;
	
	@Autowired
	RevocationRepository revocationrepository;
	
	@Autowired
	AddDusTestCenterRepository add_testcenter_repository;
	
	
	@Autowired
	ApplicationsRepository applicationsrepository;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	@Autowired
	SequenceRepository sequencerepository;
	
	@Autowired
	CandidateVarietyDetailsRepository candidatevarietydetailsrepository;
	
	@Autowired
	TypeLineRepository typelinerepository;
	
	@Autowired
	ApplicationsRepository applicationsrep;
	
	@Autowired
	MFormSectionRepository mformsectionrepository;
	
	@Autowired
	ApplicationOnlineQueryRepository apponlinequeryrepository;
	
	
	@RequestMapping(value = "/application_processing", method = RequestMethod.GET)
	public String getApplicationProcessing(Model model,HttpServletRequest request) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail = null;
		
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				//System.out.println("Priting Loggin userdetail: " + userdeail.getId());
				} else {
				String username = principal.toString();
				//System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	    //Umesh Commenting this on 20-12-2019 for adding active roles only 
		//List<Role> roles = rolerepository.findAll();
	      List<Role> roles = rolerepository.getroleslist();
		  model.addAttribute("roles", roles);
		
		List<Object[]> app_process_rep = applicationprocessrepository.find_applicationprocess_details();
		
		if(app_process_rep !=null)
			model.addAttribute("app_process_rep",app_process_rep);
		
		System.out.println("User IDD= "+userid);
		List<Object[]> app_process_inbox = applicationprocessrepository.find_applicationprocess_details_inbox(userid);
		
		if(app_process_rep !=null)
			model.addAttribute("app_process_inbox",app_process_inbox);
		
		
		List<Object[]> app_process_outbox = applicationprocessrepository.find_applicationprocess_details_outbox(userid);
		
		if(app_process_rep !=null)
			model.addAttribute("app_process_outbox",app_process_outbox);
		
		
		StringBuffer url=request.getRequestURL();
	    String val=url.substring(url.lastIndexOf("/"),url.length());
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
			 String s1=null;
			  String freshapp_view=null;
			  
			  String s2=null;
			  String freshapp_inbox_view=null;
			  
			  String s3=null;
			  String freshapp_outbox_view=null;
			  
			  
			 List<Object[]> process = rolemodulerepository.getProcessnamesviauserid(userid);
			 if(process.contains("Fresh Applications Submitted"))
				{
					s1= "Fresh Applications Submitted";
					model.addAttribute("Fresh_Applications_Submitted",s1);
				List<Object[]> submitvalue1 = rolemodulerepository.getbuttonviauserid_module(userid,s1);
					if(submitvalue1.contains("View"))
					{
						freshapp_view="viewpresent";
						model.addAttribute("freshapp_view",freshapp_view);
					}
				}
			 
			 if(process.contains("Inbox"))
				{
					s2= "Inbox";
					model.addAttribute("Inbox",s2);
				List<Object[]> submitvalue2 = rolemodulerepository.getbuttonviauserid_module(userid,s2);
					if(submitvalue2.contains("View"))
					{
						freshapp_inbox_view="viewpresent";
						model.addAttribute("freshapp_inbox_view",freshapp_inbox_view);
					}
				}
			 
			 if(process.contains("Outbox"))
				{
					s3= "Outbox";
					model.addAttribute("Outbox",s3);
				List<Object[]> submitvalue3 = rolemodulerepository.getbuttonviauserid_module(userid,s3);
					if(submitvalue3.contains("View"))
					{
						freshapp_outbox_view="viewpresent";
						model.addAttribute("freshapp_outbox_view",freshapp_outbox_view);
					}
				}
				 
			 
			 
			 ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				//System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("view");
				String val1="/application_processing";
				act.setUrl(val1);
				
				activitylogservice.save(act);
			
			  //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			    List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
					model.addAttribute("usrname_header_val",usrname_header_val);
					List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
					model.addAttribute("rolespresent",rolespresent);
				//Umesh Adding ends here
					
					List<Object[]> role_alloted = rolemodulerepository.get_logged_in_role(userid);
					 if(role_alloted !=null)
					 {
						 Object compassist=null;
						 model.addAttribute("compassist",role_alloted.get(0));
										 
					 }
					 
					
		return "applicationprocessing/application_processing";
	}
	
	@RequestMapping(value = "/forward_app/{id}", method = RequestMethod.GET)
	public String getApplicationtoforward(Model model,HttpServletRequest request,@PathVariable(name = "id") int id) {
		/// Getting Logged in user
		int userid = 0;
		
		User userdeail =null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
			    userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				//System.out.println("Priting Loggin userdetail: " + userdeail.getId());
				} else {
				String username = principal.toString();
				//System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	 
	  //List<User> user = userservice.listall();
	  List<InternalUser> user = userrepository.getinternalusers();
	  model.addAttribute("userid", user);
	  //List<Role> roleid = roleservice.listall();
	  List<Role> roleid = rolerepository.getroleslist();
	  model.addAttribute("roleid", roleid);
	
	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
	  model.addAttribute("usrname_header_val",usrname_header_val);
	  List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
	  model.addAttribute("rolespresent",rolespresent);
	  List<Object[]> process_fwd = applicationprocessrepository.find_applicationprocess_details_id(id);
		
		if(process_fwd !=null)
			model.addAttribute("process_fwd",process_fwd);
		    String val="/application_processing";
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
			 
			 Assignment_Details assingdetails = new Assignment_Details();
			 model.addAttribute("assingdetails",assingdetails);
			
			 
			 ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("view");
				String val1="/forwarding_app";
				act.setUrl(val1); 
				
		return "applicationprocessing/forward";
	}
	
	
	@RequestMapping(value="/forward_fresh_application", method=RequestMethod.POST)
	public String addroleforadmind(@ModelAttribute(value="assingdetails") Assignment_Details assigned_details , 
	Model model,HttpServletRequest request,RedirectAttributes redirectAttributes ) 
	{
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
	
	{
	String roleid_receiver = request.getParameter("received_by_user_role");
	String userid_receiver = request.getParameter("received_by_user_id");
	
	String applicationid = request.getParameter("applicationid");
	Date date = new Date();
	Timestamp ts = new Timestamp(date.getTime());
	
	assigned_details.setApplicationid(Integer.parseInt(applicationid));
	assigned_details.setForm_type_id(1);
	assigned_details.setAssigned_by_user_id(userid);
	assigned_details.setAssigned_bydesignation(userrepository.getDesignation_viaid(userid));
	assigned_details.setReceived_by_user_id(Integer.parseInt(userid_receiver));
	assigned_details.setReceived_by_designation(userrepository.getDesignation_viaid(Integer.parseInt(userid_receiver)));
	assigned_details.setAssigned_by_office_id(userrepository.getOffice_id(userid));
	assigned_details.setReceived_by_date(ts);
	assigned_details.setCreatedby(userid);
	assigned_details.setCreatedbyip(request.getRemoteAddr());
	assigned_details.setCreatedon(ts);
	assigned_details.setApplicationstatus_id(36);
	//assignmentdetailsrepository.save(assigned_details);
	Boolean success=assignmentdetailservice.save(assigned_details);
	if (success)
	{
	 int success1 = applicationsrepository.updateApplicationStatus(Integer.parseInt(applicationid));
	 if(success1 == 1)
	{
		Remarks r = new Remarks();
		r.setApplicationid(Integer.parseInt(applicationid));
		r.setRemarks("Application Forwarded "+userdeail.getUsername());
		r.setStatus(36);
		r.setCreatedby(userdeail.getId());
		r.setCreatedbyip(request.getRemoteAddr());
		r.setCreatedon(ts);
		remarksrepository.save(r);
	}
 
    redirectAttributes.addFlashAttribute("message", "Data Forwarded Successfully");
	}
	else
	{
		redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
	}
	
	 String val="/application_processing";
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
		 return "redirect:/application_processing";
	}
	
	}
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/getactions/{id}", method = RequestMethod.GET)
	public String getActions(Model model,HttpServletRequest request,@PathVariable(name="id") Long id)
	{
		int userid = 0;
		User userdeail = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			String username = ((UserDetails) principal).getUsername();
			userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId(); 
		}
		else
		{
			String username = principal.toString();
		}
		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		model.addAttribute("modulelist", modulelist);
		
		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		model.addAttribute("modulelist2", modulelist2);
		
		List<InternalUser> userbinding = userrepository.getinternalusers();
		model.addAttribute("userbinding",userbinding);
		
		List<Object[]> statuswise_application=applicationrepository.application_statuswise_all();
		if(statuswise_application!= null)
		{
			model.addAttribute("statuswise_application",statuswise_application);
		}
		
		List<Object[]> data_scr= applicationrepository.saved_data_scrutiny(id.intValue());
		if(data_scr != null && data_scr.size()>0)
		{
			model.addAttribute("data_scr",data_scr.get(0));
		} 
		//System.out.println("PRINTING ID IN TTTT = "+id);
		
		model.addAttribute("applicationid_f",id);
		
		List<Object[]> typelinedetails=applicationseedrepository.typeline1(id);
		model.addAttribute("typelinedetails",typelinedetails);
		List<Object[]> applicationstsseedreceived = applicationseedrepository.finddata_set(id);
		model.addAttribute("applicationstsseedreceived", applicationstsseedreceived);
		
		List<Object[]> app_process_inbox = applicationprocessrepository.find_applicationprocess_details_inbox(userid);
		model.addAttribute("app_process_inbox",app_process_inbox);
		
		ApplicationScrutiny applicationscrutiny =new ApplicationScrutiny();
		model.addAttribute("applicationscrutiny",applicationscrutiny);
		
		Remarks addremarks = new Remarks();
		model.addAttribute("addremarks",addremarks);
		
		Revocation revocation = new Revocation();
		model.addAttribute("revocation",revocation);
		
		
		List<Object[]> apponlinequerydata = apponlinequeryrepository.apponlinedata(id.intValue());
		if(apponlinequerydata != null && apponlinequerydata.size()>0)
		{
		//System.out.println("Trace inside getting mode"+apponlinequerydata.get(0));
		model.addAttribute("apponlinequerydata",apponlinequerydata);
		}
		
		 
		
		ApplicationOnlineQuery sendquery= new ApplicationOnlineQuery();
		model.addAttribute("sendquery",sendquery);
		int app_online_id=0;
		model.addAttribute("app_online_id",app_online_id);
		  
		
		
		
		NationalRegister nationalregister = new NationalRegister();
		model.addAttribute("nationalregister",nationalregister);
		
		List<Object[]> national_repdata = nationalrepository.getnational_data(id.intValue());
		if(national_repdata.size()>0 && national_repdata != null)
		{
		//	System.out.println("IN ONE");
			model.addAttribute("national_rep",national_repdata.get(0));
			model.addAttribute("national_repdata",national_repdata);
			model.addAttribute("nationalreg_edit",1);
		}
		else{//System.out.println("IN ZERO");
		model.addAttribute("nationalreg_edit",0);}
		
		DUSTestResults dustestresults = new DUSTestResults();
		model.addAttribute("dustestresults",dustestresults);
		
		Application application_ack = new Application();
		model.addAttribute(application_ack);
		
		List<Object[]> reportstatus=dustestresultsrepository.finddetails();
		model.addAttribute("reportstatus",reportstatus);
		
		//Umesh Adding here on 10-01-2020
		//List<ApplicationScrutiny> applicationscrutinydata= applicationscrutinyservice.listall();
		//Changed on 17-06-2020 
		//List<ApplicationScrutiny> applicationscrutinydata=applicationscrutinyrepository.getdataviaid(id.intValue());
		List<Object[]> applicationscrutinydata=applicationscrutinyrepository.get_scr_details(id.intValue());
		if(applicationscrutinydata !=null)
		model.addAttribute("applicationscrutinydata",applicationscrutinydata);
		
		Journal journal = new Journal();
		model.addAttribute("journal",journal);
		
		ApplicationPreGrantOpposition issue_cert = new ApplicationPreGrantOpposition();
		if(issue_cert != null)
		{
		model.addAttribute("issue_cert",issue_cert);
		String user_name =userdeail.getUsername();
		model.addAttribute("user_name",user_name);
		}
		
		List<Object[]> appprerepstatus=applicationpre_grantrepository.getdata_val();
		if(appprerepstatus !=null && appprerepstatus.size()>0)
		{
			model.addAttribute("appprerepstatus",appprerepstatus);
			//adding it for id fetching
			model.addAttribute("appprerepstatus_id",appprerepstatus.get(0));
		}
		List<Object[]> datasaved_once = applicationpre_grantrepository.get_data_saved(id.intValue());
		if(datasaved_once !=null && datasaved_once.size()>0)
		{
			model.addAttribute("datasaved_once",datasaved_once.get(0));
			model.addAttribute("editmode_issuecerty",1);
			model.addAttribute("localdate",LocalDate.now());
		}else {model.addAttribute("datasaved_once",0);
		model.addAttribute("editmode_issuecerty",0);}
		
		
		List<Object[]> applicationcertificatesaved =applicationcertificaterepository.applicationcertificate(id.intValue());
		if(applicationcertificatesaved != null && 
				applicationcertificatesaved.size() !=0)
		{
			System.out.println("test check");
			model.addAttribute("acs",applicationcertificatesaved.get(0));
			model.addAttribute("acs_list",applicationcertificatesaved);
			model.addAttribute("editmode_issuecertacs",1);
		}
		else {
			System.out.println("test check else");
			model.addAttribute("acs",0);
			model.addAttribute("editmode_issuecertacs",0);
			}
		
		if(applicationcertificatesaved.size()>0 && datasaved_once.size()>0)
		{
			model.addAttribute("editmode_issuecerty",1);
			model.addAttribute("editmode_issuecertacs",1);
		}
		
		PublishToPVJ publishtopvj = new PublishToPVJ();
		model.addAttribute("publishtopvj",publishtopvj);

		List<Journal> journaltitle = journalservice.listall();
		model.addAttribute("journaltitle", journaltitle);
		
		Rejuvenation rejuvenation = new Rejuvenation();
		model.addAttribute("rejuvenation",rejuvenation);
		
		List<Object[]> rejuvenationdetails = rejuvenationrepository.getRejuvenationDetails(id);
		model.addAttribute("rejuvenationdetails",rejuvenationdetails);
		
		List<TypeLine> TypeLine = typelineservice.listall();
		model.addAttribute("TypeLine",TypeLine);
		
		List<CandidateVariety> CandidateVariety=candidatevarietyservice.listall();
		model.addAttribute("CandidateVariety",CandidateVariety);
		
		TransferSeedToMTS transferseedtomts = new TransferSeedToMTS();
		model.addAttribute("transferseedtomts",transferseedtomts);
		
		MediumTermStorage mediumtermstorage = new MediumTermStorage();
		model.addAttribute("mediumtermstorage",mediumtermstorage);
		
		List<Object[]> mediumtermstoragedetails = mediumtermstoragerepository.getMediumTermStorageDetails(id);
		model.addAttribute("mediumtermstoragedetails",mediumtermstoragedetails);
		
		List<Object[]> transferseedtomtsdetails =transferseedtomtsrepository.getTransferSeedToMTSDetails(id);
		model.addAttribute("transferseedtomtsdetails",transferseedtomtsdetails);
		
		List<Object[]> details =applicationststestrepository.getApplicationStsTestDetails(id.intValue());
		model.addAttribute("details",details);
		
		List<Object> sumpackets = applicationststestrepository.sumreq(id.intValue());
		if(sumpackets != null && sumpackets.size()>0)
		{
			model.addAttribute("sumpackets",sumpackets.get(0));
			model.addAttribute("ssp",sumpackets);
		}else {model.addAttribute("sumpackets",0);
		model.addAttribute("ssp",sumpackets);
		}
		ApplicationStsTest shorttermstorage = new ApplicationStsTest();
		model.addAttribute("shorttermstorage",shorttermstorage);
		
		List<Object[]> header_data = applicationrepository.fetch_headerdata(id.intValue());
		model.addAttribute("headerdata",header_data);
		
		//pvpno added by sargam
		for(int i=0; i<header_data.size();i++) {
				String pvpno = header_data.get(i)[2].toString();
				int addDigit = pvpno.length()-10;
				String addZero = "";
				if(addDigit == 1) {
					addZero = "000";
				}
				if(addDigit == 2) {
					addZero = "00";
				}
				if(addDigit == 3) {
					addZero = "0";
				}
			pvpno = pvpno.substring(0, 6) + pvpno.substring(8,10) +addZero + pvpno.substring(10,pvpno.length());
			model.addAttribute("pvpno", pvpno);
		}
		//pvpno added by sargam
		
		
		List<Object[]> remarks_stsreceived= applicationstsseedrepository.remarks_stsreceived(id);
		if(remarks_stsreceived !=null && remarks_stsreceived.size()>0)
			model.addAttribute("remark_stsreceived",remarks_stsreceived.get(0));
		
		List<Object[]> add_remarks_value= remarksrepository.getremarks_applicationid(id.intValue());
		if(add_remarks_value != null && add_remarks_value.size()>0)
		{
			model.addAttribute("add_remarks_value",add_remarks_value.get(0));
		} 
		List<Object[]> getremarks_datafortable= remarksrepository.getremarks_datafortable(id.intValue());
		if(getremarks_datafortable != null && getremarks_datafortable.size()>0)
		{
			model.addAttribute("getremarks_datafortable",getremarks_datafortable);
		}
		
	    List<Object[]> applicationdata = applicationrepository.getfile_ack_data(id.intValue());
		
		if(applicationdata != null && applicationdata.size()>0 && !(applicationdata.isEmpty()))
		{
		
			model.addAttribute("applicationdata",applicationdata.get(0));
		}
			model.addAttribute("editmode",0);
		
		List<Object[]> dustestdata =     dustestresultsrepository.datasaved_dus_test(id.intValue());
		if(dustestdata !=null && dustestdata.size() >0)
		{
			model.addAttribute("dustestdata",dustestdata.get(0));
			model.addAttribute("editmode",1);
		}
		
		/* List<Object[]> getapplication_status = getstatus_list(); */
		
		List<Object[]> getrevocation_data = revocationrepository.getrevocation_data(id);
		if(getrevocation_data.size() > 0 && getrevocation_data != null)
		{
			model.addAttribute("getrevodata",getrevocation_data.get(0));
			model.addAttribute("editmoderevo",1);
		}else {model.addAttribute("editmoderevo",0);}
		
		//****Portion For Finding Application Process portion On 17-12-2019
		List<Object[]> pnames = rolemodulerepository.getProcessnamesviauserid(userid);
		
		if(pnames != null && pnames.size()>0)
		{
			model.addAttribute("pnames",pnames);
			Object s1 = null;
			Object s2 = null;
			Object s3 = null;
			Object s4 = null;
			Object s5 = null;
			Object s6 = null;
			Object s7 = null;
			Object s8 = null;
			Object s9 = null;
			Object s10 = null;
			Object s11 = null;
			Object s12 = null;
			Object s13 = null;
			Object s14 = null;
			Object s15 = null;
			Object s16 = null;
			Object s17 = null;
			
	//Umesh adding here for submit button cases on 28-12-2019		
			Object sub1 = null;
			Object sub2 = null;
			Object sub3 = null;
			Object sub4 = null;
			Object sub5 = null;
			Object sub6 = null;
			Object sub7 = null;
			Object sub8 = null;
			Object sub9 = null;
			Object sub10 = null;
			Object sub11 = null;
			Object sub12 = null;
			Object sub13 = null;
			Object sub14 = null;
			Object sub15 = null;
			Object sub16 = null;
			Object sub17 = null;
	//Adding ends  here ---------------------------		
			if(pnames.contains("ReceiveSeedPlantingMaterial"))
			{
				s1= "ReceiveSeedPlantingMaterial";
				model.addAttribute("ReceiveSeedPlantingMaterial",s1);
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"ReceiveSeedPlantingMaterial");
				if(submitvalue.contains("Add"))
				{
					sub1="addpresent";
					model.addAttribute("sub1",sub1);
				}
			}
			
			if(pnames.contains("ApplicationScrutiny"))
			{
				s2= "ApplicationScrutiny";
				model.addAttribute("ApplicationScrutiny",s2);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"ApplicationScrutiny");
				if(submitvalue.contains("Add"))
				{
					sub2="addpresent";
					model.addAttribute("sub2",sub2);
				}
			}
			
			if(pnames.contains("DeficiencyReport"))
			{
				s3= "DeficiencyReport";
				model.addAttribute("DeficiencyReport",s3);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"DeficiencyReport");
				if(submitvalue.contains("Add"))
				{
					sub3="addpresent";
					model.addAttribute("sub3",sub3);
				}
			}
			
			if(pnames.contains("AddRemarks"))
			{
				s4= "AddRemarks";
				model.addAttribute("AddRemarks",s4);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"AddRemarks");
				if(submitvalue.contains("Add"))
				{
					sub4="addpresent";
					model.addAttribute("sub4",sub4);
				}
			}
			
			if(pnames.contains("Forward"))
			{
				s5= "Forward";
				model.addAttribute("Forward",s5);
				 
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"Forward");
				if(submitvalue.contains("Add"))
				{
					sub5="addpresent";
					model.addAttribute("sub5",sub5);
				}
			}
			
			if(pnames.contains("SendAcknowledgementletter"))
			{
				s6= "SendAcknowledgementletter";
				model.addAttribute("SendAcknowledgementletter",s6);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"SendAcknowledgementletter");
				if(submitvalue.contains("Add"))
				{
					sub6="addpresent";
					model.addAttribute("sub6",sub6);
				}
			}
			
			if(pnames.contains("ShortTermStorage"))
			{
				s7= "ShortTermStorage";
				model.addAttribute("ShortTermStorage",s7);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"ShortTermStorage");
				if(submitvalue.contains("Add"))
				{
					sub7="addpresent";
					model.addAttribute("sub7",sub7);
				}
			}
			
			if(pnames.contains("DusTestResults"))
			{
				s8= "DusTestResults";
				model.addAttribute("DusTestResults",s8);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"DusTestResults");
				if(submitvalue.contains("Add"))
				{
					sub8="addpresent";
					model.addAttribute("sub8",sub8);
				}
			}
			
			if(pnames.contains("DusTestAnalysis"))
			{
				s9= "DusTestAnalysis";
				model.addAttribute("DusTestAnalysis",s9);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"DusTestAnalysis");
				if(submitvalue.contains("Add"))
				{
					sub9="addpresent";
					model.addAttribute("sub9",sub9);
				}
			}
			
			if(pnames.contains("PublishToPVJ"))
			{
				s10= "PublishToPVJ";
				model.addAttribute("PublishToPVJ",s10);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"PublishToPVJ");
				if(submitvalue.contains("Add"))
				{
					sub10="addpresent";
					model.addAttribute("sub10",sub10);
				}
			}
			
			if(pnames.contains("IssueCertificate"))
			{
				s11= "IssueCertificate";
				model.addAttribute("IssueCertificate",s11);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"IssueCertificate");
				if(submitvalue.contains("Add"))
				{
					sub11="addpresent";
					model.addAttribute("sub11",sub11);
				}
			}
			
			if(pnames.contains("TransferSeedsToMTS"))
			{
				s12= "TransferSeedsToMTS";
				model.addAttribute("TransferSeedsToMTS",s12);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"TransferSeedsToMTS");
				if(submitvalue.contains("Add"))
				{
					sub12="addpresent";
					model.addAttribute("sub12",sub12);
				}
			}
			
			if(pnames.contains("MediumTermStorage"))
			{
				s13= "MediumTermStorage";
				model.addAttribute("MediumTermStorage",s13);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"MediumTermStorage");
				if(submitvalue.contains("Add"))
				{
					sub13="addpresent";
					model.addAttribute("sub13",sub13);
				}
			}
			
			if(pnames.contains("ENationalEntry"))
			{
				s14= "ENationalEntry";
				model.addAttribute("ENationalEntry",s14);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"ENationalEntry");
				if(submitvalue.contains("Add"))
				{
					sub14="addpresent";
					model.addAttribute("sub14",sub14);
				}
			}
			
			if(pnames.contains("Rejuvination"))
			{
				s15= "Rejuvination";
				model.addAttribute("Rejuvination",s15);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"Rejuvination");
				if(submitvalue.contains("Add"))
				{
					sub15="addpresent";
					model.addAttribute("sub15",sub15);
				}
			}
			
			if(pnames.contains("Revocation"))
			{
				s16= "Revocation";
				model.addAttribute("Revocation",s16);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"Revocation");
				if(submitvalue.contains("Add"))
				{
					sub16="addpresent";
					model.addAttribute("sub16",sub16);
				}
			}
			
			if(pnames.contains("Send Query"))
			{
				s17= "Send Query";
				model.addAttribute("SendQuery",s17);
				
				List<Object[]> submitvalue = rolemodulerepository.getbuttonviauserid_module(userid,"Send Query");
				if(submitvalue.contains("Add"))
				{
					sub17="addpresent";
					model.addAttribute("sub17",sub17);
				}
			}
			
		}
	
		List<Object[]> cnc_center=	add_testcenter_repository.findcnc_center(id.intValue());
		
	 List<Object[]> lc_center=	add_testcenter_repository.findlc_center(id.intValue());
		
	 if(cnc_center.size()>0)
	 {
		 model.addAttribute("cnc_center",cnc_center);
	 }
	 
	 if(lc_center.size()>0)
	 {
		 model.addAttribute("lc_center",lc_center);
	 }
		
	 
	 
	 Assignment_Details assingdetails = new Assignment_Details();
	 model.addAttribute("assingdetails",assingdetails);
	 
	 List<InternalUser> user = userrepository.getinternalusers();
	 model.addAttribute("userid", user);
	 
	 List<Role> roleid = rolerepository.getroleslist();
	 model.addAttribute("roleid", roleid);
	 
	 
	 List<Object[]> docpath_ack = applicationrepository.getack_path_viaid(id.intValue());
	 
	 if(docpath_ack.size()>0 && docpath_ack !=null)
	{ 
	model.addAttribute("docpath_ack",docpath_ack);
	String fname_formed = String.valueOf((Object) docpath_ack.get(0));
	String f_name_obtained=fname_formed.substring(fname_formed.lastIndexOf('/')+1);
	model.addAttribute("f_name_obtained",f_name_obtained);
	 }
 
	 List<ApplicationScrutiny> applicationfinalsubmit=applicationscrutinyrepository.getfinalsubmitdata(id.intValue());
	if(applicationfinalsubmit != null)
	{model.addAttribute("applicationfinalsubmit",applicationfinalsubmit);
	}
	 
	List<Object[]> seqnogetting = sequencerepository.fetchsequence(""+LocalDate.now().getYear());
	if(seqnogetting != null && seqnogetting.size()>0)
	{
		model.addAttribute("current_year_run",LocalDate.now().getYear());
		
		model.addAttribute("seqnogetting",seqnogetting.get(0));
	}
	
	Long appstat= applicationsrep.returnapplication_status(id.intValue());
	if(appstat!=0 && appstat!=null)
	model.addAttribute("appstat",appstat);
	//Umesh Adding here on 14-01-2020 -------
	//Added Here For Name And Role Showing in header
		
    List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		
	List<Object[]> getcan_vartype = candidatevarietydetailsrepository.getdetails_withapplicationid(id.intValue());
	if(getcan_vartype.size()>0 && getcan_vartype !=null)
	{
	if(getcan_vartype.contains(2))
	{model.addAttribute("hybridtrue",1);
	}else { 
		model.addAttribute("hybridtrue",0);
		List<Object[]> typeline11 = typelinerepository.gettypeline_datasecond();
	    if(typeline11!=null && typeline11.size()>0)
	    {
		model.addAttribute("typeline11",typeline11);
	    }   
	      }
	}
	
	List<Object[]> getcan_var = candidatevarietydetailsrepository.can_var_type(id.intValue());
	if(getcan_var!=null && getcan_var.size()>0)
	{
	model.addAttribute("can_var",getcan_var.get(0));
	}
	
	String ftype="";
	ftype=applicationsrepository.formtype_values(id.intValue());
	if(ftype.equals("Form1"))
	{
	List<Object[]> category_data= mformsectionrepository.getMFormSection_f1();
	if(category_data.size()>0 && category_data!=null)
	   {
		model.addAttribute("category_data",category_data);
		}
	}else if(ftype.equals("Form2"))
		{
		List<Object[]> category_data= mformsectionrepository.getMFormSection_f2();
		if(category_data.size()>0 && category_data!=null)
		   {
			model.addAttribute("category_data",category_data);
			}
		}else if(ftype.equals("Form3"))
		{
			List<Object[]> category_data= mformsectionrepository.getMFormSection_f3();
			if(category_data.size()>0 && category_data!=null)
			   {
				model.addAttribute("category_data",category_data);
				}
		}
	
	List<Object[]> role_alloted = rolemodulerepository.get_logged_in_role(userid);
	 if(role_alloted !=null && role_alloted.size()>0)
	 {
		 Object role_granted=null;
		 model.addAttribute("role_granted",role_alloted.get(0));
						 
	 }
	
	//Umesh Adding ends here
	//*****Ends Here *****************************		
		ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("view");
			String val="/getActions";
			act.setUrl(val);
			activitylogservice.save(act);
		   
		model.addAttribute("applicationid",id);
		return "applicationprocessing/action";
	}
	
	
	@RequestMapping(value = "/submitting_sts_seeds", method = RequestMethod.POST)
	public String editForm(@ModelAttribute(value = "applicationstsseedreceived") ApplicationStsSeedrecieved applicationstsseedreceived,Model model,HttpServletRequest req )throws IOException {
		/// Getting Logged in user
		 
		int userid = 0;
		User userdeail = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		String username = ((UserDetails) principal).getUsername();
		userdeail = userrepository.getUserDetail(username);
		userid = userdeail.getId();
		
		String remark=req.getParameter("remarks");
		Long checkby =  (long)userid;
		
		   ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(req.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("Save");
			act.setUrl("/editseedplantdetails");
			activitylogservice.save(act); 
		        
	   }
	   	return "redirect:/submittedapplication";
	}


	
	
}	
