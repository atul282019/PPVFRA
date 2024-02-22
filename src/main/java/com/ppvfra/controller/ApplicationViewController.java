package com.ppvfra.controller;

import java.util.Date;
import java.util.HashSet;
import java.awt.Color;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.Border;
import javax.swing.text.Style;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import com.ppvfra.entity.InternalUser;
import com.ppvfra.entity.MFormSection;
import com.ppvfra.entity.Modules;
import com.ppvfra.entity.Nationality;
import com.ppvfra.entity.ObservationCategory;
import com.ppvfra.entity.Role;
import com.ppvfra.entity.Role_Modules;
import com.ppvfra.entity.States;
import com.ppvfra.entity.TypeLine;
import com.ppvfra.entity.User;
import com.ppvfra.entity.UserAdd;
import com.ppvfra.entity.User_Role;
import com.ppvfra.repository.AccessTypesRepository;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AddCropGroupRepository;
import com.ppvfra.repository.AddCropsRepository;
import com.ppvfra.repository.AddDusTestCenterRepository;
import com.ppvfra.repository.AdduserRepository;
import com.ppvfra.repository.ApplicantRegistrationRepository;
import com.ppvfra.repository.ApplicationContactsRepository;
import com.ppvfra.repository.ApplicationContactsRepository3;
import com.ppvfra.repository.ApplicationContactsRepository7;
import com.ppvfra.repository.ApplicationConventionCountriesRepository;
import com.ppvfra.repository.ApplicationDocumentsRepository;
import com.ppvfra.repository.ApplicationParentallineRepository;
import com.ppvfra.repository.ApplicationPaymentsRepository;
import com.ppvfra.repository.ApplicationRepository;
import com.ppvfra.repository.ApplicationStsSeedRepository;
import com.ppvfra.repository.ApplicationTechnicalQuestionnaireReferenceRepository;
import com.ppvfra.repository.ApplicationTechnicalQuestionnaireRepository;
import com.ppvfra.repository.ApplicationTypesRepository;
import com.ppvfra.repository.ApplicationsRepository;
import com.ppvfra.repository.CandidateVarietyDetailsRepository;
import com.ppvfra.repository.CandidateVarietyRepository;
import com.ppvfra.repository.CompanyRegistrationRepository;
import com.ppvfra.repository.CountryRepository;
import com.ppvfra.repository.CropSpeciesRepository;
import com.ppvfra.repository.DistrictRepository;
import com.ppvfra.repository.DocumentChecklistRepository;
import com.ppvfra.repository.DusCharacteristicsRepository;
import com.ppvfra.repository.DusCharacteristicsStatesRepository;
import com.ppvfra.repository.DusTestPayRepository;
import com.ppvfra.repository.FormTypesRepository;
import com.ppvfra.repository.InstitutionRegistrationRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.OfficeDetailsRepository;
import com.ppvfra.repository.PaymentRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.StateRepository;
import com.ppvfra.repository.TypeLineRepository;
import com.ppvfra.repository.UserAddRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.repository.UserTypesRepository;
import com.ppvfra.service.AdduserService;
import com.ppvfra.service.ApplicationDocumentsService;
import com.ppvfra.service.ApplicationParentallineOthersService;
import com.ppvfra.service.ApplicationParentallineService;
import com.ppvfra.service.ApplicationPaymentsService;
import com.ppvfra.service.ApplicationTechnicalQuestionnaireReferenceService;
import com.ppvfra.service.ApplicationTechnicalQuestionnaireService;
import com.ppvfra.service.ApplicationsService;
import com.ppvfra.service.CropSpeciesService;
import com.ppvfra.service.UserService;
import com.ppvfra.service.UsersService;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.DocumentChecklist;
import com.ppvfra.entity.DusCharacteristicGroup;
import com.ppvfra.entity.DusCharacteristics;
import com.ppvfra.entity.DusCharacteristicsStates;
import com.ppvfra.service.DistrictService;
import com.ppvfra.service.DocumentChecklistService;
import com.ppvfra.service.DusCharacteristicGroupService;
import com.ppvfra.service.DusCharacteristicsService;
import com.ppvfra.service.FormTypesService;
import com.ppvfra.service.ModuleService;
import com.ppvfra.service.NationalityService;
import com.ppvfra.service.ObservationCategoryService;
import com.ppvfra.service.RoleService;
import com.ppvfra.service.StateService;
import com.ppvfra.service.TypeLineService;
import com.ppvfra.service.UserAddService;
import com.ppvfra.entity.OfficeDetails;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
//import com.ppvfra.common.AES_Encryption;
//import com.ppvfra.common.AES_Encryption;
import com.ppvfra.entity.AccessTypes;
import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.ApplicantRegistration;
import com.ppvfra.entity.ApplicantTypes;
import com.ppvfra.entity.Application;
import com.ppvfra.entity.ApplicationContacts;
import com.ppvfra.entity.ApplicationContacts3;
import com.ppvfra.entity.ApplicationContacts7;
import com.ppvfra.entity.ApplicationConventionCountries;
import com.ppvfra.entity.ApplicationDocuments;
import com.ppvfra.entity.ApplicationParentalline;
import com.ppvfra.entity.ApplicationParentallineOthers;
import com.ppvfra.entity.ApplicationPayments;
import com.ppvfra.entity.ApplicationStsSeedrecieved;
import com.ppvfra.entity.ApplicationTechnicalQuestionnaire;
import com.ppvfra.entity.ApplicationTechnicalQuestionnaireReference;
import com.ppvfra.entity.Applications;
import com.ppvfra.entity.CandidateVariety;
import com.ppvfra.entity.CandidateVarietyDetails;
import com.ppvfra.entity.CompanyRegistration;
import com.ppvfra.entity.Country;
import com.ppvfra.entity.CropGroup;
import com.ppvfra.entity.CropSpecies;
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.DusTestCenter;
import com.ppvfra.entity.DusTestFee;
import com.ppvfra.entity.FormTypes;
import com.ppvfra.entity.InstitutionRegistration;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.AddCropGroupService;
import com.ppvfra.service.AddDusTestCenterService;
import com.ppvfra.service.AddcropService;

@Controller
public class ApplicationViewController {

	@Autowired
	UserService userservice;

	@Autowired
	AdduserService addusrservice;

	@Autowired
	StateRepository staterep;

	@Autowired
	RoleRepository addrolerep;

	@Autowired
	AddcropService addcropservice;

	@Autowired
	AddCropGroupService addcropgroupservice;

	@Autowired
	AddDusTestCenterService adddustestcenterservice;

	@Autowired
	StateService stateservice;

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
	AddCropGroupRepository addcropgrprep;

	@Autowired
	OfficeDetailsRepository officedetailsrep;
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
	DusCharacteristicsService duscharacteristicsservice;
	@Autowired
	Role_ModulesRepository rolemodulerepository;

	@Autowired
	AddDusTestCenterRepository dustestcenterrepository;

	@Autowired
	DusCharacteristicGroupService duscharacteristicgroupservice;
	
	@Autowired
	Environment env;
	
	@Autowired
	CompanyRegistrationRepository companyregister;
	
	@Autowired
	DistrictRepository districtrepository;
	
	@Autowired
	DusCharacteristicsRepository duscharacteristicsrepository;
	
	@Autowired
	ObservationCategoryService observationcategoryservice;
	
	@Autowired
	DocumentChecklistService documentchecklistservice;
	
	@Autowired
	FormTypesService formtypesservice;
	
	@Autowired
	FormTypesRepository formtypesrepository;
	
	@Autowired
	CropSpeciesRepository cropspeciesrepository;
	
	@Autowired
	InstitutionRegistrationRepository institutionregistrationrepository;
	
	@Autowired 
	DusCharacteristicsStatesRepository duscharacteristicsstatesrepository;
	
	@Autowired
	ApplicantRegistrationRepository applicant_registration_rep;
	
	@Autowired
	UserAddService useraddservice;
	
	@Autowired
	UserTypesRepository usertypesrepository;
	
	@Autowired
	ApplicationRepository applicationrepository;
	
	@Autowired
	AddCropsRepository addcroprepository;
	
	@Autowired
	CandidateVarietyRepository candidatevarietyrepository;
	
	@Autowired
	ApplicationStsSeedRepository applicationseedrepository;
	
	@Autowired
	ApplicationDocumentsService applicationdocumentsservice;
	
	@Autowired
	ApplicationDocumentsRepository applicationdocumentrepository;
	
	@Autowired
	ActivityLogRepository activitylogrepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	DusTestPayRepository dustestpayrepository;
	
	@Autowired
	ApplicationPaymentsRepository applicationpaymentsrepository;
	
	@Autowired
	PaymentRepository paymentrepository;
	@Autowired
	ApplicationTechnicalQuestionnaireService applicationtechnicalquestionnairservice;
	@Autowired
	ApplicationTechnicalQuestionnaireRepository applicationtqrepository;
	@Autowired
	ApplicationsRepository applicationsrepository;
	@Autowired
	ApplicationPaymentsService applicationpaymentservice;
	
	@Autowired
	ApplicationTechnicalQuestionnaireService applicationtechnicalquestionnaireservice;
	
	@Autowired
	ApplicationTechnicalQuestionnaireReferenceService applicationtechnicalquestionnairereferenceservice;
	
	@Autowired
	ApplicationTechnicalQuestionnaireReferenceRepository applicationtechnicalquestionnairereferencerepository;
	
	@Autowired
	ApplicationContactsRepository applicationcontactrepository;
	
	@Autowired
	ApplicationContactsRepository3 applicationcontactrepository3;
	
	@Autowired
	ApplicationParentallineService applicationparentallineservice;
	
	@Autowired
	ApplicationParentallineRepository applicationparentallinerepository;
	
	@Autowired
	ApplicationParentallineOthersService applicationparentallineotherservice;
	
	@Autowired
	ApplicationsService applicationservice; 
	
	@Autowired
	TypeLineService typelineservice;
	
	@Autowired
	ApplicationContactsRepository7 applicationcontactrepository7;
	

	@Autowired
	DocumentChecklistRepository documentchecklistrepository;
	
	@Autowired
	ApplicationTechnicalQuestionnaireRepository applicationtechquestionrep;
	
	@Autowired
	CandidateVarietyDetailsRepository candidatevarietydetailsrepository;
	
	@Autowired
	NationalityService nationalityservice;
	
	@Autowired
	CountryRepository countryrep;
	
	@Autowired
	TypeLineRepository typelinerepository;
	
	@Autowired
	ApplicationTypesRepository applicanttypes;
	
	@Autowired
	ApplicationConventionCountriesRepository applicationconventioncontry; 

	
	

	@RequestMapping(value = "/savedapplication", method = RequestMethod.GET)
	public String savedApplication(Model model,HttpServletRequest request) {
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
				//System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  //List<CropGroup> Crop_Group = addcropgroupservice.listall();
	  List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
	  if(Crop_Group!= null)
		model.addAttribute("Crop_Group", Crop_Group);
		
		List<Crops> allcrops = addcroprepository.findAll();
		if(allcrops !=null)
		model.addAttribute("allcrops",allcrops);
		//System.out.println("inside applicant id as  ="+userid);	
		List<Object[]> all_applications=applicationrepository.find_details_of_application(userid);
		if(all_applications!= null)
			model.addAttribute("all_applications",all_applications);
	
	    StringBuffer url=request.getRequestURL();
		String val=url.substring(url.lastIndexOf("/"),url.length());
		//System.out.println("PPPPP==="+val);
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
			     }
		 }
		 
		
			  ActivityLogTable act = new ActivityLogTable();
			  act.setIpaddress(request.getRemoteAddr());
			  act.setActivityby(userdeail.getUsername()); Date dt = new Date();
			  //System.out.println("Current date Is ="+dt); act.setLogin_time_stamp(dt);
			  act.setActivity("view"); act.setUrl(val);
			  
			  activitylogservice.save(act);
			 
		   
		   //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
		   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
				
		
		/*
		 * AES_Encryption aes =new AES_Encryption(); model.addAttribute(aes);
		 */
			
			//Umesh Adding ends here
		 
		return "admin/save_application";
	}
	
	
	@RequestMapping(value = "/submittedapplication", method = RequestMethod.GET)
	public String submitApplication(Model model, HttpServletRequest request) {
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
	  
	  List<Object[]> statuswise_application=applicationrepository.application_statuswise(userdeail.getId());
		if(statuswise_application!= null)
		{
		  model.addAttribute("statuswise_application",statuswise_application);
		}
		ApplicationStsSeedrecieved seedinfo = new ApplicationStsSeedrecieved();
		model.addAttribute("seedinfo",seedinfo);
		
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
					 actd_viewtrue ="valpresent";
					model.addAttribute("actd_viewtrue",actd_viewtrue);
				  }
				 
			 }
			 
			    ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				//System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("view");
				act.setUrl(val);
				
				activitylogservice.save(act);
			   
			   List<Crops> cropdata = addcroprep.findAll();
			   if(cropdata.size()>0)
			   model.addAttribute("cropdata",cropdata);
			   
			   DusTestFee dusfee = new DusTestFee();
			   model.addAttribute("dusfee",dusfee);
			   
			   ApplicationPayments duspayment = new ApplicationPayments();
			   model.addAttribute("duspayment",duspayment);
			   
			    //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			    List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
				
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
				
				//Umesh Adding ends here
				
				List<TypeLine> TypeLine = typelineservice.listall();
				model.addAttribute("TypeLine",TypeLine);
					
		return "admin/submit_application";
	}

	
	@RequestMapping(value = "/show_application_details/{id}", method = RequestMethod.GET)
	public String view_application_details(@PathVariable(name = "id") Long id,Model mav,HttpServletRequest request) 
	{

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
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  mav.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  mav.addAttribute("modulelist2", modulelist2);
	  
	  List<States> office_state = staterep.findAll();
	  mav.addAttribute("office_state",office_state);
	  //System.out.println("PRINITN STATES"+office_state);
	 
	  List<Object[]> attachmentlist = applicationdocumentrepository.getattachmentdetails(id.intValue());
	  mav.addAttribute("attachmentlist", attachmentlist);
	  
	 List<Object[]> firstsetbind = applicationrepository.details_portion1(id.intValue());
	 List<Object[]> secondsetbind = applicationrepository.details_portion2(id.intValue());
	 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id.intValue());
	 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id.intValue());
	 List<Object[]> fifthsetbind = applicationrepository.details_portion5(id.intValue());
		
	 
	 if(firstsetbind != null && firstsetbind.size()>0) {
	 mav.addAttribute("firstsetbind",firstsetbind.get(0));
	 mav.addAttribute("firstsetbind1",firstsetbind);
	 if(firstsetbind.get(0) !=null)
	 {
		 for (int j =0; j< firstsetbind.size() ;j++)
		 {
			 Object[] row= (Object[]) firstsetbind.get(j);
			 try
			 {
				 String multiple = (String)Array.get(row, 36);
				
				 String[] a = multiple.split(",");
				 int[] intArray = new int[a.length];
				
			      for (int i = 0; i < a.length; i++) {
			         String numberAsString = a[i];
			         intArray[i] = Integer.parseInt(numberAsString);
			        }
			      
			      if(j==0)
					 {
						mav.addAttribute("intArray",intArray);
					}
			      
			 }
			 catch(NullPointerException np){
				// System.out.println("HANDLING ERROR");
				 }
			  
		 }
	 }
	 
	 }
	 
	 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
	 mav.addAttribute("candidatevar", candidatevar);
	 
	 if(secondsetbind != null && secondsetbind.size()>0) {
		 mav.addAttribute("secondsetbind",secondsetbind.get(0));
		 mav.addAttribute("secondsetbind2",secondsetbind);
	 }
	 
	 if(thirdsetbind !=null && thirdsetbind.size()>0)
	 {
		 mav.addAttribute("thirdsetbind",thirdsetbind.get(0));
		 mav.addAttribute("thirdsetbind3",thirdsetbind);
	 }	
	
	 if(fourthsetbind !=null && fourthsetbind.size()>0)
	 {
		 //System.out.println("PRINTITNG FOURTH SET"+fourthsetbind);
		 mav.addAttribute("fourthsetbind",fourthsetbind.get(0));
		 mav.addAttribute("fourthsetbind4",fourthsetbind);
	 }
	 
	if(fifthsetbind !=null && fifthsetbind.size()>0)
	{
		mav.addAttribute("fifthsetbind",fifthsetbind.get(0));
		 
	}
		String val="/submittedapplication";
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
	     if(actd !=null)
		 {
			 Object actd_viewtrue=null;
			 mav.addAttribute("actd",actd);
			 if(actd.contains("View"))
				 {
				 actd_viewtrue ="valpresent";
				 mav.addAttribute("actd_viewtrue",actd_viewtrue);
			  }
			 
		 }

////***************************	 
	     Applications applications = applicationservice.getById(id.intValue());
	     mav.addAttribute("applications", applications);
		  
			Integer tqid = null;
			 if( applicationtqrepository.getIdByApplicationId(id.intValue())  != 0 ) {
			  tqid = applicationtqrepository.getTqId(id.intValue());
			  ApplicationTechnicalQuestionnaire applicatiotq = applicationtechnicalquestionnaireservice.getById(tqid);
			  if(applicatiotq != null)
			  {
				  mav.addAttribute("applicatiotq",applicatiotq);
			  }
			  else {
				  mav.addAttribute("applicatiotq",applicatiotq);
			  }
			 }
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id.intValue());
			  if(agentcontact !=null && agentcontact.size() > 0)
			  {
				  mav.addAttribute("agentcontact",agentcontact);
			  }
			  else {
				  mav.addAttribute("agentcontact",1);
			  }
			  
			
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id.intValue());
			  if(applicationcontact !=null && applicationcontact.size() >0) {
				  mav.addAttribute("applicationcontact",applicationcontact);
			  }
			  else
			  {
				  mav.addAttribute("applicationcontact",1);
			  }
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id.intValue());
			  mav.addAttribute("applicationnaturalcontact",applicationnaturalcontact);
			  
			  
			   List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id.intValue());
			  if(breadercontact !=null && breadercontact.size()>0) {
				  mav.addAttribute("breadercontact",breadercontact);
				  }
				  else {
					  mav.addAttribute("breadercontact",1);
				  }
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  mav.addAttribute("applicationdocuments",applicationdocuments);
			  
			  
			 
			  
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id.intValue());
			  if(tqreference != null && tqreference.size() >0)
			  {
				  mav.addAttribute("tqreference",tqreference);
			  }else {
				  mav.addAttribute("tqreference",1);
			  }
		  
			  ApplicationContacts contacts = new ApplicationContacts();
			  mav.addAttribute("contact",contacts);
			 
			  ApplicationConventionCountries convention = new ApplicationConventionCountries();
			  mav.addAttribute("convention",convention);
			 
			  ApplicationParentalline parental = new ApplicationParentalline();
			  mav.addAttribute("parental",parental);
			  
			  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			  mav.addAttribute("parentalother",parentalother);
			 
			  ApplicationContacts contactform2 = new ApplicationContacts();
			  mav.addAttribute("contactform2",contactform2);
			  
			 
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id.intValue()) != null) {
				 
				List<ApplicationPayments> paymentdetailsmade =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id.intValue());
				if(paymentdetailsmade!=null)
					mav.addAttribute("paymentdetailsmade",paymentdetailsmade);
				
					
				//paymentid = applicationpaymentsrepository.getPaymentId(id.intValue());
				 // if(paymentid !=0) 
				  {
					//ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
					  List<ApplicationPayments> payment =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id.intValue());
						mav.addAttribute("payment",payment);
				  }  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 mav.addAttribute("payment",payment);
			 }
			
			 if( applicationtqrepository.getIdByApplicationId(id.intValue())  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id.intValue());
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				 mav.addAttribute("technicalquestion",question2);
				 
				 List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id.intValue());
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						 mav.addAttribute("techques_ref",techques_ref); 
					  
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  mav.addAttribute("technicalquestion",question2);
				  
				  mav.addAttribute("techques_ref",null);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
			 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id.intValue());
			 if(varietyandsubvariety !=null && varietyandsubvariety.size() > 0)
			 {
				 mav.addAttribute("varietyandsubvariety",varietyandsubvariety.get(0));
			 }
			 
			 }
	     
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			mav.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			mav.addAttribute("rolespresent",rolespresent);
			  List<Object[]> second_screen= applicationrepository.secondscreen_data(id.intValue());
				if(second_screen.size()>0 && second_screen!=null)
				{
					mav.addAttribute("second_screen",second_screen);
				}
	     
			///**********************************	     
		    ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			//System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("view");
			act.setUrl(val);
			activitylogservice.save(act);
		   mav.addAttribute("id",id);
	
	 return "view_applicationdetails";
	}
	@RequestMapping(value = "/form_openingseed_details/{id}", method = RequestMethod.POST)
	public String save_seed_details(@ModelAttribute(value = "seedinfo") ApplicationStsSeedrecieved seedinfo,Model model,HttpServletRequest req,@RequestParam MultipartFile[] fileUpload,@PathVariable(name = "id") Long id)throws IOException {
		/// Getting Logged in user
		int userid = 0;
		User userdeail =  null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				//System.out.println("Priting Loggin user: " + username);
			} else {
				String username = principal.toString();
			}
			/// end Getting Logged in user
				
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
	   if(!(req.getParameter("typeline").equals("null")) && !(req.getParameter("typeline").equals("")) && !(req.getParameter("typeline").equals("Selectdd")))
	   { 
		 
		   String mip=req.getRemoteAddr();
		   int verifierid=0;
		   String verifierdesignation="";
		   if (principal instanceof UserDetails) 
		   {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				 verifierid= userdeail.getId();
				 String u = userrepository.getAllDetailsOfUser(verifierid);
				 verifierid= userdeail.getId();
				 verifierdesignation = u;
		   }
		   for(int i=0; i< fileUpload.length ; i++)
		   {
			    seedinfo.setApplicationid(id);
			   
				/*
				 * if(req.getParameter("typeline").equals("Candidate Variety")) {char stp='C';
				 * seedinfo.setType_line(stp);}
				 * 
				 * if(req.getParameter("typeline").equals("A Line")) {char stp='A';
				 * seedinfo.setType_line(stp);}
				 * 
				 * if(req.getParameter("typeline").equals("B Line")) {char stp='B';
				 * seedinfo.setType_line(stp);}
				 * 
				 * if(req.getParameter("typeline").equals("R Line")) {char stp='R';
				 * seedinfo.setType_line(stp);}
				 */
			    seedinfo.setType_line(req.getParameter("typeline"));
		   System.out.println("Trace after setting the typeline");
			   if(fileUpload[i].getOriginalFilename() == "" || fileUpload[i].getOriginalFilename() == null || fileUpload[i].getOriginalFilename() ==" " || fileUpload[i].getOriginalFilename().trim() =="" ||fileUpload[i].getOriginalFilename().equals("null"))
			   continue;
			   if (fileUpload[i].getSize() >0) 
			   {		
			   		StringBuilder filePath = new StringBuilder(
			   		UPLOAD_FILEPATH+"PPVFRA/SEEDORPLANT/ID"); 
			   		File file = new File(filePath.toString());
			   		if (!file.exists()) 
			   		{
			   			file.mkdirs();
			   		}
			   		String filename=	fileUpload[i].getOriginalFilename().substring(0, fileUpload[i].getOriginalFilename().lastIndexOf("."));
			   		String fileext=	fileUpload[i].getOriginalFilename().substring(fileUpload[i].getOriginalFilename().lastIndexOf("."), fileUpload[i].getOriginalFilename().length() );
			   		String ffname= (filename.replace(" ", "_")).trim();
			   		String originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+fileext;
			   		File actFile = new File(filePath.append(File.separator + originalName).toString());
			   		try {
						Files.copy(fileUpload[i].getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   		seedinfo.setSeed_testing_report_name(originalName);
			   		//System.out.println("Printng the file name as=" +originalName);
			   		seedinfo.setSeed_testing_report_path(UPLOAD_FILEPATH+"PPVFRA/SEEDORPLANT/ID/"+originalName);
			   		//System.out.println("Printng the file path as=" +UPLOAD_FILEPATH+"PPVFRA/SEEDORPLANT/ID/"+originalName);
			   }
			   applicationseedrepository.save(seedinfo) ; 

		   }
		   
		   ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(req.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			String val="/form_opening_seed_details";
			act.setUrl(val);
			activitylogservice.save(act);   
	   } 
		return "redirect:/submittedapplication";
	}
	
	@RequestMapping(value = "/editseedplantdetails/{id}", method = RequestMethod.GET)
	public ModelAndView editBySeedPlant(@PathVariable(name = "id") Integer id,HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("admin/edit_seedorplantdetails");
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
	 
	  List<Object[]> statuswise_application=applicationrepository.application_statuswise_all();
		if(statuswise_application!= null)
		{
			mav.addObject("statuswise_application",statuswise_application);
		}

	List<Object[]> typelinedetails=applicationseedrepository.typeline(id);
	mav.addObject("typelinedetails",typelinedetails);
			
		Optional<ApplicationStsSeedrecieved> applicationstsseedreceived = applicationseedrepository.findById(id);
		mav.addObject("applicationstsseedreceived", applicationstsseedreceived);
		
		 ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			//System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("view");
			String val="/editseedplantdetails";
			act.setUrl(val);
			
			activitylogservice.save(act);
		   mav.setViewName("admin/edit_seedorplantdetails");
		return mav;
	}


	@RequestMapping(value = "/editform_openingseed_details", method = RequestMethod.POST)
	public String editForm(@ModelAttribute(value = "applicationstsseedreceived") ApplicationStsSeedrecieved applicationstsseedreceived,Model model,HttpServletRequest req,@RequestParam MultipartFile[] fileUpload)throws IOException {
		/// Getting Logged in user
		int userid = 0;
		User userdeail =null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				//System.out.println("Priting Loggin user: " + username);
			} else {
				String username = principal.toString();
			}
			/// end Getting Logged in user
				
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	 
	  String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
	  
	  if(!(req.getParameter("typeline").equals("null")) && !(req.getParameter("typeline").equals("")) && !(req.getParameter("typeline").equals("Selectdd")))
		   { 
		   String mip=req.getRemoteAddr();
		   int verifierid=0;
		   String verifierdesignation="";
		   if (principal instanceof UserDetails) 
		   {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				 verifierid= userdeail.getId();
				 String u = userrepository.getAllDetailsOfUser(verifierid);
				 verifierid= userdeail.getId();
				 verifierdesignation = u;
		   }
		   
		   for(int i=0; i< fileUpload.length ; i++)
		   {
			   
			   applicationstsseedreceived.setId(Integer.parseInt(req.getParameter("id")));
			   applicationstsseedreceived.setApplicationid(Long.parseLong(req.getParameter("applicationidhidden")));
			   
				/*
				 * if(req.getParameter("typeline").equals("Candidate Variety")) { char stp='C';
				 * applicationstsseedreceived.setType_line(stp); }
				 * if(req.getParameter("typeline").equals("A Line")) { char stp='A';
				 * applicationstsseedreceived.setType_line(stp); }
				 * if(req.getParameter("typeline").equals("B Line")) {char stp='B';
				 * applicationstsseedreceived.setType_line(stp); }
				 * if(req.getParameter("typeline").equals("R Line")) {char stp='R';
				 * applicationstsseedreceived.setType_line(stp); }
				 */
			   applicationstsseedreceived.setType_line(req.getParameter("typeline"));
			   /*
			   if(fileUpload[i].getOriginalFilename() == "" || fileUpload[i].getOriginalFilename() == null || fileUpload[i].getOriginalFilename() ==" " || fileUpload[i].getOriginalFilename().trim() =="" ||fileUpload[i].getOriginalFilename().equals("null"))
			   continue;
			   */
			   if (fileUpload[i].getSize() >=0) 
			   {		
			   		StringBuilder filePath = new StringBuilder(
			   		UPLOAD_FILEPATH+"PPVFRA/SEEDORPLANT/ID"); 
			   		File file = new File(filePath.toString());
			   		if (!file.exists()) 
			   		{
			   			file.mkdirs();
			   		}
			   		
			   		if(fileUpload[i].isEmpty()) 
					{
			   			String filename=req.getParameter("editseed_plant_pdf");
						System.out.println("Printing filename"+filename);
						if(filename!=null && !(filename ==""))
						{
							System.out.println("Printing in else 968 = "+filename);
					 		applicationstsseedreceived.setSeed_testing_report_name(filename);
					   		applicationstsseedreceived.setSeed_testing_report_path(UPLOAD_FILEPATH+"PPVFRA/SEEDORPLANT/ID/"+filename);
						}
					}
				else
				{	
			   		
			   		String filename=	fileUpload[i].getOriginalFilename().substring(0, fileUpload[i].getOriginalFilename().lastIndexOf("."));
			   		String fileext=	fileUpload[i].getOriginalFilename().substring(fileUpload[i].getOriginalFilename().lastIndexOf("."), fileUpload[i].getOriginalFilename().length() );
			   		String ffname= (filename.replace(" ", "_")).trim();
			   		String originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+fileext;
			   		File actFile = new File(filePath.append(File.separator + originalName).toString());
			   		try {
						Files.copy(fileUpload[i].getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   		applicationstsseedreceived.setSeed_testing_report_name(originalName);
			   		applicationstsseedreceived.setSeed_testing_report_path(UPLOAD_FILEPATH+"PPVFRA/SEEDORPLANT/ID/"+originalName);
			      }
			   }
			   
			   applicationseedrepository.save(applicationstsseedreceived) ;  
		   }
		   
		   ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(req.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			//System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("Save");
			act.setUrl("/editseedplantdetails");
			
			activitylogservice.save(act); 
		        
	   }
	   	return "redirect:/submittedapplication";
	}

	
//Adding here on 16-04-2020
	
	
	@RequestMapping(value = "/show_application_details_f3/{id}", method = RequestMethod.GET)
	public String view_application_details_f3(@PathVariable(name = "id") Long id,Model mav,HttpServletRequest request) 
	{

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
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  mav.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  mav.addAttribute("modulelist2", modulelist2);
	  
	  List<States> office_state = staterep.findAll();
	  mav.addAttribute("office_state",office_state);
	  //System.out.println("PRINITN STATES"+office_state);
	 
	  List<Object[]> attachmentlistf3 = applicationdocumentrepository.f3_getattachmentdetails(id.intValue());
	  mav.addAttribute("attachmentlistf3", attachmentlistf3);
	  
	  
	 List<Object[]> firstsetbind = applicationrepository.details_portion1(id.intValue());
	 List<Object[]> secondsetbind = applicationrepository.details_portion2(id.intValue());
	 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id.intValue());
	 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id.intValue());
	 List<Object[]> fifthsetbind = applicationrepository.details_portion5(id.intValue());
		
	 
	 if(firstsetbind != null && firstsetbind.size()>0) {
	 mav.addAttribute("firstsetbind",firstsetbind.get(0));
	 mav.addAttribute("firstsetbind1",firstsetbind);
	 if(firstsetbind.get(0) !=null)
	 {
		 for (int j =0; j< firstsetbind.size() ;j++)
		 {
			 Object[] row= (Object[]) firstsetbind.get(j);
			 try
			 {
				 String multiple = (String)Array.get(row, 36);
				
				 String[] a = multiple.split(",");
				 int[] intArray = new int[a.length];
				
			      for (int i = 0; i < a.length; i++) {
			         String numberAsString = a[i];
			         intArray[i] = Integer.parseInt(numberAsString);
			        }
			      
			      if(j==0)
					 {
						mav.addAttribute("intArray",intArray);
					}
			      
			 }
			 catch(NullPointerException np){
				// System.out.println("HANDLING ERROR");
				 }
			  
		 }
	 }
	 
	 }
	 
	 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
	 mav.addAttribute("candidatevar", candidatevar);
	 
	 if(secondsetbind != null && secondsetbind.size()>0) {
		 mav.addAttribute("secondsetbind",secondsetbind.get(0));
		 mav.addAttribute("secondsetbind2",secondsetbind);
	 }
	 
	 if(thirdsetbind !=null && thirdsetbind.size()>0)
	 {
		 mav.addAttribute("thirdsetbind",thirdsetbind.get(0));
		 mav.addAttribute("thirdsetbind3",thirdsetbind);
	 }	
	
	 if(fourthsetbind !=null && fourthsetbind.size()>0)
	 {
		 //System.out.println("PRINTITNG FOURTH SET"+fourthsetbind);
		 mav.addAttribute("fourthsetbind",fourthsetbind.get(0));
		 mav.addAttribute("fourthsetbind4",fourthsetbind);
	 }
	 
	if(fifthsetbind !=null && fifthsetbind.size()>0)
	{
		mav.addAttribute("fifthsetbind",fifthsetbind.get(0));
		 
	}
		String val="/submittedapplication";
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
	     if(actd !=null)
		 {
			 Object actd_viewtrue=null;
			 mav.addAttribute("actd",actd);
			 if(actd.contains("View"))
				 {
				 actd_viewtrue ="valpresent";
				 mav.addAttribute("actd_viewtrue",actd_viewtrue);
			  }
			 
		 }

	     ////***************************	 
	     Applications applications = applicationservice.getById(id.intValue());
	     mav.addAttribute("applications", applications);
		  
	     List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id.intValue());
		  if(agentcontact !=null && agentcontact.size() > 0)
		  {
			  mav.addAttribute("agentcontact",agentcontact);
		  }
		  else {
			  mav.addAttribute("agentcontact",1);
		  }
		  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id.intValue());
		  if(applicationcontact !=null && applicationcontact.size() >0) {
			  mav.addAttribute("applicationcontact",applicationcontact);
		  }
		  else
		  {
			  mav.addAttribute("applicationcontact",1);
		  }
		  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id.intValue());
		  mav.addAttribute("applicationnaturalcontact",applicationnaturalcontact);
		  
		  
		   List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id.intValue());
		  if(breadercontact !=null && breadercontact.size()>0) {
			  mav.addAttribute("breadercontact",breadercontact);
			  }
			  else {
				  mav.addAttribute("breadercontact",1);
			  }
	     
		  
			  
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  mav.addAttribute("applicationdocuments",applicationdocuments);
			  
			 
			 
			  ApplicationContacts contacts = new ApplicationContacts();
			  mav.addAttribute("contact",contacts);
			 
			  ApplicationConventionCountries convention = new ApplicationConventionCountries();
			  mav.addAttribute("convention",convention);
			 
			  ApplicationParentalline parental = new ApplicationParentalline();
			  mav.addAttribute("parental",parental);
			  
			  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			  mav.addAttribute("parentalother",parentalother);
			 
			  ApplicationContacts contactform2 = new ApplicationContacts();
			  mav.addAttribute("contactform2",contactform2);
			  
			  
			  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationidf3(id.intValue()); 
			  mav.addAttribute("document_checklistdataf3", document_checklistdata);
			
			 
			 if(applications.getVarirtytypeid() !=null) {
			 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id.intValue());
			 if(varietyandsubvariety !=null && varietyandsubvariety.size() > 0)
			 {
				 mav.addAttribute("varietyandsubvariety",varietyandsubvariety.get(0));
			 }
			 
			 }
	     
			 List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			 mav.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			 mav.addAttribute("rolespresent",rolespresent);
	     
			 ///**********************************	     
		    ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			//System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("view");
			act.setUrl(val);
			activitylogservice.save(act);
		   mav.addAttribute("id",id);
	
	 return "view_applicationdetails_f3";
	}

	
	
	
	
	@RequestMapping(value = "/show_application_details_f2/{id}", method = RequestMethod.GET)
	public String view_application_details_f2(@PathVariable(name = "id") Long id,Model mav,HttpServletRequest request) 
	{

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
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  mav.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  mav.addAttribute("modulelist2", modulelist2);
	  
	  List<States> office_state = staterep.findAll();
	  mav.addAttribute("office_state",office_state);
	  //System.out.println("PRINITN STATES"+office_state);
	 
	  List<Object[]> attachmentlist = applicationdocumentrepository.getattachmentdetailsform2(id.intValue());
	  mav.addAttribute("attachmentlist", attachmentlist);
	  
	  
	 List<Object[]> firstsetbind = applicationrepository.details_portion1(id.intValue());
	 List<Object[]> secondsetbind = applicationrepository.details_portion2(id.intValue());
	 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id.intValue());
	 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id.intValue());
	 List<Object[]> fifthsetbind = applicationrepository.details_portion5(id.intValue());
		
	 
	 List<Object[]> application10c = applicationrepository.details_portionapplication2(id.intValue());
		if(application10c !=null && application10c.size()>0)
				 {
			mav.addAttribute("application10c",application10c.get(0));
			mav.addAttribute("application10c",application10c);
				 }
	 
	 if(firstsetbind != null && firstsetbind.size()>0) {
	 mav.addAttribute("firstsetbind",firstsetbind.get(0));
	 mav.addAttribute("firstsetbind1",firstsetbind);
	 if(firstsetbind.get(0) !=null)
	 {
		 for (int j =0; j< firstsetbind.size() ;j++)
		 {
			 Object[] row= (Object[]) firstsetbind.get(j);
			 try
			 {
				 String multiple = (String)Array.get(row, 36);
				
				 String[] a = multiple.split(",");
				 int[] intArray = new int[a.length];
				
			      for (int i = 0; i < a.length; i++) {
			         String numberAsString = a[i];
			         intArray[i] = Integer.parseInt(numberAsString);
			        }
			      
			      if(j==0)
					 {
						mav.addAttribute("intArray",intArray);
					}
			      
			 }
			 catch(NullPointerException np){
				// System.out.println("HANDLING ERROR");
				 }
			  
		 }
	 }
	 
	 }
	 
	 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
	 mav.addAttribute("candidatevar", candidatevar);
	 
	 if(secondsetbind != null && secondsetbind.size()>0) {
		 mav.addAttribute("secondsetbind",secondsetbind.get(0));
		 mav.addAttribute("secondsetbind2",secondsetbind);
	 }
	 
	 if(thirdsetbind !=null && thirdsetbind.size()>0)
	 {
		 mav.addAttribute("thirdsetbind",thirdsetbind.get(0));
		 mav.addAttribute("thirdsetbind3",thirdsetbind);
	 }	
	
	 if(fourthsetbind !=null && fourthsetbind.size()>0)
	 {
		 //System.out.println("PRINTITNG FOURTH SET"+fourthsetbind);
		 mav.addAttribute("fourthsetbind",fourthsetbind.get(0));
		 mav.addAttribute("fourthsetbind4",fourthsetbind);
	 }
	 
	if(fifthsetbind !=null && fifthsetbind.size()>0)
	{
		mav.addAttribute("fifthsetbind",fifthsetbind.get(0));
		 
	}
		String val="/submittedapplication";
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
	     if(actd !=null)
		 {
			 Object actd_viewtrue=null;
			 mav.addAttribute("actd",actd);
			 if(actd.contains("View"))
				 {
				 actd_viewtrue ="valpresent";
				 mav.addAttribute("actd_viewtrue",actd_viewtrue);
			  }
			 
		 }

	     ////***************************	 
	     Applications applications = applicationservice.getById(id.intValue());
	     mav.addAttribute("applications", applications);
		  
			Integer tqid = null;
			 if( applicationtqrepository.getIdByApplicationId(id.intValue())  != 0 ) {
			  tqid = applicationtqrepository.getTqId(id.intValue());
			  ApplicationTechnicalQuestionnaire applicatiotq = applicationtechnicalquestionnaireservice.getById(tqid);
			  if(applicatiotq != null)
			  {
				  mav.addAttribute("applicatiotq",applicatiotq);
			  }
			  else {
				  mav.addAttribute("applicatiotq",applicatiotq);
			  }
			 }
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id.intValue());
			  if(agentcontact !=null && agentcontact.size() > 0)
			  {
				  mav.addAttribute("agentcontact",agentcontact);
			  }
			  else {
				  mav.addAttribute("agentcontact",1);
			  }
			  
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  mav.addAttribute("applicationdocuments",applicationdocuments);
			  
			 
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id.intValue());
			  if(applicationcontact !=null && applicationcontact.size() >0) {
				  mav.addAttribute("applicationcontact",applicationcontact);
			  }
			  else
			  {
				  mav.addAttribute("applicationcontact",1);
			  }
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id.intValue());
			  mav.addAttribute("applicationnaturalcontact",applicationnaturalcontact);
			  
			  
			   List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id.intValue());
			  if(breadercontact !=null && breadercontact.size()>0) {
				  mav.addAttribute("breadercontact",breadercontact);
				  }
				  else {
					  mav.addAttribute("breadercontact",1);
				  }
		     
			 
			  
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id.intValue());
			  if(tqreference != null && tqreference.size() >0)
			  {
				  mav.addAttribute("tqreference",tqreference);
			  }else {
				  mav.addAttribute("tqreference",1);
			  }
		  
			  ApplicationContacts contacts = new ApplicationContacts();
			  mav.addAttribute("contact",contacts);
			 
			  ApplicationConventionCountries convention = new ApplicationConventionCountries();
			  mav.addAttribute("convention",convention);
			 
			  ApplicationParentalline parental = new ApplicationParentalline();
			  mav.addAttribute("parental",parental);
			  
			  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			  mav.addAttribute("parentalother",parentalother);
			 
			  ApplicationContacts contactform2 = new ApplicationContacts();
			  mav.addAttribute("contactform2",contactform2);
			  
			 
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id.intValue()) != null) {
				 
				List<ApplicationPayments> paymentdetailsmade =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id.intValue());
				if(paymentdetailsmade!=null)
					mav.addAttribute("paymentdetailsmade",paymentdetailsmade);
				
					
				//paymentid = applicationpaymentsrepository.getPaymentId(id.intValue());
				 // if(paymentid !=0) 
				  {
					//ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
					  List<ApplicationPayments> payment =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id.intValue());
						mav.addAttribute("payment",payment);
				  }  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 mav.addAttribute("payment",payment);
			 }
			
			 if( applicationtqrepository.getIdByApplicationId(id.intValue())  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id.intValue());
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				 mav.addAttribute("technicalquestion",question2);
				 
				 List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id.intValue());
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						 mav.addAttribute("techques_ref",techques_ref); 
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  mav.addAttribute("technicalquestion",question2);
				  
				  mav.addAttribute("techques_ref",null);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
			 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id.intValue());
			 if(varietyandsubvariety !=null && varietyandsubvariety.size() > 0)
			 {
				 mav.addAttribute("varietyandsubvariety",varietyandsubvariety.get(0));
			 }
			 
			 }
	     
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			mav.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			mav.addAttribute("rolespresent",rolespresent);
	     
			 ///**********************************	     
		     ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			//System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("view");
			act.setUrl(val);
			activitylogservice.save(act);
		   mav.addAttribute("id",id);
		   
			  List<Object[]> second_screen= applicationrepository.secondscreen_data(id.intValue());
				if(second_screen.size()>0 && second_screen!=null)
				{
					mav.addAttribute("second_screen",second_screen);
				}
	
	 return "view_applicationdetails_f2";
	}
	
	@RequestMapping(value = "/makepdf_f3/{id}", method = RequestMethod.GET)
	public String pdf_forming_f3(@PathVariable(name = "id") Long id,Model mav,HttpServletRequest request) 
	{

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
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  mav.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  mav.addAttribute("modulelist2", modulelist2);
	  
	  List<States> office_state = staterep.findAll();
	  mav.addAttribute("office_state",office_state);
	  //System.out.println("PRINITN STATES"+office_state);
	 
	  List<Object[]> attachmentlistf3 = applicationdocumentrepository.f3_getattachmentdetails(id.intValue());
	  mav.addAttribute("attachmentlistf3", attachmentlistf3);
	  
	  
	 List<Object[]> firstsetbind = applicationrepository.details_portion1(id.intValue());
	 List<Object[]> secondsetbind = applicationrepository.details_portion2(id.intValue());
	 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id.intValue());
	 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id.intValue());
	 List<Object[]> fifthsetbind = applicationrepository.details_portion5(id.intValue());
		
	 
	 if(firstsetbind != null && firstsetbind.size()>0) {
	 mav.addAttribute("firstsetbind",firstsetbind.get(0));
	 mav.addAttribute("firstsetbind1",firstsetbind);
	 if(firstsetbind.get(0) !=null)
	 {
		 for (int j =0; j< firstsetbind.size() ;j++)
		 {
			 Object[] row= (Object[]) firstsetbind.get(j);
			 try
			 {
				 String multiple = (String)Array.get(row, 36);
				
				 String[] a = multiple.split(",");
				 int[] intArray = new int[a.length];
				
			      for (int i = 0; i < a.length; i++) {
			         String numberAsString = a[i];
			         intArray[i] = Integer.parseInt(numberAsString);
			        }
			      
			      if(j==0)
					 {
						mav.addAttribute("intArray",intArray);
					}
			      
			 }
			 catch(NullPointerException np){
				// System.out.println("HANDLING ERROR");
				 }
			  
		 }
	 }
	 
	 }
	 
	 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
	 mav.addAttribute("candidatevar", candidatevar);
	 
	 if(secondsetbind != null && secondsetbind.size()>0) {
		 mav.addAttribute("secondsetbind",secondsetbind.get(0));
		 mav.addAttribute("secondsetbind2",secondsetbind);
	 }
	 
	 if(thirdsetbind !=null && thirdsetbind.size()>0)
	 {
		 mav.addAttribute("thirdsetbind",thirdsetbind.get(0));
		 mav.addAttribute("thirdsetbind3",thirdsetbind);
	 }	
	
	 if(fourthsetbind !=null && fourthsetbind.size()>0)
	 {
		 //System.out.println("PRINTITNG FOURTH SET"+fourthsetbind);
		 mav.addAttribute("fourthsetbind",fourthsetbind.get(0));
		 mav.addAttribute("fourthsetbind4",fourthsetbind);
	 }
	 
	if(fifthsetbind !=null && fifthsetbind.size()>0)
	{
		mav.addAttribute("fifthsetbind",fifthsetbind.get(0));
		 
	}
		String val="/submittedapplication";
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
	     if(actd !=null)
		 {
			 Object actd_viewtrue=null;
			 mav.addAttribute("actd",actd);
			 if(actd.contains("View"))
				 {
				 actd_viewtrue ="valpresent";
				 mav.addAttribute("actd_viewtrue",actd_viewtrue);
			  }
			 
		 }

	     ////***************************	 
	     Applications applications = applicationservice.getById(id.intValue());
	     mav.addAttribute("applications", applications);
		  
	     
	     List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id.intValue());
		  if(applicationcontact !=null && applicationcontact.size() >0) {
			  mav.addAttribute("applicationcontact",applicationcontact);
		  }
		  else
		  {
			  mav.addAttribute("applicationcontact",1);
		  }
		
		  
			
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id.intValue());
			  if(agentcontact !=null && agentcontact.size() > 0)
			  {
				  mav.addAttribute("agentcontact",agentcontact);
			  }
			  else {
				  mav.addAttribute("agentcontact",1);
			  }
			  
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  mav.addAttribute("applicationdocuments",applicationdocuments);
			  
			 
			 
			  ApplicationContacts contacts = new ApplicationContacts();
			  mav.addAttribute("contact",contacts);
			 
			  ApplicationConventionCountries convention = new ApplicationConventionCountries();
			  mav.addAttribute("convention",convention);
			 
			  ApplicationParentalline parental = new ApplicationParentalline();
			  mav.addAttribute("parental",parental);
			  
			  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			  mav.addAttribute("parentalother",parentalother);
			 
			  ApplicationContacts contactform2 = new ApplicationContacts();
			  mav.addAttribute("contactform2",contactform2);
			  
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id.intValue());
			  if(breadercontact !=null && breadercontact.size()>0) {
				  mav.addAttribute("breadercontact",breadercontact);
				  }
				  else {
					  mav.addAttribute("breadercontact",1);
				  }
			 
			  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationidf3(id.intValue()); 
			  mav.addAttribute("document_checklistdataf3", document_checklistdata);
			
			 
			 if(applications.getVarirtytypeid() !=null) {
			 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id.intValue());
			 if(varietyandsubvariety !=null && varietyandsubvariety.size() > 0)
			 {
				 mav.addAttribute("varietyandsubvariety",varietyandsubvariety.get(0));
			 }
			 
			 }
	     
			 List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			 mav.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			 mav.addAttribute("rolespresent",rolespresent);
			 
			
	     
	///**********************************FILE GENERATION PORTION******	     
		
			 
	 List<Object[]> fifthsetbind_newcase = applicationrepository.details_portion_newcase(id.intValue());
				
			 
	 String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
		try {
			String FILE="";
					
			System.out.println("Control On Line 1707");
			    	
		StringBuilder filePath = new StringBuilder( 
		UPLOAD_FILEPATH+"PPVFRA/Application_pdf-"+id);
		
		Document document = new Document();
		FILE = UPLOAD_FILEPATH+"PPVFRA/Application_pdf-"+id+".pdf";
		PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(FILE));
		document.open();
		
		
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN,10,BaseColor.BLACK);
		Font font1 = FontFactory.getFont(FontFactory.TIMES_BOLD,10,BaseColor.BLACK);
		System.out.println("start pdf process");
		//http://localhost:8080/assets/img/ppv-brand.png"
		
		Image image=Image.getInstance("http://localhost:8081/assets/img/brand_logo3.png");
		Image image2=Image.getInstance("http://localhost:8081/assets/img/ppv-brand.png");
		/*document.add(image);*/
		
		String ss ="Government of India";
		String s1 ="Office of the Registrar";
		String s2 ="Protection of Plant Varieties and Farmers Rights Authority";
		String s3 ="NASC Complex, DPS Marg,";
		String s4 ="Opposite Todapur Village, New Delhi - 110012";
		
		
		PdfPTable table12 = new PdfPTable(3);
		table12.setWidths(new float[] { 1, 7, 1 });
		
		PdfPCell c15 = new PdfPCell();
        c15.setHorizontalAlignment(Element.ALIGN_LEFT);
        table12.addCell(c15);
        c15 = new PdfPCell();
        c15.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table12.addCell(c15);
        c15 = new PdfPCell();
        c15.setHorizontalAlignment(Element.ALIGN_LEFT);
        c15.setBorder(Rectangle.NO_BORDER);
        table12.addCell(c15);
        
        table12.setHeaderRows(1);
        table12.setWidthPercentage(100);
        table12.setTotalWidth(520);
        table12.setLockedWidth(true);
        
        String pp= ss+"\n"+s1+"\n"+s2+"\n"+s3+"\n"+s4; 
        table12.addCell(image);
        table12.addCell(pp);
        table12.addCell(image2);
        document.add(table12);
        
        //table12.setHeaderRows(1);
        table12.setWidthPercentage(100);
        //table12.getDefaultCell().setBorder(Rectangle.NO_BORDER);
         
       
		document.add(new LineSeparator(0.5f, 100, null, 0, -5));
		
		// Creating a table object 
		float[] columnwidth= {1};
		PdfPTable table = new PdfPTable(columnwidth);
			
		// first table
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		
		document.add(new Paragraph("Identity Of The Applicant(s):",font1));
		document.add(new Paragraph(" "));
		PdfPCell c1 = new PdfPCell(new Phrase("Identity Of The Applicant(s)",font));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);
        
        table.setHeaderRows(1);
        table.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	Object[] row= (Object[]) firstsetbind.get(0);
        		String applicant_name="";
   			 try
   			 {
   			 applicant_name = (String)Array.get(row, 16);
   			 }catch(NullPointerException ne) {}
   			 table.addCell(new Phrase(applicant_name,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	
        }
        document.add(table);
		//First table end
        
        //2nd table
        //float[] columnwidth2= {1};
		PdfPTable table2 = new PdfPTable(9);
		
        document.add(new Paragraph("2.Name(s) and Nationality of Applicant(s)",font1));
		document.add(new Paragraph(" "));
		PdfPCell c2 = new PdfPCell(new Phrase("Serial No.",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Name",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Address",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("State",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("District",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Pincode",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("City",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Country",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Nationality",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        
        table2.setHeaderRows(1);
        table2.setWidthPercentage(100);
        
        if(applicationcontact!=null && applicationcontact.size()>0)
        {
        	for (int j =0; j< applicationcontact.size() ;j++)
   		 {
   			 Object[] row= (Object[]) applicationcontact.get(j);
   			 try
   			 {
   				table2.addCell(new Phrase(""+(j+1),new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 10),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 11),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 8),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 9),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   	        	
   			 }catch(NullPointerException ne) {}
   		 } 
   			 
        }
        
        document.add(table2);
		//2nd a part ends
        
        PdfPTable table_2c = new PdfPTable(2);
        document.add(new Paragraph("2.(b) Principle place of business or domicile of applicant",font1));
		document.add(new Paragraph(" "));
		PdfPCell c_2 = new PdfPCell(new Phrase("State",font));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        c_2 = new PdfPCell(new Phrase("District",font));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        
        table_2c.setHeaderRows(1);
        table_2c.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	Object[] row= (Object[]) firstsetbind.get(0);
        		String principle_dom_state="";
        		String principle_dom_district="";
   			 try
   			 {
   				principle_dom_state = (String)Array.get(row, 22);
   				principle_dom_district = (String)Array.get(row, 23);
   			 }catch(NullPointerException ne) {}
   			table_2c.addCell(new Phrase(principle_dom_state,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2c.addCell(new Phrase(principle_dom_district,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	
        }
        document.add(table_2c);
        
        //2nd b portion table ends
        
        
       
        //3rd Table
        PdfPTable table3 = new PdfPTable(9);
		
        document.add(new Paragraph(" 3.Name and Address of the Person to whom Correspondence related to this application is to be sent:",font1));
		document.add(new Paragraph(" "));
		PdfPCell c3 = new PdfPCell(new Phrase("Serial No.",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Name",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Country",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("State",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("District",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Address",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Pincode/Zipcode",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Telephone",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Fax",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        
        table3.setHeaderRows(1);
        table3.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   			// System.out.println("Printing ==>> "+(String)Array.get(row, 8)+"Printing row as == >> "+row);
   			 //if((String)Array.get(row, 8) =="Agent" || Boolean.toString((Boolean)Array.get(row, 8)).equals("Agent") )
   			
   			 if(Array.get(row, 8)!= null)
   			 {	 
   			 if((String)Array.get(row, 8) =="Agent" || ((String)Array.get(row, 8)).equals("Agent") )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table3.addCell(""+(j+1));
   				table3.addCell(new Phrase((String)Array.get(row, 4),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 11),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 17),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 18),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   	        	
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 }
   			 
   		 } 
   			 
        }
        document.add(table3);
		
        //3rd Table Ends
        
        //4th table start
        
        
        PdfPTable table4 = new PdfPTable(5);
        document.add(new Paragraph("4.General Information of Candidate variety:",font1));
		document.add(new Paragraph(" "));
		PdfPCell c4 = new PdfPCell(new Phrase("Crop Details",font));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Common Name Of Crop",font));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Botanical Name",font));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Family",font));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Denomination(in Block letters)",font));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        
        table4.setHeaderRows(1);
        table4.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	Object[] row= (Object[]) firstsetbind.get(0);
        		String c_variety_cropdetail="";
        		String c_variety_commonname="";
        		String c_variety_botanical="";
        		String c_variety_family="";
        		String c_variety_deno="";
   			 try
   			 {
   				c_variety_cropdetail = (String)Array.get(row, 25);
   				c_variety_commonname = (String)Array.get(row, 26);
   				c_variety_botanical = (String)Array.get(row, 27);
   				c_variety_family = (String)Array.get(row, 28);
   				c_variety_deno = (String)Array.get(row, 29);
   			 }catch(NullPointerException ne) {}
   			table4.addCell(new Phrase(c_variety_cropdetail,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_commonname,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_botanical,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_family,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_deno,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	
        }
        document.add(table4);
        
        //4th table ends
        
       
       //5th table 
        PdfPTable table5 = new PdfPTable(1);
        document.add(new Paragraph("5(a). Classification of the Candidate Variety:",font1));
		document.add(new Paragraph(" "));
		PdfPCell c5 = new PdfPCell(new Phrase("Variety Type",font));
		c5.setHorizontalAlignment(Element.ALIGN_LEFT);
        table5.addCell(c5);
       
        
        table5.setHeaderRows(1);
        table5.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	
        	System.out.println("CONTROL 1980");
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
       if(Array.get(row, 36)!= null)
       {
    	   
        String multiple = (String)Array.get(row, 36);
		
		 String[] a = multiple.split(",");
		 int[] intArray = new int[a.length];
		
	      for (int i = 0; i < a.length; i++) {
	         String numberAsString = a[i];
	         intArray[i] = Integer.parseInt(numberAsString);
	        }
	     // System.out.println("CONTROL 1994= "+intArray+"and its size= "+intArray.length);
	     
				if(j==0)
				{	
				      for (int k=0;k<intArray.length;k++)
				      {
				    	  for(int l=0;l<candidatevar.size();l++)
				    	  {
				    	 if(intArray[k]== candidatevar.get(l).getId())
				    	 {
				    		 table5.addCell(new Phrase(candidatevar.get(l).getCandidate_variety(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
				    	 }
				    	  }
				      }
			   		 
			   	}
	     
       }
   		 }
        	
        }	
        
        document.add(table5);
        
        //5th b table
        
        PdfPTable table5_a = new PdfPTable(1);
        document.add(new Paragraph("5(a.i). FOR Others:",font1));
		document.add(new Paragraph(" "));
		
        //table4.setHeaderRows(1);
		table5_a.setWidthPercentage(100);
        
        if(fifthsetbind_newcase !=null && fifthsetbind_newcase.size()>0)
        {
        	//for(int j=0;j<fifthsetbind.size();j++)
        		{
        		Object[] row= (Object[]) fifthsetbind_newcase.get(0);
        		
        		String other_data="";
        	System.out.println("Printing fifth set= "+(String)Array.get(row,1));	
   			 try
   			 {
   				other_data = ((String)Array.get(row,1));
   			 }
   			 catch(NullPointerException ne) {}
   			table5_a.addCell(new Phrase(other_data,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	}	
        }
        document.add(table5_a);
        
        //5th table ends
        
        //6th table
        
        PdfPTable table6 = new PdfPTable(10);
		
        document.add(new Paragraph("6.Names and Addresses of farmer(s) who has/have bred the candidate Variety:",font1));
		document.add(new Paragraph(" "));
		PdfPCell c6 = new PdfPCell(new Phrase("Serial No.",font));
		c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Name",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Address",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Country",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("State",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("District",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Telephone",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Fax",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Email",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Nationality",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        
        table6.setHeaderRows(1);
        table6.setWidthPercentage(100);
        
        if(breadercontact!=null && breadercontact.size()>0)
        {
        	for (int j =0; j< breadercontact.size() ;j++)
   		 {
   			 Object[] row= (Object[]) breadercontact.get(j);
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table6.addCell(new Phrase(""+(j+1),new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 10),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 11),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 12),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 8),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 9),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   	        	
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table6);
		//6th table ends
        
        //7th table
        
        PdfPTable table7 = new PdfPTable(6);
		
        document.add(new Paragraph("7. Has the candidate variety been commercialised or "
        		+ "otherwise exploited:",font1));
		document.add(new Paragraph(" "));
		PdfPCell c7 = new PdfPCell(new Phrase("Selected Yes/No",font));
		c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Data of the first sale of the variety",font));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Country (ies) where Protection is made",font));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Denomiantion Used",font));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Trademark used, if any",font));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Variation is important trait with respect to first "
        		+ "filling(Attach sheet)",font));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        
        table7.setHeaderRows(1);
        table7.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   			// System.out.println("Printing ==>> "+(String)Array.get(row, 8)+"Printing row as == >> "+row);
   			 //if((String)Array.get(row, 8) =="Agent" || Boolean.toString((Boolean)Array.get(row, 8)).equals("Agent") )
   			if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				//table7.addCell(""+(j+1));
   				String val1=String.valueOf(Array.get(row, 38));
   				System.out.println("Printing val1= "+val1);
   				 if(val1.equals("true"))
   				{ table7.addCell(new Phrase("Yes",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				else {table7.addCell(new Phrase("No",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				
   				//table7.addCell("Yes");
   				 //Timestamp tt1= Timestamp.valueOf((String)Array.get(row, 39));
   				 //Date d= new Date(tt1.getDate());
   				 //System.out.println("CONTROL ON 2171 = "+tt1);
   				 String ts1 =(String)Array.get(row, 39).toString();
   				 String[] t=ts1.split(" ");
   				System.out.println("CONTROL ON 2171 = "+t[0]);
   				//String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(tt1.getDate()));
   				table7.addCell(new Phrase("Date:"+t[0]+(String)Array.get(row, 40),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table7.addCell(new Phrase((String)Array.get(row, 42),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table7.addCell(new Phrase((String)Array.get(row, 43),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table7.addCell(new Phrase((String)Array.get(row, 44),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table7.addCell(new Phrase((String)Array.get(row, 50),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table7);
        
        //7th table end
        Paragraph para3= new Paragraph("");
        Paragraph para11= new Paragraph("");
        Paragraph para= new Paragraph("Declaration",font1);
        Paragraph para1= new Paragraph("");
        Paragraph para2= new Paragraph("I/We hereby declare that genetic material or parental material acquired for breeding, "
        		+ "evolving or developing the variety has been lawfully acquired.",font);
        para.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(para3);
        document.add(para11);
        document.add(para);
        document.add(para1);
        document.add(para2);
        
        PdfPTable table8 = new PdfPTable(1);
        PdfPTable table9 = new PdfPTable(1);
        
        document.add(new Paragraph("I Agree",font));
		document.add(new Paragraph(" "));
		PdfPCell c8 = new PdfPCell(new Phrase("Place",font));
		c8.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8.addCell(c8);
        PdfPCell c9 = new PdfPCell(new Phrase("Date",font));
		c9.setHorizontalAlignment(Element.ALIGN_LEFT);
        table9.addCell(c9);
        

        table8.setWidthPercentage(100);
       
        table9.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   			if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table8.addCell(new Phrase((String)Array.get(row, 47),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				String ts1 =(String)Array.get(row, 46).toString();
  				 String[] t=ts1.split(" ");
   				table9.addCell(new Phrase(t[0],  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table8);
        document.add(table9);
       
        //Attachment case
        
        Paragraph para5= new Paragraph("");
        Paragraph para12= new Paragraph("");
        Paragraph para13= new Paragraph("Attachments:",font1);
        Paragraph para14= new Paragraph("");
        Paragraph para4 = new Paragraph("Following are the attachments (duly signed/seal) "
        		+ "submitted along  with of the application (note that wherever signature "
        		+ "is affixed in the application or attachments, all such "
        		+ "signatures shall be in the original",font);
        
        document.add(para12);
        document.add(para13);
        document.add(para14);
        document.add(para5);
        document.add(para4);
        
        document.add(new Paragraph(" "));
        PdfPTable table10 = new PdfPTable(3);
        PdfPCell c10 = new PdfPCell(new Phrase("Serial No",font));
		c10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(c10);
        c10 = new PdfPCell(new Phrase("Title",font));
		c10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(c10);
        c10 = new PdfPCell(new Phrase("Attachment",font));
		c10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(c10);
        
        table10.setWidthPercentage(100);
        
        if(document_checklistdata!=null && document_checklistdata.size()>0)
        {
        	for (int j =0; j< document_checklistdata.size() ;j++)
   		 {
   			 Object[] row= (Object[]) document_checklistdata.get(j);
   			//if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table10.addCell(new Phrase(""+(j+1),new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL)));
   				table10.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			 }catch(NullPointerException ne) {}
   			 
   			}
   			 
   		 } 
   			 
        }
        document.add(table10);
        
        Paragraph para10 =new Paragraph(" If felt necessary attach colour pictures of specific  characteristics "
        		+ "used for establishing distinctiveness.Please sign each "
        		+ "page of the application and other document on the left margin",font);
        document.add(para10);
        
        
//Attachment Ends Here----        
        
		int hash1=document.hashCode();
		System.out.println("hashcode for application pdf generated:"+hash1);
		document.close();
		writer.close();
		}
		catch(IOException | DocumentException  es) 
		{es.printStackTrace();}
		
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			act.setLogin_time_stamp(dt);
			act.setActivity("form3 pdf generated");
			act.setUrl(val);
			activitylogservice.save(act);
		   mav.addAttribute("id",id);
	
	// return "view_applicationdetails_f3";
		   return "redirect:/ppvfra/Application_pdf-"+id+".pdf";
	}

	
	
	@RequestMapping(value = "/makepdf_f2/{id}", method = RequestMethod.GET)
	public String pdf_forming_f2(@PathVariable(name = "id") Long id,Model mav,HttpServletRequest request) 
	{

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
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  mav.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  mav.addAttribute("modulelist2", modulelist2);
	  
	  List<States> office_state = staterep.findAll();
	  mav.addAttribute("office_state",office_state);
	  //System.out.println("PRINITN STATES"+office_state);
	 
	  List<Object[]> attachmentlist = applicationdocumentrepository.getattachmentdetailsform2(id.intValue());
	  mav.addAttribute("attachmentlist", attachmentlist);
	  
	  
	 List<Object[]> firstsetbind = applicationrepository.details_portion1(id.intValue());
	 List<Object[]> secondsetbind = applicationrepository.details_portion2(id.intValue());
	 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id.intValue());
	 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id.intValue());
	 List<Object[]> fifthsetbind = applicationrepository.details_portion5(id.intValue());
	 List<Object[]> details_portion5thsecond = applicationrepository.details_portion5thsecond(id.intValue());
			
	 
	 List<Object[]> application10c = applicationrepository.details_portionapplication2(id.intValue());
		if(application10c !=null && application10c.size()>0)
				 {
			mav.addAttribute("application10c",application10c.get(0));
			mav.addAttribute("application10c",application10c);
				 }
	 
	 if(firstsetbind != null && firstsetbind.size()>0) {
	 mav.addAttribute("firstsetbind",firstsetbind.get(0));
	 mav.addAttribute("firstsetbind1",firstsetbind);
	 if(firstsetbind.get(0) !=null)
	 {
		 for (int j =0; j< firstsetbind.size() ;j++)
		 {
			 Object[] row= (Object[]) firstsetbind.get(j);
			 try
			 {
				 String multiple = (String)Array.get(row, 36);
				
				 String[] a = multiple.split(",");
				 int[] intArray = new int[a.length];
				
			      for (int i = 0; i < a.length; i++) {
			         String numberAsString = a[i];
			         intArray[i] = Integer.parseInt(numberAsString);
			        }
			      
			      if(j==0)
					 {
						mav.addAttribute("intArray",intArray);
					}
			      
			 }
			 catch(NullPointerException np){
				// System.out.println("HANDLING ERROR");
				 }
			  
		 }
	 }
	 
	 }
	 
	 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
	 mav.addAttribute("candidatevar", candidatevar);
	 
	 if(secondsetbind != null && secondsetbind.size()>0) {
		 mav.addAttribute("secondsetbind",secondsetbind.get(0));
		 mav.addAttribute("secondsetbind2",secondsetbind);
	 }
	 
	 if(thirdsetbind !=null && thirdsetbind.size()>0)
	 {
		 mav.addAttribute("thirdsetbind",thirdsetbind.get(0));
		 mav.addAttribute("thirdsetbind3",thirdsetbind);
	 }	
	
	 if(fourthsetbind !=null && fourthsetbind.size()>0)
	 {
		 //System.out.println("PRINTITNG FOURTH SET"+fourthsetbind);
		 mav.addAttribute("fourthsetbind",fourthsetbind.get(0));
		 mav.addAttribute("fourthsetbind4",fourthsetbind);
	 }
	 
	if(fifthsetbind !=null && fifthsetbind.size()>0)
	{
		mav.addAttribute("fifthsetbind",fifthsetbind.get(0));
		 
	}
		String val="/submittedapplication";
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
	     if(actd !=null)
		 {
			 Object actd_viewtrue=null;
			 mav.addAttribute("actd",actd);
			 if(actd.contains("View"))
				 {
				 actd_viewtrue ="valpresent";
				 mav.addAttribute("actd_viewtrue",actd_viewtrue);
			  }
			 
		 }

	     ////***************************	 
	     Applications applications = applicationservice.getById(id.intValue());
	     mav.addAttribute("applications", applications);
		  
			Integer tqid = null;
			 if( applicationtqrepository.getIdByApplicationId(id.intValue())  != 0 ) {
			  tqid = applicationtqrepository.getTqId(id.intValue());
			  ApplicationTechnicalQuestionnaire applicatiotq = applicationtechnicalquestionnaireservice.getById(tqid);
			  if(applicatiotq != null)
			  {
				  mav.addAttribute("applicatiotq",applicatiotq);
			  }
			  else {
				  mav.addAttribute("applicatiotq",applicatiotq);
			  }
			 }
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id.intValue());
			  if(agentcontact !=null && agentcontact.size() > 0)
			  {
				  mav.addAttribute("agentcontact",agentcontact);
			  }
			  else {
				  mav.addAttribute("agentcontact",1);
			  }
			  
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  mav.addAttribute("applicationdocuments",applicationdocuments);
			  
			  
			 
			  
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id.intValue());
			  if(tqreference != null && tqreference.size() >0)
			  {
				  mav.addAttribute("tqreference",tqreference);
			  }else {
				  mav.addAttribute("tqreference",1);
			  }
		  
			  ApplicationContacts contacts = new ApplicationContacts();
			  mav.addAttribute("contact",contacts);
			 
			  ApplicationConventionCountries convention = new ApplicationConventionCountries();
			  mav.addAttribute("convention",convention);
			 
			  ApplicationParentalline parental = new ApplicationParentalline();
			  mav.addAttribute("parental",parental);
			  
			  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			  mav.addAttribute("parentalother",parentalother);
			 
			  ApplicationContacts contactform2 = new ApplicationContacts();
			  mav.addAttribute("contactform2",contactform2);
			  
			 
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id.intValue()) != null) {
				 
				List<ApplicationPayments> paymentdetailsmade =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id.intValue());
				if(paymentdetailsmade!=null)
					mav.addAttribute("paymentdetailsmade",paymentdetailsmade);
				
					
				//paymentid = applicationpaymentsrepository.getPaymentId(id.intValue());
				 // if(paymentid !=0) 
				  {
					//ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
					  List<ApplicationPayments> payment =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id.intValue());
						mav.addAttribute("payment",payment);
				  }  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 mav.addAttribute("payment",payment);
			 }
			
			 if( applicationtqrepository.getIdByApplicationId(id.intValue())  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id.intValue());
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				 mav.addAttribute("technicalquestion",question2);
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  mav.addAttribute("technicalquestion",question2);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
			 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id.intValue());
			 if(varietyandsubvariety !=null && varietyandsubvariety.size() > 0)
			 {
				 mav.addAttribute("varietyandsubvariety",varietyandsubvariety.get(0));
			 }
			 
			 }
	     
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			mav.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			mav.addAttribute("rolespresent",rolespresent);
	     
			
		   
		   List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id.intValue());
			  if(applicationcontact !=null && applicationcontact.size() >0) 
			  {
				  mav.addAttribute("applicationcontact",applicationcontact);
			  }
			  else
			  {
				  mav.addAttribute("applicationcontact",1);
			  }
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id.intValue());
			  mav.addAttribute("applicationnaturalcontact",applicationnaturalcontact);
			  
			  List<Object[]> second_screen= applicationrepository.secondscreen_data(id.intValue());
				if(second_screen.size()>0 && second_screen!=null)
				{
					mav.addAttribute("second_screen",second_screen);
				}
	
	
	     
	///**********************************FILE GENERATION PORTION******	     
		
			 
	 List<Object[]> fifthsetbind_newcase = applicationrepository.details_portion_newcase(id.intValue());
	 List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id.intValue());
	 List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationidform2(id.intValue());
	 List<ApplicationPayments> paymentdetailsmade =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id.intValue());
	// if( applicationtqrepository.getIdByApplicationId(id.intValue())  != 0 ) 
	 tqid = applicationtqrepository.getIdByApplicationId_pdfmethod(id.intValue());

	 System.out.println("Getting tq id as 2677 === >> "+tqid);
	
	 //	 List<ApplicationTechnicalQuestionnaire> get_tech_data =applicationtechquestionrep.get_tech_data(tqid);
	
		 
		 List<ApplicationTechnicalQuestionnaire> get_tech_data=null;
		 if(tqid!=0)
		 get_tech_data =applicationtechquestionrep.get_tech_data(tqid);
		 
		 List<Object[]> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq_forpdf(id.intValue());
		 
		 
	System.out.println("Getting tech data="+get_tech_data);
	 
	 
	
			 
	 String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
		try {
			String FILE="";
					
			System.out.println("Control On Line 2676");
			    	
		StringBuilder filePath = new StringBuilder( 
		UPLOAD_FILEPATH+"PPVFRA/Application_pdf-"+id);
		
		Document document = new Document();
		FILE = UPLOAD_FILEPATH+"PPVFRA/Application_pdf-"+id+".pdf";
		PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(FILE));
		document.open();
		
		
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD,10,BaseColor.BLACK);
		Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN,10,BaseColor.BLACK);
		System.out.println("start pdf process");
		
		
		//Image image=Image.getInstance("C:\\WORK\\Projectworkspace\\PPVFRA\\src\\main\\resources\\static\\img\\brand_logo3.png");
		//Image image2=Image.getInstance("C:\\WORK\\Projectworkspace\\PPVFRA\\src\\main\\resources\\static\\img\\ppv-brand.png");
		Image image=Image.getInstance("http://localhost:8081/assets/img/brand_logo3.png");
		Image image2=Image.getInstance("http://localhost:8081/assets/img/ppv-brand.png");
		/*document.add(image);*/
		
		String ss ="Government of India";
		String s1 ="Office of the Registrar";
		String s2 ="Protection of Plant Varieties and Farmers Rights Authority";
		String s3 ="NASC Complex, DPS Marg,";
		String s4 ="Opposite Todapur Village, New Delhi - 110012";
		
		
		PdfPTable table12 = new PdfPTable(3);
		table12.setWidths(new float[] { 1, 7, 1 });
		
		PdfPCell c15 = new PdfPCell();
        c15.setHorizontalAlignment(Element.ALIGN_LEFT);
        table12.addCell(c15);
        c15 = new PdfPCell();
        c15.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table12.addCell(c15);
        c15 = new PdfPCell();
        c15.setHorizontalAlignment(Element.ALIGN_LEFT);
        c15.setBorder(Rectangle.NO_BORDER);
        table12.addCell(c15);
        
        table12.setHeaderRows(1);
        table12.setWidthPercentage(100);
        table12.setTotalWidth(520);
        table12.setLockedWidth(true);
        
        String pp= ss+"\n"+s1+"\n"+s2+"\n"+s3+"\n"+s4; 
        table12.addCell(image);
        table12.addCell(pp);
        table12.addCell(image2);
        document.add(table12);
        
        //table12.setHeaderRows(1);
        table12.setWidthPercentage(100);
        
		document.add(new LineSeparator(0.5f, 100, null, 0, -5));
		
		// Creating a table object 
		float[] columnwidth= {1};
		PdfPTable table = new PdfPTable(columnwidth);
			
		// first table
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		
		document.add(new Paragraph("Identity Of The Applicant(s):",font));
		document.add(new Paragraph(" "));
		PdfPCell c1 = new PdfPCell(new Phrase("Identity Of The Applicant(s)",font1));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);
        
        table.setHeaderRows(1);
        table.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	Object[] row= (Object[]) firstsetbind.get(0);
        		String applicant_name="";
   			 try
   			 {
   			 applicant_name = (String)Array.get(row, 16);
   			 }catch(NullPointerException ne) {}
   			 table.addCell(new Phrase(applicant_name,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	
        }
        document.add(table);
		//First table end
        
        //2nd table
        //float[] columnwidth2= {1};
		PdfPTable table2 = new PdfPTable(9);
		
        document.add(new Paragraph("2.Name(s) and Nationality of Applicant(s)",font));
		document.add(new Paragraph("(a.) (If natural person):",font));
		PdfPCell c2 = new PdfPCell(new Phrase("Serial No.",font1));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Name",font1));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Address",font1));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Country",font1));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("State",font1));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("District",font1));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Pincode",font1));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("City",font1));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Nationality",font1));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        
        table2.setHeaderRows(1);
        table2.setWidthPercentage(100);
        
        if(applicationcontact!=null && applicationcontact.size()>0)
        {
        	for (int j =0; j< applicationcontact.size() ;j++)
   		 {
   			 Object[] row= (Object[]) applicationcontact.get(j);
   			 try
   			 {
   				table2.addCell(new Phrase(""+(j+1), new Font(Font.FontFamily.TIMES_ROMAN, 10 ,Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 10),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 11),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 8),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 9),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   	        	
   			 }catch(NullPointerException ne) {}
   		 } 
   			 
        }
        
        document.add(table2);
		//2nd a part ends
        
        PdfPTable table_2c = new PdfPTable(5);
        document.add(new Paragraph("(b.) (If a legal person; for example a firm or company or institution):",font));
		document.add(new Paragraph(" "));
		PdfPCell c_2 = new PdfPCell(new Phrase("Name",font1));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        c_2 = new PdfPCell(new Phrase("Address of eshtablishment(Registered office)",font1));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        c_2 = new PdfPCell(new Phrase("Year of Incorporation",font1));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        c_2 = new PdfPCell(new Phrase("State whether the applicant legal person has non-Indian participation in capital or management",font1));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        c_2 = new PdfPCell(new Phrase("Identify the Nationality",font1));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        
        table_2c.setHeaderRows(1);
        table_2c.setWidthPercentage(100);
        
        if(second_screen!=null && second_screen.size()>0)
        {
        	Object[] row= (Object[]) second_screen.get(0);
        	String vv=String.valueOf(Array.get(row, 4));
        	String v1=String.valueOf(Array.get(row, 5));
   			 try
   			 {
   			 table_2c.addCell(new Phrase((String)Array.get(row, 1),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2c.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2c.addCell(new Phrase((String)Array.get(row, 3),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			if(vv.equals("true"))
   			{
   				table_2c.addCell(new Phrase("Yes",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			}else if(vv.equals("false")) {
   				table_2c.addCell(new Phrase("No",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			}else { table_2c.addCell(new Phrase(" "));}
   			
   			if(v1.equals("1"))
   			{
   				table_2c.addCell(new Phrase("Indian",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			}else {
   				table_2c.addCell(new Phrase(" "));
   			}
   			 }catch(NullPointerException ne) {}
   		
        }
        document.add(table_2c);
        
        //2nd b portion table ends
        
        PdfPTable table_2cc = new PdfPTable(8);
        document.add(new Paragraph("(c.) Indicate the name and address of the natural person, being an "
        		+ "employee of the legal person, who is duly authorized to represent the legal person "
        		+ "(example a director of a company or a partner of a firm):",font));
		document.add(new Paragraph(" "));
		PdfPCell c_22 = new PdfPCell(new Phrase("Serial No",font1));
		c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("Name",font1));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("Address",font1));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("Country",font1));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("State",font1));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("District",font1));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("Pincode",font1));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("City",font1));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        
        table_2cc.setHeaderRows(1);
        table_2cc.setWidthPercentage(100);
        
        if(applicationnaturalcontact!=null && applicationnaturalcontact.size()>0)
        {
        	for (int j =0; j< applicationnaturalcontact.size() ;j++)
        	{	
        	Object[] row= (Object[]) applicationnaturalcontact.get(j);
        	//String vv=String.valueOf(Array.get(row, 4));
        	//String v1=String.valueOf(Array.get(row, 5));
   			 try
   			 {
   			table_2cc.addCell(new Phrase(""+(j+1), new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 3),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 4),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 8),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			 }catch(NullPointerException ne) {}
   			 
          }
   		
        }
        document.add(table_2cc);
        
     //2 c table ends   
       
        //3rd Table
        PdfPTable table3 = new PdfPTable(9);
		
        document.add(new Paragraph(" 3.Name and Address of the Person to whom Correspondence related to this application is to be sent:",font));
		document.add(new Paragraph(" "));
		PdfPCell c3 = new PdfPCell(new Phrase("Serial No.",font1));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Name",font1));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Country",font1));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("State",font1));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("District",font1));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Address",font1));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Pincode/Zipcode",font1));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Telephone",font1));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Fax",font1));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        
        table3.setHeaderRows(1);
        table3.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   			// System.out.println("Printing ==>> "+(String)Array.get(row, 8)+"Printing row as == >> "+row);
   			 //if((String)Array.get(row, 8) =="Agent" || Boolean.toString((Boolean)Array.get(row, 8)).equals("Agent") )
   			if(Array.get(row, 8) != null)
   			{	
   			 if((String)Array.get(row, 8) =="Agent" || ((String)Array.get(row, 8)).equals("Agent") )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table3.addCell(new Phrase(""+(j+1),new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 4),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 11),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 17),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 18),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   	        	
   			 }catch(NullPointerException ne) {}
   			 }
   			
   		 }
   			 
   		 } 
   			 
        }
        document.add(table3);
	
        //3rd -B case
        
        PdfPTable table3b = new PdfPTable(3);
		
        document.add(new Paragraph(" 3.(a):",font1));
		document.add(new Paragraph(" "));
		PdfPCell c3a = new PdfPCell(new Phrase("Principle place of business or domicile of applicant",font1));
		c3a.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3b.addCell(c3a);
        c3a = new PdfPCell(new Phrase("Name",font1));
        c3a.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3b.addCell(c3a);
        c3a = new PdfPCell(new Phrase("Country",font1));
        c3a.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3b.addCell(c3a);
        
        table3b.setHeaderRows(1);
        table3b.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   			// System.out.println("Printing ==>> "+(String)Array.get(row, 8)+"Printing row as == >> "+row);
   			//if((String)Array.get(row, 8) =="Agent" || ((String)Array.get(row, 8)).equals("Agent") )
   			if(j ==0)
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				//table3.addCell(""+(j+1));
   				table3b.addCell(new Phrase((String)Array.get(row, 21),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3b.addCell(new Phrase((String)Array.get(row, 22),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3b.addCell(new Phrase((String)Array.get(row, 23),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table3b);
		
        
        //3rd Table Ends
       
        //4th table start
        
        
        PdfPTable table4 = new PdfPTable(5);
        document.add(new Paragraph("4.General Information of Candidate variety:",font));
		document.add(new Paragraph(" "));
		PdfPCell c4 = new PdfPCell(new Phrase("Crop Details",font1));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Common Name Of Crop",font1));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Botanical Name",font1));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Family",font1));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Denomination(in Block letters)",font1));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        
        table4.setHeaderRows(1);
        table4.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	Object[] row= (Object[]) firstsetbind.get(0);
        		String c_variety_cropdetail="";
        		String c_variety_commonname="";
        		String c_variety_botanical="";
        		String c_variety_family="";
        		String c_variety_deno="";
   			 try
   			 {
   				c_variety_cropdetail = (String)Array.get(row, 25);
   				c_variety_commonname = (String)Array.get(row, 26);
   				c_variety_botanical = (String)Array.get(row, 27);
   				c_variety_family = (String)Array.get(row, 28);
   				c_variety_deno = (String)Array.get(row, 29);
   			 }catch(NullPointerException ne) {}
   			table4.addCell(new Phrase(c_variety_cropdetail,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_commonname,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_botanical,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_family,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_deno,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	
        }
        document.add(table4);
        
        //4th table ends
       
        //5th table
        
        PdfPTable table5th = new PdfPTable(2);
		
        document.add(new Paragraph("5.(a)Type of Essentially Derived Variety",font));
		document.add(new Paragraph(" "));
		PdfPCell c5th = new PdfPCell(new Phrase("Variety",font1));
		c5th.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5th.addCell(c5th);
		c5th = new PdfPCell(new Phrase("Sub Variety",font1));
		c5th.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5th.addCell(c5th);
       
		table5th.setHeaderRows(1);
		table5th.setWidthPercentage(100);
        
        if(details_portion5thsecond!=null && details_portion5thsecond.size()>0)
        {
        	for (int j =0; j< details_portion5thsecond.size() ;j++)
   		 {
   			 Object[] row= (Object[]) details_portion5thsecond.get(j);
   			String mainp="";
   			String subp="";
   			{
   				System.out.println("CONTROL ON 3150");
   				if(String.valueOf((Array.get(row, 0))).equals("3"))
   				{ mainp="Essentialy Derived Variety";}
   				System.out.println("After EDV aS = "+String.valueOf((Array.get(row, 2))));
   				if(String.valueOf((Array.get(row, 1))).equals("3"))
   				{subp="TRANSGENIC";}
   				else if(String.valueOf((Array.get(row, 1))).equals("4"))
   				{subp ="MUTANT";}
   				else if(String.valueOf((Array.get(row, 1))).equals("5"))
   				{subp ="TISSUE CULTURE DERIVED";}
   				else if(String.valueOf((Array.get(row, 1))).equals("6"))
   				{subp ="BACK CROSS DERIVATIVE";}
   				else if(String.valueOf((Array.get(row, 1))).equals("7"))
   				{subp ="ANY OTHER(Ploidy Change ets)";}
   			 try
   			 {
   				//table3.addCell(""+(j+1));
   				table5th.addCell(new Phrase(mainp,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table5th.addCell(new Phrase(subp,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table5th);
        
        
        PdfPTable table5thb = new PdfPTable(2);
		
        document.add(new Paragraph("5.(b) If transgenic attach copy of Genetic Engineering Approval "
        		+ "Committee and if the Essentially Derived Variety is a transgenic attach clearance on "
        		+ "Bio-safety angle and Environment Department(GEAC) and related seed production "
        		+ "permission (Give details)",font));
		document.add(new Paragraph(" "));
		PdfPCell c5thb = new PdfPCell(new Phrase("File Name",font1));
		c5thb.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5thb.addCell(c5thb);
		c5thb = new PdfPCell(new Phrase("Remarks:",font1));
		c5thb.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5thb.addCell(c5thb);
       
		table5thb.setHeaderRows(1);
		table5thb.setWidthPercentage(100);
        
        if(details_portion5thsecond!=null && details_portion5thsecond.size()>0)
        {
        	for (int j =0; j< details_portion5thsecond.size() ;j++)
   		 {
   			 Object[] row= (Object[]) details_portion5thsecond.get(j);
   			String mainp="";
   			String subp="";
   			
   			{
   				System.out.println("CONTROL ON 3208");
   				
   				if(String.valueOf((Array.get(row, 1))).equals("3"))
   				{subp="TRANSGENIC";}
   				
   			 try
   			 {
   				//table3.addCell(""+(j+1));
   				if(subp.equals("TRANSGENIC"))
   				{
   				table5thb.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table5thb.addCell(new Phrase((String)Array.get(row, 3),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				}
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table5thb);
        
        //5th Table Ends
       
        
        //6th table
        
        PdfPTable table5 = new PdfPTable(1);
        document.add(new Paragraph("6(a). Classification of the Candidate Variety:",font));
		document.add(new Paragraph(" "));
		PdfPCell c5 = new PdfPCell(new Phrase("Variety Type",font1));
		c5.setHorizontalAlignment(Element.ALIGN_LEFT);
        table5.addCell(c5);
       
        
        table5.setHeaderRows(1);
        table5.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	
        	System.out.println("CONTROL 1980");
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
        if(Array.get(row, 36)!=null)
        {	
        String multiple = (String)Array.get(row, 36);
		
		 String[] a = multiple.split(",");
		 int[] intArray = new int[a.length];
		
	      for (int i = 0; i < a.length; i++) {
	         String numberAsString = a[i];
	         intArray[i] = Integer.parseInt(numberAsString);
	        }
	     // System.out.println("CONTROL 1994= "+intArray+"and its size= "+intArray.length);
	     
				if(j==0)
				{	
				      for (int k=0;k<intArray.length;k++)
				      {
				    	  for(int l=0;l<candidatevar.size();l++)
				    	  {
				    	 if(intArray[k]== candidatevar.get(l).getId())
				    	 {
				    		 table5.addCell(new Phrase(candidatevar.get(l).getCandidate_variety(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
				    	 }
				    	  }
				      }
			   		 
			   	}
				
   		 }
	     
   		 }
        	
        }	
        
        document.add(table5);
        
        //6th b table
        
        PdfPTable table5_a = new PdfPTable(1);
        document.add(new Paragraph("6(a.i). FOR Others:",font));
		document.add(new Paragraph(" "));
		
        //table4.setHeaderRows(1);
		table5_a.setWidthPercentage(100);
        
        if(fifthsetbind_newcase !=null && fifthsetbind_newcase.size()>0 )
        {
        	//for(int j=0;j<fifthsetbind.size();j++)
        		{
        		Object[] row= (Object[]) fifthsetbind_newcase.get(0);
        		
        		String other_data="";
        	System.out.println("Printing fifth set= "+(String)Array.get(row,1));	
   			 try
   			 {
   				other_data = ((String)Array.get(row,1));
   			 }
   			 catch(NullPointerException ne) {}
   			table5_a.addCell(new Phrase(other_data,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	}	
        }
        document.add(table5_a);
        
        //6th b-for others case ends
        
        //6th b start 
        PdfPTable table5_b = new PdfPTable(1);
        document.add(new Paragraph("6(b.) What is (are) the Distinctiveness Uniformity Stability feature "
        		+ "on the basis of which registration is sought. Explain in detail the group characters "
        		+ "(see specific guidelines for details). Attach 'Technical Questionnaire' sheet with "
        		+ "all needed details duly signed with seal.",font));
		document.add(new Paragraph(" "));
		
        //table4.setHeaderRows(1);
		table5_b.setWidthPercentage(100);
        
        if(firstsetbind !=null && firstsetbind.size()>0)
        {
        	//for(int j=0;j<firstsetbind.size();j++)
        		{
        		Object[] row= (Object[]) firstsetbind.get(0);
        		
        		String other_data="";
        	System.out.println("Printing fifth 3329 set= "+(String)Array.get(row,37));	
   			 try
   			 {
   				other_data = ((String)Array.get(row,37));
   			 }
   			 catch(NullPointerException ne) {}
   			table5_b.addCell(new Phrase(other_data,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	}	
        }
        document.add(table5_b);
        
        //6th b ends 
        
       //6th c---- 
        PdfPTable table5_c = new PdfPTable(1);
        document.add(new Paragraph("6(c.) Whether the authorization of the breeder of initial variety has been taken.",font));
		document.add(new Paragraph(" "));
		
        //table4.setHeaderRows(1);
		table5_c.setWidthPercentage(100);
        
        if(firstsetbind !=null && firstsetbind.size()>0)
        {
        	//for(int j=0;j<firstsetbind.size();j++)
        		{
        		Object[] row= (Object[]) firstsetbind.get(0);
        		
        		String other_data="";
        	System.out.println("Printing 6th 3357 set= "+Array.get(row,55));	
   			 try
   			 {
   				 if(String.valueOf((Array.get(row, 55))).equals("1"))
   				 {
   					other_data ="Yes";
   				 }
   				if(String.valueOf((Array.get(row, 55))).equals("2"))
  				 {
  					other_data ="No";
  				 }
   			 }
   			 catch(NullPointerException ne) {}
   			table5_c.addCell(new Phrase(other_data,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	}	
        }
        document.add(table5_c);
        
        //6th c---ends
        
        //6th d--case
        
        PdfPTable table5_d = new PdfPTable(1);
        document.add(new Paragraph("6(d.) If yes please attach copy of Authorization.",font));
		document.add(new Paragraph(" "));
		
        //table4.setHeaderRows(1);
		table5_d.setWidthPercentage(100);
        
        if(firstsetbind !=null && firstsetbind.size()>0)
        {
        	//for(int j=0;j<firstsetbind.size();j++)
        		{
        		Object[] row= (Object[]) firstsetbind.get(0);
        		
        		String other_data="";
        	System.out.println("Printing 6th 3393 set= "+Array.get(row,55));	
   			 try
   			 {
   				 if(String.valueOf((Array.get(row, 55))).equals("1"))
   				 {
   					other_data ="Yes";
   				 }
   				if(String.valueOf((Array.get(row, 55))).equals("2"))
  				 {
  					other_data ="No";
  				 }
   			 }
   			 catch(NullPointerException ne) {}
   			if(other_data.equals("Yes"))
   			{
   				table5_d.addCell(new Phrase((String)(Array.get(row, 52)),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			}
   			}
        	}	
        document.add(table5_d);
        
        //6th d case ends
        
        
        //6th table ends
        
        //7th table
        
        PdfPTable table6 = new PdfPTable(10);
		
        document.add(new Paragraph("7.Names and Addresses of Breeder(s) who had/have bred the candidate "
        		+ "Variety:",font));
		document.add(new Paragraph(" "));
		PdfPCell c6 = new PdfPCell(new Phrase("Serial No.",font1));
		c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Name",font1));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Address",font1));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Country",font1));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("State",font1));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("District",font1));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Telephone",font1));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Fax",font1));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Email",font1));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Nationality",font1));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        
        table6.setHeaderRows(1);
        table6.setWidthPercentage(100);
        
        if(breadercontact!=null && breadercontact.size()>0)
        {
        	for (int j =0; j< breadercontact.size() ;j++)
   		 {
   			 Object[] row= (Object[]) breadercontact.get(j);
   			
   			 
   			 // System.out.println("Printing ==>> "+(String)Array.get(row, 8)+"Printing row as == >> "+row);
   			 //if((String)Array.get(row, 8) =="Agent" || Boolean.toString((Boolean)Array.get(row, 8)).equals("Agent") )
   			//if((String)Array.get(row, 8) =="Agent" || ((String)Array.get(row, 8)).equals("Agent") )
   			{
   				 System.out.println("CONTROL ON 3524");
   			 try
   			 {
   				table6.addCell(new Phrase(""+(j+1), new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 10),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 11),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 12),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 8),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 9),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   	        	
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table6);
		//7th table ends
        
        //8th table----
        PdfPTable table8th = new PdfPTable(10);
		
        document.add(new Paragraph("8. Has the candidate variety been commercialised or otherwise exploited:",font));
		document.add(new Paragraph(" "));
		PdfPCell c8th = new PdfPCell(new Phrase("Serial No",font1));
		c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
		table8th.addCell(c8th);
		c8th = new PdfPCell(new Phrase("Variety Denomination",font1));
		c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Nature of right applied for",font1));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Filing Date",font1));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Name Of Country",font1));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Name of Authority",font1));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Application Number",font1));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Status of Application",font1));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("In(Country)",font1));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("On (Date of Application)",font1));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        
        table8th.setHeaderRows(1);
        table8th.setWidthPercentage(100);
        
        if(secondsetbind!=null && secondsetbind.size()>0)
        {
        	for (int j =0; j< secondsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) secondsetbind.get(j);
   			if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table8th.addCell(""+(j+1));
   				
   				String ts1 =(String)Array.get(row, 4).toString();
   				String ts11 =(String)Array.get(row, 10).toString();
   				String[] t=ts1.split(" ");
   				String[] t1=ts11.split(" ");
   				 System.out.println("CONTROL ON 3602 = "+t[0]+" and other ="+t1[0]);
   				table8th.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 3),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase(t[0],  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 8),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 9),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase(t1[0],  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table8th);
        //8th ===end
        
        //9th table
        
        PdfPTable table7 = new PdfPTable(5);
		
        document.add(new Paragraph("9. Has the candidate variety been commercialised or otherwise exploited:",font));
		document.add(new Paragraph(" "));
		PdfPCell c7 = new PdfPCell(new Phrase("Selected Yes/No",font1));
		c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Data of the first sale of the variety",font1));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Country (ies) where Protection is made",font1));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Denomiantion Used",font1));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Trademark used, if any",font1));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
       
        
        table7.setHeaderRows(1);
        table7.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   			// System.out.println("Printing ==>> "+(String)Array.get(row, 8)+"Printing row as == >> "+row);
   			 //if((String)Array.get(row, 8) =="Agent" || Boolean.toString((Boolean)Array.get(row, 8)).equals("Agent") )
   			if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				//table7.addCell(""+(j+1));
   				String val1=String.valueOf(Array.get(row, 38));
   				System.out.println("Printing val1= "+val1);
   				 if(val1.equals("true"))
   				{ table7.addCell(new Phrase("Yes",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				else if(val1.equals("false")) 
   				{table7.addCell(new Phrase("No",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				else {table7.addCell(new Phrase(" "));}
   				
   				 String ts1 =(String)Array.get(row, 39).toString();
   				 String[] t=ts1.split(" ");
   				System.out.println("CONTROL ON 2171 = "+t[0]);
   				//String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(tt1.getDate()));
   				table7.addCell(new Phrase("Date:"+t[0]+"\n"+(String)Array.get(row, 40),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table7.addCell(new Phrase((String)Array.get(row, 42),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table7.addCell(new Phrase((String)Array.get(row, 43),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table7.addCell(new Phrase((String)Array.get(row, 44),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table7);
        
        //9th table end
        
        //10th tab
        
        PdfPTable table10th = new PdfPTable(6);
		
        document.add(new Paragraph("10.(a.) If the candidate variety is a hybrid, state whether all the "
        		+ "parental lines required for the repeated propagation of the hybrid are bred "
        		+ "exclusively by the applicant(s):",font));
		document.add(new Paragraph(" "));
		PdfPCell c10tha = new PdfPCell(new Phrase("Serial",font1));
		c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
		table10th.addCell(c10tha);
		c10tha = new PdfPCell(new Phrase("Type of line",font1));
		c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10th.addCell(c10tha);
        c10tha = new PdfPCell(new Phrase("Parental Line(s)",font1));
        c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10th.addCell(c10tha);
        c10tha = new PdfPCell(new Phrase("Source",font1));
        c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10th.addCell(c10tha);
        c10tha = new PdfPCell(new Phrase("Denominations",font1));
        c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10th.addCell(c10tha);
        c10tha = new PdfPCell(new Phrase("Authorized Letter Obtained",font1));
        c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10th.addCell(c10tha);
       
        
        table10th.setHeaderRows(1);
        table10th.setWidthPercentage(100);
        
        if(thirdsetbind!=null && thirdsetbind.size()>0 )
        {
        	for (int j =0; j< thirdsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) thirdsetbind.get(j);
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				
   				
   				table10th.addCell(""+(j+1));
   				table10th.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10th.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10th.addCell(new Phrase((String)Array.get(row, 4),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10th.addCell(new Phrase((String)Array.get(row, 3),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10th.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table10th);
        //10th-b case
        
        PdfPTable table10thb = new PdfPTable(5);
		
        document.add(new Paragraph("10(b). If the candidate variety is a hybrid, state whether all the "
        		+ "parental lines required for the repeated propagation of the hybrid are bred "
        		+ "exclusively by the applicant(s):",font));
		document.add(new Paragraph(" "));
		PdfPCell c10thb = new PdfPCell(new Phrase("Select YES/NO",font1));
		c10thb.setHorizontalAlignment(Element.ALIGN_LEFT);
		table10thb.addCell(c10thb);
		c10thb = new PdfPCell(new Phrase("Denomination",font1));
		c10thb.setHorizontalAlignment(Element.ALIGN_LEFT);
		table10thb.addCell(c10thb);
		c10thb = new PdfPCell(new Phrase("Geographical Source",font1));
		c10thb.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10thb.addCell(c10thb);
        c10thb = new PdfPCell(new Phrase("Detail of Attribution(origion)",font1));
        c10thb.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10thb.addCell(c10thb);
        c10thb = new PdfPCell(new Phrase("Details of owner farmer/village community/Institution/Organisation",font1));
        c10thb.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10thb.addCell(c10thb);
        
        
        table10thb.setHeaderRows(1);
        table10thb.setWidthPercentage(100);
        
        if(fourthsetbind!=null && fourthsetbind.size()>0)
        {
        	for (int j =0; j< fourthsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) fourthsetbind.get(j);
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				
   				String val1=String.valueOf(Array.get(row, 7));
   				//System.out.println("Printing val1= "+val1);
   				if(val1.equals("true"))
   				{ table10thb.addCell(new Phrase("Yes",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				else if(val1.equals("false")) 
   				{table10thb.addCell(new Phrase("No",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				else {table10thb.addCell(new Phrase(" "));}
   				
   				
   				table10thb.addCell(new Phrase((String)Array.get(row, 1),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10thb.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10thb.addCell(new Phrase((String)Array.get(row, 3),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10thb.addCell(new Phrase((String)Array.get(row, 4),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table10thb);
        //10th-b ends
        
        
//10th-b case
        
        PdfPTable table10thc = new PdfPTable(4);
		
        document.add(new Paragraph("10(c). If the candidate is an Essentially Derived Variety, state "
        		+ "whether a variety registered under the Protection of Plant Varieties and Farmers' "
        		+ "Rights, Act 2001 and notified in the Plant Variety of Journal of India has been used"
        		+ " as the initial variety:",font));
		document.add(new Paragraph(" "));
		PdfPCell c10thc = new PdfPCell(new Phrase("Select YES/NO",font1));
		c10thb.setHorizontalAlignment(Element.ALIGN_LEFT);
		table10thc.addCell(c10thc);
		c10thc = new PdfPCell(new Phrase("Denomination",font1));
		c10thc.setHorizontalAlignment(Element.ALIGN_LEFT);
		table10thc.addCell(c10thc);
		c10thc = new PdfPCell(new Phrase("Geographical Source",font1));
		c10thc.setHorizontalAlignment(Element.ALIGN_LEFT);
		table10thc.addCell(c10thc);
		c10thc = new PdfPCell(new Phrase("Details of owner farmer/village community/Institution/Organisation",font1));
		c10thc.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10thc.addCell(c10thc);
        
        
        table10thc.setHeaderRows(1);
        table10thc.setWidthPercentage(100);
        
        if(application10c!=null && application10c.size()>0)
        {
        	for (int j =0; j< application10c.size() ;j++)
   		 {
   			 Object[] row= (Object[]) application10c.get(j);
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				
   				String val1=String.valueOf(Array.get(row, 3));
   				//System.out.println("Printing val1= "+val1);
   				if(val1.equals("y"))
   				{ table10thc.addCell(new Phrase("Yes",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				else if(val1.equals("n"))
   				{table10thc.addCell(new Phrase("No", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				else {table10thc.addCell(new Phrase(" "));}
   				
   				table10thc.addCell(new Phrase((String)Array.get(row, 0),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10thc.addCell(new Phrase((String)Array.get(row, 1),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10thc.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				//table10thc.addCell((String)Array.get(row, 4));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table10thc);
        //10th-c ends
        
        //10th-ends
        
        //11th tab
        
        PdfPTable table11th = new PdfPTable(1);
        document.add(new Paragraph("11. In case exotic germplasm was used in the derivation of "
        		+ "the variety or hybrid, give details:",font));
		document.add(new Paragraph(" "));
		
        //table4.setHeaderRows(1);
		table11th.setWidthPercentage(100);
        
        if(fourthsetbind !=null && fourthsetbind.size()>0)
        {
        	//for(int j=0;j<firstsetbind.size();j++)
        		{
        		Object[] row= (Object[]) fourthsetbind.get(0);
        		
        	
        	System.out.println("Printing 11th 3871 set= "+Array.get(row,6));	
   			 try
   			 {
   				table11th.addCell(new Phrase((String)(Array.get(row, 6)),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			 }
   			 catch(NullPointerException ne) {}
   			
   			}
        }	
        document.add(table11th); 
        
        //11th ends
        
        Paragraph para3= new Paragraph(" ");
        Paragraph para11= new Paragraph(" ");
        Paragraph para= new Paragraph("Declaration",font);
        Paragraph para1= new Paragraph(" ");
        Paragraph para2= new Paragraph("I/We hereby apply for the grant of registration of the "
        		+ "candidate variety with the above said denomination and I/we am are conversant with "
        		+ "the Protection of Plant Varieties and Farmers' Rights Act, 2001 and Rules thereof "
        		+ "related to this application."+"\n"+"I/We hereby declare that no person other than "
        		+ "the person or persons mentioned in this application has been involved in the breeding, "
        		+ "or discovery or development of the candidate variety."+"\n"+"I/We hereby declare that "
        		+ "the candidate variety complies with the sub- section (3) of section 29 of Protection "
        		+ "of Plant Varieties and Farmers Rights Act, 2001."+"\n"+"I/We hereby declare that "
        		+ "I/we have not applied for or received a trademark for the said denomination of the "
        		+ "variety"+"\n"+"I/We hereby attach an affidavit in compliance with clause (C) of sub- "
        		+ "section (1) of section 18 of Protection of Plant Varieties and Farmers' Rights Act, "
        		+ "2001."+"\n"+"I/We hereby declare that the information given in this application for "
        		+ "the registration of t he above said candidate variety, including annexure and all "
        		+ "supporting documents are complete, true and correct to the best of my/our knowledge, "
        		+ "information and belief and no information has been wilfully concealed."+"\n"+"I/We "
        		+ "hereby declare that genetic material or parental material acquired for breeding, "
        		+ "evolving or developing the variety has been lawfully acquired."+"\n"+"I/We hereby "
        		+ "declare that I/We shall abide by all the provisions and guidelines of Protection of "
        		+ "Plant Varieties and Farmers' Rights Act, 2001",font1);
       
        para.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(para3);
        document.add(para11);
        document.add(para);
        document.add(para1);
        document.add(para2);
        
        PdfPTable table8 = new PdfPTable(1);
        PdfPTable table9 = new PdfPTable(1);
        
        document.add(new Paragraph("I Agree",font1));
		document.add(new Paragraph(" "));
		PdfPCell c8 = new PdfPCell(new Phrase("Place",font1));
		c8.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8.addCell(c8);
        PdfPCell c9 = new PdfPCell(new Phrase("Date",font1));
		c9.setHorizontalAlignment(Element.ALIGN_LEFT);
        table9.addCell(c9);
        

        table8.setWidthPercentage(100);
       
        table9.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   			if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table8.addCell(new Phrase((String)Array.get(row, 47),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				String ts1 =(String)Array.get(row, 46).toString();
  				 String[] t=ts1.split(" ");
   				table9.addCell(new Phrase(t[0],  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table8);
        document.add(table9);
       
        //Attachment case
        
        Paragraph para5= new Paragraph(" ");
        Paragraph para12= new Paragraph(" ");
        Paragraph para13= new Paragraph("Attachments:",font);
        Paragraph para14= new Paragraph(" ");
        Paragraph para4 = new Paragraph("Following are the attachments (duly signed/seal) "
        		+ "submitted along  with of the application (note that wherever signature "
        		+ "is affixed in the application or attachments, all such "
        		+ "signatures shall be in the original",font1);
        
        document.add(para12);
        document.add(para13);
        document.add(para14);
        document.add(para5);
        document.add(para4);
        
        document.add(new Paragraph(" "));
        PdfPTable table10 = new PdfPTable(3);
        PdfPCell c10 = new PdfPCell(new Phrase("Serial No",font1));
		c10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(c10);
        c10 = new PdfPCell(new Phrase("Title",font1));
		c10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(c10);
        c10 = new PdfPCell(new Phrase("Attachment",font1));
		c10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(c10);
        
        table10.setWidthPercentage(100);
        
        if(document_checklistdata!=null && document_checklistdata.size()>0)
        {
        	for (int j =0; j< document_checklistdata.size() ;j++)
   		 {
   			 Object[] row= (Object[]) document_checklistdata.get(j);
   			//if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table10.addCell(""+(j+1));
   				table10.addCell(new Phrase((String)Array.get(row, 4),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			 }catch(NullPointerException ne) {}
   			 
   			}
   			 
   		 } 
   			 
        }
        document.add(table10);
        
        Paragraph para10 =new Paragraph(" If felt necessary attach colour pictures of specific  characteristics "
        		+ "used for establishing distinctiveness.Please sign each "
        		+ "page of the application and other document on the left margin",font1);
        document.add(para10);
        
    //Attachment ends
        
    //Technical Questionarie start
        document.add(new Paragraph("Technical Questionnaire",font));
		
        if(get_tech_data!=null && get_tech_data.size()>0)
        {
        	
        
        PdfPTable table11 = new PdfPTable(3);
       
        table11.setWidths(new float[] { (float) 0.3, 3,2});
       document.add(new Paragraph(" "));
		PdfPCell ctec = new PdfPCell(new Phrase("1"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
        ctec = new PdfPCell(new Phrase("Name of the Applicant/breeder/company",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getName(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));

		
		ctec = new PdfPCell(new Phrase("2"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Year of Establishment",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getYear_of_establishment(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		ctec = new PdfPCell(new Phrase("3"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("If registered company under Company's Act 1956 (Give details)",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getRegistered_address(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		ctec = new PdfPCell(new Phrase("4"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Location of corporate office and address",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getLocation_office(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		    
		
		
		ctec = new PdfPCell(new Phrase("5.1"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Telephone",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getTelephone(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("5.2"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Fax",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getFax(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		ctec = new PdfPCell(new Phrase("5.3"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Email",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getEmail(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("6"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Name Of Candidate Variety",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCandidatevariety(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
			
		
		ctec = new PdfPCell(new Phrase("a"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Has it been released in any Convention Country earlier",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);	
		table11.addCell(new Phrase(""+get_tech_data.get(0).getReleasedearlier(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("b"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Pedigree/genealogy",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getPedigree(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("b.ii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("File(If Any)",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getPedigree_imagename(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("c"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Breeding of Candidate Variety",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		 table11.addCell("");
			
		ctec = new PdfPCell(new Phrase("(i)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Origination",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(""+get_tech_data.get(0).getOrigination(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("(ii)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Parental material (name of the parental material, characteristics of the parental material,distinguishable from the candidate variety). If the variety was developed by selection, then the number of selection cycles completed before fixing it",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getParental_material(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("(iii)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Breeding technique/procedure used",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell("");
		
		
		ctec = new PdfPCell(new Phrase("(iv)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Selection criteria used",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getSelectioncriteria(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("(v)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Stage of selection and multiplication",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getSelectionstage(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("(vi)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Location where breeding was conducted",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getBreeding_location(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		ctec = new PdfPCell(new Phrase("7"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Particulars of comparative trial conducted by the applicant, "
				+ "if any."+"\n"+"Information on the location, place, period and year/month of "
				+ "comparative trial conducted method of cultivation such as open field, facilities, "
				+ "planting, potting etc., scale of cultivation, reference varieties used, criteria for "
				+ "choice of the reference varieties, design of experiment, method of analysis of "
				+ "variance experimental error where applicable, and other details."+"\n"+"NOTE: "
				+ "Applicant may, furnish data, tables, copy (ies) of publication(s) related to the "
				+ "details of breeding, comparative trial and comparative data in addition to table of "
				+ "characteristics of candidate and reference varieties. This information provided under "
				+ "this item will not be published by the Authority but will be used to facilitate "
				+ "examination of candidate variety.",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_trial_filename(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("i"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Location",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_location(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("ii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Place",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_place(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("iii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Period",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_period(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("iv"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Year and month of comparative trial",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_year()+":"+get_tech_data.get(0).getComparative_month(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("v"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Conducted method of cultivation",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_cultivation(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("vi"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Scale of Cultivation",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_reference(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		if(techques_ref !=null && techques_ref.size()>0)
		{	
		for(int tq=0;tq<techques_ref.size();tq++)
		{
		Object[] row= (Object[]) techques_ref.get(tq);
		//String tecq="";
		ctec = new PdfPCell(new Phrase("vii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Reference Varieties Used",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		//tecq=""+techques_ref.get(tq).getTechnical_questionnaire_reference();
		table11.addCell(new Phrase( (String)Array.get(row, 1),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		}
		}else {
			ctec = new PdfPCell(new Phrase("vii"));
			ctec.setVerticalAlignment(Element.ALIGN_LEFT);
			table11.addCell(ctec);
			ctec = new PdfPCell(new Phrase("Reference Varieties Used",font));
			ctec.setVerticalAlignment(Element.ALIGN_LEFT);
			table11.addCell(ctec);
			//tecq=""+techques_ref.get(tq).getTechnical_questionnaire_reference();
			table11.addCell(" ");
			
		}



		
		ctec = new PdfPCell(new Phrase("viii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Criteria for Choice of the reference varieties",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_criteria(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("ix"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Design of experiment",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_design(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("x"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Method of Analysis of variance experimental error",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_analysis(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("xi"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Other Details",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		 table11.addCell(new Phrase(get_tech_data.get(0).getComparative_other(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
			
		
		ctec = new PdfPCell(new Phrase("8"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Characteristics of the candidate variety"+"\n"+"Please describe "
				+ "characteristics of the variety in the subheadings: Plant, Stem, Leaf, Inflorescence, "
				+ "Flower and flower parts, Fruit and fruit parts, Seed etc. Describe characters within "
				+ "subheadings generally in the following order: habit, height, length, width, size, "
				+ "shape, colour (RHS colour chart reference with edition). Refer the specific guideline "
				+ "wherever necessary for clarity of description.",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_group(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("a(i)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Give group characters.",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_distinguishing(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("a(ii)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Distinguishing characteristics (descriptive or elaborate)",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		ctec = new PdfPCell(new Phrase("b"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Table of characteristics between candidate denomination and "
				+ "reference variety"+"\n"+"Please give replicated values for all of its distinguishing "
				+ "and other description for important characteristics along with the corresponding "
				+ "average values of the references varieties."+"\n"+"NOTE: Two or more reference "
				+ "varieties should be compared with the candidate variety in the characteristics table, "
				+ "including one deemed to be the most similar variety and other(s) as obvious/similar "
				+ "as possible. If you provide this information it will facilitate the Authority in "
				+ "their DUS test further in examination of the candidate variety.",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		
		ctec = new PdfPCell(new Phrase("9"));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Characteristics of the reference varieties",font1));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		ctec = new PdfPCell(new Phrase("a"));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Most similar variety",font1));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		ctec = new PdfPCell(new Phrase("i"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Denomination",font1));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_denomination(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("ii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Basis of choice of this variety for comparison",font1));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_variety_comparison(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("iii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Distinguishable Characteristics",font1));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_variety_distinguishable(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("b"));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Other reference variety",font1));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		
		ctec = new PdfPCell(new Phrase("i"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Denomination",font1));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_variety_distinguishable(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("ii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Basis of choice of this variety for comparison",font1));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_variety_referencedenomination(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("iii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Distinguishable Characteristics",font1));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_basic_distinguishable(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("10"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Statement of distinctiveness of candidate variety"+"\n"+"Please "
				+ "give a distinctiveness statement covering a brief summary of the characteristics that "
				+ "distinguish the candidate variety from all varieties of common knowledge. "
				+ "The Distinctiveness statement should include, (i) names of reference variety (ies) "
				+ "that have been observed most similar to the candidate variety, and (ii) salient "
				+ "comparison for major distinguishing characteristics between the candidate variety and "
				+ "the similar/reference variety (ies).",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getStatement_distinctiveness(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("11"));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Statement on uniformity and stability of candidate "
				+ "variety"+"\n"+"Please give a brief statement describing any variation in the "
				+ "variety that may be regarded as part of its normal uniform or stable expression, "
				+ "which is predictable, capable of being described in clear terms and commercially "
				+ "acceptable. This should include description and frequency of any off-types, variants "
				+ "or mutations. In your opinion what should be the frequency of off types or any other "
				+ "describable variation beyond which the candidate variety shall be deem to be "
				+ "non-uniform. Also please point out which are the traits that may be particularly "
				+ "referred to as indicators to determine an unstable expression of the phenotype of "
				+ "candidate variety.",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		
		ctec = new PdfPCell(new Phrase("i"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Uniformity",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getStatement_uniformity(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("ii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Stability",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getStatement_stability(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("12"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Methods for maintaining the candidate variety"+"\n"+"Please "
				+ "provide in a brief statement as to how the propagating material will be maintained "
				+ "throughout the duration of the plant breeder�s right, and complete address where "
				+ "the variety will be maintained. This should include status of varieties that are "
				+ "not propagated by seeds including place and method of maintenance and storage of "
				+ "their vegetative material."+"\n"+"NOTE: The holder of a plant breeder's right is "
				+ "responsible for ensuring that propagating material representative of the variety is "
				+ "maintained for the duration of the right.",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getMethods_candidate(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("13"));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Information on variety registered in Convention Countries.",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		
		
		ctec = new PdfPCell(new Phrase("a"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("What were the grouping characters in that application for this candidate variety?",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		
		ctec = new PdfPCell(new Phrase("b"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("What was the Distinctiveness Uniformity and Stability parameter on which it was registered?",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getGrouping_characters(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("c"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("What is the variation in important trait with respect to first filing and the present one (Attach photograph)?",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getVariation_important_trait_filename(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		ctec = new PdfPCell(new Phrase(" "));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Has the Variety been withdrawn in the first filed country from cultivation or banned or from any of the subsequently released country?",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell( new Phrase(get_tech_data.get(0).getStability_parameter(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase(" "));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("If so, the reasons (supplement with information)?",font1));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getSupplement_information_name(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		table11.setWidthPercentage(100);
		table11.setTotalWidth(520);
		
		//System.out.println("Control on 4419 tech size= "+get_tech_data.size());
		 document.add(table11);
        
        }
        
//Attachment Ends Here----   
     
        
//Payment start
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        PdfPTable tablepay = new PdfPTable(9);
	
        document.add(new Paragraph("Payment:",font));
		document.add(new Paragraph(" "));
		PdfPCell paytab = new PdfPCell(new Phrase("Serial No",font1));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("Amount",font1));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("Payment Mode",font1));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("DD No(If Any)",font1));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("DD Date(If Any)",font1));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("Payment Status",font1));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("Bank Name",font1));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("Bank Branch",font1));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("File Name",font1));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
        
        
		tablepay.setHeaderRows(1);
		tablepay.setWidthPercentage(100);
        
        if(paymentdetailsmade!=null)
        {
        	for (int j =0; j< paymentdetailsmade.size() ;j++)
   		 {
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				Date dd=paymentdetailsmade.get(j).getDddate();
   				//String datechanged=String.vadd.getDate();
   			 Date date = dd;  
             DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
             String strDate = dateFormat.format(date); 
             
   				tablepay.addCell(""+(j+1));
   				//phrase.add(new Chunk(paymentdetailsmade.get(j).getAmount(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getAmount(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getPaymentmode(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getDdnumber(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(strDate,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getPaymentstatus(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getBankname(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			    tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getBranchname(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getDocumentname(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				}catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(tablepay);
        
	//Payment end---------------
		
		int hash1=document.hashCode();
		System.out.println("hashcode for application pdf generated:"+hash1);
		document.close();
		writer.close();
		}
   		 
		catch(IOException | DocumentException  es) 
		{es.printStackTrace();}
		
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			act.setLogin_time_stamp(dt);
			act.setActivity("form2 pdf generated");
			act.setUrl(val);
			activitylogservice.save(act);
		   mav.addAttribute("id",id);
	
	 //return "view_applicationdetails_f2";
		   return "redirect:/ppvfra/Application_pdf-"+id+".pdf";
	}

//pdf for form1
	
	
	@RequestMapping(value = "/makepdf_f1/{id}", method = RequestMethod.GET)
	public String pdf_forming_f1(@PathVariable(name = "id") Long id,Model mav,HttpServletRequest request) 
	{
		/// Getting Logged in user
		int userid = 0;
		User userdeail =null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				userid = userdeail.getId();
				 } else {
				String username = principal.toString();
			}
			/// end Getting Logged in user
			System.out.println("printing user id: "+userid);
	  Applications applications = applicationservice.getById(id.intValue());
	  mav.addAttribute("applications", applications);
	  Integer tqid = null;
	  
	  List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
	  if(checkbox !=null)
	  {
		  mav.addAttribute("checkbox",checkbox);
		 }
	  String varietyval= candidatevarietydetailsrepository.hibridVarietyCount(id.intValue());
	  if(varietyval !=null && varietyval.length() >0)
		 {
		  mav.addAttribute("varietyval",varietyval);
		 }
	  else {
		  mav.addAttribute("varietyval",null);
	  }
	  
	 if( applicationtqrepository.getIdByApplicationId(id.intValue())  != 0 ) {
	  tqid = applicationtqrepository.getTqId(id.intValue());
	  ApplicationTechnicalQuestionnaire applicatiotq = applicationtechnicalquestionnaireservice.getById(tqid);
	  if(applicatiotq != null)
	  {
		  mav.addAttribute("applicatiotq",applicatiotq);
	  }
	  else {
		  mav.addAttribute("applicatiotq",applicatiotq);
	  }
	 }
	  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id.intValue());
	  if(agentcontact !=null && agentcontact.size() > 0)
	  {
		  mav.addAttribute("agentcontact",agentcontact);
	  }
	  else {
		  mav.addAttribute("agentcontact",1);
	  }
	  
	  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
	  mav.addAttribute("applicationdocuments",applicationdocuments);
	  
	  System.out.println("Trace For Doc Upload begin");
	  
	  //Umesh Commenting below method and adding new case here-----
	  //Done On 16-01-2020
	  //List<Object[]> document_checklistdata=documentchecklistrepository.getformdetails();
	  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationid(id.intValue()); 
	  mav.addAttribute("document_checklistdata", document_checklistdata);
	  System.out.println("Trace For Doc Upload end");
	  
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  mav.addAttribute("modulelist", modulelist);
	  mav.addAttribute("editmode", 1);
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  mav.addAttribute("modulelist2", modulelist2);
	  
	  List<Object[]> binded_values = applicationsrepository.bind_values(id.intValue());
	  mav.addAttribute("binded_values",binded_values.get(0));
	  
	  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
	  mav.addAttribute("applicanttypelist", applicanttypelist);
	  
	  //List<Country> Country = countryservice.listall();
	  List<Country> Country = countryrep.getConutry();
	  mav.addAttribute("Country",Country);  
	  
	  List<Nationality> nationality = nationalityservice.listall();
	  mav.addAttribute("nationality",nationality);  
	  
	  //List<TypeLine> typeline = typelineservice.listall();
	  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
	  mav.addAttribute("typeline",typeline);
	  
      //List<States> State = stateservice.listall();
	  List<States> State=staterep.getstates();
	  mav.addAttribute("State", State);
	  
	  List<Districts> District = districtrepository.getalldistricts();
	  mav.addAttribute("District", District);
	    
	  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
	  mav.addAttribute("addcroplist", addcroplist);
		  
	  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
	  mav.addAttribute("cropspecieslist", cropspecieslist);
	 
	  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
	  mav.addAttribute("userapplicant", userapplicant);
	  
	  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id.intValue());
	  if(applicationcontact !=null && applicationcontact.size() >0) {
		 mav.addAttribute("applicationcontact",applicationcontact);
	  }
	  else
	  {
		  mav.addAttribute("applicationcontact",1);
	  }
	  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id.intValue());
	  mav.addAttribute("applicationnaturalcontact",applicationnaturalcontact);
	  
	  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
	  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
		  mav.addAttribute("detailofcomapnytype", detailofcomapnytype.get(0));
		 }
		  
	  
	  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id.intValue());
	  if(breadercontact !=null && breadercontact.size()>0) {
		  mav.addAttribute("breadercontact",breadercontact);
		  }
		  else {
			  mav.addAttribute("breadercontact",1);
		  }
	  
	  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id.intValue());
	  if(conventioncontry != null && conventioncontry.size() >0 ) {
		  mav.addAttribute("conventioncontry",conventioncontry);
		  }
		  else {
			  mav.addAttribute("conventioncontry",1);
		  }
	  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id.intValue());
	  if(parentaline != null && parentaline.size() >0)
	  {
		  mav.addAttribute("parentaline",parentaline);
	  }else {
		  mav.addAttribute("parentaline",1);
	  }
 
	  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id.intValue());
	  if(tqreference != null && tqreference.size() >0)
	  {
		  mav.addAttribute("tqreference",tqreference);
	  }else {
		  mav.addAttribute("tqreference",1);
	  }
	  
	  List<States> office_state = staterep.findAll();
	  mav.addAttribute("office_state",office_state);

	  ApplicationContacts contacts = new ApplicationContacts();
	  mav.addAttribute("contact",contacts);
	  
	  ApplicationContacts3 contacts3 = new ApplicationContacts3();
	  mav.addAttribute("contact3",contacts3);
	  
	  ApplicationContacts7 contacts7 = new ApplicationContacts7();
	  mav.addAttribute("contact7",contacts7);
	 
	  ApplicationConventionCountries convention = new ApplicationConventionCountries();
	  mav.addAttribute("convention",convention);
	 
	  ApplicationParentalline parental = new ApplicationParentalline();
	  mav.addAttribute("parental",parental);
	  
	  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
	  mav.addAttribute("parentalother",parentalother);
	 
	  ApplicationContacts contactform2 = new ApplicationContacts();
	  mav.addAttribute("contactform2",contactform2);
	  
	  List<Object[]> attachmentlist = applicationdocumentrepository.getattachmentdetails(id.intValue());
	  mav.addAttribute("attachmentlist", attachmentlist);
	  
	 Integer paymentid = null;
	 if(applicationpaymentsrepository.getPaymentByApplicationid(id.intValue()) != null && applicationpaymentsrepository.getPaymentByApplicationid(id.intValue()).size() > 0	 ) 
	 {
		 
		List<ApplicationPayments> paymentdetailsmade =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id.intValue());
		if(paymentdetailsmade!=null)
			 mav.addAttribute("paymentdetailsmade",paymentdetailsmade);
		
			
	
	   
	 }
	 else {
		 ApplicationPayments payment = new ApplicationPayments();
		 mav.addAttribute("payment",payment);
	 }
	
	 if( applicationtqrepository.getIdByApplicationId(id.intValue())  != 0 ) {
		 tqid = applicationtqrepository.getTqId(id.intValue());
		 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
		 mav.addAttribute("technicalquestion",question2);
	 }
	 else {	
		  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
		  mav.addAttribute("technicalquestion",question2);
	 }
	 
	 if(applications.getVarirtytypeid() !=null) {
	 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id.intValue());
	 if(varietyandsubvariety !=null && varietyandsubvariety.size() > 0)
	 {
	mav.addAttribute("varietyandsubvariety",varietyandsubvariety.get(0));
	 }
	 
	 }

	 List<Object[]> firstsetbind = applicationrepository.details_portion1(id.intValue());
	 List<Object[]> secondsetbind = applicationrepository.details_portion2(id.intValue());
	 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id.intValue());
	 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id.intValue());
	 List<Object[]> fifthsetbind = applicationrepository.details_portion5(id.intValue());
		 
	 if(firstsetbind != null && firstsetbind.size()>0) {
		 mav.addAttribute("firstsetbind",firstsetbind.get(0));
		 mav.addAttribute("firstsetbind1",firstsetbind);
	 if(firstsetbind.get(0) !=null)
	 {
		 for (int j =0; j< firstsetbind.size() ;j++)
		 {
			 Object[] row= (Object[]) firstsetbind.get(j);
			 try
			 {
				 String multiple = (String)Array.get(row, 36);
				
				 String[] a = multiple.split(",");
				 int[] intArray = new int[a.length];
				
			      for (int i = 0; i < a.length; i++) {
			         String numberAsString = a[i];
			         intArray[i] = Integer.parseInt(numberAsString);
			        }
			      
			      if(j==0)
					 {
			    	  mav.addAttribute("intArray",intArray);
					}
			    }
			 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
			  
		 }
	 }
	 
	 }
	 
	 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
	 mav.addAttribute("candidatevar", candidatevar);
	 
	 if(secondsetbind != null && secondsetbind.size()>0) {
		 mav.addAttribute("secondsetbind",secondsetbind.get(0));
		 mav.addAttribute("secondsetbind2",secondsetbind);
	 }
	 
	 if(thirdsetbind !=null && thirdsetbind.size()>0)
	 {
		 mav.addAttribute("thirdsetbind",thirdsetbind.get(0));
		 mav.addAttribute("thirdsetbind3",thirdsetbind);
	 }	
	
	 if(fourthsetbind !=null && fourthsetbind.size()>0)
	 {
		 mav.addAttribute("fourthsetbind",fourthsetbind.get(0));
		 mav.addAttribute("fourthsetbind4",fourthsetbind);
	 }
	 
	 
	 if(fifthsetbind !=null && fifthsetbind.size() > 0)
		{
		 mav.addAttribute("fifthsetbind",fifthsetbind.get(0));
		 }
	 
	 long statusofapplication = applicationsrepository.returnapplicationstat(id.intValue());
	if(statusofapplication != 0 && statusofapplication<2)
		mav.addAttribute("appstatus",statusofapplication);
	
	//Umesh Adding here on 14-01-2020 -------
	//Added Here For Name And Role Showing in header
		
	List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
	mav.addAttribute("usrname_header_val",usrname_header_val);
	List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
	mav.addAttribute("rolespresent",rolespresent);
	
	mav.addAttribute("date_verifying",LocalDate.now());
	
	mav.addAttribute("id_fs1",id);
	 
	  
	List<Object[]> second_screen= applicationrepository.secondscreen_data(id.intValue());
	if(second_screen.size()>0 && second_screen!=null)
	{
		mav.addAttribute("second_screen",second_screen);
	}
	
	     
	///**********************************FILE GENERATION PORTION******	     
		
			 
	 List<Object[]> fifthsetbind_newcase = applicationrepository.details_portion_newcase(id.intValue());
	 List<ApplicationPayments> paymentdetailsmade =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id.intValue());
//	 if( applicationtqrepository.getIdByApplicationId(id.intValue())  != 0 )
	
//	 if(applicationtqrepository.getIdByApplicationId_pdfmethod(id.intValue())  != 0)	 
		 tqid = applicationtqrepository.getIdByApplicationId_pdfmethod(id.intValue());
	 System.out.println("Getting tq id as 2677 === >> "+tqid);
	
	 // ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
	 List<ApplicationTechnicalQuestionnaire> get_tech_data=null;
	 if(tqid!=0)
	 get_tech_data =applicationtechquestionrep.get_tech_data(tqid);
	
	System.out.println("Getting tech data="+get_tech_data);
	
	 List<Object[]> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq_forpdf(id.intValue());
		// System.out.println("Trace on 594  as = "+techques_ref.size());
		 
		
	 
	
			 
	 String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
		try {
			String FILE="";
					
			System.out.println("Control On Line 2676");
			    	
		StringBuilder filePath = new StringBuilder( 
		UPLOAD_FILEPATH+"PPVFRA/Application_pdf-"+id);
		
		Document document = new Document();
		FILE = UPLOAD_FILEPATH+"PPVFRA/Application_pdf-"+id+".pdf";
		PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(FILE));
		document.open();
		
		
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN,10,BaseColor.BLACK);
		Font font1 = FontFactory.getFont(FontFactory.TIMES_BOLD,10,BaseColor.BLACK);
		System.out.println("start pdf process");
		
		
	//	Image image=Image.getInstance("C:\\WORK\\Projectworkspace\\PPVFRA\\src\\main\\resources\\static\\img\\brand_logo3.png");
	//	Image image2=Image.getInstance("C:\\WORK\\Projectworkspace\\PPVFRA\\src\\main\\resources\\static\\img\\ppv-brand.png");
	
	Image image=Image.getInstance("http://localhost:8081/assets/img/brand_logo3.png");
	Image image2=Image.getInstance("http://localhost:8081/assets/img/ppv-brand.png");
			
		/*document.add(image);*/
		
		String ss ="Government of India";
		String s1 ="Office of the Registrar";
		String s2 ="Protection of Plant Varieties and Farmers Rights Authority";
		String s3 ="NASC Complex, DPS Marg,";
		String s4 ="Opposite Todapur Village, New Delhi - 110012";
		
		
		PdfPTable table12 = new PdfPTable(3);
		table12.setWidths(new float[] { 1, 7, 1 });
		
		PdfPCell c15 = new PdfPCell();
        c15.setHorizontalAlignment(Element.ALIGN_LEFT);
        table12.addCell(c15);
        c15 = new PdfPCell();
        c15.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table12.addCell(c15);
        c15 = new PdfPCell();
        c15.setHorizontalAlignment(Element.ALIGN_LEFT);
        c15.setBorder(Rectangle.NO_BORDER);
        table12.addCell(c15);
        
        table12.setHeaderRows(1);
        table12.setWidthPercentage(100);
        table12.setTotalWidth(520);
        table12.setLockedWidth(true);
        
        String pp= ss+"\n"+s1+"\n"+s2+"\n"+s3+"\n"+s4; 
        table12.addCell(image);
        table12.addCell(pp);
        table12.addCell(image2);
        document.add(table12);
        
        //table12.setHeaderRows(1);
        table12.setWidthPercentage(100);
        //table12.getDefaultCell().setBorder(Rectangle.NO_BORDER);
         
        
		document.add(new LineSeparator(0.5f, 100, null, 0, -5));
		
		// Creating a table object 
		float[] columnwidth= {1};
		PdfPTable table = new PdfPTable(columnwidth);
			
		// first table
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		
		document.add(new Paragraph("Identity Of The Applicant(s):",font1));
		document.add(new Paragraph(" "));
		PdfPCell c1 = new PdfPCell(new Phrase("Identity Of The Applicant(s)",font));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);
        
        table.setHeaderRows(1);
        table.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	Object[] row= (Object[]) firstsetbind.get(0);
        		String applicant_name="";
   			 try
   			 {
   			 applicant_name = (String)Array.get(row, 16);
   			 }catch(NullPointerException ne) {}
   			 table.addCell(new Phrase(applicant_name,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	
        }
        document.add(table);
		//First table end
        
        //2nd table
        //float[] columnwidth2= {1};
		PdfPTable table2 = new PdfPTable(9);
		
        document.add(new Paragraph("2.Name(s) and Nationality of Applicant(s)",font1));
		document.add(new Paragraph("(a.) (If natural person):",font1));
		PdfPCell c2 = new PdfPCell(new Phrase("Serial No.",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Name",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Address",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Country",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("State",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("District",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Pincode",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("City",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        c2 = new PdfPCell(new Phrase("Nationality",font));
        c2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(c2);
        
        table2.setHeaderRows(1);
        table2.setWidthPercentage(100);
        
        if(applicationcontact!=null && applicationcontact.size()>0)
        {
        	for (int j =0; j< applicationcontact.size() ;j++)
   		 {
   			 Object[] row= (Object[]) applicationcontact.get(j);
   			 try
   			 {
   				table2.addCell(new Phrase(""+(j+1),new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 10),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 11),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 8),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 9),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table2.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   	        	
   			 }catch(NullPointerException ne) {}
   		 } 
   			 
        }
        
        document.add(table2);
		//2nd a part ends
        
        PdfPTable table_2c = new PdfPTable(5);
        document.add(new Paragraph("(b.) (If a legal person; for example a firm or company or "
        		+ "institution):",font1));
		document.add(new Paragraph(" "));
		PdfPCell c_2 = new PdfPCell(new Phrase("Name",font));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        c_2 = new PdfPCell(new Phrase("Address of eshtablishment(Registered office)",font));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        c_2 = new PdfPCell(new Phrase("Year of Incorporation",font));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        c_2 = new PdfPCell(new Phrase("State whether the applicant legal person has non-Indian participation in capital or management",font));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        c_2 = new PdfPCell(new Phrase("Identify the Nationality",font));
        c_2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2c.addCell(c_2);
        
        table_2c.setHeaderRows(1);
        table_2c.setWidthPercentage(100);
        
        if(second_screen!=null && second_screen.size()>0)
        {
        	Object[] row= (Object[]) second_screen.get(0);
        	String vv=String.valueOf(Array.get(row, 4));
        	String v1=String.valueOf(Array.get(row, 5));
   			 try
   			 {
   			 table_2c.addCell(new Phrase((String)Array.get(row, 1),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2c.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2c.addCell(new Phrase((String)Array.get(row, 3),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			if(vv.equals("true"))
   			{
   				table_2c.addCell(new Phrase("Yes",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			}else if(vv.equals("false")) 
   			{
   				table_2c.addCell(new Phrase("No",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			}else { table_2c.addCell(new Phrase(" ")); }
   			
   			if(v1.equals("1"))
   			{
   				table_2c.addCell(new Phrase("Indian",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			}else {
   				table_2c.addCell(new Phrase(" "));
   			}
   			 }catch(NullPointerException ne) {}
   		
        }
        document.add(table_2c);
        
        //2nd b portion table ends
        
        PdfPTable table_2cc = new PdfPTable(8);
        document.add(new Paragraph("(c.) Indicate the name and address of the natural person, being "
        		+ "an employee of the legal person, who is duly authorized to represent the legal "
        		+ "person (example a director of a company or a partner of a firm):",font1));
		document.add(new Paragraph(" "));
		PdfPCell c_22 = new PdfPCell(new Phrase("Serial No",font));
		c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("Name",font));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("Address",font));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("Country",font));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("State",font));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("District",font));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("Pincode",font));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        c_22 = new PdfPCell(new Phrase("City",font));
        c_22.setHorizontalAlignment(Element.ALIGN_LEFT);
        table_2cc.addCell(c_22);
        
        table_2cc.setHeaderRows(1);
        table_2cc.setWidthPercentage(100);
        
        if(applicationnaturalcontact!=null && applicationnaturalcontact.size()>0)
        {
        	for (int j =0; j< applicationnaturalcontact.size() ;j++)
        	{	
        	Object[] row= (Object[]) applicationnaturalcontact.get(j);
        	//String vv=String.valueOf(Array.get(row, 4));
        	//String v1=String.valueOf(Array.get(row, 5));
   			 try
   			 {
   			table_2cc.addCell(new Phrase(""+(j+1),new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 3),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 4),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table_2cc.addCell(new Phrase((String)Array.get(row, 8),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			 }catch(NullPointerException ne) {}
   			 
          }
   		
        }
        document.add(table_2cc);
        
     //2 c table ends   
       
        //3rd Table
        PdfPTable table3 = new PdfPTable(9);
		
        document.add(new Paragraph(" 3.Name and Address of the Person to whom Correspondence related "
        		+ "to this application is to be sent:",font1));
		document.add(new Paragraph(" "));
		PdfPCell c3 = new PdfPCell(new Phrase("Serial No.",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Name",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Country",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("State",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("District",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Address",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Pincode/Zipcode",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Telephone",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        c3 = new PdfPCell(new Phrase("Fax",font));
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(c3);
        
        table3.setHeaderRows(1);
        table3.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   			// System.out.println("Printing ==>> "+(String)Array.get(row, 8)+"Printing row as == >> "+row);
   			 //if((String)Array.get(row, 8) =="Agent" || Boolean.toString((Boolean)Array.get(row, 8)).equals("Agent") )
   			if(Array.get(row, 8)!=null)
   				{
   		
   			if((String)Array.get(row, 8) =="Agent" || ((String)Array.get(row, 8)).equals("Agent") )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table3.addCell(new Phrase(""+(j+1), new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 4),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 11),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 5),   new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 12),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 17),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3.addCell(new Phrase((String)Array.get(row, 18),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   	        	
   			 }catch(NullPointerException ne) {}
   			 }
   			
   		 }
   			 
   		 } 
   			 
        }
        document.add(table3);
	
        //3rd -B case
        
        PdfPTable table3b = new PdfPTable(3);
		
        document.add(new Paragraph(" 3.(a):",font1));
		document.add(new Paragraph(" "));
		PdfPCell c3a = new PdfPCell(new Phrase("Principle place of business or domicile of applicant",font));
		c3a.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3b.addCell(c3a);
        c3a = new PdfPCell(new Phrase("Name",font));
        c3a.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3b.addCell(c3a);
        c3a = new PdfPCell(new Phrase("Country",font));
        c3a.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3b.addCell(c3a);
        
        table3b.setHeaderRows(1);
        table3b.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   			// System.out.println("Printing ==>> "+(String)Array.get(row, 8)+"Printing row as == >> "+row);
   			//if((String)Array.get(row, 8) =="Agent" || ((String)Array.get(row, 8)).equals("Agent") )
   			if(j ==0)
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				//table3.addCell(""+(j+1));
   				table3b.addCell(new Phrase((String)Array.get(row, 21),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3b.addCell(new Phrase((String)Array.get(row, 22),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table3b.addCell(new Phrase((String)Array.get(row, 23),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table3b);
		
        
        //3rd Table Ends
       
        //4th table start
        
        
        PdfPTable table4 = new PdfPTable(5);
        document.add(new Paragraph("4.General Information of Candidate variety:",font1));
		document.add(new Paragraph(" "));
		PdfPCell c4 = new PdfPCell(new Phrase("Crop Details",font));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Common Name Of Crop",font));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Botanical Name",font));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Family",font));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        c4 = new PdfPCell(new Phrase("Denomination(in Block letters)",font));
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(c4);
        
        table4.setHeaderRows(1);
        table4.setWidthPercentage(100);
        
        if(firstsetbind!=null&& firstsetbind.size()>0)
        {
        	Object[] row= (Object[]) firstsetbind.get(0);
        		String c_variety_cropdetail="";
        		String c_variety_commonname="";
        		String c_variety_botanical="";
        		String c_variety_family="";
        		String c_variety_deno="";
   			 try
   			 {
   				c_variety_cropdetail = (String)Array.get(row, 25);
   				c_variety_commonname = (String)Array.get(row, 26);
   				c_variety_botanical = (String)Array.get(row, 27);
   				c_variety_family = (String)Array.get(row, 28);
   				c_variety_deno = (String)Array.get(row, 29);
   			 }catch(NullPointerException ne) {}
   			table4.addCell(new Phrase(c_variety_cropdetail,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_commonname,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_botanical,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_family,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table4.addCell(new Phrase(c_variety_deno,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	
        }
        document.add(table4);
        
        //4th table ends
       
        //5th table
        
        PdfPTable table5th = new PdfPTable(6);
		
        document.add(new Paragraph("5.Type of variety(see in chapter III of the protection of "
        		+ "plant varieties and farmers' Right Authority,2003):",font1));
		document.add(new Paragraph(" "));
		PdfPCell c5th = new PdfPCell(new Phrase("Variety",font));
		c5th.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5th.addCell(c5th);
		c5th = new PdfPCell(new Phrase("Sub Variety",font));
		c5th.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5th.addCell(c5th);
		c5th = new PdfPCell(new Phrase("Date Of Notification",font));
		c5th.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5th.addCell(c5th);
		c5th = new PdfPCell(new Phrase("Notification Number",font));
		c5th.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5th.addCell(c5th);
		c5th = new PdfPCell(new Phrase("Copy of Notification",font));
		c5th.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5th.addCell(c5th);
		c5th = new PdfPCell(new Phrase("Copy of Proposal",font));
		c5th.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5th.addCell(c5th);
       
		table5th.setHeaderRows(1);
		table5th.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
            Object[] row= (Object[]) firstsetbind.get(j);
            
        	if(j==0)
        	{
   			table5th.addCell(new Phrase((String)Array.get(row,30),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table5th.addCell(new Phrase((String)Array.get(row,31),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			if(Array.get(row, 32)!=null) {
   	        	String ts1 =(String)Array.get(row, 32).toString();
   	        	String[] t= ts1.split(" ");
   	        	table5th.addCell(new Phrase(t[0],  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   	            }else {table5th.addCell("");}
   			table5th.addCell(new Phrase((String)Array.get(row,33),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table5th.addCell(new Phrase((String)Array.get(row,34),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			table5th.addCell(new Phrase((String)Array.get(row,48),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        
        	}
   		 }
        	
        }
        document.add(table5th);
        
        
        //5th Table Ends
       
        
        //6th table
        
        PdfPTable table5 = new PdfPTable(1);
        document.add(new Paragraph("6(a). Classification of the Candidate Variety:",font1));
		document.add(new Paragraph(" "));
		PdfPCell c5 = new PdfPCell(new Phrase("Variety Type",font));
		c5.setHorizontalAlignment(Element.ALIGN_LEFT);
        table5.addCell(c5);
       
        
        table5.setHeaderRows(1);
        table5.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	
        	System.out.println("CONTROL 5557");
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
        if(Array.get(row, 36)!= null)
        {
        String multiple = (String)Array.get(row, 36);
		
		 String[] a = multiple.split(",");
		 int[] intArray = new int[a.length];
		
	      for (int i = 0; i < a.length; i++) {
	         String numberAsString = a[i];
	         intArray[i] = Integer.parseInt(numberAsString);
	        }
	     // System.out.println("CONTROL 1994= "+intArray+"and its size= "+intArray.length);
	     
				if(j==0)
				{	
				      for (int k=0;k<intArray.length;k++)
				      {
				    	  for(int l=0;l<candidatevar.size();l++)
				    	  {
				    	 if(intArray[k]== candidatevar.get(l).getId())
				    	 {
				    		 table5.addCell(new Phrase(candidatevar.get(l).getCandidate_variety(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
				    	 }
				    	  }
				      }
			   		 
			   	}
				
        }		
	     
   		 }
        	
        }	
        
        document.add(table5);
        
        //6th b table
        
        PdfPTable table5_a = new PdfPTable(1);
        document.add(new Paragraph("6(a.i). FOR Others:",font1));
		document.add(new Paragraph(" "));
		
        //table4.setHeaderRows(1);
		table5_a.setWidthPercentage(100);
		
        if(fifthsetbind_newcase !=null && fifthsetbind_newcase.size()>0)
        {
        	//for(int j=0;j<fifthsetbind.size();j++)
        		{
        		Object[] row= (Object[]) fifthsetbind_newcase.get(0);
        		
        		String other_data="";
        	System.out.println("Printing fifth set= "+(String)Array.get(row,1));	
   			 try
   			 {
   				other_data = ((String)Array.get(row,1));
   			 }
   			 catch(NullPointerException ne) {}
   			table5_a.addCell(new Phrase(other_data,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	}	
        }
        document.add(table5_a);
        
        //6th b-for others case ends
        
        //6th b start 
        PdfPTable table5_b = new PdfPTable(1);
        document.add(new Paragraph("6(b.) What is (are) the Distinctiveness Uniformity Stability feature "
        		+ "on the basis of which registration is sought. Explain in detail the group characters "
        		+ "(see specific guidelines for details). Attach 'Technical Questionnaire' sheet with "
        		+ "all needed details duly signed with seal.",font1));
		document.add(new Paragraph(" "));
		
        //table4.setHeaderRows(1);
		table5_b.setWidthPercentage(100);
        
        if(firstsetbind !=null && firstsetbind.size()>0)
        {
        	//for(int j=0;j<firstsetbind.size();j++)
        		{
        		Object[] row= (Object[]) firstsetbind.get(0);
        		
        		String other_data="";
        	System.out.println("Printing fifth 5644 set= "+(String)Array.get(row,37));	
   			 try
   			 {
   				other_data = ((String)Array.get(row,37));
   			 }
   			 catch(NullPointerException ne) {}
   			table5_b.addCell(new Phrase(other_data,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	}	
        }
        document.add(table5_b);
        
        //6th b ends 
        
       //6th c---- 
        PdfPTable table5_c = new PdfPTable(1);
        document.add(new Paragraph("6(c.)  If new variety is a transgenic attach clearance on "
        		+ "Bio-safety from Ministry of Environment and Forests.",font1));
		document.add(new Paragraph(" "));
		
        //table4.setHeaderRows(1);
		table5_c.setWidthPercentage(100);
        
        if(firstsetbind !=null && firstsetbind.size()>0)
        {
        	//for(int j=0;j<firstsetbind.size();j++)
        		{
        		Object[] row= (Object[]) firstsetbind.get(0);
        		
        		String other_data="";
        	System.out.println("Printing 6th 5673 set= "+Array.get(row,55));	
   			 try
   			 {
   				 if(String.valueOf((Array.get(row, 55))).equals("1"))
   				 {
   					other_data ="Yes";
   				 }
   				if(String.valueOf((Array.get(row, 55))).equals("2"))
  				 {
  					other_data ="No";
  				 }
   			 }
   			 catch(NullPointerException ne) {}
   			table5_c.addCell(new Phrase(other_data,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
        	}	
        }
        document.add(table5_c);
        
        //6th c---ends
        
        //6th table ends
        
        //7th table
        
        PdfPTable table6 = new PdfPTable(10);
		
        document.add(new Paragraph("7.Names and Addresses of Breeder(s) who had/have bred the "
        		+ "candidate Variety:",font1));
		document.add(new Paragraph(" "));
		PdfPCell c6 = new PdfPCell(new Phrase("Serial No.",font));
		c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Name",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Address",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Country",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("State",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("District",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Telephone",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Fax",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Email",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        c6 = new PdfPCell(new Phrase("Nationality",font));
        c6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(c6);
        
        table6.setHeaderRows(1);
        table6.setWidthPercentage(100);
        
        if(breadercontact!=null && breadercontact.size()>0)
        {
        	for (int j =0; j< breadercontact.size() ;j++)
   		 {
   			 Object[] row= (Object[]) breadercontact.get(j);
   			
   			 
   			{
   				 //System.out.println("CONTROL ON 3477");
   			 try
   			 {
   				table6.addCell(new Phrase(""+(j+1),new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 10),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 11),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 12),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 8),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table6.addCell(new Phrase((String)Array.get(row, 9),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   	        	
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table6);
		//7th table ends
        
        //8th table----
        PdfPTable table8th = new PdfPTable(10);
		
        document.add(new Paragraph("8. Has the candidate variety been commercialised or otherwise "
        		+ "exploited:",font1));
		document.add(new Paragraph(" "));
		PdfPCell c8th = new PdfPCell(new Phrase("Serial No",font));
		c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
		table8th.addCell(c8th);
		c8th = new PdfPCell(new Phrase("Variety Denomination",font));
		c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Nature of right applied for",font));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Filing Date",font));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Name Of Country",font));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Name of Authority",font));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Application Number",font));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("Status of Application",font));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("In(Country)",font));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        c8th = new PdfPCell(new Phrase("On (Date of Application)",font));
        c8th.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8th.addCell(c8th);
        
        table8th.setHeaderRows(1);
        table8th.setWidthPercentage(100);
        
        if(secondsetbind!=null && secondsetbind.size()>0)
        {
        	for (int j =0; j< secondsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) secondsetbind.get(j);
   			// System.out.println("Printing ==>> "+(String)Array.get(row, 8)+"Printing row as == >> "+row);
   			 //if((String)Array.get(row, 8) =="Agent" || Boolean.toString((Boolean)Array.get(row, 8)).equals("Agent") )
   			if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table8th.addCell(""+(j+1));
   				/*
   				String val1=String.valueOf(Array.get(row, 38));
   				System.out.println("Printing val1= "+val1);
   				 if(val1.equals("true"))
   				{ table8th.addCell("Yes");}
   				else {table8th.addCell("No");}
   				
   				 */
   				//table7.addCell("Yes");
   				 //Timestamp tt1= Timestamp.valueOf((String)Array.get(row, 39));
   				 //Date d= new Date(tt1.getDate());
   				 //System.out.println("CONTROL ON 2171 = "+tt1);
   				String ts1 =(String)Array.get(row, 4).toString();
   				String ts11 =(String)Array.get(row, 10).toString();
   				String[] t=ts1.split(" ");
   				String[] t1=ts11.split(" ");
   				 System.out.println("CONTROL ON 5836 = "+t[0]+" and other ="+t1[0]);
   				//String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(tt1.getDate()));
   				
   				//table8th.addCell("Date:"+t[0]+(String)Array.get(row, 40));
   				table8th.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 3),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase(t[0],  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 8),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase((String)Array.get(row, 9),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table8th.addCell(new Phrase(t1[0],  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table8th);
        //8th ===end
        
        //9th table
        
        PdfPTable table7 = new PdfPTable(6);
		
        document.add(new Paragraph("9. Has the candidate variety been commercialised or otherwise exploited:",font1));
		document.add(new Paragraph(" "));
		PdfPCell c7 = new PdfPCell(new Phrase("Selected Yes/No",font));
		c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Data of the first sale of the variety",font));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Country (ies) where Protection is made",font));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Denomiantion Used",font));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Trademark used, if any",font));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
        c7 = new PdfPCell(new Phrase("Variation is important trait with respect to first filling("
        		+ "Attach sheet)",font));
        c7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(c7);
       
        
        table7.setHeaderRows(1);
        table7.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   			// System.out.println("Printing ==>> "+(String)Array.get(row, 8)+"Printing row as == >> "+row);
   			 //if((String)Array.get(row, 8) =="Agent" || Boolean.toString((Boolean)Array.get(row, 8)).equals("Agent") )
   			if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				//table7.addCell(""+(j+1));
   				String val1=String.valueOf(Array.get(row, 38));
   				System.out.println("Printing val1= "+val1);
   				 if(val1.equals("true"))
   				{ table7.addCell(new Phrase("Yes",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				else {table7.addCell(new Phrase("No",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				
   				//table7.addCell("Yes");
   				 //Timestamp tt1= Timestamp.valueOf((String)Array.get(row, 39));
   				 //Date d= new Date(tt1.getDate());
   				 //System.out.println("CONTROL ON 2171 = "+tt1);
   				 String ts1 =(String)Array.get(row, 39).toString();
   				 String[] t=ts1.split(" ");
   				System.out.println("CONTROL ON 2171 = "+t[0]);
   				//String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(tt1.getDate()));
   				table7.addCell(new Phrase("Date:"+t[0]+"\n"+(String)Array.get(row, 40),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table7.addCell(new Phrase((String)Array.get(row, 42),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table7.addCell(new Phrase((String)Array.get(row, 43),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table7.addCell(new Phrase((String)Array.get(row, 44),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table7.addCell(new Phrase((String)Array.get(row, 50),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table7);
        
        //9th table end
        
        //10th tab
        
        PdfPTable table10th = new PdfPTable(6);
		
        document.add(new Paragraph("10.(a.) If the candidate variety is a hybrid, state whether all "
        		+ "the parental lines required for the repeated propagation of the hybrid are bred "
        		+ "exclusively by the applicant(s):",font1));
		document.add(new Paragraph(" "));
		PdfPCell c10tha = new PdfPCell(new Phrase("Serial",font));
		c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
		table10th.addCell(c10tha);
		c10tha = new PdfPCell(new Phrase("Type of line",font));
		c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10th.addCell(c10tha);
        c10tha = new PdfPCell(new Phrase("Parental Line(s)",font));
        c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10th.addCell(c10tha);
        c10tha = new PdfPCell(new Phrase("Source",font));
        c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10th.addCell(c10tha);
        c10tha = new PdfPCell(new Phrase("Denominations",font));
        c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10th.addCell(c10tha);
        c10tha = new PdfPCell(new Phrase("Authorized Letter Obtained",font));
        c10tha.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10th.addCell(c10tha);
       
        
        table10th.setHeaderRows(1);
        table10th.setWidthPercentage(100);
        
        if(thirdsetbind!=null && thirdsetbind.size()>0)
        {
        	for (int j =0; j< thirdsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) thirdsetbind.get(j);
   			// System.out.println("Printing ==>> "+(String)Array.get(row, 8)+"Printing row as == >> "+row);
   			 //if((String)Array.get(row, 8) =="Agent" || Boolean.toString((Boolean)Array.get(row, 8)).equals("Agent") )
   			//if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				
   				
   				table10th.addCell(new Phrase(""+(j+1), new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL)));
   				table10th.addCell(new Phrase((String)Array.get(row, 6),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10th.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10th.addCell(new Phrase((String)Array.get(row, 4),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10th.addCell(new Phrase((String)Array.get(row, 3),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10th.addCell(new Phrase((String)Array.get(row, 5),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table10th);
        //10th-b case
        
        PdfPTable table10thb = new PdfPTable(5);
		
        document.add(new Paragraph("10(b). State if any Farmers' Variety or Variety of Common "
        		+ "Knowledge or variety in public domain is used as parental line for the repeated "
        		+ "propagation of the hybrid.",font1));
		document.add(new Paragraph(" "));
		PdfPCell c10thb = new PdfPCell(new Phrase("Select YES/NO",font));
		c10thb.setHorizontalAlignment(Element.ALIGN_LEFT);
		table10thb.addCell(c10thb);
		c10thb = new PdfPCell(new Phrase("Denomination",font));
		c10thb.setHorizontalAlignment(Element.ALIGN_LEFT);
		table10thb.addCell(c10thb);
		c10thb = new PdfPCell(new Phrase("Geographical Source",font));
		c10thb.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10thb.addCell(c10thb);
        c10thb = new PdfPCell(new Phrase("Detail of Attribution(origion)",font));
        c10thb.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10thb.addCell(c10thb);
        c10thb = new PdfPCell(new Phrase("Details of owner farmer/village community/Institution/"
        		+ "Organisation",font));
        c10thb.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10thb.addCell(c10thb);
        
        
        table10thb.setHeaderRows(1);
        table10thb.setWidthPercentage(100);
        
        if(fourthsetbind!=null && fourthsetbind.size()>0)
        {
        	for (int j =0; j< fourthsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) fourthsetbind.get(j);
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				
   				String val1=String.valueOf(Array.get(row, 7));
   				//System.out.println("Printing val1= "+val1);
   				if(val1.equals("true"))
   				{ table10thb.addCell(new Phrase("Yes",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				else if(val1.equals("false"))
   				{table10thb.addCell(new Phrase("No",  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));}
   				else { table10thb.addCell(new Phrase(" "));}
   				
   				table10thb.addCell(new Phrase((String)Array.get(row, 1),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10thb.addCell(new Phrase((String)Array.get(row, 2),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10thb.addCell(new Phrase((String)Array.get(row, 3),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10thb.addCell(new Phrase((String)Array.get(row, 4),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table10thb);
        //10th-b ends
        
        
//10th-b case
        
        PdfPTable table10thc = new PdfPTable(4);
		
        document.add(new Paragraph("10(c). The Protection of Plant Varieties and Farmers' Rights "
        		+ "Act, 2001 provides access to benefit sharing to farmers who have conserved the "
        		+ "genetic resource that has contributed towards variety development. In this "
        		+ "particular case what sort of farmer/community recognition the Applicant has "
        		+ "planned:",font1));
		document.add(new Paragraph(" "));
		
        
        table10thc.setHeaderRows(1);
        table10thc.setWidthPercentage(100);

        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   		table10thc.addCell(new Phrase((String)Array.get(row, 51),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   		 }
        }
        
        document.add(table10thc);
        
        
        //10th-c ends
        
        //10th-ends
        
        //11th tab
        
        PdfPTable table11th = new PdfPTable(1);
        document.add(new Paragraph("11. In case exotic germplasm was used in the derivation of the "
        		+ "variety or hybrid, give details:",font1));
		document.add(new Paragraph(" "));
		
        //table4.setHeaderRows(1);
		table11th.setWidthPercentage(100);
        
        if(fourthsetbind !=null && fourthsetbind.size()>0)
        {
        	//for(int j=0;j<firstsetbind.size();j++)
        		{
        		Object[] row= (Object[]) fourthsetbind.get(0);
        		
        	
        	System.out.println("Printing 11th 6100 set= "+Array.get(row,6));	
   			 try
   			 {
   				table11th.addCell(new Phrase((String)(Array.get(row, 6)),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			 }
   			 catch(NullPointerException ne) {}
   			
   			}
        }	
        document.add(table11th); 
        
        //11th ends
        
 //11th tab
        
        PdfPTable table12th = new PdfPTable(1);
        document.add(new Paragraph("12. Expected performance of the variety under the given "
        		+ "conditions as per the Proforma specified by the Authority for respective crop"
        		+ " species in its Journal.",font1));
		document.add(new Paragraph(" "));
		
        //table4.setHeaderRows(1);
		table12th.setWidthPercentage(100);
        
        if(fourthsetbind !=null && fourthsetbind.size()>0)
        {
        	//for(int j=0;j<firstsetbind.size();j++)
        		{
        		Object[] row= (Object[]) fourthsetbind.get(0);
        		
        	
        	System.out.println("Printing 11th 6131 set= "+Array.get(row,8));	
   			 try
   			 {
   				table12th.addCell(new Phrase((String)(Array.get(row, 8)),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			 }
   			 catch(NullPointerException ne) {}
   			
   			}
        }	
        document.add(table12th); 
        
        //12th ends
        
        Paragraph para3= new Paragraph(" ");
        Paragraph para11= new Paragraph(" ");
        Paragraph para= new Paragraph("Declaration",font1);
        Paragraph para1= new Paragraph(" ");
        Paragraph para2= new Paragraph("I/We hereby apply for the grant of"
        + " registration of the candidate variety with the above said "
        + "denomination and I/we am are conversant with the Protection of "
        + "Plant Varieties and Farmers' Rights Act, 2001 and Rules thereof"
        + " related to this application."+"\n"+"I/We hereby declare that "
        + "no person other than the person or persons mentioned in this "
        + "application has been involved in the breeding, or discovery or "
        + "development of the candidate variety."+"\n"+"I/We hereby "
        + "declare that the candidate variety complies with the sub- "
        + "section (3) of section 29 of Protection of Plant Varieties and "
        + "Farmers Rights Act, 2001."+"\n"+"I/We hereby declare that I/we "
        + "have not applied for or received a trademark for the said "
        + "denomination of the variety"+"\n"+"I/We hereby attach an "
        + "affidavit in compliance with clause (C) of sub- section (1) of "
        + "section 18 of Protection of Plant Varieties and Farmers' Rights"
        + " Act, 2001."+"\n"+"I/We hereby declare that the information "
        + "given in this application for the registration of t he above "
        + "said candidate variety, including annexure and all supporting "
        + "documents are complete, true and correct to the best of my/our "
        + "knowledge, information and belief and no information has been "
        + "wilfully concealed."+"\n"+"I/We hereby declare that genetic "
        + "material or parental material acquired for breeding, evolving "
        + "or developing the variety has been lawfully acquired."+"\n"+"I/We hereby declare that I/We shall abide by all the provisions and guidelines of Protection of Plant Varieties and Farmers' Rights Act, 2001",font);
       
        para.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(para3);
        document.add(para11);
        document.add(para);
        document.add(para1);
        document.add(para2);
        
        PdfPTable table8 = new PdfPTable(1);
        PdfPTable table9 = new PdfPTable(1);
        
        document.add(new Paragraph("I Agree",font));
		document.add(new Paragraph(" "));
		PdfPCell c8 = new PdfPCell(new Phrase("Place",font));
		c8.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8.addCell(c8);
        PdfPCell c9 = new PdfPCell(new Phrase("Date",font));
		c9.setHorizontalAlignment(Element.ALIGN_LEFT);
        table9.addCell(c9);
        

        table8.setWidthPercentage(100);
       
        table9.setWidthPercentage(100);
        
        if(firstsetbind!=null && firstsetbind.size()>0)
        {
        	for (int j =0; j< firstsetbind.size() ;j++)
   		 {
   			 Object[] row= (Object[]) firstsetbind.get(j);
   			if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table8.addCell(new Phrase((String)Array.get(row, 47),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				String ts1 =(String)Array.get(row, 46).toString();
  				 String[] t=ts1.split(" ");
   				table9.addCell(new Phrase(t[0],  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				
   			 }catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(table8);
        document.add(table9);
       
        //Attachment case
        
        Paragraph para5= new Paragraph(" ");
        Paragraph para12= new Paragraph(" ");
        Paragraph para13= new Paragraph("Attachments:",font1);
        Paragraph para14= new Paragraph(" ");
        Paragraph para4 = new Paragraph("Following are the attachments (duly signed/seal) "
        		+ "submitted along  with of the application (note that wherever signature "
        		+ "is affixed in the application or attachments, all such "
        		+ "signatures shall be in the original",font);
        
        document.add(para12);
        document.add(para13);
        document.add(para14);
        document.add(para5);
        document.add(para4);
        
        document.add(new Paragraph(" "));
        PdfPTable table10 = new PdfPTable(3);
        PdfPCell c10 = new PdfPCell(new Phrase("Serial No",font));
		c10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(c10);
        c10 = new PdfPCell(new Phrase("Title",font));
		c10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(c10);
        c10 = new PdfPCell(new Phrase("Attachment",font));
		c10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(c10);
        
        table10.setWidthPercentage(100);
        
        if(document_checklistdata!=null && document_checklistdata.size()>0)
        {
        	for (int j =0; j< document_checklistdata.size() ;j++)
   		 {
   			 Object[] row= (Object[]) document_checklistdata.get(j);
   			//if(j==0 )
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				table10.addCell(new Phrase(""+(j+1), new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL)));
   				table10.addCell(new Phrase((String)Array.get(row, 4),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				table10.addCell(new Phrase((String)Array.get(row, 7),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   			 }catch(NullPointerException ne) {}
   			 
   			}
   			 
   		 } 
   			 
        }
        document.add(table10);
        
        Paragraph para10 =new Paragraph(" If felt necessary attach colour pictures of specific  characteristics "
        		+ "used for establishing distinctiveness.Please sign each "
        		+ "page of the application and other document on the left margin",font);
        document.add(para10);
        
    //Attachment ends
        
    //Technical Questionarie start
        
        document.add(new Paragraph("Technical Questionnaire",font1));
		 
        if(get_tech_data !=null && get_tech_data.size()>0)
        {	
        	
        PdfPTable table11 = new PdfPTable(3);
       
        table11.setWidths(new float[] { (float) 0.3, 3,2});
       document.add(new Paragraph(" "));
		PdfPCell ctec = new PdfPCell(new Phrase("1"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
        ctec = new PdfPCell(new Phrase("Name of the Applicant/breeder/company",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getName(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));

		
		ctec = new PdfPCell(new Phrase("2"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Year of Establishment",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getYear_of_establishment(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		ctec = new PdfPCell(new Phrase("3"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("If registered company under Company's Act 1956 (Give details)",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getRegistered_address(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		ctec = new PdfPCell(new Phrase("4"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Location of corporate office and address",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getLocation_office(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		    
		
		
		ctec = new PdfPCell(new Phrase("5.1"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Telephone",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getTelephone(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("5.2"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Fax",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getFax(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		ctec = new PdfPCell(new Phrase("5.3"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Email",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getEmail(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("6"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Name Of Candidate Variety",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCandidatevariety(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
			
		
		ctec = new PdfPCell(new Phrase("a"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Has it been released in any Convention Country earlier",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);	
		table11.addCell(new Phrase(""+get_tech_data.get(0).getReleasedearlier(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("b"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Pedigree/genealogy",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getPedigree(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("b.ii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("File(If Any)",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getPedigree_imagename(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("c"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Breeding of Candidate Variety",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		 table11.addCell("");
			
		ctec = new PdfPCell(new Phrase("(i)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Origination",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(""+get_tech_data.get(0).getOrigination(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("(ii)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Parental material (name of the parental material, "
				+ "characteristics of the parental material,distinguishable from "
				+ "the candidate variety). If the variety was developed by selection, then the "
				+ "number of selection cycles completed before fixing it",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getParental_material(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("(iii)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Breeding technique/procedure used",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell("");
		
		
		ctec = new PdfPCell(new Phrase("(iv)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Selection criteria used",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getSelectioncriteria(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("(v)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Stage of selection and multiplication",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getSelectionstage(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("(vi)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Location where breeding was conducted",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getBreeding_location(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		ctec = new PdfPCell(new Phrase("7"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Particulars of comparative trial conducted by the applicant, "
				+ "if any."+"\n"+"Information on the location, place, period and year/month of "
				+ "comparative trial conducted method of cultivation such as open field, facilities, "
				+ "planting, potting etc., scale of cultivation, reference varieties used, criteria "
				+ "for choice of the reference varieties, design of experiment, method of analysis of "
				+ "variance experimental error where applicable, and other details."+"\n"+"NOTE: "
				+ "Applicant may, furnish data, tables, copy (ies) of publication(s) related to the "
				+ "details of breeding, comparative trial and comparative data in addition to table "
				+ "of characteristics of candidate and reference varieties. This information provided "
				+ "under this item will not be published by the Authority but will be used to "
				+ "facilitate examination of candidate variety.",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_trial_filename(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("i"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Location",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_location(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("ii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Place",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_place(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("iii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Period",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_period(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("iv"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Year and month of comparative trial",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_year()+":"+get_tech_data.get(0).getComparative_month(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("v"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Conducted method of cultivation",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_cultivation(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("vi"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Scale of Cultivation",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_reference(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		if(techques_ref !=null && techques_ref.size()>0)
		{	
		for(int tq=0;tq<techques_ref.size();tq++)
		{
		Object[] row= (Object[]) techques_ref.get(tq);
		//String tecq="";
		ctec = new PdfPCell(new Phrase("vii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Reference Varieties Used",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		//tecq=""+techques_ref.get(tq).getTechnical_questionnaire_reference();
		table11.addCell(new Phrase( (String)Array.get(row, 1),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		}
		}else {
			ctec = new PdfPCell(new Phrase("vii"));
			ctec.setVerticalAlignment(Element.ALIGN_LEFT);
			table11.addCell(ctec);
			ctec = new PdfPCell(new Phrase("Reference Varieties Used",font));
			ctec.setVerticalAlignment(Element.ALIGN_LEFT);
			table11.addCell(ctec);
			//tecq=""+techques_ref.get(tq).getTechnical_questionnaire_reference();
			table11.addCell(" ");
			
		}
		
		ctec = new PdfPCell(new Phrase("viii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Criteria for Choice of the reference varieties",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_criteria(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("ix"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Design of experiment",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_design(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("x"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Method of Analysis of variance experimental error",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getComparative_analysis(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("xi"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Other Details",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		 table11.addCell(new Phrase(get_tech_data.get(0).getComparative_other(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
			
		
		ctec = new PdfPCell(new Phrase("8"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Characteristics of the candidate variety"+"\n"+"Please "
				+ "describe characteristics of the variety in the subheadings: Plant, Stem, Leaf, "
				+ "Inflorescence, Flower and flower parts, Fruit and fruit parts, Seed etc. Describe"
				+ " characters within subheadings generally in the following order: habit, height, "
				+ "length, width, size, shape, colour (RHS colour chart reference with edition). "
				+ "Refer the specific guideline wherever necessary for clarity of description.",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_group(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("a(i)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Give group characters.",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_distinguishing(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("a(ii)"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Distinguishing characteristics (descriptive or elaborate)",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		ctec = new PdfPCell(new Phrase("b"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Table of characteristics between candidate denomination and reference "
				+ "variety"+"\n"+"Please give replicated values for all of its distinguishing and "
				+ "other description for important characteristics along with the corresponding "
				+ "average values of the references varieties."+"\n"+"NOTE: Two or more reference "
				+ "varieties should be compared with the candidate variety in the characteristics "
				+ "table, including one deemed to be the most similar variety and other(s) as "
				+ "obvious/similar as possible. If you provide this information it will "
				+ "facilitate the Authority in their DUS test further in examination of the "
				+ "candidate variety.",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		
		ctec = new PdfPCell(new Phrase("9"));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Characteristics of the reference varieties",font));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		ctec = new PdfPCell(new Phrase("a"));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Most similar variety",font));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		ctec = new PdfPCell(new Phrase("i"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Denomination",font));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_denomination(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("ii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Basis of choice of this variety for comparison",font));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_variety_comparison(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("iii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Distinguishable Characteristics",font));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_variety_distinguishable(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("b"));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Other reference variety",font));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		
		ctec = new PdfPCell(new Phrase("i"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Denomination",font));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_variety_distinguishable(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("ii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Basis of choice of this variety for comparison",font));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_variety_referencedenomination(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("iii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Distinguishable Characteristics",font));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getCharacteristics_basic_distinguishable(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("10"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Statement of distinctiveness of candidate "
				+ "variety"+"\n"+"Please give a distinctiveness statement covering a brief summary "
				+ "of the characteristics that distinguish the candidate variety from all varieties"
				+ " of common knowledge. The Distinctiveness statement should include, (i) names of"
				+ " reference variety (ies) that have been observed most similar to the candidate "
				+ "variety, and (ii) salient comparison for major distinguishing characteristics "
				+ "between the candidate variety and the similar/reference variety (ies).",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getStatement_distinctiveness(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		ctec = new PdfPCell(new Phrase("11"));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Statement on uniformity and stability of candidate"
				+ " variety"+"\n"+"Please give a brief statement describing any variation in the "
				+ "variety that may be regarded as part of its normal uniform or stable expression,"
				+ " which is predictable, capable of being described in clear terms and commercially"
				+ " acceptable. This should include description and frequency of any off-types, "
				+ "variants or mutations. In your opinion what should be the frequency of off "
				+ "types or any other describable variation beyond which the candidate variety "
				+ "shall be deem to be non-uniform. Also please point out which are the traits "
				+ "that may be particularly referred to as indicators to determine an unstable "
				+ "expression of the phenotype of candidate variety.",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		
		ctec = new PdfPCell(new Phrase("i"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Uniformity",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getStatement_uniformity(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("ii"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Stability",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getStatement_stability(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("12"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Methods for maintaining the candidate variety"+"\n"+"Please "
				+ "provide in a brief statement as to how the propagating material will be "
				+ "maintained throughout the duration of the plant breeder�s right, and complete "
				+ "address where the variety will be maintained. This should include status of "
				+ "varieties that are not propagated by seeds including place and method of "
				+ "maintenance and storage of their vegetative material."+"\n"+"NOTE: The holder "
				+ "of a plant breeder's right is responsible for ensuring that propagating material "
				+ "representative of the variety is maintained for the duration of the right.",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getMethods_candidate(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("13"));
		ctec.setHorizontalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Information on variety registered in Convention Countries.",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		
		
		ctec = new PdfPCell(new Phrase("a"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("What were the grouping characters in that application for this candidate variety?",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(" ");
		
		
		
		ctec = new PdfPCell(new Phrase("b"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("What was the Distinctiveness Uniformity and Stability "
				+ "parameter on which it was registered?",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getGrouping_characters(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase("c"));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("What is the variation in important trait with respect to "
				+ "first filing and the present one (Attach photograph)?",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getVariation_important_trait_filename(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		ctec = new PdfPCell(new Phrase(" "));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("Has the Variety been withdrawn in the first filed country"
				+ " from cultivation or banned or from any of the subsequently released country?",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getStability_parameter(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		
		ctec = new PdfPCell(new Phrase(" "));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		ctec = new PdfPCell(new Phrase("If so, the reasons (supplement with information)?",font));
		ctec.setVerticalAlignment(Element.ALIGN_LEFT);
		table11.addCell(ctec);
		table11.addCell(new Phrase(get_tech_data.get(0).getSupplement_information_name(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
		
		
		table11.setWidthPercentage(100);
		table11.setTotalWidth(520);
		
		
		//System.out.println("Control on 4419 tech size= "+get_tech_data.size());
		    
        document.add(table11);
        
        }
//Attachment Ends Here----        
        
//Payment start
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        PdfPTable tablepay = new PdfPTable(9);
	
        document.add(new Paragraph("Payment:",font1));
		document.add(new Paragraph(" "));
		PdfPCell paytab = new PdfPCell(new Phrase("Serial No",font));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("Amount",font));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("Payment Mode",font));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("DD No(If Any)",font));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("DD Date(If Any)",font));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("Payment Status",font));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("Bank Name",font));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("Bank Branch",font));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
		paytab = new PdfPCell(new Phrase("File Name",font));
		paytab.setHorizontalAlignment(Element.ALIGN_LEFT);
		tablepay.addCell(paytab);
        
        
		tablepay.setHeaderRows(1);
		tablepay.setWidthPercentage(100);
        
        if(paymentdetailsmade!=null)
        {
        	for (int j =0; j< paymentdetailsmade.size() ;j++)
   		 {
   			{
   				// System.out.println("CONTROL ON 1883");
   			 try
   			 {
   				Date dd=paymentdetailsmade.get(j).getDddate();
   				//String datechanged=String.vadd.getDate();
   			 Date date = dd;  
             DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
             String strDate = dateFormat.format(date); 
             
   				tablepay.addCell(""+(j+1));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getAmount(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getPaymentmode(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getDdnumber(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(strDate,  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getPaymentstatus(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getBankname(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getBranchname(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				tablepay.addCell(new Phrase(paymentdetailsmade.get(j).getDocumentname(),  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
   				}catch(NullPointerException ne) {}
   			 }
   			 
   		 } 
   			 
        }
        document.add(tablepay);
        
	//Payment end---------------
		
		int hash1=document.hashCode();
		System.out.println("hashcode for application pdf generated:"+hash1);
		document.close();
		writer.close();
		}
   		 
		catch(IOException | DocumentException  es) 
		{es.printStackTrace();}
		
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			act.setLogin_time_stamp(dt);
			act.setActivity("form1 pdf generated");
			act.setUrl("makepdf_f1");
			activitylogservice.save(act);
		   mav.addAttribute("id",id);
	
	 //return "view_applicationdetails";
		   return "redirect:/ppvfra/Application_pdf-"+id+".pdf";
	}

//Adding here ends ---------------------------
	
	
	@RequestMapping(value = "/savedapplication/{applicantid}", method = RequestMethod.GET)
	public String savedApplicationofapplicant(@PathVariable(name = "applicantid") Long applicantid,Model model,HttpServletRequest request) {
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
				//System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
			
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  //List<CropGroup> Crop_Group = addcropgroupservice.listall();
	  List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
	  if(Crop_Group!= null)
		model.addAttribute("Crop_Group", Crop_Group);
		
		List<Crops> allcrops = addcroprepository.findAll();
		if(allcrops !=null)
		model.addAttribute("allcrops",allcrops);
		
		System.out.println("inside applicant id as  ="+userid+ "Pring applicant id = "+applicantid);	
		List<Object[]> all_applications=applicationrepository.find_details_of_application(userid);
		if(all_applications!= null)
			model.addAttribute("all_applications",all_applications);
	
	    StringBuffer url=request.getRequestURL();
	    String val=url.substring(url.lastIndexOf("/")-17,url.lastIndexOf("/"));
	    //String val=url.substring(url.lastIndexOf("/"),url.length());
		
	    System.out.println("Printing the url as = "+url+ " and value = "+url.lastIndexOf("/"));
		System.out.println("PPPPP 7003  line number ==="+val);
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
			     }
		 }
		 
		
			  ActivityLogTable act = new ActivityLogTable();
			  act.setIpaddress(request.getRemoteAddr());
			  act.setActivityby(userdeail.getUsername()); Date dt = new Date();
			  //System.out.println("Current date Is ="+dt); act.setLogin_time_stamp(dt);
			  act.setActivity("view"); act.setUrl(val);
			  
			  activitylogservice.save(act);
			 
		   
		   //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
		   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
				
		
		/*
		 * AES_Encryption aes =new AES_Encryption(); model.addAttribute(aes);
		 */
			
			//Umesh Adding ends here
		 
		return "admin/save_application";
	}
	
	
	@RequestMapping(value = "/submittedapplication/{applicantid}", method = RequestMethod.GET)
	public String submitApplicationofapplicant(@PathVariable(name = "applicantid") Long applicantid, Model model, HttpServletRequest request) {
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
	  
	  List<Object[]> statuswise_application=applicationrepository.application_statuswise(userdeail.getId());
		if(statuswise_application!= null)
		{
		  model.addAttribute("statuswise_application",statuswise_application);
		}
		ApplicationStsSeedrecieved seedinfo = new ApplicationStsSeedrecieved();
		model.addAttribute("seedinfo",seedinfo);
		
		  StringBuffer url=request.getRequestURL();
		  String val=url.substring(url.lastIndexOf("/")-21,url.lastIndexOf("/"));
		    //String val=url.substring(url.lastIndexOf("/"),url.length());
			
		    System.out.println("Printing the url as = "+url+ " and value = "+url.lastIndexOf("/"));
			System.out.println("PPPPP 7083  line number ==="+val);
			
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
			 
			    ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				//System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("view");
				act.setUrl(val);
				
				activitylogservice.save(act);
			   
			   List<Crops> cropdata = addcroprep.findAll();
			   if(cropdata.size()>0)
			   model.addAttribute("cropdata",cropdata);
			   
			   DusTestFee dusfee = new DusTestFee();
			   model.addAttribute("dusfee",dusfee);
			   
			   ApplicationPayments duspayment = new ApplicationPayments();
			   model.addAttribute("duspayment",duspayment);
			   
			    //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			    List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
				
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
				
				//Umesh Adding ends here
				
				List<TypeLine> TypeLine = typelineservice.listall();
				model.addAttribute("TypeLine",TypeLine);
					
		return "admin/submit_application";
	}

	
	
	
	
	@RequestMapping(value = "/totaldetails/{applicantid}", method = RequestMethod.GET)
	public String totalapplicantfilled_info(@PathVariable(name = "applicantid") Long applicantid,
			Model model, HttpServletRequest request)
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
	  
	  List<Object[]> statuswise_application=applicationrepository.total_applications_applicantwise(applicantid.intValue());
		if(statuswise_application!= null)
		{
		  model.addAttribute("statuswise_application",statuswise_application);
		}
		ApplicationStsSeedrecieved seedinfo = new ApplicationStsSeedrecieved();
		model.addAttribute("seedinfo",seedinfo);
		
		  
			
			   
			   List<Crops> cropdata = addcroprep.findAll();
			   if(cropdata.size()>0)
			   model.addAttribute("cropdata",cropdata);
			   
			   DusTestFee dusfee = new DusTestFee();
			   model.addAttribute("dusfee",dusfee);
			   
			   ApplicationPayments duspayment = new ApplicationPayments();
			   model.addAttribute("duspayment",duspayment);
			   
			    //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			    List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
				
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
				
				//Umesh Adding ends here
				
				List<TypeLine> TypeLine = typelineservice.listall();
				model.addAttribute("TypeLine",TypeLine);
					
		return "admin/company_view_applicant_data";
	}


	
}	