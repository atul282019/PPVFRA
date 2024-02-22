package com.ppvfra.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.ApplicantTypes;
import com.ppvfra.entity.ApplicationContacts;
import com.ppvfra.entity.ApplicationContacts3;
import com.ppvfra.entity.ApplicationContacts7;
import com.ppvfra.entity.ApplicationConventionCountries;
import com.ppvfra.entity.ApplicationDocuments;
import com.ppvfra.entity.ApplicationDusFeatures;
import com.ppvfra.entity.ApplicationDusVariety;
import com.ppvfra.entity.ApplicationParentalline;
import com.ppvfra.entity.ApplicationParentallineOthers;
import com.ppvfra.entity.ApplicationPayments;
import com.ppvfra.entity.ApplicationSubmission;
import com.ppvfra.entity.ApplicationTechnicalQuestionnaire;
import com.ppvfra.entity.ApplicationTechnicalQuestionnaireReference;
import com.ppvfra.entity.Applications;
import com.ppvfra.entity.CandidateVariety;
import com.ppvfra.entity.CandidateVarietyDetails;
import com.ppvfra.entity.CompanyRegistration;
import com.ppvfra.entity.Country;
import com.ppvfra.entity.CropSpecies;
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.DusCharacteristics;
import com.ppvfra.entity.DusCharacteristicsStates;
import com.ppvfra.entity.DusTqGroup;
import com.ppvfra.entity.MFormSection;
import com.ppvfra.entity.Nationality;
import com.ppvfra.entity.Payment;
import com.ppvfra.entity.Remarks;
import com.ppvfra.entity.SeqNo_PVP;
import com.ppvfra.entity.States;
import com.ppvfra.entity.TypeLine;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AddCropsRepository;
import com.ppvfra.repository.ApplicationContactsRepository;
import com.ppvfra.repository.ApplicationContactsRepository3;
import com.ppvfra.repository.ApplicationContactsRepository7;
import com.ppvfra.repository.ApplicationConventionCountriesRepository;
import com.ppvfra.repository.ApplicationDocumentsRepository;
import com.ppvfra.repository.ApplicationDusFeaturesRepository;
import com.ppvfra.repository.ApplicationDusVarietyRepository;
import com.ppvfra.repository.ApplicationParentallineRepository;
import com.ppvfra.repository.ApplicationPaymentsRepository;
import com.ppvfra.repository.ApplicationRepository;
import com.ppvfra.repository.ApplicationSubmissionRepository;
import com.ppvfra.repository.ApplicationTechnicalQuestionnaireReferenceRepository;
import com.ppvfra.repository.ApplicationTechnicalQuestionnaireRepository;
import com.ppvfra.repository.ApplicationTypesRepository;
import com.ppvfra.repository.ApplicationsRepository;
import com.ppvfra.repository.CandidateVarietyDetailsRepository;
import com.ppvfra.repository.CandidateVarietyRepository;
import com.ppvfra.repository.DistrictRepository;
import com.ppvfra.repository.DocumentChecklistRepository;
import com.ppvfra.repository.DusCharacteristicsRepository;
import com.ppvfra.repository.DusCharacteristicsStatesRepository;
import com.ppvfra.repository.DusTqGroupRepository;
import com.ppvfra.repository.MFormSectionRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.PaymentRepository;
import com.ppvfra.repository.RemarksRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.CropSpeciesRepository;
import com.ppvfra.repository.SequenceRepositoryPVP;
import com.ppvfra.repository.StateRepository;
import com.ppvfra.repository.TypeLineRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.repository.CountryRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.AddcropService;
import com.ppvfra.service.ApplicationContactsService;
import com.ppvfra.service.ApplicationContactsService3;
import com.ppvfra.service.ApplicationContactsService7;
import com.ppvfra.service.ApplicationConventionCountriesService;
import com.ppvfra.service.ApplicationDocumentsService;
import com.ppvfra.service.ApplicationDusFeaturesService;
import com.ppvfra.service.ApplicationDusVarietyService;
import com.ppvfra.service.ApplicationParentallineOthersService;
import com.ppvfra.service.ApplicationParentallineService;
import com.ppvfra.service.ApplicationPaymentsService;
import com.ppvfra.service.ApplicationSubmissionService;
import com.ppvfra.service.ApplicationTechnicalQuestionnaireReferenceService;
import com.ppvfra.service.ApplicationTechnicalQuestionnaireService;
import com.ppvfra.service.ApplicationsService;
import com.ppvfra.service.CountryService;
import com.ppvfra.service.CropSpeciesService;
import com.ppvfra.service.DistrictService;
import com.ppvfra.service.NationalityService;
import com.ppvfra.service.RemarksService;
import com.ppvfra.service.StateService;
import com.ppvfra.service.TypeLineService;


@Controller
public class ApplicationsController2 {
	
	@Autowired
	UserRepository userrepository;
	@Autowired
	ApplicationContactsService7 spplicationsontactsservice7;
	@Autowired
	ApplicationsService applicationservice;
	@Autowired
	RemarksService remarkservice;
	@Autowired
	AddCropsRepository addcroprepository;
	@Autowired
	CountryRepository countryrep;
	@Autowired
	ModulesRepository modulesrepository;
	@Autowired
	TypeLineRepository typelinerepository;
	@Autowired
	ApplicationConventionCountriesService applicationconventioncountriesservice;
	
	@Autowired
	ApplicationsRepository applicationsrepository;
	@Autowired
	ApplicationPaymentsService applicationpaymentservice;
	@Autowired
	ApplicationContactsRepository7 applicationcontactrepository7;
	@Autowired
	ApplicationContactsRepository3 applicationcontactrepository3;
	@Autowired
	ApplicationTypesRepository applicanttypes;
	
	@Autowired
	StateService stateservice;
	
	@Autowired
	ApplicationConventionCountriesRepository applicationconventioncontry; 

	@Autowired
	CountryService countryservice;
	
	@Autowired
	CropSpeciesRepository cropspeciesrepository;
	
	
	@Autowired		   
	DistrictService districtservice;
	
	@Autowired
	AddcropService addcropservice;
	
	@Autowired
	CropSpeciesService cropspeciesservice;
	
	@Autowired
	Environment env;
	
	@Autowired
	ApplicationContactsService applicationcontactsservice;
	
	@Autowired
	ApplicationContactsService3 applicationcontactsservice3;
	
	@Autowired
	ApplicationContactsService7 applicationcontactsservice7;
	
	@Autowired
	ApplicationContactsRepository applicationcontactrepository;
	
	@Autowired
	ApplicationParentallineService applicationparentallineservice;
	
	@Autowired
	ApplicationParentallineRepository applicationparentallinerepository;
	
	@Autowired
	ApplicationParentallineOthersService applicationparentallineotherservice;
	
	@Autowired
	DocumentChecklistRepository documentchecklistrepository;
	
	@Autowired
	CandidateVarietyRepository candidatevarietyrepository;
	
	@Autowired
	StateRepository staterep; 
	
	@Autowired
	ApplicationRepository applicationrepository;
	
	@Autowired
	CandidateVarietyDetailsRepository candidatevarietydetailsrepository;

	@Autowired
	NationalityService nationalityservice;
	
	@Autowired
	ApplicationPaymentsRepository applicationpaymentsrepository;
	
	@Autowired
	TypeLineService typelineservice;
	
	@Autowired
	PaymentRepository paymentrepository;
	@Autowired
	ApplicationTechnicalQuestionnaireService applicationtechnicalquestionnairservice;
	@Autowired
	ApplicationTechnicalQuestionnaireRepository applicationtqrepository;
	
	@Autowired
	ApplicationDocumentsRepository applicationdocumentrepository;
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	DusCharacteristicsRepository duscharacteristicsrepository;
	
	@Autowired
	DusTqGroupRepository dustqgrouprepository;
	
	@Autowired
	ApplicationDusVarietyService applicationdusvarietyservice;
	
	@Autowired
	ApplicationDusVarietyRepository applicationdusvarietyrepository;
	@Autowired
	ApplicationDusFeaturesRepository applicationdusfeaturerepository;
	
	@Autowired
	ApplicationDusFeaturesService applicationdusfeatureservice;
	
	@Autowired
	ApplicationDocumentsRepository applicationdocumentsrepository;
	
	@Autowired
	ApplicationDocumentsService applicationdocumentservice;
	
	@Autowired
	ApplicationTechnicalQuestionnaireService applicationtechnicalquestionnaireservice;
	
	@Autowired
	ApplicationTechnicalQuestionnaireReferenceService applicationtechnicalquestionnairereferenceservice;
	
	@Autowired
	ApplicationTechnicalQuestionnaireReferenceRepository applicationtechnicalquestionnairereferencerepository;

	@Autowired
	SequenceRepositoryPVP sequencepvprepository;
	
	 @Autowired
	 RemarksRepository remarksrepository;
	 
	 @Autowired
	 DistrictRepository districtrepository;
	 @Autowired
	 RoleAssignRepository roleassignrepository;
	
	 @Autowired
	 MFormSectionRepository master_form_section_repository;
	 
	 @Autowired
	 ApplicationSubmissionRepository application_sub_repository;
	
	 @Autowired
	 DusCharacteristicsStatesRepository duscharstaterepository;
	 
	 @Autowired
	 ApplicationSubmissionService appsubservice;
	 
	@RequestMapping(value="/saveform2", method=RequestMethod.POST)
	public String saveApplicationForm2(HttpServletRequest request, Model model,Applications application,RedirectAttributes redirectAttributes) {
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
					
					List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
					model.addAttribute("userapplicant", userapplicant);
					/// end Getting Logged in user
					Date date = new Date();
					Timestamp ts = new Timestamp(date.getTime());
					
					int applicantid = Integer.parseInt(request.getParameter("applicantid"));
					int id = Integer.parseInt(request.getParameter("id"));
					int applicanttypeid = Integer.parseInt(request.getParameter("applicanttypeid"));
					int editmode =Integer.parseInt(request.getParameter("editmode"));
					if(editmode== 1)
					{
						int success = applicationsrepository.updateApplicationByFormVII(applicantid,applicanttypeid,id);
						if(success !=0) {
							if(request.getParameter("f1_id").equals("0") || request.getParameter("f1_id")==null)
							{
							ApplicationSubmission app_sub =new ApplicationSubmission();
							app_sub.setForm_section_id(17);
							app_sub.setActive(true);
							app_sub.setApplication_id(application.getId());
							app_sub.setCreatedby(Long.parseLong(""+userid));
							app_sub.setCreatedon(new Date(ts.getTime()));
							app_sub.setCreatedbyip(request.getRemoteAddr());
							appsubservice.save(app_sub);
							}
							redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
						}
						else {
							redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
						}
					}
					else {
						application.setApplicantid(Integer.parseInt(request.getParameter("applicantid")));
						application.setCreatedby(userid);
						application.setFormtype("Form2");
						application.setCreatedon(ts);
						application.setCreatedbyip(request.getRemoteAddr());
						application.setApplication_current_status(1);
						Boolean success = applicationservice.save(application);
					  if(success) {
						
						  //SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
							if(request.getParameter("f1_id").equals("0") || request.getParameter("f1_id")==null)
							{
							ApplicationSubmission app_sub =new ApplicationSubmission();
							app_sub.setForm_section_id(17);
							app_sub.setActive(true);
							app_sub.setApplication_id(application.getId());
							app_sub.setCreatedby(Long.parseLong(""+userid));
							app_sub.setCreatedon(new Date(ts.getTime()));
							app_sub.setCreatedbyip(request.getRemoteAddr());
							appsubservice.save(app_sub);
							}
							
					//SAVING ENDS HERE	
							
							redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
						}
						else {
							redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
						}
					}

					//Added On 26-02-2020 For TAB RETURNING	
					return "redirect:/applicationform2/"+application.getId();
				  // return "redirect:/applicationform2/"+application.getId()+"/F_1/1";	
				
		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/applicationform2/{id}", method = RequestMethod.GET)
	public ModelAndView editApplicationsById(@PathVariable int id,HttpServletRequest req) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
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
					//System.out.println("printing user id: "+userid);
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  Integer tqid = null;
			  
			  List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
			  if(checkbox !=null)
			  {
				  model.addObject("checkbox",checkbox);
				 }
			  String varietyval= candidatevarietydetailsrepository.hibridVarietyCount(id);
			  if(varietyval !=null && varietyval.length() >0)
				 {
				  model.addObject("varietyval",varietyval);
				 }
			  else {
				  model.addObject("varietyval",null);
			  }
			  
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
			  tqid = applicationtqrepository.getTqId(id);
			  ApplicationTechnicalQuestionnaire applicatiotq = applicationtechnicalquestionnaireservice.getById(tqid);
			  if(applicatiotq != null)
			  {
				  model.addObject("applicatiotq",applicatiotq);
			  }
			  else {
				  model.addObject("applicatiotq",applicatiotq);
			  }
			 }
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id);
			  if(agentcontact !=null && agentcontact.size() > 0)
			  {
			  model.addObject("agentcontact",agentcontact);
			  }
			  else {
				  model.addObject("agentcontact",1);
			  }
			  
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			  
			  //System.out.println("Trace For Doc Upload begin");
			  
			  //Umesh Commenting below method and adding new case here-----
			  //Done On 16-01-2020
			  //List<Object[]> document_checklistdata=documentchecklistrepository.getform2details();
			  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationidform2(id); 
			  model.addObject("document_checklistdata", document_checklistdata);
			  //System.out.println("Trace For Doc Upload end");
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 1);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<ApplicantTypes> applicanttypelist2 =  applicanttypes.applicanttypeform2();
			  model.addObject("applicanttypelist2", applicanttypelist2);
			  
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
			  //List<TypeLine> typeline = typelineservice.listall();
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
		      //List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  if(applicationcontact !=null && applicationcontact.size() >0) {
			  model.addObject("applicationcontact",applicationcontact);
			  }
			  else
			  {
				  model.addObject("applicationcontact",1);
			  }
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id);
			  model.addObject("applicationnaturalcontact",applicationnaturalcontact);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
				  
			  
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
				  model.addObject("breadercontact",breadercontact);
				  }
				  else {
					  model.addObject("breadercontact",1);
				  }
			  
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
				  model.addObject("conventioncontry",conventioncontry);
				  }
				  else {
					  model.addObject("conventioncontry",1);
				  }
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
		 
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			  
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
		
			  ApplicationContacts contacts = new ApplicationContacts();
			  model.addObject("contact",contacts);
			  
			  ApplicationContacts3 contacts3 = new ApplicationContacts3();
			  model.addObject("contact3",contacts3);
			  
			  ApplicationContacts7 contacts7 = new ApplicationContacts7();
			  model.addObject("contact7",contacts7);
			 
			  ApplicationConventionCountries convention = new ApplicationConventionCountries();
			  model.addObject("convention",convention);
			 
			  ApplicationParentalline parental = new ApplicationParentalline();
			  model.addObject("parental",parental);
			  
			  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			  model.addObject("parentalother",parentalother);
			 
			  ApplicationContacts contactform2 = new ApplicationContacts();
			  model.addObject("contactform2",contactform2);
			  
			  List<Object[]> attachmentlist = applicationdocumentrepository.getattachmentdetailsform2(id);
			  model.addObject("attachmentlist", attachmentlist);
			  
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0	 ) 
			 {
				 
				List<ApplicationPayments> paymentdetailsmade =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id);
				if(paymentdetailsmade!=null)
					model.addObject("paymentdetailsmade",paymentdetailsmade);
				
					
			
			 paymentid = applicationpaymentsrepository.getPaymentId(id); 
			 if(paymentid !=0)
			  {
				 ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				 // List<ApplicationPayments> payment =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id);
				   model.addObject("payment",payment);
				   
				   String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
					  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
						  {
						  model.addObject("app_payed_data",getapp_pay_via_id);
						  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
						  }
					  
				  }  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id);
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				  model.addObject("technicalquestion",question2);
				  
				  List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id);
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						model.addObject("techques_ref",techques_ref); 
					  
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  model.addObject("technicalquestion",question2);
				  
				  model.addObject("techques_ref",null);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
			 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
			 if(varietyandsubvariety !=null && varietyandsubvariety.size() > 0)
			 {
			 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
			 }
			 
			 }
		
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 List<Object[]> fifthsetbind = applicationrepository.details_portion5(id);
		     
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
			 
			 if(firstsetbind != null && firstsetbind.size()>0) {
				 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
					    	  model.addObject("intArray",intArray);
							}
					    }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 if(secondsetbind != null && secondsetbind.size()>0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size()>0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size()>0)
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			 
			 if(application10c !=null && application10c.size()>0)
			 {
				 model.addObject("application10c",application10c.get(0));
				 model.addObject("application10c",application10c);
			 }
			 
			 if(fifthsetbind !=null && fifthsetbind.size() > 0)
				{
				 model.addObject("fifthsetbind",fifthsetbind.get(0));
				 }
			 
		
			  ActivityLogTable act = new ActivityLogTable();
			  act.setIpaddress(req.getRemoteAddr());
			  act.setActivityby(userdeail.getUsername()); Date dt = new Date();
			  System.out.println("Current date Is ="+dt); act.setLogin_time_stamp(dt);
			  act.setActivity("edit"); act.setUrl("/savedapplication");
			  activitylogservice.save(act);
			 
			
				
			long statusofapplication = applicationsrepository.returnapplicationstat(id);
			if(statusofapplication != 0 && statusofapplication<2)
			model.addObject("appstatus",statusofapplication);
			
			//Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addObject("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addObject("rolespresent",rolespresent);
			
			model.addObject("date_verifying",LocalDate.now());
			
			
			List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			 model.addObject("getMFormSectiondata",getMFormSectiondata);
			
			 model.addObject("id_fs1",id);
			 
			 List<Object[]> f1_id= application_sub_repository.get_id_details(id,17);
			 List<Object[]> f2_id= application_sub_repository.get_id_details(id,18);
			 List<Object[]> f3_id= application_sub_repository.get_id_details(id,19);
			 List<Object[]> f4_id= application_sub_repository.get_id_details(id,20);
			 List<Object[]> f5_id= application_sub_repository.get_id_details(id,21);
			 List<Object[]> f6_id= application_sub_repository.get_id_details(id,22);
			 List<Object[]> f7_id= application_sub_repository.get_id_details(id,23);
			 List<Object[]> f8_id= application_sub_repository.get_id_details(id,24);
			 List<Object[]> f9_id= application_sub_repository.get_id_details(id,25);
			 List<Object[]> f10_id= application_sub_repository.get_id_details(id,26);
			 List<Object[]> f11_id= application_sub_repository.get_id_details(id,27);
			 
			 List<Object[]> f13_id= application_sub_repository.get_id_details(id,28);
			 List<Object[]> f14_id= application_sub_repository.get_id_details(id,29);
			 List<Object[]> f15_id= application_sub_repository.get_id_details(id,30);
			 List<Object[]> f16_id= application_sub_repository.get_id_details(id,31);
			 
			 
			 if(f1_id!=null && f1_id.size()>0)
			 {model.addObject("f1_id",f1_id.get(0));}
			 else {model.addObject("f1_id",0);}
			 
			 if(f2_id!=null && f2_id.size()>0)
			 {model.addObject("f2_id",f2_id.get(0));}
			 else {model.addObject("f2_id",0);}
			 
			 if(f3_id!=null && f3_id.size()>0)
			 {model.addObject("f3_id",f3_id.get(0));}
			 else {model.addObject("f3_id",0);}
			 
			 if(f4_id!=null && f4_id.size()>0)
			 {model.addObject("f4_id",f4_id.get(0));}
			 else {model.addObject("f4_id",0);}
			 
			 if(f5_id!=null && f5_id.size()>0)
			 {model.addObject("f5_id",f5_id.get(0));}
			 else {model.addObject("f5_id",0);}
			 
			 if(f6_id!=null && f6_id.size()>0)
			 {model.addObject("f6_id",f6_id.get(0));}
			 else {model.addObject("f6_id",0);}
			 
			 if(f7_id!=null && f7_id.size()>0)
			 {model.addObject("f7_id",f7_id.get(0));}
			 else {model.addObject("f7_id",0);}
			 
			 if(f8_id!=null && f8_id.size()>0)
			 {model.addObject("f8_id",f8_id.get(0));}
			 else {model.addObject("f8_id",0);}
			 
			 if(f9_id!=null && f9_id.size()>0)
			 {model.addObject("f9_id",f9_id.get(0));}
			 else {model.addObject("f9_id",0);}
			 
			 if(f10_id!=null && f10_id.size()>0)
			 {model.addObject("f10_id",f10_id.get(0));}
			 else {model.addObject("f10_id",0);}
			 
			 if(f11_id!=null && f11_id.size()>0)
			 {model.addObject("f11_id",f11_id.get(0));}
			 else {model.addObject("f11_id",0);}
			 
			
			 
			 if(f13_id!=null && f13_id.size()>0)
			 {model.addObject("f13_id",f13_id.get(0));}
			 else {model.addObject("f13_id",0);}
			 
			 if(f14_id!=null && f14_id.size()>0)
			 {model.addObject("f14_id",f14_id.get(0));}
			 else {model.addObject("f14_id",0);}
			 
			 if(f15_id!=null && f15_id.size()>0)
			 {model.addObject("f15_id",f15_id.get(0));}
			 else {model.addObject("f15_id",0);}
			 
			 if(f16_id!=null && f16_id.size()>0)
			 {model.addObject("f16_id",f16_id.get(0));}
			 else {model.addObject("f16_id",0);}
			 
			List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
			if(second_screen.size()>0 && second_screen!=null)
			{
				model.addObject("second_screen",second_screen);
			}
			
			List<Object[]> applicanttype_fetch= applicationrepository.fetchapplicanttypeid(id);
			if(applicanttype_fetch.size()>0 && applicanttype_fetch !=null)
			{
			model.addObject("applicanttype_fetch",applicanttype_fetch.get(0));
			}
			//Umesh Adding ends here 
			  return model;
			
	}
	
	@RequestMapping(value = "/saveapplication2form2", method = RequestMethod.POST)
	public String saveApplications2Form2(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("applicationid"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
				  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		     // List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
		      List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  //List<TypeLine> typeline = typelineservice.listall();
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  int applicationid = Integer.parseInt(request.getParameter("applicationid"));
			  int contactid = Integer.parseInt(request.getParameter("contactid"));
			  
			  if(applicationid != 0)
				{
				  
					    	ApplicationContacts applicationcontacts = new ApplicationContacts();
					    	if(contactid != 0)
							 {
					    	applicationcontacts.setId(contactid);
							 }
					    	applicationcontacts.setApplicationid(applicationid);
					    	applicationcontacts.setSerialno(request.getParameter("serialno"));
					    	applicationcontacts.setAddress(request.getParameter("address"));
					    	applicationcontacts.setCity(request.getParameter("city"));
					    	applicationcontacts.setCountryid(Integer.parseInt(request.getParameter("countryid")));
					    	if(request.getParameter("countryid").equals("1"))
					    	{
					    	applicationcontacts.setStateid(Integer.parseInt(request.getParameter("stateid")));
					    	applicationcontacts.setDistrictid(Integer.parseInt(request.getParameter("districtid")));
					    	applicationcontacts.setPincode(request.getParameter("pincode"));
					    	applicationcontacts.setNationality(Integer.parseInt(request.getParameter("nationality")));
					    	}
					    	applicationcontacts.setName(request.getParameter("name"));
					    	applicationcontacts.setActive(true);
					    	applicationcontacts.setContacttype(1);
					    	applicationcontacts.setCreatedby(userid);
					    	applicationcontacts.setCreatedon(date);
					    	applicationcontacts.setCreatedbyip(request.getRemoteAddr());
					    	Boolean succes = applicationcontactsservice.save(applicationcontacts);
					    	
					    	
					    	//SAVING ENDS HERE	
					    	if(succes) {
					    		
					    		//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
						    	if(request.getParameter("f2_id").equals("0") || request.getParameter("f2_id")==null)
						    	{
						    	ApplicationSubmission app_sub =new ApplicationSubmission();
						    	app_sub.setForm_section_id(18);
						    	app_sub.setActive(true);
						    	app_sub.setApplication_id(applicationid);
						    	app_sub.setCreatedby(Long.parseLong(""+userid));
						    	app_sub.setCreatedon(new Date(ts.getTime()));
						    	app_sub.setCreatedbyip(request.getRemoteAddr());
						    	appsubservice.save(app_sub);
						    	}
						    	
								redirectAttributes.addFlashAttribute("message", "Data Updated Successfully");
							}
							else {
								redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
							}
						 	
		 } 
			  
			  return "redirect:/applicationform2/"+applicationid+"/F_1/2";
			   
	}

	@RequestMapping(value = "/saveapplication2form2typecompany", method = RequestMethod.POST)
	public String saveApplications2Form2typecompany(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("applicationid"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
				  
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  //List<TypeLine> typeline = typelineservice.listall();
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
		      // List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  // List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  //System.out.println("Inside this user id: " +userid);
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  int applicationid = Integer.parseInt(request.getParameter("applicationid"));
			  
			  String radio = request.getParameter("participation_in_capital");
			  String companyname = request.getParameter("companyname");
			  String establishmentaddress = request.getParameter("establishmentaddress");
			  String incorporationyear = request.getParameter("incorporationyear");
			  int nationality = Integer.parseInt(request.getParameter("company_nationality"));
			  int contactid = Integer.parseInt(request.getParameter("contactid"));
			  Boolean radiovalue;
			  
			  if(radio.equals("true")) { radiovalue=true; } else { radiovalue=false; }
		 
			  if(applicationid != 0)
				{
				   int success = applicationsrepository.updateApplicationByCompanyorInstitution(radiovalue,companyname,establishmentaddress,incorporationyear,nationality,applicationid);  
				  if(success !=0)
						 {
					    	ApplicationContacts applicationcontacts = new ApplicationContacts();
					    	if(contactid !=0) {
					    		applicationcontacts.setId(contactid);
					    	}
					    	applicationcontacts.setApplicationid(applicationid);
					    	applicationcontacts.setAddress(request.getParameter("address"));
					    	applicationcontacts.setCity(request.getParameter("city"));
					    	applicationcontacts.setCountryid(Integer.parseInt(request.getParameter("countryid")));
					    	if(request.getParameter("countryid").equals("1"))
					    	{
					        applicationcontacts.setStateid(Integer.parseInt(request.getParameter("stateid")));
					    	applicationcontacts.setDistrictid(Integer.parseInt(request.getParameter("districtid")));
					    	applicationcontacts.setPincode(request.getParameter("pincode"));
					    	}
					    	//applicationcontacts.setNationality(request.getParameter("Nationality"));
					    	applicationcontacts.setName(request.getParameter("name"));
					    	applicationcontacts.setActive(true);
					    	applicationcontacts.setContacttype(2);
					    	applicationcontacts.setCreatedby(userid);
					    	applicationcontacts.setCreatedon(date);
					    	applicationcontacts.setCreatedbyip(request.getRemoteAddr());
					    	Boolean succes = applicationcontactsservice.save(applicationcontacts);
					    	
					    	
					    	if(succes) {

						    	//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
					 if(request.getParameter("f2_id").equals("0") ||
					 request.getParameter("f2_id")==null) 
					 { 
						 ApplicationSubmission app_sub =new ApplicationSubmission(); 
						 app_sub.setForm_section_id(18);
						 app_sub.setActive(true); 
						 app_sub.setApplication_id(applicationid);
						 app_sub.setCreatedby(Long.parseLong(""+userid)); 
						 app_sub.setCreatedon(new Date(ts.getTime())); 
						 app_sub.setCreatedbyip(request.getRemoteAddr());
					     appsubservice.save(app_sub);
					  }
					 
						    	//SAVING ENDS HERE	
						    	
								redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
							}
							else {
								redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
							}
						 }	
				}
				
			  return "redirect:/applicationform2/"+applicationid+"/F_1/2";  
	}

	
	@RequestMapping(value = "/saveapplication2form3", method = RequestMethod.POST)
	public String saveApplications2Form3(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("applicationidfromiii"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
				  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		      // List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  // List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			  
			  // List<TypeLine> typeline = typelineservice.listall();
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  int applicationid = Integer.parseInt(request.getParameter("applicationidfromiii"));
			 
			  String principaladdress = request.getParameter("principalplace");
			  int domicilestatecode = Integer.parseInt(request.getParameter("domicile_statecode"));
		      int districtstatecode = Integer.parseInt(request.getParameter("domicile_districtcode"));
		      int contactidformiii = Integer.parseInt(request.getParameter("contactidformiii"));
				if(applicationid != 0)
				{
					int success = applicationsrepository.updateApplicationByFormIII(principaladdress,domicilestatecode,districtstatecode,applicationid);
					    if(success !=0)
						 {
					    	ApplicationContacts3 applicationcontacts3 = new ApplicationContacts3();
					    	if(contactidformiii !=0) {
					    	applicationcontacts3.setId(contactidformiii);
					    	}
					    	applicationcontacts3.setApplicationid(applicationid);
					    	applicationcontacts3.setName3(request.getParameter("name3"));
					    	applicationcontacts3.setAddress3(request.getParameter("address3"));
					    	applicationcontacts3.setCountryid3(Integer.parseInt(request.getParameter("countryid3")));
					    	if(request.getParameter("countryid3").equals("1"))
					    	{
					    	applicationcontacts3.setStateid3(Integer.parseInt(request.getParameter("stateid3")));
					    	applicationcontacts3.setDistrictid3(Integer.parseInt(request.getParameter("districtid3")));
					    	applicationcontacts3.setPincode3(request.getParameter("pincode3"));
					    	}
					    	applicationcontacts3.setCity3(request.getParameter("city3"));
					    	applicationcontacts3.setTelephone_stdcode3(request.getParameter("telephone_stdcode3"));
					    	applicationcontacts3.setTelephone_number3(request.getParameter("telephone_number3"));
					    	applicationcontacts3.setFax_stdcode3(request.getParameter("fax_stdcode3"));
					    	applicationcontacts3.setFax_number3(request.getParameter("fax_number3"));
					    	applicationcontacts3.setEmailid3(request.getParameter("emailid3"));
					    	applicationcontacts3.setActive(true);
					    	applicationcontacts3.setContacttype3(3);
					    	applicationcontacts3.setCreatedby(userid);
					    	applicationcontacts3.setCreatedon(date);;
					    	applicationcontacts3.setCreatedbyip(request.getRemoteAddr());
					    	Boolean succes = applicationcontactsservice3.save(applicationcontacts3);
					    	 if(succes) {
					    		 
					    		//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
							    	if(request.getParameter("f3_id").equals("0") || request.getParameter("f3_id")== null)
							    	{
							    	ApplicationSubmission app_sub =new ApplicationSubmission();
							    	app_sub.setForm_section_id(19);
							    	app_sub.setActive(true);
							    	app_sub.setApplication_id(applicationid);
							    	app_sub.setCreatedby(Long.parseLong(""+userid));
							    	app_sub.setCreatedon(new Date(ts.getTime()));
							    	app_sub.setCreatedbyip(request.getRemoteAddr());
							    	appsubservice.save(app_sub);
							    	}
							    	//SAVING ENDS HERE
							    	
								redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
							}
							else {
								redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
							}
						 }	
					    else {
							redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
						}
				}
				
				return "redirect:/applicationform2/"+applicationid+"/F_1/3";	
			
	}
	
	@RequestMapping(value = "/saveapplication2form4", method = RequestMethod.POST)
	public String saveApplications2Form4(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		/// Getting Logged in user
				int userid = 0;
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if (principal instanceof UserDetails) {
						String username = ((UserDetails) principal).getUsername();
						User userdeail = userrepository.getUserDetail(username);
						userid = userdeail.getId();
						 } else {
						String username = principal.toString();
						//System.out.println("Priting else Loggin user: " + username);
					}
					/// end Getting Logged in user
			  int id = Integer.parseInt(request.getParameter("applicationidfromiv"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		      //List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
		      //List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
				  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  int applicationid = Integer.parseInt(request.getParameter("applicationidfromiv"));
			  int cropid = Integer.parseInt(request.getParameter("cropid"));
			  int cropspeciesid =  Integer.parseInt(request.getParameter("cropspeciesid"));
			  String cropdetail = request.getParameter("cropdetail");
			  String denomination = request.getParameter("denomination");
			  String cropfamily = request.getParameter("cropfamily");
				if(applicationid != 0)
				{	
				try {
					int success = applicationsrepository.updateApplicationByFormIV(cropid,cropspeciesid,denomination.toUpperCase(),cropdetail,id);
					if(success !=0) {
						
						//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
				    	if(request.getParameter("f4_id").equals("0") || request.getParameter("f4_id")==null)
				    	{
				    	ApplicationSubmission app_sub =new ApplicationSubmission();
				    	app_sub.setForm_section_id(20);
				    	app_sub.setActive(true);
				    	app_sub.setApplication_id(applicationid);
				    	app_sub.setCreatedby(Long.parseLong(""+userid));
				    	app_sub.setCreatedon(new Date(ts.getTime()));
				    	app_sub.setCreatedbyip(request.getRemoteAddr());
				    	appsubservice.save(app_sub);
				    	}
				    	//SAVING ENDS HERE	
						redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				}
			
			return "redirect:/applicationform2/"+applicationid+"/F_1/4";	
			
	}

	@RequestMapping(value = "/saveapplication2form5", method = RequestMethod.POST)
	public String saveApplications2Form5(HttpServletRequest request,@ModelAttribute(value="application") @Valid Applications application,BindingResult bindingResult,RedirectAttributes redirectAttributes,@RequestParam MultipartFile proposalfile) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		/// Getting Logged in user
				int userid = 0;
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if (principal instanceof UserDetails) {
						String username = ((UserDetails) principal).getUsername();
						User userdeail = userrepository.getUserDetail(username);
						userid = userdeail.getId();
						 } else {
						String username = principal.toString();
						//System.out.println("Priting else Loggin user: " + username);
					}
					/// end Getting Logged in user
			   int id = Integer.parseInt(request.getParameter("applicationidfromv"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		     // List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			//  List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
		      //List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
				  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  int applicationid = Integer.parseInt(request.getParameter("applicationidfromv"));
		
			  String radio1val = request.getParameter("varirtytypeid");
			  String notified = request.getParameter("subvarietytypeid");
			  int subvariety = Integer.parseInt(notified);
			  //String notificationno = request.getParameter("notification_number");
			  String genetic_engineering = request.getParameter("genetic_engineering");
				if(applicationid != 0)
				{	
					if(radio1val.equals("1")) {
					//int success =	applicationsrepository.updateApplicationByFormVarity(1,id);
						int success = applicationsrepository.updateApplicationByFormV(1,0,"","","", null,"","",id);
						 if(success !=0) {
							 
							//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
						    	if(request.getParameter("f5_id").equals("0") || request.getParameter("f5_id")==null)
						    	{
						    	ApplicationSubmission app_sub =new ApplicationSubmission();
						    	app_sub.setForm_section_id(21);
						    	app_sub.setActive(true);
						    	app_sub.setApplication_id(applicationid);
						    	app_sub.setCreatedby(Long.parseLong(""+userid));
						    	app_sub.setCreatedon(new Date(ts.getTime()));
						    	app_sub.setCreatedbyip(request.getRemoteAddr());
						    	appsubservice.save(app_sub);
						    	}
						    	//SAVING ENDS HERE
						    	
								redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
							}
							else {
								redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
							}
					}
					else {
							if(notified.equals("3")) {
					
							 String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
					
							 String filepath =null;
							 String filepath2 = null; 
							 String originalName = null; 
							 String originalName2=null;
									
							 
							 
							 if (proposalfile.getSize() >=0) 
									{
										StringBuilder filePath = new StringBuilder(
										UPLOAD_FILEPATH+"FORMV/"); 
										File file = new File(filePath.toString());
										if (!file.exists()) 
										{
										   file.mkdirs();
										}
										
										if(proposalfile.isEmpty()) 
										{
										String filename=request.getParameter("file_urp");
										System.out.println("Printing filename"+filename);
										if(filename!=null && !(filename ==""))
										{
											System.out.println("Printing in else 1412 = "+filename);
										originalName=filename;
										filepath = UPLOAD_FILEPATH+"FORMV/"+originalName;
										}
									}
									else
									{

										
										String filename=	proposalfile.getOriginalFilename().substring(0, proposalfile.getOriginalFilename().lastIndexOf("."));
											String fileext=	proposalfile.getOriginalFilename().substring(proposalfile.getOriginalFilename().lastIndexOf("."), proposalfile.getOriginalFilename().length() );
											String ffname= (filename.replace(" ", "_")).trim();
											originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date())+fileext;
											System.out.println("Printing the original name as 1049="+originalName);
											
											File actFile = new File(filePath.append(File.separator + originalName).toString());
										    try {
												Files.copy(proposalfile.getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										    filepath = UPLOAD_FILEPATH+"FORMV/"+originalName;
									}
										  	    	
									}
								
								int success = applicationsrepository.updateApplication2ByFormV(3,3,originalName,filepath,genetic_engineering,id);	
							 //int success = applicationsrepository.updateApplication2ByFormV(varirtyval,originalName,filepath,genetic_engineering,id);	    
							 if(success !=0) {
								    	
								    	//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
								    	if(Integer.parseInt(request.getParameter("f5_id"))==0 || request.getParameter("f5_id") == null)
								    	{
								    	ApplicationSubmission app_sub =new ApplicationSubmission();
								    	app_sub.setForm_section_id(21);
								    	app_sub.setActive(true);
								    	app_sub.setApplication_id(applicationid);
								    	app_sub.setCreatedby(Long.parseLong(""+userid));
								    	app_sub.setCreatedon(new Date(ts.getTime()));
								    	app_sub.setCreatedbyip(request.getRemoteAddr());
								    	appsubservice.save(app_sub);
								    	}
								    	//SAVING ENDS HERE
								    	
										redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
									}
									else {
										redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
									}
							
						}
						else {
							int success = applicationsrepository.updateApplication2ByFormV(3,subvariety,"","","",id);
							if(success !=0) {
								
								//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
						    	if(request.getParameter("f5_id")==null || request.getParameter("f5_id").equals("0"))
						    	{
						    	ApplicationSubmission app_sub =new ApplicationSubmission();
						    	app_sub.setForm_section_id(21);
						    	app_sub.setActive(true);
						    	app_sub.setApplication_id(applicationid);
						    	app_sub.setCreatedby(Long.parseLong(""+userid));
						    	app_sub.setCreatedon(new Date(ts.getTime()));
						    	app_sub.setCreatedbyip(request.getRemoteAddr());
						    	appsubservice.save(app_sub);
						    	}
						    	//SAVING ENDS HERE
						    	
								redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
							}
							else {
								redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
							}
							
						}
					}
					
			
				}
				
				return "redirect:/applicationform2/"+applicationid+"/F_1/5";	
	}
	
	@RequestMapping(value = "/saveapplication2form6", method = RequestMethod.POST)
	public String saveApplications2Form6(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes,@RequestParam MultipartFile transgenicfile) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("applicationidfromvi"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  int applicationid = Integer.parseInt(request.getParameter("applicationidfromvi"));
			  
			  String chkbox[] = request.getParameterValues("checkbox");
			 
			  String result = ""; 
			  
			  try {
				  applicationdocumentsrepository.updatecheckboxval(id); 
				}
			  catch(Exception apdoc){}
			  
			   for(int z=0 ; z<chkbox.length ; z++)
			  {
				   if (chkbox.length > 0) 
				  { StringBuilder sb = new StringBuilder(); 
				  for (String s : chkbox) 
				  { sb.append(s).append(","); } 
				  result = sb.deleteCharAt(sb.length() - 1).toString(); 
				  } 
			  }
			  String othervalue = request.getParameter("othervalue"); 
			  String feature = request.getParameter("dus_features"); 

			  String initialvariety = request.getParameter("initialvariety");
			  int initialvarietyval = Integer.parseInt(initialvariety);
			  
				if(applicationid != 0)
				{
					int success = applicationsrepository.updateApplication2ByFormVI(result,othervalue,feature,initialvarietyval,id);
					for(int i=0;i<chkbox.length;i++) {
					CandidateVarietyDetails details = new CandidateVarietyDetails();
					details.setApplicationid(Long.parseLong(""+id));
					details.setCandidate_variety_id(Integer.parseInt(chkbox[i]));
					if(Integer.parseInt(chkbox[i]) ==4)
					details.setCandidate_variety_other(othervalue);
					
					details = candidatevarietydetailsrepository.save(details);
					}
					
					if(success !=0) {
				    	if(request.getParameter("f6_id").equals("0") || request.getParameter("f6_id")==null)
				    	{
				    	ApplicationSubmission app_sub =new ApplicationSubmission();
				    	app_sub.setForm_section_id(22);
				    	app_sub.setActive(true);
				    	app_sub.setApplication_id(applicationid);
				    	app_sub.setCreatedby(Long.parseLong(""+userid));
				    	app_sub.setCreatedon(new Date(ts.getTime()));
				    	app_sub.setCreatedbyip(request.getRemoteAddr());
				    	appsubservice.save(app_sub);
				    	}
				    	//SAVING ENDS HERE
				    	
						redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
					
					 String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
						
					 String filepath =null;
					 String originalName = null; 
							if (transgenicfile.getSize() >=0) 
							{
								StringBuilder filePath = new StringBuilder(
								UPLOAD_FILEPATH+"FORMV/"); 
								File file = new File(filePath.toString());
								if (!file.exists()) {
								   file.mkdirs();
								}
								
								if(transgenicfile.isEmpty()) 
								{
								String filename=request.getParameter("file_f1f6");
								if(filename!=null && !(filename ==""))
								{
								originalName=filename;
								filepath = UPLOAD_FILEPATH+"FORMV/"+originalName;
								}
								}
								else
								{
									
								    String filename=	transgenicfile.getOriginalFilename().substring(0, transgenicfile.getOriginalFilename().lastIndexOf("."));
									String fileext=	transgenicfile.getOriginalFilename().substring(transgenicfile.getOriginalFilename().lastIndexOf("."), transgenicfile.getOriginalFilename().length() );
									String ffname= (filename.replace(" ", "_")).trim();
									originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date())+fileext;
									File actFile = new File(filePath.append(File.separator + originalName).toString());
								    try {
										Files.copy(transgenicfile.getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								    filepath = UPLOAD_FILEPATH+"FORMV/"+originalName;
								  	    	
							}
						}	
					//Adding here ends on 26-03-2020		
							if(success !=0) {
							applicationsrepository.updateApplicationforVIform( originalName,filepath,applicationid );
							}
							
				}
				
			 	return "redirect:/applicationform2/"+applicationid+"/F_1/6";	
					
			
	}
	
	@RequestMapping(value = "/saveapplication2form7", method = RequestMethod.POST)
	public String saveApplications2Form7(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("applicationidfromvii"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		      // List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //  List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
		      List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
		      
		      //List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
		      
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  int applicationid = Integer.parseInt(request.getParameter("applicationidfromvii"));
			  int applicantid = Integer.parseInt(request.getParameter("applicantidformvii"));
			  
			  int contactid = Integer.parseInt(request.getParameter("contactid"));
				if(applicationid != 0)
				{
					
					    	ApplicationContacts7 applicationcontacts7 = new ApplicationContacts7();
					    	applicationcontacts7.setId(contactid);
					    	applicationcontacts7.setApplicationid(applicationid);
					    	applicationcontacts7.setName7(request.getParameter("name7"));
					    	applicationcontacts7.setAddress7(request.getParameter("address7"));
					    	applicationcontacts7.setCountryid7(Integer.parseInt(request.getParameter("countryid7")));
					    	if(request.getParameter("countryid7").equals("1"))
					    	{	
					    	applicationcontacts7.setStateid7(Integer.parseInt(request.getParameter("stateid7")));
					    	applicationcontacts7.setDistrictid7(Integer.parseInt(request.getParameter("districtid7")));
					    	applicationcontacts7.setPincode7(request.getParameter("pincode7"));
					    	applicationcontacts7.setNationality7(Integer.parseInt(request.getParameter("nationality7")));
					    	}
					    	
					    	applicationcontacts7.setCity7(request.getParameter("city7"));
					    	//applicationcontacts.setTelephone_stdcode(request.getParameter("telephone_stdcode"));
					    	applicationcontacts7.setTelephone_number7(request.getParameter("telephone_number7"));
					    	applicationcontacts7.setFax_stdcode7(request.getParameter("fax_stdcode7"));
					    	applicationcontacts7.setFax_number7(request.getParameter("fax_number7"));
					    	applicationcontacts7.setEmailid7(request.getParameter("emailid7"));
					    	applicationcontacts7.setActive(true);
					    	applicationcontacts7.setContacttype7(4);
					    	applicationcontacts7.setCreatedon7(date);
					    	applicationcontacts7.setCreatedby7(userid);
					    	applicationcontacts7.setCreatedbyip(request.getRemoteAddr());
					    	Boolean succes = applicationcontactsservice7.save(applicationcontacts7);
					    	if(succes) {
					    		 //SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
						    	if(request.getParameter("f7_id").equals("0") || request.getParameter("f7_id")==null)
						    	{
						    	ApplicationSubmission app_sub =new ApplicationSubmission();
						    	app_sub.setForm_section_id(23);
						    	app_sub.setActive(true);
						    	app_sub.setApplication_id(applicationid);
						    	app_sub.setCreatedby(Long.parseLong(""+userid));
						    	app_sub.setCreatedon(new Date(ts.getTime()));
						    	app_sub.setCreatedbyip(request.getRemoteAddr());
						    	appsubservice.save(app_sub);
						    	}
						    	//SAVING ENDS HERE	
					    		
								redirectAttributes.addFlashAttribute("message", "Data updated Successfully");
							}
							else {
								redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
							}
						 }	
					  
				return "redirect:/applicationform2/"+applicationid+"/F_1/7";	
					
	}
	
	@RequestMapping(value = "/saveapplication2form8", method = RequestMethod.POST)
	public String saveApplications2Form8(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("applicationidfromviii"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		      // List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //  List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  //List<TypeLine> typeline = typelineservice.listall();
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			  int applicationid = Integer.parseInt(request.getParameter("applicationidfromviii"));
			  int conventionid =  Integer.parseInt(request.getParameter("conventionid"));
			  String fillingdate = request.getParameter("fillingdate");
			  String ondate = request.getParameter("ondateofapplication");
			  SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
			  Date date1 = null;
			  Date date2 = null;
				try {
					date1 = formatter1.parse(fillingdate);
					date2 = formatter1.parse(ondate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Boolean conventionapplicable = null;
				if(request.getParameter("is_convention_applicable").equals("true"))
				{
					conventionapplicable =true;
					if(applicationid != 0)
					{
						int app = applicationsrepository.updateApplicationByFormVIII(conventionapplicable,applicationid);
						    if(conventionid !=0 )
							 {
						    	ApplicationConventionCountries applicationconvention = new ApplicationConventionCountries();
						    	applicationconvention.setId(conventionid);
						    	applicationconvention.setApplicationid(applicationid);
						    	applicationconvention.setDenomination(request.getParameter("denomination"));
						    	if(request.getParameter("nature") != null) { 
						    	applicationconvention.setNature(request.getParameter("nature"));
						    	}
						    	applicationconvention.setFillingdate(date1);
						    	applicationconvention.setCountry(Integer.parseInt(request.getParameter("country")));
						    	applicationconvention.setAuthority(request.getParameter("authority"));
						    	applicationconvention.setApplication_number(request.getParameter("application_number"));
						    	//applicationconvention.setApplicationstatus(request.getParameter("applicationstatus"));
						    	applicationconvention.setIncountries(Integer.parseInt(request.getParameter("incountries")));
						    	applicationconvention.setCreatedon(ts);
						    	applicationconvention.setCreatedby(userid);
						    	applicationconvention.setCreatedbyip(request.getRemoteAddr());
						    	applicationconvention.setOndateofapplication(date2);
						    	if(request.getParameter("applicationstatus") != null) {
						    	applicationconvention.setApplicationstatus(request.getParameter("applicationstatus"));
						    	}
						    	applicationconvention.setActive(true);
						    	Boolean success = applicationconventioncountriesservice.save(applicationconvention);
						    	if(success) {
						    		
						    		 //SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
							    	if(request.getParameter("f8_id").equals("0") || request.getParameter("f8_id")==null)
							    	{
							    	ApplicationSubmission app_sub =new ApplicationSubmission();
							    	app_sub.setForm_section_id(24);
							    	app_sub.setActive(true);
							    	app_sub.setApplication_id(applicationid);
							    	app_sub.setCreatedby(Long.parseLong(""+userid));
							    	app_sub.setCreatedon(new Date(ts.getTime()));
							    	app_sub.setCreatedbyip(request.getRemoteAddr());
							    	appsubservice.save(app_sub);
							    	}
							    	//SAVING ENDS HERE
							    	
									redirectAttributes.addFlashAttribute("message", "Data Updated Successfully");
								}
								else {
									redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
								}
							 }	
						    else {

						    	ApplicationConventionCountries applicationconvention = new ApplicationConventionCountries();
						    	applicationconvention.setId(conventionid);
						    	applicationconvention.setApplicationid(applicationid);
						    	applicationconvention.setDenomination(request.getParameter("denomination"));
						    	if(request.getParameter("nature") != null) { 
						    	applicationconvention.setNature(request.getParameter("nature"));
						    	}
						    	applicationconvention.setFillingdate(date1);
						    	applicationconvention.setCountry(Integer.parseInt(request.getParameter("country")));
						    	applicationconvention.setAuthority(request.getParameter("authority"));
						    	applicationconvention.setApplication_number(request.getParameter("application_number"));
						    	//applicationconvention.setApplicationstatus(request.getParameter("applicationstatus"));
						    	applicationconvention.setIncountries(Integer.parseInt(request.getParameter("incountries")));
						    	applicationconvention.setOndateofapplication(date2);
						    	applicationconvention.setCreatedon(ts);
						    	applicationconvention.setCreatedby(userid);
						    	applicationconvention.setCreatedbyip(request.getRemoteAddr());
						    	if(request.getParameter("applicationstatus") != null) {
						    	applicationconvention.setApplicationstatus(request.getParameter("applicationstatus"));
						    	}
						    	applicationconvention.setActive(true);
						    	Boolean success = applicationconventioncountriesservice.save(applicationconvention);
						    	if(success) {
						    		
						    		 //SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
						
						  if(Integer.parseInt(request.getParameter("f8_id"))==0 ||
						  request.getParameter("f8_id") == null) 
						  { 
						  ApplicationSubmission app_sub =new ApplicationSubmission(); 
						  app_sub.setForm_section_id(24);
						  app_sub.setActive(true); 
						  app_sub.setApplication_id(applicationid);
						  app_sub.setCreatedby(Long.parseLong(""+userid)); 
						  app_sub.setCreatedon(new Date(ts.getTime())); 
						  app_sub.setCreatedbyip(request.getRemoteAddr());
						  appsubservice.save(app_sub);
						 }
						 
							    	//SAVING ENDS HERE
							    	
									redirectAttributes.addFlashAttribute("message", "Data Updated Successfully");
								}
								else {
									redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
								}
							 
						    }
					}
				}
				else {
					conventionapplicable = false;
					int app = applicationsrepository.updateApplicationByFormVIII(conventionapplicable,applicationid);
					if(app !=0) {
						applicationconventioncontry.updateApplicationConventionByApplicationid(applicationid);
						 //SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
				    	if(Integer.parseInt(request.getParameter("f8_id"))==0 || request.getParameter("f8_id") == null)
				    	{
				    	ApplicationSubmission app_sub =new ApplicationSubmission();
				    	app_sub.setForm_section_id(24);
				    	app_sub.setActive(true);
				    	app_sub.setApplication_id(applicationid);
				    	app_sub.setCreatedby(Long.parseLong(""+userid));
				    	app_sub.setCreatedon(new Date(ts.getTime()));
				    	app_sub.setCreatedbyip(request.getRemoteAddr());
				    	appsubservice.save(app_sub);
				    	}
				    	//SAVING ENDS HERE
				    	
						redirectAttributes.addFlashAttribute("message", "Data Updated Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
				}
			  
				return "redirect:/applicationform2/"+applicationid+"/F_1/8";	
				}
	
	@RequestMapping(value = "/saveapplication2form9", method = RequestMethod.POST)
	public String saveApplications2Form9(HttpServletRequest request,@ModelAttribute(value="application") @Valid Applications application,BindingResult bindingResult,RedirectAttributes redirectAttributes,@RequestParam MultipartFile firstsaleproof) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("applicationidfromix"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
		      //List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //  List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
		     // List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
		      
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  String iscommercialised = request.getParameter("is_commercialised");
			  Boolean radio;
			  if(iscommercialised.equals("yes")) {
				  radio = true;
			  }
			  else
			  {
				  radio = false;
			  }
			  String firstsaledate = request.getParameter("first_sale_date");
			  String protection = request.getParameter("protection_countries");
			  String denomination = request.getParameter("c_denomination");
			  String trademark = request.getParameter("trademark");
			  int applicationid = Integer.parseInt(request.getParameter("applicationidfromix"));
			  int applicantid = Integer.parseInt(request.getParameter("applicantidformix"));
			  int applicanttypeid = Integer.parseInt(request.getParameter("applicanttypeidformix"));
			  SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
			  Date date1 = null;
			try {
				date1 = formatter1.parse(firstsaledate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 if(iscommercialised.equals("yes")) {
				if(applicationid != 0)
				{
						 String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
						 String filepath =null;
						 String originalName = null; String filepath2 = null; String originalName2=null;
								if (firstsaleproof.getSize() >=0) 
								{
									StringBuilder filePath = new StringBuilder(
									UPLOAD_FILEPATH+"FORMV/"); 
									File file = new File(filePath.toString());
									if (!file.exists()) {
									   file.mkdirs();
									}
									
									if(firstsaleproof.isEmpty()) 
									{
									String filename=request.getParameter("file_f9");
									//System.out.println("Printing filename"+filename);
									if(filename!=null && !(filename ==""))
									{
										//System.out.println("Printing in else 1622 = "+filename);
									originalName=filename;
									filepath = UPLOAD_FILEPATH+"FORMV/"+originalName;
									}
								}
								else
								{
										String filename=	firstsaleproof.getOriginalFilename().substring(0, firstsaleproof.getOriginalFilename().lastIndexOf("."));
										String fileext=	firstsaleproof.getOriginalFilename().substring(firstsaleproof.getOriginalFilename().lastIndexOf("."), firstsaleproof.getOriginalFilename().length() );
										String ffname= (filename.replace(" ", "_")).trim();
										originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date())+fileext;
										
										File actFile = new File(filePath.append(File.separator + originalName).toString());
									    try {
											Files.copy(firstsaleproof.getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									        filepath = UPLOAD_FILEPATH+"FORMV/"+originalName;
									}
								}
			
								int success =applicationsrepository.updateApplication2ByFormIX(radio,originalName,filepath,protection,denomination,trademark,date1,applicantid,applicanttypeid,id);
								
								if(success !=0) {
									
									//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
							    	if(request.getParameter("f9_id").equals("0") || request.getParameter("f9_id")==null)
							    	{
							    	ApplicationSubmission app_sub =new ApplicationSubmission();
							    	app_sub.setForm_section_id(25);
							    	app_sub.setActive(true);
							    	app_sub.setApplication_id(applicationid);
							    	app_sub.setCreatedby(Long.parseLong(""+userid));
							    	app_sub.setCreatedon(new Date(ts.getTime()));
							    	app_sub.setCreatedbyip(request.getRemoteAddr());
							    	appsubservice.save(app_sub);
							    	}
							    	//SAVING ENDS HERE
									redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
								}
								else {
									redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
								}
						
					}
	}
	else {
		int success =applicationsrepository.updateApplication2ByFormIX(radio,"","",protection,denomination,trademark,date1,applicantid,applicanttypeid,id);
		if(success !=0)
		{
			//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
	    	if(request.getParameter("f9_id").equals("0") || request.getParameter("f9_id")==null)
	    	{
	    	ApplicationSubmission app_sub =new ApplicationSubmission();
	    	app_sub.setForm_section_id(25);
	    	app_sub.setActive(true);
	    	app_sub.setApplication_id(applicationid);
	    	app_sub.setCreatedby(Long.parseLong(""+userid));
	    	app_sub.setCreatedon(new Date(ts.getTime()));
	    	app_sub.setCreatedbyip(request.getRemoteAddr());
	    	appsubservice.save(app_sub);
	    	}
	    	//SAVING ENDS HERE
			redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
		}
		else {
			redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
		}
	}
				return "redirect:/applicationform2/"+applicationid+"/F_1/9";	
			
	}
	
	@RequestMapping(value = "/saveapplication2form10", method = RequestMethod.POST)
	public String saveApplications2Form10(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("applicationidfromx"));
			  Applications applications = applicationservice.getById(id);
			  if(applications != null) {
			  model.addObject("applications", applications);
			  }
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		      //List<States> State = stateservice.listall();
		      List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  // List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List<TypeLine> typeline = typelineservice.listall();
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);

			  String linetext = request.getParameter("parental_line");
			  String source = request.getParameter("source");
			  String denominations = request.getParameter("denominations");
			 
			  String commondenomination = request.getParameter("denominationsx");
			  String graphicalsource = request.getParameter("geographical_source");
			  String attribution = request.getParameter("attribution");
			  String owner = request.getParameter("owners");
			  String planning_benefit_sharing = request.getParameter("planning_benefit_sharing");
			  int applicationid = Integer.parseInt(request.getParameter("applicationidfromx"));
			  int parental = Integer.parseInt(request.getParameter("parental")); 
			  
			  String isparentalrequired=null;
			  String letter =null;
			  Boolean is_farmer_variety_required = null;
			  String essentially_derived_variety =null;
			  String  cdenomination= request.getParameter("essentially_derived_variety_denomination");
			  String  csource= request.getParameter("essentially_derived_variety_geographical_source");
			  String  cowner= request.getParameter("essentially_derived_variety_owner");
			  if(request.getParameter("is_parental_required").equals("y")) {  isparentalrequired = "y";  }
			  else 
				{ isparentalrequired = "n";}
			
			 
			  if(request.getParameter("addrecord") != null) {
				  int parentalline = Integer.parseInt(request.getParameter("typeline"));
				  if(request.getParameter("authorised_letter_obtained").equals("attached"))  { letter="attached";  }
				  else {letter="notattached";  }
				  if(applicationid != 0) { 
	 
						 int app = applicationsrepository.updateApplicationByParentalCase(isparentalrequired,applicationid);
						 if(app !=0 ) { 
		  
							 if(parental !=0) {
								 ApplicationParentalline applicationparentalline = new ApplicationParentalline();
								 applicationparentalline.setId(parental);
								 applicationparentalline.setApplicationid(id);
								 applicationparentalline.setParental_line(linetext);
								 applicationparentalline.setTypeline(parentalline);
								 applicationparentalline.setDenominations(denominations);
								 applicationparentalline.setSource(source);
								 applicationparentalline.setAuthorised_letter_obtained(letter);
								 applicationparentalline.setCreatedon(date);
								 applicationparentalline.setCreatedby(userid);
								 applicationparentalline.setCreatedbyip(request.getRemoteAddr()); 
								 Boolean success = applicationparentallineservice.save(applicationparentalline);
								 if(success) { 
									//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
								    	if(request.getParameter("f10_id").equals("0") || request.getParameter("f10_id")==null)
								    	{
								    	ApplicationSubmission app_sub =new ApplicationSubmission();
								    	app_sub.setForm_section_id(26);
								    	app_sub.setActive(true);
								    	app_sub.setApplication_id(applicationid);
								    	app_sub.setCreatedby(Long.parseLong(""+userid));
								    	app_sub.setCreatedon(new Date(ts.getTime()));
								    	app_sub.setCreatedbyip(request.getRemoteAddr());
								    	appsubservice.save(app_sub);
								    	}
								    	//SAVING ENDS HERE
								    	
								    	redirectAttributes.addFlashAttribute("message", "Parental Data Updated Successfully"); } else {redirectAttributes.addFlashAttribute("message","Something went wrong please try again."); }
							 	} 
							 else {
							   ApplicationParentalline applicationparentalline = new ApplicationParentalline();
						       applicationparentalline.setApplicationid(id);
						       applicationparentalline.setParental_line(linetext);
						       applicationparentalline.setTypeline(parentalline);
						       applicationparentalline.setDenominations(denominations);
						       applicationparentalline.setSource(source);
						       applicationparentalline.setAuthorised_letter_obtained(letter);
						       applicationparentalline.setCreatedon(date);
						       applicationparentalline.setCreatedby(userid);
						       applicationparentalline.setCreatedbyip(request.getRemoteAddr());
					  
						       Boolean success = applicationparentallineservice.save(applicationparentalline); 
						       if(success) { 
						    	 //SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
							    	if(Integer.parseInt(request.getParameter("f10_id"))==0 || request.getParameter("f10_id") == null)
							    	{
							    	ApplicationSubmission app_sub =new ApplicationSubmission();
							    	app_sub.setForm_section_id(26);
							    	app_sub.setActive(true);
							    	app_sub.setApplication_id(applicationid);
							    	app_sub.setCreatedby(Long.parseLong(""+userid));
							    	app_sub.setCreatedon(new Date(ts.getTime()));
							    	app_sub.setCreatedbyip(request.getRemoteAddr());
							    	appsubservice.save(app_sub);
							    	}
							    	//SAVING ENDS HERE
							    	
							    	redirectAttributes.addFlashAttribute("message", "Parental Data Saved Successfully"); }
						       else { redirectAttributes.addFlashAttribute("message","Something went wrong please try again."); }
					  
					  		}
					  }
					  
				}
				  
			  }
			  else if(request.getParameter("is_farmer_variety_required") !=null){
				  if(request.getParameter("is_farmer_variety_required").equals("true")) {
					  
					  is_farmer_variety_required = true;  
					  }
				  else
				  {  
					  is_farmer_variety_required = false;
				  }
				  if(request.getParameter("essentially_derived_variety").equals("y")) {
					  
					  essentially_derived_variety = "y";  
					  }
				  else
				  {  
					  essentially_derived_variety = "n";
				  }
				  if(applicationid != 0) { 
					  if(essentially_derived_variety=="y") {
						  int s = applicationparentallinerepository.updatedapplicationparentaline(applicationid);
						 int app = applicationsrepository.updateApplication2ByFormX(commondenomination, graphicalsource,attribution,owner,planning_benefit_sharing,is_farmer_variety_required,essentially_derived_variety,cdenomination,csource,cowner,isparentalrequired,applicationid); 
						 if(app !=0) { 
								//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
							    	if(Integer.parseInt(request.getParameter("f10_id"))==0 || request.getParameter("f10_id") == null)
							    	{
							    	ApplicationSubmission app_sub =new ApplicationSubmission();
							    	app_sub.setForm_section_id(26);
							    	app_sub.setActive(true);
							    	app_sub.setApplication_id(applicationid);
							    	app_sub.setCreatedby(Long.parseLong(""+userid));
							    	app_sub.setCreatedon(new Date(ts.getTime()));
							    	app_sub.setCreatedbyip(request.getRemoteAddr());
							    	appsubservice.save(app_sub);
							    	}
							    	//SAVING ENDS HERE
							    	
							    	redirectAttributes.addFlashAttribute("message", "Data Saved Successfully"); }
						       else { redirectAttributes.addFlashAttribute("message","Something went wrong please try again."); }
					  
					}
					  else if(essentially_derived_variety=="n") {
						  int s = applicationparentallinerepository.updatedapplicationparentaline(applicationid);
							 int app = applicationsrepository.updateApplication2ByFormX(commondenomination, graphicalsource,attribution,owner,planning_benefit_sharing,is_farmer_variety_required,essentially_derived_variety,"","","",isparentalrequired,applicationid); 
							 if(app !=0) { 
									//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
								    	if(Integer.parseInt(request.getParameter("f10_id"))==0 || request.getParameter("f10_id") == null)
								    	{
								    	ApplicationSubmission app_sub =new ApplicationSubmission();
								    	app_sub.setForm_section_id(26);
								    	app_sub.setActive(true);
								    	app_sub.setApplication_id(applicationid);
								    	app_sub.setCreatedby(Long.parseLong(""+userid));
								    	app_sub.setCreatedon(new Date(ts.getTime()));
								    	app_sub.setCreatedbyip(request.getRemoteAddr());
								    	appsubservice.save(app_sub);
								    	}
								    	//SAVING ENDS HERE
								    	
								    	redirectAttributes.addFlashAttribute("message", "Data Saved Successfully"); }
							       else { redirectAttributes.addFlashAttribute("message","Something went wrong please try again."); }
						  
						}
					  else if(is_farmer_variety_required==true && essentially_derived_variety=="y") {
						  int s = applicationparentallinerepository.updatedapplicationparentaline(applicationid);
						  int app = applicationsrepository.updateApplication2ByFormX(commondenomination, graphicalsource,attribution,owner,planning_benefit_sharing,is_farmer_variety_required,essentially_derived_variety,cdenomination,csource,cowner,isparentalrequired,applicationid); 
							 if(app !=0) { 
									//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
								    	if(Integer.parseInt(request.getParameter("f10_id"))==0 || request.getParameter("f10_id") == null)
								    	{
								    	ApplicationSubmission app_sub =new ApplicationSubmission();
								    	app_sub.setForm_section_id(26);
								    	app_sub.setActive(true);
								    	app_sub.setApplication_id(applicationid);
								    	app_sub.setCreatedby(Long.parseLong(""+userid));
								    	app_sub.setCreatedon(new Date(ts.getTime()));
								    	app_sub.setCreatedbyip(request.getRemoteAddr());
								    	appsubservice.save(app_sub);
								    	}
								    	//SAVING ENDS HERE
								    	
								    	redirectAttributes.addFlashAttribute("message", "Data Saved Successfully"); }
							       else { redirectAttributes.addFlashAttribute("message","Something went wrong please try again."); }
						  
						}
					  else if(is_farmer_variety_required==false && essentially_derived_variety=="n") {
						  int s = applicationparentallinerepository.updatedapplicationparentaline(applicationid);
							 int app = applicationsrepository.updateApplication2ByFormX("", "","","","",is_farmer_variety_required,essentially_derived_variety,"","","",isparentalrequired,applicationid); 
							 if(app !=0) { 
									//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
								    	if(Integer.parseInt(request.getParameter("f10_id"))==0 || request.getParameter("f10_id") == null)
								    	{
								    	ApplicationSubmission app_sub =new ApplicationSubmission();
								    	app_sub.setForm_section_id(26);
								    	app_sub.setActive(true);
								    	app_sub.setApplication_id(applicationid);
								    	app_sub.setCreatedby(Long.parseLong(""+userid));
								    	app_sub.setCreatedon(new Date(ts.getTime()));
								    	app_sub.setCreatedbyip(request.getRemoteAddr());
								    	appsubservice.save(app_sub);
								    	}
								    	//SAVING ENDS HERE
								    	
								    	redirectAttributes.addFlashAttribute("message", "Data Saved Successfully"); }
							       else { redirectAttributes.addFlashAttribute("message","Something went wrong please try again."); }
						  
						}
					  }
				  
				  
				  
			  }
			  else if(isparentalrequired == "y") {
				  System.out.println("check a, b and c");
				  if(request.getParameter("is_farmer_variety_required") !=null){
				  if(request.getParameter("is_farmer_variety_required").equals("true")) {
					  
					  is_farmer_variety_required = true;  
					  }
				  else
				  {  
					  is_farmer_variety_required = false;
				  }
				  if(request.getParameter("essentially_derived_variety").equals("y")) {
					  
					  essentially_derived_variety = "y";  
					  }
				  else
				  {  
					  essentially_derived_variety = "n";
				  }
				  if(applicationid != 0) { 
					  if(essentially_derived_variety=="y") {
						  int s = applicationparentallinerepository.updatedapplicationparentaline(applicationid);
						 int app = applicationsrepository.updateApplication2ByFormX(commondenomination, graphicalsource,attribution,owner,planning_benefit_sharing,is_farmer_variety_required,essentially_derived_variety,cdenomination,csource,cowner,isparentalrequired,applicationid); 
						 if(app !=0) { 
								//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
							    	if(Integer.parseInt(request.getParameter("f10_id"))==0 || request.getParameter("f10_id") == null)
							    	{
							    	ApplicationSubmission app_sub =new ApplicationSubmission();
							    	app_sub.setForm_section_id(26);
							    	app_sub.setActive(true);
							    	app_sub.setApplication_id(applicationid);
							    	app_sub.setCreatedby(Long.parseLong(""+userid));
							    	app_sub.setCreatedon(new Date(ts.getTime()));
							    	app_sub.setCreatedbyip(request.getRemoteAddr());
							    	appsubservice.save(app_sub);
							    	}
							    	//SAVING ENDS HERE
							    	
							    	redirectAttributes.addFlashAttribute("message", "Data Saved Successfully"); }
						       else { redirectAttributes.addFlashAttribute("message","Something went wrong please try again."); }
					  
					}
					  else if(essentially_derived_variety=="n") {
						  int s = applicationparentallinerepository.updatedapplicationparentaline(applicationid);
							 int app = applicationsrepository.updateApplication2ByFormX(commondenomination, graphicalsource,attribution,owner,planning_benefit_sharing,is_farmer_variety_required,essentially_derived_variety,"","","",isparentalrequired,applicationid); 
							 if(app !=0) { 
									//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
								    	if(Integer.parseInt(request.getParameter("f10_id"))==0 || request.getParameter("f10_id") == null)
								    	{
								    	ApplicationSubmission app_sub =new ApplicationSubmission();
								    	app_sub.setForm_section_id(26);
								    	app_sub.setActive(true);
								    	app_sub.setApplication_id(applicationid);
								    	app_sub.setCreatedby(Long.parseLong(""+userid));
								    	app_sub.setCreatedon(new Date(ts.getTime()));
								    	app_sub.setCreatedbyip(request.getRemoteAddr());
								    	appsubservice.save(app_sub);
								    	}
								    	//SAVING ENDS HERE
								    	
								    	redirectAttributes.addFlashAttribute("message", "Data Saved Successfully"); }
							       else { redirectAttributes.addFlashAttribute("message","Something went wrong please try again."); }
						  
						}
					  else if(is_farmer_variety_required==true && essentially_derived_variety=="y") {
						  int s = applicationparentallinerepository.updatedapplicationparentaline(applicationid);
						  int app = applicationsrepository.updateApplication2ByFormX(commondenomination, graphicalsource,attribution,owner,planning_benefit_sharing,is_farmer_variety_required,essentially_derived_variety,cdenomination,csource,cowner,isparentalrequired,applicationid); 
							 if(app !=0) { 
									//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
								    	if(Integer.parseInt(request.getParameter("f10_id"))==0 || request.getParameter("f10_id") == null)
								    	{
								    	ApplicationSubmission app_sub =new ApplicationSubmission();
								    	app_sub.setForm_section_id(26);
								    	app_sub.setActive(true);
								    	app_sub.setApplication_id(applicationid);
								    	app_sub.setCreatedby(Long.parseLong(""+userid));
								    	app_sub.setCreatedon(new Date(ts.getTime()));
								    	app_sub.setCreatedbyip(request.getRemoteAddr());
								    	appsubservice.save(app_sub);
								    	}
								    	//SAVING ENDS HERE
								    	
								    	redirectAttributes.addFlashAttribute("message", "Data Saved Successfully"); }
							       else { redirectAttributes.addFlashAttribute("message","Something went wrong please try again."); }
						  
						}
					  else if(is_farmer_variety_required==false && essentially_derived_variety=="n") {
						  int s = applicationparentallinerepository.updatedapplicationparentaline(applicationid);
							 int app = applicationsrepository.updateApplication2ByFormX("", "","","","",is_farmer_variety_required,essentially_derived_variety,"","","",isparentalrequired,applicationid); 
							 if(app !=0) { 
									//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
								    	if(Integer.parseInt(request.getParameter("f10_id"))==0 || request.getParameter("f10_id") == null)
								    	{
								    	ApplicationSubmission app_sub =new ApplicationSubmission();
								    	app_sub.setForm_section_id(26);
								    	app_sub.setActive(true);
								    	app_sub.setApplication_id(applicationid);
								    	app_sub.setCreatedby(Long.parseLong(""+userid));
								    	app_sub.setCreatedon(new Date(ts.getTime()));
								    	app_sub.setCreatedbyip(request.getRemoteAddr());
								    	appsubservice.save(app_sub);
								    	}
								    	//SAVING ENDS HERE
								    	
								    	redirectAttributes.addFlashAttribute("message", "Data Saved Successfully"); }
							       else { redirectAttributes.addFlashAttribute("message","Something went wrong please try again."); }
						  
						}
					  }
				  
				  
				  
			  }
			  }
			  
			  
			  else {
			  if(applicationid != 0) { 
					 int app = applicationsrepository.updateApplicationByFormXBlockC(planning_benefit_sharing,applicationid); 
					 if(app !=0) { 
						//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
					    	if(Integer.parseInt(request.getParameter("f10_id"))==0 || request.getParameter("f10_id") == null)
					    	{
					    	ApplicationSubmission app_sub =new ApplicationSubmission();
					    	app_sub.setForm_section_id(26);
					    	app_sub.setActive(true);
					    	app_sub.setApplication_id(applicationid);
					    	app_sub.setCreatedby(Long.parseLong(""+userid));
					    	app_sub.setCreatedon(new Date(ts.getTime()));
					    	app_sub.setCreatedbyip(request.getRemoteAddr());
					    	appsubservice.save(app_sub);
					    	}
					    	//SAVING ENDS HERE
					    	
					    	redirectAttributes.addFlashAttribute("message", "Data Saved Successfully"); }
				       else { redirectAttributes.addFlashAttribute("message","Something went wrong please try again."); }
			  
			  	}
			  }
			
			  return "redirect:/applicationform2/"+applicationid+"/F_1/10";	
	}
	
	
	@RequestMapping(value = "/saveapplication2form11", method = RequestMethod.POST)
	public String saveApplications2Form11(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("applicationidfromxi"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		      //   List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			 // List<TypeLine> typeline = typelineservice.listall();
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			 // List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			 // List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);

			  int applicationid = Integer.parseInt(request.getParameter("applicationidfromxi"));
			  int applicantid = Integer.parseInt(request.getParameter("applicantidformxi"));
			  int applicanttypeid = Integer.parseInt(request.getParameter("applicanttypeidformxi"));
			  String exoticgermplasm = request.getParameter("exotic_germplasm");
			  if(applicationid != 0)
				{
				  int success = applicationsrepository.updateApplicationByFormXI(exoticgermplasm,applicantid,applicanttypeid,id);
				  if(success != 0) {
					//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
				    	if(request.getParameter("f11_id").equals("0") || request.getParameter("f11_id")==null)
				    	{
				    	ApplicationSubmission app_sub =new ApplicationSubmission();
				    	app_sub.setForm_section_id(27);
				    	app_sub.setActive(true);
				    	app_sub.setApplication_id(applicationid);
				    	app_sub.setCreatedby(Long.parseLong(""+userid));
				    	app_sub.setCreatedon(new Date(ts.getTime()));
				    	app_sub.setCreatedbyip(request.getRemoteAddr());
				    	appsubservice.save(app_sub);
				    	}
				    	//SAVING ENDS HERE
				    	
					   redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
				}
			
			  return "redirect:/applicationform2/"+applicationid+"/F_1/11";	
			
	}
	
	@RequestMapping(value = "/saveapplication2form12", method = RequestMethod.POST)
	public String saveApplications2Form12(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("applicationidfromxii"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		      //List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			 // List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
		      //List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  int applicationid = Integer.parseInt(request.getParameter("applicationidfromxii"));
			  int applicantid = Integer.parseInt(request.getParameter("applicantidformxii"));
			  int applicanttypeid = Integer.parseInt(request.getParameter("applicanttypeidformxii"));
			  String expectedperformance = request.getParameter("expected_performance");
				if(applicationid != 0)
				{
					int success = applicationsrepository.updateApplicationByFormXII(expectedperformance,applicantid,applicanttypeid,id);
					if(success != 0) {
						
						//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
				    	if(request.getParameter("f12_id").equals("0") || request.getParameter("f12_id")==null)
				    	{
				    	ApplicationSubmission app_sub =new ApplicationSubmission();
				    	app_sub.setForm_section_id(12);
				    	app_sub.setActive(true);
				    	app_sub.setApplication_id(applicationid);
				    	app_sub.setCreatedby(Long.parseLong(""+userid));
				    	app_sub.setCreatedon(new Date(ts.getTime()));
				    	app_sub.setCreatedbyip(request.getRemoteAddr());
				    	appsubservice.save(app_sub);
				    	}
				    	//SAVING ENDS HERE
				    	
						   redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
						}
						else {
							redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
						}
				}
				//Added On 26-02-2020 For TAB RETURNING	
				return "redirect:/applicationform2/"+applicationid;
			   //return "redirect:/applicationform2/"+applicationid+"/F_1/12";	
	           }
	
	
	@RequestMapping(value = "/savedeclaration_first_form2", method = RequestMethod.POST)
	public String saveApplications2Form2_Declare2(HttpServletRequest request,@ModelAttribute(value="application") @Valid Applications application,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			  int id =  Integer.parseInt(request.getParameter("applicationid"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		      //List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //  List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  int applicationid = Integer.parseInt(request.getParameter("applicationid"));
			  int applicantid = Integer.parseInt(request.getParameter("applicantid"));
			 
			  String is_agree = request.getParameter("is_agreed_clause");
			  String place= request.getParameter("application_declare_place");
			  String dategiven = request.getParameter("application_signed_date");
			  
			  SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
			  Date dd = null;
			  try {
				  dd= sdft.parse(dategiven);
			  }catch(Exception e1) {System.out.println("IN EX");}
			  
			  String agree="";
			  if(is_agree.equals("Y"))
			  {
				agree="Y";  
			  }else
			  {
				  agree="N";
			  }
			  if(is_agree == null || place == null || dategiven == null || is_agree.equals("null") || place.equals("null") || dategiven.equals("null"))
			  return null;
				if(applicationid != 0)
				{
			
					int app = applicationsrepository.updateDeclaration_Form1(applicationid,is_agree,place,dd);
					    if(app !=0 )
						 {
					    	//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
					    	if(request.getParameter("f13_id").equals("0") || request.getParameter("f13_id")==null)
					    	{
					    	ApplicationSubmission app_sub =new ApplicationSubmission();
					    	app_sub.setForm_section_id(28);
					    	app_sub.setActive(true);
					    	app_sub.setApplication_id(applicationid);
					    	app_sub.setCreatedby(Long.parseLong(""+userid));
					    	app_sub.setCreatedon(new Date(ts.getTime()));
					    	app_sub.setCreatedbyip(request.getRemoteAddr());
					    	appsubservice.save(app_sub);
					    	}
					    	//SAVING ENDS HERE
					    	
								redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
							}
							else {
								redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
							}
				}	
				
				return "redirect:/applicationform2/"+applicationid+"/F_1/Declaration";
	}
	
	@RequestMapping(value = "/updaterow_form2_subform/{id}/{applicationid}", method = RequestMethod.GET)
	public String updateApplicationsForm2_subform(@PathVariable(name = "id") Integer id,@PathVariable(name = "applicationid") Integer applicationid,HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) 
	{
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			 int main_application_id = applicationid;
			  Applications applications = applicationservice.getById(main_application_id);
			  model.addObject("applications", applications);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(main_application_id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
		
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		       //   List<States> State = stateservice.listall();
			   List<States> State=staterep.getstates();
			   model.addObject("State", State);
			  
			  //  List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			  //System.out.println("Inside this user id: " +userid);
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  if( id != 0)
				{
					int app =  applicationcontactrepository.updatedataform1sub2(id);
					    
				}	
			    //Added Here For Name And Role Showing in header
				
				long statusofapplication = applicationsrepository.returnapplicationstat(id);
				if(statusofapplication != 0 && statusofapplication<2)
				model.addObject("appstatus",statusofapplication);
				
				List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addObject("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addObject("rolespresent",rolespresent);
			    //Umesh Adding ends here  
				return "redirect:/applicationform2/"+main_application_id;
					
	}
	
	@RequestMapping(value = "/editnaturalcontactform2/{id}/{contactid}/{form}/{formid}", method = RequestMethod.GET)
	public ModelAndView editApplication2NaturalAgentContactId(
			@PathVariable(name = "id") Integer id,
			@PathVariable(name = "contactid") Integer 
			contactid,HttpServletRequest request,
			Applications application,
			RedirectAttributes redirectAttributes,
			@PathVariable(name = "form") String form,
			@PathVariable(name = "formid") String formid) 
	{
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
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
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
			  if(checkbox !=null)
				 {
				 model.addObject("checkbox",checkbox);
				 }
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  ApplicationContacts3 contacts3 = new ApplicationContacts3();
			  model.addObject("contact3",contacts3);
			 
			  List<ApplicantTypes> applicanttypelist2 =  applicanttypes.applicanttypeform2();
			  model.addObject("applicanttypelist2", applicanttypelist2);
			  
			  ApplicationContacts7 contacts7 = new ApplicationContacts7();
			  model.addObject("contact7",contacts7);
			  
			  List<Object[]> document_checklistdata1=documentchecklistrepository.getform2details();
				 
			  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationidform2(id);
			  if(document_checklistdata.size()>0 && document_checklistdata!=null)
			  {model.addObject("document_checklistdata", document_checklistdata);
			  model.addObject("docattach",1);
			  }
			  else
			  {model.addObject("document_checklistdata", document_checklistdata1);
			  model.addObject("docattach",0);
			  }
			 
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id);
			  model.addObject("applicationnaturalcontact",applicationnaturalcontact);
			 	 
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 1);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
		      //List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //  List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
		      //List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  model.addObject("applicationcontact",applicationcontact);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
				  
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
			  model.addObject("breadercontact",breadercontact);
			  }
			  else {
				  model.addObject("breadercontact",1);
			  }
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
				  model.addObject("conventioncontry",conventioncontry);
				  }
				  else {
					  model.addObject("conventioncontry",1);
				  }
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
		 
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
			 
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
			 
			 if(firstsetbind != null) {
				 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
					    	  model.addObject("intArray",intArray);
							}
					 }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0) {
				 
				 paymentid = applicationpaymentsrepository.getPaymentId(id);
				  if(paymentid !=0) {
					ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				    model.addObject("payment",payment);
				    
				    String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
					  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
						  {
						  model.addObject("app_payed_data",getapp_pay_via_id);
						  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
						  }
				  
				  }
				  
				  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			 
			 Integer tqid = null;
			 
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id);
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				  model.addObject("technicalquestion",question2);
				  
				  List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id);
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						model.addObject("techques_ref",techques_ref); 
					  
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  model.addObject("technicalquestion",question2);
				  model.addObject("techques_ref",null);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
				 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
				 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
				 }
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 if(secondsetbind != null && secondsetbind.size() >0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size()>0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size()>0)
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			 
			 if(application10c !=null && application10c.size()>0)
			 {
				 model.addObject("application10c",application10c.get(0));
				 model.addObject("application10c",application10c);
			 }
			 List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			ApplicationContacts contact =  applicationcontactsservice.getById(contactid);
			if(contact != null) {
			 model.addObject("contact",contact);
			}
			ApplicationConventionCountries convention = new ApplicationConventionCountries();
			model.addObject("convention",convention);
			
			ApplicationParentalline parental = new ApplicationParentalline();
			model.addObject("parental",parental);
			
			ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			model.addObject("parentalother",parentalother);
			
			//Added Here For Name And Role Showing in header
			
			long statusofapplication = applicationsrepository.returnapplicationstat(id);
			if(statusofapplication != 0 && statusofapplication<2)
			model.addObject("appstatus",statusofapplication);
			
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addObject("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addObject("rolespresent",rolespresent);
			//Umesh Adding ends here 
			
			List<Object[]> f2_id= application_sub_repository.get_id_details(id,18);
			if(f2_id!=null && f2_id.size()>0)
		   {model.addObject("f2_id",f2_id.get(0));}
		   else {model.addObject("f2_id",0);}	
			
			
			List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
			if(second_screen.size()>0 && second_screen!=null)
			{
				model.addObject("second_screen",second_screen);
			}
			
			
			List<Object[]> applicanttype_fetch= applicationrepository.fetchapplicanttypeid(id);
			if(applicanttype_fetch.size()>0 && applicanttype_fetch !=null)
			{
			model.addObject("applicanttype_fetch",applicanttype_fetch.get(0));
			}
			
			
			return model;
			
	}
	
	@RequestMapping(value = "/editagentcontactApplication2/{id}/{contactid}/{form}/{formid}", method = RequestMethod.GET)
	public ModelAndView editApplication2NaturalContactId(@PathVariable(name = "id") Integer id,@PathVariable(name = "contactid") Integer contactid,HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes,
			@PathVariable(name = "form") String form,@PathVariable(name = "formid") String formid) 
	{
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
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
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			/*
			 * ApplicationContacts3 contacts3 = new ApplicationContacts3();
			 * model.addObject("contact3",contacts3);
			 */
			  List<ApplicantTypes> applicanttypelist2 =  applicanttypes.applicanttypeform2();
			  model.addObject("applicanttypelist2", applicanttypelist2);
			  
			  ApplicationContacts7 contacts7 = new ApplicationContacts7();
			  model.addObject("contact7",contacts7);
			  
			  List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
			  if(checkbox !=null)
				 {
				 model.addObject("checkbox",checkbox);
				 }
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			 
				String varietyval= candidatevarietydetailsrepository.hibridVarietyCount(id);
				  if(varietyval !=null && varietyval.length() >0)
					 {
					  model.addObject("varietyval",varietyval);
					 }
				  else {
					  model.addObject("varietyval",null);
				  }
				  
			  // List<Object[]> document_checklistdata=documentchecklistrepository.getform2details();
			  // model.addObject("document_checklistdata", document_checklistdata);
			  
			  
			  List<Object[]> document_checklistdata1=documentchecklistrepository.getform2details();
				 
			  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationidform2(id);
			  if(document_checklistdata.size()>0 && document_checklistdata!=null)
			  {model.addObject("document_checklistdata", document_checklistdata);
			  model.addObject("docattach",1);
			  }
			  else
			  {model.addObject("document_checklistdata", document_checklistdata1);
			  model.addObject("docattach",0);
			  }
				 
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 1);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id);
			  model.addObject("applicationnaturalcontact",applicationnaturalcontact);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
		      // List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //  List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
		      //List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  model.addObject("applicationcontact",applicationcontact);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
				  
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
				  model.addObject("breadercontact",breadercontact);
				  }
				  else {
					  model.addObject("breadercontact",1);
				  }
			  
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id);
			  if(agentcontact !=null && agentcontact.size()>0) {
			  model.addObject("agentcontact",agentcontact);
			  }
			  else {
				  model.addObject("agentcontact",1);
			  }
			  
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
			  model.addObject("conventioncontry",conventioncontry);
			  }
			  else {
				  model.addObject("conventioncontry",1);
			  }
			  
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
		 
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
			 
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
			 
			 if(firstsetbind != null) {
				 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
					    	  model.addObject("intArray",intArray);
							}
					 }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0) {
				 
				 paymentid = applicationpaymentsrepository.getPaymentId(id);
				  if(paymentid !=0) {
					ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				    model.addObject("payment",payment);
				    
				    String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
					  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
						  {
						  model.addObject("app_payed_data",getapp_pay_via_id);
						  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
						  }
					  
				  
				  }	  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			 Integer tqid = null;
			 
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id);
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				  model.addObject("technicalquestion",question2);
				
				  List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id);
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						model.addObject("techques_ref",techques_ref); 
					  
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  model.addObject("technicalquestion",question2);
				  model.addObject("techques_ref",null);
			 }
			 if(applications.getVarirtytypeid() !=null) {
				 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
				 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
				 }
			 if(secondsetbind != null && secondsetbind.size() >0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size()>0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size()>0 )
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			ApplicationContacts3 contact3 =  applicationcontactsservice3.getById(contactid);
			if(contact3 != null) {
			 model.addObject("contact3",contact3);
			}
			ApplicationContacts contact =  new ApplicationContacts();
			if(contact != null) {
			 model.addObject("contact",contact);
			}
			ApplicationContacts7 contact7 =  new ApplicationContacts7();
			if(contact7 != null) {
			 model.addObject("contact7",contact7);
			}
			ApplicationConventionCountries convention = new ApplicationConventionCountries();
			model.addObject("convention",convention);
			
			ApplicationParentalline parental = new ApplicationParentalline();
			model.addObject("parental",parental);
			
			ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			model.addObject("parentalother",parentalother);
			//Added Here For Name And Role Showing in header
			
			long statusofapplication = applicationsrepository.returnapplicationstat(id);
			if(statusofapplication != 0 && statusofapplication<2)
			model.addObject("appstatus",statusofapplication);
			
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addObject("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addObject("rolespresent",rolespresent);
			
			 List<Object[]> f3_id= application_sub_repository.get_id_details(id,19);
			 if(f3_id!=null && f3_id.size()>0)
			 {model.addObject("f3_id",f3_id.get(0));}
			 else {model.addObject("f3_id",0);}
			
			 List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
				if(second_screen.size()>0 && second_screen!=null)
				{
					model.addObject("second_screen",second_screen);
				}
				
				List<Object[]> applicanttype_fetch= applicationrepository.fetchapplicanttypeid(id);
				if(applicanttype_fetch.size()>0 && applicanttype_fetch !=null)
				{
				model.addObject("applicanttype_fetch",applicanttype_fetch.get(0));
				}
				
		   //Umesh Adding ends here  
			return model;
			
	}
	
	
	@RequestMapping(value = "/editapplication2naturalcontact/{id}/{contactid}/{form}/{formid}", method = RequestMethod.GET)
	public ModelAndView editApplication2NaturalContactByApplicationAndContactId(@PathVariable(name = "id") Integer id,@PathVariable(name = "contactid") Integer contactid,HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes,
			@PathVariable(name = "form") String form,@PathVariable(name = "formid") String formid) 
	{
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
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
					List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
					  if(checkbox !=null)
						 {
						 model.addObject("checkbox",checkbox);
						 }
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id);
			  if(agentcontact !=null && agentcontact.size()>0) {
				  model.addObject("agentcontact",agentcontact);
				  }
				  else {
					  model.addObject("agentcontact",1);
				  }
			  String varietyval= candidatevarietydetailsrepository.hibridVarietyCount(id);
			  if(varietyval !=null && varietyval.length() >0)
				 {
				  model.addObject("varietyval",varietyval);
				 }
			  else {
				  model.addObject("varietyval",null);
			  }
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			  
			  // List<Object[]> document_checklistdata=documentchecklistrepository.getform2details();
			  // model.addObject("document_checklistdata", document_checklistdata);
			 	
			  List<Object[]> document_checklistdata1=documentchecklistrepository.getform2details();
				 
			  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationidform2(id);
			  if(document_checklistdata.size()>0 && document_checklistdata!=null)
			  {model.addObject("document_checklistdata", document_checklistdata);
			  model.addObject("docattach",1);
			  }
			  else
			  {model.addObject("document_checklistdata", document_checklistdata1);
			  model.addObject("docattach",0);
			  }
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 1);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<ApplicantTypes> applicanttypelist2 =  applicanttypes.applicanttypeform2();
			  model.addObject("applicanttypelist2", applicanttypelist2);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
		     // List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			 // List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
		      //List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  model.addObject("applicationcontact",applicationcontact);
			  
			 
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
				  
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
				  model.addObject("breadercontact",breadercontact);
				  }
				  else {
					  model.addObject("breadercontact",1);
				  }
			  
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
				  model.addObject("conventioncontry",conventioncontry);
				  }
				  else {
					  model.addObject("conventioncontry",1);
				  }
			  
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
		 
		 
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
			 
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0) {
				 
				 paymentid = applicationpaymentsrepository.getPaymentId(id);
				  if(paymentid !=0) {
					ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				    model.addObject("payment",payment);
				    
				    String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
					  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
						  {
						  model.addObject("app_payed_data",getapp_pay_via_id);
						  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
						  }
				  
				  }
				  
				  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			 
			 Integer tqid = null;
			 
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id);
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				  model.addObject("technicalquestion",question2);
				  
				  List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id);
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						model.addObject("techques_ref",techques_ref); 
					  
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  model.addObject("technicalquestion",question2);
				  
				  model.addObject("techques_ref",null);
			 }
			 if(applications.getVarirtytypeid() !=null) {
				 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
				 if(varietyandsubvariety !=null && varietyandsubvariety.size()>0)
				 {
				 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
				 }
				 }
			 
			 if(firstsetbind != null) {
			 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
					    	  model.addObject("intArray",intArray);
							}
					 }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 if(secondsetbind != null && secondsetbind.size() >0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size()>0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size()>0)
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			
			 if(application10c !=null && application10c.size()>0)
			 {
				 model.addObject("application10c",application10c.get(0));
				 model.addObject("application10c",application10c);
			 }
			 
			 ApplicationContacts3 contact3 =new ApplicationContacts3();
			 model.addObject("contact3",contact3);
			 
			  ApplicationContacts7 contacts7 = new ApplicationContacts7();
			  model.addObject("contact7",contacts7);
			  
		
			ApplicationContacts contact = applicationcontactsservice.getById(contactid);
		    if(contact != null) { model.addObject("contact",contact); }
		  
			ApplicationConventionCountries convention = new ApplicationConventionCountries();
			model.addObject("convention",convention);
			
			ApplicationParentalline parental = new ApplicationParentalline();
			model.addObject("parental",parental);
			
			ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			model.addObject("parentalother",parentalother);
			//Added Here For Name And Role Showing in header
			
			long statusofapplication = applicationsrepository.returnapplicationstat(id);
			if(statusofapplication != 0 && statusofapplication<2)
			model.addObject("appstatus",statusofapplication);
			
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addObject("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addObject("rolespresent",rolespresent);
			
			List<Object[]> f2_id= application_sub_repository.get_id_details(id,18);
			if(f2_id!=null && f2_id.size()>0)
			 {model.addObject("f2_id",f2_id.get(0));}
			 else {model.addObject("f2_id",0);}	
			
			List<Object[]> f7_id= application_sub_repository.get_id_details(id,23);
			if(f7_id!=null && f7_id.size()>0)
			{model.addObject("f7_id",f7_id.get(0));}
			else {model.addObject("f7_id",0);}	
			
			List<Object[]> f3_id= application_sub_repository.get_id_details(id,19);
			if(f3_id!=null && f3_id.size()>0)
			{model.addObject("f3_id",f3_id.get(0));}
			else {model.addObject("f3_id",0);}
			
			List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
			if(second_screen.size()>0 && second_screen!=null)
			{
				model.addObject("second_screen",second_screen);
			}
			
			List<Object[]> applicanttype_fetch= applicationrepository.fetchapplicanttypeid(id);
			if(applicanttype_fetch.size()>0 && applicanttype_fetch !=null)
			{
			model.addObject("applicanttype_fetch",applicanttype_fetch.get(0));
			}
		    //Umesh Adding ends here  
			return model;
			
	}
	
	@RequestMapping(value = "/editapplication2contact/{id}/{contactid}/{form}/{formid}", method = RequestMethod.GET)
	public ModelAndView editApplication2ContactByApplicationAndContactId(@PathVariable(name = "id") Integer id,@PathVariable(name = "contactid") Integer contactid,HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes,
			@PathVariable(name = "form") String form,@PathVariable(name = "formid") String formid) 
	{
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
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
					List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
					  if(checkbox !=null)
						 {
						 model.addObject("checkbox",checkbox);
						 }
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id);
			  if(agentcontact !=null && agentcontact.size()>0) {
				  model.addObject("agentcontact",agentcontact);
				  }
				  else {
					  model.addObject("agentcontact",1);
				  }
			  String varietyval= candidatevarietydetailsrepository.hibridVarietyCount(id);
			  if(varietyval !=null && varietyval.length() >0)
				 {
				  model.addObject("varietyval",varietyval);
				 }
			  else {
				  model.addObject("varietyval",null);
			  }
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			  
			 // List<Object[]> document_checklistdata=documentchecklistrepository.getform2details();
			 // model.addObject("document_checklistdata", document_checklistdata);
				
			  List<Object[]> document_checklistdata1=documentchecklistrepository.getform2details();
				 
			  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationidform2(id);
			  if(document_checklistdata.size()>0 && document_checklistdata!=null)
			  {model.addObject("document_checklistdata", document_checklistdata);
			  model.addObject("docattach",1);
			  }
			  else
			  {model.addObject("document_checklistdata", document_checklistdata1);
			  model.addObject("docattach",0);
			  }
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 1);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<ApplicantTypes> applicanttypelist2 =  applicanttypes.applicanttypeform2();
			  model.addObject("applicanttypelist2", applicanttypelist2);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
		     // List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			 // List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
		      //List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  model.addObject("applicationcontact",applicationcontact);
			  
			  
			  
			  ApplicationContacts3 contacts3 = new ApplicationContacts3();
			  model.addObject("contact3",contacts3);
			  
			  ApplicationContacts7 contacts7 = new ApplicationContacts7();
			  model.addObject("contact7",contacts7);
			  
			  ApplicationContacts contact =new ApplicationContacts();
			  model.addObject("contact",contact);
			  
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
			
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id);
			  model.addObject("applicationnaturalcontact",applicationnaturalcontact);
			  
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
				  model.addObject("breadercontact",breadercontact);
				  }
				  else {
					  model.addObject("breadercontact",1);
				  }
			  
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
				  model.addObject("conventioncontry",conventioncontry);
				  }
				  else {
					  model.addObject("conventioncontry",1);
				  }
			  
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
		 
		 
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
			 
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0) {
				 
				 paymentid = applicationpaymentsrepository.getPaymentId(id);
				  if(paymentid !=0) {
					ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				    model.addObject("payment",payment);
				    
				    String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
					  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
						  {
						  model.addObject("app_payed_data",getapp_pay_via_id);
						  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
						  }
				  
				  }
				  
				  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			 
			 Integer tqid = null;
			 
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id);
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				  model.addObject("technicalquestion",question2);
				  
				  List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id);
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						model.addObject("techques_ref",techques_ref); 
					  
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  model.addObject("technicalquestion",question2);
				  
				  model.addObject("techques_ref",null);
			 }
			 if(applications.getVarirtytypeid() !=null) {
				 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
				 if(varietyandsubvariety !=null && varietyandsubvariety.size()>0)
				 {
				 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
				 }
				 }
			 
			 if(firstsetbind != null) {
			 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
					    	  model.addObject("intArray",intArray);
							}
					 }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 if(secondsetbind != null && secondsetbind.size() >0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size()>0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size()>0)
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			
			 if(application10c !=null && application10c.size()>0)
			 {
				 model.addObject("application10c",application10c.get(0));
				 model.addObject("application10c",application10c);
			 }
			 
			ApplicationContacts7 contact7 =  applicationcontactsservice7.getById(contactid);
			if(contact7 != null) {
			 model.addObject("contact7",contact7);
			}
			
			ApplicationConventionCountries convention = new ApplicationConventionCountries();
			model.addObject("convention",convention);
			
			ApplicationParentalline parental = new ApplicationParentalline();
			model.addObject("parental",parental);
			
			ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			model.addObject("parentalother",parentalother);
			//Added Here For Name And Role Showing in header
			
			long statusofapplication = applicationsrepository.returnapplicationstat(id);
			if(statusofapplication != 0 && statusofapplication<2)
			model.addObject("appstatus",statusofapplication);
			
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addObject("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addObject("rolespresent",rolespresent);
			
			List<Object[]> f2_id= application_sub_repository.get_id_details(id,18);
			if(f2_id!=null && f2_id.size()>0)
		    {model.addObject("f2_id",f2_id.get(0));}
		    else {model.addObject("f2_id",0);}	
			
			List<Object[]> f7_id= application_sub_repository.get_id_details(id,23);
			if(f7_id!=null && f7_id.size()>0)
			{model.addObject("f7_id",f7_id.get(0));}
			else {model.addObject("f7_id",0);}	
			
			List<Object[]> f3_id= application_sub_repository.get_id_details(id,19);
			if(f3_id!=null && f3_id.size()>0)
			{model.addObject("f3_id",f3_id.get(0));}
			else {model.addObject("f3_id",0);}
			
			List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
			if(second_screen.size()>0 && second_screen!=null)
			{
				model.addObject("second_screen",second_screen);
			}
			
			List<Object[]> applicanttype_fetch= applicationrepository.fetchapplicanttypeid(id);
			if(applicanttype_fetch.size()>0 && applicanttype_fetch !=null)
			{
			model.addObject("applicanttype_fetch",applicanttype_fetch.get(0));
			}
		    //Umesh Adding ends here  
			return model;
			
	}
	
	
	/* edit convention contries start */
	@RequestMapping(value = "/editapplication2convention/{id}/{conventionid}/{form}/{formid}", method = RequestMethod.GET)
	public ModelAndView editApplication2ConventionByApplicationAndConventionidId(@PathVariable(name = "id") Integer id,@PathVariable(name = "conventionid") Integer conventionid,HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes,
			@PathVariable(name = "form") String form,@PathVariable(name = "formid") String formid) 
	{
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		// Getting Logged in user
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
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
			  if(checkbox !=null)
				 {
				 model.addObject("checkbox",checkbox);
				 }
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			  
			 // List<Object[]> document_checklistdata=documentchecklistrepository.getform2details();
			 //  model.addObject("document_checklistdata", document_checklistdata);
			  
			  List<Object[]> document_checklistdata1=documentchecklistrepository.getform2details();
				 
			  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationidform2(id);
			  if(document_checklistdata.size()>0 && document_checklistdata!=null)
			  {model.addObject("document_checklistdata", document_checklistdata);
			  model.addObject("docattach",1);
			  }
			  else
			  {model.addObject("document_checklistdata", document_checklistdata1);
			  model.addObject("docattach",0);
			  }
			  
			  
				 
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 1);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
		      //  List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
		    	//  List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
		      //List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  model.addObject("applicationcontact",applicationcontact);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
				  
			  
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
				  model.addObject("breadercontact",breadercontact);
				  }
				  else {
					  model.addObject("breadercontact",1);
				  }
			  
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
				  model.addObject("conventioncontry",conventioncontry);
				  }
				  else {
					  model.addObject("conventioncontry",1);
				  }
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
			 
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
			 
			 if(firstsetbind != null) {
				 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
								 model.addObject("intArray",intArray);
							}
					 }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0) {
				 
				 paymentid = applicationpaymentsrepository.getPaymentId(id);
				  if(paymentid !=0) {
					ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				    model.addObject("payment",payment);
				    
				    String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
					  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
						  {
						  model.addObject("app_payed_data",getapp_pay_via_id);
						  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
						  }
				  
				  }
				  
				  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			 Integer tqid = null;
			 
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id);
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				  model.addObject("technicalquestion",question2);
				  
				  List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id);
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						model.addObject("techques_ref",techques_ref); 
					  
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  model.addObject("technicalquestion",question2);
				  
				  model.addObject("techques_ref",null);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
				 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
				 if(varietyandsubvariety !=null && varietyandsubvariety.size() > 0) {
				 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
				 }
				 
				 }
			 
			 if(secondsetbind != null && secondsetbind.size() > 0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size() > 0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size() >0 )
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			
			//System.out.println("contact id "+id+"application id"+conventionid);
			ApplicationContacts contact = new ApplicationContacts();
			model.addObject("contact",contact);
			
			ApplicationContacts3 contact3 = new ApplicationContacts3();
			model.addObject("contact3",contact3);
			
			ApplicationContacts7 contacts7 = new ApplicationContacts7();
		    model.addObject("contact7",contacts7);
			
			ApplicationConventionCountries convention = applicationconventioncountriesservice.getById(conventionid);
			if(convention != null) {
			model.addObject("convention",convention);
			}
		
			ApplicationParentalline parental = new ApplicationParentalline();
			model.addObject("parental",parental);
			
			ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			model.addObject("parentalother",parentalother);
			//Added Here For Name And Role Showing in header
			
			long statusofapplication = applicationsrepository.returnapplicationstat(id);
			if(statusofapplication != 0 && statusofapplication<2)
			model.addObject("appstatus",statusofapplication);
			
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addObject("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addObject("rolespresent",rolespresent);
			
			List<Object[]> f8_id= application_sub_repository.get_id_details(id,24);
			if(f8_id!=null && f8_id.size()>0)
			 {model.addObject("f8_id",f8_id.get(0));}
			 else {model.addObject("f8_id",0);}
			
			List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
			if(second_screen.size()>0 && second_screen!=null)
			{
				model.addObject("second_screen",second_screen);
			}
			
			List<Object[]> applicanttype_fetch= applicationrepository.fetchapplicanttypeid(id);
			if(applicanttype_fetch.size()>0 && applicanttype_fetch !=null)
			{
			model.addObject("applicanttype_fetch",applicanttype_fetch.get(0));
			}
			//Umesh Adding ends here  
			  return model;
			
	}
	
	/* end edit convention contries */
	
	/* edit start parentalline other */
	@RequestMapping(value = "/editapplication2parentalline/{id}/{parentalid}/{form}/{formid}", method = RequestMethod.GET)
	public ModelAndView editApplication2ParentallineByApplicationAndParentalId(@PathVariable(name = "id") Integer id,@PathVariable(name = "parentalid") Integer parentalid,HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes
			,@PathVariable(name = "form") String form,@PathVariable(name = "formid") String formid) 
	{
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		// Getting Logged in user
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
			  Applications applications =applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
			  if(checkbox !=null)
				 {
				 model.addObject("checkbox",checkbox);
				 }
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
			  model.addObject("technicalquestion",question2);
			  
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id);
			  model.addObject("applicationnaturalcontact",applicationnaturalcontact);
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id);
			  if(agentcontact !=null && agentcontact.size()>0) {
				  model.addObject("agentcontact",agentcontact);
				  }
				  else {
					  model.addObject("agentcontact",1);
				  }
			  // List<Object[]> document_checklistdata=documentchecklistrepository.getform2details();
			  // model.addObject("document_checklistdata", document_checklistdata);
			  String varietyval= candidatevarietydetailsrepository.hibridVarietyCount(id);
			  if(varietyval !=null && varietyval.length() >0)
				 {
				  model.addObject("varietyval",varietyval);
				 }
			  else {
				  model.addObject("varietyval",null);
			  }
			  List<Object[]> document_checklistdata1=documentchecklistrepository.getform2details();
				 
			  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationidform2(id);
			  if(document_checklistdata.size()>0 && document_checklistdata!=null)
			  {model.addObject("document_checklistdata", document_checklistdata);
			  model.addObject("docattach",1);
			  }
			  else
			  {model.addObject("document_checklistdata", document_checklistdata1);
			  model.addObject("docattach",0);
			  }
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 1);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
		      //List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
		      //List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  model.addObject("applicationcontact",applicationcontact);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
				  
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
				  model.addObject("breadercontact",breadercontact);
				  }
				  else {
					  model.addObject("breadercontact",1);
				  }
			  
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
				  model.addObject("conventioncontry",conventioncontry);
				  }
				  else {
					  model.addObject("conventioncontry",1);
				  }
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
		 
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
			 
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
			 
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0) {
				 
				 paymentid = applicationpaymentsrepository.getPaymentId(id);
				  if(paymentid !=0) {
					ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				    model.addObject("payment",payment);
				    
				    String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
					  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
						  {
						  model.addObject("app_payed_data",getapp_pay_via_id);
						  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
						  }
				  
				  }		  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
				 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
				 if(varietyandsubvariety != null && varietyandsubvariety.size()>0) {
				 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
				 }
				
				 }
			 if(firstsetbind != null) {
				 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
								model.addObject("intArray",intArray);
							}
					 }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 if(secondsetbind != null && secondsetbind.size() >0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size()>0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size()>0)
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			
			 if(application10c !=null && application10c.size()>0)
			 {
				 model.addObject("application10c",application10c.get(0));
				 model.addObject("application10c",application10c);
			 }
			 
			ApplicationContacts contact = new ApplicationContacts();
			model.addObject("contact",contact);
			
			ApplicationContacts3 contacts3 = new ApplicationContacts3();
			model.addObject("contacts3",contacts3);
			
			ApplicationContacts7 contacts7 = new ApplicationContacts7();
		    model.addObject("contact7",contacts7);
		    
			ApplicationConventionCountries convention = new ApplicationConventionCountries();
			model.addObject("convention",convention);
			
			ApplicationParentalline parental = applicationparentallineservice.getById(parentalid);
			model.addObject("parental",parental);
			//Added Here For Name And Role Showing in header
			
			long statusofapplication = applicationsrepository.returnapplicationstat(id);
			if(statusofapplication != 0 && statusofapplication<2)
			model.addObject("appstatus",statusofapplication);
			
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addObject("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addObject("rolespresent",rolespresent);
			
			 List<Object[]> f10_id= application_sub_repository.get_id_details(id,26);
			 if(f10_id!=null && f10_id.size()>0)
			 {model.addObject("f10_id",f10_id.get(0));}
			 else {model.addObject("f10_id",0);}
			 
			 
			 List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
				if(second_screen.size()>0 && second_screen!=null)
				{
					model.addObject("second_screen",second_screen);
				}
		        // Adding ends here 
			 
				List<Object[]> applicanttype_fetch= applicationrepository.fetchapplicanttypeid(id);
				if(applicanttype_fetch.size()>0 && applicanttype_fetch !=null)
				{
				model.addObject("applicanttype_fetch",applicanttype_fetch.get(0));
				}

			return model;
			
	}
	/* end edit parentalline other */
	@RequestMapping(value = "/saveapplication2payment", method = RequestMethod.POST)
	public String saveApplications2Payment(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes,@RequestParam MultipartFile paymentfile) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id  = Integer.parseInt(request.getParameter("applicantid"));
			 
			   List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
				  model.addObject("getMFormSectiondata",getMFormSectiondata);
			   
			  int applicationid = Integer.parseInt(request.getParameter("applicationid"));
			  int category = Integer.parseInt(request.getParameter("payment_category"));
			  String amount = request.getParameter("amount");
			  String offline = null;
			  if(request.getParameter("paymentmode") == offline )
			  {
				  offline  = "online";
			  }
			  else {
				  offline  = "offline";
			  }
			  String ddnumber = request.getParameter("ddnumber");
			  String dddate = request.getParameter("dddate");
		
			  SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
			  java.util.Date ddate=null;
			try {
				ddate = sdf1.parse(dddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  java.sql.Date sqlStartDate = new java.sql.Date(ddate.getTime());
			  //System.out.println("ddate:" +ddate);
			  //System.out.println("sqlStartDate:" +sqlStartDate);
			  String bankname = request.getParameter("bankname");
			  String branchname = request.getParameter("branchname");
			  int paymentid = Integer.parseInt(request.getParameter("paymentid"));
			  if(applicationid != 0)
				{
				  String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
					 String filepath =null;
					 String originalName = null; String filepath2 = null; String originalName2=null;
							if (paymentfile.getSize() >=0) 
							{
								StringBuilder filePath = new StringBuilder(
								UPLOAD_FILEPATH+"PAYMENT/"); 
								File file = new File(filePath.toString());
								if (!file.exists()) {
								   file.mkdirs();
								}
								
								if(paymentfile.isEmpty()) 
								{
								String filename=request.getParameter("pay_f2_id");
								//System.out.println("Printing filename"+filename);
								if(filename!=null && !(filename ==""))
								{
									//System.out.println("Printing in else 4619 = "+filename);
								originalName=filename;
								filepath = UPLOAD_FILEPATH+"PAYMENT/"+originalName;
								}
							}
							else
							{
								
								String filename=	paymentfile.getOriginalFilename().substring(0, paymentfile.getOriginalFilename().lastIndexOf("."));
									String fileext=	paymentfile.getOriginalFilename().substring(paymentfile.getOriginalFilename().lastIndexOf("."), paymentfile.getOriginalFilename().length() );
									String ffname= (filename.replace(" ", "_")).trim();
									originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date())+fileext;
									
									File actFile = new File(filePath.append(File.separator + originalName).toString());
								    try {
										Files.copy(paymentfile.getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								        filepath = UPLOAD_FILEPATH+"PAYMENT/"+originalName;
								}	
							}
				  ApplicationPayments payments = new ApplicationPayments();
				  if(paymentid !=0)
				  {
					  payments.setId(paymentid);
				  }
				  
				  payments.setAmount(amount);
				  payments.setBankname(bankname);
				  payments.setBranchname(branchname);
				  payments.setDddate(sqlStartDate);
				  payments.setApplicationid(applicationid);
				  payments.setDdnumber(ddnumber);
				  payments.setPaymentmode(offline);
				  payments.setActive(true);
				  payments.setCreatedby(userid);
				  payments.setDocumentname(originalName);
				  payments.setDocumentpath(filepath);
				  payments.setPayment_category(category);
				  payments.setCreatedon(date);
				  payments.setCreatedby(userid);
				  payments.setCreatedbyip(request.getRemoteAddr());
				  payments.setPaymenttype("Application Registration");
				  Boolean success = applicationpaymentservice.save(payments);
				  if(success) {
					  
					//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
				    	if(request.getParameter("f16_id").equals("0") || request.getParameter("f16_id")==null)
				    	{
				    	ApplicationSubmission app_sub =new ApplicationSubmission();
				    	app_sub.setForm_section_id(16);
				    	app_sub.setActive(true);
				    	app_sub.setApplication_id(applicationid);
				    	app_sub.setCreatedby(Long.parseLong(""+userid));
				    	app_sub.setCreatedon(new Date(ts.getTime()));
				    	app_sub.setCreatedbyip(request.getRemoteAddr());
				    	appsubservice.save(app_sub);
				    	}
				    	//SAVING ENDS HERE
				    	
					   redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
				}
			
			    return "redirect:/applicationform2/"+applicationid;
				//return "redirect:/applicationform2/"+applicationid+"/F_1/Payments";	 
	}
	
	
	@RequestMapping(value = "/saveapplication2questionnair", method = RequestMethod.POST)
	public String saveApplications2TechnicalQuestionnair(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes,@RequestParam MultipartFile pedigreefile, @RequestParam MultipartFile trialfile,@RequestParam MultipartFile variation_important_trait,@RequestParam MultipartFile withdrawfile) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		//System.out.println("Inside technical questionnair");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("tqapplicationid"));
			  Applications applications = applicationservice.getById(id);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  model.addObject("applications", applications);
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values);
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
		      // List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
		      
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  //List<TypeLine> typeline = typelineservice.listall();
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  int tqid = Integer.parseInt(request.getParameter("tqid"));
			  int applicationid = Integer.parseInt(request.getParameter("tqapplicationid"));
			  
			  Boolean is_convention_applicable =null;
			  if(request.getParameter("is_convention_applicable") =="true")
			  {
				  is_convention_applicable=true;  
			  }
			  if(request.getParameter("is_convention_applicable") =="false")
			  {
				  is_convention_applicable=false;  
			  }
			  int origin = Integer.parseInt(request.getParameter("origination"));
			  int origination = 0;
			  if(origin==1)
			  {
				  origination=1;  
			  }
			  else if(origin==2)
			  {
				  origination=2;  
			  }
			  else if(origin==3)
			  {
				  origination=3;  
			  }
			  else if(origin==4)
			  {
				  origination=4;  
			  }
			  else if(origin==5)
			  {
				  origination=5;  
			  }
			  else if(origin==6)
			  {
				  origination=6;  
			  }
			  else if(origin==7)
			  {
				  origination=7;  
			  }
			  else
			  {
				  origination=8;  
			  }
			  if(applicationid != 0)
				{
				  			
				  		    ApplicationTechnicalQuestionnaire technicalquestionnair = new ApplicationTechnicalQuestionnaire();
				  		    technicalquestionnair.setActive(true);
				  		    if(tqid !=0) {
				  		    	 technicalquestionnair.setId(tqid);
				  		    }
				  		    technicalquestionnair.setApplicationid(applicationid);
				  		    technicalquestionnair.setName(request.getParameter("companyname"));
				  		    technicalquestionnair.setYear_of_establishment(request.getParameter("establishment"));
				  		    technicalquestionnair.setRegistered_address(request.getParameter("registered_address"));
				  		    technicalquestionnair.setLocation_office(request.getParameter("companylocation"));
				  		    technicalquestionnair.setTelephone(request.getParameter("ofctelephone"));
				  		    technicalquestionnair.setTelephone_std(request.getParameter("telephone_std"));
				  		    technicalquestionnair.setFax_std(request.getParameter("fax_std"));
				  		    technicalquestionnair.setFax(request.getParameter("fax"));
				  		    technicalquestionnair.setEmail(request.getParameter("email"));
				  		    technicalquestionnair.setCandidatevariety(request.getParameter("candidatevariety"));
				  		    technicalquestionnair.setReleasedearlier(is_convention_applicable);
				  		    technicalquestionnair.setPedigree(request.getParameter("pedigree"));
				  		    technicalquestionnair.setOrigination(origination);
				  		    technicalquestionnair.setParental_material(request.getParameter("parentalmaretial"));
				  		    technicalquestionnair.setTechnique(request.getParameter("technique"));
				  		    technicalquestionnair.setSelectioncriteria(request.getParameter("selectioncriteria"));
				  		    technicalquestionnair.setSelectionstage(request.getParameter("selectionstage"));
				  		    technicalquestionnair.setBreeding_location(request.getParameter("comparative_location"));
				  		    //technicalquestionnair.setComparative_trial_filename(request.getParameter("trialfile"));	
				  		    technicalquestionnair.setComparative_location(request.getParameter("comparative_location"));
				  		    technicalquestionnair.setComparative_place(request.getParameter("comparative_place"));
				  		    technicalquestionnair.setComparative_period(request.getParameter("comparative_period"));
				  		    technicalquestionnair.setComparative_month(request.getParameter("comparative_month"));
				  		    technicalquestionnair.setComparative_year(request.getParameter("comparative_year"));
				  		    technicalquestionnair.setComparative_cultivation(request.getParameter("comparative_cultivation"));
				  		    technicalquestionnair.setComparative_scale(request.getParameter("comparative_scale"));
				  		    technicalquestionnair.setComparative_criteria(request.getParameter("comparative_criteria"));
				  		    technicalquestionnair.setComparative_design(request.getParameter("comparative_design"));
				  		    technicalquestionnair.setComparative_analysis(request.getParameter("comparative_analysis"));
				  		    technicalquestionnair.setComparative_other(request.getParameter("comparative_other"));
				  		    technicalquestionnair.setCharacteristics_group(request.getParameter("chargroup"));
				  		    technicalquestionnair.setCharacteristics_distinguishing(request.getParameter("characteristicsdist"));
				  		    technicalquestionnair.setCharacteristics_denomination(request.getParameter("cd"));
				  		    technicalquestionnair.setCharacteristics_variety_denomination(request.getParameter("characteristics_variety_denomination"));
				  		    technicalquestionnair.setCharacteristics_variety_comparison(request.getParameter("characteristics_variety_choice"));
				  		    technicalquestionnair.setCharacteristics_variety_distinguishable(request.getParameter("characteristics_variety_distinguishable"));
				  		    technicalquestionnair.setCharacteristics_variety_referencedenomination(request.getParameter("characteristics_variety_referencedenomination"));
				  		    technicalquestionnair.setCharacteristics_variety_choice(request.getParameter("characteristics_variety_choice"));
				  		    technicalquestionnair.setCharacteristics_basic_distinguishable(request.getParameter("characteristics_basic_distinguishable"));
				  		    technicalquestionnair.setStatement_distinctiveness(request.getParameter("diststatement"));
				  		    technicalquestionnair.setStatement_uniformity(request.getParameter("uniformitystatement"));
				  		    technicalquestionnair.setStatement_stability(request.getParameter("statmentstability"));
				  		    technicalquestionnair.setMethods_candidate(request.getParameter("statementmethod"));
				  		    technicalquestionnair.setGrouping_characters(request.getParameter("conventioninformation"));
				  		    technicalquestionnair.setStability_parameter(request.getParameter("uniformityparameter"));
				  		    technicalquestionnair.setCreatedon(date);
				  		    technicalquestionnair.setCreatedby(userid);
				  		    technicalquestionnair.setCreatedbyip(request.getRemoteAddr());
				  		    Boolean isVariety_withdrawn = null;
				  		    if(request.getParameter("isVariety_withdrawn") == "yes") {
				  			 isVariety_withdrawn = true;
				  		    }
				  		    else {
				  			 isVariety_withdrawn = false;
				  		    }
				  		    technicalquestionnair.setVariety_withdrawn(isVariety_withdrawn);
				  		    String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
							 String filepath =null;String filepath2 =null;String filepath3 =null;String filepath4 =null;
							 String originalName = null;String originalName2=null;String originalName3=null;String originalName4=null;
							 
									if (pedigreefile.getSize() >=0) 
									{
										StringBuilder filePath = new StringBuilder(
										UPLOAD_FILEPATH+"FORMV/"); 
										File file = new File(filePath.toString());
										if (!file.exists()) {
										   file.mkdirs();
										}
										
										if(pedigreefile.isEmpty()) 
										{
										String filename=request.getParameter("file_pedigree");
										//System.out.println("Printing filename"+filename);
										if(filename!=null && !(filename ==""))
										{
											//System.out.println("Printing in else 4883 = "+filename);
										originalName=filename;
										filepath = UPLOAD_FILEPATH+"FORMV/"+originalName;
										}
									}
									else
									{
										
											String filename=	pedigreefile.getOriginalFilename().substring(0, pedigreefile.getOriginalFilename().lastIndexOf("."));
											String fileext=	pedigreefile.getOriginalFilename().substring(pedigreefile.getOriginalFilename().lastIndexOf("."), pedigreefile.getOriginalFilename().length() );
											String ffname= (filename.replace(" ", "_")).trim();
											originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date())+fileext;
											
											File actFile = new File(filePath.append(File.separator + originalName).toString());
										    try {
												Files.copy(pedigreefile.getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										        filepath = UPLOAD_FILEPATH+"FORMV/"+originalName;
										}	      	
									}
									technicalquestionnair.setPedigree_imagename(originalName);
									technicalquestionnair.setPedigree_imagepath(filepath);
									
									if (trialfile.getSize() >=0) 
									{
										StringBuilder filePath = new StringBuilder(
										UPLOAD_FILEPATH+"FORMV/"); 
										File file = new File(filePath.toString());
										if (!file.exists()) {
										   file.mkdirs();
										}
										
										if(trialfile.isEmpty()) 
										{
										String filename=request.getParameter("file_trial");
										//System.out.println("Printing filename"+filename);
										if(filename!=null && !(filename ==""))
										{
											//System.out.println("Printing in else 1622 = "+filename);
										originalName2=filename;
										filepath2 = UPLOAD_FILEPATH+"FORMV/"+originalName2;
										}
									}
									else
									{
											String filename=	trialfile.getOriginalFilename().substring(0, trialfile.getOriginalFilename().lastIndexOf("."));
											String fileext=	trialfile.getOriginalFilename().substring(trialfile.getOriginalFilename().lastIndexOf("."), trialfile.getOriginalFilename().length() );
											String ffname= (filename.replace(" ", "_")).trim();
											originalName2 = ffname+ new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date())+fileext;
											
											File actFile = new File(filePath.append(File.separator + originalName2).toString());
										    try {
												Files.copy(trialfile.getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										        filepath2 = UPLOAD_FILEPATH+"FORMV/"+originalName2;
										      	
									}
									
									}	
									technicalquestionnair.setComparative_trial_filename(originalName2);
									technicalquestionnair.setComparative_trial_filepath(filepath2);
									
									if (variation_important_trait.getSize() >=0) 
									{
										StringBuilder filePath = new StringBuilder(
										UPLOAD_FILEPATH+"FORMV/"); 
										File file = new File(filePath.toString());
										if (!file.exists()) {
										   file.mkdirs();
										}
										
										
										if(variation_important_trait.isEmpty()) 
										{
										String filename=request.getParameter("file_variation");
										//System.out.println("Printing filename"+filename);
										if(filename!=null && !(filename ==""))
										{
											//System.out.println("Printing in else 4970 = "+filename);
										originalName3=filename;
										filepath3 = UPLOAD_FILEPATH+"FORMV/"+originalName3;
										}
									}
									else
									{
											String filename=	variation_important_trait.getOriginalFilename().substring(0, variation_important_trait.getOriginalFilename().lastIndexOf("."));
											String fileext=	variation_important_trait.getOriginalFilename().substring(variation_important_trait.getOriginalFilename().lastIndexOf("."), variation_important_trait.getOriginalFilename().length() );
											String ffname= (filename.replace(" ", "_")).trim();
											originalName3 = ffname+ new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date())+fileext;
											//System.out.println("PRINTING ORIGINAL NAME 3= "+originalName3);
											File actFile = new File(filePath.append(File.separator + originalName3).toString());
										    try {
												Files.copy(variation_important_trait.getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										        filepath3 = UPLOAD_FILEPATH+"FORMV/"+originalName3;
										      	
									}
									}
									
									technicalquestionnair.setVariation_important_trait_filename(originalName3);
									technicalquestionnair.setVariation_important_trait_filepath(filepath3);
									
									if (withdrawfile.getSize() >=0) 
									{
										StringBuilder filePath = new StringBuilder(
										UPLOAD_FILEPATH+"FORMV/"); 
										File file = new File(filePath.toString());
										if (!file.exists()) {
										   file.mkdirs();
										}
										
										if(withdrawfile.isEmpty()) 
										{
										String filename=request.getParameter("file_sup");
										//System.out.println("Printing filename"+filename);
										if(filename!=null && !(filename ==""))
										{
											//System.out.println("Printing in else 5012 = "+filename);
										originalName4=filename;
										filepath4 = UPLOAD_FILEPATH+"FORMV/"+originalName4;
										}
									}
									else
									{
											String filename=	withdrawfile.getOriginalFilename().substring(0, withdrawfile.getOriginalFilename().lastIndexOf("."));
											String fileext=	withdrawfile.getOriginalFilename().substring(withdrawfile.getOriginalFilename().lastIndexOf("."), withdrawfile.getOriginalFilename().length() );
											String ffname= (filename.replace(" ", "_")).trim();
											originalName4 = ffname+ new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date())+fileext;
											
											File actFile = new File(filePath.append(File.separator + originalName4).toString());
										    try {
												Files.copy(withdrawfile.getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										        filepath4 = UPLOAD_FILEPATH+"FORMV/"+originalName4;
										      	
									}
									}
									
									
									technicalquestionnair.setSupplement_information_name(originalName4);
									technicalquestionnair.setSupplement_information_path(filepath4);
									technicalquestionnair.setCreatedby(userid);
									Date date1 = new Date();
									technicalquestionnair.setCreatedon(date1);
									technicalquestionnair.setCreatedbyip(request.getRemoteAddr());
									
				  		    Boolean success = applicationtechnicalquestionnairservice.save(technicalquestionnair);
					    	if(success) {
					    		    int technicalquestionid = technicalquestionnair.getId();
					    		    String[] comparative_reference = request.getParameterValues("comparative_reference");
					    		    String [] tqreferenceid = request.getParameterValues("tqreferenceid");
						  		    for(int k=0; k<comparative_reference.length; k++) { 
						  			   
						  			 ApplicationTechnicalQuestionnaireReference tqreference = new ApplicationTechnicalQuestionnaireReference();
						  			 if(tqreferenceid[k] != null) {
						  			 tqreference.setId(Integer.parseInt(tqreferenceid[k]));
						  		     }
						  			 tqreference.setApplicationtqid(technicalquestionid);
						  			 tqreference.setApplicationid(applicationid);
						  			 tqreference.setTechnical_questionnaire_reference(comparative_reference[k]);
						  			 tqreference.setCreatedby(userid);
									 tqreference.setCreatedon(ts);
									 tqreference.setCreatedbyip(request.getRemoteAddr());
						  			 applicationtechnicalquestionnairereferenceservice.save(tqreference);
						  			 
						  		   }
						  		    
						  		    //SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
							    	if(request.getParameter("f15_id").equals("0") || request.getParameter("f15_id")==null)
							    	{
							    	ApplicationSubmission app_sub =new ApplicationSubmission();
							    	app_sub.setForm_section_id(30);
							    	app_sub.setActive(true);
							    	app_sub.setApplication_id(applicationid);
							    	app_sub.setCreatedby(Long.parseLong(""+userid));
							    	app_sub.setCreatedon(new Date(ts.getTime()));
							    	app_sub.setCreatedbyip(request.getRemoteAddr());
							    	appsubservice.save(app_sub);
							    	}
							    	//SAVING ENDS HERE
								redirectAttributes.addFlashAttribute("message", "Data saved Successfully");
							}
							else {
								redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
							}
				}
			
			  return "redirect:/applicationform2/"+applicationid+"/F_1/Questionaire";  
				
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/tocformapplication2/{id}/{sno}", method = RequestMethod.GET)
	public ModelAndView getApplication2TocForm(@PathVariable int id,@PathVariable String sno) {
		ModelAndView model = new ModelAndView("application2/toc-form");
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
			 //System.out.println("printing user id: "+userid);
			 
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
			  if(checkbox !=null)
				 {
				 model.addObject("checkbox",checkbox);
				 }
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			  ApplicationDusVariety dusvarietybyid = new ApplicationDusVariety();
			  model.addObject("dusvarietybyid", dusvarietybyid);
			  int  serialnumber =  Integer.parseInt(sno);
			  List<Object[]> characteristics = duscharacteristicsrepository.getAllCharacteristicsByApplicationId(id,serialnumber);
			 if(characteristics != null && characteristics.size() > 0) {
			  model.addObject("characteristics",characteristics);
			 }
			 else {
				 model.addObject("characteristics",1);
			 }
			 
			  List<Object[]> pivotcharacteristics = duscharacteristicsrepository.getPivotCharacteristics(id,serialnumber);
			  if(pivotcharacteristics != null && pivotcharacteristics.size()>0) {
			  model.addObject("pivotcharacteristics",pivotcharacteristics);
			  }
			  else {
				  model.addObject("pivotcharacteristics",1);
			  }
			  int cropid =  applications.getCropid();
			  List<Object[]> duscharacteristicslist = duscharacteristicsrepository.getAllDusCharacteristicsByApplicationCropid(cropid);
			  model.addObject("duscharacteristicslist",duscharacteristicslist);
			  
			  
			  List<Object[]> applicationvariety = applicationdusvarietyrepository.getAllVarityByApplicationId(id,serialnumber);
			  model.addObject("applicationvariety",applicationvariety);
			  List<Object[]> deno = applicationdusvarietyrepository.getDenomination(id);
			  model.addObject("deno",deno);
			  
			  List<DusTqGroup> dustqgroup = dustqgrouprepository.getTqGroupBySerialNumber(serialnumber);
			  model.addObject("dustqgroup",dustqgroup.get(0));
			  
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id);
			  if(agentcontact !=null && agentcontact.size()>0) {
				  model.addObject("agentcontact",agentcontact);
				  }
				  else {
					  model.addObject("agentcontact",1);
				  }
			  
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			  
			  List<Object[]> document_checklistdata=documentchecklistrepository.getform2details();
			  model.addObject("document_checklistdata", document_checklistdata);
				 
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 0);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
			  //List<TypeLine> typeline = typelineservice.listall();
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
		      //List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  model.addObject("applicationcontact",applicationcontact);
			 
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id);
			  model.addObject("applicationnaturalcontact",applicationnaturalcontact);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
			  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
			  }
			  
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
				  model.addObject("breadercontact",breadercontact);
				  }
				  else {
					  model.addObject("breadercontact",1);
				  }
			  
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
				  model.addObject("conventioncontry",conventioncontry);
				  }
				  else {
					  model.addObject("conventioncontry",1);
				  }
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
		 
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
		
			  ApplicationContacts contacts = new ApplicationContacts();
			  model.addObject("contact",contacts);
			  
			  ApplicationContacts3 contacts3 = new ApplicationContacts3();
			  model.addObject("contacts3",contacts3);
			  
			  ApplicationContacts7 contacts7 = new ApplicationContacts7();
			  model.addObject("contact7",contacts7);
			 
			  ApplicationConventionCountries convention = new ApplicationConventionCountries();
			  model.addObject("convention",convention);
			 
			  ApplicationParentalline parental = new ApplicationParentalline();
			  model.addObject("parental",parental);
			  
			  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			  model.addObject("parentalother",parentalother);
			 
			  ApplicationContacts contactform2 = new ApplicationContacts();
			  model.addObject("contactform2",contactform2);
			  
			  List<Object[]> attachmentlist = applicationdocumentrepository.getattachmentdetailsform2(id);
			  model.addObject("attachmentlist", attachmentlist);
			  
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0) {
				 
				 paymentid = applicationpaymentsrepository.getPaymentId(id);
				  if(paymentid !=0) {
					ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				    model.addObject("payment",payment);
				    
				    String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
					  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
						  {
						  model.addObject("app_payed_data",getapp_pay_via_id);
						  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
						  }
				  }  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			 
			 Integer tqid = null;
			 
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id);
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				  model.addObject("technicalquestion",question2);
				  
				  List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id);
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						model.addObject("techques_ref",techques_ref); 
					  
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  model.addObject("technicalquestion",question2);
				  
				  model.addObject("techques_ref",null);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
			 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
			 if(varietyandsubvariety !=null && varietyandsubvariety.size()>0) {
			 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
			 }
			 else {
				 model.addObject("varietyandsubvariety",1);
			 }
			 }
			 
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
			 
			 if(firstsetbind != null && firstsetbind.size()>0) {
				 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
					    	  model.addObject("intArray",intArray);
							}
					    }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 if(secondsetbind != null && secondsetbind.size()>0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size()>0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size()>0 )
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			  model.addObject("sno",sno);
			  
			  long statusofapplication = applicationsrepository.returnapplicationstat(id);
				if(statusofapplication != 0 && statusofapplication<2)
				model.addObject("appstatus",statusofapplication);
				//Added Here For Name And Role Showing in header
				
				List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addObject("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addObject("rolespresent",rolespresent);
				//Adding on 28-02-2020
				model.addObject("applicationid",id);
				
				List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
				if(second_screen.size()>0 && second_screen!=null)
				{
					model.addObject("second_screen",second_screen);
				}
				// Adding ends here  
			 return model;
			
	}
	
	@RequestMapping(value = "/saveapplication2characteristics", method = RequestMethod.POST)
	public String saveApplication2TQCharacteristics(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("tocapplicationid"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  int tqserialnumber = Integer.parseInt(request.getParameter("tqserialnumber"));
			  String tqdusgroup = request.getParameter("tqdusgroup");
			  int tqtypeline = Integer.parseInt(request.getParameter("typeline"));
			  String tqtypelinedenomination = request.getParameter("typelinedenomination");
			  String[] duscharistics = request.getParameterValues("duscharistics");
			  String[] remarks = request.getParameterValues("remarks");
			  String [] charisticsid = request.getParameterValues("characteristicid");
			  int tqdusgroupid = Integer.parseInt(request.getParameter("tqdusgroupid"));
			 
			  int editmode = Integer.parseInt(request.getParameter("editmode"));
			  int vtid =Integer.parseInt(request.getParameter("id"));
			  int applicationid = Integer.parseInt(request.getParameter("tocapplicationid"));
		/*
		 * System.out.println("TRACE PRINTING editmode= " +
		 * ""+editmode+"tqserialnumber = "+tqserialnumber+"tqdusgroup = " +
		 * ""+tqdusgroup+"tqtypeline ="+tqtypeline+
		 * "tqtypelinedenomination = "+tqtypelinedenomination+"" +
		 * "duscharistics"+duscharistics+"" + "remarks = "+remarks+"charisticsid =" +
		 * ""+charisticsid+"tqdusgroupid= "+ tqdusgroupid+"vtid= "+vtid);
		 */
			  if(editmode == 1) {
				  //System.out.println("TRACE IN EDIT MODE 1");
				  ApplicationDusVariety variety = new ApplicationDusVariety();
					  if(vtid != 0) {	
						variety.setId(vtid);
					  variety.setApplicationid(applicationid);
					  }
					variety.setDustqgroupid(tqdusgroupid);
					variety.setDenomination(tqtypelinedenomination);
					variety.setTypeline(tqtypeline);
					Boolean success =applicationdusvarietyservice.save(variety);
					if(success) {
						int varietyid = variety.getId();
						for(int i=0; i<duscharistics.length; i++) {
						ApplicationDusFeatures feature = new ApplicationDusFeatures();
						//System.out.println("duscharistics.length as ="+duscharistics.length);
						//System.out.println("Getting i as ="+i+"and length as ="+duscharistics.length);
						//System.out.println("Getting the charisticid ="+Integer.parseInt(charisticsid[i]));
						if(Integer.parseInt(charisticsid[i]) !=0) {
							feature.setId(Integer.parseInt(charisticsid[i]));
						}
						feature.setApplicationid(applicationid);
						feature.setDustqgroupid(tqdusgroupid);
						feature.setDustvarietyid(varietyid);
						feature.setDuscharacteristicsid(Integer.parseInt(duscharistics[i]));
						feature.setDuscharacteristicsstatesid(Integer.parseInt(remarks[i]));
						feature.setCreatedon(ts);
						feature.setCreatedby(userid);
						feature.setCreatedbyip(request.getRemoteAddr());
						applicationdusfeatureservice.save(feature);
						}
						redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
			  }
			  else
				{
					ApplicationDusVariety variety = new ApplicationDusVariety();
					variety.setApplicationid(applicationid);
					variety.setDustqgroupid(tqdusgroupid);
					variety.setDenomination(tqtypelinedenomination);
					variety.setTypeline(tqtypeline);
					Boolean success =applicationdusvarietyservice.save(variety);
					if(success) {
						int varietyid = variety.getId();
						for(int i=0; i<duscharistics.length; i++) {
						ApplicationDusFeatures feature = new ApplicationDusFeatures();
						feature.setApplicationid(applicationid);
						feature.setDustqgroupid(tqdusgroupid);
						feature.setDustvarietyid(varietyid);
						feature.setDuscharacteristicsid(Integer.parseInt(duscharistics[i]));
						feature.setDuscharacteristicsstatesid(Integer.parseInt(remarks[i]));
						feature.setCreatedon(ts);
						feature.setCreatedby(userid);
						feature.setCreatedbyip(request.getRemoteAddr());
						applicationdusfeatureservice.save(feature);
						}
						redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
				}
				
				return "redirect:/tocformapplication2/"+applicationid+"/"+tqserialnumber;
			
	}
	
	@RequestMapping(value = "/deletecharacteristicsapplication2/{id}/{varietyid}/{sno}", method = RequestMethod.GET)
	public String deleteCharacteristicsByApplication2IdAndTypeLine(@PathVariable(name = "id") Integer id,@PathVariable(name = "varietyid") Integer varietyid,@PathVariable(name = "sno") Integer sno,HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) 
	{
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
					List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
					  if(checkbox !=null)
						 {
						 model.addObject("checkbox",checkbox);
						 }
					applicationdusfeaturerepository.deleteApplicationDusFeaturesByVarietyId(varietyid);
					applicationdusvarietyrepository.deleteApplicationDusFeaturesByApplicationIdAndVarietyId(id,varietyid);
					//Added Here For Name And Role Showing in header
					
					List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
					  model.addObject("getMFormSectiondata",getMFormSectiondata);
					  
					List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
					model.addObject("usrname_header_val",usrname_header_val);
					List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
					model.addObject("rolespresent",rolespresent);
					
					List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
					if(second_screen.size()>0 && second_screen!=null)
					{
						model.addObject("second_screen",second_screen);
					}
					
				   //Umesh Adding ends here  
					return "redirect:/tocformapplication2/"+id+"/"+sno;
			  	
	}
	
	@RequestMapping(value = "/editapplication2characteristics/{id}/{varietyid}/{sno}", method = RequestMethod.GET)
	public ModelAndView editApplication2CharacteristicsByApplicationIdAndTypeLine(@PathVariable(name = "id") Integer id,@PathVariable(name = "varietyid") Integer varietyid,@PathVariable(name = "sno") Integer sno,HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) 
	{
		ModelAndView model = new ModelAndView("application2/toc-form");
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
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
			  if(checkbox !=null)
				 {
				 model.addObject("checkbox",checkbox);
				 }
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			  int  serialnumber =  sno;
			  List<Object[]> characteristics = duscharacteristicsrepository.getAllCharacteristicsByApplicationId(id,serialnumber);
			  if(characteristics != null && characteristics.size() > 0) {
			  model.addObject("characteristics",characteristics);
			  }
			  else {
				  model.addObject("characteristics",1);
			  }
			  
			  List<Object[]> editcharacteristicsdata = applicationdusfeaturerepository.getCharacteristicsEditData(id,sno,varietyid);
			  if(editcharacteristicsdata != null && editcharacteristicsdata.size() > 0) {
				  model.addObject("editcharacteristicsdata",editcharacteristicsdata);
				  }
				  else {
					  model.addObject("editcharacteristicsdata",editcharacteristicsdata);
				  }
			  List<Object[]> pivotcharacteristics = duscharacteristicsrepository.getPivotCharacteristics(id,serialnumber);
			  if(pivotcharacteristics != null && pivotcharacteristics.size() >0) {
			  model.addObject("pivotcharacteristics",pivotcharacteristics);
			  }
			  else {
				  model.addObject("pivotcharacteristics",1);
			  }
			  
			  int cropid =  applications.getCropid();
			  List<Object[]> duscharacteristicslist = duscharacteristicsrepository.getAllDusCharacteristicsByApplicationCropid(cropid);
			  model.addObject("duscharacteristicslist",duscharacteristicslist);
			  
			  List<Object[]> applicationvariety = applicationdusvarietyrepository.getAllVarityByApplicationId(id,serialnumber);
			  model.addObject("applicationvariety",applicationvariety);
			  List<Object[]> deno = applicationdusvarietyrepository.getDenomination(id);
			  model.addObject("deno",deno);
			  
			  List<DusTqGroup> dustqgroup = dustqgrouprepository.getTqGroupBySerialNumber(serialnumber);
			  model.addObject("dustqgroup",dustqgroup.get(0));
			  
			  List<Object[]> remarks_filled=  duscharstaterepository.getremarks_saved(id,varietyid);
			  model.addObject("remarks_filled",remarks_filled);
			  
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id);
			  if(agentcontact !=null && agentcontact.size()>0) {
				  model.addObject("agentcontact",agentcontact);
				  }
				  else {
					  model.addObject("agentcontact",1);
				  }
			  
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			  
			  List<Object[]> document_checklistdata=documentchecklistrepository.getform2details();
			  model.addObject("document_checklistdata", document_checklistdata);
				 
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 1);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
			  //List<TypeLine> typeline = typelineservice.listall();
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
		      //List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  //List<Districts> District = districtservice.listall();
		      List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  model.addObject("applicationcontact",applicationcontact);
			 
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id);
			  model.addObject("applicationnaturalcontact",applicationnaturalcontact);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
				  model.addObject("breadercontact",breadercontact);
				  }
				  else {
					  model.addObject("breadercontact",1);
				  }
			  
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
				  model.addObject("conventioncontry",conventioncontry);
				  }
				  else {
					  model.addObject("conventioncontry",1);
				  }
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
		 
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
		
			  ApplicationContacts contacts = new ApplicationContacts();
			  model.addObject("contact",contacts);
			  
			  ApplicationContacts3 contacts3 = new ApplicationContacts3();
			  model.addObject("contacts3",contacts3);
			  
			  ApplicationContacts7 contacts7 = new ApplicationContacts7();
			  model.addObject("contact7",contacts7);
			 
			  ApplicationConventionCountries convention = new ApplicationConventionCountries();
			  model.addObject("convention",convention);
			 
			  ApplicationParentalline parental = new ApplicationParentalline();
			  model.addObject("parental",parental);
			  
			  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			  model.addObject("parentalother",parentalother);
			 
			  ApplicationContacts contactform2 = new ApplicationContacts();
			  model.addObject("contactform2",contactform2);
			  
			  List<Object[]> attachmentlist = applicationdocumentrepository.getattachmentdetailsform2(id);
			  model.addObject("attachmentlist", attachmentlist);
			  
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0) {
				 
				 paymentid = applicationpaymentsrepository.getPaymentId(id);
				  if(paymentid !=0) {
					ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				    model.addObject("payment",payment);
				    
				    String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
					  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
						  {
						  model.addObject("app_payed_data",getapp_pay_via_id);
						  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
						  }
					  
				  }  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			 
			 Integer tqid = null;
			 
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id);
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				  model.addObject("technicalquestion",question2);
				  
				  List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id);
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						model.addObject("techques_ref",techques_ref); 
					  
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  model.addObject("technicalquestion",question2);
				  
				  model.addObject("techques_ref",null);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
			 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
			 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
			 }
			 
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 List<Object[]> fifthsetbind = applicationrepository.details_portion5(id.intValue());
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
				
			 if(firstsetbind != null) {
				 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
					    	  model.addObject("intArray",intArray);
							}
					    }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 if(secondsetbind != null && secondsetbind.size() >0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size()>0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size()>0)
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			 
			 if(application10c !=null && application10c.size()>0)
			 {
				 model.addObject("application10c",application10c.get(0));
				 model.addObject("application10c",application10c);
			 }
			 
			 if(fifthsetbind !=null && fifthsetbind.size()>0)
				{
				 model.addObject("fifthsetbind",fifthsetbind.get(0));
				 
				}
			 
			  ApplicationDusVariety dusvarietybyid = applicationdusvarietyservice.getById(varietyid);
			  if(dusvarietybyid != null) {
			  model.addObject("dusvarietybyid", dusvarietybyid);
			  }
			  model.addObject("sno",sno);
			 //Added Here For Name And Role Showing in header
				
			    List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addObject("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addObject("rolespresent",rolespresent);
				
				List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
				if(second_screen.size()>0 && second_screen!=null)
				{
					model.addObject("second_screen",second_screen);
				}
			  //Umesh Adding ends here  
			  return model;
			 
	}
	
	@RequestMapping(value="/form2_attachments", method=RequestMethod.POST)
    public String Form2Attacment(@Valid @ModelAttribute(value="applicationdocuments")ApplicationDocuments applicationdocuments,BindingResult bindingResult, Model model, HttpServletRequest request,RedirectAttributes redirectAttributes,@RequestParam MultipartFile[] fileUploadf2)throws IOException
	{   
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
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		if (bindingResult.hasErrors())
		{ 
			return "admin/new_extend_edv_english2";
		}else 
	    {
			String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
			
		 String[] documenttypeid=request.getParameterValues("documenttypeid");
		for(int k=0; k< fileUploadf2.length ; k++)
		{	
			if(fileUploadf2[k].getOriginalFilename().equals("null") ||fileUploadf2[k].getOriginalFilename() == "" || fileUploadf2[k].getOriginalFilename() == null)
			{
				continue;
				
			}
			else {
			}
		}
		try {
			applicationdocumentsrepository.updatedocuments(Integer.parseInt(request.getParameter("applicationidhidden"))); 
		}catch(Exception apdoc) {}
		 
		 for(int i = 0; i < documenttypeid.length; i++) {
		    	 ApplicationDocuments applicationdocuments1= new ApplicationDocuments();
		    	 applicationdocuments1.setApplicationid(Integer.parseInt(request.getParameter("applicationidhidden")));
		    	 applicationdocuments1.setDocumenttypeid(Integer.parseInt(documenttypeid[i]));
		    	/* if(fileUpload[i].getOriginalFilename() == "" || fileUpload[i].getOriginalFilename() == null || fileUpload[i].getOriginalFilename() ==" " || fileUpload[i].getOriginalFilename().trim() =="" ||fileUpload[i].getOriginalFilename().equals("null"))
					  continue;
					*/
		    	 if (fileUploadf2[i].getSize() >=0) 
					{
						StringBuilder filePath = new StringBuilder(
						UPLOAD_FILEPATH+"DOCUMENTS/"); 
						File file = new File(filePath.toString());
						if (!file.exists()) {
						   file.mkdirs();
						}
							
						if(fileUploadf2[i].isEmpty()) 
						{
						String filename[]=request.getParameterValues("file_f2_attach");
						System.out.println("Printing filename"+filename[i]);
						if(filename[i]!=null && !(filename[i] ==""))
						{
						System.out.println("Printing in else 5987 = "+filename[i]);
						String originalName=filename[i];
						applicationdocuments1.setDocumentname(originalName);
						applicationdocuments1.setDocumentpath(UPLOAD_FILEPATH+"DOCUMENTS/"+originalName);
						}
					}
					else
					{
						
						
						
							String filename=	fileUploadf2[i].getOriginalFilename().substring(0, fileUploadf2[i].getOriginalFilename().lastIndexOf("."));
							String fileext=	fileUploadf2[i].getOriginalFilename().substring(fileUploadf2[i].getOriginalFilename().lastIndexOf("."), fileUploadf2[i].getOriginalFilename().length() );
							String ffname= (filename.replace(" ", "_")).trim();
							String originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date())+fileext;
							
							File actFile = new File(filePath.append(File.separator + originalName).toString());
							try {
								Files.copy(fileUploadf2[i].getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							applicationdocuments1.setDocumentname(originalName);
							applicationdocuments1.setCreatedon(ts);
							applicationdocuments1.setCreatedby(userid);
							applicationdocuments1.setCreatedbyip(request.getRemoteAddr());
							
							applicationdocuments1.setDocumentpath(UPLOAD_FILEPATH+"DOCUMENTS/"+originalName);
					}
						
					
					}
		    	 
		    	 
					  Boolean success =  applicationdocumentservice.save(applicationdocuments1);
					  if(success) {
						//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
					    	if((request.getParameter("f14_id").equals("0") || request.getParameter("f14_id")== null) && i==0)
					    	{
					    	ApplicationSubmission app_sub =new ApplicationSubmission();
					    	app_sub.setForm_section_id(29);
					    	app_sub.setActive(true);
					    	app_sub.setApplication_id(Integer.parseInt(request.getParameter("applicationidhidden")));
					    	app_sub.setCreatedby(Long.parseLong(""+userid));
					    	app_sub.setCreatedon(new Date(ts.getTime()));
					    	app_sub.setCreatedbyip(request.getRemoteAddr());
					    	appsubservice.save(app_sub);
					    	}
					    	//SAVING ENDS HERE
					    	
						  redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
						}
						else {
							redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
						}
				 	 }
			  }
		int appid=Integer.parseInt(request.getParameter("applicationidhidden"));
		
		return "redirect:/applicationform2/"+appid+"/F_1/Attachments";
	}


	@RequestMapping(value="/application2finalsubmit", method=RequestMethod.POST)
	public String saveApplication2FinalSubmit(HttpServletRequest request, Model model,Applications application,RedirectAttributes redirectAttributes) {
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

	
	List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
	model.addAttribute("userapplicant", userapplicant);
	/// end Getting Logged in user
	Date date = new Date();
	Timestamp ts = new Timestamp(date.getTime());

	int id = Integer.parseInt(request.getParameter("finalapplicationid"));
	//System.out.println("Printing the ID OF FINAL SUBMIT ="+id);
	int success = applicationsrepository.updateApplicationByFinalSubmit(id);
	//System.out.println("Printing the success as ="+success);
	 if(success !=0) {
	
		//Umesh Adding here just for pvp no ... on 06-01-2019
		 List<Object> finan_year=applicationrepository.getdate_data();
		 String ffyear="";	
		 String pvpno_generated="";
		 String offcode="";
		 //System.out.println("financial year: "+finan_year);
		 List<Object> seqno1     =applicationrepository.getpvpseq_no((String) (finan_year.get(0)));
		      
		 List<Object> office_code=applicationrepository.getoffice_code(id);
	     if(office_code!=null)
		 offcode=(String)(office_code.get(0));
	     //System.out.println("Generating PVP NUMBER OFFICE CODE TO TEST= "+offcode);
		 int pvp_seq_no=0;
		 SeqNo_PVP pvpsno= new SeqNo_PVP();
		 pvp_seq_no =(Integer) (seqno1.get(0));
		 pvpsno.setId(pvp_seq_no);
		 pvpsno.setFyear((String)finan_year.get(0)); 
		 sequencepvprepository.save(pvpsno);
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		 formatter.format(LocalDate.now());
		 String locdate = formatter.format(LocalDate.now());
		 String ldate =locdate.replace("/","");
		 //System.out.println("Generating PVP NUMBER TO TEST= "+offcode+" "+ldate+" "+pvpsno.getId());
		 pvpno_generated=offcode+""+ldate+""+pvpsno.getId();
		 int success1 = applicationsrepository.updateApplicationpvpno(id,pvpno_generated,date);
		 Remarks r = new Remarks();
		 if(success1 == 1)
			{
				
				r.setApplicationid(id);
				r.setRemarks("Application Submitted by  "+userdeail.getUsername());
				r.setStatus(32);
				r.setCreatedby(userdeail.getId());
				r.setCreatedbyip(request.getRemoteAddr());
				r.setCreatedon(ts);
				
			}
		   //Umesh Adding Ends Here ------
		 Boolean succ = remarkservice.save(r);
		 if(succ) {
		 redirectAttributes.addFlashAttribute("message", "Application submitted successfully. Your auto generated PVP No is: "+pvpno_generated);
		 }
		 else {  
		 redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
		 }
		
	 	}
	 	//Commented this on 22-01-2020
	 	return "redirect:/applicationform2/"+id;
	 //return "redirect:/savedapplication";
	}

//Addding Here On 25-02-2020
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/applicationform2/{id}/{form}/{form_attach}", method = RequestMethod.GET)
	public ModelAndView formwithtabByIdApplication2(@PathVariable int id,HttpServletRequest req
				,@PathVariable String form,@PathVariable String form_attach) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		/// Getting Logged in user
		model.addObject("form",form);
		model.addObject("form_attach",form_attach);
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
				
			  //System.out.println("printing user id: "+userid);
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  Integer tqid = null;
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
			  if(checkbox !=null)
			  {
				  model.addObject("checkbox",checkbox);
				 }
			  String varietyval= candidatevarietydetailsrepository.hibridVarietyCount(id);
			  if(varietyval !=null && varietyval.length() >0)
				 {
				  model.addObject("varietyval",varietyval);
				 }
			  else {
				  model.addObject("varietyval",null);
			  }
			  

			  
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
			  tqid = applicationtqrepository.getTqId(id);
			  ApplicationTechnicalQuestionnaire applicatiotq = applicationtechnicalquestionnaireservice.getById(tqid);
			  if(applicatiotq != null)
			  {
				  model.addObject("applicatiotq",applicatiotq);
			  }
			  else {
				  model.addObject("applicatiotq",applicatiotq);
			  }
			 }
		
			  List<Object[]> agentcontact =applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id);
			  if(agentcontact !=null && agentcontact.size() > 0) {
			  model.addObject("agentcontact",agentcontact); } else {
			  model.addObject("agentcontact",1); }
		 			  
			  ApplicationContacts3 contacts3 = new ApplicationContacts3();
			  model.addObject("contact3",contacts3);
			  
			  ApplicationContacts7 contacts7 = new ApplicationContacts7();
			  model.addObject("contact7",contacts7);
			  
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			  
			  //System.out.println("Trace For Doc Upload begin");
			  
			  //Done On 16-01-2020
			  //List<Object[]> document_checklistdata=documentchecklistrepository.getform2details();
			  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationidform2(id); 
			  model.addObject("document_checklistdata", document_checklistdata);
			  //System.out.println("Trace For Doc Upload end");
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 1);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<ApplicantTypes> applicanttypelist2 =  applicanttypes.applicanttypeform2();
			  model.addObject("applicanttypelist2", applicanttypelist2);
			  
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
		      List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  if(applicationcontact !=null && applicationcontact.size() >0) {
			  model.addObject("applicationcontact",applicationcontact);
			  }
			  else
			  {
				  model.addObject("applicationcontact",1);
			  }
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id);
			  model.addObject("applicationnaturalcontact",applicationnaturalcontact);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
				  
			  
		
			  List<Object[]> breadercontact =applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
			  model.addObject("breadercontact",breadercontact); } else {
			  model.addObject("breadercontact",1); }
			 
			  
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
				  model.addObject("conventioncontry",conventioncontry);
				  }
				  else {
					  model.addObject("conventioncontry",1);
				  }
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
		 
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			  
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
		
			  ApplicationContacts contacts = new ApplicationContacts();
			  model.addObject("contact",contacts);
			  
			 
			  ApplicationConventionCountries convention = new ApplicationConventionCountries();
			  model.addObject("convention",convention);
			 
			  ApplicationParentalline parental = new ApplicationParentalline();
			  model.addObject("parental",parental);
			  
			  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			  model.addObject("parentalother",parentalother);
			 
			  ApplicationContacts contactform2 = new ApplicationContacts();
			  model.addObject("contactform2",contactform2);
			  
			  List<Object[]> attachmentlist = applicationdocumentrepository.getattachmentdetailsform2(id);
			  model.addObject("attachmentlist", attachmentlist);
			  
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0	 ) 
			 {
				 
				List<ApplicationPayments> paymentdetailsmade =applicationpaymentsrepository.getpaymentdetails_viaapplicationid(id);
				if(paymentdetailsmade!=null)
					model.addObject("paymentdetailsmade",paymentdetailsmade);
				
					
			
			 paymentid = applicationpaymentsrepository.getPaymentId(id); 
			 if(paymentid !=0)
			  {
				 ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				   model.addObject("payment",payment);
				  String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
				  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
					  {
					  model.addObject("app_payed_data",getapp_pay_via_id);
					  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
					  }
				  }  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id);
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				  model.addObject("technicalquestion",question2);
				  
				  List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id);
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						model.addObject("techques_ref",techques_ref); 
					  
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  model.addObject("technicalquestion",question2);
				  model.addObject("techques_ref",null);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
			 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
			 if(varietyandsubvariety !=null && varietyandsubvariety.size() > 0)
			 {
			 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
			 }
			 
			 }
		
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 List<Object[]> fifthsetbind = applicationrepository.details_portion5(id);
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
				 
			 if(firstsetbind != null && firstsetbind.size()>0) {
				 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
					    	  model.addObject("intArray",intArray);
							}
					    }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 if(secondsetbind != null && secondsetbind.size()>0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size()>0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size()>0)
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			 
			 if(application10c !=null && application10c.size()>0)
			 {
				 model.addObject("application10c",application10c.get(0));
				 model.addObject("application10c",application10c);
			 }
			 
			 if(fifthsetbind !=null && fifthsetbind.size() > 0)
				{
				 model.addObject("fifthsetbind",fifthsetbind.get(0));
				 }
			 
			   ActivityLogTable act = new ActivityLogTable();
			   act.setIpaddress(req.getRemoteAddr());
			   act.setActivityby(userdeail.getUsername()); Date dt = new Date();
			   System.out.println("Current date Is ="+dt); act.setLogin_time_stamp(dt);
			   act.setActivity("edit"); act.setUrl("/savedapplication");
			   activitylogservice.save(act);
			 
			
				
			long statusofapplication = applicationsrepository.returnapplicationstat(id);
			if(statusofapplication != 0 && statusofapplication<2)
			model.addObject("appstatus",statusofapplication);
			
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addObject("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addObject("rolespresent",rolespresent);
			
			model.addObject("date_verifying",LocalDate.now());
			
			
			 model.addObject("id_fs1",id);
			 
			 List<Object[]> f1_id= application_sub_repository.get_id_details(id,17);
			 List<Object[]> f2_id= application_sub_repository.get_id_details(id,18);
			 List<Object[]> f3_id= application_sub_repository.get_id_details(id,19);
			 List<Object[]> f4_id= application_sub_repository.get_id_details(id,20);
			 List<Object[]> f5_id= application_sub_repository.get_id_details(id,21);
			 List<Object[]> f6_id= application_sub_repository.get_id_details(id,22);
			 List<Object[]> f7_id= application_sub_repository.get_id_details(id,23);
			 List<Object[]> f8_id= application_sub_repository.get_id_details(id,24);
			 List<Object[]> f9_id= application_sub_repository.get_id_details(id,25);
			 List<Object[]> f10_id= application_sub_repository.get_id_details(id,26);
			 List<Object[]> f11_id= application_sub_repository.get_id_details(id,27);
			 
			 List<Object[]> f13_id= application_sub_repository.get_id_details(id,28);
			 List<Object[]> f14_id= application_sub_repository.get_id_details(id,29);
			 List<Object[]> f15_id= application_sub_repository.get_id_details(id,30);
			 List<Object[]> f16_id= application_sub_repository.get_id_details(id,31);
			
			 
			 if(f1_id!=null && f1_id.size()>0)
			 {model.addObject("f1_id",f1_id.get(0));}
			 else {model.addObject("f1_id",0);}
			 
			 if(f2_id!=null && f2_id.size()>0)
			 {model.addObject("f2_id",f2_id.get(0));}
			 else {model.addObject("f2_id",0);}
			 
			 if(f3_id!=null && f3_id.size()>0)
			 {model.addObject("f3_id",f3_id.get(0));}
			 else {model.addObject("f3_id",0);}
			 
			 if(f4_id!=null && f4_id.size()>0)
			 {model.addObject("f4_id",f4_id.get(0));}
			 else {model.addObject("f4_id",0);}
			 
			 if(f5_id!=null && f5_id.size()>0)
			 {model.addObject("f5_id",f5_id.get(0));}
			 else {model.addObject("f5_id",0);}
			 
			 if(f6_id!=null && f6_id.size()>0)
			 {model.addObject("f6_id",f6_id.get(0));}
			 else {model.addObject("f6_id",0);}
			 
			 if(f7_id!=null && f7_id.size()>0)
			 {model.addObject("f7_id",f7_id.get(0));}
			 else {model.addObject("f7_id",0);}
			 
			 if(f8_id!=null && f8_id.size()>0)
			 {model.addObject("f8_id",f8_id.get(0));}
			 else {model.addObject("f8_id",0);}
			 
			 if(f9_id!=null && f9_id.size()>0)
			 {model.addObject("f9_id",f9_id.get(0));}
			 else {model.addObject("f9_id",0);}
			 
			 if(f10_id!=null && f10_id.size()>0)
			 {model.addObject("f10_id",f10_id.get(0));}
			 else {model.addObject("f10_id",0);}
			 
			 if(f11_id!=null && f11_id.size()>0)
			 {model.addObject("f11_id",f11_id.get(0));}
			 else {model.addObject("f11_id",0);}
			 
			 
			 if(f13_id!=null && f13_id.size()>0)
			 {model.addObject("f13_id",f13_id.get(0));}
			 else {model.addObject("f13_id",0);}
			 
			 if(f14_id!=null && f14_id.size()>0)
			 {model.addObject("f14_id",f14_id.get(0));}
			 else {model.addObject("f14_id",0);}
			 
			 if(f15_id!=null && f15_id.size()>0)
			 {model.addObject("f15_id",f15_id.get(0));}
			 else {model.addObject("f15_id",0);}
			 
			 if(f16_id!=null && f16_id.size()>0)
			 {model.addObject("f16_id",f16_id.get(0));}
			 else {model.addObject("f16_id",0);}
			 
			 
			 List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
				if(second_screen.size()>0 && second_screen!=null)
				{
					model.addObject("second_screen",second_screen);
				}
				return model;
			
	}
	
	@RequestMapping(value = "/savecharacteristicsapplication2", method = RequestMethod.POST)
	public String saveTQCharacteristics(HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id = Integer.parseInt(request.getParameter("tocapplicationid"));
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  int tqserialnumber = Integer.parseInt(request.getParameter("tqserialnumber"));
			  String tqdusgroup = request.getParameter("tqdusgroup");
			  int tqtypeline = Integer.parseInt(request.getParameter("typeline"));
			  String tqtypelinedenomination = request.getParameter("typelinedenomination");
			  String[] duscharistics = request.getParameterValues("duscharistics");
			  String[] remarks = request.getParameterValues("remarks");
			  String [] charisticsid = request.getParameterValues("characteristicid");
			  int tqdusgroupid = Integer.parseInt(request.getParameter("tqdusgroupid"));
			 
			  int editmode = Integer.parseInt(request.getParameter("editmode"));
			  int vtid =Integer.parseInt(request.getParameter("id"));
			  int applicationid = Integer.parseInt(request.getParameter("tocapplicationid"));
		/*
		 * System.out.println("TRACE PRINTING editmode= " +
		 * ""+editmode+"tqserialnumber = "+tqserialnumber+"tqdusgroup = " +
		 * ""+tqdusgroup+"tqtypeline ="+tqtypeline+
		 * "tqtypelinedenomination = "+tqtypelinedenomination+"" +
		 * "duscharistics"+duscharistics+"" + "remarks = "+remarks+"charisticsid =" +
		 * ""+charisticsid+"tqdusgroupid= "+ tqdusgroupid+"vtid= "+vtid);
		 */
			  if(editmode == 1) {
				  //System.out.println("TRACE IN EDIT MODE 1");
				  ApplicationDusVariety variety = new ApplicationDusVariety();
					  if(vtid != 0) {	
						variety.setId(vtid);
					  variety.setApplicationid(applicationid);
					  }
					variety.setDustqgroupid(tqdusgroupid);
					variety.setDenomination(tqtypelinedenomination);
					variety.setTypeline(tqtypeline);
					Boolean success =applicationdusvarietyservice.save(variety);
					if(success) {
						int varietyid = variety.getId();
						for(int i=0; i<duscharistics.length; i++) {
						ApplicationDusFeatures feature = new ApplicationDusFeatures();
						//System.out.println("duscharistics.length as ="+duscharistics.length);
						//System.out.println("Getting i as ="+i+"and length as ="+duscharistics.length);
						//System.out.println("Getting the charisticid ="+Integer.parseInt(charisticsid[i]));
						if(Integer.parseInt(charisticsid[i]) !=0) {
							feature.setId(Integer.parseInt(charisticsid[i]));
						}
						feature.setApplicationid(applicationid);
						feature.setDustqgroupid(tqdusgroupid);
						feature.setDustvarietyid(varietyid);
						feature.setDuscharacteristicsid(Integer.parseInt(duscharistics[i]));
						feature.setDuscharacteristicsstatesid(Integer.parseInt(remarks[i]));
						feature.setCreatedon(ts);
						feature.setCreatedby(userid);
						feature.setCreatedbyip(request.getRemoteAddr());
						applicationdusfeatureservice.save(feature);
						}
						redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
			  }
			  else
				{
					ApplicationDusVariety variety = new ApplicationDusVariety();
					variety.setApplicationid(applicationid);
					variety.setDustqgroupid(tqdusgroupid);
					variety.setDenomination(tqtypelinedenomination);
					variety.setTypeline(tqtypeline);
					Boolean success =applicationdusvarietyservice.save(variety);
					if(success) {
						int varietyid = variety.getId();
						for(int i=0; i<duscharistics.length; i++) {
						ApplicationDusFeatures feature = new ApplicationDusFeatures();
						feature.setApplicationid(applicationid);
						feature.setDustqgroupid(tqdusgroupid);
						feature.setDustvarietyid(varietyid);
						feature.setDuscharacteristicsid(Integer.parseInt(duscharistics[i]));
						feature.setDuscharacteristicsstatesid(Integer.parseInt(remarks[i]));
						feature.setCreatedon(ts);
						feature.setCreatedby(userid);
						feature.setCreatedbyip(request.getRemoteAddr());
						applicationdusfeatureservice.save(feature);
						}
						redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
				}
				
				return "redirect:/tocformapplication2/"+applicationid+"/"+tqserialnumber;
			
	}
	
	@RequestMapping(value = "/editcharacteristicsapplication2/{id}/{varietyid}/{sno}", method = RequestMethod.GET)
	public ModelAndView editCharacteristicsByApplicationIdAndTypeLine(@PathVariable(name = "id") Integer id,@PathVariable(name = "varietyid") Integer varietyid,@PathVariable(name = "sno") Integer sno,HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes) 
	{
		ModelAndView model = new ModelAndView("application2/toc-form");
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
			  Applications applications = applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
			  if(checkbox !=null)
				 {
				 model.addObject("checkbox",checkbox);
				 }
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			  int  serialnumber =  sno;
			  List<Object[]> characteristics = duscharacteristicsrepository.getAllCharacteristicsByApplicationId(id,serialnumber);
			  if(characteristics != null && characteristics.size() > 0) {
			  model.addObject("characteristics",characteristics);
			  }
			  else {
				  model.addObject("characteristics",1);
			  }
			  
			  List<Object[]> editcharacteristicsdata = applicationdusfeaturerepository.getCharacteristicsEditData(id,sno,varietyid);
			  if(editcharacteristicsdata != null && editcharacteristicsdata.size() > 0) {
				  model.addObject("editcharacteristicsdata",editcharacteristicsdata);
				  }
				  else {
					  model.addObject("editcharacteristicsdata",editcharacteristicsdata);
				  }
			  List<Object[]> pivotcharacteristics = duscharacteristicsrepository.getPivotCharacteristics(id,serialnumber);
			  if(pivotcharacteristics != null && pivotcharacteristics.size() >0) {
			  model.addObject("pivotcharacteristics",pivotcharacteristics);
			  }
			  else {
				  model.addObject("pivotcharacteristics",1);
			  }
			  
			  int cropid =  applications.getCropid();
			  List<Object[]> duscharacteristicslist = duscharacteristicsrepository.getAllDusCharacteristicsByApplicationCropid(cropid);
			  model.addObject("duscharacteristicslist",duscharacteristicslist);
			  
			  List<Object[]> applicationvariety = applicationdusvarietyrepository.getAllVarityByApplicationId(id,serialnumber);
			  model.addObject("applicationvariety",applicationvariety);
			  List<Object[]> deno = applicationdusvarietyrepository.getDenomination(id);
			  model.addObject("deno",deno);
			  
			  List<DusTqGroup> dustqgroup = dustqgrouprepository.getTqGroupBySerialNumber(serialnumber);
			  model.addObject("dustqgroup",dustqgroup.get(0));
			  
			  List<Object[]> remarks_filled=  duscharstaterepository.getremarks_saved(id,varietyid);
			  model.addObject("remarks_filled",remarks_filled);
			  
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id);
			  if(agentcontact !=null && agentcontact.size()>0) {
				  model.addObject("agentcontact",agentcontact);
				  }
				  else {
					  model.addObject("agentcontact",1);
				  }
			  
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			  
			  List<Object[]> document_checklistdata=documentchecklistrepository.getformdetails();
			  model.addObject("document_checklistdata", document_checklistdata);
				 
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 1);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
			  //List<TypeLine> typeline = typelineservice.listall();
			  List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
		   //   List<States> State = stateservice.listall();
			List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			//  List<Districts> District = districtservice.listall();
		  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
		      List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  model.addObject("applicationcontact",applicationcontact);
			 
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id);
			  model.addObject("applicationnaturalcontact",applicationnaturalcontact);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
				  model.addObject("breadercontact",breadercontact);
				  }
				  else {
					  model.addObject("breadercontact",1);
				  }
			  
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
				  model.addObject("conventioncontry",conventioncontry);
				  }
				  else {
					  model.addObject("conventioncontry",1);
				  }
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
		 
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
		
			  ApplicationContacts contacts = new ApplicationContacts();
			  model.addObject("contact",contacts);
			  
			  ApplicationContacts3 contacts3 = new ApplicationContacts3();
			  model.addObject("contact3",contacts3);
			  
			  ApplicationContacts7 contacts7 = new ApplicationContacts7();
			  model.addObject("contact7",contacts7);
			 
			  ApplicationConventionCountries convention = new ApplicationConventionCountries();
			  model.addObject("convention",convention);
			 
			  ApplicationParentalline parental = new ApplicationParentalline();
			  model.addObject("parental",parental);
			  
			  ApplicationParentallineOthers parentalother = new ApplicationParentallineOthers();
			  model.addObject("parentalother",parentalother);
			 
			  ApplicationContacts contactform2 = new ApplicationContacts();
			  model.addObject("contactform2",contactform2);
			  
			  List<Object[]> attachmentlist = applicationdocumentrepository.getattachmentdetailsform2(id);
			  model.addObject("attachmentlist", attachmentlist);
			  
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0) {
				 
				 paymentid = applicationpaymentsrepository.getPaymentId(id);
				  if(paymentid !=0) {
					ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				    model.addObject("payment",payment);
				    
				    String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
					  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
						  {
						  model.addObject("app_payed_data",getapp_pay_via_id);
						  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
						  }
					  
				  }  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			 
			 Integer tqid = null;
			 
			 if( applicationtqrepository.getIdByApplicationId(id)  != 0 ) {
				 tqid = applicationtqrepository.getTqId(id);
				 ApplicationTechnicalQuestionnaire question2= applicationtechnicalquestionnairservice.getById(tqid);
				  model.addObject("technicalquestion",question2);
				  
				  List<ApplicationTechnicalQuestionnaireReference> techques_ref = applicationtechnicalquestionnairereferencerepository.refrence_of_tq(id);
					// System.out.println("Trace on 594  as = "+techques_ref.size());
					 
					 if(techques_ref != null && techques_ref.size()>0)
						model.addObject("techques_ref",techques_ref); 
					  
			 }
			 else {	
				  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
				  model.addObject("technicalquestion",question2);
				  model.addObject("techques_ref",null);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
			 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
			 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
			 }
			 
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 List<Object[]> fifthsetbind = applicationrepository.details_portion5(id.intValue());
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
				
			 if(firstsetbind != null) {
				 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
					    	  model.addObject("intArray",intArray);
							}
					    }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 if(secondsetbind != null && secondsetbind.size() >0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size()>0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size()>0)
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			 
			 if(application10c !=null && application10c.size()>0)
			 {
				 model.addObject("application10c",application10c.get(0));
				 model.addObject("application10c",application10c);
			 }
			 
			 if(fifthsetbind !=null && fifthsetbind.size()>0)
				{
				 model.addObject("fifthsetbind",fifthsetbind.get(0));
				 
				}
			 
			  ApplicationDusVariety dusvarietybyid = applicationdusvarietyservice.getById(varietyid);
			  if(dusvarietybyid != null) {
			  model.addObject("dusvarietybyid", dusvarietybyid);
			  }
			  model.addObject("sno",sno);
			//Added Here For Name And Role Showing in header
				
			  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addObject("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addObject("rolespresent",rolespresent);
				
				List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
				if(second_screen.size()>0 && second_screen!=null)
				{
					model.addObject("second_screen",second_screen);
				}
			//Umesh Adding ends here  
			  return model;
			 
	}
	
	
	@RequestMapping(value = "/savepaymentapplication2", method = RequestMethod.POST)
	public String saveApplicationsPayment(HttpServletRequest request,
			Applications application,RedirectAttributes redirectAttributes,
			@RequestParam MultipartFile file2f_2) 
	{
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
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
			   int id  = Integer.parseInt(request.getParameter("applicantid"));
			 
			   List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata_f2();
				  model.addObject("getMFormSectiondata",getMFormSectiondata);
			   
			  int applicationid = Integer.parseInt(request.getParameter("applicationid"));
			  int category = Integer.parseInt(request.getParameter("payment_category"));
			  String amount = request.getParameter("amount");
			  String offline = null;
			  if(request.getParameter("paymentmode") == offline )
			  {
				  offline  = "online";
			  }
			  else {
				  offline  = "offline";
			  }
			  String ddnumber = request.getParameter("ddnumber");
			  String dddate = request.getParameter("dddate");
			  
			
			  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			  java.util.Date ddate=null;
			try {
				ddate = sdf1.parse(dddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  java.sql.Date sqlStartDate = new java.sql.Date(ddate.getTime());
			  System.out.println("ddate:" +ddate);
			  System.out.println("sqlStartDate:" +sqlStartDate);
			  String bankname = request.getParameter("bankname");
			  String branchname = request.getParameter("branchname");
			  int paymentid = Integer.parseInt(request.getParameter("paymentid"));
			  if(applicationid != 0)
				{
				  String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
					 String filepath =null;
					 String originalName = null; String filepath2 = null; String originalName2=null;
							if (file2f_2.getSize() >=0) 
							{
								StringBuilder filePath = new StringBuilder(
								UPLOAD_FILEPATH+"PAYMENT/"); 
								File file = new File(filePath.toString());
								if (!file.exists()) {
								   file.mkdirs();
								}
								
								if(file2f_2.isEmpty()) 
								{
								String filename=request.getParameter("pay_f2_id");
								System.out.println("Printing filename"+filename);
								if(filename!=null && !(filename ==""))
								{
									System.out.println("Printing in else 4619 = "+filename);
								originalName=filename;
								filepath = UPLOAD_FILEPATH+"PAYMENT/"+originalName;
								}
							}
							else
							{
								
								String filename=	file2f_2.getOriginalFilename().substring(0, file2f_2.getOriginalFilename().lastIndexOf("."));
									String fileext=	file2f_2.getOriginalFilename().substring(file2f_2.getOriginalFilename().lastIndexOf("."), file2f_2.getOriginalFilename().length() );
									String ffname= (filename.replace(" ", "_")).trim();
									originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date())+fileext;
									
									File actFile = new File(filePath.append(File.separator + originalName).toString());
								    try {
										Files.copy(file2f_2.getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								        filepath = UPLOAD_FILEPATH+"PAYMENT/"+originalName;
								}	
							}
				  ApplicationPayments payments = new ApplicationPayments();
				  if(paymentid !=0)
				  {
					  payments.setId(paymentid);
				  }
				  
				  payments.setAmount(amount);
				  payments.setBankname(bankname);
				  payments.setBranchname(branchname);
				  payments.setDddate(sqlStartDate);
				  payments.setApplicationid(applicationid);
				  payments.setDdnumber(ddnumber);
				  payments.setPaymentmode(offline);
				  payments.setActive(true);
				  payments.setCreatedby(userid);
				  payments.setDocumentname(originalName);
				  payments.setDocumentpath(filepath);
				  payments.setPayment_category(category);
				  payments.setCreatedon(date);
				  payments.setCreatedby(userid);
				  payments.setCreatedbyip(request.getRemoteAddr());
				  payments.setPaymenttype("Application Registration");
				  Boolean success = applicationpaymentservice.save(payments);
				  if(success) {
					  
					//SAVING DETAILS IN APPLICATION_SUBMISSION TABLE		
				    	if(request.getParameter("f16_id").equals("0") || request.getParameter("f16_id")==null)
				    	{
				    	ApplicationSubmission app_sub =new ApplicationSubmission();
				    	app_sub.setForm_section_id(31);
				    	app_sub.setActive(true);
				    	app_sub.setApplication_id(applicationid);
				    	app_sub.setCreatedby(Long.parseLong(""+userid));
				    	app_sub.setCreatedon(new Date(ts.getTime()));
				    	app_sub.setCreatedbyip(request.getRemoteAddr());
				    	appsubservice.save(app_sub);
				    	}
				    	//SAVING ENDS HERE
				    	
					   redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
				}
			
			  return "redirect:/applicationform2/"+applicationid+"/F_1/Payments";	 
	}
//Adding Ends Here --------------------------	
	@RequestMapping(value = "/editparentallineapplication2/{id}/{parentalid}/{form}/{formid}", method = RequestMethod.GET)
	public ModelAndView editApplicationParentallineByApplicationAndParentalId(@PathVariable(name = "id") Integer id,@PathVariable(name = "parentalid") Integer parentalid,HttpServletRequest request,Applications application,RedirectAttributes redirectAttributes
			,@PathVariable(name = "form") String form,@PathVariable(name = "formid") String formid) 
	{
		ModelAndView model = new ModelAndView("admin/new_extend_edv_english2");
		// Getting Logged in user
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
			  Applications applications =applicationservice.getById(id);
			  model.addObject("applications", applications);
			  
			  List<MFormSection> getMFormSectiondata= master_form_section_repository.getMFormSectiondata();
			  model.addObject("getMFormSectiondata",getMFormSectiondata);
			  
			  List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(""+id));
			  if(checkbox !=null)
				 {
				 model.addObject("checkbox",checkbox);
				 }
			  List<Object[]> tqreference = applicationtechnicalquestionnairereferencerepository.tqReferenceByApplicationId(id);
			  if(tqreference != null && tqreference.size() >0)
			  {
			  model.addObject("tqreference",tqreference);
			  }else {
				  model.addObject("tqreference",1);
			  }
			  ApplicationTechnicalQuestionnaire question2= new ApplicationTechnicalQuestionnaire();
			  model.addObject("technicalquestion",question2);
			  
			  ApplicationDocuments applicationdocuments= new ApplicationDocuments();
			  model.addObject("applicationdocuments",applicationdocuments);
			  List<Object[]> applicationnaturalcontact = applicationcontactrepository.getAllByApplicationIdNaturalType(id);
			  model.addObject("applicationnaturalcontact",applicationnaturalcontact);
			  List<Object[]> agentcontact = applicationcontactrepository3.getAllApplicationContactByIdAndAgent(id);
			  if(agentcontact !=null && agentcontact.size()>0) {
				  model.addObject("agentcontact",agentcontact);
				  }
				  else {
					  model.addObject("agentcontact",1);
				  }
			 // List<Object[]> document_checklistdata=documentchecklistrepository.getformdetails();
			 // model.addObject("document_checklistdata", document_checklistdata);
			  String varietyval= candidatevarietydetailsrepository.hibridVarietyCount(id);
			  if(varietyval !=null && varietyval.length() >0)
				 {
				  model.addObject("varietyval",varietyval);
				 }
			  else {
				  model.addObject("varietyval",null);
			  }
			  List<Object[]> document_checklistdata1=documentchecklistrepository.getformdetails();
				 
			  List<Object[]> document_checklistdata=documentchecklistrepository.getdetails_applicationid(id);
			  if(document_checklistdata.size()>0 && document_checklistdata!=null)
			  {model.addObject("document_checklistdata", document_checklistdata);
			  model.addObject("docattach",1);
			  }
			  else
			  {model.addObject("document_checklistdata", document_checklistdata1);
			  model.addObject("docattach",0);
			  }
			  
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addObject("modulelist", modulelist);
			  model.addObject("editmode", 1);
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addObject("modulelist2", modulelist2);
			  
			  List<Object[]> binded_values = applicationsrepository.bind_values(id);
			  model.addObject("binded_values",binded_values.get(0));
			  
			  List<ApplicantTypes> applicanttypelist =  applicanttypes.applicanttype();
			  model.addObject("applicanttypelist", applicanttypelist);
			  
			  //List<Country> Country = countryservice.listall();
			  List<Country> Country = countryrep.getConutry();
			  model.addObject("Country",Country);  
			  
			  List<Nationality> nationality = nationalityservice.listall();
			  model.addObject("nationality",nationality);  
			  
		  // List<States> State = stateservice.listall();
			  List<States> State=staterep.getstates();
			  model.addObject("State", State);
			  
			//  List<Districts> District = districtservice.listall();
			  List<Districts> District = districtrepository.getalldistricts();
			  model.addObject("District", District);
			    
			  //List <Crops> addcroplist = addcropservice.listall();
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addObject("addcroplist", addcroplist);
				  
		      //List<TypeLine> typeline = typelineservice.listall();
		      List<TypeLine> typeline = typelinerepository.getTypleLineOrderByCode();
			  model.addObject("typeline",typeline);
			  
			  //List <CropSpecies> cropspecieslist = cropspeciesservice.listall();
			  List <CropSpecies> cropspecieslist = cropspeciesrepository.getAllbotanicalNameOrderByName();
			  model.addObject("cropspecieslist", cropspecieslist);
			 
			  List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
			  model.addObject("userapplicant", userapplicant);
			  
			  List<Object[]> applicationcontact = applicationcontactrepository.getAllByApplicationId(id);
			  model.addObject("applicationcontact",applicationcontact);
			  
			  List<Object[]> detailofcomapnytype = applicationsrepository.getDetailofCompanyorInstitutionTypeByUserId(userid);
			  if(detailofcomapnytype != null && detailofcomapnytype.size()>0) {
				  model.addObject("detailofcomapnytype", detailofcomapnytype.get(0));
				 }
				  
			  List<Object[]> breadercontact = applicationcontactrepository7.getAllApplicationContactById(id);
			  if(breadercontact !=null && breadercontact.size()>0) {
				  model.addObject("breadercontact",breadercontact);
				  }
				  else {
					  model.addObject("breadercontact",1);
				  }
			  
			  List<Object[]> conventioncontry = applicationconventioncontry.getConventionCountriesDetailById(id);
			  if(conventioncontry != null && conventioncontry.size() >0 ) {
				  model.addObject("conventioncontry",conventioncontry);
				  }
				  else {
					  model.addObject("conventioncontry",1);
				  }
			  List<Object[]> parentaline = applicationparentallinerepository.getParentaLineById(id);
			  if(parentaline != null && parentaline.size() >0)
			  {
			  model.addObject("parentaline",parentaline);
			  }else {
				  model.addObject("parentaline",1);
			  }
		 
			  List<States> office_state = staterep.findAll();
			  model.addObject("office_state",office_state);
			 
			 List<Object[]> firstsetbind = applicationrepository.details_portion1(id);
			 List<Object[]> secondsetbind = applicationrepository.details_portion2(id);
			 List<Object[]> thirdsetbind  = applicationrepository.details_portion3(id);
			 List<Object[]> fourthsetbind = applicationrepository.details_portion4(id);
			 List<Object[]> application10c = applicationrepository.details_portionapplication2(id);
			 
			 Integer paymentid = null;
			 if(applicationpaymentsrepository.getPaymentByApplicationid(id) != null && applicationpaymentsrepository.getPaymentByApplicationid(id).size() > 0) {
				 
				 paymentid = applicationpaymentsrepository.getPaymentId(id);
				  if(paymentid !=0) {
					ApplicationPayments payment=  applicationpaymentservice.getById(paymentid);
				    model.addObject("payment",payment);
				    
				    String getapp_pay_via_id= applicationpaymentsrepository.getapp_pay_via_id(paymentid);
					  if(getapp_pay_via_id!=null && !(getapp_pay_via_id==""))
						  {
						  model.addObject("app_payed_data",getapp_pay_via_id);
						  //model.addObject("app_payed_data1",getapp_pay_via_id.get(0));
						  }
				  
				  }		  
			 }
			 else {
				 ApplicationPayments payment = new ApplicationPayments();
				 model.addObject("payment",payment);
			 }
			 
			 if(applications.getVarirtytypeid() !=null) {
				 List<Object[]> varietyandsubvariety = applicationsrepository.getVarietyById(id);
				 if(varietyandsubvariety != null && varietyandsubvariety.size()>0) {
				 model.addObject("varietyandsubvariety",varietyandsubvariety.get(0));
				 }
				
				 }
			 if(firstsetbind != null) {
				 model.addObject("firstsetbind",firstsetbind.get(0));
			 model.addObject("firstsetbind1",firstsetbind);
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
								model.addObject("intArray",intArray);
							}
					 }
					 catch(NullPointerException np){System.out.println("HANDLING ERROR");}
					  
				 }
			 }
			 
			 }
			 
			 List<CandidateVariety> candidatevar = candidatevarietyrepository.findAll();
			 model.addObject("candidatevar", candidatevar);
			 
			 if(secondsetbind != null && secondsetbind.size() >0) {
				 model.addObject("secondsetbind",secondsetbind.get(0));
				 model.addObject("secondsetbind2",secondsetbind);
			 }
			 
			 if(thirdsetbind !=null && thirdsetbind.size()>0)
			 {
				 model.addObject("thirdsetbind",thirdsetbind.get(0));
				 model.addObject("thirdsetbind3",thirdsetbind);
			 }	
			
			 if(fourthsetbind !=null && fourthsetbind.size()>0)
			 {
				 model.addObject("fourthsetbind",fourthsetbind.get(0));
				 model.addObject("fourthsetbind4",fourthsetbind);
			 }
			 
			 if(application10c !=null && application10c.size()>0)
			 {
				 model.addObject("application10c",application10c.get(0));
				 model.addObject("application10c",application10c);
			 }
			
			ApplicationContacts contact = new ApplicationContacts();
			model.addObject("contact",contact);
			
			ApplicationContacts3 contacts3 = new ApplicationContacts3();
			model.addObject("contact3",contacts3);
			
			ApplicationContacts7 contacts7 = new ApplicationContacts7();
		    model.addObject("contact7",contacts7);
		    
			ApplicationConventionCountries convention = new ApplicationConventionCountries();
			model.addObject("convention",convention);
			
			ApplicationParentalline parental = applicationparentallineservice.getById(parentalid);
			model.addObject("parental",parental);
			//Added Here For Name And Role Showing in header
			
			long statusofapplication = applicationsrepository.returnapplicationstat(id);
			if(statusofapplication != 0 && statusofapplication<2)
			model.addObject("appstatus",statusofapplication);
			
			List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addObject("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addObject("rolespresent",rolespresent);
			
			 List<Object[]> f10_id= application_sub_repository.get_id_details(id,10);
			 if(f10_id!=null && f10_id.size()>0)
			 {model.addObject("f10_id",f10_id.get(0));}
			 else {model.addObject("f10_id",0);}
			 
			 
			 List<Object[]> second_screen= applicationrepository.secondscreen_data(id);
				if(second_screen.size()>0 && second_screen!=null)
				{
					model.addObject("second_screen",second_screen);
				}
		   // Adding ends here 
			 

			return model;
			
	}
	
	@RequestMapping(value="/finalsubmitform2", method=RequestMethod.POST)
	public String saveFinalSubmit(HttpServletRequest request, Model model,Applications application,RedirectAttributes redirectAttributes) {
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

	
	List<Object[]> userapplicant = userrepository.getApplicantIdByUserId(userid);
	model.addAttribute("userapplicant", userapplicant);
	/// end Getting Logged in user
	Date date = new Date();
	Timestamp ts = new Timestamp(date.getTime());

	int id = Integer.parseInt(request.getParameter("finalapplicationid"));
	System.out.println("Printing the ID OF FINAL SUBMIT ="+id);
	int success = applicationsrepository.updateApplicationByFinalSubmit(id);
	System.out.println("Printing the success as ="+success);
	 if(success !=0) {
	
		//Umesh Adding here just for pvp no ... on 06-01-2019
		 List<Object> finan_year=applicationrepository.getdate_data();
		 String ffyear="";	
		 String pvpno_generated="";
		 String offcode="";
		 //System.out.println("financial year: "+finan_year);
		 List<Object> seqno1     =applicationrepository.getpvpseq_no((String) (finan_year.get(0)));
		      
		 List<Object> office_code=applicationrepository.getoffice_code(id);
	     if(office_code!=null)
		 offcode=(String)(office_code.get(0));
	     //System.out.println("Generating PVP NUMBER OFFICE CODE TO TEST= "+offcode);
		 int pvp_seq_no=0;
		 SeqNo_PVP pvpsno= new SeqNo_PVP();
		 pvp_seq_no =(Integer) (seqno1.get(0));
		 pvpsno.setId(pvp_seq_no);
		 pvpsno.setFyear((String)finan_year.get(0)); 
		 sequencepvprepository.save(pvpsno);
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		 formatter.format(LocalDate.now());
		 String locdate = formatter.format(LocalDate.now());
		 String ldate =locdate.replace("/","");
		 //System.out.println("Generating PVP NUMBER TO TEST= "+offcode+" "+ldate+" "+pvpsno.getId());
		 pvpno_generated=offcode+""+ldate+""+pvpsno.getId();
		 int success1 = applicationsrepository.updateApplicationpvpno(id,pvpno_generated,date);
		 Remarks r = new Remarks();
		 if(success1 == 1)
			{
				
				r.setApplicationid(id);
				r.setRemarks("Application Submitted by  "+userdeail.getUsername());
				r.setStatus(32);
				r.setCreatedby(userdeail.getId());
				r.setCreatedbyip(request.getRemoteAddr());
				r.setCreatedon(ts);
				
			}
		   //Umesh Adding Ends Here ------
		 Boolean succ = remarkservice.save(r);
		 if(succ) {
		 redirectAttributes.addFlashAttribute("message", "Application submitted successfully. Your auto generated PVP No is: "+pvpno_generated);
		 }
		 else {  
		 redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
		 }
		
	 	}
	 	//Commented this on 22-01-2020
	 	//return "redirect:/applicationform2/"+id;
	 	
	 
	 //return "redirect:/savedapplication";
	 return "redirect:/submittedapplication";
	}
}
