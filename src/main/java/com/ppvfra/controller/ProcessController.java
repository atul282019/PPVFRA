package com.ppvfra.controller;

import java.io.File;
import java.io.FileOutputStream;
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
import java.util.Collections;						   
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.Query;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.ppvfra.common.MailCommons;
import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.Application;
import com.ppvfra.entity.ApplicationCertificates;
import com.ppvfra.entity.ApplicationOnlineQuery;
import com.ppvfra.entity.ApplicationPreGrantOpposition;
import com.ppvfra.entity.ApplicationScrutiny;
import com.ppvfra.entity.ApplicationStsSeedrecieved;
import com.ppvfra.entity.ApplicationStsTest;
import com.ppvfra.entity.ApplicationStsTestSeedDetails;
import com.ppvfra.entity.Applications;
import com.ppvfra.entity.Assignment_Details;
import com.ppvfra.entity.CandidateVariety;
import com.ppvfra.entity.Certificatenoissued;
import com.ppvfra.entity.CropGroup;
import com.ppvfra.entity.CropSpecies;
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.DUSTestResults;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.DusTestCenter;
import com.ppvfra.entity.File_Composite;
import com.ppvfra.entity.File_No;
import com.ppvfra.entity.Journal;
import com.ppvfra.entity.MediumTermStorage;
import com.ppvfra.entity.NationalRegister;
import com.ppvfra.entity.OfficeStates;
import com.ppvfra.entity.PublishToPVJ;
import com.ppvfra.entity.Rejuvenation;
import com.ppvfra.entity.Remarks;
import com.ppvfra.entity.Revocation;
import com.ppvfra.entity.States;
import com.ppvfra.entity.TransferSeedToMTS;
import com.ppvfra.entity.TypeLine;
import com.ppvfra.entity.User;
									   
											
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AddDusTestCenterRepository;
import com.ppvfra.entity.SeqNo;
import com.ppvfra.entity.SeqNo_PVP;
import com.ppvfra.repository.ApplicationRepository;
import com.ppvfra.repository.ApplicationScrutinyRepository;
import com.ppvfra.repository.ApplicationStsSeedRepository;
import com.ppvfra.repository.ApplicationStsTestRepository;
import com.ppvfra.repository.ApplicationStsTestSeedDetailsRepository;
import com.ppvfra.repository.ApplicationsRepository;
import com.ppvfra.repository.CandidateVarietyDetailsRepository;
import com.ppvfra.repository.CertificateRepository;
import com.ppvfra.repository.FileNoRepository;
import com.ppvfra.repository.MFormSectionRepository;
import com.ppvfra.repository.MediumTermStorageRepository;
import com.ppvfra.repository.ModulesRepository;
												   
import com.ppvfra.repository.RejuvenationRepository;
import com.ppvfra.repository.RemarksRepository;
import com.ppvfra.repository.ApplicationCertificatesRepository;
import com.ppvfra.repository.ApplicationOnlineQueryRepository;
import com.ppvfra.repository.ApplicationPreGrantRepository;
import com.ppvfra.repository.SequenceRepository;
import com.ppvfra.repository.SequenceRepositoryPVP;
import com.ppvfra.repository.TransferSeedToMTSRepository;
import com.ppvfra.repository.TypeLineRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.ApplicationOnlineQueryService;
import com.ppvfra.service.ApplicationScrutinyService;
import com.ppvfra.service.ApplicationStsTestSeedDetailsService;
import com.ppvfra.service.ApplicationStsTestService;
import com.ppvfra.service.ApplicationsService;
import com.ppvfra.service.AssignmentDetailService;
import com.ppvfra.service.CandidateVarietyService;
import com.ppvfra.service.DUSTestResultsService;
import com.ppvfra.service.JournalService;
import com.ppvfra.service.MediumTermStorageService;
import com.ppvfra.service.NationalRegisterService;
import com.ppvfra.service.PublishToPVJService;
import com.ppvfra.service.RejuvenationService;
import com.ppvfra.service.RemarksService;
import com.ppvfra.service.RevocationService;
import com.ppvfra.service.TransferSeedToMTSService;
import com.ppvfra.service.TypeLineService;
@Controller
public class ProcessController {
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	ApplicationScrutinyService applicationscrutinyservice;
	
	@Autowired
	ApplicationScrutinyRepository applicationscrutinyrepository;
	
	@Autowired
	RemarksService remarksservice;
	
	@Autowired
	RevocationService revocationservice;
	
	@Autowired
	NationalRegisterService nationalregisterservice;
	
	@Autowired
	DUSTestResultsService dustestresultsservice;
	
	@Autowired
	Environment env;
	
	@Autowired
	ApplicationStsSeedRepository applicationstsseedrepository;
	
	@Autowired
	ApplicationRepository applicationrepository;
	
	@Autowired
	PublishToPVJService publishtopvjservice;

	@Autowired
	JournalService journalservice;
	
	@Autowired
	RejuvenationRepository rejuvenationrepository;
	
	@Autowired
	TransferSeedToMTSService transferseedtomtsservice;
	
	@Autowired
	MediumTermStorageRepository mediumtermstoragerepository;
	
	@Autowired
	MediumTermStorageService mediumtermstorageservice;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	TypeLineService typelineservice;
	
	@Autowired
	CandidateVarietyService candidatevarietyservice;
	
	@Autowired
	RejuvenationService rejuvenationservice;
	
	@Autowired
	ApplicationCertificatesRepository application_certificate;
	
	@Autowired
	SequenceRepository sequencerepository;
	 
	@Autowired
	TransferSeedToMTSRepository transferseedtomtsrepository;
 
	@Autowired
	ApplicationStsTestService applicationststestservice;
 
	@Autowired
	ApplicationStsTestSeedDetailsRepository applicationststestseeddetailsrepository;
	
	@Autowired
	ApplicationStsTestSeedDetailsService applicationststestseeddetailsservice;
	
	@Autowired
	ApplicationStsTestRepository applicationststestrepository;
	
	@Autowired
	FileNoRepository filenorepository;
	
	@Autowired
	ApplicationPreGrantRepository applicationpregrantrepository;
	
	@Autowired
	ApplicationCertificatesRepository applicationcertificaterepository;
	
	@Autowired
	ApplicationsRepository applicationsrep;
	
	@Autowired
	CertificateRepository certificaterepository;
	
	@Autowired
	ApplicationStsTestSeedDetailsService applicationsts_test_seeddetails;
	
	@Autowired
	AddDusTestCenterRepository add_testcenter_repository;
	
	 @Autowired
	 RemarksRepository remarksrepository;
	 
	 @Autowired
	 TypeLineRepository typelinerepository;
	 
	@Autowired
	CandidateVarietyDetailsRepository candidatevarietydetailsrepository;
		
	@Autowired
	AssignmentDetailService assignmentdetailservice;
	
	@Autowired
	MFormSectionRepository mformsectionrepository;
	
	@Autowired
	ApplicationOnlineQueryService apponlineservice;
	
	@Autowired
	ApplicationOnlineQueryRepository apponlinequeryrepository;
	
	
	
	@RequestMapping(value = "/applicationscrutiny", method = RequestMethod.POST)
	public String a(@Valid @ModelAttribute(value = "applicationscrutiny") ApplicationScrutiny applicationscrutiny,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes
			) throws IOException {
		String username="";
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
		 { 
			User userdeail = null;
			  int userid = 0;
			  
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				  userid = userdeail.getId();
				
			 } else {
				  username = principal.toString();
				
			}
			
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			String mailaddress="";
		    String mailusername="";
		    
			 int user_uid=0;
			 String designation="";
			  
			 List<Object[]> mail= userrepository.getprofiledata_applicant(applicationid);
				if(mail !=null)
				{
					Object[] r1= (Object[]) mail.get(0);
					mailaddress= String.valueOf(r1[2]);
					mailusername= String.valueOf(r1[0]);
					user_uid= Integer.parseInt(String.valueOf(r1[5]));
				}
				
			if(request.getParameter("btnsubmit2")!=null)
			{
				String dateof_submission = request.getParameter("dateof_submission");
				String remarks_doc_scrutiny = request.getParameter("remarks_doc_scrutiny");
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date1 = null;
				try 
				{
					date1 = formatter1.parse(dateof_submission);
				} 
				catch(ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				int pr = applicationscrutinyrepository.updateApplicationScrutiny(date1,remarks_doc_scrutiny,applicationid);
				if(pr >= 1) {
				Remarks r = new Remarks();
				r.setApplicationid(applicationid);
				r.setRemarks(remarks_doc_scrutiny);
				r.setStatus(32);
				r.setCreatedby(userdeail.getId());
				r.setCreatedbyip(request.getRemoteAddr());
				r.setCreatedon(ts);
				remarksrepository.save(r);
				
				String roleid_receiver = designation;
				String userid_receiver = ""+user_uid;
				Assignment_Details assigned_details = new Assignment_Details();
			    assigned_details.setApplicationid(applicationid);
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
				assigned_details.setApplicationstatus_id(37);
				Boolean success=assignmentdetailservice.save(assigned_details);
				int a1= applicationsrep.deficiencyupdate_status(applicationid);
				if(a1 == 0) {
					redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
				}
				else {
					redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
				}
				 try {
				 send_applicationscrutiny_mail(mailaddress,mailusername,applicationid);
				  } 
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
				}
				else {
					redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
				}
			
			}
			if(request.getParameter("btnsubmit1")!=null)
			{
			 if (principal instanceof UserDetails)
				username = ((UserDetails) principal).getUsername();
			User userdetails = userrepository.getUserDetail(username);
			applicationscrutiny.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
			applicationscrutiny.setCreatedby(userdetails.getId());
			applicationscrutiny.setCreatedon(ts);
			applicationscrutiny.setCreatedbyip(request.getRemoteAddr());
            
			Boolean savedsuccess = applicationscrutinyservice.save(applicationscrutiny);
			 if(savedsuccess) {
				 redirectAttributes.addFlashAttribute("message", "Application Scrutiny Saved Successfully");
			 }
			 else {
				 redirectAttributes.addFlashAttribute("message","Something went wrong please try again.");
			 }
			}
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			//System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/applicationscrutiny");
			activitylogservice.save(act);
		}
		//Commented On 17-02-2020
		return "redirect:/getactions/"+applicationid;
		//Comment Ends Here-----------
		//return "redirect:/application_processing";	
	}

///*****Adding here on 13-01-2020
	boolean send_applicationscrutiny_mail(String mailadd,String username,int applicationid ) throws IOException {
		boolean valreturn = false;

		try {
			new Thread(new Runnable() {
			
				public void run() {
					try {
						//System.out.println("Printing MIME inside send_applicationscrutiny_mail");
						MimeMessage mimeMessage = new MailCommons().mimeMessage();
						mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailadd));
						//System.out.println("Printing mime message 2");
						mimeMessage.setSubject("Document Scrutiny Mail");

						StringBuilder sb = new StringBuilder();
						//System.out.println("Printing mime message3");
						sb.append("Dear " + username + ",");
						//sb.append("<br/><br/>Ms " + orgname
						sb.append("<br/><br/>Ms  , During The Document Scrutiny regarding your Application number: " + applicationid
								+ "<br/>(Form), some shortcommings\r\n" + "\r\n"
								+ "were observed.You are requested to,");
						sb.append("<br/>"
								+ " Please furnish the compliance for further processing of application within 15 days.");
						// sb.append("<br/><br/>" + appid + "on" + dateinsp);
						sb.append("<br/><br/>Thank You<br/>Team PPVFRA");

						mimeMessage.setContent(sb.toString(), "text/html; charset=utf-8");
						Transport.send(mimeMessage);
						//System.out.println("Printing mime message7");
						//System.out.println("Printing at last =="+mimeMessage);

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
	
///****** Adding ends here -------------	
	@RequestMapping(value = "/deleteapplicationscrutiny/{id}/{applicationid}")
	public String deleteById(@PathVariable(name = "id") Integer id,
			@PathVariable(name="applicationid") Integer applicationid ,HttpServletRequest request)
	{
		 
		applicationscrutinyservice.delete(id);
		 
		//int applicationid=Integer.parseInt(request.getParameter("applicationid"));
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
			
		  
		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is ="+dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("save");
		act.setUrl("/deleteapplicationscrutiny");
		
		activitylogservice.save(act);
		 
		 System.out.println("GOING AFTER ACTIVITY LOG ");
		 
		 
		 return "redirect:/getactions/"+applicationid;
	
	}
	
	@RequestMapping(value = "/remarks", method = RequestMethod.POST)
	public String addRemarks(@Valid @ModelAttribute(value 
			= "addremarks")	Remarks addremarks,
			BindingResult bindingResult, Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		String username="";
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
		{ 
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
			addremarks.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
			addremarks.setCreatedby(userdetails.getId());
			addremarks.setCreatedon(ts);
			addremarks.setCreatedbyip(request.getRemoteAddr());
            
			Boolean savedsuccess = remarksservice.save(addremarks);
			if (savedsuccess)
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
			act.setUrl("/remarks");
			activitylogservice.save(act);
		return "redirect:/getactions/"+applicationid;
		}
	}
 
	@RequestMapping(value = "/revocation", method = RequestMethod.POST)
	public String addRevocation(@Valid @ModelAttribute(value = "revocation") Revocation revocation,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username="";
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
		
	/*	if (bindingResult.hasErrors()) {
			/// Getting Logged in user
			int userid = 0;
			System.out.println("PritingeeLoggin user: " + username);
			
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					 username = ((UserDetails) principal).getUsername();
					User userdeail = userrepository.getUserDetail(username);
					userid = userdeail.getId();
					
			 } else {
					 username = principal.toString();
					 //System.out.println("Priting else Loggin user: " + username);
				} 
				/// end Getting Logged in user
				
				
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  model.addAttribute("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  model.addAttribute("modulelist2", modulelist2);
		 
          return "applicationprocessing/action.html";
          
		} 
		else */
		{ 
			System.out.println("tttt11111tssss");
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
			 
				
			 } else {
				  username = principal.toString();
				
			}
						
				String date_revocation = request.getParameter("date_revocation");
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date = null;
				try 
				{
					date = formatter1.parse(date_revocation);
				} 
				catch(ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			
					
			 if (principal instanceof UserDetails)
				username = ((UserDetails) principal).getUsername();
			Date date1 = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			User userdetails = userrepository.getUserDetail(username);
			int id= Integer.parseInt(request.getParameter("id"));
			if(id != 0)
			revocation.setId(id);
			
			revocation.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
			revocation.setDate_revocation(date);
			revocation.setCreatedby(userdetails.getId());
			revocation.setCreatedon(ts);
			revocation.setCreatedbyip(request.getRemoteAddr());
            
			
			/*
			 Umesh Adding here on 30-12-2019 for remarks	
			 */
				/* Umesh Adding here on 30-12-2019------
	               * for adding remarks in application_remarks
	               */
				 Remarks r = new Remarks();
				   	r.setApplicationid(applicationid);
					r.setRemarks(request.getParameter("remarks"));
					r.setStatus(32);
					r.setCreatedby(userdeail.getId());
					r.setCreatedbyip(request.getRemoteAddr());
					r.setCreatedon(ts);
					remarksrepository.save(r);
					
			//Adding here ends on 30-12-2019
					
			Boolean savedsuccess = revocationservice.save(revocation);
			if (savedsuccess)
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
			act.setUrl("/revocation");
			activitylogservice.save(act);
		}
		
		return "redirect:/getactions/"+applicationid;
		
	}

	@RequestMapping(value = "/nationalregister", method = RequestMethod.POST)
	public String addEntryInNationalRegister(@Valid @ModelAttribute(value = "nationalregister") NationalRegister nationalregister,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username="";
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
		int id = Integer.parseInt(request.getParameter("id"));
	/*	if (bindingResult.hasErrors()) {
			/// Getting Logged in user
			int userid = 0;
			System.out.println("PritingeeLoggin user: " + username);
			
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					 username = ((UserDetails) principal).getUsername();
					User userdeail = userrepository.getUserDetail(username);
					userid = userdeail.getId();
					
			 } else {
					 username = principal.toString();
					 //System.out.println("Priting else Loggin user: " + username);
				} 
				/// end Getting Logged in user
				
				
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  model.addAttribute("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  model.addAttribute("modulelist2", modulelist2);
		 
          return "applicationprocessing/action.html";
          
		} 
		else */
		{ 
			System.out.println("tttt11111tssss");
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
			 
				
			 } else {
				  username = principal.toString();
				
			}
						
				String date_of_entry = request.getParameter("date_of_entry");
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date = null;
				try 
				{
					date = formatter1.parse(date_of_entry);
				} 
				catch(ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			
					
			 if (principal instanceof UserDetails)
				username = ((UserDetails) principal).getUsername();
			Date date1 = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			User userdetails = userrepository.getUserDetail(username);
			if(id !=0)
			nationalregister.setId(id);
			
			nationalregister.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
			nationalregister.setDate_of_entry(date);
			nationalregister.setCreatedby(userdetails.getId());
			nationalregister.setCreatedon(ts);
			nationalregister.setCreatedbyip(request.getRemoteAddr());
            
			
			/*
			 Umesh Adding here on 30-12-2019 for remarks	
			 */
				/* Umesh Adding here on 30-12-2019------
	               * for adding remarks in application_remarks
	               */
				 Remarks r = new Remarks();
				 	r.setApplicationid(applicationid);
					r.setRemarks("ENationalEntryRegister Process");
					r.setStatus(32);
					r.setCreatedby(userdeail.getId());
					r.setCreatedbyip(request.getRemoteAddr());
					r.setCreatedon(ts);
					remarksrepository.save(r);
					
			//Adding here ends on 30-12-2019
					
			Boolean savedsuccess = nationalregisterservice.save(nationalregister);
			if (savedsuccess)
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
			act.setUrl("/nationalregister");
			activitylogservice.save(act);
		}
		
		return "redirect:/getactions/"+applicationid;
		
	}
	
	
	@RequestMapping(value = "/dustestresults", method = RequestMethod.POST)
	public String addDusTestResults(@Valid @ModelAttribute(value = "dustestresults") DUSTestResults dustestresults,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes,@RequestParam MultipartFile fileUpload)throws IOException {
		String username="";
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
		int id= Integer.parseInt(request.getParameter("id"));
		
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
			 
				
			 } else {
				  username = principal.toString();
				
			}
				String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");	
				String date_of_completion = request.getParameter("date_of_completion");
				 	
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date = null;
				try 
				{
					date = formatter1.parse(date_of_completion);
				} 
				catch(ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			//	System.out.println("Trace For File Upload Length ="+fileUpload);
			 
				// for(int i=0; i< fileUpload.length ; i++) 
				{	
			 if (principal instanceof UserDetails)
				username = ((UserDetails) principal).getUsername();
			Date date1 = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			User userdetails = userrepository.getUserDetail(username);
			if(id !=0 && (request.getParameter("id") !=null))
			dustestresults.setId(id);
			
			dustestresults.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
			dustestresults.setDate_of_completion(date);
			dustestresults.setCreatedby(userdetails.getId());
			dustestresults.setCreatedon(ts);
			dustestresults.setCreatedbyip(request.getRemoteAddr());
			//if(fileUpload[i].getOriginalFilename() == "" || fileUpload[i].getOriginalFilename() == null || fileUpload[i].getOriginalFilename() ==" " || fileUpload[i].getOriginalFilename().trim() =="" ||fileUpload[i].getOriginalFilename().equals("null"))
			//	   continue;
			System.out.println("Printing no times="+fileUpload.getSize());
				   if (fileUpload.getSize() >=0) 
				   {		
				   		StringBuilder filePath = new StringBuilder(
				   		UPLOAD_FILEPATH+"PPVFRA/PROCESS/DUSTESTRESULT/"+applicationid); 
				   		File file = new File(filePath.toString());
				   		if (!file.exists()) 
				   		{
				   			file.mkdirs();
				   		}
				   		
				   		if(fileUpload.isEmpty()) 
						{
						String filename=request.getParameter("file_dustest");
						System.out.println("Printing filename 857 ="+filename);
						if(filename!=null && !(filename ==""))
						{
							System.out.println("Printing in 860 = "+filename);
							dustestresults.setReportname(filename);
					   		dustestresults.setReportpath(UPLOAD_FILEPATH+"PPVFRA/PROCESS/DUSTESTRESULT/"+applicationid+"/"+filename);
					   
						}
					}
					else
					{
				   		String filename=	fileUpload.getOriginalFilename().substring(0, fileUpload.getOriginalFilename().lastIndexOf("."));
				   		String fileext=	fileUpload.getOriginalFilename().substring(fileUpload.getOriginalFilename().lastIndexOf("."), fileUpload.getOriginalFilename().length() );
				   		String ffname= (filename.replace(" ", "_")).trim();
				   		String originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+fileext;
				   		File actFile = new File(filePath.append(File.separator + originalName).toString());
				   		try {
							Files.copy(fileUpload.getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   		dustestresults.setReportname(originalName);
				   		dustestresults.setReportpath(UPLOAD_FILEPATH+"PPVFRA/PROCESS/DUSTESTRESULT/"+applicationid+"/"+originalName);
				   }
				  }  		
				   
			if(request.getParameter("editmode").equals("1"))
			{
				if(!(request.getParameter("radio").equals(null) || 
						request.getParameter("radio").equals("")))
				{
					System.out.println("Trace inside 889 ");
				String rad=request.getParameter("radio");
				dustestresults.setDus_test_completed(rad);
				}
								
			}
				 Remarks r = new Remarks();
					r.setApplicationid(applicationid);
					r.setRemarks(request.getParameter("remarks"));
					r.setStatus(32);
					r.setCreatedby(userdeail.getId());
					r.setCreatedbyip(request.getRemoteAddr());
					r.setCreatedon(ts);
					remarksrepository.save(r);
					
			//Adding here ends on 30-12-2019
			Boolean savedsuccess = dustestresultsservice.save(dustestresults);
					   
			if (savedsuccess)
			{
				redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
			}
			else
			{
				redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
			}
				 
		} 
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/nationalregister");
			activitylogservice.save(act);
		
		
		return "redirect:/getactions/"+applicationid;
		
	}
	
	@RequestMapping(value = "/applicationstsseedreceived", method = RequestMethod.POST)
	public String asda(@Valid @ModelAttribute(value = "applicationstsseedreceived") ApplicationStsSeedrecieved applicationstsseedreceived,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
			String username="";
			long applicationid=Long.parseLong(request.getParameter("applicationid"));
			
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
			 } 
			else 
			{
				  username = principal.toString();
			}
				Long checkedby = (long) userdeail.getId();
				String remarks = request.getParameter("remarks");
				int savedsuccess = applicationstsseedrepository.updateApplicationStsSeedrecieved(checkedby,remarks,applicationid);
				if (savedsuccess !=0)
				{
					
						Remarks r = new Remarks();
						r.setApplicationid((int)applicationid);
						r.setRemarks(remarks);
						r.setStatus(32);
						r.setCreatedby(userdeail.getId());
						r.setCreatedbyip(request.getRemoteAddr());
						r.setCreatedon(ts);
						remarksrepository.save(r);
						
						//Adding here ends on 30-12-2019
						ActivityLogTable act = new ActivityLogTable();
						act.setIpaddress(request.getRemoteAddr());
						act.setActivityby(userdeail.getUsername());
						Date dt = new Date();
						System.out.println("Current date Is ="+dt);
						act.setLogin_time_stamp(dt);
						act.setActivity("save");
						act.setUrl("/applicationscrutiny");
						activitylogservice.save(act);
						System.out.println("inside test forward");
					redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
				}
				else
				{
					redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
				}
				
		
		return "redirect:/getactions/"+applicationid;
		
	}
	
	 
	@RequestMapping(value = "/deficency_report", method = RequestMethod.POST)
	public String defi_report(@Valid @ModelAttribute(value = "applicationscrutiny") ApplicationScrutiny applicationscrutiny,
			 Model model,HttpServletRequest request,RedirectAttributes redirectAttributes
			,@RequestParam MultipartFile[] fileUpload) throws IOException {
		String username="";
		System.out.println("deficency_report INSIDE 11111111111111");
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
		
		Date date1 = new Date();
		Timestamp ts = new Timestamp(date1.getTime());
		
		User userdeail = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			  username = ((UserDetails) principal).getUsername();
			  userdeail = userrepository.getUserDetail(username);
		 
			
		 } else {
			  username = principal.toString();
		 }
	if(request.getParameter("submit_defi_report") != null)
			{
				 Boolean savedsuccess=false;
				 String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
				 String data_scr_remarks="";
				 Date last_date_sub= null;
		 //String date_of_completion = request.getParameter("date_of_completion");
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date = null;
				
				String id [] = request.getParameterValues("id");
				System.out.println("PP IDDDD"+id+"ID LENGTH ="+id.length);
				data_scr_remarks= applicationrepository.saved_data_scrutiny_remarks(applicationid);
				last_date_sub= applicationrepository.saved_data_scrutiny_lastdate(applicationid);
				
			for(int j = 0; j< id.length ; j++)
			 {	
				String originalName ="";
				String pathvar="";
				 ApplicationScrutiny applicationscrutiny1 = new  ApplicationScrutiny();
				
				String comp[] = request.getParameterValues("complaince");
			System.out.println("PRINTING COMP="+comp);
			
				/* for(int i=0; i< fileUpload.length ; i++) 
				 {*/	
			 
			if (principal instanceof UserDetails)
			username = ((UserDetails) principal).getUsername();
			
			
			User userdetails = userrepository.getUserDetail(username);
			if(id[j]!=null && id[j] !="0")
			{
				applicationscrutiny1.setId(Integer.parseInt(id[j]));
			}
			applicationscrutiny1.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
			
			//applicationscrutiny1.setDateof_submission(applicationscrutiny.getDateof_submission());
			if(last_date_sub!=null)
			applicationscrutiny1.setDateof_submission(last_date_sub);
			if(data_scr_remarks!= null)
			applicationscrutiny1.setRemarks_doc_scrutiny(data_scr_remarks);
			applicationscrutiny1.setCreatedby(userdetails.getId());
			applicationscrutiny1.setCreatedon(ts);
			applicationscrutiny1.setCreatedbyip(request.getRemoteAddr());
			applicationscrutiny1.setComplaince(comp[j]);
			ApplicationScrutiny apsdata = applicationscrutinyrepository.getOne(Integer.parseInt(id[j]));
			 System.out.println("Printing asts as ="+apsdata);
			 String observ = apsdata.getObservation();
			 int categ= apsdata.getCategory();
			 
			System.out.println("Printing data"+j+" ="+observ+" and Categ "+categ);
			applicationscrutiny1.setCategory(categ);
			applicationscrutiny1.setObservation(observ);
			
			if(fileUpload[j].getOriginalFilename() == "" || 
					fileUpload[j].getOriginalFilename() == null ||
					fileUpload[j].getOriginalFilename() ==" " || 
					fileUpload[j].getOriginalFilename().trim() =="" || 
					fileUpload[j].getOriginalFilename().equals("null")
					)
			continue;
			
			System.out.println("FILE UPLOAD PPR LOOP="+j);
			System.out.println("FILE UPLOAD PPR="+fileUpload[j].getSize());
			if (fileUpload[j].getSize() >0) 
			 {
				System.out.println("COMING INSIDE IF NOT ZERO"+fileUpload[j].getSize());
	   		StringBuilder filePath = new StringBuilder(
	   		UPLOAD_FILEPATH+"PPVFRA/PROCESS/DEFICIENCY_REPORT/"+applicationid+"/"); 
	   		File file = new File(filePath.toString());
	   		if (!file.exists()) 
	   		{
	   			file.mkdirs();
	   		}
	   		System.out.println("PRINTING J AS="+j);
	   		String filename=	fileUpload[j].getOriginalFilename().substring(0, fileUpload[j].getOriginalFilename().lastIndexOf("."));
	   		String fileext=	fileUpload[j].getOriginalFilename().substring(fileUpload[j].getOriginalFilename().lastIndexOf("."), fileUpload[j].getOriginalFilename().length() );
	   		String ffname= (filename.replace(" ", "_")).trim();
	   		 originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+fileext;
	   		File actFile = new File(filePath.append(File.separator + originalName).toString());
	   		try {
				Files.copy(fileUpload[j].getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   		System.out.println("PRINTING F NAME AS="+originalName);
	   		applicationscrutiny1.setDocumentname(originalName);
	   		pathvar =UPLOAD_FILEPATH+"PPVFRA/PROCESS/DEFICIENCY_REPORT/"+applicationid+"/"+originalName;
	   		//applicationscrutiny.setDocumentpath(UPLOAD_FILEPATH+"PPVFRA/PROCESS/DEFICIENCY_REPORT/"+applicationid+"/"+originalName);
	   		applicationscrutiny1.setDocumentpath(pathvar);
			 }
			 
			savedsuccess = applicationscrutinyservice.save(applicationscrutiny1);
		 originalName ="";
		 pathvar="";
		 
		 
				 }
			if (savedsuccess)
			{
				redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
			}
			else
			{
				redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
			}
			 

			 /*
			 Umesh Adding here on 30-12-2019 for remarks	
			 */
				/* Umesh Adding here on 30-12-2019------
	               * for adding remarks in application_remarks
	               */
				
				if(savedsuccess)
				{
					Remarks r = new Remarks();
					r.setApplicationid((int)applicationid);
					r.setRemarks("Deficiency Report Submitted");
					r.setStatus(32);
					r.setCreatedby(userdeail.getId());
					r.setCreatedbyip(request.getRemoteAddr());
					r.setCreatedon(ts);
					remarksrepository.save(r);
				}
					
			//Adding here ends on 30-12-2019
	 
			}
	if(request.getParameter("submit_defi_report_final") != null)
	{
		System.out.println("Iniside Final Submit Date CLAUSE");
		int success1=applicationscrutinyrepository.updateApplicationScrutiny_finalsubmit(ts,Integer.parseInt(request.getParameter("applicationid")));
		
		 if(success1 == 1)
			{
				Remarks r = new Remarks();
				r.setApplicationid((int)applicationid);
				r.setRemarks("Final Deficency Report Submitted");
				r.setStatus(32);
				r.setCreatedby(userdeail.getId());
				r.setCreatedbyip(request.getRemoteAddr());
				r.setCreatedon(ts);
				remarksrepository.save(r);
			}
		 String assigned_add="";
		 String assigned_username="";
		 String assigned_userdesig="";
		 String sender_username="";
		 String sender_userdesig="";
		 
		 
		 
		 if(success1 ==1)
		 {
			 List<Object[]> getscrdata=applicationscrutinyrepository.getscrdetails(userdeail.getId());
			 if(getscrdata !=null)
			 {
				 Object[] r= (Object[]) getscrdata.get(0);
				 assigned_add= String.valueOf(r[1]);
				 assigned_username= String.valueOf(r[2]);
				 assigned_userdesig= String.valueOf(r[3]);
				 sender_username= String.valueOf(r[5]);
				 sender_userdesig= String.valueOf(r[6]);
				 
			 }
			sendmail_deficiencyreport(assigned_add,assigned_username,assigned_userdesig,sender_username,sender_userdesig); 
		 }
		System.out.println("Iniside Final Submit Date CLAUSE ENDS ----");
	}
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/applicationscrutiny");
			activitylogservice.save(act);
		
		
		return "redirect:/getactions/"+applicationid;
		
	}
///Umesh At Mail Sending For Deficiency Report
	
	boolean sendmail_deficiencyreport(String assignedmail, String assignedusername 
			,String assigneddesignation,String sendername,String senderdesignation) throws IOException {
		boolean valreturn = false;

		try {
			new Thread(new Runnable() {
			
				public void run() {
					try {
						System.out.println("Printing mime message deffffffiiiiiiii"+assignedmail);
						MimeMessage mimeMessage = new MailCommons().mimeMessage();
						mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(assignedmail));
						System.out.println("Printing mime message 2");
						mimeMessage.setSubject("Deficiency Report Submitted");

						StringBuilder sb = new StringBuilder();
						System.out.println("Printing mime message3");
						sb.append("Dear " + assignedusername + ",");
						//sb.append("<br/><br/>Ms " + orgname
						sb.append("<br/><br/>The Deficency Report is finally submitted please verify. ");
						sb.append("<br/><br/>Thank You<br/>"+sendername+"<br/>"+senderdesignation+"<br/>Team PPVFRA");

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
	
	
	
////******Deficiency Report Ends	
	
	
	
	
	@RequestMapping(value = "/save_acknowledge_letter", method = RequestMethod.POST)
	public String save_acknowledge_letter(  @ModelAttribute(value = "application_ack") Application  application_ack,
			  Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username="";
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
		String ackno = request.getParameter("ackno");
		String fno = request.getParameter("fno");
		String ackdate = request.getParameter("ackdate");
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
		Date nn = null;
		try {
			nn= ss.parse(ackdate);
		}catch(Exception e){}
		
		System.out.println("Printing ="+applicationid+"ACK="+ackno+"fno="+fno+"ack date="+nn);
		
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
			 } 
			else 
			{
				  username = principal.toString();
			}
				Long checkedby = (long) userdeail.getId();
				String remarks = request.getParameter("remarks");
				//applicationstsseedrepository.updateApplicationStsSeedrecieved(checkedby,remarks,applicationid);
				
				 List<Object> finan_year=applicationrepository.getdate_data();
			     List<Object> seqno     =applicationrepository.getseq_no((String) (finan_year.get(0)));
			     Integer sqno =0;
			     String ffyear="";
			     String ackno1="";
			     String fno_n1 ="";
			     Integer sqno_year=0;
			     Integer financial_y_file=0;
			     Integer file_sqno=0;
			    
			      System.out.println("getting the financial year as ="+finan_year);
			
				sqno =(Integer) (seqno.get(0));
				ffyear = (String) (finan_year.get(0));
				 System.out.println("Printing sequence no="+sqno+"PRINTING FF YEAR AS ="+ffyear);
				 SeqNo ss_no = new SeqNo();
				 
				 ss_no.setId(sqno);
				 ss_no.setFyear(ffyear);
				sequencerepository.save(ss_no);
				
			 ackno1="Reg/"+finan_year.get(0)+"/"+sqno;
			 
			 
			 
					
				
			      
				System.out.println("ACK NO="+ackno1);
			     
				//Commented By Umesh On 14-11-2019
				//applicationrepository.updateApplicationrep(ackno,fno,applicationid,nn);
				List<Object> finan_year_data=applicationrepository.getdate_data_data();
				financial_y_file = (Integer) (finan_year_data.get(0));
				System.out.println("Printing the fyear for file="+financial_y_file);
				List<Object[]> fileno_data_getting = applicationrepository.getfiledata(applicationid);
				System.out.println("Printing the filedata as ="+Arrays.toString(fileno_data_getting.get(0)));
				for(int y= 0 ; y< fileno_data_getting.size() ;y++)
				{
					if(y==0)
					{
					Object[] row = (Object[]) fileno_data_getting.get(y);
					System.out.println("Printing the row as = "+row);
					String btn_name= String.valueOf(row[6]);
					if(btn_name == null || btn_name == "null")
					{
						String [] chartosplit = String.valueOf(row[2]).split(" ");
						System.out.println("Trace after spliting = "+chartosplit);
						btn_name="";
						for (int km =0;km< chartosplit.length;km++)
						{
							String mword = chartosplit[km];
							mword.toUpperCase();
							btn_name= btn_name+mword.charAt(0)+" ";
						}
					}
					
					String vartype= String.valueOf(row[5]);
					String vartype1=String.valueOf(row[4]);
					if(vartype1.equals("EDV"))
						vartype="ED";
					
					System.out.println("Printing the values as "+btn_name+" var type="+vartype);
					List<Object> fdatagetter =applicationrepository.getdata_for_filenogeneration(btn_name.trim(), vartype, financial_y_file); 
					file_sqno =(Integer) fdatagetter.get(0);
					System.out.println("Printing the values just before saving");
					System.out.println("Printing the values as= "+btn_name+" var type= "+vartype+"financial_y_file ="+financial_y_file+"file_sqno="+file_sqno);
					fno_n1=vartype+""+file_sqno+" "+btn_name+""+ file_sqno+" "+financial_y_file.toString().substring(2)+" "+file_sqno;
					System.out.println("Printing the fileno generated is ="+fno_n1);
					File_No fn = new File_No(btn_name,vartype,financial_y_file,file_sqno);
					filenorepository.save(fn);
					
					}
				}
				
				
			
	//PAGE MAKING IN PDF ================
				
				String username1="";
				String address="";
				String deno="";
				String botn="";
				String dated="";
				String cname="";
				
				List<Object[]> acknowledgedetails=applicationrepository.getackdetails(applicationid);
				if(acknowledgedetails!= null && acknowledgedetails.size() >0)
				{
					model.addAttribute("acknowledgedetails",acknowledgedetails);
					for(int w = 0 ; w<acknowledgedetails.size() ; w++)
					{
						Object[] rw = (Object[]) acknowledgedetails.get(w);
						  username1 = (String) String.valueOf(rw[5]);
						  address = (String) String.valueOf(rw[6])+" "+String.valueOf(rw[7])+" "+String.valueOf(rw[8]);
						  deno = (String) String.valueOf(rw[9]);
						  botn = (String) String.valueOf(rw[10]);
						  //dated = (String) String.valueOf(rw[11]);
						 dated = ackdate;
						 cname= (String) String.valueOf(rw[12]);
						 System.out.println("Printing Crop name="+cname);
								 
					}
				}
			
				String view ="";
				    {
				    	
				    ///System.out.println("Inside view Memorandom");
					String fname="";
					String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
					try {
						String FILE="";
						

				    	
				    	StringBuilder filePath = new StringBuilder( 
								UPLOAD_FILEPATH+"PPVFRA/PROCESS/Acknowledge");
						
						File file = new File(filePath.toString());
						if (!file.exists()) {
							file.mkdirs();
						}
						
						Document document = new Document();
						
						
							  FILE = UPLOAD_FILEPATH+"PPVFRA/PROCESS/Acknowledge/"+applicationid+".pdf";
						
						
					    PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(FILE));
						document.open();
						System.out.println("start pdf process");
						Font font   = FontFactory.getFont(FontFactory.COURIER, 18, BaseColor.BLACK);
						Font font10 = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
						Font font12 = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
						Font font14 = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
						Paragraph mainhead=new Paragraph("Government of India",font);
						Paragraph mainhead1=new Paragraph("Office Of The Registrar",font10);
						Paragraph mainhead2=new Paragraph("Protection of Plant Varieties and Farmers Rights Authority",font10);
						Paragraph mainhead3=new Paragraph("NASC Complex, DPS Marg,",font10);
						Paragraph mainhead4=new Paragraph("Opposite Todapur Village, New Delhi - 110012",font10);
						Paragraph mainhead5=new Paragraph("ACKNOWLEDGEMENT RECEIPT",font10);
						//Paragraph mainhead=new Paragraph("FORM-"+ftypee,font);
						mainhead.setAlignment(Element.ALIGN_CENTER);
						mainhead1.setAlignment(Element.ALIGN_CENTER);
						mainhead2.setAlignment(Element.ALIGN_CENTER);
						mainhead3.setAlignment(Element.ALIGN_CENTER);
						mainhead4.setAlignment(Element.ALIGN_CENTER);
						mainhead5.setAlignment(Element.ALIGN_CENTER);
						try {
							 document.add(mainhead);
							 document.add(mainhead1);
							 document.add(mainhead2);
							 document.add(mainhead3);
							 document.add(mainhead4);
							 document.add(mainhead5);
							 document.add(new Paragraph("___________________________________________________________________________ "));
							 
							 
							 Paragraph paradate=new Paragraph("Application No."+ackno1);
							 paradate.setAlignment(Element.ALIGN_RIGHT);
							 document.add(paradate);
							 document.add(new Paragraph(" "));
							 document.add(new Paragraph(" "));
						 
					/*
					 * Paragraph para3=new Paragraph("To,");
					 * para3.setAlignment(Element.ALIGN_JUSTIFIED); document.add(para3);
					 */
							 Paragraph paraorg=new Paragraph("The receipt of the application from " +username1+ "   address: " +address,font10);
							 Paragraph paraorg1=new Paragraph(" for registration of plant variety ",font10);
							 
							 document.add(paraorg);
							 document.add(paraorg1);
							 
							 Paragraph pararr=new Paragraph("in respect of denomination "+deno+" Crop (Botanical name) "+cname+"   ("+botn+")   is"
							 		+ " acknowledged in the office of th Registrar, PPV&FRA Authority on dated "+dated ,font10);
							 document.add(pararr);
							 //para3.setAlignment(Element.ALIGN_JUSTIFIED);
							
							 
							 document.add(new Paragraph(" "));
							 
							 	
							  
							 Paragraph ins=new Paragraph("Seal of Registrar's Office",font10);
							 ins.setAlignment(Element.ALIGN_LEFT);
							 
							 document.add(ins);

							 Paragraph ins1=new Paragraph("Signature on behalf of Registrar",font10);
							 ins1.setAlignment(Element.ALIGN_RIGHT);
							 
							 document.add(ins1);
							  document.add(new Paragraph("___________________________________________________________________________ "));
							 document.add(new Paragraph(" "));
							 document.add(new Paragraph(" "));
							  
							 
							 Paragraph office_copy=new Paragraph("Office Copy",font10);
							 office_copy.setAlignment(Element.ALIGN_LEFT);
							 
							 document.add(office_copy);
							 
							 
							 Paragraph paradate1=new Paragraph("Application No."+ackno1);
							 paradate1.setAlignment(Element.ALIGN_RIGHT);
							 document.add(paradate1);
							 
							 document.add(new Paragraph(" "));
							 

							 
							 Paragraph paradown=new Paragraph("The receipt of the application from "+username1+" address : "+address,font10);
							 Paragraph paradown1=new Paragraph(" for registration of plant variety ",font10);
							 
							 document.add(paradown);
							 document.add(paradown1);
							 
							 Paragraph pararrdown=new Paragraph("in respect of denomination "+deno+" Crop (Botanical name)  " +cname+"   ("+botn+")  is "
							 		+ "acknowledged in the office of th Registrar, PPV&FRA Authority on dated "+dated,font10);
							 document.add(pararrdown);
							 //para3.setAlignment(Element.ALIGN_JUSTIFIED);
							
							 document.add(new Paragraph(" "));
							 document.add(new Paragraph(" "));
							 
							 	
							  
							 Paragraph insdown=new Paragraph("Seal of Registrar's Office",font10);
							 insdown.setAlignment(Element.ALIGN_LEFT);
							 
							 document.add(insdown);

							 Paragraph insdown1=new Paragraph("Signature on behalf of Registrar",font10);
							 insdown1.setAlignment(Element.ALIGN_RIGHT);
							 
							 document.add(insdown1);
							 
							 document.add(new Paragraph(" "));
							 
						 }catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("document created");
						 
						 int hash1=document.hashCode();
						 System.out.println("hashcode1:"+hash1);
						document.close();
						writer.close();
						 
						 
						{
							 view = UPLOAD_FILEPATH+"PPVFRA/PROCESS/Acknowledge/"+applicationid+".pdf";
						  	fname="PPVFRA/PROCESS/Acknowledge/"+applicationid+".pdf";
						  	System.out.println("Processing view as =" +view);
						 } 
						
					}catch(IOException | DocumentException  es) {es.printStackTrace();}
					
					//Umesh Commenting to update fileno on 20-11-2019
					//applicationrepository.updateApplicationrep(ackno1,fno,applicationid,nn);
					
					applicationrepository.updateApplicationrep(ackno1,fno_n1,applicationid,nn,view);
					
					/*
					 Umesh Adding here on 30-12-2019 for remarks	
					 */
						/* Umesh Adding here on 30-12-2019------
			               * for adding remarks in application_remarks
			               */
						Date date = new Date();
						Timestamp ts = new Timestamp(date.getTime());
						
							Remarks r = new Remarks();
							r.setApplicationid(applicationid);
							r.setRemarks("SendAcknowlegement Letter Processed");
							r.setStatus(32);
							r.setCreatedby(userdeail.getId());
							r.setCreatedbyip(request.getRemoteAddr());
							r.setCreatedon(ts);
							remarksrepository.save(r);
							
					//Adding here ends on 30-12-2019
							
					String fname_1= null;
					fname_1 = applicationid+".pdf";
					model.addAttribute("fname_1",fname_1);
					model.addAttribute("fname",fname);
				}
				
			 
				
				
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/applicationscrutiny");
			activitylogservice.save(act);
		
		
		return "redirect:/getactions/"+applicationid;
		
	}
	
	
	@RequestMapping(value = "/acknowledgement/{applicationid}", method = RequestMethod.GET)
	public String save_acknowledge_letter_display( Model model,  HttpServletRequest request,RedirectAttributes redirectAttributes ,@PathVariable(name="applicationid") int applicationid) 
	{	int userid = 0;
		 String username="";
		int applicationid1=applicationid;
		String ackno = request.getParameter("ackno");
		String fno = request.getParameter("fno");
		String ackdate = request.getParameter("ackdate");
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
		Date nn = null;
		try {
			nn= ss.parse(ackdate);
		}catch(Exception e){}
		
		System.out.println("Printing ="+applicationid+"ACK="+ackno+"fno="+fno+"ack date="+nn);
		
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				  userid = userdeail.getId(); 
			 } 
			else 
			{
				  username = principal.toString();
			}
			List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			model.addAttribute("modulelist", modulelist);
			
			List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			model.addAttribute("modulelist2", modulelist2);
			
			Long checkedby = (long) userdeail.getId();
				String remarks = request.getParameter("remarks");
			
				List<Object[]> acknowledgedetails=applicationrepository.getackdetails(applicationid);
				model.addAttribute("acknowledgedetails",acknowledgedetails);
				
				
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/applicationscrutiny");
			activitylogservice.save(act);
		
		
		return "admin/acknowledgement";
		
	}
	
	
	@RequestMapping(value = "/send_letter/{applicationid}", method = RequestMethod.GET)
	public String send_acknowlegement_letter( Model model,  HttpServletRequest request,RedirectAttributes redirectAttributes ,@PathVariable(name="applicationid") int applicationid) throws IOException 
	{	int userid = 0;
		 String username="";
		int applicationid1=applicationid;
		String ackno = request.getParameter("ackno");
		String fno = request.getParameter("fno");
		String ackdate = request.getParameter("ackdate");
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
		Date nn = null;
		try {
			nn= ss.parse(ackdate);
		}catch(Exception e){}
		
		System.out.println("Printing ="+applicationid+"ACK="+ackno+"fno="+fno+"ack date="+nn);
		
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				  userid = userdeail.getId(); 
			 } 
			else 
			{
				  username = principal.toString();
			}
			List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			model.addAttribute("modulelist", modulelist);
			
			List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			model.addAttribute("modulelist2", modulelist2);
			
			Long checkedby = (long) userdeail.getId();
				String remarks = request.getParameter("remarks");
				
	String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");	
	
	List<Object[]> obj_n = applicationrepository.getmail_data(applicationid);
	Object[] r = (Object[]) obj_n.get(0);
	String username_mail = String.valueOf(r[5]);
	String emailadd =String.valueOf(r[7]); 
	System.out.println("Printing username to mail="+username_mail);
	//Uncomment for checkingmail
	//username_mail = "amit242042@gmail.com";
				
	String pathvar =UPLOAD_FILEPATH+"PPVFRA/PROCESS/Acknowledge";
	String fname= applicationid+".pdf";
    System.out.println("Printing username to mail="+username_mail+"pathvar="+pathvar+"file name="+fname+"applicationid="+applicationid);
		   	
    
    List<Object[]> acknowledgedetails=applicationrepository.getackdetails(applicationid);
	String s11="";
	String s12="";
	String s13="";
	String s14="";
	String s15="";
	String s16="";
	String s17="";
	String s18="";
	
    if(acknowledgedetails != null)
	{
		Object[] r2 = (Object[]) acknowledgedetails.get(0);
		s11 = String.valueOf(r2[5]);
		s12 = String.valueOf(r2[6]);
		s13 = String.valueOf(r2[7]);
		s14 = String.valueOf(r2[8]);
		s15 = String.valueOf(r2[9]);
		s16 = String.valueOf(r2[12]);
		s17 = String.valueOf(r2[10]);
		s18 = String.valueOf(r2[11]);
	}
				try {
					sendmail(emailadd,username_mail,pathvar,fname,(long)applicationid,s11,s12,s13,s14,s15,s16,s17,s18);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/applicationscrutiny");
			activitylogservice.save(act);
		
		
			 return "redirect:/getactions/"+applicationid;
		
	}
	
	boolean sendmail(String emailadd,String fullname, String UPLOAD_FILEPATH1, 
			String FILEname, long applicationid,String s11,String s22,String s33,String s44,String s15,String s16,String s17,String s18) throws IOException {
		boolean valreturn = false;

		try {
			new Thread(new Runnable() {
			
				public void run() {
					try {
						System.out.println("Printing mime message");
						MimeMessage mimeMessage = new MailCommons().mimeMessage();
						mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailadd));
						System.out.println("Printing mime message 2");
						mimeMessage.setSubject("Acknowledgement");

						StringBuilder sb = new StringBuilder();
						System.out.println("Printing mime message3");
						sb.append("Dear " + fullname + ",");
						//sb.append("<br/><br/>Ms " + orgname
						sb.append("<br/><br/>The receipt of the application from" + s11+" address:  "+s22+"  "+s33+"  "+s44+ " for registration of"
								+ "<br/>plant variety in respect of denomination" + s15+"  Crop (Botanical name)"  +s16+ " ("+s17+") is "
								+ "<br/>acknowledged in the office of the Registrar, PPV&FRA Authority on dated "+s18);
						sb.append("<br/><br/>Thank You<br/>Team PPVFRA");

						mimeMessage.setContent(sb.toString(), "text/html; charset=utf-8");
						BodyPart messageBodyPart = new MimeBodyPart();
						/* messageBodyPart.setText("This is message body"); */
						messageBodyPart.setContent(sb.toString(), "text/html; charset=utf-8");
						Multipart multipart = new MimeMultipart();
						multipart.addBodyPart(messageBodyPart);

						messageBodyPart = new MimeBodyPart();
						String filename = UPLOAD_FILEPATH1 + "/" + FILEname;
						// C:/AYUSH_Files/pdf/80SiteInspection24D.pdf";
				System.out.println("Printing mime message4");
						DataSource source = new FileDataSource(filename);
						messageBodyPart.setDataHandler(new DataHandler(source));
						// messageBodyPart.setFileName(FILEname);
				System.out.println("Printing mime message5");
				System.out.println("Printing filename="+filename+"WITH FILE="+FILEname);
						messageBodyPart.setFileName(FILEname);
						multipart.addBodyPart(messageBodyPart);
						System.out.println("Printing mime message6");
						mimeMessage.setContent(multipart);
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
	
	
	
	
	@RequestMapping(value = "/publishtopvj", method = RequestMethod.POST)
	public String addPublishToPVJ(@Valid @ModelAttribute(value = "publishtopvj") PublishToPVJ publishtopvj,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username="";
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
		
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
					 //System.out.println("Priting else Loggin user: " + username);
				} 
				/// end Getting Logged in user
				
				
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  model.addAttribute("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  model.addAttribute("modulelist2", modulelist2);
		 
          return "applicationprocessing/action.html";
          
		} 
		else 
		{ 
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
			publishtopvj.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
			publishtopvj.setCreatedby(userdetails.getId());
			publishtopvj.setCreatedon(ts);
			publishtopvj.setCreatedbyip(request.getRemoteAddr());
			
				 Remarks r = new Remarks();
					r.setApplicationid(applicationid);
					r.setRemarks("Publish To PVJ Process");
					r.setStatus(32);
					r.setCreatedby(userdeail.getId());
					r.setCreatedbyip(request.getRemoteAddr());
					r.setCreatedon(ts);
					remarksrepository.save(r);
			Boolean savedsuccess = publishtopvjservice.save(publishtopvj);
			if (savedsuccess)
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
			act.setUrl("/publishtopvj");
			activitylogservice.save(act);
		}
		return "redirect:/getactions/"+applicationid;
		
	}
	
	
		@RequestMapping(value = "/journal", method = RequestMethod.POST)
		public String addJournal(@Valid @ModelAttribute(value = "journal") Journal journal,
			BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username="";
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
		
		if (bindingResult.hasErrors()) {
			/// Getting Logged in user
			int userid = 0;
			System.out.println("PritingeeLoggin user: " + username);
			
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					 username = ((UserDetails) principal).getUsername();
					User userdeail = userrepository.getUserDetail(username);
					userid = userdeail.getId();
					
			 } else {
					 username = principal.toString();
					 //System.out.println("Priting else Loggin user: " + username);
				} 
				/// end Getting Logged in user
				
				
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  model.addAttribute("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  model.addAttribute("modulelist2", modulelist2);
		 
          return "applicationprocessing/action.html";
          
		} 
		else 
		{ 
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
			journal.setCreatedby(userdetails.getId());
			journal.setCreatedon(ts);
			journal.setCreatedbyip(request.getRemoteAddr());
            
			Boolean savedsuccess = journalservice.save(journal);
			if (savedsuccess)
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
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/journal");
			activitylogservice.save(act);
		}
		
		return "redirect:/getactions/"+applicationid;
		
	}

		@RequestMapping(value = "/rejuvenation", method = RequestMethod.POST)
		public String addRejuvenation(@Valid @ModelAttribute(value = "rejuvenation") Rejuvenation rejuvenation,
				BindingResult bindingResult, Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
			 

			String username="";
		 int id=0;  
			
		if(Integer.parseInt(request.getParameter("id"))!=0 && request.getParameter("id") != null)
			 id = Integer.parseInt(request.getParameter("id"));
			 
		
		/* id= Integer.parseInt(request.getParameter("id")); */
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
			String[] checkbox =request.getParameterValues("checkbox");
			String date_rejuvenation = request.getParameter("date_rejuvenation");
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
			Date date = null;
			try 
			{
				date = formatter1.parse(date_rejuvenation);
			} 
			catch(ParseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
				HashSet s = new HashSet();
				for(int k=0; k<checkbox.length;k++)
				{
					Rejuvenation r = new Rejuvenation();
					if(id != 0)
					{
						r.setId(id);
					}
					
					r.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
					r.setCandidatevariety(Integer.parseInt(checkbox[k]));
					r.setDenomination(request.getParameter("denomination"));
					r.setFindings(request.getParameter("findings"));
					r.setNo_of_packets_added_mts(Integer.parseInt(request.getParameter("no_of_packets_added_mts")));
					r.setTotal_packets_mts(Integer.parseInt(request.getParameter("total_packets_mts")));
					r.setType_line(Integer.parseInt(request.getParameter("type_line")));
					r.setPlace(request.getParameter("place"));
					r.setQuantity(Integer.parseInt(request.getParameter("quantity")));
					r.setDate_rejuvenation(date);
					r.setCandidatevariety_other(request.getParameter("othervalue"));
					System.out.println("trace=="+checkbox[k]);
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

					if (principal instanceof UserDetails)
						username = ((UserDetails) principal).getUsername();
					Timestamp ts = new Timestamp(date.getTime());
					User userdetails = userrepository.getUserDetail(username);
					
					r.setCreatedon(ts);
					r.setCreatedbyip(request.getRemoteAddr());
					s.add(r);
				}
				
				/*
				 Umesh Adding here on 30-12-2019 for remarks	
				 */
					/* Umesh Adding here on 30-12-2019------
		               * for adding remarks in application_remarks
		               */
					 Remarks r = new Remarks();
					  Timestamp ts = new Timestamp(date.getTime());
					  User userdeail = userrepository.getUserDetail(username);
						r.setApplicationid(applicationid);
						r.setRemarks("REJUVENATION PROCESS");
						r.setStatus(32);
						r.setCreatedby(userdeail.getId());
						r.setCreatedbyip(request.getRemoteAddr());
						r.setCreatedon(ts);
						remarksrepository.save(r);
						
				//Adding here ends on 30-12-2019
						
				 rejuvenationrepository.saveAll(s);
			
				 return "redirect:/getactions/"+applicationid;
		}
	 
		
		@RequestMapping(value = "/transferseedtomts", method = RequestMethod.POST)
		public String addTransferSeedToMTS(@Valid @ModelAttribute(value = "transferseedtomts") TransferSeedToMTS transferseedtomts,
				BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
			String username="";
			int applicationid=Integer.parseInt(request.getParameter("applicationid"));
			
		
			{ 
				System.out.println("tttt11111tssss");
				User userdeail = null;
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					  username = ((UserDetails) principal).getUsername();
					  userdeail = userrepository.getUserDetail(username);
				 
					
				 } else {
		 
	 
					  username = principal.toString();
	 
											   
													  
																									  
					
				}
							
					String seed_transfer_date = request.getParameter("seed_transfer_date");
					SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
					Date date = null;
					try 
					{
						date = formatter1.parse(seed_transfer_date);
					} 
					catch(ParseException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
				
						
				 if (principal instanceof UserDetails)
					username = ((UserDetails) principal).getUsername();
				Date date1 = new Date();
				Timestamp ts = new Timestamp(date.getTime());
				User userdetails = userrepository.getUserDetail(username);
				transferseedtomts.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
				transferseedtomts.setSeed_transfer_date(date);
				transferseedtomts.setCreatedby(userdetails.getId());
				transferseedtomts.setCreatedon(ts);
				transferseedtomts.setCreatedbyip(request.getRemoteAddr());
				
				
				/*
				 Umesh Adding here on 30-12-2019 for remarks	
				 */
					/* Umesh Adding here on 30-12-2019------
		               * for adding remarks in application_remarks
		               */
				 
				
					 Remarks r = new Remarks();
						r.setApplicationid(applicationid);
						r.setRemarks(request.getParameter("remarks"));
						r.setStatus(32);
						r.setCreatedby(userdetails.getId());
						r.setCreatedbyip(request.getRemoteAddr());
						r.setCreatedon(ts);
						remarksrepository.save(r);
						
				//Adding here ends on 30-12-2019
	            
				Boolean savedsuccess = transferseedtomtsservice.save(transferseedtomts);
				if (savedsuccess)
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
				act.setUrl("/transferseedtomts");
				activitylogservice.save(act);
			}
			
			return "redirect:/getactions/"+applicationid;
			
		}
		
		
		@RequestMapping(value = "/mediumtermstorage", method = RequestMethod.POST)
		public String addMediumTermStorage(@Valid @ModelAttribute(value = "mediumtermstorage") MediumTermStorage mediumtermstorage,
				BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes
				)  {
			String username="";
			int applicationid=Integer.parseInt(request.getParameter("applicationid"));
			{ 
				User userdeail = null;
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					  username = ((UserDetails) principal).getUsername();
					  userdeail = userrepository.getUserDetail(username);
				  } else {
					  username = principal.toString();
					
				}
				//if(request.getParameter("btnsubmit2")!=null)
				if(request.getParameter("btnsubmit2medium")!=null)
					{
					String postregistration = request.getParameter("postregistration");
					String handedoverto = request.getParameter("handedoverto");
					int no_of_packets = Integer.parseInt(request.getParameter("no_of_packets"));
					String handingoverdate = request.getParameter("handingoverdate");
					String handingover_remarks = request.getParameter("handingover_remarks");
					SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
					Date date1 = null;
					try 
					{
						date1 = formatter1.parse(handingoverdate);
					} 
					catch(ParseException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					int success = mediumtermstoragerepository.updateMediumTermStorage(postregistration,handedoverto,no_of_packets,date1,handingover_remarks,(long)applicationid);
					if(success >= 0) {
						redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
						 Remarks r = new Remarks();
						 Date date = new Date();
						 Timestamp ts = new Timestamp(date.getTime());
							r.setApplicationid(applicationid);
							r.setRemarks(handingover_remarks);
							r.setStatus(32);
							r.setCreatedby(userdeail.getId());
							r.setCreatedbyip(request.getRemoteAddr());
							r.setCreatedon(ts);
							remarksrepository.save(r);		
					//Adding here ends on 30-12-2019		
				}  
				if(request.getParameter("btnsubmit1")!=null)
				{
				 if (principal instanceof UserDetails)
					username = ((UserDetails) principal).getUsername();
				Date date = new Date();
				Timestamp ts = new Timestamp(date.getTime());
				User userdetails = userrepository.getUserDetail(username);
				
				String moisture_tobe_checked_on = request.getParameter("moisture_tobe_checked_on");
				String date_of_discard = request.getParameter("date_of_discard");
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date1 = null;
				Date date2 =null;
				try 
				{
					date1 = formatter1.parse(moisture_tobe_checked_on);
					date2= formatter1.parse(date_of_discard);
				} 
				catch(ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				mediumtermstorage.setApplicationid(Long.parseLong(request.getParameter("applicationid")));
				mediumtermstorage.setMoisture_tobe_checked_on(date1);
				mediumtermstorage.setDate_of_discard(date2);
				mediumtermstorage.setCreatedby(userdetails.getId());
				mediumtermstorage.setCreatedon(ts);
				mediumtermstorage.setCreatedbyip(request.getRemoteAddr());
	            
				
				Boolean savedsuccess = mediumtermstorageservice.save(mediumtermstorage);
				if (savedsuccess)
				{
					redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
				}
				else
				{
					redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
				}
				}
				ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("save");
				act.setUrl("/mediumtermstorage");
				activitylogservice.save(act);
			}
			
			return "redirect:/getactions/"+applicationid;
			
		}	
		
		@RequestMapping(value = "/editapplicationscrutiny/{id}", method = RequestMethod.GET)
		public ModelAndView editApplicationScrutiny(@PathVariable(name = "id") Integer id,HttpServletRequest request) {
			ModelAndView mav = new ModelAndView("applicationprocessing/edit_application_scrutiny");
			

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
		 
			
			ApplicationScrutiny applicationscrutiny = applicationscrutinyservice.get(id);
			mav.addObject("applicationscrutiny", applicationscrutiny);
			
			 
			
			//****Portion For Finding Application Process portion On 17-12-2019
			List<Object[]> pnames = rolemodulerepository.getProcessnamesviauserid(userid);
			 
			if(pnames != null && pnames.size()>0)
			{
				mav.addObject("pnames",pnames);
				 
				Object s2 = null;
				 if(pnames.contains("ApplicationScrutiny"))
				{
					s2= "ApplicationScrutiny";
					mav.addObject("ApplicationScrutiny",s2);
				}
				
				 
				
			}
		
			
			List<Object[]> category_data= mformsectionrepository.getMFormSection_f1();
			if(category_data.size()>0 && category_data!=null)
			   {
				mav.addObject("category_data",category_data);
				}

	//*****Ends Here *****************************	
			
				
				 ActivityLogTable act = new ActivityLogTable();
					act.setIpaddress(request.getRemoteAddr());
					act.setActivityby(userdeail.getUsername());
					Date dt = new Date();
					System.out.println("Current date Is ="+dt);
					act.setLogin_time_stamp(dt);
					act.setActivity("view");
					act.setUrl("/editapplicationscrutiny");
					
					activitylogservice.save(act);
				 
			mav.setViewName("applicationprocessing/edit_application_scrutiny");
			return mav;
		}	
		
		@RequestMapping(value = "/editrejuvenation/{id}", method = RequestMethod.GET)
		public ModelAndView editRejuvenation(@PathVariable(name = "id") Integer id,HttpServletRequest request) {
			ModelAndView mav = new ModelAndView("applicationprocessing/edit_rejuvenation");
			

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
		 
		  List<TypeLine> TypeLine = typelineservice.listall();
		  mav.addObject("TypeLine",TypeLine);
		  
		  List<CandidateVariety> CandidateVariety=candidatevarietyservice.listall();
		  mav.addObject("CandidateVariety",CandidateVariety);
		
		  Rejuvenation rejuvenation = rejuvenationservice.get(id);
		  mav.addObject("rejuvenation",rejuvenation);
		 List<Object[]> pnames = rolemodulerepository.getProcessnamesviauserid(userid);
		
			if(pnames != null && pnames.size()>0)
			{
				mav.addObject("pnames",pnames);
				 
				Object s15 = null;
				 
				 if(pnames.contains("Rejuvination"))
				{
					s15= "Rejuvination";
					mav.addObject("Rejuvination",s15);
				}
				
				 
				
			}
			
				 ActivityLogTable act = new ActivityLogTable();
					act.setIpaddress(request.getRemoteAddr());
					act.setActivityby(userdeail.getUsername());
					Date dt = new Date();
					System.out.println("Current date Is ="+dt);
					act.setLogin_time_stamp(dt);
					act.setActivity("view");
					act.setUrl("/editrejuvenation");
					
					activitylogservice.save(act);
				 
			mav.setViewName("applicationprocessing/edit_rejuvenation");
			return mav;
		}	
		
		
		@RequestMapping(value = "/issue_certificate", method = RequestMethod.POST)
		public String issuecertificate(  @ModelAttribute(value = "issue_cert") ApplicationPreGrantOpposition issue_certificate,
				BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) 
		{
			String username="";
			long applicationid=Long.parseLong(request.getParameter("applicationid"));
			int id =Integer.parseInt(request.getParameter("id"));
  						 
				User userdeail = null;
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					  username = ((UserDetails) principal).getUsername();
					  userdeail = userrepository.getUserDetail(username);
	 
	 
				 } 
				else 
				{
					  username = principal.toString();
				}
					Long checkedby = (long) userdeail.getId();
					String remarks = request.getParameter("remarks");
					
					Date date = new Date();
					Timestamp ts = new Timestamp(date.getTime());
					
					
					if(request.getParameter("editmode_issuecerty").equals("0"))
					{
					String ppgrant =issue_certificate.getIs_pre_grant_opposition_received();
					System.out.println("Pre grant rep 2449=== "+ppgrant);
					if(ppgrant.equals("Yes") && !(issue_certificate.getIs_pre_grant_opposition_received().equals("null"))) 
					{
						issue_certificate.setIs_pre_grant_opposition_received("Y");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
						Date date1 = null;
						String dicisiondate = request.getParameter("date_of_deceision");
						 
						try 
						{
							date1 = formatter1.parse(dicisiondate);
							 
						} 
						catch(ParseException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						issue_certificate.setDate_of_deceision(date1);
									
						String opstat=request.getParameter("opposition_status");
						if(issue_certificate.getOpposition_status().equals("R"))
						{
							System.out.println("Trace on Rejected selected case ");
						
						ApplicationCertificates ac = new ApplicationCertificates();  
						Date date4 = null;
						Date date2 = null;
						Date date3 = null;
						String issue_date = request.getParameter("date_of_issue");
						String certificateno_no = request.getParameter("certificateno");
						String startdateprotect = request.getParameter("startdate");
						String enddateprotect = request.getParameter("enddate");
						 int acsid = Integer.parseInt(request.getParameter("acsid"));
						 List<Object[]> issue_cert_date = applicationrepository.getissue_cert_data((int)applicationid);
							for(int y= 0 ; y< issue_cert_date.size() ;y++)
							{
								if(y==0)
								{
								Object[] row = (Object[]) issue_cert_date.get(y);
								String vartype= String.valueOf(row[2]);
								String cropdetail= String.valueOf(row[3]);
								 
								if(vartype.equals("New") && cropdetail.equals("Trees/Vines"))
								{ List<Object[]> ddfetched = applicationcertificaterepository.date_data();
									 // List<Object[]> ddfetched = applicationcertificaterepository.date_data();
										if(ddfetched.size()>0)
										{
											Object[] r = (Object[]) ddfetched.get(0);
											issue_date = String.valueOf(r[0]);
											startdateprotect = String.valueOf(r[0]);
											enddateprotect = String.valueOf(r[1]);
								}			
								}
								else if(vartype.equals("New") && cropdetail.equals("Others"))
								{ 
									List<Object[]> ddfetched = applicationcertificaterepository.date_data_others();
											if(ddfetched.size()>0)
											{
												Object[] r = (Object[]) ddfetched.get(0);
												issue_date = String.valueOf(r[0]);
												startdateprotect = String.valueOf(r[0]);
												enddateprotect = String.valueOf(r[1]);
									}			
								}
								else if(vartype.equals("Extant")||vartype.equals("Farmer")){ 
									
									List<Object[]> ddfetched = applicationcertificaterepository.date_data_others();
											if(ddfetched.size()>0)
											{
												Object[] r = (Object[]) ddfetched.get(0);
												issue_date = String.valueOf(r[0]);
												startdateprotect = String.valueOf(r[0]);
												enddateprotect = String.valueOf(r[1]);
									}			
									}
								
								}
							}
						  
						try 
						{
							date4 = formatter1.parse(issue_date);
							date2 = formatter1.parse(startdateprotect);
							date3 = formatter1.parse(enddateprotect);
						 } 
						catch(ParseException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					 
						Applications ap = new Applications();
						ap =applicationsrep.getOne((int)applicationid);
						int crop_id= ap.getCropid();
						String vtn="";
						
						 List<Object> vtname= applicationsrep.variety_name(crop_id,(int)applicationid);
						 if(vtname.size()>0)
						 {
						vtn= (String) vtname.get(0);
						System.out.println("Trace on 2549 ===== "+vtn);
						
						ac.setType(vtn);
						 }
						 
						if(acsid!=0)
						ac.setId(acsid);
						
						ac.setApplicationid(applicationid);
						ac.setCert_issue_date(date4);
						ac.setPeriod_of_protection(date2);
						ac.setProtection_expiry_date(date3);
						ac.setCertificate_serial_no(certificateno_no);
						ac.setCrop_id(crop_id);
						ac.setCreatedby(userdeail.getId());
						ac.setCreatedbyip(request.getRemoteAddr());
						ac.setCreatedon(ts);
						ac.setActive(true);
						
						issue_certificate.setCreatedby((long)userdeail.getId());
						issue_certificate.setCreatedbyip(request.getRemoteAddr());
						issue_certificate.setCreatedon(ts);
						issue_certificate.setActive(true);
						
						applicationpregrantrepository.save(issue_certificate);
						applicationcertificaterepository.save(ac);
						}else {						
						issue_certificate.setCreatedby((long)userdeail.getId());
						issue_certificate.setCreatedbyip(request.getRemoteAddr());
						issue_certificate.setCreatedon(ts);
						issue_certificate.setActive(true);
						applicationpregrantrepository.save(issue_certificate);
						}
					  }else if(ppgrant.equals("No") && !(issue_certificate.getIs_pre_grant_opposition_received().equals("null")))
						 {
						ApplicationCertificates ac = new ApplicationCertificates();
						
						issue_certificate.setIs_pre_grant_opposition_received("N");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
						Date date1 = null;
						Date date2 = null;
						Date date3 = null;
						String issue_date = request.getParameter("date_of_issue");
						String certificateno_no = request.getParameter("certificateno");
						String startdateprotect = request.getParameter("startdate");
						String enddateprotect = request.getParameter("enddate");
						 int acsid = Integer.parseInt(request.getParameter("acsid"));
						 List<Object[]> issue_cert_date = applicationrepository.getissue_cert_data((int)applicationid);
							for(int y= 0 ; y< issue_cert_date.size() ;y++)
							{
								if(y==0)
								{
								Object[] row = (Object[]) issue_cert_date.get(y);
								String vartype= String.valueOf(row[2]);
								String cropdetail= String.valueOf(row[3]);
								 
								if(vartype.equals("New") && cropdetail.equals("Trees/Vines"))
								{ List<Object[]> ddfetched = applicationcertificaterepository.date_data();
										if(ddfetched.size()>0)
										{
											Object[] r = (Object[]) ddfetched.get(0);
											issue_date = String.valueOf(r[0]);
											startdateprotect = String.valueOf(r[0]);
											enddateprotect = String.valueOf(r[1]);
								}			
								}
								else if(vartype.equals("New") && cropdetail.equals("Others"))
								{ 
									List<Object[]> ddfetched = applicationcertificaterepository.date_data_others();
										if(ddfetched.size()>0)
											{
												Object[] r = (Object[]) ddfetched.get(0);
												
												issue_date = String.valueOf(r[0]);
												startdateprotect = String.valueOf(r[0]);
												enddateprotect = String.valueOf(r[1]);
									}			
								}
								else if(vartype.equals("Extant")|| vartype.equals("Farmer") ){ 
									
									List<Object[]> ddfetched = applicationcertificaterepository.date_data_others();
										 // List<Object[]> ddfetched = applicationcertificaterepository.date_data();
											if(ddfetched.size()>0)
											{
												Object[] r = (Object[]) ddfetched.get(0);
												issue_date = String.valueOf(r[0]);
												startdateprotect = String.valueOf(r[0]);
												enddateprotect = String.valueOf(r[1]);
									}			
								}
									
								}
							}
						 
						try 
						{
							date1 = formatter1.parse(issue_date);
							date2 = formatter1.parse(startdateprotect);
							date3 = formatter1.parse(enddateprotect);
						 } 
						catch(ParseException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					 
						Applications ap = new Applications();
						ap =applicationsrep.getOne((int)applicationid);
						int crop_id= ap.getCropid();
						String vtn="";
						
						 List<Object> vtname= applicationsrep.variety_name(crop_id,(int)applicationid);
						 if(vtname.size()>0)
						 {
						vtn= (String) vtname.get(0);
						System.out.println("Trace on NO ==== 2653 "+vtn);
						ac.setType(vtn);
						 }
						 
						if(acsid!=0)
						ac.setId(acsid);
						
						ac.setApplicationid(applicationid);
						ac.setCert_issue_date(date1);
						ac.setPeriod_of_protection(date2);
						ac.setProtection_expiry_date(date3);
						ac.setCertificate_serial_no(certificateno_no);
						ac.setCrop_id(crop_id);
						ac.setCreatedby(userdeail.getId());
						ac.setCreatedbyip(request.getRemoteAddr());
						ac.setCreatedon(ts);
						ac.setActive(true);
						
						issue_certificate.setCreatedby((long)userdeail.getId());
						issue_certificate.setCreatedbyip(request.getRemoteAddr());
						issue_certificate.setCreatedon(ts);
						issue_certificate.setActive(true);
						applicationpregrantrepository.save(issue_certificate);
						
						applicationcertificaterepository.save(ac);
					}
					}else if(request.getParameter("editmode_issuecerty").equals("1"))
					{
						if( request.getParameter("radio2").equals("Y"))
					{
						System.out.println("Trace on 2666 Edit Yes");
						issue_certificate.setIs_pre_grant_opposition_received("Y");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
						Date date1 = null;
						String dicisiondate = request.getParameter("date_of_deceision");
						 
						try 
						{
							date1 = formatter1.parse(dicisiondate);
							 
						} 
						catch(ParseException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						issue_certificate.setDate_of_deceision(date1);
						
						issue_certificate.setOpposition_status(request.getParameter("opposition_status"));
						if(request.getParameter("opposition_status") == "A")
						{
							ApplicationCertificates ac = new ApplicationCertificates();
							
							Date date4 = null;
							Date date2 = null;
							Date date3 = null;
							String issue_date = request.getParameter("date_of_issue");
							String certificateno_no = request.getParameter("certificateno");
							String startdateprotect = request.getParameter("startdate");
							String enddateprotect = request.getParameter("enddate");
							 int acsid = Integer.parseInt(request.getParameter("acsid"));
							 
							 List<Object[]> issue_cert_date = applicationrepository.getissue_cert_data((int)applicationid);
								for(int y= 0 ; y< issue_cert_date.size() ;y++)
								{
									if(y==0)
									{
									Object[] row = (Object[]) issue_cert_date.get(y);
									System.out.println("Printing the row as = "+row);
									String vartype= String.valueOf(row[2]);
									String cropdetail= String.valueOf(row[3]);
									 
									if(vartype.equals("New") && cropdetail.equals("Trees/Vines"))
									{ List<Object[]> ddfetched = applicationcertificaterepository.date_data();
										 // List<Object[]> ddfetched = applicationcertificaterepository.date_data();
											if(ddfetched.size()>0)
											{
												Object[] r = (Object[]) ddfetched.get(0);
												issue_date = String.valueOf(r[0]);
												startdateprotect = String.valueOf(r[0]);
												enddateprotect = String.valueOf(r[1]);
									}			
									}
									else if(vartype.equals("New") && cropdetail.equals("Others"))
									{ 
										List<Object[]> ddfetched = applicationcertificaterepository.date_data_others();
											 // List<Object[]> ddfetched = applicationcertificaterepository.date_data();
												if(ddfetched.size()>0)
												{
													Object[] r = (Object[]) ddfetched.get(0);
													issue_date = String.valueOf(r[0]);
													startdateprotect = String.valueOf(r[0]);
													enddateprotect = String.valueOf(r[1]);
										}			
									}
									else if(vartype.equals("Extant")|| vartype.equals("Farmer")){ 
										
										System.out.println("Trace on line number  2732 on Yes Edit case");
										List<Object[]> ddfetched = applicationcertificaterepository.date_data_others();
											 // List<Object[]> ddfetched = applicationcertificaterepository.date_data();
												if(ddfetched.size()>0)
												{
													Object[] r = (Object[]) ddfetched.get(0);
													issue_date = String.valueOf(r[0]);
													startdateprotect = String.valueOf(r[0]);
													enddateprotect = String.valueOf(r[1]);
												}			
												}
									
									}
								}
							 
							try 
							{
								date4 = formatter1.parse(issue_date);
								date2 = formatter1.parse(startdateprotect);
								date3 = formatter1.parse(enddateprotect);
							 } 
							catch(ParseException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
						 
							Applications ap = new Applications();
							ap =applicationsrep.getOne((int)applicationid);
							int crop_id= ap.getCropid();
							String vtn="";
							
							 List<Object> vtname= applicationsrep.variety_name(crop_id,(int)applicationid);
							 if(vtname.size()>0)
							 {
							vtn= (String) vtname.get(0);
							System.out.println("Trace in Yes CAse For Edit Mode 2767 "+vtn);
							ac.setType(vtn);
							 }
							 
							if(acsid!=0)
							ac.setId(acsid);
							
							ac.setApplicationid(applicationid);
							ac.setCert_issue_date(date4);
							ac.setPeriod_of_protection(date2);
							ac.setProtection_expiry_date(date3);
							ac.setCertificate_serial_no(certificateno_no);
							ac.setCrop_id(crop_id);
							ac.setCreatedby(userdeail.getId());
							ac.setCreatedbyip(request.getRemoteAddr());
							ac.setCreatedon(ts);
							
							issue_certificate.setOpposition_status(request.getParameter("opposition_status"));
							issue_certificate.setCreatedby((long)userdeail.getId());
							issue_certificate.setCreatedbyip(request.getRemoteAddr());
							issue_certificate.setCreatedon(ts);
							issue_certificate.setActive(true);
							applicationpregrantrepository.save(issue_certificate);
							applicationcertificaterepository.save(ac);
							
						}else{
							issue_certificate.setOpposition_status(request.getParameter("opposition_status"));
							issue_certificate.setCreatedby((long)userdeail.getId());
							issue_certificate.setCreatedbyip(request.getRemoteAddr());
							issue_certificate.setCreatedon(ts);
							issue_certificate.setActive(true);
							applicationpregrantrepository.save(issue_certificate); 
							}
						
						
					}else if( request.getParameter("radio2").equals("N"))
					{
						System.out.println("Trace inside N case---2786");
						
						ApplicationCertificates ac = new ApplicationCertificates();
						
						issue_certificate.setIs_pre_grant_opposition_received("N");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
						Date date1 = null;
						Date date2 = null;
						Date date3 = null;
						String issue_date = request.getParameter("date_of_issue");
						String certificateno_no = request.getParameter("certificateno");
						String startdateprotect = request.getParameter("startdate");
						String enddateprotect = request.getParameter("enddate");
						 int acsid = Integer.parseInt(request.getParameter("acsid"));
						 
						 List<Object[]> issue_cert_date = applicationrepository.getissue_cert_data((int)applicationid);
							
							for(int y= 0 ; y< issue_cert_date.size() ;y++)
							{
								if(y==0)
								{
								Object[] row = (Object[]) issue_cert_date.get(y);
								
								String vartype= String.valueOf(row[2]);
								String cropdetail= String.valueOf(row[3]);
								 
								if(vartype.equals("New") && cropdetail.equals("Trees/Vines"))
								{ List<Object[]> ddfetched = applicationcertificaterepository.date_data();
									 // List<Object[]> ddfetched = applicationcertificaterepository.date_data();
										if(ddfetched.size()>0)
										{
											Object[] r = (Object[]) ddfetched.get(0);
											issue_date = String.valueOf(r[0]);
											startdateprotect = String.valueOf(r[0]);
											enddateprotect = String.valueOf(r[1]);
								}			
								}
								else if(vartype.equals("New") && cropdetail.equals("Others"))
								{ 
									List<Object[]> ddfetched = applicationcertificaterepository.date_data_others();
										 // List<Object[]> ddfetched = applicationcertificaterepository.date_data();
											if(ddfetched.size()>0)
											{
												Object[] r = (Object[]) ddfetched.get(0);
												issue_date = String.valueOf(r[0]);
												startdateprotect = String.valueOf(r[0]);
												enddateprotect = String.valueOf(r[1]);
												
									}			
								}
								else if(vartype.equals("Extant") || vartype.equals("Farmer")){ 
									
									List<Object[]> ddfetched = applicationcertificaterepository.date_data_others();
										 // List<Object[]> ddfetched = applicationcertificaterepository.date_data();
											if(ddfetched.size()>0)
											{
												Object[] r = (Object[]) ddfetched.get(0);
												
												issue_date = String.valueOf(r[0]);
												startdateprotect = String.valueOf(r[0]);
												enddateprotect = String.valueOf(r[1]);
												
											}			
											}
									
								}
							}
						 
						try 
						{
							date1 = formatter1.parse(issue_date);
							date2 = formatter1.parse(startdateprotect);
							date3 = formatter1.parse(enddateprotect);
						 } 
						catch(ParseException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					 
						Applications ap = new Applications();
						ap =applicationsrep.getOne((int)applicationid);
						int crop_id= ap.getCropid();
						String vtn="";
						System.out.println("Trace inside No CAse Selectedd ="+crop_id);
						 List<Object> vtname= applicationsrep.variety_name(crop_id,(int)applicationid);
						 if(vtname.size()>0)
						 {
						vtn= (String) vtname.get(0);
						
						System.out.println("trace on 2894 == "+vtn);
						ac.setType(vtn);
						 }
						
						 System.out.println("Trace on 2896 line number ==== ");
						if(acsid!=0)
						ac.setId(acsid);
						
						ac.setApplicationid(applicationid);
						ac.setCert_issue_date(date1);
						ac.setPeriod_of_protection(date2);
						ac.setProtection_expiry_date(date3);
						ac.setCertificate_serial_no(certificateno_no);
						ac.setCrop_id(crop_id);
						ac.setCreatedby(userdeail.getId());
						ac.setCreatedbyip(request.getRemoteAddr());
						ac.setCreatedon(ts);
						issue_certificate.setOpposition_status(request.getParameter("opposition_status"));
						issue_certificate.setCreatedby((long)userdeail.getId());
						issue_certificate.setCreatedbyip(request.getRemoteAddr());
						issue_certificate.setCreatedon(ts);
						issue_certificate.setActive(true);
						
						applicationpregrantrepository.save(issue_certificate);
						applicationcertificaterepository.save(ac);
					}
			
		}
	 
					
						 Remarks r = new Remarks();
							r.setApplicationid((int)applicationid);
							r.setRemarks("Registration Certificate Issued");
							r.setStatus(28);
							r.setCreatedby(userdeail.getId());
							r.setCreatedbyip(request.getRemoteAddr());
							r.setCreatedon(ts);
							remarksrepository.save(r);
							
					//Adding here ends on 30-12-2019
					
				 ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				act.setLogin_time_stamp(dt);
				act.setActivity("Save and Issue");
				act.setUrl("/issuecertificate");
				activitylogservice.save(act);
				 return "redirect:/getactions/"+applicationid;
			
		}
		
		
		@RequestMapping(value = "/editmediumtermstorage/{id}/{applicationid}", method = RequestMethod.GET)
		public ModelAndView editMediumTermStorage(@PathVariable(name = "id") Integer id,HttpServletRequest request,@PathVariable(name = "applicationid") Integer applicationid) {
			ModelAndView mav = new ModelAndView("applicationprocessing/edit_medium_term_storage");
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
		 
		  List<TypeLine> TypeLine = typelineservice.listall();
		  mav.addObject("TypeLine",TypeLine);
		  
		   MediumTermStorage mediumtermstorage = mediumtermstorageservice.get(id);
		   mav.addObject("mediumtermstorage",mediumtermstorage);
			
		   
		 //****Portion For Finding Application Process portion On 17-12-2019
			List<Object[]> pnames = rolemodulerepository.getProcessnamesviauserid(userid);
			 
			if(pnames != null && pnames.size()>0)
			{
				 mav.addObject("pnames",pnames);
				 
				Object s13 = null;
				if(pnames.contains("MediumTermStorage"))
				{
					s13= "MediumTermStorage";
					 mav.addObject("MediumTermStorage",s13);
				}
				
				 
				
			}
			List<Object[]> getcan_vartype = candidatevarietydetailsrepository.getdetails_withapplicationid(applicationid);
			if(getcan_vartype.size()>0 && getcan_vartype !=null)
			{
			if(getcan_vartype.contains(2))
			{mav.addObject("hybridtrue",1);
			}else { 
				mav.addObject("hybridtrue",0);
				List<Object[]> typeline11 = typelinerepository.gettypeline_datasecond();
			    if(typeline11!=null && typeline11.size()>0)
			    {
			    	mav.addObject("typeline11",typeline11);
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
					act.setUrl("/editmediumtermstorage");
					
					activitylogservice.save(act);
				 
			mav.setViewName("applicationprocessing/edit_medium_term_storage");
			return mav;
		}
		
		
		@RequestMapping(value = "/edittransferseedtomts/{id}/{applicationid}", method = RequestMethod.GET)
		public ModelAndView editTransferSeedToMTS(@PathVariable(name = "id") Integer id,HttpServletRequest request,@PathVariable(name = "applicationid") Integer applicationid) {
			ModelAndView mav = new ModelAndView("applicationprocessing/edit_transfer_seed_to_mts");
			

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
		 
		  List<TypeLine> TypeLine = typelineservice.listall();
		  mav.addObject("TypeLine",TypeLine);
		  
		  TransferSeedToMTS transferseedtomts = transferseedtomtsservice.get(id);
		   mav.addObject("transferseedtomts",transferseedtomts);
			
		   
		   
		 //****Portion For Finding Application Process portion On 17-12-2019
			List<Object[]> pnames = rolemodulerepository.getProcessnamesviauserid(userid);
			 
			if(pnames != null && pnames.size()>0)
			{
				mav.addObject("pnames",pnames);
			 
				Object s12 = null;
				 
				if(pnames.contains("TransferSeedsToMTS"))
				{
					s12= "TransferSeedsToMTS";
					mav.addObject("TransferSeedsToMTS",s12);
				}
				
				 
				
			}

			
			List<Object[]> getcan_vartype = candidatevarietydetailsrepository.getdetails_withapplicationid(applicationid);
			if(getcan_vartype.size()>0 && getcan_vartype !=null)
			{
			if(getcan_vartype.contains(2))
			{mav.addObject("hybridtrue",1);
			}else { 
				mav.addObject("hybridtrue",0);
				List<Object[]> typeline11 = typelinerepository.gettypeline_datasecond();
			    if(typeline11!=null && typeline11.size()>0)
			    {
			    	mav.addObject("typeline11",typeline11);
			    }   
			      }
			}
			
			List<Object[]> getcan_var = candidatevarietydetailsrepository.can_var_type(applicationid);
			if(getcan_var!=null && getcan_var.size()>0)
			{
				mav.addObject("can_var",getcan_var.get(0));
			}		
		

	//*****Ends Here *****************************		

		   ActivityLogTable act = new ActivityLogTable();
					act.setIpaddress(request.getRemoteAddr());
					act.setActivityby(userdeail.getUsername());
					Date dt = new Date();
					System.out.println("Current date Is ="+dt);
					act.setLogin_time_stamp(dt);
					act.setActivity("view");
					act.setUrl("/edittransferseedtomts");
					
					activitylogservice.save(act);
				 
			mav.setViewName("applicationprocessing/edit_transfer_seed_to_mts");
			return mav;
		}
		
		
		
	@RequestMapping(value = "/shorttermstorage", method = RequestMethod.POST)
		public String addShortTermStorage(@Valid @ModelAttribute(value = "shorttermstorage") ApplicationStsTest shorttermstorage, BindingResult bindingResult,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) 
		{   String username="";
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
			 
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				if (principal instanceof UserDetails)
					username = ((UserDetails) principal).getUsername();
				Date date = new Date();
				Timestamp ts = new Timestamp(date.getTime());
				User userdetails = userrepository.getUserDetail(username);
				
				String dateof_seadsent = request.getParameter("dateof_seadsent");
				String dateof_additional_seadsent = request.getParameter("dateof_additional_seadsent");
				String date_of_discard = request.getParameter("date_of_discard"); 
				
				
				
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date1 = null;
				Date date2 =null;
				Date date3 = null;
				
			  try 
				{
					date1 = formatter1.parse(dateof_seadsent);
					date2= formatter1.parse(dateof_additional_seadsent);
				
				} 
				catch(ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				if(request.getParameter("btnsubmit2")!=null)
				{
					
					int no_of_final_packets = Integer.parseInt(request.getParameter("no_of_final_packets"));
					int a = applicationststestrepository.updateApplicationSts(no_of_final_packets,applicationid);
					if(a >= 0) {
						redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
					}
					else {
						redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
					}
				}
				
				
				if(request.getParameter("btnsubmit1")!=null)
				{
					if(!(request.getParameter("id").equals("0")) && 
							!(request.getParameter("id").equals("null")) )
					shorttermstorage.setId(Integer.parseInt(request.getParameter("id")));
					shorttermstorage.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
				
					if((request.getParameter("testtype") == "DUS") || (request.getParameter("testtype").equals("DUS")))     
					{ 
						
						try {
							date3= formatter1.parse(date_of_discard);
						}
						catch(Exception ee)
						{System.out.println("INDIDE  2700 .....");}
						
						
						
						shorttermstorage.setTesttype(request.getParameter("testtype"));
						
						if(request.getParameter("radio") == "speed_post")
						{
							shorttermstorage.setTracking(request.getParameter("tracking"));
							shorttermstorage.setSpeedpost_no(request.getParameter("speed_post_no"));
						}				
						shorttermstorage.setDateof_seadsent(date1);
						shorttermstorage.setCreatedby(userdetails.getId());
						shorttermstorage.setCreatedon(ts);
						shorttermstorage.setCreatedbyip(request.getRemoteAddr());
						shorttermstorage=applicationststestservice.save(shorttermstorage);
						
						 if(shorttermstorage.getId() !=0 )
						 {
							  
							 int shorttermstorageid=shorttermstorage.getId();
							
							 ApplicationStsTestSeedDetails stsdetails = new ApplicationStsTestSeedDetails();
							 if(!(request.getParameter("secondid").equals("0")) && 
									 !(request.getParameter("secondid").equals("null")) )
							 stsdetails.setId(Integer.parseInt(request.getParameter("secondid")));
							 
							 stsdetails.setApplication_sts_test_id(shorttermstorageid);
							 stsdetails.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
							 stsdetails.setReason_of_discard(request.getParameter("reason_of_discard"));
							 stsdetails.setSeed_sent_to_leadcenter(request.getParameter("seed_sent_to_leadcenter"));
							 stsdetails.setSeed_discarded(request.getParameter("seed_discarded"));
							 stsdetails.setSeed_sent_to_coopcenter(request.getParameter("seed_sent_to_coopcenter"));
							 stsdetails.setAdditional_seed(request.getParameter("additional_seed"));
							 stsdetails.setDate_of_discard(date3);
							 stsdetails.setDateof_seadsent(date1);
							 stsdetails.setDateof_additionalseadsent(date2);
							 stsdetails.setCreatedby(userdetails.getId());
							 stsdetails.setCreatedon(ts);
							 stsdetails.setCreatedbyip(request.getRemoteAddr());
							 stsdetails.setType_line(Integer.parseInt(request.getParameter("type_line")));
								
							 //stsdetails=applicationststestseeddetailsrepository.save(stsdetails);
							 Boolean sussess =applicationststestseeddetailsservice.save(stsdetails);
							 if(sussess) {
									redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
								}
								else {
									redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
								}		
						 }
						 
					}
					
					if((request.getParameter("testtype") == "Hybridity") || (request.getParameter("testtype").equals("Hybridity")))
					{
						String date_of_discard1 = "";
						if (!(request.getParameter("date_of_discard1").equals("null")))
							{
							if(!(request.getParameter("date_of_discard1").equals("null")) && (request.getParameter("date_of_discard1").length() >0))
							date_of_discard1=request.getParameter("date_of_discard1");
					
							}
						Date date11 = null;
						if(!(request.getParameter("date_of_discard1").equals("null")))
							try {
								date11 = formatter1.parse(date_of_discard1);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
								System.out.println("INSIDE OF PARSING EXCEPTION HERE");
							}
						shorttermstorage.setTesttype(request.getParameter("testtype"));
						
						if(request.getParameter("radio") == "speed_post")
						{
							shorttermstorage.setTracking(request.getParameter("tracking"));
							shorttermstorage.setSpeedpost_no(request.getParameter("speed_post_no"));
						}				
						shorttermstorage.setDateof_seadsent(date1);
						shorttermstorage.setCreatedby(userdetails.getId());
						shorttermstorage.setCreatedon(ts);
						shorttermstorage.setCreatedbyip(request.getRemoteAddr());
						shorttermstorage=applicationststestservice.save(shorttermstorage);
						
						 if(shorttermstorage.getId() !=0 )
						 {
							 int shorttermstorageid=shorttermstorage.getId();
							 ApplicationStsTestSeedDetails stsdetails = new ApplicationStsTestSeedDetails();
							 
							 if(!(request.getParameter("secondid").equals("0")) &&  !(request.getParameter("secondid").equals("null")) )
							 stsdetails.setId(Integer.parseInt(request.getParameter("secondid")));
							
							 stsdetails.setApplication_sts_test_id(shorttermstorageid);
							 stsdetails.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
							 String s="";
							 if( !(request.getParameter("reason_of_discard1").equals("null")) )
							 {
								 if(!(request.getParameter("reason_of_discard1").equals("null")) && request.getParameter("reason_of_discard1").length()>0)
								s=request.getParameter("reason_of_discard1");
							
								 stsdetails.setReason_of_discard(s);
							 } 
							 
							 stsdetails.setSeed_sent_to_leadcenter(request.getParameter("seed_sent_to_leadcenter"));
							 String stset2="";
							
							if(!(request.getParameter("seed_discarded1").equals("null")) && request.getParameter("seed_discarded1").length()>0)
							{
								stset2=request.getParameter("seed_discarded1");
							}
							
							stsdetails.setSeed_discarded(stset2);
							stsdetails.setSeed_sent_to_coopcenter(request.getParameter("seed_sent_to_coopcenter1"));
							String stset1="";
									if(!(request.getParameter("additional_seed1").equals("null")) && request.getParameter("additional_seed1").length()>0)
									{
										stset1=request.getParameter("additional_seed1");
									}
									
								String stset="";
								if(!(request.getParameter("seed_sent_to_leadcenter1").equals("null")) && request.getParameter("seed_sent_to_leadcenter1").length()>0)
									{
										stset=request.getParameter("seed_sent_to_leadcenter1");
									}
								
							 System.out.println("Printing stest = "+stset);
							 stsdetails.setSeed_sent_to_leadcenter(stset);
							 //System.out.println("Printing stest1 = "+stset1);
							 stsdetails.setAdditional_seed(stset1);	
							 stsdetails.setType_line(Integer.parseInt(request.getParameter("type_line")));
							 stsdetails.setDate_of_discard(date11);
							 stsdetails.setDateof_seadsent(date1);
							 stsdetails.setDateof_additionalseadsent(date2);
							 stsdetails.setCreatedby(userdetails.getId());
							 stsdetails.setCreatedon(ts);
							 stsdetails.setCreatedbyip(request.getRemoteAddr());
							 Boolean sussess =applicationststestseeddetailsservice.save(stsdetails);
							 if(sussess) {
									redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
								}
								else {
									redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
								}	
							 
						 }
						 
					}
				}
				
					  Remarks r = new Remarks();
					  User userdeail = userrepository.getUserDetail(username);
						
						r.setApplicationid(applicationid);
						r.setRemarks("ShortTermStorage Processed");
						r.setStatus(32);
						r.setCreatedby(userdeail.getId());
						r.setCreatedbyip(request.getRemoteAddr());
						r.setCreatedon(ts);
						remarksrepository.save(r);
						
						//Adding here ends on 30-12-2019
						
					return "redirect:/getactions/"+applicationid;
			}
	
	
	@RequestMapping(value = "/issue_certificate_number/{applicationid}", method = RequestMethod.GET)
	public String issuecertificate_number(  @ModelAttribute(value = "issue_cert") ApplicationPreGrantOpposition issue_certificate,
			BindingResult bindingResult,@PathVariable(name="applicationid") long applicationid,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username="";
		 //applicationid=Long.parseLong(request.getParameter("applicationid"));
		//int id =Integer.parseInt(request.getParameter("id"));

 
									 
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
 
 
			 } 
			else 
			{
				  username = principal.toString();
			}
				Long checkedby = (long) userdeail.getId();
				String remarks = request.getParameter("remarks");
				String cert_check="";
				System.out.println("APPPCERTIFICATE= "+applicationid);
				cert_check =application_certificate.whether_cert_generated(applicationid);
				
				System.out.println("Printing the certificate serial no = "+cert_check);
				if(cert_check.equals("0"))
				{	
				int crtno = Integer.parseInt(cert_check); 
				System.out.println("Inside Issue Certificate No Clause"+crtno);
				if(crtno ==0)
				{
				List<Object> finan_year=certificaterepository.getdate_data_certificate();
				Integer certid=0; 
				String wd="";
				
				//List<Object> fyearstr = applicationrepository.getdate_data_data();
				 
				List<Object> issuecert= certificaterepository.getdata_for_certnum((String) finan_year.get(0));
				certid = (Integer) issuecert.get(0);
				wd=certid+"-"+(String) finan_year.get(0);
				Certificatenoissued ac = new Certificatenoissued();
				ac.setId(certid);
				ac.setFyear((String) finan_year.get(0));
				
				certificaterepository.save(ac);
				
				System.out.println("Inside Issue Certificate No Clause"+wd);
				application_certificate.issuecertificateno("Y",wd,(int)applicationid);
				
				}
				}
	//PAGE MAKING IN PDF ================
				
				String regno="";
				String financialyear="";
				String frmname="";
				String varname="";
				String termyear="";
				String totaltime="";
				String inititaltime="";
				String currentdate="";
				String grantdate="";
				
				List<Object[]> app_cert_show = applicationcertificaterepository.cert_issue_show((int)applicationid);
				 if(app_cert_show!= null && app_cert_show.size() >0)
				{
					model.addAttribute("app_cert_show",app_cert_show);
					for(int w = 0 ; w<app_cert_show.size() ; w++)
					{
						Object[] rw = (Object[]) app_cert_show.get(w);
				 
				   regno = (String) String.valueOf(rw[2]); 
				   financialyear = (String) String.valueOf(rw[4]);
				   frmname = (String) String.valueOf(rw[13]); 
				   varname = (String)String.valueOf(rw[12]); 
				   termyear = (String) String.valueOf(rw[11]);
				   totaltime = (String) String.valueOf(rw[7]);
				   inititaltime = (String) String.valueOf(rw[6])+"th day of "+(String) String.valueOf(rw[5])+" month "+(String) String.valueOf(rw[4])+" year";
				   currentdate= (String) String.valueOf(rw[17])+"th day of "+(String) String.valueOf(rw[16])+" month "+(String) String.valueOf(rw[15])+" year";
				   grantdate= (String) String.valueOf(rw[3]);
					}
				}
			
				//PDF FORMING PART	
				 
				 String fname="";
					String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
					
				   
				    	
				    
					try {
						String FILE="";
						

				    	
				    	StringBuilder filePath = new StringBuilder( 
								UPLOAD_FILEPATH+"PPVFRA/PROCESS/IssueCertificate");
						
						File file = new File(filePath.toString());
						if (!file.exists()) {
							file.mkdirs();
						}
						
						Document document = new Document();
						
						
							  FILE = UPLOAD_FILEPATH+"PPVFRA/PROCESS/IssueCertificate/"+applicationid+".pdf";
						
						
					    PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(FILE));
						document.open();
						System.out.println("start pdf process");
						Font font = FontFactory.getFont(FontFactory.COURIER, 18, BaseColor.BLACK);
						Font font10 = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
						Font font12 = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
						Font font14 = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
						Paragraph mainhead=new Paragraph("Form O - 2",font);
						Paragraph mainhead1=new Paragraph("[See rules 36 and 37]",font10);
						Paragraph mainhead2=new Paragraph("GOVERNMENT OF INDIA, PLANT VARIETY REGISTRY",font10);
						Paragraph mainhead3=new Paragraph("CERTIFICATE OF REGISTRATION",font10);
						Paragraph mainhead4=new Paragraph("REGISTRATION NO."+regno+" OF "+financialyear,font10);
						
						//Paragraph mainhead=new Paragraph("FORM-"+ftypee,font);
						mainhead.setAlignment(Element.ALIGN_CENTER);
						mainhead1.setAlignment(Element.ALIGN_CENTER);
						mainhead2.setAlignment(Element.ALIGN_CENTER);
						mainhead3.setAlignment(Element.ALIGN_CENTER);
						mainhead4.setAlignment(Element.ALIGN_CENTER);
						
						try {
							 document.add(mainhead);
							 document.add(mainhead1);
							 document.add(mainhead2);
							 document.add(mainhead3);
							 document.add(mainhead4);
							 
							 document.add(new Paragraph("___________________________________________________________________________ "));
							 
							 
					/*
					 * Paragraph paradate=new Paragraph("Application No."+applicationid);
					 * paradate.setAlignment(Element.ALIGN_RIGHT); document.add(paradate);
					 * document.add(new Paragraph(" "));
					 */
							 document.add(new Paragraph(" "));
						 
					 
							 Paragraph paraorg=new Paragraph("Whereas  "+frmname+"  has declared that he has developed plant "+varname+" variety/essentially derived plant variety and that he is the true breeder",font10);
							 Paragraph paraorg1=new Paragraph("thereof (or the legal representative or assignee of the true breeder) and that he is entitled to a plant variety right",font10);
							 Paragraph paraorg2=new Paragraph("on the said variety, having regard to the provisions of the Protection of Plant Varieties and Farmer's Right Act, 2001 and that there",font10);
							 Paragraph paraorg3=new Paragraph("is no objection to the registration of plant variety in favour of him.",font10);
							 Paragraph paraorg4=new Paragraph("And whereas he has, by an application, requested that registration of plant variety/essentially derived plant variety may be allowed to him",font10);
							 Paragraph paraorg5=new Paragraph("for the said plant Variety.",font10);
							 Paragraph paraorg6=new Paragraph("And whereas he has, by and in his application, particularly described the various distinctive features and mentioned the denomination of",font10);
							 Paragraph paraorg7=new Paragraph("the said plant variety",font10);
							 Paragraph paraorg8=new Paragraph("Now, these presents that the above said applicant (including his legal representatives and assignees or any of them) shall, subject to the",font10);
							 Paragraph paraorg9=new Paragraph("provisions of the Protection of Plant Varieties and Farmers Rights Act, 2001 and the conditions specified in section 47 of the said Act,",font10);
							 Paragraph paraorg10=new Paragraph("and to the conditions and provisions specified by any other law for the time being in force, have the exclusive right to produce, sell, market,",font10);
							 Paragraph paraorg11=new Paragraph("distribute, import or export the variety for a term of "+termyear+" years("+totaltime+") from the "+inititaltime+". And of",font10);
							 Paragraph paraorg12=new Paragraph("authorizing any other",font10);
							 Paragraph paraorg13=new Paragraph("person to do so, subject to the conditions that the validity of this registration is not guaranteed and that the fee prescribed for the",font10);
							 Paragraph paraorg14=new Paragraph("continuance of this registration are duly paid.",font10);
							 Paragraph paraorg15=new Paragraph("In witness thereof, the Registrar has caused this registration to be sealed as of the  "+currentdate+".",font10);
							 document.add(paraorg);
							 document.add(paraorg1);
							 document.add(paraorg2);
							 document.add(paraorg3);
							 document.add(paraorg4);
							 document.add(paraorg5);
							 document.add(paraorg6);
							 document.add(paraorg7);
							 document.add(paraorg8);
							 document.add(paraorg9);
							 document.add(paraorg10);
							 document.add(paraorg11);
							 document.add(paraorg12);
							 document.add(paraorg13);
							 document.add(paraorg14);
							 document.add(paraorg15);
							 
					/*
					 * Paragraph pararr=new
					 * Paragraph("in respect of denomination "+deno+" Crop (Botanical name) "
					 * +botn+" is" +
					 * " acknowledged in the office of th Registrar, PPV&FRA Authority on dated "
					 * +dated ,font10); document.add(pararr);
					 */
							 //para3.setAlignment(Element.ALIGN_JUSTIFIED);
							
							 
							 document.add(new Paragraph(" "));
							 
							 	
							  
					/*
					 * Paragraph ins=new Paragraph("Seal of Registrar's Office",font10);
					 * ins.setAlignment(Element.ALIGN_LEFT);
					 * 
					 * document.add(ins);
					 */

							 Paragraph ins1=new Paragraph("Signature on behalf of Registrar",font10);
							 Paragraph ins2=new Paragraph("Plant Varieties Registry",font10);
							 Paragraph ins3=new Paragraph("Date of Grant"+grantdate,font10);
								ins1.setAlignment(Element.ALIGN_RIGHT);
								ins2.setAlignment(Element.ALIGN_RIGHT);
								ins3.setAlignment(Element.ALIGN_RIGHT);
							 
							 document.add(ins1);
							 document.add(ins2);
							 document.add(ins3);
							  document.add(new Paragraph("___________________________________________________________________________ "));
							 document.add(new Paragraph(" "));
							 document.add(new Paragraph(" "));
							  
							 
							 
							 
							 document.add(new Paragraph(" "));
							 
						 }catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("document created");
						 
						 int hash1=document.hashCode();
						 System.out.println("hashcode1:"+hash1);
						document.close();
						writer.close();
						 
						 
						{
							String view = UPLOAD_FILEPATH+"PPVFRA/PROCESS/IssueCertificate/"+applicationid+".pdf";
						  	fname="PPVFRA/PROCESS/IssueCertificate/"+applicationid+".pdf";
						  	System.out.println("Processing view as =" +view);
						 } 
						
					}catch(IOException | DocumentException  es) {es.printStackTrace();}
					
					model.addAttribute("fname",fname);
				 
					List<Object[]> obj_n = applicationrepository.getmail_data((int)applicationid);
					Object[] r = (Object[]) obj_n.get(0);
					String username_email = String.valueOf(r[7]);
					String username_f = String.valueOf(r[8]);
					
				 //Uncomment for checkingmail
					 // username_mail = "amit242042@gmail.com";
								
					String pathvar =UPLOAD_FILEPATH+"PPVFRA/PROCESS/IssueCertificate/";
					String fname1= applicationid+".pdf";
				   // System.out.println("Printing username to mail="+username_email+"pathvar="+pathvar+"file name="+fname+"applicationid="+applicationid+"certificate number as ="+regno);
						   		
								try {
									sendmail2(username_email,pathvar,fname1,applicationid,regno,username_f);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				//MAIL SENDING ENDS HERE
   
			 ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/applicationscrutiny");
			activitylogservice.save(act);
		
		
		return "redirect:/getactions/"+applicationid;
		
	}
	
	
	
	@RequestMapping(value = "/view_cert_issued/{applicationid}", method = RequestMethod.GET)
	public String view_cert_issued( Model model,  HttpServletRequest request,RedirectAttributes redirectAttributes ,@PathVariable(name="applicationid") int applicationid) 
	{	int userid = 0;
		 String username="";
		int applicationid1=applicationid;
		 
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
		 
		
		System.out.println("Printing ="+applicationid );
		
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails) principal).getUsername();
				  userdeail = userrepository.getUserDetail(username);
				  userid = userdeail.getId(); 
			 } 
			else 
			{
				  username = principal.toString();
			}
			List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			model.addAttribute("modulelist", modulelist);
			
			List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			model.addAttribute("modulelist2", modulelist2);
			
			Long checkedby = (long) userdeail.getId();
				String remarks = request.getParameter("remarks");
			
				List<Object[]> acknowledgedetails=applicationrepository.getackdetails(applicationid);
				model.addAttribute("acknowledgedetails",acknowledgedetails);
			
				List<Object[]> app_cert_show = applicationcertificaterepository.cert_issue_show((int)applicationid);
				if(app_cert_show.size()> 0)
				{
					model.addAttribute("app_cert_show",app_cert_show.get(0));
				}
				
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/getactions");
			activitylogservice.save(act);
		
		
		return "applicationprocessing/certificate_issued";
		
	}
	
	
	boolean sendmail2(String emailidval, String UPLOAD_FILEPATH1, 
			String FILEname, long applicationid,String regno,String firstname) throws IOException {
		boolean valreturn = false;

		try {
			new Thread(new Runnable() {
			
				public void run() {
					try {
						System.out.println("Printing mime message");
						MimeMessage mimeMessage = new MailCommons().mimeMessage();
						mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailidval));
						System.out.println("Printing mime message 2");
						mimeMessage.setSubject("Acknowledgement");

						StringBuilder sb = new StringBuilder();
						System.out.println("Printing mime message3");
						sb.append("Dear " + firstname + ",");
						//sb.append("<br/><br/>Ms " + orgname
						sb.append("<br/><br/>Certificate No - "+regno+" issued against Your Application number: " + applicationid );
						sb.append("<br/><br/>Thank You<br/>Team PPVFRA");

						mimeMessage.setContent(sb.toString(), "text/html; charset=utf-8");
						BodyPart messageBodyPart = new MimeBodyPart();
						/* messageBodyPart.setText("This is message body"); */
						messageBodyPart.setContent(sb.toString(), "text/html; charset=utf-8");
						Multipart multipart = new MimeMultipart();
						multipart.addBodyPart(messageBodyPart);

						messageBodyPart = new MimeBodyPart();
						String filename = UPLOAD_FILEPATH1 + "/" + FILEname;
						// C:/AYUSH_Files/pdf/80SiteInspection24D.pdf";
				System.out.println("Printing mime message4");
						DataSource source = new FileDataSource(filename);
						messageBodyPart.setDataHandler(new DataHandler(source));
						// messageBodyPart.setFileName(FILEname);
				System.out.println("Printing mime message5");
				System.out.println("Printing filename="+filename+"WITH FILE="+FILEname);
						messageBodyPart.setFileName(FILEname);
						multipart.addBodyPart(messageBodyPart);
						System.out.println("Printing mime message6");
						mimeMessage.setContent(multipart);
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
	
	
	@RequestMapping(value = "/editshorttermstorage/{id}/{applicationid}/{secondid}", method = RequestMethod.GET)
	public ModelAndView editShortTermStorage(@PathVariable(name = "id") Integer id,HttpServletRequest request,@PathVariable(name = "applicationid") Integer applicationid,@PathVariable(name = "secondid") Integer secondid) {
		ModelAndView mav = new ModelAndView("applicationprocessing/edit_short_term_storage");
		

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
	 
	  //Commented On 24-01-2020
	  //List<TypeLine> TypeLine = typelineservice.listall();
	  List<Object[]> TypeLine = typelinerepository.gettypeline_datasecond_class();
	  mav.addObject("TypeLine",TypeLine);
	  
	   ApplicationStsTest shorttermstorage = applicationststestservice.get(id);
	   if(shorttermstorage != null)
	   {
		   mav.addObject("shorttermstorage",shorttermstorage);
		   
		  int searchedid=  applicationststestseeddetailsrepository.returnid(id);
	   ApplicationStsTestSeedDetails astsdetails= applicationsts_test_seeddetails.get(searchedid);
	   if(astsdetails!= null)
	   mav.addObject("astsdetails",astsdetails);
	   
	   }
	   
	   
	 //****Portion For Finding Application Process portion On 17-12-2019
	 		List<Object[]> pnames = rolemodulerepository.getProcessnamesviauserid(userid);
	 		//for(Object[] pname : pnames)
	 		//{
	 		//	System.out.println("Printing the pnames list as= "+(Object)Arrays.toString(pname));
	 		//}
	 		if(pnames != null && pnames.size()>0)
	 		{
	 			mav.addObject("pnames",pnames);
	 		
	 			Object s7 = null;
	 		 if(pnames.contains("ShortTermStorage"))
	 			{
	 				s7= "ShortTermStorage";
	 				mav.addObject("ShortTermStorage",s7);
	 			}
	 			
	 			
	 			
	 		}
	 		
	 		List<Object[]> cnc_center=	add_testcenter_repository.findcnc_center(applicationid);
			
	 		 List<Object[]> lc_center=	add_testcenter_repository.findlc_center(applicationid);
	 			
	 		 if(cnc_center.size()>0)
	 		 {
	 			mav.addObject("cnc_center",cnc_center);
	 		 }
	 		 
	 		 if(lc_center.size()>0)
	 		 {
	 			mav.addObject("lc_center",lc_center);
	 		 }
	 		

	 //*****Ends Here *****************************	
		
	   ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("view");
				act.setUrl("/editshorttermstorage");
				
				activitylogservice.save(act);
	    
			   mav.addObject("applicationid",applicationid);
			   mav.addObject("id",id);
			   mav.addObject("secondid",secondid);
			 
		mav.setViewName("applicationprocessing/edit_short_term_storage");
		return mav;
	}
	
	
	
	@RequestMapping(value = "/editshort_termstorage_hybrid/{id}/{applicationid}/{secondid}", method = RequestMethod.GET)
	public ModelAndView editShort_Term_Storage(@PathVariable(name = "id") Integer id,HttpServletRequest request,@PathVariable(name = "applicationid") Integer applicationid,@PathVariable(name = "secondid") Integer secondid) {
		ModelAndView mav = new ModelAndView("applicationprocessing/edit_short_term_storagehybrid");
		

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
	 
	 // List<TypeLine> TypeLine = typelineservice.listall();
	  List<Object[]> TypeLine = typelinerepository.gettypeline_data_class();
	  mav.addObject("TypeLine",TypeLine);
	  
	   ApplicationStsTest shorttermstorage = applicationststestservice.get(id);
	   if(shorttermstorage != null)
	   {
		   mav.addObject("shorttermstorage",shorttermstorage);
		   
		  int searchedid=  applicationststestseeddetailsrepository.returnid(id);
	   ApplicationStsTestSeedDetails astsdetails= applicationsts_test_seeddetails.get(searchedid);
	   if(astsdetails!= null)
	   mav.addObject("astsdetails",astsdetails);
	   
	   }
		
	   //****Portion For Finding Application Process portion On 17-12-2019
		List<Object[]> pnames = rolemodulerepository.getProcessnamesviauserid(userid);
		//for(Object[] pname : pnames)
		//{
		//	System.out.println("Printing the pnames list as= "+(Object)Arrays.toString(pname));
		//}
		if(pnames != null && pnames.size()>0)
		{
			mav.addObject("pnames",pnames);
		
			Object s7 = null;
		 if(pnames.contains("ShortTermStorage"))
			{
				s7= "ShortTermStorage";
				mav.addObject("ShortTermStorage",s7);
			}
			
			
			
		}
		
		
		List<Object[]> cnc_center=	add_testcenter_repository.findcnc_center(applicationid);
		
		 List<Object[]> lc_center=	add_testcenter_repository.findlc_center(applicationid);
			
		 if(cnc_center.size()>0)
		 {
			mav.addObject("cnc_center",cnc_center);
		 }
		 
		 if(lc_center.size()>0)
		 {
			mav.addObject("lc_center",lc_center);
		 }

//*****Ends Here *****************************	
		
	   ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("view");
				act.setUrl("/editshorttermstorage_hybrid");
				
				activitylogservice.save(act);
	    
			   mav.addObject("applicationid",applicationid);
			   mav.addObject("id",id);
			   mav.addObject("secondid",secondid);
		System.out.println("OPENING THE SCREEN ==3430");
		mav.setViewName("applicationprocessing/edit_short_term_storagehybrid");
		return mav;
	}
	

	
	
	@RequestMapping(value = "/deficency_report_byapplicant", method = RequestMethod.POST)
	public String defi_report_byapplicant(@ModelAttribute(value = "applicationscrutiny") ApplicationScrutiny applicationscrutiny,
			 Model model,HttpServletRequest request,RedirectAttributes redirectAttributes
			,@RequestParam MultipartFile[] fileUpload) throws IOException {
		String username="";
		//System.out.println("deficency_report INSIDE 11111111111111");
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
		
		Date date1 = new Date();
		Timestamp ts = new Timestamp(date1.getTime());
		
		User userdeail = null;
		int user_id=0;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			  username = ((UserDetails) principal).getUsername();
			  userdeail = userrepository.getUserDetail(username);
			  user_id=userdeail.getId();
			
		 } else {
			  username = principal.toString();
		 }
		if(request.getParameter("submit_defi_report_applicant") != null)
			{
				 Boolean savedsuccess=false;
				 String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
				 String data_scr_remarks="";
				 Date last_date_sub= null;
				 //String date_of_completion = request.getParameter("date_of_completion");
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date = null;
				
				String id [] = request.getParameterValues("id");
				//System.out.println("PP IDDDD"+id+"ID LENGTH ="+id.length);
				data_scr_remarks= applicationrepository.saved_data_scrutiny_remarks(applicationid);
				last_date_sub= applicationrepository.saved_data_scrutiny_lastdate(applicationid);
				
			for(int j = 0; j< id.length ; j++)
			 {	
				String originalName ="";
				String pathvar="";
				 ApplicationScrutiny applicationscrutiny1 = new  ApplicationScrutiny();
				
				String comp[] = request.getParameterValues("complaince");
			//System.out.println("PRINTING COMP="+comp);
			
				/* for(int i=0; i< fileUpload.length ; i++) 
				 {*/	
			 
			if (principal instanceof UserDetails)
			username = ((UserDetails) principal).getUsername();
			
			
			User userdetails = userrepository.getUserDetail(username);
			if(id[j]!=null && id[j] !="0")
			{
				applicationscrutiny1.setId(Integer.parseInt(id[j]));
			}
			applicationscrutiny1.setApplicationid(Integer.parseInt(request.getParameter("applicationid")));
			
			//applicationscrutiny1.setDateof_submission(applicationscrutiny.getDateof_submission());
			if(last_date_sub!=null)
			applicationscrutiny1.setDateof_submission(last_date_sub);
			if(data_scr_remarks!= null)
			applicationscrutiny1.setRemarks_doc_scrutiny(data_scr_remarks);
			applicationscrutiny1.setCreatedby(userdetails.getId());
			applicationscrutiny1.setCreatedon(ts);
			applicationscrutiny1.setCreatedbyip(request.getRemoteAddr());
			applicationscrutiny1.setComplaince(comp[j]);
			ApplicationScrutiny apsdata = applicationscrutinyrepository.getOne(Integer.parseInt(id[j]));
			 //System.out.println("Printing asts as ="+apsdata);
			 String observ = apsdata.getObservation();
			 int categ= apsdata.getCategory();
			 
			//System.out.println("Printing data"+j+" ="+observ+" and Categ "+categ);
			applicationscrutiny1.setCategory(categ);
			applicationscrutiny1.setObservation(observ);
			
			/*if(fileUpload[j].getOriginalFilename() == "" || 
					fileUpload[j].getOriginalFilename() == null ||
					fileUpload[j].getOriginalFilename() ==" " || 
					fileUpload[j].getOriginalFilename().trim() =="" || 
					fileUpload[j].getOriginalFilename().equals("null")
					)
			continue;
			*/
			
			//System.out.println("FILE UPLOAD PPR LOOP="+j);
			//System.out.println("FILE UPLOAD PPR="+fileUpload[j].getSize());
			
		
			
			if (fileUpload[j].getSize() >=0) 
			 {
				//System.out.println("COMING INSIDE IF NOT ZERO"+fileUpload[j].getSize());
	   		StringBuilder filePath = new StringBuilder(
	   		UPLOAD_FILEPATH+"DEFICIENCY_REPORT/"+applicationid+"/"); 
	   		File file = new File(filePath.toString());
	   		if (!file.exists()) 
	   		{
	   			file.mkdirs();
	   		}
	   		
	   		if(fileUpload[j].isEmpty()) 
			{
			String filename[]=request.getParameterValues("fname_discrepant");
			System.out.println("Printing filename"+filename[j]);
			if(filename[j]!=null && !(filename[j] ==""))
			{
			System.out.println("Printing in else 3978 = "+filename[j]);
			 originalName=filename[j];
			 applicationscrutiny1.setDocumentname(originalName);
		   		pathvar =UPLOAD_FILEPATH+"DEFICIENCY_REPORT/"+applicationid+"/"+originalName;
		   		applicationscrutiny1.setDocumentpath(pathvar);
				}
		}
		else
		{
	   		System.out.println("PRINTING J AS="+j);
	   		String filename=	fileUpload[j].getOriginalFilename().substring(0, fileUpload[j].getOriginalFilename().lastIndexOf("."));
	   		String fileext=	fileUpload[j].getOriginalFilename().substring(fileUpload[j].getOriginalFilename().lastIndexOf("."), fileUpload[j].getOriginalFilename().length() );
	   		String ffname= (filename.replace(" ", "_")).trim();
	   		 originalName = ffname+ new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+fileext;
	   		File actFile = new File(filePath.append(File.separator + originalName).toString());
	   		try {
				Files.copy(fileUpload[j].getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   		//System.out.println("PRINTING F NAME AS="+originalName);
	   		applicationscrutiny1.setDocumentname(originalName);
	   		pathvar =UPLOAD_FILEPATH+"DEFICIENCY_REPORT/"+applicationid+"/"+originalName;
	   		//applicationscrutiny.setDocumentpath(UPLOAD_FILEPATH+"PPVFRA/PROCESS/DEFICIENCY_REPORT/"+applicationid+"/"+originalName);
	   		applicationscrutiny1.setDocumentpath(pathvar);
			 }
		}
			 
			savedsuccess = applicationscrutinyservice.save(applicationscrutiny1);
		 originalName ="";
		 pathvar="";
		 
		 
				 }
			if (savedsuccess)
			{
				redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
			}
			else
			{
				redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
			}
			 

			 /*
			 Umesh Adding here on 30-12-2019 for remarks	
			 */
				/* Umesh Adding here on 30-12-2019------
	               * for adding remarks in application_remarks
	               */
				
			/*
				if(savedsuccess)
				{
					Remarks r = new Remarks();
					r.setApplicationid((int)applicationid);
					r.setRemarks("Deficiency Report Saved By Applicant");
					r.setStatus(32);
					r.setCreatedby(userdeail.getId());
					r.setCreatedbyip(request.getRemoteAddr());
					r.setCreatedon(ts);
					remarksrepository.save(r);
				}
				
			*/		
			//Adding here ends on 30-12-2019
	 
			}
	if(request.getParameter("submit_defi_report_final_applicant") != null)
	{
		//System.out.println("Iniside Final Submit Date CLAUSE");
		int success1=applicationscrutinyrepository.updateApplicationScrutiny_finalsubmit(ts,Integer.parseInt(request.getParameter("applicationid")));
		//System.out.println("VAL RETURNED="+success1);
		 if(success1 >= 1)
			{
				Remarks r = new Remarks();
				r.setApplicationid((int)applicationid);
				r.setRemarks("Deficency Report Submitted by Applicant");
				r.setStatus(32);
				r.setCreatedby(userdeail.getId());
				r.setCreatedbyip(request.getRemoteAddr());
				r.setCreatedon(ts);
				remarksrepository.save(r);
			}
		 String assigned_add="";
		 String assigned_username="";
		 int assignestouserid=0;
		 String assigned_userdesig="";
		 String sender_username="";
		 String sender_userdesig="";
		 
		 
		 
		 if(success1 >=1)
		 {
			
			 System.out.println("LINE NUMBER 4327--"+userdeail.getId());
			 List<Object[]> getscrdata=applicationscrutinyrepository.getscrdetails(userdeail.getId());
			 if(getscrdata !=null)
			 {
				 Object[] r= (Object[]) getscrdata.get(0);
				 assigned_add= String.valueOf(r[1]);
				 assigned_username= String.valueOf(r[2]);
				 assigned_userdesig= String.valueOf(r[3]);
				 sender_username= String.valueOf(r[5]);
				 sender_userdesig= String.valueOf(r[6]);
				 assignestouserid=Integer.parseInt(String.valueOf(r[0])); 
			 }
			 
				 //String remarks = request.getParameter("remarks");
				 //System.out.println("Printing receiving userid="+assignestouserid);
				
				//System.out.println("Printing  applicationid ="+applicationid);
				Assignment_Details assigned_details = new Assignment_Details();
			    assigned_details.setApplicationid(applicationid);
				assigned_details.setForm_type_id(1);
				assigned_details.setAssigned_by_user_id(user_id);
				assigned_details.setAssigned_bydesignation(userrepository.getDesignation_viaid(user_id));
				assigned_details.setReceived_by_user_id(assignestouserid);
				assigned_details.setReceived_by_designation(userrepository.getDesignation_viaid(assignestouserid));
				assigned_details.setAssigned_by_office_id(userrepository.getOffice_id(user_id));
				assigned_details.setReceived_by_date(ts);
				assigned_details.setCreatedby(user_id);
				assigned_details.setCreatedbyip(request.getRemoteAddr());
				assigned_details.setCreatedon(ts);
				assigned_details.setApplicationstatus_id(38);
				//assignmentdetailsrepository.save(assigned_details);
				Boolean success=assignmentdetailservice.save(assigned_details);
							
				sendmail_deficiencyreport_via_applicant(assigned_add,assigned_username,assigned_userdesig,sender_username,sender_userdesig);
			
			
			 applicationsrep.deficiencyupdate_status_afterdefiupload((int)applicationid);	 
		 }
		//System.out.println("Iniside Final Submit Date CLAUSE ENDS ----");
	}
			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			//System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/applicationscrutiny");
			activitylogservice.save(act);
		
		
		return "redirect:/submittedapplication";
		
	}
	
	
	boolean sendmail_deficiencyreport_via_applicant(String assignedmail, String assignedusername 
			,String assigneddesignation,String sendername,String senderdesignation) throws IOException {
		boolean valreturn = false;

		try {
			new Thread(new Runnable() {
			
				public void run() {
					try {
						//System.out.println("Printing mime message deffffffiiiiiiii"+assignedmail);
						MimeMessage mimeMessage = new MailCommons().mimeMessage();
						mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(assignedmail));
						//System.out.println("Printing mime message 2");
						mimeMessage.setSubject("Deficiency Report Submitted By Applicant");

						StringBuilder sb = new StringBuilder();
						//System.out.println("Printing mime message3");
						sb.append("Dear " + assignedusername + ",");
						//sb.append("<br/><br/>Ms " + orgname
						sb.append("<br/><br/>The Deficency Report is finally submitted please verify. ");
						sb.append("<br/><br/>Thank You<br/>"+sendername+"<br/>"+senderdesignation+"<br/>Team PPVFRA");

						mimeMessage.setContent(sb.toString(), "text/html; charset=utf-8");
						
						
						Transport.send(mimeMessage);
						//System.out.println("Printing mime message7");
						//System.out.println("Printing at last =="+mimeMessage);

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
	
	
	
	@RequestMapping(value = "/sendquery", 
			method = RequestMethod.POST)
	public String addsendquery(@Valid @ModelAttribute(value = "sendquery") 
	ApplicationOnlineQuery sendquery,BindingResult 
	bindingResult, Model model,HttpServletRequest 
	request,RedirectAttributes redirectAttributes,
	@RequestParam MultipartFile[] fileUpload) 
	{
		String username="";
		int applicationid=Integer.parseInt(request.getParameter("applicationid"));
		 
		{ 
			System.out.println("Trace in New Method Send Query");
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
			Date date1 = new Date();
			Date date = new Date();
			
			Timestamp ts = new Timestamp(date.getTime());
			User userdetails = userrepository.getUserDetail(username);
			String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
			
			String mailaddress="";
		    String mailusername="";
		    
			 int user_uid=0;
			 String designation="";
			  
			 List<Object[]> mail= userrepository.getprofiledata_applicant(applicationid);
				if(mail !=null)
				{
					Object[] r1= (Object[]) mail.get(0);
					mailaddress= String.valueOf(r1[2]);
					mailusername= String.valueOf(r1[0]);
					user_uid= Integer.parseInt(String.valueOf(r1[5]));
				}
				
			
			
			for(int i=0; i< fileUpload.length ; i++) {	
				 if (principal instanceof UserDetails)
					username = ((UserDetails) principal).getUsername();
			 
			int id= Integer.parseInt(request.getParameter("app_online_id"));
			if(id != 0)
			sendquery.setId(id);
			sendquery.setSender_id(userdeail.getId());
			
			sendquery.setApplication_id(Integer.parseInt(request.getParameter("applicationid")));
			sendquery.setCreatedby(userdetails.getId());
			sendquery.setCreatedon(ts);
			sendquery.setCreatedbyip(request.getRemoteAddr());
			if(fileUpload[i].getOriginalFilename() == "" || 
			 fileUpload[i].getOriginalFilename() == null || 
			 fileUpload[i].getOriginalFilename() ==" " || 
			 fileUpload[i].getOriginalFilename().trim() =="" 
	  || fileUpload[i].getOriginalFilename().equals("null"))
				   continue;
			
			if (fileUpload[i].getSize() >0) 
			   {		
			   		StringBuilder filePath = new StringBuilder(
			   		UPLOAD_FILEPATH+"PPVFRA/PROCESS/SENDQUERY/"+applicationid); 
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
			   		sendquery.setReportname(originalName);
			   		sendquery.setReportpath(UPLOAD_FILEPATH+"PPVFRA/PROCESS/SENDQUERY/"+applicationid+"/"+originalName);
			   }
			
			
			
			
			}
			 
				 Remarks r = new Remarks();
				   	r.setApplicationid(applicationid);
					r.setRemarks(request.getParameter("remarks"));
					r.setStatus(32);
					r.setCreatedby(userdeail.getId());
					r.setCreatedbyip(request.getRemoteAddr());
					r.setCreatedon(ts);
					remarksrepository.save(r);
					
			//Adding here ends on 30-12-2019
			String remarks_formail="";	
			String idfetched_query="";
			String fpath="";
			String fname="";
			Boolean savedsuccess = apponlineservice.save(sendquery);
			if (savedsuccess)
			{
				redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
				 try {
					 List<Object[]> idfetched= apponlinequeryrepository.getidfetched(applicationid);
					 if(idfetched.size()>0&& idfetched !=null)
						{
						 Object[] rem= (Object[]) idfetched.get(0);
						 idfetched_query= String.valueOf(rem[1]);
						 List<Object[]> remarksfetched= apponlinequeryrepository.getmaildata(Integer.parseInt(idfetched_query));
						if(remarksfetched.size()>0 && remarksfetched !=null)	
						{
							 Object[] rem1= (Object[]) remarksfetched.get(0);
							remarks_formail= String.valueOf(rem1[4]);
							fname=String.valueOf(rem1[7]);
						 
						  }
						}
					 
					 fpath=UPLOAD_FILEPATH+"PPVFRA/PROCESS/SENDQUERY/"+applicationid;
					 send_query_mail_portion(mailaddress,mailusername,applicationid,remarks_formail,fname,fpath);
					  } 
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
			act.setActivity("submit");
			act.setUrl("/sendquery");
			activitylogservice.save(act);
			
		}
		
		return "redirect:/getactions/"+applicationid;
		
	}
	
	
///*****Adding here on 17-03-2020
	
boolean send_query_mail_portion(String mailadd,String username,int applicationid,String remarks_formail ,String filename1,String filepath) throws IOException {
			boolean valreturn = false;

			try {
				new Thread(new Runnable() {
				
					public void run() {
						try {
							//System.out.println("Printing MIME inside send_applicationscrutiny_mail");
							MimeMessage mimeMessage = new MailCommons().mimeMessage();
							mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailadd));
							//System.out.println("Printing mime message 2");
							mimeMessage.setSubject("Attachment Query Mail");

							StringBuilder sb = new StringBuilder();
							//System.out.println("Printing mime message3");
							sb.append("Dear " + username + ",");
							//sb.append("<br/><br/>Ms " + orgname
							sb.append("<br/><br/>Please Find The Attachment attached within for your Application number: " + applicationid
									+ "<br/>(Form),and please find remarks : "+remarks_formail
									+ "<br/>.You are requested to,");
							sb.append("<br/>"
									+ " Please furnish the attachment as soon as possible.");
							sb.append("<br/><br/>Thank You<br/>Team PPVFRA");

							
							mimeMessage.setContent(sb.toString(), "text/html; charset=utf-8");
							BodyPart messageBodyPart = new MimeBodyPart();
							
							messageBodyPart.setContent(sb.toString(), "text/html; charset=utf-8");
							
							Multipart multipart = new MimeMultipart();
							multipart.addBodyPart(messageBodyPart);

							messageBodyPart = new MimeBodyPart();
							String filename = filepath + "/" + filename1;
						    System.out.println("Printing mime message4");
							DataSource source = new FileDataSource(filename);
							messageBodyPart.setDataHandler(new DataHandler(source));
						 
							System.out.println("Printing mime message5");
							System.out.println("Printing filename="+filename+"WITH FILE="+filepath);
							messageBodyPart.setFileName(filename);
							multipart.addBodyPart(messageBodyPart);
							System.out.println("Printing mime message6");
							mimeMessage.setContent(multipart);
							
										
							Transport.send(mimeMessage);
							//System.out.println("Printing mime message7");
							//System.out.println("Printing at last =="+mimeMessage);

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
	
	
/////*********Adding Here Of 17-03-2020 Ends
	
	
		
}