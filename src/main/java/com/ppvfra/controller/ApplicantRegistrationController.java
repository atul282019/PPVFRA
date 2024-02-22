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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.ppvfra.entity.ApplicantRegistration;
import com.ppvfra.entity.ApplicantRole;
import com.ppvfra.entity.Applicantdata;
import com.ppvfra.entity.Application_Status_Paginate;
import com.ppvfra.entity.CompanyRegistration;
import com.ppvfra.entity.Country;
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.EditApplicantRegistration;
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
import com.ppvfra.repository.ApplicantdataRepository;
import com.ppvfra.repository.CompanyRegistrationRepository;
import com.ppvfra.repository.CountryRepository;
import com.ppvfra.repository.DistrictRepository;
import com.ppvfra.repository.InstitutionRegistrationRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.StateRepository;
import com.ppvfra.repository.UserAddRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.repository.UserTypesRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.AddcropService;
import com.ppvfra.service.ApplicantRegistrationService;
import com.ppvfra.service.CompanyRegistrationService;
import com.ppvfra.service.CountryService;
import com.ppvfra.service.DistrictService;
import com.ppvfra.service.EditApplicantRegistrationService;
import com.ppvfra.service.EditInstitutionRegistrationService;
import com.ppvfra.service.InstitutionRegistrationService;
import com.ppvfra.service.InstitutionTypesService;
import com.ppvfra.service.StateService;

@Controller
public class ApplicantRegistrationController {

	@Autowired
	ApplicantRegistrationService applicantregistrationservice;

	@Autowired
	StateService stateservice;

	@Autowired
	DistrictService districtservice;

	@Autowired
	CompanyRegistrationService companyregistrationservice;

	@Autowired
	InstitutionRegistrationService institutionregistrationservice;

	@Autowired
	CountryService countryservice;

	@Autowired
	AdduserRepository adduserrepository;

	@Autowired
	InstitutionTypesService institutiontypesservice;

	@Autowired
	UserTypesRepository usertypesrepository;

	@Autowired
	RoleAssignRepository roleassignrepository;

	@Autowired
	RoleRepository rolerepository;

	@Autowired
	CompanyRegistrationRepository companyregistrationrepository;

	@Autowired
	ApplicantRoleRepository applicantrolerepository;

	@Autowired
	UserRepository userrepository;

	@Autowired
	ModulesRepository modulesrepository;

	@Autowired
	InstitutionRegistrationRepository institutionregistrationrepository;

	@Autowired
	ApplicantRegistrationRepository applicantregistrationrepository;

	@Autowired
	EditApplicantRegistrationService editapplicantregistrationservice;

	@Autowired
	StateRepository staterep;

	@Autowired
	DistrictRepository districtrepository;

	@Autowired
	ApplicantRegistrationRepository applicant_registration_rep;

	@Autowired
	AddcropService addcropservice;

	@Autowired
	Role_ModulesRepository rolemodulerepository;

	@Autowired
	ActivityLogService activitylogservice;

	@Autowired
	UserAddRepository useraddrepository;

	@Autowired
	ApplicantdataRepository applicantdatarepository;

	@Autowired
	CountryRepository countryrep;

	@Autowired
	EditInstitutionRegistrationService editinstitutionregistrationservice;
	
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 50;

	@RequestMapping(value = "/ApplicantRegistration", method = RequestMethod.GET)
	public String ApplicantRegistration(Model model) {
		ApplicantRegistration applicantregistration = new ApplicantRegistration();

		List<CompanyRegistration> companyregistration = companyregistrationrepository.companyList("Accepted", true);
		model.addAttribute("companyregistration", companyregistration);

		List<InstitutionRegistration> institutionregistration = institutionregistrationrepository
				.institutionList("Accepted", true);
		model.addAttribute("institutionregistration", institutionregistration);

		List<ApplicantRole> applicantrolelist = applicantrolerepository.applicantRoleList();
		model.addAttribute("applicantrolelist", applicantrolelist);

		/*
		 * List<CompanyRegistration> companyregistration =
		 * companyregistrationservice.listall();
		 * model.addAttribute("companyregistration", companyregistration);
		 */

		/*
		 * List<InstitutionRegistration> institutionregistration =
		 * institutionregistrationservice.listall();
		 * model.addAttribute("institutionregistration", institutionregistration);
		 */

		List<Country> Country = countryservice.listall();
		model.addAttribute("Country", Country);
		// List<States> State = stateservice.listall();
		List<States> State = staterep.getstates();
		model.addAttribute("State", State);

		// List<Districts> District = districtservice.listall();
		List<Districts> District = districtrepository.getalldistricts();
		model.addAttribute("District", District);
		model.addAttribute("applicantregistration", applicantregistration);
		return "applicant_registration";
	}

	@RequestMapping(value = "/applicantregistrationmethod", method = RequestMethod.POST)
	public String applicantregistrationmethod(
			@Valid @ModelAttribute(value = "applicantregistration") ApplicantRegistration applicantregistration,
			BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			InternalUser internaluser = new InternalUser();
			model.addAttribute("internaluser", internaluser);

			/*
			 * List<CompanyRegistration> companyregistration =
			 * companyregistrationrepository.companyList("Accepted",true);
			 * model.addAttribute("companyregistration", companyregistration);
			 * 
			 * List<InstitutionRegistration> institutionregistration =
			 * institutionregistrationservice.listall();
			 * model.addAttribute("institutionregistration", institutionregistration);
			 */

			List<CompanyRegistration> companyregistration = companyregistrationrepository.companyList("Accepted", true);
			model.addAttribute("companyregistration", companyregistration);

			List<InstitutionRegistration> institutionregistration = institutionregistrationrepository
					.institutionList("Accepted", true);
			model.addAttribute("institutionregistration", institutionregistration);

			List<ApplicantRole> applicantrolelist = applicantrolerepository.applicantRoleList();
			model.addAttribute("applicantrolelist", applicantrolelist);

			List<Country> Country = countryservice.listall();
			model.addAttribute("Country", Country);
			// List<States> State = stateservice.listall();
			List<States> State = staterep.getstates();
			model.addAttribute("State", State);

			// List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);
			return "applicant_registration";
		}

		else {
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			List<Country> Country = countryservice.listall();
			model.addAttribute("Country", Country);
			// List<States> State = stateservice.listall();
			List<States> State = staterep.getstates();
			model.addAttribute("State", State);

			// List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);
			companyregistrationservice.toString();
			applicantregistration.setApplicantname(request.getParameter("applicationname"));
			applicantregistration.setActive(false);
			applicantregistration.setCreatedon(ts);
			applicantregistration.setCreatedbyip(request.getRemoteAddr());
			applicantregistration = applicantregistrationservice.save(applicantregistration);
			if (applicantregistration.getId() != 0) {
				redirectAttributes.addFlashAttribute("message",
						"Thank You for Registration. Your Account is currently inactive. You will be notified via SMS and Email once your request has been accepted by admin.");

				int companyid = applicantregistration.getId();
				int typeid = usertypesrepository.getIdByType("Applicant");
				int roleid = rolerepository.getIdByName("Applicant");
				InternalUser internaluser = new InternalUser();
				internaluser.setApplicantid(companyid);
				internaluser.setName(request.getParameter("firstname").trim());
				internaluser.setFullname(request.getParameter("firstname").trim());
				internaluser.setEmail(request.getParameter("email").trim());
				internaluser.setUsername(request.getParameter("username").trim());
				internaluser.setDesignation(request.getParameter("designation").trim());
				internaluser.setMobile_number(request.getParameter("mobile_number").trim());
				internaluser.setContactno(request.getParameter("contactno").trim());
				internaluser.setUsertypeid(typeid);
				internaluser.setRole("" + roleid);
				internaluser.setFirstname(request.getParameter("firstname").trim());
				internaluser = adduserrepository.save(internaluser);
				if (internaluser.getId() != 0) {
					User_Role userrole = new User_Role();
					userrole.setUser_id(internaluser.getId());
					userrole.setRole_id(roleid);
					userrole.setCreatedon(ts);
					userrole.setCreatedby(internaluser.getId());
					userrole = roleassignrepository.save(userrole);

				}
				internaluser.setIsactive(false);
			} else {
				redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
			}

		}
		return "redirect:/login";
	}
	
	// sargam--- code started
		@RequestMapping(value = "/getuserpplicationPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public String applicationStatusPage(@RequestBody Map<String, Integer> data, Model model, HttpServletRequest request,
				RedirectAttributes redirectAttributes) {

			redirectAttributes.addAttribute("max", data.get("max"));
			redirectAttributes.addAttribute("offset", data.get("offset"));
			return "redirect:/getuserapplication";
		}
	    //sargam ---code ends 

	@RequestMapping(value = "/getuserapplication", method = RequestMethod.GET)
	public String getApplicant(Model model, HttpServletRequest request, Optional<Integer> max,
			Optional<Integer> offset)
	{
		/// Getting Logged in user
		int userid = 0;
		User userdeail = null;

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId();
			System.out.println("Priting Loggin user: " + username);
		} else {
			String username = principal.toString();
			System.out.println("Priting else Loggin user: " + username);
		}
		/// end Getting Logged in user

		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		model.addAttribute("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		model.addAttribute("modulelist2", modulelist2);

		List<Applicantdata> appregistration = applicantdatarepository.findAll();
		
		int pageitem = max.orElse(INITIAL_PAGE_SIZE);
		int pageNumber = (offset.orElse(0) < 1) ? INITIAL_PAGE : offset.get() - 1;

		int srno = pageitem * pageNumber;
		
		// sargam code start
		PagedListHolder<Object> page = new PagedListHolder(appregistration);
		page.setPageSize(pageitem); // number of items per page
		page.setPage(pageNumber); // set to first page

		int totalPages = page.getPageCount();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("srno", srno);
		
		// Retrieval
		List<Object> applicantregistration = page.getPageList(); // a List which represents the current page

		model.addAttribute("applicantregistration", applicantregistration);
		// sargam code ends

		List<States> stateselections = staterep.findAll();
		model.addAttribute("stateselections", stateselections);

		List<Districts> districtselection = districtrepository.findAll();
		model.addAttribute("distsel", districtselection);

		ApplicantRegistration applicantreg = new ApplicantRegistration();
		model.addAttribute("applicantreg", applicantreg);

		StringBuffer url = request.getRequestURL();
		String val = url.substring(url.lastIndexOf("/"), url.length());
		System.out.println("PPPPP===" + val);
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid, val);
		if (actd != null) {
			Object actd_viewtrue = null;
			Object actd_addtrue = null;
			model.addAttribute("actd", actd);

			// System.out.println("PRINTING contains IN INST " +actd.contains("View"));

			if (actd.contains("View")) {
				actd_viewtrue = "valpresent";
				// System.out.println("PRINING IN INST." +actd_viewtrue);

				model.addAttribute("actd_viewtrue", actd_viewtrue);

				String val1 = "Accept_Applicant";
				List<Object[]> accesstypeprocess_data = rolemodulerepository.getAccesstypes_Processdata(userid, val1);
				// System.out.println("PRINTING IN
				// INST="+accesstypeprocess_data.contains("Add"));
				if (accesstypeprocess_data.contains("Add")) {
					actd_addtrue = "addpresent";
					model.addAttribute("actd_addtrue", actd_addtrue);
				}

			}

			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is =" + dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("view");
			act.setUrl("/getuserapplication");

			activitylogservice.save(act);
			// Umesh Adding here on 14-01-2020 -------
			// Added Here For Name And Role Showing in header

			List<Object[]> usrname_header_val = userrepository.getUser_Nameviaid(userdeail.getId());
			model.addAttribute("usrname_header_val", usrname_header_val);
			List<Object[]> rolespresent = roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent", rolespresent);
			// Umesh Adding ends here

		}
		return "admin/applicants";
	}
	
 //code for excel extraction
	
  	@RequestMapping(value = "/getexcelapp", method = RequestMethod.GET)
  	public void getApplicantdetails_excel(HttpServletResponse response) throws IOException {
  		response.setContentType("application/octet-stream");
  		String headerKey = "Content-Disposition";
  		DateFormat  dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
  		String currentDateTime = dateformatter.format(new Date());
  		String fileName = "applicant_" + currentDateTime + ".xlsx";
  		String headerValue = "attachment; filename=" + fileName;
  		
  		response.setHeader(headerKey, headerValue);
  		
  		List<Applicantdata> appregistration = applicantdatarepository.findAll();
  		
  		applicantregistrationservice.export(response,appregistration);
  	}
  	
  	//code for excel extraction

	@RequestMapping(value = "/view_applicantdetails/{id}", method = RequestMethod.GET)
	public String viewbyapplicantid(@PathVariable(name = "id") Integer id, Model mav) {

		int userid = 0;
		User userdeail = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId();
		} else {
			String username = principal.toString();
			// System.out.println("Priting else Loggin user: " + username);
		}
		/// end Getting Logged in user
		// System.out.println("Priting Loggin user id: " + userid);

		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		mav.addAttribute("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		mav.addAttribute("modulelist2", modulelist2);

		List<States> office_state = staterep.findAll();
		mav.addAttribute("office_state", office_state);

		List<Object[]> details = applicantregistrationrepository.findDetails(id);
		mav.addAttribute("details", details);
		// Umesh Adding here on 14-01-2020 -------
		// Added Here For Name And Role Showing in header

		List<Object[]> usrname_header_val = userrepository.getUser_Nameviaid(userdeail.getId());
		mav.addAttribute("usrname_header_val", usrname_header_val);
		List<Object[]> rolespresent = roleassignrepository.getrole_nameviauserid(userid);
		mav.addAttribute("rolespresent", rolespresent);
		// Umesh Adding ends here
		return "view_applicant_registration";

	}

	@RequestMapping(value = "/editapplicant/{id}", method = RequestMethod.GET)
	public ModelAndView editApplicant(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/edit_company_registration");
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
			// System.out.println("Priting else Loggin user: " + username);
		}
		/// end Getting Logged in user
		// System.out.println("Priting Loggin user id: " + userid);

		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		mav.addObject("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		mav.addObject("modulelist2", modulelist2);

		List<CompanyRegistration> companyregistration = companyregistrationrepository.companyList("Accepted", true);
		mav.addObject("companyregistration", companyregistration);

		List<InstitutionRegistration> institutionregistration = institutionregistrationrepository
				.institutionList("Accepted", true);
		mav.addObject("institutionregistration", institutionregistration);

		List<ApplicantRole> applicantrolelist = applicantrolerepository.applicantRoleList();
		mav.addObject("applicantrolelist", applicantrolelist);

		List<Country> Country = countryservice.listall();
		mav.addObject("Country", Country);

		// List<States> State = stateservice.listall();
		List<States> State = staterep.getstates();
		mav.addObject("State", State);

		// List<Districts> District = districtservice.listall();
		List<Districts> District = districtrepository.getalldistricts();
		mav.addObject("District", District);

		List<Object[]> details = applicantregistrationrepository.findDetails(id);
		mav.addObject("details", details);

		EditApplicantRegistration applicantregistration = editapplicantregistrationservice.get(id);
		mav.addObject("edit_applicantregistration", applicantregistration);

		String val = "/getuserapplication";
		System.out.println("PPPPP===" + val);
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid, val);
		if (actd != null) {
			Object actd_viewtrue = null;

			mav.addObject("actd", actd);

			// System.out.println("PRINTING contains IN INST " +actd.contains("View"));

			if (actd.contains("View")) {
				actd_viewtrue = "valpresent";
				// System.out.println("PRINING IN INST." +actd_viewtrue);

				mav.addObject("actd_viewtrue", actd_viewtrue);
			}

		}

		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is =" + dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("edit");
		act.setUrl("/editapplicant");

		activitylogservice.save(act);

		// Umesh Adding here on 14-01-2020 -------
		// Added Here For Name And Role Showing in header

		List<Object[]> usrname_header_val = userrepository.getUser_Nameviaid(userdeail.getId());
		mav.addObject("usrname_header_val", usrname_header_val);
		List<Object[]> rolespresent = roleassignrepository.getrole_nameviauserid(userid);
		mav.addObject("rolespresent", rolespresent);
		// Umesh Adding ends here

		mav.setViewName("admin/edit_applicant_registration");

		return mav;
	}

	@RequestMapping(value = "/editapplicantregistration", method = RequestMethod.POST)
	public String editApplicantregistrationmethod(
			@Valid @ModelAttribute(value = "edit_applicantregistration") EditApplicantRegistration applicantregistration,
			BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
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
				// System.out.println("Priting else Loggin user: " + username);
			}
			/// end Getting Logged in user
			// System.out.println("Priting Loggin user id: " + userid);

			List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			model.addAttribute("modulelist", modulelist);

			List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			model.addAttribute("modulelist2", modulelist2);

			List<CompanyRegistration> companyregistration = companyregistrationrepository.companyList("Accepted", true);
			model.addAttribute("companyregistration", companyregistration);

			List<InstitutionRegistration> institutionregistration = institutionregistrationrepository
					.institutionList("Accepted", true);
			model.addAttribute("institutionregistration", institutionregistration);

			List<ApplicantRole> applicantrolelist = applicantrolerepository.applicantRoleList();
			model.addAttribute("applicantrolelist", applicantrolelist);

			List<Country> Country = countryservice.listall();
			model.addAttribute("Country", Country);

			// List<States> State = stateservice.listall();
			List<States> State = staterep.getstates();
			model.addAttribute("State", State);

			// List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);

			String val = "/getuserapplication";
			System.out.println("PPPPP===" + val);
			List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid, val);
			if (actd != null) {
				Object actd_viewtrue = null;
				Object actd_addtrue = null;
				model.addAttribute("actd", actd);

				if (actd.contains("View")) {
					actd_viewtrue = "valpresent";

					model.addAttribute("actd_viewtrue", actd_viewtrue);

					String val1 = "Accept_Company";
					List<Object[]> accesstypeprocess_data = rolemodulerepository.getAccesstypes_Processdata(userid,
							val1);

					if (accesstypeprocess_data.contains("Add")) {
						actd_addtrue = "addpresent";
						model.addAttribute("actd_addtrue", actd_addtrue);
					}

				}

			}

			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is =" + dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("editapplicant-error");
			act.setUrl("/editapplicant");

			activitylogservice.save(act);

			return "admin/edit_applicant_registration";
		}

		else {

			int userid1 = 0;
			User userdeail = null;

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				userdeail = userrepository.getUserDetail(username);
				userid1 = userdeail.getId();
				// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
			} else {
				String username = principal.toString();
				// System.out.println("Priting else Loggin user: " + username);
			}

			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			List<Country> Country = countryservice.listall();
			model.addAttribute("Country", Country);
			// List<States> State = stateservice.listall();
			List<States> State = staterep.getstates();
			model.addAttribute("State", State);

			// List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);
			applicantregistration.setApplicantname(request.getParameter("applicationname"));
			EditApplicantRegistration applicantregistration11 = editapplicantregistrationservice
					.get(applicantregistration.getId());
			if (applicantregistration11 != null) {
				if (applicantregistration11.getActive() != null) {
					applicantregistration.setActive(applicantregistration11.getActive());
				}

				if (applicantregistration11.getVerificationstatus() != null) {
					applicantregistration.setVerificationstatus(applicantregistration11.getVerificationstatus());
				}

				if (applicantregistration11.getVerifierremarks() != null) {
					applicantregistration.setVerifierremarks(applicantregistration11.getVerifierremarks());
				}
			}
			applicantregistration.setCreatedon(ts);
			applicantregistration.setCreatedbyip(request.getRemoteAddr());
			applicantregistration = editapplicantregistrationservice.save(applicantregistration);
			if (applicantregistration.getId() != 0) {
				redirectAttributes.addFlashAttribute("message", "Data saved successfully.");
				int applicantid = applicantregistration.getId();
				int typeid = usertypesrepository.getIdByType("Applicant");
				int roleid = rolerepository.getIdByName("Applicant");
				InternalUser internaluser = new InternalUser();
				String userid = userrepository.getUseridvia_Applicantid(applicantid);
				if (userid != null && (Integer.parseInt(userid)) != 0) {
					internaluser.setId(Integer.parseInt(userid));

				}
				internaluser.setApplicantid(applicantid);
				internaluser.setName(request.getParameter("firstname"));
				internaluser.setFullname(request.getParameter("firstname"));
				internaluser.setEmail(request.getParameter("email"));
				internaluser.setUsername(request.getParameter("username"));
				internaluser.setDesignation(request.getParameter("designation"));
				internaluser.setMobile_number(request.getParameter("mobile_number"));
				internaluser.setContactno(request.getParameter("contactno"));
				internaluser.setUsertypeid(typeid);
				internaluser.setRole("" + roleid);
				internaluser.setFirstname(request.getParameter("firstname"));
				// internaluser.setCreatedon(ts);
				// internaluser.setCreatedby(internaluser.getId());
				String userrole_id = "";
				// System.out.println("trace Internal user id= "+internaluser);
				if (internaluser.getId() != 0) {
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
				
				if (applicantregistration11.getVerificationstatus().equals("Accepted"))
				{		
		internaluser.setIsactive(true);
				}else if(applicantregistration11.getVerificationstatus().equals("Rejected"))
						{
					internaluser.setIsactive(false);
							}else {internaluser.setIsactive(null);}

				
				internaluser = adduserrepository.save(internaluser);
			} else {
				redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
			}

			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is =" + dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/editapplicantregistration");

			activitylogservice.save(act);
		}
		return "redirect:/getuserapplication";
	}

	@RequestMapping(value = "/acceptrejectapplicant/{id}", method = RequestMethod.POST)
	public String acceptreject(
			@ModelAttribute(value = "acceptrejectapplicant") ApplicantRegistration acceptrejectapplicant, Model model,
			HttpServletRequest req, @PathVariable(name = "id") Integer id) {
		/// Getting Logged in user
		int userid = 0;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			User userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId();
			System.out.println("Priting Loggin user: " + username);
		} else {
			String username = principal.toString();
			System.out.println("Priting else Loggin user222222222: " + username);
		}
		/// end Getting Logged in user

		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		model.addAttribute("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		model.addAttribute("modulelist2", modulelist2);

		if (!(req.getParameter("applicantstatus").equals("null"))
				&& !(req.getParameter("applicantstatus").equals(""))) {
			String statustobeset = req.getParameter("applicantstatus");
			String remarks = req.getParameter("remarks");
			// System.out.println("trace=="+statustobeset);
			// System.out.println("trace=="+remarks);

			System.out.println("TRACE INSIDE -----NOT NULL");
			String mip = req.getRemoteAddr();
			int verifierid = 0;
			String verifierdesignation = "";
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				User userdeail = userrepository.getUserDetail(username);
				verifierid = userdeail.getId();
				String u = userrepository.getAllDetailsOfUser(verifierid);
				verifierid = userdeail.getId();
				verifierdesignation = u;
				System.out.println("Priting Loggin user: " + username);
			}
			int applicant_accepted = 0;
			if (statustobeset.equals("Accepted")) {
				applicant_accepted = applicant_registration_rep.dataadding(statustobeset, remarks, id, mip, verifierid,
						verifierdesignation);

				String resetpass = "ppvfra@2020";
				String mailaddress = "";
				String mailusername = "";
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12
				String encodedPassword = encoder.encode(resetpass);
				int a = useraddrepository.update_applicant(encodedPassword, id);
				if (a == 1) {
					List<Object[]> mail = useraddrepository.getapplicantemail_address(id);
					if (mail != null) {
						Object[] r = (Object[]) mail.get(0);
						System.out
								.println("Printing the object val=" + String.valueOf(r[0]) + "" + String.valueOf(r[1]));
						mailaddress = String.valueOf(r[1]);
						mailusername = String.valueOf(r[2]);
						System.out.println("Printing the mailaddress=" + mailaddress);
					}
					try {
						send_resetpassword_mail(resetpass, mailaddress, mailusername);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			} else {
				applicant_accepted = applicant_registration_rep.dataadding1(statustobeset, remarks, id, mip, verifierid,
						verifierdesignation);

				String mailaddress1 = "";
				String mailusername1 = "";

				List<Object[]> mail = useraddrepository.getapplicantemail_address(id);
				{
					Object[] r = (Object[]) mail.get(0);
					System.out.println("Printing the object val=" + String.valueOf(r[0]) + "" + String.valueOf(r[1]));
					mailaddress1 = String.valueOf(r[1]);
					mailusername1 = String.valueOf(r[2]);
					System.out.println("Printing the mailaddress=" + mailaddress1);
				}
				try {
					send_reject_mail(mailaddress1, mailusername1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// System.out.println("TRACE PRINTING ACCEPTED STATUS="+applicant_accepted);
			if (applicant_accepted == 1) {
				if (statustobeset.equals("Rejected")) {
					applicant_registration_rep.dataupdate1(id);
				} else {
					applicant_registration_rep.dataupdate(id);
				}

			}
		}
		// System.out.println("TRACE FOR VIEW=="+req.getParameter("applicant_view"));
		if (req.getParameter("applicant_view") != null)
			return "redirect:/admin";
		else
			return "redirect:/getuserapplication";
	}

	boolean send_resetpassword_mail(String pass, String mailadd, String username) throws IOException {
		boolean valreturn = false;

		try {
			new Thread(new Runnable() {

				public void run() {
					try {
						System.out.println("Printing MIME RESET PASSWORD");
						MimeMessage mimeMessage = new MailCommons().mimeMessage();
						mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailadd));
						System.out.println("Printing mime message 2");
						mimeMessage.setSubject("Password Reset");

						StringBuilder sb = new StringBuilder();
						System.out.println("Printing mime message3");
						sb.append("Dear " + username + ",");
						// sb.append("<br/><br/>Ms " + orgname
						sb.append("<br/><br/>Your reset Password is:   " + pass);
						sb.append("<br/><br/>Thank You<br/>Team PPVFRA");

						mimeMessage.setContent(sb.toString(), "text/html; charset=utf-8");

						Transport.send(mimeMessage);
						System.out.println("Printing mime message7");
						System.out.println("Printing at last ==" + mimeMessage);

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

	boolean send_reject_mail(String mailadd, String username) throws IOException {
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
						// sb.append("<br/><br/>Ms " + orgname
						sb.append("<br/><br/>Your Application is not accepted .");
						sb.append("<br/><br/>Thank You<br/>Team PPVFRA");

						mimeMessage.setContent(sb.toString(), "text/html; charset=utf-8");

						Transport.send(mimeMessage);
						System.out.println("Printing mime message7");
						System.out.println("Printing at last ==" + mimeMessage);

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

	@RequestMapping(value = "/getuserapplication/{status}", method = RequestMethod.GET)
	public String getApplicant_statuswise(@PathVariable("status") String status, Model model,
			HttpServletRequest request) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail = null;

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId();
			System.out.println("Priting Loggin user: " + username);
		} else {
			String username = principal.toString();
			System.out.println("Priting else Loggin user: " + username);
		}
		/// end Getting Logged in user

		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		model.addAttribute("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		model.addAttribute("modulelist2", modulelist2);

		if (status.equals("New")) {
			List<Object[]> applicantregistration = applicant_registration_rep.findapplicant_new_pidata();
			model.addAttribute("applicantregistration", applicantregistration);
		} else {
			List<Object[]> applicantregistration = applicant_registration_rep.findapplicant_statuswise_pidata(status);
			model.addAttribute("applicantregistration", applicantregistration);
		}

		List<States> stateselections = staterep.findAll();
		model.addAttribute("stateselections", stateselections);

		List<Districts> districtselection = districtrepository.findAll();
		model.addAttribute("distsel", districtselection);

		ApplicantRegistration applicantreg = new ApplicantRegistration();
		model.addAttribute("applicantreg", applicantreg);

		StringBuffer url = request.getRequestURL();
		String val = url.substring(url.lastIndexOf("/") - 19, url.lastIndexOf("/"));
		System.out.println("PPPPP===" + val);
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid, val);
		if (actd != null) {
			Object actd_viewtrue = null;
			Object actd_addtrue = null;
			model.addAttribute("actd", actd);

			// System.out.println("PRINTING contains IN INST " +actd.contains("View"));

			if (actd.contains("View")) {
				actd_viewtrue = "valpresent";
				// System.out.println("PRINING IN INST." +actd_viewtrue);

				model.addAttribute("actd_viewtrue", actd_viewtrue);

				String val1 = "Accept_Applicant";
				List<Object[]> accesstypeprocess_data = rolemodulerepository.getAccesstypes_Processdata(userid, val1);
				// System.out.println("PRINTING IN
				// INST="+accesstypeprocess_data.contains("Add"));
				if (accesstypeprocess_data.contains("Add")) {
					actd_addtrue = "addpresent";
					model.addAttribute("actd_addtrue", actd_addtrue);
				}

			}

			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is =" + dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("view");
			act.setUrl("/getuserapplication");

			activitylogservice.save(act);

		}

		// Umesh Adding here on 14-01-2020 -------
		// Added Here For Name And Role Showing in header

		List<Object[]> usrname_header_val = userrepository.getUser_Nameviaid(userdeail.getId());
		model.addAttribute("usrname_header_val", usrname_header_val);
		List<Object[]> rolespresent = roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent", rolespresent);
		// Umesh Adding ends here

		return "admin/applicants";
	}

//Adding here on 19-06-2020

	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/editapplicant_forcompany_admin/{id}/{mainstatus}/{substatus}", method = RequestMethod.GET)
	public ModelAndView editapplicant_companyadmin(@PathVariable(name = "id") Integer id, HttpServletRequest request,
			@PathVariable(name = "mainstatus") String mainstatus, @PathVariable(name = "substatus") String substatus) {
		ModelAndView mav = new ModelAndView("admin/edit_company_registration_compadmin");
		/// Getting Logged in user
		System.out.println("1052 line number---------------------");
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
			// System.out.println("Priting else Loggin user: " + username);
		}
		/// end Getting Logged in user
		// System.out.println("Priting Loggin user id: " + userid);

		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		mav.addObject("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		mav.addObject("modulelist2", modulelist2);

		List<CompanyRegistration> companyregistration = companyregistrationrepository.companyList("Accepted", true);
		mav.addObject("companyregistration", companyregistration);

		List<InstitutionRegistration> institutionregistration = institutionregistrationrepository
				.institutionList("Accepted", true);
		mav.addObject("institutionregistration", institutionregistration);

		List<ApplicantRole> applicantrolelist = applicantrolerepository.applicantRoleList();
		mav.addObject("applicantrolelist", applicantrolelist);

		List<Country> Country = countryservice.listall();
		mav.addObject("Country", Country);

		// List<States> State = stateservice.listall();
		List<States> State = staterep.getstates();
		mav.addObject("State", State);

		// List<Districts> District = districtservice.listall();
		List<Districts> District = districtrepository.getalldistricts();
		mav.addObject("District", District);

		List<Object[]> details = applicantregistrationrepository.findDetails(id);
		mav.addObject("details", details);

		EditApplicantRegistration applicantregistration = editapplicantregistrationservice.get(id);
		mav.addObject("edit_applicantregistration", applicantregistration);

		List<Object[]> datahas = applicantregistrationrepository.getdata_forcompany_admin(userid, id);
		String vp = "";

		if (datahas.size() > 0) {
			System.out.println("Printing the length = = = =1101 ===" + userid + " id= " + id + " and size="
					+ datahas.size() + " data =" + datahas.get(0));
			if (datahas.contains(userid))

			{
				vp = "valpresent";
				mav.addObject("vp", vp);
			} else {
				vp = "novalue";
				mav.addObject("vp", vp);
			}
		} else {
			datahas = applicantregistrationrepository.getdata_forinstitute_admin(userid, id);
			if (datahas.contains(userid))

			{
				vp = "valpresent";
				mav.addObject("vp", vp);
			} else {
				vp = "novalue";
				mav.addObject("vp", vp);
			}

		}

		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is =" + dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("edit");
		act.setUrl("/editapplicant_forcompany_admin");

		activitylogservice.save(act);

		// Umesh Adding here on 14-01-2020 -------
		// Added Here For Name And Role Showing in header

		List<Object[]> usrname_header_val = userrepository.getUser_Nameviaid(userdeail.getId());
		mav.addObject("usrname_header_val", usrname_header_val);
		List<Object[]> rolespresent = roleassignrepository.getrole_nameviauserid(userid);
		mav.addObject("rolespresent", rolespresent);
		// Umesh Adding ends here

		mav.addObject("mainp", mainstatus);
		mav.addObject("subp", substatus);

		mav.setViewName("admin/edit_applicant_registration_compadmin");

		return mav;
	}

	@RequestMapping(value = "/editapplicantregistration_compadmin", method = RequestMethod.POST)
	public String editApplicantregistrationmethod_compmethod(
			@Valid @ModelAttribute(value = "edit_applicantregistration") EditApplicantRegistration applicantregistration,
			BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {

			System.out.println("Inside Exception -------------------------------");
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
				// System.out.println("Priting else Loggin user: " + username);
			}

			List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			model.addAttribute("modulelist", modulelist);

			List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			model.addAttribute("modulelist2", modulelist2);

			List<CompanyRegistration> companyregistration = companyregistrationrepository.companyList("Accepted", true);
			model.addAttribute("companyregistration", companyregistration);

			List<InstitutionRegistration> institutionregistration = institutionregistrationrepository
					.institutionList("Accepted", true);
			model.addAttribute("institutionregistration", institutionregistration);

			List<ApplicantRole> applicantrolelist = applicantrolerepository.applicantRoleList();
			model.addAttribute("applicantrolelist", applicantrolelist);

			List<Country> Country = countryservice.listall();
			model.addAttribute("Country", Country);

			List<States> State = staterep.getstates();
			model.addAttribute("State", State);

			// List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);

			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is =" + dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("editapplicant-error");
			act.setUrl("/editapplicant");

			activitylogservice.save(act);

			return "admin/edit_applicant_registration_compadmin";
		}

		else {

			int userid1 = 0;
			User userdeail = null;

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				userdeail = userrepository.getUserDetail(username);
				userid1 = userdeail.getId();
				// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
			} else {
				String username = principal.toString();
				// System.out.println("Priting else Loggin user: " + username);
			}

			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			List<Country> Country = countryservice.listall();
			model.addAttribute("Country", Country);
			// List<States> State = stateservice.listall();
			List<States> State = staterep.getstates();
			model.addAttribute("State", State);

			// List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);
			applicantregistration.setApplicantname(request.getParameter("applicationname"));
			EditApplicantRegistration applicantregistration11 = editapplicantregistrationservice
					.get(applicantregistration.getId());
			if (applicantregistration11 != null) {
				if (applicantregistration11.getActive() != null) {
					applicantregistration.setActive(applicantregistration11.getActive());
				}

				if (applicantregistration11.getVerificationstatus() != null) {
					applicantregistration.setVerificationstatus(applicantregistration11.getVerificationstatus());
				}

				if (applicantregistration11.getVerifierremarks() != null) {
					applicantregistration.setVerifierremarks(applicantregistration11.getVerifierremarks());
				}
			}
			applicantregistration.setCreatedon(ts);
			applicantregistration.setCreatedbyip(request.getRemoteAddr());
			applicantregistration = editapplicantregistrationservice.save(applicantregistration);
			if (applicantregistration.getId() != 0) {
				redirectAttributes.addFlashAttribute("message", "Data saved successfully.");
				int applicantid = applicantregistration.getId();
				int typeid = usertypesrepository.getIdByType("Applicant");
				int roleid = rolerepository.getIdByName("Applicant");
				InternalUser internaluser = new InternalUser();
				String userid = userrepository.getUseridvia_Applicantid(applicantid);
				if (userid != null && (Integer.parseInt(userid)) != 0) {
					internaluser.setId(Integer.parseInt(userid));

				}
				internaluser.setApplicantid(applicantid);
				internaluser.setName(request.getParameter("firstname").trim());
				internaluser.setFullname(request.getParameter("firstname").trim());
				internaluser.setEmail(request.getParameter("email").trim());
				internaluser.setUsername(request.getParameter("username").trim());
				internaluser.setDesignation(request.getParameter("designation").trim());
				internaluser.setMobile_number(request.getParameter("mobile_number").trim());
				internaluser.setContactno(request.getParameter("contactno").trim());
				internaluser.setUsertypeid(typeid);
				internaluser.setRole("" + roleid);
				internaluser.setFirstname(request.getParameter("firstname").trim());
				// internaluser.setCreatedon(ts);
				// internaluser.setCreatedby(internaluser.getId());
				String userrole_id = "";
				// System.out.println("trace Internal user id= "+internaluser);
				if (internaluser.getId() != 0) {
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
				if (applicantregistration11.getVerificationstatus().equals("Accepted"))
						{		
				internaluser.setIsactive(true);
						}else if(applicantregistration11.getVerificationstatus().equals("Rejected"))
								{
							internaluser.setIsactive(false);
									}else {internaluser.setIsactive(null);}

				internaluser = adduserrepository.save(internaluser);
			} else {
				redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
			}

			ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is =" + dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("save");
			act.setUrl("/editapplicantregistration");

			activitylogservice.save(act);
		}

		String mainp = request.getParameter("mainportion");
		String subp = request.getParameter("subportion");

		model.addAttribute("mainp", mainp);
		model.addAttribute("subp", subp);

		return "redirect:/" + mainp + "/" + subp;
	}

	/*
	 * 
	 * @RequestMapping(value =
	 * "/editinstitution_institutionadmin/{id}/{mainstatus}/{substatus}", method =
	 * RequestMethod.GET) public ModelAndView editById(@PathVariable(name = "id")
	 * Integer id, HttpServletRequest req,@PathVariable(name = "mainstatus") String
	 * mainstatus,
	 * 
	 * @PathVariable(name = "substatus") String substatus) { ModelAndView mav = new
	 * ModelAndView("admin/edit_institution_registration"); /// Getting Logged in
	 * user int userid = 0; User userdeail = null; Object principal =
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal(); if
	 * (principal instanceof UserDetails) { String username = ((UserDetails)
	 * principal).getUsername(); userdeail = userrepository.getUserDetail(username);
	 * userid = userdeail.getId(); //
	 * System.out.println("Priting Loggin userdetail: "+userdeail.getClass()); }
	 * else { String username = principal.toString();
	 * System.out.println("Priting else Loggin user: " + username); } /// end
	 * Getting Logged in user System.out.println("Priting Loggin user id: " +
	 * userid);
	 * 
	 * List<Object[]> modulelist =
	 * modulesrepository.findParentModulesByUserId(userid);
	 * mav.addObject("modulelist", modulelist);
	 * 
	 * List<Object[]> modulelist2 =
	 * modulesrepository.findAllModulesByUserId(userid);
	 * mav.addObject("modulelist2", modulelist2);
	 * 
	 * List<InstitutionTypes> InstitutionType = institutiontypesservice.listall();
	 * mav.addObject("InstitutionType",InstitutionType); //List<Country> Country =
	 * countryservice.listall(); List<Country> Country = countryrep.getConutry();
	 * mav.addObject("Country",Country); // List<States> State =
	 * stateservice.listall(); List<States> State=staterep.getstates();
	 * mav.addObject("State", State);
	 * 
	 * // List<Districts> District = districtservice.listall(); List<Districts>
	 * District = districtrepository.getalldistricts(); mav.addObject("District",
	 * District); EditInstitutionRegistration institutionregistration =
	 * editinstitutionregistrationservice.get(id); String username11 =
	 * userrepository.getUser_Name(id); if(username11 !=null) {
	 * System.out.println("trace=="+username11);
	 * mav.addObject("username11",username11);
	 * 
	 * } // mav.addObject("institutionregistration", institutionregistration);
	 * 
	 * 
	 * 
	 * String val="/getinstitution";
	 * 
	 * ActivityLogTable act = new ActivityLogTable();
	 * act.setIpaddress(req.getRemoteAddr());
	 * act.setActivityby(userdeail.getUsername()); Date dt = new Date();
	 * System.out.println("Current date Is ="+dt); act.setLogin_time_stamp(dt);
	 * act.setActivity("edit"); act.setUrl("/edit_institutiondetails");
	 * 
	 * //Umesh Adding here on 14-01-2020 ------- //Added Here For Name And Role
	 * Showing in header
	 * 
	 * List<Object[]> usrname_header_val
	 * =userrepository.getUser_Nameviaid(userdeail.getId());
	 * mav.addObject("usrname_header_val",usrname_header_val); List<Object[]>
	 * rolespresent= roleassignrepository.getrole_nameviauserid(userid);
	 * mav.addObject("rolespresent",rolespresent); //Umesh Adding ends here
	 * 
	 * 
	 * EditApplicantRegistration applicantregistration =
	 * editapplicantregistrationservice.get(id);
	 * mav.addObject("institutionregistration", applicantregistration);
	 * 
	 * 
	 * List<Object[]> datahas=
	 * applicantregistrationrepository.getdata_forinstitute_admin(userid,id); String
	 * vp="";
	 * 
	 * System.out.println("Printing the length = = = =1388 ==="+userid+" id= "
	 * +id+" and size="+datahas.size()+" data ="+datahas.get(0)); Integer uid =
	 * Integer.valueOf(userid);
	 * 
	 * if(datahas.contains(userid)) { vp="valpresent"; mav.addObject("vp",vp); }else
	 * { vp="novalue"; mav.addObject("vp",vp);}
	 * 
	 * mav.addObject("mainp",mainstatus); mav.addObject("subp",substatus);
	 * 
	 * mav.setViewName("admin/edit_institution_registration_instituteadmin");
	 * 
	 * return mav; }
	 * 
	 */
	// Adding ends here------------------------------------------------

}
