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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.ppvfra.entity.EditCompanyRegistration;
import com.ppvfra.entity.InternalUser;
import com.ppvfra.entity.States;
import com.ppvfra.entity.User;
import com.ppvfra.entity.User_Role;
import com.ppvfra.repository.AdduserRepository;
import com.ppvfra.repository.CompanyRegistrationRepository;
import com.ppvfra.repository.CountryRepository;
import com.ppvfra.repository.DistrictRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.StateRepository;
import com.ppvfra.repository.UserAddRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.repository.UserTypesRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.CompanyRegistrationService;
import com.ppvfra.service.CountryService;
import com.ppvfra.service.DistrictService;
import com.ppvfra.service.EditCompanyRegistrationService;
import com.ppvfra.service.StateService;

@Controller
public class CompanyRegistrationController {
	@Autowired
	StateService stateservice;

	@Autowired
	CountryService countryservice;

	@Autowired
	DistrictService districtservice;

	@Autowired
	UserTypesRepository usertypesrepository;

	@Autowired
	RoleAssignRepository roleassignrepository;

	@Autowired
	CountryRepository countryrep;

	@Autowired
	StateRepository staterep;

	@Autowired
	RoleRepository rolerepository;

	@Autowired
	CompanyRegistrationRepository companyregistrationrepository;

	@Autowired
	AdduserRepository adduserrepository;

	@Autowired
	UserRepository userrepository;

	@Autowired
	ModulesRepository modulesrepository;

	@Autowired
	CompanyRegistrationRepository companyregister;

	@Autowired
	DistrictRepository districtrepository;

	@Autowired
	CompanyRegistrationService companyregistrationservice;

	@Autowired
	EditCompanyRegistrationService editcompanyregistrationservice;

	@Autowired
	Role_ModulesRepository rolemodulerepository;

	@Autowired
	ActivityLogService activitylogservice;

	@Autowired
	UserAddRepository useraddrepository;

	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 10;

	@RequestMapping(value = "/CompanyRegistration", method = RequestMethod.GET)
	public String addCompanyRegistration(Model model) {
		CompanyRegistration companyregistration = new CompanyRegistration();

		// List<Country> Country = countryservice.listall();
		List<Country> Country = countryrep.getConutry();
		model.addAttribute("Country", Country);

		// Umesh Adding here on 13-01-2020
		// List<States> State = stateservice.listall();
		List<States> State = staterep.getstates();
		model.addAttribute("State", State);

		// List<Districts> District = districtservice.listall();
		List<Districts> District = districtrepository.getalldistricts();
		model.addAttribute("District", District);
		model.addAttribute("companyregistration", companyregistration);
		return "company_registration";
	}

	@RequestMapping(value = "/newcompanyregistration", method = RequestMethod.POST)
	public String companyRegistrationMethod(
			@Valid @ModelAttribute(value = "companyregistration") CompanyRegistration companyregistration,
			BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			// List<Country> Country = countryservice.listall();
			List<Country> Country = countryrep.getConutry();
			model.addAttribute("Country", Country);
			// List<States> State = stateservice.listall();
			List<States> State = staterep.getstates();
			model.addAttribute("State", State);
			// List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);
			return "company_registration";
		} else {
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			// List<Country> Country = countryservice.listall();
			List<Country> Country = countryrep.getConutry();
			model.addAttribute("Country", Country);

			// List<States> State = stateservice.listall();
			List<States> State = staterep.getstates();
			model.addAttribute("State", State);

			// List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);
			companyregistrationservice.toString();
			companyregistration.setActive(false);
			companyregistration.setCreatedon(ts);
			companyregistration.setCreatedbyip(request.getRemoteAddr());
			companyregistration = companyregistrationservice.saveAndFlush(companyregistration);

			if (companyregistration.getId() != 0) {
				redirectAttributes.addFlashAttribute("message",
						"Thank You for Registration. Your Account is currently inactive. You will be notified via SMS and Email once your request has been accepted by admin.");

				Long companyid = companyregistration.getId();
				int typeid = usertypesrepository.getIdByType("Company");
				int roleid = rolerepository.getIdByName("Company");
				InternalUser internaluser = new InternalUser();
				internaluser.setCompanyid(companyid.intValue());
				internaluser.setName(request.getParameter("Author_Name").trim());
				internaluser.setFullname(request.getParameter("Author_Name").trim());
				internaluser.setEmail(request.getParameter("Author_Email").trim());
				internaluser.setUsername(request.getParameter("username").trim());
				internaluser.setDesignation(request.getParameter("Author_Designation").trim());
				internaluser.setContactno(request.getParameter("Author_Mobile").trim());
				internaluser.setMobile_number(request.getParameter("Author_Mobile").trim());
				internaluser.setUsertypeid(typeid);
				internaluser.setRole("" + roleid);
				internaluser.setFirstname(request.getParameter("Author_Name").trim());
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

	// sargam code starts
	@RequestMapping(value = "/getCompanyPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String mainWithComapnyParam(@RequestBody Map<String, Integer> data, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("max", data.get("max"));
		redirectAttributes.addAttribute("offset", data.get("offset"));
		return "redirect:/getcompany";
	}
	// sargam code ends

	@RequestMapping(value = "/getcompany", method = RequestMethod.GET)
	public String getCompany(Model model, HttpServletRequest request, Optional<Integer> max, Optional<Integer> offset) {
		/// Getting Logged in user
		// sargam code start

		int totalRecords, pageCount, totalPages, recordPages;

		int page = max.orElse(INITIAL_PAGE_SIZE);
		int pageNumber = (offset.orElse(0) < 1) ? INITIAL_PAGE : offset.get() - 1;

		int srno = page * pageNumber;
		Page<CompanyRegistration> compregPage = companyregister.findAll(PageRequest.of(pageNumber, page));
		List<CompanyRegistration> compregList = compregPage.getContent();
		model.addAttribute("compregList", compregList);
		model.addAttribute("srno", srno);
		// sargam --code ends
		int userid = 0;
		User userdeail = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId();
			// System.out.println("GETTTTT"+request.getRequestURL());

		} else {
			String username = principal.toString();
			// System.out.println("GETTTTT2222"+request.getRequestURL());
		}
		/// end Getting Logged in user

		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		model.addAttribute("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		model.addAttribute("modulelist2", modulelist2);

		List<CompanyRegistration> compreg = companyregister.findAll();
		model.addAttribute("compreg", compreg);

		List<States> stateselections = staterep.findAll();
		model.addAttribute("stateselections", stateselections);

		List<Districts> districtselection = districtrepository.findAll();
		model.addAttribute("distsel", districtselection);

		CompanyRegistration cmpreg = new CompanyRegistration();
		model.addAttribute("cmpreg", cmpreg);
		// sargam code start

		totalRecords = compreg.size();
		pageCount = totalRecords / page;
		totalPages = pageCount;
		recordPages = pageCount * page;
		if (recordPages < totalRecords) {
			totalPages = totalPages + 1;
		}
		model.addAttribute("totalPages", totalPages);
		// sargam code ends
		
		StringBuffer url = request.getRequestURL();
		String val = url.substring(url.lastIndexOf("/"), url.length());
		System.out.println("PPPPP===" + val);
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid, val);
		if (actd != null) {
			Object actd_viewtrue = null;
			Object actd_addtrue = null;
			model.addAttribute("actd", actd);

			// System.out.println("PRINTING contains"+actd.contains("View"));

			/*
			 * for (int j =0; j< actd.size() ;j++) { Object[] row= (Object[]) actd.get(j);
			 * try { String multiple = (String)Array.get(row, 0);
			 * if(multiple.equals("View")) { actd_viewtrue ="valpresent";
			 * System.out.println("PRINING TRUEEEEE"+actd_viewtrue);
			 * 
			 * model.addAttribute("actd_viewtrue",actd_viewtrue); } }catch(Exception e) {}
			 * 
			 * }
			 */

			if (actd.contains("View")) {
				actd_viewtrue = "valpresent";
				// System.out.println("PRINING TRUEEEEE"+actd_viewtrue);

				model.addAttribute("actd_viewtrue", actd_viewtrue);

				String val1 = "Accept_Company";
				List<Object[]> accesstypeprocess_data = rolemodulerepository.getAccesstypes_Processdata(userid, val1);
				// System.out.println("PRINTING )))))="+accesstypeprocess_data.contains("Add"));
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
			act.setUrl("/getcompany");

			activitylogservice.save(act);

		}

		// Umesh Adding here on 14-01-2020 -------
		// Added Here For Name And Role Showing in header

		List<Object[]> usrname_header_val = userrepository.getUser_Nameviaid(userdeail.getId());
		model.addAttribute("usrname_header_val", usrname_header_val);
		List<Object[]> rolespresent = roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent", rolespresent);
		// Umesh Adding ends here

		Date date = new Date();

		long time = date.getTime();
		System.out.println("Time in Milliseconds: " + time);
		Timestamp ts = new Timestamp(time);
		System.out.println("Current 342 Time Stamp: " + ts);
		model.addAttribute("time_obt2", ts);

		return "admin/company";
	}

	@RequestMapping(value = "/acceptrejectcompany/{id}", method = RequestMethod.POST)
	public String accept_or_reject(@ModelAttribute(value = "cmpreg") CompanyRegistration cmpreg, Model model,
			HttpServletRequest req, @PathVariable(name = "id") Long id) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail = null;

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId();
			// System.out.println("Priting Loggin user: " + username);
		} else {
			String username = principal.toString();
			// System.out.println("Priting else Loggin user222222222: " + username);
		}
		/// end Getting Logged in user

		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		model.addAttribute("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		model.addAttribute("modulelist2", modulelist2);

		// CompanyRegistration cr = companyregister.companydetails(id);
		if (!(req.getParameter("companystatus").equals("null")) && !(req.getParameter("companystatus").equals(""))) {
			String statustobeset = req.getParameter("companystatus");
			String remarks = req.getParameter("remarks");

			// System.out.println("TRACE INSIDE -----NOT NULL");
			String mip = req.getRemoteAddr();
			int verifierid = 0;
			String verifierdesignation = "";
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				userdeail = userrepository.getUserDetail(username);
				verifierid = userdeail.getId();
				String u = userrepository.getAllDetailsOfUser(verifierid);
				verifierid = userdeail.getId();
				verifierdesignation = u;
				// System.out.println("Priting Loggin user: " + username);
			}
			int company_accepted = 0;
			if (statustobeset.equals("Accepted")) {
				company_accepted = companyregister.dataadding(statustobeset, remarks, id, mip, verifierid,
						verifierdesignation);
				// Umesh Adding here on 07-01-2020

				String resetpass = "ppvfra@2020";
				String mailaddress = "";
				String mailusername = "";
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12
				String encodedPassword = encoder.encode(resetpass);

				try {
					int a = useraddrepository.update_comp(encodedPassword, id.intValue());

					if (a == 1) {
						List<Object[]> mail = companyregistrationrepository.company_email(id.intValue());
						if (mail != null) {
							Object[] r = (Object[]) mail.get(0);
							System.out.println(
									"Printing the object val=" + String.valueOf(r[0]) + "" + String.valueOf(r[1]));
							mailaddress = String.valueOf(r[1]);
							mailusername = String.valueOf(r[2]);
							System.out.println("Printing the mailaddress=" + mailaddress);
						}
						try {
							send_accepted_mail(resetpass, mailaddress, mailusername);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (Exception ee1) {
					System.out.println("COMPANY EXCP==");
				}
			} else {

				String mailaddress1 = "";
				String mailusername1 = "";
				company_accepted = companyregister.dataadding1(statustobeset, remarks, id, mip, verifierid,
						verifierdesignation);
				List<Object[]> mail = companyregistrationrepository.company_email(id.intValue());
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

			if (company_accepted == 1) {
				if (statustobeset.equals("Rejected")) {
					companyregister.dataupdate1(id.intValue());
				} else {
					companyregister.dataupdate(id.intValue());
				}
			}
		}

		// cr.setVerificationstatus(statustobeset);
		// cr.setVerificationremarks(remarks);

		// companyregister.saveAndFlush(cr);

		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(req.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is =" + dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("save");
		act.setUrl("/acceptrejectcompany");

		activitylogservice.save(act);

		return "redirect:/getcompany";
	}

	boolean send_accepted_mail(String pass, String mailadd, String username) throws IOException {
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
						// sb.append("<br/><br/>Ms " + orgname
						sb.append("<br/><br/>Your Default Password is:  " + pass
								+ " You May Change It Via Accessing Your Profile Section and then change Password");
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

	@RequestMapping(value = "/view_applicationdetails/{id}", method = RequestMethod.GET)
	public String viewbyidpassing(@PathVariable(name = "id") Long id, Model mav, HttpServletRequest req) {

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
		// Umesh Adding here on 14-01-2020 -------
		// Added Here For Name And Role Showing in header

		List<Object[]> usrname_header_val = userrepository.getUser_Nameviaid(userdeail.getId());
		mav.addAttribute("usrname_header_val", usrname_header_val);
		List<Object[]> rolespresent = roleassignrepository.getrole_nameviauserid(userid);
		mav.addAttribute("rolespresent", rolespresent);
		// Umesh Adding ends here
		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		mav.addAttribute("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		mav.addAttribute("modulelist2", modulelist2);

		List<States> office_state = staterep.findAll();
		mav.addAttribute("office_state", office_state);

		CompanyRegistration companyregistration = companyregistrationservice.get(id);
		mav.addAttribute("companyregistration", companyregistration);

		List<Object[]> details = companyregister.findDetails(id);
		mav.addAttribute("details", details);

		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(req.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is =" + dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("view");
		act.setUrl("/viewapplication");

		activitylogservice.save(act);

		return "view_company_registration";
	}

	@RequestMapping(value = "/editcompany/{id}", method = RequestMethod.GET)
	public ModelAndView editCompany(@PathVariable(name = "id") Long id, HttpServletRequest request) {
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

		// List<Country> Country = countryservice.listall();
		List<Country> Country = countryrep.getConutry();
		mav.addObject("Country", Country);

		// List<States> State = stateservice.listall();
		List<States> State = staterep.getstates();
		mav.addObject("State", State);

		// List<Districts> District = districtservice.listall();
		List<Districts> District = districtrepository.getalldistricts();
		mav.addObject("District", District);

		EditCompanyRegistration companyregistration = editcompanyregistrationservice.get(id);
		String username11 = userrepository.getUserName(id.intValue());
		if (username11 != null) {
			// System.out.println("trace=="+username11);
			mav.addObject("username11", username11);

		}
		mav.addObject("companyregistration", companyregistration);

		String val = "/getcompany";
		System.out.println("PPPPP===" + val);
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid, val);
		if (actd != null) {
			Object actd_viewtrue = null;
			Object actd_addtrue = null;
			mav.addObject("actd", actd);

			// System.out.println("PRINTING contains"+actd.contains("View"));

			if (actd.contains("View")) {
				actd_viewtrue = "valpresent";
				// System.out.println("PRINING TRUEEEEE"+actd_viewtrue);
				mav.addObject("actd_viewtrue", actd_viewtrue);
			}

		}
		mav.setViewName("admin/edit_company_registration");

		ActivityLogTable act = new ActivityLogTable();
		act.setIpaddress(request.getRemoteAddr());
		act.setActivityby(userdeail.getUsername());
		Date dt = new Date();
		System.out.println("Current date Is =" + dt);
		act.setLogin_time_stamp(dt);
		act.setActivity("edit");
		act.setUrl("/editcompany");

		activitylogservice.save(act);

		// Umesh Adding here on 14-01-2020 -------
		// Added Here For Name And Role Showing in header

		List<Object[]> usrname_header_val = userrepository.getUser_Nameviaid(userdeail.getId());
		mav.addObject("usrname_header_val", usrname_header_val);
		List<Object[]> rolespresent = roleassignrepository.getrole_nameviauserid(userid);
		mav.addObject("rolespresent", rolespresent);
		// Umesh Adding ends here

		return mav;
	}

	@RequestMapping(value = "/editcompanyregistration", method = RequestMethod.POST)
	public String editCompanyRegistrationMethod(
			@Valid @ModelAttribute(value = "companyregistration") EditCompanyRegistration companyregistration,
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

			// List<Country> Country = countryservice.listall();
			List<Country> Country = countryrep.getConutry();
			model.addAttribute("Country", Country);
			// List<States> State = stateservice.listall();
			List<States> State = staterep.getstates();
			model.addAttribute("State", State);

			// List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);

			String val = "/getcompany";
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
			act.setActivity("edit-error");
			act.setUrl("/editcompanyregistration");

			activitylogservice.save(act);

			return "admin/edit_company_registration";
		} else {
			int userid11 = 0;
			User userdeail = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				userdeail = userrepository.getUserDetail(username);
				userid11 = userdeail.getId();
				// System.out.println("Priting Loggin userdetail: "+userdeail.getClass());
			} else {
				String username = principal.toString();
				// System.out.println("Priting else Loggin user: " + username);
			}

			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			// List<Country> Country = countryservice.listall();
			List<Country> Country = countryrep.getConutry();
			model.addAttribute("Country", Country);
			// List<States> State = stateservice.listall();

			List<States> State = staterep.getstates();
			model.addAttribute("State", State);

			// List<Districts> District = districtservice.listall();
			List<Districts> District = districtrepository.getalldistricts();
			model.addAttribute("District", District);
			editcompanyregistrationservice.toString();
			EditCompanyRegistration companyregistration11 = editcompanyregistrationservice
					.get(companyregistration.getId());
			if (companyregistration11 != null) {
				if (companyregistration11.getActive() != null) {
					companyregistration.setActive(companyregistration11.getActive());
				}

				if (companyregistration11.getVerificationstatus() != null) {
					companyregistration.setVerificationstatus(companyregistration11.getVerificationstatus());
				}

				if (companyregistration11.getVerificationremarks() != null) {
					companyregistration.setVerificationremarks(companyregistration11.getVerificationremarks());
				}
			}
			companyregistration.setCreatedon(ts);
			companyregistration.setCreatedbyip(request.getRemoteAddr());
			companyregistration = editcompanyregistrationservice.saveAndFlush(companyregistration);

			if (companyregistration.getId() != 0) {
				redirectAttributes.addFlashAttribute("message", "Data saved successfully.");

				int companyid = companyregistration.getId().intValue();
				int typeid = usertypesrepository.getIdByType("Company");
				int roleid = rolerepository.getIdByName("Company");
				InternalUser internaluser = new InternalUser();
				String userid = userrepository.getUseridvia_Companyid(companyid);
				if (userid != null && (Integer.parseInt(userid)) != 0) {
					internaluser.setId(Integer.parseInt(userid));
				}
				internaluser.setCompanyid(companyid);
				internaluser.setName(request.getParameter("Author_Name"));
				internaluser.setFullname(request.getParameter("Author_Name"));
				internaluser.setEmail(request.getParameter("Author_Email").trim());
				internaluser.setUsername(request.getParameter("username"));
				internaluser.setDesignation(request.getParameter("Author_Designation"));
				internaluser.setContactno(request.getParameter("Author_Mobile"));
				internaluser.setMobile_number(request.getParameter("Author_Mobile"));
				internaluser.setUsertypeid(typeid);
				internaluser.setRole("" + roleid);
				internaluser.setFirstname(request.getParameter("Author_Name"));
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

				if (companyregistration11.getVerificationstatus().equals("Accepted")) {
					internaluser.setIsactive(true);
				} else if (companyregistration11.getVerificationstatus().equals("Rejected")) {
					internaluser.setIsactive(false);
				} else {
					internaluser.setIsactive(null);
				}

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
			act.setActivity("edit-error");
			act.setUrl("/editcompanyregistration");

			activitylogservice.save(act);
		}
		return "redirect:/getcompany";
	}

	//code for excel extraction
	
	@RequestMapping(value = "/getexcel", method = RequestMethod.GET)
	public void getCompanydetails_excel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		DateFormat  dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateformatter.format(new Date());
		String fileName = "company_" + currentDateTime + ".xlsx";
		String headerValue = "attachment; filename=" + fileName;
		
		response.setHeader(headerKey, headerValue);
		List<CompanyRegistration> compreg = companyregister.findAll();
		List<States> stateselections = staterep.findAll();
		List<Districts> districtselection = districtrepository.findAll();
		
		companyregistrationservice.export(response,compreg,stateselections,districtselection);
	}
	
	//code for excel extraction
	
	
	
//Umesh Adding here on 04-01-2020 ------for chart clicking

	@RequestMapping(value = "/getcompany/{status}", method = RequestMethod.GET)
	public String getCompanydetails_statuswise(@PathVariable(name = "status") String status, Model model,
			HttpServletRequest request) {
		/// Getting Logged in user
		int userid = 0;
		User userdeail = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			userdeail = userrepository.getUserDetail(username);
			userid = userdeail.getId();
			// System.out.println("GETTTTT"+request.getRequestURL());

		} else {
			String username = principal.toString();
		}

		List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		model.addAttribute("modulelist", modulelist);

		List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		model.addAttribute("modulelist2", modulelist2);
		if (status.equals("New")) {
			List<CompanyRegistration> compreg = companyregister.findvia_status_NewComp();
			model.addAttribute("compreg", compreg);
		} else {
			List<CompanyRegistration> compreg = companyregister.findvia_status(status);
			model.addAttribute("compreg", compreg);
		}

		List<States> stateselections = staterep.findAll();
		model.addAttribute("stateselections", stateselections);

		List<Districts> districtselection = districtrepository.findAll();
		model.addAttribute("distsel", districtselection);

		CompanyRegistration cmpreg = new CompanyRegistration();
		model.addAttribute("cmpreg", cmpreg);

		StringBuffer url = request.getRequestURL();
		String val = url.substring(url.lastIndexOf("/") - 11, url.lastIndexOf("/"));
		System.out.println("PPPPP===" + val);
		List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid, val);
		if (actd != null) {
			Object actd_viewtrue = null;
			Object actd_addtrue = null;
			model.addAttribute("actd", actd);

			// System.out.println("PRINTING contains"+actd.contains("View"));

			/*
			 * for (int j =0; j< actd.size() ;j++) { Object[] row= (Object[]) actd.get(j);
			 * try { String multiple = (String)Array.get(row, 0);
			 * if(multiple.equals("View")) { actd_viewtrue ="valpresent";
			 * System.out.println("PRINING TRUEEEEE"+actd_viewtrue);
			 * 
			 * model.addAttribute("actd_viewtrue",actd_viewtrue); } }catch(Exception e) {}
			 * 
			 * }
			 */

			if (actd.contains("View")) {
				actd_viewtrue = "valpresent";
				// System.out.println("PRINING TRUEEEEE"+actd_viewtrue);

				model.addAttribute("actd_viewtrue", actd_viewtrue);

				String val1 = "Accept_Company";
				List<Object[]> accesstypeprocess_data = rolemodulerepository.getAccesstypes_Processdata(userid, val1);
				// System.out.println("PRINTING )))))="+accesstypeprocess_data.contains("Add"));
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
			act.setUrl("/getcompany");

			activitylogservice.save(act);

		}

		// Umesh Adding here on 14-01-2020 -------
		// Added Here For Name And Role Showing in header

		List<Object[]> usrname_header_val = userrepository.getUser_Nameviaid(userdeail.getId());
		model.addAttribute("usrname_header_val", usrname_header_val);
		List<Object[]> rolespresent = roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent", rolespresent);
		// Umesh Adding ends here

		return "admin/company";
	}

}
