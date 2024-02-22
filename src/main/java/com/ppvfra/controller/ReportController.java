package com.ppvfra.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ppvfra.entity.CropGroup;
import com.ppvfra.entity.ActivityLogTable;
import com.ppvfra.entity.Applications;
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.ReportTen;
import com.ppvfra.entity.States;
import com.ppvfra.entity.User;
import com.ppvfra.entity.ViewDusTestReport;
import com.ppvfra.entity.ViewStateWiseReport;
import com.ppvfra.entity.ViewStateWiseReport7;
import com.ppvfra.repository.AddCropGroupRepository;
import com.ppvfra.repository.AddCropsRepository;
import com.ppvfra.repository.ApplicationsRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.StateRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ApplicationsService;
import com.ppvfra.service.DistrictService;
import com.ppvfra.service.ModuleService;
import com.ppvfra.service.StateService;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.AddCropGroupService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ReportController 
{
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	AddCropGroupService addcropgroupservice;
	
	@Autowired
	AddCropGroupRepository addcropgrprep;
	
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	@Autowired
	ModuleService moduleservice;
	
	@Autowired
	Environment env;
	
	@Autowired
	AddCropsRepository cropsrepository;
	
	@Autowired
	ModulesRepository modulerepository;

	@Autowired
	ApplicationsRepository applicationsrepository;

	@Autowired
	StateService stateservice;
	
	@Autowired
	ApplicationsService applicationservice;
	
	@Autowired
	AddCropsRepository addcroprepository;

	@Autowired		   
	DistrictService districtservice;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@RequestMapping(value = "/listofapplicationcropwise", method = RequestMethod.GET)
	public String getReport1(Model model) {
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
			}
			/// end Getting Logged in user
			 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	 
	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
		  if(Crop_Group!= null)
			model.addAttribute("Crop_Group", Crop_Group);
		  
		  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
	      model.addAttribute("addcroplist", addcroplist);

		return "report/listofcropwisereport";
	}
	
	@RequestMapping(value = "/listofapplicationreceived", method = RequestMethod.GET)
	public String getReport2(Model model) {
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
			}
			/// end Getting Logged in user
			
			List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
			  if(Crop_Group!= null)
				model.addAttribute("Crop_Group", Crop_Group);
			  
			  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
		      model.addAttribute("addcroplist", addcroplist);
	  
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		
		return "report/detailedlistapplicationsreceived";
	}
	
	@RequestMapping(value = "/listofapplicatioclosed", method = RequestMethod.GET)
	public String getReport3(Model model) {
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
			}
			/// end Getting Logged in user
			 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  List<Object[]> Stages = applicationsrepository.getstatus();
	  if(Stages!= null) {
		  model.addAttribute("Stages", Stages);
	  }
	  
	  List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
	  if(Crop_Group!= null)
		model.addAttribute("Crop_Group", Crop_Group);
	  
	  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
      model.addAttribute("addcroplist", addcroplist);

	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
			List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
			model.addAttribute("rolespresent",rolespresent);
		return "report/listofapplicatioclosed";
	}
	
	@RequestMapping(value = "/listofapplicationspending", method = RequestMethod.GET)
	public String getReport4(Model model) {
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
			}
			/// end Getting Logged in user
			 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  List<Object[]> Stages = applicationsrepository.getlegalstatus();
	  if(Stages!= null) {
		  model.addAttribute("Stages", Stages);
	  }
	  
	  List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
	  if(Crop_Group!= null)
		model.addAttribute("Crop_Group", Crop_Group);
	  
	  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
      model.addAttribute("addcroplist", addcroplist);

	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		
		return "report/listofapplicationspending";
	}
	
	@RequestMapping(value = "/registeredvarietiescertificate", method = RequestMethod.GET)
	public String getReportRegisteredVarietiesCertificate(Model model) {
		/// Getting Logged in user
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
			/// end Getting Logged in user
			 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	 //List<Object[]> Stages = applicationsrepository.getstatus();
	  List<Object[]> Stages = applicationsrepository.getregisterstatus();
	  if(Stages!= null) {
		  model.addAttribute("Stages", Stages);
	  }
	  
	  List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
	  if(Crop_Group!= null)
		model.addAttribute("Crop_Group", Crop_Group);
	  
	  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
      model.addAttribute("addcroplist", addcroplist);

	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		
		model.addAttribute("rvc","rvc");
		
		return "report/registeredvarietiescertificate";
	}
	
	@RequestMapping(value = "/testresultreports", method = RequestMethod.GET)
	public String getDusTestResultReports(Model model) {
		/// Getting Logged in user
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
			/// end Getting Logged in user
			 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  //List<Object[]> Stages = applicationsrepository.getstatus();
	    List<Object[]> Stages = applicationsrepository.getdus_result_received();
	  if(Stages!= null) {
		  model.addAttribute("Stages", Stages);
	  }
	  
	  List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
	  if(Crop_Group!= null)
		model.addAttribute("Crop_Group", Crop_Group);
	  
	  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
      model.addAttribute("addcroplist", addcroplist);

	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		
		return "report/testresultreports";
	}
	
	@RequestMapping(value = "/statewisereports", method = RequestMethod.GET)
	public String geCityStatewiseReports(Model model) {
		/// Getting Logged in user
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
			/// end Getting Logged in user
			 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  List<Object[]> Stages = applicationsrepository.getstatus();
	  if(Stages!= null) {
		  model.addAttribute("Stages", Stages);
	  }
	  List<States> State = stateservice.listall();
	  model.addAttribute("State", State);
	  
	  List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
	  if(Crop_Group!= null)
		model.addAttribute("Crop_Group", Crop_Group);
	  
	  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
      model.addAttribute("addcroplist", addcroplist);

	  List<Districts> District = districtservice.listall();
	  model.addAttribute("District", District);
	  
	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		
		return "report/statewisereports";
	}
	
	@RequestMapping(value = "/yearwisereports", method = RequestMethod.GET)
	public String geYearWiseReports(Model model) {
		/// Getting Logged in user
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
			/// end Getting Logged in user
			 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  List<Object[]> Stages = applicationsrepository.getstatus();
	  if(Stages!= null) {
		  model.addAttribute("Stages", Stages);
	  }
	  List<States> State = stateservice.listall();
	  model.addAttribute("State", State);
	  
	  List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
	  if(Crop_Group!= null)
		model.addAttribute("Crop_Group", Crop_Group);
	  
	  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
      model.addAttribute("addcroplist", addcroplist);

      
	  List<Districts> District = districtservice.listall();
	  model.addAttribute("District", District);
		
	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		
		return "report/yearwisereports";
	}
	
	@RequestMapping(value = "/yearonyearreview", method = RequestMethod.GET)
	public String getYearOnYearReviewReports(Model model) {
		/// Getting Logged in user
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
			/// end Getting Logged in user
	List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
	  if(Crop_Group!= null)
		model.addAttribute("Crop_Group", Crop_Group);
	  
	  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
      model.addAttribute("addcroplist", addcroplist);
 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  List<Object[]> Stages = applicationsrepository.getstatus();
	  if(Stages!= null) {
		  model.addAttribute("Stages", Stages);
	  }
	  
	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		
		return "report/yearonyearreview";
	}
	
	@RequestMapping(value = "/cropwise", method = RequestMethod.GET)
	public String getCropGroupWiseReport(Model model) {
		/// Getting Logged in user
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
			/// end Getting Logged in user
			 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  List<Object[]> Stages = applicationsrepository.getstatus();
	  if(Stages!= null) {
		  model.addAttribute("Stages", Stages);
	  }
	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		
		List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
		  if(Crop_Group!= null)
			model.addAttribute("Crop_Group", Crop_Group);
		  
		  List <Crops> addcroplist = addcroprepository.getAllCropsByOrderbyCommonName();
	      model.addAttribute("addcroplist", addcroplist);

		return "report/cropgroupwise";
	}
	
	@RequestMapping(value = "/registrarwise", method = RequestMethod.GET)
	public String getRegistrarwiseReport(Model model) {
		/// Getting Logged in user
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
			/// end Getting Logged in user
			 		
	  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
	  model.addAttribute("modulelist", modulelist);
	
	  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
	  model.addAttribute("modulelist2", modulelist2);
	  
	  List<Object[]> Stages = applicationsrepository.getstatus();
	  if(Stages!= null) {
		  model.addAttribute("Stages", Stages);
	  }
	  List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
		model.addAttribute("usrname_header_val",usrname_header_val);
		List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
		model.addAttribute("rolespresent",rolespresent);
		
		return "report/registrarwisereport";
	}
	@RequestMapping(value = {"/getReportData2"}, method = RequestMethod.POST)
	@ResponseBody
	public List<Object[]> getReportData2(@RequestBody String data,HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView model = new ModelAndView();
		String refromdate =  request.getParameter("f_date");
	 	String retodate  = request.getParameter("to_date");
	 	String reapplicantname = request.getParameter("applicantname");
	 	int cropgroup = Integer.parseInt(request.getParameter("cropgroup"));
	 	int crop = Integer.parseInt(request.getParameter("crop"));
	 	String denomination = request.getParameter("denomination");
	 	
	 	java.util.Date datee=new java.util.Date();
		Timestamp ts=new Timestamp(datee.getTime());
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(refromdate);
			if(retodate =="" || retodate ==null)
			{
			date2= new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());	
			}else {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("printing the dates as = "+date1+ " "+date2);
		List <Object[]> reportdata = applicationsrepository.findReport2ByFromdataandToDate(date1,date2,reapplicantname,cropgroup,crop,denomination); 
	 	model.addObject("reportdata", reportdata);
	 	String format="pdf"; 
	 	
	 	try {
	 		
	 		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
			 retodate =df.format(date2);
		System.out.println("Printing data="+format+" " +refromdate+ " "
		+ "" +retodate+ " "+reapplicantname+ " "+cropgroup+ " "+crop+" "
		+ ""+denomination);
			exportReport2(format,refromdate,retodate,reapplicantname,cropgroup,crop,denomination);
			 } 
	 		catch
			 (FileNotFoundException | JRException e) { // TODO Auto-generated catch block
			 e.printStackTrace(); 
			 }
		return  reportdata;
	 	  }
	
	@RequestMapping(value = {"/getReportData3"}, method = RequestMethod.POST)
	@ResponseBody
	public List<Object[]> getReport3(@RequestBody String formdata, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		ModelAndView model = new ModelAndView();
		
		String refromdate =  request.getParameter("f_date");
	 	String retodate  = request.getParameter("to_date");
	 	//int status = Integer.parseInt(request.getParameter("status"));
	 	String reapplicantname;
	 	if(request.getParameter("applicantname") != null && request.getParameter("applicantname")!=""){
	 		reapplicantname = request.getParameter("applicantname");
	 	}
	 	else {
	 		reapplicantname = "";
	 	}
	 	int cropgroup = Integer.parseInt(request.getParameter("cropgroup"));
	 	
	 	int crop = Integer.parseInt(request.getParameter("crop"));
	 	
	 	String denomination;
	 	if(request.getParameter("denomination") != null && request.getParameter("denomination") !="") {
	 	denomination = request.getParameter("denomination");
	 	}
	 	else {
	 		denomination = "";
	 	}
	 
	 	java.util.Date datee=new java.util.Date();
		Timestamp ts=new Timestamp(datee.getTime());
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(refromdate);
			
			//date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			if(retodate =="" || retodate ==null)
			{
			date2= new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());	
			}else {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//List<Object[]> reportdata = applicationsrepository.findReport3(date1,date2,reapplicantname,cropgroup,crop,denomination,status);
		List<Object[]> reportdata = applicationsrepository.findReport3(date1,date2,reapplicantname,cropgroup,crop,denomination);
	 	if(reportdata != null) {
	 	    model.addObject("reportdata", reportdata);

		 } 
	 	String format="pdf"; 
	 	
	 	try {
			
	 		System.out.println("Trace on line number 662");
	 		
	 		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
			 retodate =df.format(date2);
			
			 // exportReport3(format,refromdate,retodate,reapplicantname,cropgroup,crop,denomination,status);
			 exportReport3(format,refromdate,retodate,reapplicantname,cropgroup,crop,denomination);
			
	 	    } 
	 		catch
			 (FileNotFoundException | JRException e) { // TODO Auto-generated catch block
			 e.printStackTrace(); 
			 }
		return  reportdata;
	}
	
	@RequestMapping(value = {"/getReportData4"}, method = RequestMethod.POST)
	@ResponseBody
	public List<Object[]> getReport4(@RequestBody String formdata, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		ModelAndView model = new ModelAndView();
		String refromdate =  request.getParameter("f_date");
	 	String retodate  = request.getParameter("to_date");
	 	int status = Integer.parseInt(request.getParameter("status"));
	 	int cropgroup = Integer.parseInt(request.getParameter("cropgroup"));
	 	int crop =Integer.parseInt(request.getParameter("crop"));
	 	String reapplicantname;
	 	if(request.getParameter("applicantname") != null && request.getParameter("applicantname")!=""){
	 		reapplicantname = request.getParameter("applicantname");
	 	}
	 	else {
	 		reapplicantname = "";
	 	} 	
	 	String denomination;
	 	if(request.getParameter("denomination") != null && request.getParameter("denomination") !="") {
	 	denomination = request.getParameter("denomination");
	 	}
	 	else {
	 		denomination = "";
	 	}
	 	java.util.Date datee=new java.util.Date();
		Timestamp ts=new Timestamp(datee.getTime());
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(refromdate);
			//date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			
			if(retodate =="" || retodate ==null)
			{
			date2= new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());	
			}else {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Object[]> reportdata = applicationsrepository.findReport4(date1,date2,reapplicantname,cropgroup,crop,denomination,status); 
	 	System.out.println("Trace printing 734 reportdata ="+reportdata.size());
		if(reportdata != null) {
	 	    model.addObject("reportdata", reportdata);

		 } 
	 	String format="pdf"; 
	 	try {
	 		
	 		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
			retodate =df.format(date2);
			System.out.println("Traceonline ------ 744 -----"+retodate +" "+refromdate);
			exportReport4(format,refromdate,retodate,reapplicantname,cropgroup,crop,denomination,status);
			 } 
	 		catch
			 (FileNotFoundException | JRException e) { // TODO Auto-generated catch block
			 e.printStackTrace(); 
			 }
		return  reportdata;
	}
	
	@RequestMapping(value = {"/getReportData5"}, method = RequestMethod.POST)
	@ResponseBody
	public List<Object[]> getReport5(@RequestBody String formdata, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		ModelAndView model = new ModelAndView();
		
		String refromdate =  request.getParameter("f_date");
	 	String retodate  = request.getParameter("to_date");
	 	int status = Integer.parseInt(request.getParameter("status"));
	 	String reapplicantname;
	 	if(request.getParameter("applicantname") != null && request.getParameter("applicantname")!=""){
	 		reapplicantname = request.getParameter("applicantname");
	 	}
	 	else {
	 		reapplicantname = "";
	 	}
	 	int cropgroup = Integer.parseInt(request.getParameter("cropgroup"));
	 	int crop = Integer.parseInt(request.getParameter("crop"));
	 	
	 	String denomination;
	 	if(request.getParameter("denomination") != null && request.getParameter("denomination") !="") {
	 	denomination = request.getParameter("denomination");
	 	}
	 	else {
	 		denomination = "";
	 	}
	 
	 	java.util.Date datee=new java.util.Date();
		Timestamp ts=new Timestamp(datee.getTime());
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(refromdate);
			if(retodate == null || retodate =="")
			{
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());
			}else {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Printing dus test result Status = "+status+"and dates as "+date1+", "+date2);
	 	List<Object[]> reportdata = applicationsrepository.findReport5(date1,date2,reapplicantname,cropgroup,crop,denomination,status); 
	 	
	 	System.out.println("Trace on line  ------ 793 "+reportdata.size());
	 	if(reportdata != null) {
	 	    model.addObject("reportdata", reportdata);

		 } 
		
		 String format="pdf"; 
		 
		 try {
			 System.out.println("Trace on line  ------ 810 "); 
			 DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
			 retodate =df.format(date2);
			 exportReport5(format,refromdate,retodate,reapplicantname,
				cropgroup,crop,denomination,status); 
		 }
		  catch (FileNotFoundException | JRException e)  { //TODO Auto-generated catch block e.printStackTrace(); 
		  }
		 
		return  reportdata;
	}
	
	@RequestMapping(value = {"/getReportData6"}, method = RequestMethod.POST)
	@ResponseBody
	public List<Object[]> getReport6(@RequestBody String formdata, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		ModelAndView model = new ModelAndView();
		
		String refromdate =  request.getParameter("f_date");
		System.out.println("printing from date"+refromdate);
	 	String retodate  = request.getParameter("to_date");
	 	int status = Integer.parseInt(request.getParameter("status"));
	 	int statecode = Integer.parseInt(request.getParameter("statecode"));
	 	int districtcode = Integer.parseInt(request.getParameter("districtcode"));
	 	
	 	//System.out.println("printing status: "+status);
	 	String reapplicantname;
	 	if(request.getParameter("applicantname") != null && request.getParameter("applicantname")!=""){
	 		reapplicantname = request.getParameter("applicantname");
	 	}
	 	else {
	 		reapplicantname = "";
	 	}
	 	int cropgroup = Integer.parseInt(request.getParameter("cropgroup"));
	 	
	 	int crop = Integer.parseInt(request.getParameter("crop"));
	 	
	 	String denomination;
	 	if(request.getParameter("denomination") != null && request.getParameter("denomination") !="") {
	 	denomination = request.getParameter("denomination");
	 	}
	 	else {
	 		denomination = "";
	 	}
	 
	 	java.util.Date datee=new java.util.Date();
		Timestamp ts=new Timestamp(datee.getTime());
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(refromdate);
			if(retodate !=null && !(retodate ==""))
			{
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			System.out.println("Trace for date not null = 785 line number"+date1+" and 2nd as ="+date2);
			}else {
				
				Date d =new Date();
				String pattern="yyyy-MM-dd";
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				date2=sdf.parse(LocalDate.now().toString());
				System.out.println("Trace on 790 for local date as =" +date2+"and d as ="+d.getTime()+" "+d.getDate());
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Object[]> reportdata = applicationsrepository.findReport6(date1,date2,reapplicantname,cropgroup,crop,denomination,status,statecode,districtcode); 
	 	System.out.println("Printing report data="+reportdata.size()+"reportdata="+reportdata);
		if(reportdata != null) {
	 	    model.addObject("reportdata", reportdata);

		 } 
	 	String format="pdf"; 
	 	try {
	 		//Commenting on 11-05-2020
	 		//	exportReport6(format,refromdate,retodate,reapplicantname,cropgroup,crop,denomination,status,statecode,districtcode);
			DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
			String strfrmt =df.format(date2);
			System.out.println("Trace on 828 = "+date2+" and other ="+strfrmt+" refor"
			+ "m date="+refromdate+"applicant name ="+reapplicantname+"cr"
			+ "op group ="+cropgroup+" and crop = "+crop+" denomination = "+denomination+""
			+ "status"+status+" statecode= "+statecode+" districtcode "+districtcode);
	 		exportReport6(format,refromdate,strfrmt,reapplicantname,cropgroup,crop,denomination,status,statecode,districtcode);
			 System.out.println("Trace on line no 807 ");
			 } 
	 		catch
			 (FileNotFoundException | JRException e) { // TODO Auto-generated catch block
			 e.printStackTrace(); 
			 }
		return  reportdata;
	}
	
	@RequestMapping(value = {"/getYearWiseReport8"}, method = RequestMethod.POST)
	@ResponseBody
	public List<Object[]> getYearWiseReport8(@RequestBody String formdata, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		ModelAndView model = new ModelAndView();
		
		String refromdate =  request.getParameter("f_date");
	 	String retodate  = request.getParameter("to_date");
	 	int status = Integer.parseInt(request.getParameter("status"));
	 	int statecode = Integer.parseInt(request.getParameter("statecode"));
	 	int districtcode = Integer.parseInt(request.getParameter("districtcode"));
	 	String reapplicantname;
	 	if(request.getParameter("applicantname") != null && request.getParameter("applicantname")!=""){
	 		reapplicantname = request.getParameter("applicantname");
	 	}
	 	else {
	 		reapplicantname = "";
	 	}
	 	int cropgroup = Integer.parseInt(request.getParameter("cropgroup"));
	 	
	 	int crop = Integer.parseInt(request.getParameter("crop"));
	 	
	 	String denomination;
	 	if(request.getParameter("denomination") != null && request.getParameter("denomination") !="") {
	 	denomination = request.getParameter("denomination");
	 	}
	 	else {
	 		denomination = "";
	 	}
	 
	 	java.util.Date datee=new java.util.Date();
		Timestamp ts=new Timestamp(datee.getTime());
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(refromdate);
			
			//date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			if(retodate =="" || retodate ==null)
			{
			date2= new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());	
			}else {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Object[]> reportdata = applicationsrepository.findReport8(date1,date2,reapplicantname,cropgroup,crop,denomination,status,statecode,districtcode); 
	 	if(reportdata != null) {
	 	    model.addObject("reportdata", reportdata);

		 } 
	 	
		return  reportdata;
	}
	
	public String getReport4test(HttpServletRequest request,HttpServletResponse response,Model model) throws ParseException {
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
			  model.addAttribute("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addAttribute("modulelist2", modulelist2);
		String refromdate =  request.getParameter("fromDate");
	 	String retodate  = request.getParameter("todate");
	 	int status = Integer.parseInt(request.getParameter("status"));
	 	//System.out.println("printing status: "+status);
	 	String reapplicantname;
	 	if(request.getParameter("applicantname") != null && request.getParameter("applicantname")!=""){
	 		reapplicantname = request.getParameter("applicantname");
	 	}
	 	else {
	 		reapplicantname = "";
	 	}
	 	int cropgroup = Integer.parseInt(request.getParameter("cropgroup"));
	 	int crop =Integer.parseInt(request.getParameter("crop"));
	 	
	 	String denomination;
	 	if(request.getParameter("denomination") != null && request.getParameter("denomination") !="") {
	 	denomination = request.getParameter("denomination");
	 	}
	 	else {
	 		denomination = "";
	 	}
	 
	 	java.util.Date datee=new java.util.Date();
		Timestamp ts=new Timestamp(datee.getTime());
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(refromdate);
			
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 	System.out.println("printing report data four values: from date: "+refromdate+",to date: "+retodate+",applicant Name "+reapplicantname+",crop group:  "+cropgroup+",Denomination:  "+denomination+", crop: "+crop);
	 	List<Object[]> reportdata = applicationsrepository.findReport4(date1,date2,reapplicantname,cropgroup,crop,denomination,status); 
	 	if(reportdata != null) {
	 		System.out.println("checking null condition");
	 	    model.addAttribute("reportdata", reportdata);

		 } 
	 	
	 	return "report/report4";
	}
	@RequestMapping(value = {"/getCropGroupWiseReport10"}, method = RequestMethod.POST)
	@ResponseBody
	public List<Object[]> getCropGroupWiseReport10(@RequestBody String formdata, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		ModelAndView model = new ModelAndView();
		String refromdate =  request.getParameter("f_date");
	 	String retodate  = request.getParameter("to_date");
	 	int cropgroup = Integer.parseInt(request.getParameter("cropgroup"));
	 	
	 	java.util.Date datee=new java.util.Date();
		Timestamp ts=new Timestamp(datee.getTime());
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(refromdate);
			if(retodate == null || retodate=="")
			{
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());	
			}else {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("printing report data crop wise value values: from date: "+refromdate+",to date: "+retodate+",crop group:  "+cropgroup);
	 	List<Object[]> reportdata = applicationsrepository.findReport10Html(date1,date2,cropgroup); 
	 	System.out.println("printing on line number 1031 ----- "+reportdata.size());
	 	if(reportdata != null) {
	 	    model.addObject("reportdata", reportdata);
	 		}
	 	
	 	String format="pdf"; 
	 	
	 	try {
	 		
	 		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
			retodate =df.format(date2);
			exportReport10(format,refromdate,retodate,cropgroup);
			 } 
	 		catch
			 (FileNotFoundException | JRException e) { // TODO Auto-generated catch block
			 e.printStackTrace(); 
			 }
		return  reportdata;
	}
	
	
	@RequestMapping(value = {"/getRegistrarWiseReport"}, method = RequestMethod.POST)
	@ResponseBody
	public List<Object[]> getRegistrarWiseReport(@RequestBody String formdata, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		ModelAndView model = new ModelAndView();
		String refromdate =  request.getParameter("f_date");
	 	String retodate  = request.getParameter("to_date");
	 	String registrar;
	 	if(request.getParameter("denomination") != null && request.getParameter("denomination") !="") {
	 		registrar = request.getParameter("denomination");
	 	}
	 	else {
	 		registrar = "";
	 	}
	 	 
	 	java.util.Date datee=new java.util.Date();
		Timestamp ts=new Timestamp(datee.getTime());
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(refromdate);
			
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("printing report data crop wise value values: from date: "+refromdate+",to date: "+retodate+",registrar:  "+registrar);
	 	List<Object[]> reportdata = applicationsrepository.findReport11(date1,date2,registrar); 
	 	if(reportdata != null) {
	 	    model.addObject("reportdata", reportdata);

		 }
		/*
		 * String format="pdf"; try {
		 * exportReport10(format,refromdate,retodate,cropgroup); } catch
		 * (FileNotFoundException | JRException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return  reportdata;
	}
	
	@RequestMapping(value = {"/getYearOnYearReport"}, method = RequestMethod.POST)
	@ResponseBody
	public List<Object[]> getYearOnYearReport(@RequestBody String formdata, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		ModelAndView model = new ModelAndView();
		String refromdate =  request.getParameter("f_date");
	 	String retodate  = request.getParameter("to_date");
	 	java.util.Date datee=new java.util.Date();
		Timestamp ts=new Timestamp(datee.getTime());
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(refromdate);
			
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Object[]> reportdata = applicationsrepository.findReport9(date1,date2); 
	 	if(reportdata != null) {
	 	    model.addObject("reportdata", reportdata);

		 } 
	 	
		return  reportdata;
	}
	
	@RequestMapping(value = {"/getReportData"}, method = RequestMethod.POST)
	@ResponseBody
	public List<Object[]> getReport(@RequestBody String f_date, HttpServletRequest request) throws ParseException {
			String value="";
			//System.out.println("Inside post " +f_date);
			String fdate = f_date;
			String date[] = fdate.split("&");
			//System.out.println("Printing value of split"+date[0]+" "+date[1]);
			
			String[] firstdate=date[0].split("=");
			String [] todate= date[1].split("=");
			
			java.util.Date datee=new java.util.Date();
			Timestamp ts=new Timestamp(datee.getTime());
		
			String sDate1=firstdate[1];
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
			//String sDate2=todate[1];
			//Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
			
			String retodate = request.getParameter("todate");
			Date date2=null;
			
			if(retodate =="" || retodate ==null)
			{
			date2= new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());	
			}else {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			}
			
			
			ModelAndView model = new ModelAndView();
			List <Object[]> reportdata  = applicationsrepository.findReport1(date1,date2);
			model.addObject("reportdata", reportdata);
	
			return  reportdata;
		}
   
	public String exportReport2(String reportFormat,String date1,String date2,String reapplicantname,int cropgroup,int crop,String denomination) throws FileNotFoundException, JRException {
		System.out.println("Trace on 1118 ---------------");
		   String path  = env.getRequiredProperty("static.content.directory"); 
	        List<ViewStateWiseReport> reportdata = applicationsrepository.findJasperReportWithoutStatus(date1,date2,reapplicantname,cropgroup,crop,denomination);
	        
	        System.out.println("Trace on 1122 ---------------"+reportdata.size());
	        File file = ResourceUtils.getFile("classpath:templates/report/listofapplicationreceived.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource((Collection<?>) reportdata);
	        System.out.println("Trace on 1126 ---------------");
	        Map<String, Object> parameters = new HashMap<>();
	        
	        //do not delete the parameter ds as itis mapped to the jrxml
	        parameters.put("ds", dataSource);
	        //parameter ds case ends here
	        
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/listofapplicationreceived.pdf");
	            return "redirect:/ppvfra/download/japerreport/listofapplicationreceived"+reportFormat;
	        }
	        return "redirect:/ppvfra/download/japerreport/listofapplicationreceived"+reportFormat;
	       
	    }
	
   // public String exportReport3(String reportFormat,String date1,String date2,String reapplicantname,int cropgroup,int crop,String denomination,int statusid) throws FileNotFoundException, JRException {
	 public String exportReport3(String reportFormat,String date1,String date2,String reapplicantname,int cropgroup,int crop,String denomination) throws FileNotFoundException, JRException 
	 {
		   
		 String path  = env.getRequiredProperty("static.content.directory");
		   System.out.println("Trace on line number 1148 as = "+date1+ " "+date2+ " " +reapplicantname+ " " +cropgroup+ " " +crop+ ""
		   		+ " "+denomination);
	       
		   // List<ViewStateWiseReport> reportdata = applicationsrepository.findJasperReport2(date1,date2,reapplicantname,cropgroup,crop,denomination,statusid);
		   List<ViewStateWiseReport> reportdata = applicationsrepository.findJasperReport2(date1,date2,reapplicantname,cropgroup,crop,denomination);
	        System.out.println("Trace on line number 1150"+reportdata.size());
	        File file = ResourceUtils.getFile("classpath:templates/report/listofapplicationclosed.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource((Collection<?>) reportdata);
	        
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("ds", dataSource);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	        if (reportFormat.equalsIgnoreCase("html")) {
	            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/listofapplicationclosed.jrxml");
	        }
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/listofapplicationclosed.pdf");
	            return "redirect:/ppvfra/download/japerreport/listofapplicationclosed"+reportFormat;
	        }
	        return "redirect:/ppvfra/download/japerreport/listofapplicationclosed"+reportFormat;
	       
	    }
	
	public String exportReport4(String reportFormat,String date1,String date2,String reapplicantname,int cropgroup,int crop,String denomination,int statusid) throws FileNotFoundException, JRException {
		   String path  = env.getRequiredProperty("static.content.directory"); 
	       System.out.println("Printing  on 1231 ============"); 
	       
	       //List<ViewStateWiseReport> reportdata = applicationsrepository.findJasperReport2(date1,date2,reapplicantname,cropgroup,crop,denomination,statusid);
	       List<ViewStateWiseReport> reportdata = applicationsrepository.findlegal_jasperreport(date1,date2,reapplicantname,cropgroup,crop,denomination,statusid);
		   System.out.println("Printing  on 1235 ============"+reportdata.size());
		   
		 
		   File file = ResourceUtils.getFile("classpath:templates/report/listofapplicationpending.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource((Collection<?>) reportdata);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("ds", dataSource);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/listofapplicationpending.pdf");
	            return "redirect:/ppvfra/download/japerreport/listofapplicationpending"+reportFormat;
	        }
	        return "redirect:/ppvfra/download/japerreport/listofapplicationpending"+reportFormat;
	       
	    }
	
	public String exportReport5(String reportFormat,String date1,String date2,String reapplicantname,int cropgroup,int crop,String denomination,int status) throws FileNotFoundException, JRException {
		   String path  = env.getRequiredProperty("static.content.directory"); 
	        List<ViewDusTestReport> reportdata = applicationsrepository.findJasperReportDusTestReport(date1,date2,reapplicantname,cropgroup,crop,denomination,status);
	        System.out.println("Line number 1257 ------------- "+reportdata.size()); 
	        File file = ResourceUtils.getFile("classpath:templates/report/dustestresultreports.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource((Collection<?>) reportdata);
	        
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("ds", dataSource);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/dustestresultreports.pdf");
	            return "redirect:/ppvfra/download/japerreport/dustestresultreports"+reportFormat;
	        }
	        return "redirect:/ppvfra/download/japerreport/dustestresultreports"+reportFormat;
	       
	    }
  
	public String exportReport6(String reportFormat,String date1,String date2,String reapplicantname,int cropgroup,int crop,String denomination,int statusid,int statecode,int districtcode) throws FileNotFoundException, JRException {
		System.out.println("Trace on 1178 -----");  
		String path  = env.getRequiredProperty("static.content.directory"); 
		System.out.println("Trace on 1138 ---"+date1+" and date2 is = "+date2);
	        List<ViewStateWiseReport> reportdata1 = applicationsrepository.findJasperReportStatwise(date1,date2,reapplicantname,cropgroup,crop,denomination,statusid,statecode,districtcode);
	        System.out.println("Printing the view report as ="+reportdata1.size());
	        
	        File file = ResourceUtils.getFile("classpath:templates/report/statewisereports.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource((Collection<?>)reportdata1);
	        
	        Map<String, Object> parameters = new HashMap<>();
	        
	        //DO NOT REMOVE ds as it is required type for jrxml
	        parameters.put("ds", datasource);
	        //DO NOT  ENDS HERE
	        
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/statewisereports.pdf");
	            return "redirect:/ppvfra/download/japerreport/statewisereports"+reportFormat;
	        }
	        return "redirect:/ppvfra/download/japerreport/statewisereports"+reportFormat;
	       
	    }
	
    public String exportReport10(String reportFormat,String date1,String date2,int cropgroup) throws FileNotFoundException, JRException {
		   String path  = env.getRequiredProperty("static.content.directory");
	        List<ReportTen> reportdata = applicationsrepository.findReport10(date1,date2,cropgroup);
	        System.out.println("Trace on line numner 1302 --------------"+reportdata.size());
	        File file = ResourceUtils.getFile("classpath:templates/report/cropgroupwisereport.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource((Collection<?>) reportdata);
	        
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("ds", dataSource);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/cropgroupwisereport.pdf");
	            return "redirect:/ppvfra/download/japerreport/cropgroupwisereport"+reportFormat;
	        }
	        return "redirect:/ppvfra/download/japerreport/cropgroupwisereport"+reportFormat;
	       
	    }
	
	@RequestMapping(value = "/ppvfra/download/japerreport/{type}", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @PathVariable("type") String type) throws IOException 
	{
		String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
		File file = null;
		file=new File(UPLOAD_FILEPATH+""+type);
		if(!file.exists()){
			file=new File(UPLOAD_FILEPATH+""+type);
			}
		 if(!file.exists()){
	            String errorMessage = "Sorry. The file you are looking for does not exist";
	            OutputStream outputStream = response.getOutputStream();
	            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
	            outputStream.close();
	            return;
	        }
		    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	        if(mimeType==null){
	            mimeType = "application/octet-stream";
	        }
	        response.setContentType(mimeType);
	        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
		    response.setContentLength((int)file.length());
		    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		    FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
	
	@RequestMapping(value = "/getListByStatus/{status_no}/{stat}/{f_date}/{t_date}", method = RequestMethod.GET)
	public String getListByStatus(@PathVariable(name="status_no") Long 
			status_no,@PathVariable(name="stat") String stat,
			@PathVariable(name="f_date") String f_date,
			@PathVariable(name="t_date") String t_date,
			Model model,HttpServletRequest request) throws IOException
	{
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
		
		System.out.println("Trace on 1282  ="+f_date+" 2nd date "+t_date);	
		
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(f_date);
			if(t_date =="" || t_date ==null)
			{
			date2= new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());	
			}else {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(t_date);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Object[]> all_applications=applicationsrepository.getstatus_wise_applications_list(status_no.intValue(),date1,date2);
		if(all_applications!= null)
			model.addAttribute("all_applications",all_applications);
	
	    StringBuffer url=request.getRequestURL();
		String val=url.substring(url.lastIndexOf("/"),url.length());
	
			  ActivityLogTable act = new ActivityLogTable();
			  act.setIpaddress(request.getRemoteAddr());
			  act.setActivityby(userdeail.getUsername()); Date dt = new Date();
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
				
		model.addAttribute("stat",stat);
		model.addAttribute("status_no",status_no);
		return "report/status_wise_applications";
	}
	
	
	@RequestMapping(value = "/yearwiseview/{status_no}/{stat}/{year_obtained}/{f_date}/{to_date}", method = RequestMethod.GET)
	public String yearwiseview(@PathVariable(name="status_no") Long 
			status_no,@PathVariable(name="stat") String stat,
			@PathVariable(name="year_obtained") Long year_obtained,
			@PathVariable(name="f_date") String f_date,
			@PathVariable(name="to_date") String to_date,
			Model model,HttpServletRequest request) 
	{
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
		
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(f_date);
			if(to_date =="" || to_date ==null)
			{
			date2= new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());	
			}else {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Object[]> all_applications=applicationsrepository.getstatus_wise_applications_list2(status_no.intValue(),year_obtained.intValue(),date1,date2);
		if(all_applications!= null)
			model.addAttribute("all_applications",all_applications);
	
	    StringBuffer url=request.getRequestURL();
		String val=url.substring(url.lastIndexOf("/"),url.length());
	
			  ActivityLogTable act = new ActivityLogTable();
			  act.setIpaddress(request.getRemoteAddr());
			  act.setActivityby(userdeail.getUsername()); Date dt = new Date();
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
				
		model.addAttribute("stat",stat);
		model.addAttribute("status_no",status_no);
		return "report/status_wise_applications2";
	}
	
	
	@RequestMapping(value = "/show_application_details/{stat}/{f_date}/{t_date}", method = RequestMethod.GET)
	public String getyearonyear(@PathVariable(name="stat") int stat,@PathVariable(name="f_date") String f_date,
			@PathVariable(name="t_date") String t_date,
			Model model,HttpServletRequest request) throws IOException
	{
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
		
		System.out.println("Trace on 1450  ="+f_date+" 2nd date "+t_date);	
		
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(f_date);
			if(t_date =="" || t_date ==null)
			{
			date2= new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());	
			}else {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(t_date);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Object[]> all_applications=applicationsrepository.getyearonyear_reviewdata(date1,date2,stat);
		if(all_applications!= null)
			model.addAttribute("all_applications",all_applications);
	
	    StringBuffer url=request.getRequestURL();
		String val=url.substring(url.lastIndexOf("/"),url.length());
	
			  ActivityLogTable act = new ActivityLogTable();
			  act.setIpaddress(request.getRemoteAddr());
			  act.setActivityby(userdeail.getUsername()); Date dt = new Date();
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
				
		model.addAttribute("stat",null);
		//model.addAttribute("status_no",status_no);
		return "report/status_wise_applications";
	}
	
	
	@RequestMapping(value = {"/getReportData7"}, method = RequestMethod.POST)
	@ResponseBody
	public List<Object[]> getReport7(@RequestBody String formdata, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		ModelAndView model = new ModelAndView();
		String refromdate =  request.getParameter("f_date");
	 	String retodate  = request.getParameter("to_date");
	 	int status = Integer.parseInt(request.getParameter("status"));
	 	int cropgroup = Integer.parseInt(request.getParameter("cropgroup"));
	 	int crop =Integer.parseInt(request.getParameter("crop"));
	 	String reapplicantname;
	 	if(request.getParameter("applicantname") != null && request.getParameter("applicantname")!=""){
	 		reapplicantname = request.getParameter("applicantname");
	 	}
	 	else {
	 		reapplicantname = "";
	 	} 	
	 	String denomination;
	 	if(request.getParameter("denomination") != null && request.getParameter("denomination") !="") {
	 	denomination = request.getParameter("denomination");
	 	}
	 	else {
	 		denomination = "";
	 	}
	 	java.util.Date datee=new java.util.Date();
		Timestamp ts=new Timestamp(datee.getTime());
		Date date1 =null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(refromdate);
			//date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			
			if(retodate =="" || retodate ==null)
			{
			date2= new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());	
			}else {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(retodate);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Object[]> reportdata = applicationsrepository.findReport7(date1,date2,reapplicantname,cropgroup,crop,denomination,status); 
	 	System.out.println("Trace printing 1641 reportdata ="+reportdata.size()+" "+reportdata.get(0));
		if(reportdata != null) {
	 	    model.addObject("reportdata", reportdata);

		 } 
	 	String format="pdf"; 
	 	try {
	 		
	 		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
			retodate =df.format(date2);
			System.out.println("Traceonline ------ 1651 -----"+retodate +" "+refromdate);
			exportReport7(format,refromdate,retodate,reapplicantname,cropgroup,crop,denomination,status);
			 } 
	 		catch
			 (FileNotFoundException | JRException e) { // TODO Auto-generated catch block
			 e.printStackTrace(); 
			 }
		return  reportdata;
	}
	
	
	public String exportReport7(String reportFormat,String date1,String date2,String reapplicantname,int cropgroup,int crop,String denomination,int statusid) throws FileNotFoundException, JRException {
		   String path  = env.getRequiredProperty("static.content.directory"); 
	       System.out.println("Printing  on 1664 ============"); 
		   List<ViewStateWiseReport7> reportdata = applicationsrepository.findJasperReport7(date1,date2,reapplicantname,cropgroup,crop,denomination,statusid);
		   System.out.println("Printing  on 1666 ============"+reportdata.size());
		   
		 
		   File file = ResourceUtils.getFile("classpath:templates/report/listofvaritycertificates.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource((Collection<?>) reportdata);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("ds", dataSource);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/listofvaritycertificates.pdf");
	            return "redirect:/ppvfra/download/japerreport/listofvaritycertificates"+reportFormat;
	        }
	        return "redirect:/ppvfra/download/japerreport/listofvaritycertificates"+reportFormat;
	       
	    }
	

	
	
}
