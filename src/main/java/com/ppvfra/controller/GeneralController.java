package com.ppvfra.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ppvfra.entity.CandidateVarietyDetails;
import com.ppvfra.entity.CompanyRegistration;
import com.ppvfra.entity.CropGroup;
import com.ppvfra.entity.CropSpecies;
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.DusCharacteristics;
import com.ppvfra.entity.DusCharacteristicsStates;
import com.ppvfra.entity.DusTestFee;
import com.ppvfra.entity.Fees;
import com.ppvfra.entity.InstitutionRegistration;
import com.ppvfra.entity.InternalUser;
import com.ppvfra.entity.MediumTermStorage;
import com.ppvfra.entity.Modules;
import com.ppvfra.entity.ObservationCategory;
import com.ppvfra.entity.OfficeDetails;
import com.ppvfra.entity.OfficeStates;
import com.ppvfra.entity.RegistrarJurisdictionCrops;
import com.ppvfra.entity.RegistrarJurisdictionOffice;
import com.ppvfra.entity.Rejuvenation;
import com.ppvfra.entity.Role;
import com.ppvfra.entity.Role_Modules;
import com.ppvfra.entity.States;
import com.ppvfra.entity.TransferSeedToMTS;
import com.ppvfra.entity.TypeLine;
import com.ppvfra.entity.User;
import com.ppvfra.entity.User_Role;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AddCropsRepository;
import com.ppvfra.repository.AdduserRepository;
import com.ppvfra.repository.ApplicantRegistrationRepository;
import com.ppvfra.repository.ApplicationConventionCountriesRepository;
import com.ppvfra.repository.ApplicationDusVarietyRepository;
import com.ppvfra.repository.ApplicationParentallineRepository;
import com.ppvfra.repository.ApplicationPaymentsRepository;
import com.ppvfra.repository.ApplicationRepository;
import com.ppvfra.repository.ApplicationScrutinyRepository;
import com.ppvfra.repository.CandidateVarietyDetailsRepository;
import com.ppvfra.repository.CandidateVarietyRepository;
import com.ppvfra.repository.CompanyRegistrationRepository;
import com.ppvfra.repository.CropSpeciesRepository;
import com.ppvfra.repository.DistrictRepository;
import com.ppvfra.repository.DocumentChecklistRepository;
import com.ppvfra.repository.DusCharacteristicsRepository;
import com.ppvfra.repository.DusCharacteristicsStatesRepository;
import com.ppvfra.repository.DusTestPayRepository;
import com.ppvfra.repository.FeesRepository;
import com.ppvfra.repository.InstitutionRegistrationRepository;
import com.ppvfra.repository.LoginLogRepository;
import com.ppvfra.repository.MediumTermStorageRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.ObservationCategoryRepository;
import com.ppvfra.repository.OfficeDetailsRepository;
import com.ppvfra.repository.OfficeStateRepository;
import com.ppvfra.repository.RegistrarJurisdictionCropsRepository;
import com.ppvfra.repository.RegistrarJurisdictionOfficeRepository;
import com.ppvfra.repository.RejuvenationRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.RoleRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.StateRepository;
import com.ppvfra.repository.TransferSeedToMTSRepository;
import com.ppvfra.repository.TypeLineRepository;
import com.ppvfra.repository.UserAddRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.AddCropGroupService;
import com.ppvfra.service.CompanyRegistrationService;
import com.ppvfra.service.DistrictService;
import com.ppvfra.service.DocumentChecklistService;
import com.ppvfra.service.InstitutionRegistrationService;
import com.ppvfra.common.MailCommons;
import com.ppvfra.entity.Application;
import com.ppvfra.entity.ApplicationConventionCountries;
import com.ppvfra.entity.ApplicationPayments;
import com.ppvfra.entity.ApplicationScrutiny;
import com.ppvfra.entity.ApplicationStsSeedrecieved;
import com.ppvfra.entity.ApplicationStsTest;
import com.ppvfra.entity.ApplicationSubmission;
import com.ppvfra.entity.Application_Stages;
import com.ppvfra.repository.ApplicationStsSeedRepository;
import com.ppvfra.repository.ApplicationStsTestRepository;
import com.ppvfra.repository.ApplicationSubmissionRepository;
import com.ppvfra.repository.ApplicationsRepository;

@Controller
public class GeneralController
{
	@Autowired
	AdduserRepository addusrrep;
	
	@Autowired
	ModulesRepository modulerep;
	
	@Autowired
	RoleAssignRepository roleassignrep;
	
	@Autowired
	RoleRepository addrolerep;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	DistrictService districtservice;
	
	@Autowired
	DistrictRepository districtrepository;

	@Autowired
	AddCropsRepository addcroprepository;
	
	@Autowired
	DusCharacteristicsRepository duscharacteristicsrepository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	AddCropGroupService addcropgroupservice;
	
	@Autowired
	CompanyRegistrationRepository companyregister;
	
	@Autowired
	OfficeStateRepository officestatesrepository;
	
	@Autowired
	StateRepository staterep;
	
	@Autowired
	CompanyRegistrationService companyregisterservice;
	
	
	@Autowired
	AddCropsRepository addcroprep;
	
	@Autowired
	OfficeDetailsRepository officedetailsrepository;
	
	@Autowired
	InstitutionRegistrationRepository institutionregistrationrepository;
	
	@Autowired
	Environment env;
	
	@Autowired
	InstitutionRegistrationService institutionregistrationservice;
	
	@Autowired
	DocumentChecklistService documentchecklistservice;
	
	@Autowired
	DocumentChecklistRepository documentchecklistrep;
	
	@Autowired
	ApplicantRegistrationRepository applicantregistrationrepository;
	
	@Autowired
	CandidateVarietyDetailsRepository candidatevarietydetailsrepository;
	
	@Autowired
	CropSpeciesRepository cropspeciesrepository;
	
	@Autowired
	ApplicationStsSeedRepository applicationseedrepository;
	
	@Autowired
	DusCharacteristicsStatesRepository duscharacteristicsstatesrepository;
	
	@Autowired
	FeesRepository feesrepository;
	
	@Autowired
	RegistrarJurisdictionCropsRepository registrarjurisdictioncropsrepository;
	
	@Autowired
	RegistrarJurisdictionOfficeRepository registrarjurisdictionofficerepository;
	
	@Autowired
	RejuvenationRepository rejuvenationrepository;
	
	@Autowired
	MediumTermStorageRepository mediumtermstorage;
	
	@Autowired
	TransferSeedToMTSRepository transferseedmts;
	
	@Autowired
	DusTestPayRepository dustestpayrepository;
	
	@Autowired
	ApplicationPaymentsRepository applicationpaymentrepository;
	
	@Autowired
	LoginLogRepository loginlogrepository;
	
	@Autowired
	ActivityLogRepository activitylogrepository;
	
	@Autowired
	UserAddRepository useraddrepository;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	@Autowired
	TypeLineRepository typelinerepository;
	
	@Autowired
	ApplicationStsTestRepository applicationststest;
	
	@Autowired
	ApplicationSubmissionRepository applicationsubmissionrepository;
	
	@Autowired
	ApplicationRepository applicationrepository;
	
	@Autowired
	ApplicationScrutinyRepository applicationscrutinyrepository;
	
	@Autowired
	ApplicationsRepository applicationsrep;
	
	@Autowired
	ApplicationDusVarietyRepository applicationdusvarietyrepository;
	
	@Autowired
	ApplicationParentallineRepository applicationparentallinerepository;
	
	@Autowired
	ApplicationConventionCountriesRepository appconvencounrtyrep;
	
	@Autowired
	FeesRepository feerepository;
	
	@Autowired
	ObservationCategoryRepository observationcategory;
	
	@RequestMapping(value = {"/ppvfra/checkmobile"}, method = RequestMethod.POST )
	@ResponseBody
	public String MobileAvailabilityCheck(@RequestBody String mobileno, HttpServletRequest request)
	{
		String value="";
		//System.out.println("Trace Printing Email Id  Check="+mobileno);
		List<InternalUser> mobilechk= addusrrep.mobilecheck(mobileno);
		if(mobilechk.size() >= 1)
		{
			value="taken";
		}
		else
		{
			value="not taken";
		}
		
		return value;
	}
	
	@RequestMapping(value = {"/ppvfra/cinnumber"}, method = RequestMethod.POST )
	@ResponseBody
	public String cinNumberAvailabilityCheck(@RequestBody String cinnumber, HttpServletRequest request)
	{
		String value="";
		//System.out.println("Trace Printing Email Id  Check="+cinnumber);
		List<CompanyRegistration> cinchk= companyregister.checkCinNumber(cinnumber);
		if(cinchk.size() >= 1)
		{
			value="taken";
		}
		else
		{
			value="not taken";
		}
		
		return value;
	}
	
	@RequestMapping(value = {"/ppvfra/company"}, method = RequestMethod.POST )
	@ResponseBody
	public String checkCompanyName(@RequestBody String companyname, HttpServletRequest request)
	{
		String value="";
		//System.out.println("Trace Printing Email Id  Check="+companyname);
		List<CompanyRegistration> emailchk= companyregister.checkCompanyName(companyname);
		if(emailchk.size() >= 1)
		{
			value="taken";
		}
		else
		{
			value="not taken";
		}
		
		return value;
	}
	
	@RequestMapping(value = {"/ppvfra/acronym"}, method = RequestMethod.POST )
	@ResponseBody
	public String checkAcronym(@RequestBody String acronym, HttpServletRequest request)
	{
		String value="";
		//System.out.println("Trace Printing Email Id  Check="+acronym);
		List<CompanyRegistration> acronymchk= companyregister.checkAcronym(acronym);
		if(acronymchk.size() >= 1)
		{
			value="taken";
		}
		else
		{
			value="not taken";
		}
		
		return value;
	}
	
	@RequestMapping(value = {"/ppvfra/checkmail"}, method = RequestMethod.POST )
	@ResponseBody
	public String emailAvailabilityCheck(@RequestBody String emailid, HttpServletRequest request)
	{
		String value="";
		//System.out.println("Trace Printing Email Id  Check="+emailid);
		List<InternalUser> emailchk= addusrrep.emailcheck(emailid);
		if(emailchk.size() >= 1)
		{
			value="taken";
		}
		else
		{
			value="not taken";
		}
		
		return value;
	}
	
	@RequestMapping(value = {"/ppvfra/checkusername"}, method = RequestMethod.POST )
	@ResponseBody
	public String usernameCheck(@RequestBody String emailid, HttpServletRequest request)
	{
		String value="";
		//System.out.println("Trace Printing Email Id  Check="+emailid);
		List<InternalUser> emailchk= addusrrep.usernamecheck(emailid.toLowerCase());
		if(emailchk.size() >= 1)
		{
			value="taken";
		}
		else
		{
			value="not taken";
		}
		
		return value;
	}
	
	
	
	@RequestMapping(value = {"usernamecheck"}, method = RequestMethod.POST )
	@ResponseBody
	public String usernameAvailabilityCheck(@RequestBody String username, HttpServletRequest request)
	{
		String value="";
		//System.out.println("Trace Printing Email Id  Check="+username);
		List<InternalUser> usernamechk= addusrrep.usernamecheck(username.toLowerCase());
		if(usernamechk.size() >= 1)
		{
			value="taken";
		}
		else
		{
			value="not taken";
		}
		
		return value;
	}
	
	 @RequestMapping(value = {"/ppvfra/getmenulist"}, method = RequestMethod.POST )
	@ResponseBody
	public List<Modules> menulist(Model model,@RequestBody String menutype) 
	{ 
		//System.out.println("Trace Printing MODULE  Check="+menutype);
		String menuval = menutype;
		String menuarr[] = menuval.split("=");
		if(menuarr[1].toString().equals("1111"))
		{
			 List<Modules> moduledatalist=  modulerep.moduledatafetchallfeilds();
			 return moduledatalist;
		}else 
		{
			List<Modules> moduledatalist=  modulerep.moduledatafetchwithProcess(Integer.parseInt(menuarr[1]));
			return moduledatalist;
		}
	}
	 
 
	 @RequestMapping(value = {"/ppvfra/getroleidvalue"}, method = RequestMethod.POST )
		@ResponseBody
		public List<User_Role> fetchroleidval(@RequestBody String user_id, HttpServletRequest request,Model model)
		{
			String value="";
			String a = user_id;
			String b[] = a.split("="); 
			List<User_Role> roleids= roleassignrep.searchfor_roles(Integer.parseInt(b[1]));
			 if(roleids !=null)
				 {
				 model.addAttribute("roleidfetched",roleids);
				 }
			 
			List<Role> certificates = addrolerep.findAll();
			model.addAttribute("certificates", certificates);
			 
			return roleids;
		}
	 
		
		 @RequestMapping(value = {"/ppvfra/databefore"}, method = RequestMethod.POST )
			@ResponseBody
			public List<Role_Modules> datapresentinmodules(Model model,@RequestBody String moduledata) 
			{
				String menuarr[]  = moduledata.split("&");
				String menuarr1[] = menuarr[0].split("=");
				String rolearr[]  =  menuarr[1].split("=");
				
				if(menuarr[1].toString().equals("1111"))
				{
					 List<Role_Modules> moduledatalist=  rolemodulerepository.getPrevligedata_all();
					 model.addAttribute("moduledatalist",moduledatalist);
					 return moduledatalist;
				}else 
				{ 
					List<Role_Modules> moduledatalist=  rolemodulerepository.getPrevligedata(Integer.parseInt(rolearr[1]),Integer.parseInt(menuarr1[1]));
					model.addAttribute("moduledatalist",moduledatalist);
					return moduledatalist;
				}
					
			}
	
			
			@RequestMapping(value = { "/ppvfra/getDistricts" }, method = RequestMethod.POST)
			@ResponseBody
			public List<Districts> getDistrict(@RequestBody String stateCode,Model model,HttpServletRequest request)
			{	try {
			//String state=request.getParameter("stateCode");
			System.out.println("trace="+stateCode);
			String a[] = stateCode.split("=");
			       
			//int statecode = Integer.parseInt(state);
			//System.out.println("trace="+state);
			List<Districts>  districts = districtrepository.getAllDistrictsByStateCode(Integer.parseInt(a[1]));
			       model.addAttribute("districts",districts);
			       return districts;

			} catch (NumberFormatException e) {

			e.printStackTrace();
			}
			return null;

			}
			
			
			
			@RequestMapping(value = { "/ppvfra/getCropName" }, method = RequestMethod.POST)
			@ResponseBody
			public List<Crops> getCropName(@RequestBody String cropgroupid,Model model,HttpServletRequest request)
			{	try {
			//String state=request.getParameter("cropgroup");
			//System.out.println("trace="+cropgroupid);
			String a[] = cropgroupid.split("=");
			        

			List<Crops>  crops = addcroprepository.getAllCropsByCropGroup(Integer.parseInt(a[1]));
			       model.addAttribute("crops",crops);
			       return crops;

			} catch (NumberFormatException e) {

			e.printStackTrace();
			}
			return null;

			}	
			
			
 
 
 @RequestMapping(value = {"/ppvfra/duscharacteristics"}, method = RequestMethod.POST )
 @ResponseBody 
 public List<DusCharacteristics> list(Model model,@RequestBody String cropid)  
 {  
	
	  String a[] = cropid.split("=");
	 
	  List<DusCharacteristics> duscharacteristics = duscharacteristicsrepository.getAllDusCharacteristics(Integer.parseInt(a[1])); 
	  model.addAttribute("duscharacteristics",duscharacteristics); 
	  return duscharacteristics;
	  
 
 }
 

 @RequestMapping(value = {"/ppvfra/fetchstateidval"}, method = RequestMethod.POST )
	@ResponseBody
	public List<OfficeStates> fetchidval(@RequestBody String officeid, HttpServletRequest request,Model model)
	{
	      String a[] = officeid.split("=");
	     
		
       	List<OfficeStates> stateid= officestatesrepository.searchfor_states(Integer.parseInt(a[1]));
		 if(stateid !=null)
			 {
			 model.addAttribute("stateid",stateid);
			 }
		
		    List<States> bindstates = staterep.findAll();
			model.addAttribute("bindstates", bindstates);
		
		return stateid;
	}
 
 
	/*
	 * @RequestMapping(value = {"/ppvfra/fetchcropidval"}, method =
	 * RequestMethod.POST )
	 * 
	 * @ResponseBody public List<RegistrarJurisdiction> fetchval(@RequestBody String
	 * userid, HttpServletRequest request,Model model) { String a[] =
	 * userid.split("&"); String a1[] = a[0].split("="); String a2[] =
	 * a[1].split("="); List<RegistrarJurisdiction> crops=
	 * registrarjurisdictionrepository.searchfor_crops(Integer.parseInt(a1[1]),
	 * Integer.parseInt(a2[1])); if(crops !=null) {
	 * model.addAttribute("crops",crops); }
	 * 
	 * List<Crops> bindcrops = addcroprep.findAll(); model.addAttribute("bindcrops",
	 * bindcrops);
	 * 
	 * return crops; }
	 * 
	 * 
	 * @RequestMapping(value = {"/ppvfra/fetchofficeidval"}, method =
	 * RequestMethod.POST )
	 * 
	 * @ResponseBody public List<RegistrarJurisdiction> fetchofficeval(@RequestBody
	 * String userid, HttpServletRequest request,Model model) { String a[] =
	 * userid.split("&"); String a1[] = a[0].split("="); String a2[] =
	 * a[1].split("="); List<RegistrarJurisdiction> officedetails=
	 * registrarjurisdictionrepository.searchfor_office(Integer.parseInt(a1[1]),
	 * Integer.parseInt(a2[1])); if(officedetails !=null) {
	 * model.addAttribute("officedetails",officedetails); }
	 * 
	 * return officedetails; }
	 */
 
 
 @RequestMapping(value = "/ppvfra/download/external/{type}", method = RequestMethod.GET)
 public void downloadFile(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
	 
																						   
	String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
	//String EXTERNAL_FILE_PATH="/home/akaladmin/files";
	File file = null;
	// file = new File(EXTERNAL_FILE_PATH + type+".pdf");
	file=new File(UPLOAD_FILEPATH+"PPVFRA/DUS_CHARACHTERSTICS/CROPID/"+type);
	// System.out.println(EXTERNAL_FILE_PATH + type+".pdf");
	if(!file.exists()){
		file=new File(UPLOAD_FILEPATH+"PPVFRA/DUS_CHARACHTERSTICS/CROPID/"+type);
		 //System.out.println("printing extension: "+UPLOAD_FILEPATH+"PPVFRA/DUS_CHARACHTERSTICS/CROPID/"+type+".PDF");
	 }
	 if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
           // System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
	 String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            //System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        System.out.println("mimetype : "+mimeType);
         
        response.setContentType(mimeType);
        
        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
        while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
    response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
    
    /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
    //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
     
    response.setContentLength((int)file.length());

    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

    //Copy bytes from source to destination(outputstream in this example), closes both streams.
    FileCopyUtils.copy(inputStream, response.getOutputStream());
}
 
 @RequestMapping(value = {"ppfvra/api/filesizecheck"}, method = RequestMethod.POST )
	@ResponseBody
	public boolean filesizecheck(@RequestBody String filename,@RequestBody String ifilesize ,HttpServletRequest request)
	{
		boolean value;	 
										   
	
			String lno = filename;
			//System.out.println("trace name of file"+filename);
			
			String filenametopass[] = lno.split("&");
																						 
			
			String actualfilename1[] =filenametopass[0].split("=");
			String filesizetopass[] = filenametopass[1].split("=");
			//System.out.println("Umesh Printing the file name"+actualfilename1[1]+"and size="+Long.parseLong(filesizetopass[1]));
			Long size= Long.parseLong(filesizetopass[1]);
			
			value = DocumentChecklistRepository.filesizechking(actualfilename1[1],size);
			
																
			return value;
				 
		
	}
	

 @RequestMapping(value = {"/ppvfra/getvarietyvalue"}, method = RequestMethod.POST )
	@ResponseBody
	public List<CandidateVarietyDetails> fetchvarietyid(@RequestBody String applicationid, HttpServletRequest request,Model model)
	{
		
		String a = applicationid;
		String b[] = a.split("="); 
		
		List<CandidateVarietyDetails> checkbox= candidatevarietydetailsrepository.searchforvariety(Long.parseLong(b[1]));
		 if(checkbox !=null)
			 {
			 model.addAttribute("checkbox",checkbox);
			 }
		 
		return checkbox;
	}


 
 
 @RequestMapping(value = { "/ppvfra/getBotanicalName" }, method = RequestMethod.POST)
	@ResponseBody
	public List<CropSpecies> getBotanicalName(@RequestBody String cropid,Model model,HttpServletRequest request)
	{	
	
	   String a[] = cropid.split("=");
	   List<CropSpecies>  crops = cropspeciesrepository.getAllbotanicalNameByCropName(Integer.parseInt(a[1]));
		if(crops !=null)
		{      
		   model.addAttribute("crops",crops);
		}
		return crops;
	}
 
 @RequestMapping(value = { "/ppvfra/getCropsByCropGroup" }, method = RequestMethod.POST)
	@ResponseBody
	public List<Crops> getCropsByCropGroup(@RequestBody String cropid,Model model,HttpServletRequest request)
	{	
	
	   String a[] = cropid.split("=");
	   List<Crops>  crops = addcroprepository.getCropsByCropGroup(Integer.parseInt(a[1]));
		if(crops !=null)
		{      
		   model.addAttribute("crops",crops);
		}
		return crops;
	}

 
 @RequestMapping(value = { "/ppvfra/getFamilyName" }, method = RequestMethod.POST)
	@ResponseBody
	public List<CropSpecies> getFamilyName(@RequestBody String crop_botanical_name,Model model,HttpServletRequest request)
	{	
	   //System.out.println("print"+crop_botanical_name);
	   String a[] = crop_botanical_name.split("=");
	   String nn = a[1].replace("+", " ");
	   //System.out.println("AFTER REPLACING == "+nn);
	   List<CropSpecies>  crops = cropspeciesrepository.getAllFamilyNameByCropName(nn);
		if(crops !=null)
		{      
		   model.addAttribute("crops",crops);
		}
		return crops;
	}

@RequestMapping(value = {"/ppvfra/ishybrid_data"}, method = RequestMethod.POST )
	@ResponseBody
	public String hybrid_datacheck(@RequestBody String obj, HttpServletRequest request)
	{
		String value="";
		//System.out.println("Trace Printing Email Id  Check="+username);
		List<Object[]> usernamechk= applicationseedrepository.if_present(Long.parseLong(obj));
		if(usernamechk.size() >= 1)
		{
			value="true";
		}
		else
		{
			value="false";
		}
		
		return value;
	}
 
 
 
 @RequestMapping(value = {"/ppvfra/getdatasaved"}, method = RequestMethod.POST )
	@ResponseBody
	public List<ApplicationStsSeedrecieved> hybrid_datadetails(@RequestBody String Obj,  Model model)
	{
	 
		
		String no[] = Obj.split("=");
		List<ApplicationStsSeedrecieved> dataintab= applicationseedrepository.returnlist(Long.parseLong(no[0]));
		if(dataintab !=null)
		{
			model.addAttribute("dataintab",dataintab);
			return dataintab;
		}
		else { return null;} 
		
		
	}
 
	
	 @RequestMapping(value = "/ppvfra/download/{type}", method = RequestMethod.GET)
	 public void downloadReport(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
		 
																							   
		String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
		File file = null;
		file=new File(UPLOAD_FILEPATH+"PPVFRA/SEEDORPLANT/ID/"+type);
		if(!file.exists())
		{
			file=new File(UPLOAD_FILEPATH+"PPVFRA/SEEDORPLANT/ID/"+type);
		}
		if(!file.exists())
		{
	        String errorMessage = "Sorry. The file you are looking for does not exist";
	        OutputStream outputStream = response.getOutputStream();
	        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
	        outputStream.close();
	        return;
	     }
		    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	        if(mimeType==null)
	        {
	            mimeType = "application/octet-stream";
	        }
	         response.setContentType(mimeType);
	         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
	         response.setContentLength((int)file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	 
	 @RequestMapping(value = {"/ppvfra/checkinstitute"}, method = RequestMethod.POST )
		@ResponseBody
		public String checkInstituteNameAvailability(@RequestBody String name, HttpServletRequest request)
		{
			String value="";
			System.out.println("ss"+name);
			List<InstitutionRegistration> namechk= institutionregistrationrepository.checkInstituteName(name);
			if(namechk.size() >= 1)
			{
				value="taken";
			}
			else
			{
				value="not taken";
			}
			
			return value;
		}
	 
	 
	 
	 @RequestMapping(value = {"/ppvfra/abbreviation"}, method = RequestMethod.POST )
		@ResponseBody
		public String checkAbbreviationAvailability(@RequestBody String abbreviation, HttpServletRequest request)
		{
			String value="";
			
			List<InstitutionRegistration> namechk= institutionregistrationrepository.checkAbbreviation(abbreviation);
			if(namechk.size() >= 1)
			{
				value="taken";
			}
			else
			{
				value="not taken";
			}
			
			return value;
		}
	 @RequestMapping(value = { "/ppvfra/getFees" }, method = RequestMethod.POST)
		@ResponseBody
		List<Fees> getFees(@RequestBody String variety,Model model,HttpServletRequest request)
		{	
		 
		try {
		//System.out.println("variety value print: "+variety);
		String a[] = variety.split("=");
		String a1[] =a[1].split("&");
		String a2[] =a[2].split("&");
		
		//System.out.println("print value="+a[0]+"var"+a[1]+"sub"+a[2]+"cat"+a[3]);
		//System.out.println("print value="+a1[0]+"sub="+a2[0]+"cat="+a[3]);
		//System.out.println("print value---"+a2[0]);
		String registation = "Registration";
		String category =null;
		if(a[3].contains("1")){category = "Individual";}
		if(a[3].contains("3")) {	category = "Educational";	}
		if(a[3].contains("5")){category = "Commercial";}
		if(a[3].contains("2")){category = "All";}
		//System.out.println("a1[0]="+a1[0]);
		
		    if(a1[0].contains("2")) {
		    List<Fees>  fees = feesrepository.getFeesByVarietyAndSubvarietytype(Integer.parseInt(a1[0]),Integer.parseInt(a2[0]),category, registation);
			model.addAttribute("fees",fees);
			return fees;
			
			}
		    if(a1[0].contains("1")) {
			List<Fees>  fees = feesrepository.getFeesByVarietyAndSubvarietytype(Integer.parseInt(a1[0]),category, registation);
			model.addAttribute("fees",fees);
			return fees;
		    }
			
		} catch (NumberFormatException e) {
			
		e.printStackTrace();
		}
		return null;

		}
	 
	 @RequestMapping(value = { "/ppvfra/getFeesApplication2" }, method = RequestMethod.POST)
		@ResponseBody
		List<Fees> getFeesApplication2(@RequestBody String variety,Model model,HttpServletRequest request)
		{	
		 
		try {
		//System.out.println("variety value print: "+variety);
		String a[] = variety.split("=");
		String a1[] =a[1].split("&");
		String a2[] =a[2].split("&");
			/*
			 * System.out.println("print value="+a[0]+"var"+a[1]+"sub"+a[2]+"cat"+a[3]);
			 * System.out.println("print value="+a1[0]+"sub="+a2[0]+"cat="+a[3]);
			 * System.out.println("print value---"+a2[0]);
			 */
		String registation = "Registration";
		String category =null;
		if(a[3].contains("1")){category = "Individual";}
		if(a[3].contains("3")) {	category = "Educational";	}
		if(a[3].contains("5")){category = "Commercial";}
		if(a[3].contains("2")){category = "All";}
		//System.out.println("a1[0]="+a1[0]);
		
		    if(a1[0].contains("3")) {
		    List<Fees>  fees = feesrepository.getFeesByVarietytype(Integer.parseInt(a1[0]),category, registation);
			model.addAttribute("fees",fees);
			return fees;
			
			}
		    if(a1[0].contains("1")) {
			List<Fees>  fees = feesrepository.getFeesByVarietytype(Integer.parseInt(a1[0]),category, registation);
			model.addAttribute("fees",fees);
			return fees;
		    }
			
		} catch (NumberFormatException e) {
			
		e.printStackTrace();
		}
		return null;

		}
	 
	 @RequestMapping(value = "/ppvfra/download/attachment/{type}", method = RequestMethod.GET)
	 public void downloadAttachments(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
		 
																							   
		String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
		File file = null;
		file=new File(UPLOAD_FILEPATH+"APPLICATIONID/"+type);
		if(!file.exists())
		{
			file=new File(UPLOAD_FILEPATH+"APPLICATIONID/"+type);
		}
		if(!file.exists())
		{
	        String errorMessage = "Sorry. The file you are looking for does not exist";
	        OutputStream outputStream = response.getOutputStream();
	        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
	        outputStream.close();
	        return;
	     }
		    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	        if(mimeType==null)
	        {
	            mimeType = "application/octet-stream";
	        }
	         response.setContentType(mimeType);
	         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
	         response.setContentLength((int)file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
	}	
	 
	 
	 @RequestMapping(value = { "/ppvfra/getCharacteristicsStates" }, method = RequestMethod.POST)
		@ResponseBody
		public List<DusCharacteristicsStates> CharacteristicsStates(@RequestBody String stateCode,Model model,HttpServletRequest request)
		{	
		 try {
		System.out.println("trace="+stateCode);
		String a[] = stateCode.split("=");
		List<DusCharacteristicsStates>  charstates = duscharacteristicsstatesrepository.getAllStatesByDusCharacteristicId(Integer.parseInt(a[1]));
		       model.addAttribute("charstates",charstates);
		       return charstates;

		} catch (NumberFormatException e) {

		e.printStackTrace();
		}
		return null;

		}
		
	 @RequestMapping(value = { "/ppvfra/getuser" }, method = RequestMethod.POST)
		@ResponseBody
		public List<User_Role> getUser(@RequestBody String roleid1,Model model,HttpServletRequest request)
		{	try {
		//String state=request.getParameter("stateCode");
		System.out.println("trace="+roleid1);
		String a[] = roleid1.split("=");
		       
		//int statecode = Integer.parseInt(state);
		//System.out.println("trace="+state);
		List<User_Role>  user = roleassignrep.getAllUsers(Integer.parseInt(a[1]));
		       model.addAttribute("districts",user);
		       return user;

		} catch (NumberFormatException e) {

		e.printStackTrace();
		}
		return null;

		} 
		
		

		    @RequestMapping(value = {"/ppvfra/fetchcropval"}, method = RequestMethod.POST )
			@ResponseBody
			public List<RegistrarJurisdictionCrops> fetchid(@RequestBody String userid, HttpServletRequest request,Model model)
			{
			  String a[] = userid.split("=");
			  List<RegistrarJurisdictionCrops> crops= registrarjurisdictioncropsrepository.getcropdetails(Integer.parseInt(a[1]));
			  if(crops !=null)
			  {
				model.addAttribute("crops",crops);
			  }
				 List<Crops> bindcrops = addcroprep.findAll();
			     model.addAttribute("bindcrops",bindcrops);
				 return crops;
			 }
		    
		    @RequestMapping(value = {"/ppvfra/fetchofficeval"}, method = RequestMethod.POST )
			@ResponseBody
			public List<RegistrarJurisdictionOffice> fetchofficeid(@RequestBody String userid, HttpServletRequest request,Model model)
			{
			  String a[] = userid.split("=");
			  List<RegistrarJurisdictionOffice> office= registrarjurisdictionofficerepository.getofficedetails(Integer.parseInt(a[1]));
			  if(office !=null)
			  {
				model.addAttribute("office",office);
			  }
				 List<Crops> bindcrops = addcroprep.findAll();
			     model.addAttribute("bindcrops",bindcrops);
				 return office;
			 }
		    
		    @RequestMapping(value = {"/ppvfra/getvarietyval"}, method = RequestMethod.POST )
			@ResponseBody
			public List<Rejuvenation> fetchvariety(@RequestBody String id, HttpServletRequest request,Model model)
			{
				
				String a = id;
				String b[] = a.split("="); 
				
				List<Rejuvenation> checkbox= rejuvenationrepository.searchforvarietyvalue(Integer.parseInt(b[1]));
				 if(checkbox !=null)
					 {
					 model.addAttribute("checkbox",checkbox);
					 }
				 
				return checkbox;
			}
		    
		    
		    
		    
		    @RequestMapping(value = "/ppvfra/download/{applicationid}/{type}", method = RequestMethod.GET)
			 public void viewreport_deficiency(HttpServletResponse response,@PathVariable("applicationid") String applicationid, @PathVariable("type") String type) throws IOException {
				 
																									   
				String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
				File file = null;
				file=new File(UPLOAD_FILEPATH+"PPVFRA/PROCESS/DEFICIENCY_REPORT/"+applicationid+"/"+type);
				if(!file.exists())
				{
					file=new File(UPLOAD_FILEPATH+"PPVFRA/PROCESS/DEFICIENCY_REPORT/"+applicationid+"/"+type);
				}
				if(!file.exists())
				{
			        String errorMessage = "Sorry. The file you are looking for does not exist";
			        OutputStream outputStream = response.getOutputStream();
			        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			        outputStream.close();
			        return;
			     }
				    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
			        if(mimeType==null)
			        {
			            mimeType = "application/octet-stream";
			        }
			         response.setContentType(mimeType);
			         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
			         response.setContentLength((int)file.length());
					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
					FileCopyUtils.copy(inputStream, response.getOutputStream());
			}
		
		    
		    
		   
		    @RequestMapping(value = "/ppvfra/download/dustestresult/{applicationid}/{type}", method = RequestMethod.GET)
			 public void viewreport_dustestcenter(HttpServletResponse response,@PathVariable("applicationid") String applicationid, @PathVariable("type") String type) throws IOException {
				 
																									   
				String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
				File file = null;
				file=new File(UPLOAD_FILEPATH+"PPVFRA/PROCESS/DUSTESTRESULT/"+applicationid+"/"+type);
				if(!file.exists())
				{
					file=new File(UPLOAD_FILEPATH+"PPVFRA/PROCESS/DUSTESTRESULT/"+applicationid+"/"+type);
				}
				if(!file.exists())
				{
			        String errorMessage = "Sorry. The file you are looking for does not exist";
			        OutputStream outputStream = response.getOutputStream();
			        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			        outputStream.close();
			        return;
			     }
				    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
			        if(mimeType==null)
			        {
			            mimeType = "application/octet-stream";
			        }
			         response.setContentType(mimeType);
			         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
			         response.setContentLength((int)file.length());
					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
					FileCopyUtils.copy(inputStream, response.getOutputStream());
			}
		    
		    
		    @RequestMapping(value = {"/ppvfra/getdatasaved_forrejuvination"}, method = RequestMethod.POST )
			@ResponseBody
			public List<Rejuvenation> hybrid_datadetails_rejuvination(@RequestBody String Obj,  Model model)
			{
			 
				
				String no[] = Obj.split("=");
				List<Rejuvenation> dataintab_rejuv= rejuvenationrepository.returnlist(Long.parseLong(no[0]));
				if(dataintab_rejuv !=null)
				{
					model.addAttribute("dataintab_rejuv",dataintab_rejuv);
					return dataintab_rejuv;
				}
				else { return null;} 
				
				
			}
		    
		    
		    @RequestMapping(value = {"/ppvfra/getdatasaved_formts"}, method = RequestMethod.POST )
			@ResponseBody
			public List<MediumTermStorage> datadetails_mts(@RequestBody String Obj,  Model model)
			{
			 
				
				String no[] = Obj.split("=");
				List<MediumTermStorage> dataintab_mts= mediumtermstorage.getmtsdata(Long.parseLong(no[0]));
				if(dataintab_mts !=null)
				{
					model.addAttribute("dataintab_mts",dataintab_mts);
					return dataintab_mts;
				}
				else { return null;} 
				
				
			}
		    
		    
		    @RequestMapping(value = {"/ppvfra/getdatasaved_fortransfermts"}, method = RequestMethod.POST )
			@ResponseBody
			public List<TransferSeedToMTS> datadetails_transfermts(@RequestBody String Obj,  Model model)
			{
			 
				
				String no[] = Obj.split("=");
				List<TransferSeedToMTS> dataintab_transfermts= transferseedmts.transfermtsdetails(Long.parseLong(no[0]));
				if(dataintab_transfermts !=null)
				{
					model.addAttribute("dataintab_transfermts",dataintab_transfermts);
					return dataintab_transfermts;
				}
				else { return null;} 
				
				
			}
		    
		    
		   
		    @RequestMapping(value = { "/ppvfra/getpaymentfees" }, method = RequestMethod.POST)
			@ResponseBody
			long getpaymentfees(@RequestBody String variety,Model model,HttpServletRequest request)
			{	
			 int appid=0;
			try {
			System.out.println("variety value print 1106: "+variety);
			String a[] = variety.split("=");
			System.out.println("print value---111111-"+a[0]+" -var-"+a[1]);
			appid=Integer.parseInt(a[1]);
			
			/*
			 * List<DusTestFee> fees =
			 * dustestpayrepository.getfeedetails(Integer.parseInt(a1[0]));
			 */
			long fees = dustestpayrepository.getlatestfee(Integer.parseInt(a[1]));
			if(fees>=0) 
			{
			System.out.println("FF=" +fees);
			model.addAttribute("fees",fees);
			return fees;
			}

			} catch (NumberFormatException e) {
				
			e.printStackTrace();
			}
			
			List<ApplicationPayments> getdusfeedata=applicationpaymentrepository.getdustestfeedata(appid);
			if(getdusfeedata != null && getdusfeedata.size()>0 )
			{
				model.addAttribute("getdusfeedata",getdusfeedata);
				model.addAttribute("btenable",1);
			}else {
				model.addAttribute("btenable",0);
			}
			return (Long) null;

			}
		    
		    
		    
		   
		    @RequestMapping(value = { "/ppvfra/getpaymentfeesdata" }, method = RequestMethod.POST)
			@ResponseBody
			List<ApplicationPayments> getpaymentfeesdata(@RequestBody String variety,Model model,HttpServletRequest request)
			{	
			 int appid=0;
		 
			//System.out.println("second table val: "+variety);
			String a[] = variety.split("=");
			//System.out.println("second table val--111111-"+a[0]+" -var-"+a[1]);
			appid=Integer.parseInt(a[1]);
			
			List<ApplicationPayments> getdusfeedata=applicationpaymentrepository.getdustestfeedata(appid);
			if(getdusfeedata != null && getdusfeedata.size()>0 )
			{
				model.addAttribute("getdusfeedata",getdusfeedata);
				model.addAttribute("getdusfeedata1",getdusfeedata.get(0));
				model.addAttribute("btenable",1);
			}else {
				model.addAttribute("btenable",0);
			}
			return getdusfeedata;
			 
			}
		        
		    @RequestMapping(value = { "/ppvfra/getannualrenewalfees" }, method = RequestMethod.POST)
			@ResponseBody
			List<Object[]> getannualrenewalfees(@RequestBody String variety,Model model,HttpServletRequest request)
			{	
			 int appid=0;
		 
			System.out.println("variety value print 1183: "+variety);
			String a[] = variety.split("&");
			System.out.println("print value---111111-"+a[0]+" -var-"+a[1]);
			String a1[] =a[0].split("=");
			String a2[] =a[1].split("=");
			appid=Integer.parseInt(a1[1]);
			String feetype=a2[1];
			
			List<Object[]> getdusfeedata=applicationpaymentrepository.getannualrenewalfee(appid,feetype);
			if(getdusfeedata != null && getdusfeedata.size()>0 )
			{
				System.out.println("Trace Printing in 1202");
				model.addAttribute("getdusfeedata",getdusfeedata);
				model.addAttribute("anualrenewal",getdusfeedata.get(0));
				model.addAttribute("btenable",1);
			}else {
				System.out.println("Trace Printing in 1207");
				model.addAttribute("btenable",0);
			}
			return getdusfeedata;
			

			}
			
		    @RequestMapping(value = { "/ppvfra/getpaymentanualdatafee" }, method = RequestMethod.POST)
			@ResponseBody
			List<ApplicationPayments> getpaymentfeesdataanualrenewal(@RequestBody String variety,Model model,HttpServletRequest request)
			{	
			 int appid=0;
		 
			System.out.println("Trace on 1221: "+variety);
			String a[] = variety.split("=");
			System.out.println("Trace value---1223-"+a[0]+" -var-"+a[1]);
			appid=Integer.parseInt(a[1]);
			
			List<ApplicationPayments> getdusfeedata=applicationpaymentrepository.getannualrenewalfeedata(appid);
			if(getdusfeedata != null && getdusfeedata.size()>0 )
			{
				model.addAttribute("getdusfeedata",getdusfeedata);
				model.addAttribute("getdusfeedata1",getdusfeedata.get(0));
				model.addAttribute("btenable",1);
			}else {
				model.addAttribute("btenable",0);
			}
			return getdusfeedata;
			 
			}
			
		    @RequestMapping(value = {"/getLogData"}, method = RequestMethod.POST)
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
				
				Date date2= null;
				Date date1= null;
				
				
			try { if(firstdate[1].length()>0 && firstdate[1]!= null 
			 && !(firstdate[1] =="")  && !
			 (firstdate[1].equals("")) )
						{	String sDate1=firstdate[1];
						date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						}
			}catch(ArrayIndexOutOfBoundsException ex1)
			{date1=new Date(2010-01-01);
			System.out.println("Inside Ex1 date = "+date1);
			}
						//System.out.println("Printing inside calll"+todate[1].length());
			try { 
			if(!(todate[1].equals("null")) && 
					 !(todate[1] == "") && 
					 !(todate[1].equals("")) &&
			 !(todate[1] == null) && todate[1].length()>0)
				{
					String sDate2=todate[1];
					date2=new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
						}
			    }
			catch(ArrayIndexOutOfBoundsException ex2)
			{
			date2 = java.sql.Date.valueOf(LocalDate.now());
			System.out.println("Iniside date2 Ex = "+date2);
			}
						  
				
				ModelAndView model = new ModelAndView();
				List <Object[]> reportdata  = loginlogrepository.findReport1(date1,date2);
				model.addObject("reportdata", reportdata);
				
				return  reportdata;
			}
			
			
			
			@RequestMapping(value = {"/getActivityData"}, method = RequestMethod.POST)
				@ResponseBody
				public List<Object[]> getactivityReport(@RequestBody String f_date, HttpServletRequest request) throws ParseException {
					String value="";
					System.out.println("Inside post activity report " +f_date);
					String fdate = f_date;
					String date[] = fdate.split("&");
					//System.out.println("Printing value of split"+date[0]+" "+date[1]);
					
					String[] firstdate=date[0].split("=");
					String [] todate= date[1].split("=");
					
					java.util.Date datee=new java.util.Date();
					Timestamp ts=new Timestamp(datee.getTime());
					
					Date date2= null;
					Date date1= null;
					
					
		try { if(firstdate[1].length()>0 && firstdate[1]!= null 
		 && !(firstdate[1] =="")  && !
		 (firstdate[1].equals("")) )
					{	String sDate1=firstdate[1];
					date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
					}
		}catch(ArrayIndexOutOfBoundsException ex1)
		{date1=new Date(2010-01-01);
		//System.out.println("Inside Ex1 date = "+date1);
		}
					//System.out.println("Printing inside calll"+todate[1].length());
		try { 
		if(!(todate[1].equals("null")) && 
				 !(todate[1] == "") && 
				 !(todate[1].equals("")) &&
		 !(todate[1] == null) && todate[1].length()>0)
					{
						String sDate2=todate[1];
						date2=new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
					}
		    }
		catch(ArrayIndexOutOfBoundsException ex2)
		{
		date2 = java.sql.Date.valueOf(LocalDate.now());
		//System.out.println("Iniside date2 Ex = "+date2);
		}
					  
					
					ModelAndView model = new ModelAndView();
					List <Object[]> reportdata  = activitylogrepository.findReport1(date1,date2);
					model.addObject("reportdata", reportdata);
					
					return  reportdata;
				}

				
				@RequestMapping(value = { "/ppvfra/getusername_role" }, method = RequestMethod.POST)
				@ResponseBody
				public List<Object[]> getuserName(@RequestBody String userrole,Model model,HttpServletRequest request)
				{	try {
				
				String a[] = userrole.split("=");
				
				List<Object[]>  user_role_id = userrepository.getuserviaroleid(Integer.parseInt(a[1]));
				model.addAttribute("user_role_id",user_role_id);
				return user_role_id;

				} catch (NumberFormatException e) {

				e.printStackTrace();
				}
				return null;

				}	
	
				
//	Umesh Adding here on 30-12-2019
//Umesh Adding here For Showing The Pdf Generated just
//Before Send Acknowledegement Letter To Applicant
	
				//  UPLOAD_FILEPATH+"PPVFRA/PROCESS/Acknowledge");
				    @RequestMapping(value = "/ppvfra/download/Acknowledge/{applicationid}", method = RequestMethod.GET)
					 public void applicant_report(HttpServletResponse response,@PathVariable("applicationid") String applicationid) throws IOException {
						 
				    	String type=".pdf";																					   
						String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
						File file = null;
						file=new File(UPLOAD_FILEPATH+"PPVFRA/PROCESS/Acknowledge/"+applicationid+type);
						if(!file.exists())
						{
							file=new File(UPLOAD_FILEPATH+"PPVFRA/PROCESS/Acknowledge/"+applicationid+type);
						}
						if(!file.exists())
						{
					        String errorMessage = "Sorry. The file you are looking for does not exist";
					        OutputStream outputStream = response.getOutputStream();
					        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
					        outputStream.close();
					        return;
					     }
						    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
					        if(mimeType==null)
					        {
					            mimeType = "application/octet-stream";
					        }
					         response.setContentType(mimeType);
					         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
					         response.setContentLength((int)file.length());
							InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
							FileCopyUtils.copy(inputStream, response.getOutputStream());
					}				
				
				    //Umesh Adding Here Ends ------------30-12-2019				
				
				//Umesh Adding here on 03-01-2020   ------------------
					
				
				@RequestMapping(value = {"/ppvfra/removing_allprevileges"}, method = RequestMethod.POST )
				@ResponseBody
				public String removing_previleges(@RequestBody String data, HttpServletRequest request,Model model)
				{
					
					System.out.println("Printing data = "+data);
					String b[] = data.split("&");
					String rol[] =b[0].split("=");
					int r1 = Integer.parseInt(rol[1].toString());
					System.out.println("Printing role= "+r1);
					String mod[] =b[1].split("=");
					int mod1 =Integer.parseInt(mod[1].toString());
					System.out.println("Printing module= "+mod1);
					
					if(rolemodulerepository.remove_by_role_module_id(r1, mod1) ==1)
					{
						return "success";
					}
					else return "false";
				}
				
				
				@RequestMapping(value = { "/ppvfra/profilemanagement" }, method = RequestMethod.GET)
				public String getprofile(Model model) {
					//System.out.println("inside profile Management");
					
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
					
					List<Object[]> userdata_profile=userrepository.getprofiledata(userid);
					model.addAttribute("userdata_profile",userdata_profile.get(0));
					

					 //Umesh Adding here on 14-01-2020 -------
					//Added Here For Name And Role Showing in header
						
				   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
					model.addAttribute("usrname_header_val",usrname_header_val);
						List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
						model.addAttribute("rolespresent",rolespresent);
					//Umesh Adding ends here 
						
					return"profile";
				}
				
				
				@RequestMapping(value = { "/chngpass" }, method = RequestMethod.POST)
				public String getprofile_chngpass(Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
					System.out.println("inside profile Management");
					
					int userid = 0;
					boolean ismatch=false;
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
					String passtochange="";
					String passnew="";
					String passconfirm="";
					
					passtochange=request.getParameter("oldpassword");
					passnew =request.getParameter("newpwd");
					passconfirm = request.getParameter("confpwd");
					
					System.out.println("Printing the passnew= "+passnew+"passconfirm as = "+passconfirm); 
					
					
					System.out.println("Printing the pass to change is = "+passtochange+"getting password from user="+userdeail.getPassword());
					 BCryptPasswordEncoder passtomatch = new BCryptPasswordEncoder(12);
					 ismatch=passtomatch.matches(passtochange ,userdeail.getPassword());
					 System.out.println("Whether match ="+ismatch);
					 int t=0;
					 if(!ismatch)
					 {
						 redirectAttributes.addFlashAttribute("message", "Old Password does not match.Please Try Again.");
					 }else
						{
						
						 if(!(passnew.equals(passconfirm)))
							{
								System.out.println("Inside New Password Does Not Match The Confirm Password");
							redirectAttributes.addFlashAttribute("message", "New Password And Confirmed Password Does not Match. Please Try Again.");
							
							}
						 
						 else {
							 t=useraddrepository.update(passtomatch.encode(passconfirm), userid); 
						 if(t==1)
						 {
							 redirectAttributes.addFlashAttribute("message", "Password Changed Successfully.");
						 }
						 }
							
						}
					
					 return "redirect:/ppvfra/profilemanagement";
				}
				
			 
				 @RequestMapping(value = "/ppvfra/download/paymentdetails/{type}", method = RequestMethod.GET)
				 public void downloadpaidattachments(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
					 
																										   
					String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
					File file = null;
					file=new File(UPLOAD_FILEPATH+"PAYMENT/"+type);
					if(!file.exists())
					{
						file=new File(UPLOAD_FILEPATH+"PAYMENT/"+type);
					}
					if(!file.exists())
					{
				        String errorMessage = "Sorry. The file you are looking for does not exist";
				        OutputStream outputStream = response.getOutputStream();
				        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
				        outputStream.close();
				        return;
				     }
					    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
				        if(mimeType==null)
				        {
				            mimeType = "application/octet-stream";
				        }
				         response.setContentType(mimeType);
				         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
				         response.setContentLength((int)file.length());
						InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
						FileCopyUtils.copy(inputStream, response.getOutputStream());
				}
				 
				 
				 
				 @RequestMapping(value = "/ppvfra/download/formvnotification/{type}", method = RequestMethod.GET)
				 public void downloadformvnotification(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
					 
																										   
					String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
					File file = null;
					file=new File(UPLOAD_FILEPATH+"FORMV/"+type);
					if(!file.exists())
					{
						file=new File(UPLOAD_FILEPATH+"FORMV/"+type);
					}
					if(!file.exists())
					{
				        String errorMessage = "Sorry. The file you are looking for does not exist";
				        OutputStream outputStream = response.getOutputStream();
				        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
				        outputStream.close();
				        return;
				     }
					    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
				        if(mimeType==null)
				        {
				            mimeType = "application/octet-stream";
				        }
				         response.setContentType(mimeType);
				         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
				         response.setContentLength((int)file.length());
						InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
						FileCopyUtils.copy(inputStream, response.getOutputStream());
				}
				
				 
				 
				 @RequestMapping(value = "/ppvfra/download/app_attach/{type}", method = RequestMethod.GET)
				 public void downloadforattachments(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
					 
																										   
					String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
					File file = null;
					file=new File(UPLOAD_FILEPATH+"DOCUMENTS/"+type);
					if(!file.exists())
					{
						file=new File(UPLOAD_FILEPATH+"DOCUMENTS/"+type);
					}
					if(!file.exists())
					{
				        String errorMessage = "Sorry. The file you are looking for does not exist";
				        OutputStream outputStream = response.getOutputStream();
				        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
				        outputStream.close();
				        return;
				     }
					    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
				        if(mimeType==null)
				        {
				            mimeType = "application/octet-stream";
				        }
				         response.setContentType(mimeType);
				         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
				         response.setContentLength((int)file.length());
						InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
						FileCopyUtils.copy(inputStream, response.getOutputStream());
				}
				 
			
				 
				 //Umesh Adding Ends here -------
				 
		 
			@RequestMapping(value = { "/ppvfra/gettypeline" }, method = RequestMethod.POST)
			@ResponseBody
			public List<Object[]> getTypeline_data(@RequestBody String typeline,Model model,HttpServletRequest request)
			{	
				 
				try {
			//String state=request.getParameter("stateCode");
			System.out.println("trace="+typeline);
			String a[] = typeline.split("=");
			       
			//int statecode = Integer.parseInt(state);
			//System.out.println("trace="+state);
			if(a[1].equals("DUS"))
			{
				List<Object[]> typeline1 = typelinerepository.gettypeline_datasecond();
			       model.addAttribute("typeline1",typeline1);
			       return typeline1;
			}
			else//(a[1].equals("Hybridity"))
			{
				List<Object[]> typeline1    = typelinerepository.gettypeline_data();
			       model.addAttribute("typeline1",typeline1);
			       return typeline1;
			}
			} catch ( Exception e) {

			//e.printStackTrace();
	 
			}
				return null; 
			
		

			}
			
			 
	  @RequestMapping(value = {"/ppvfra/getdatasaved_forsts"}, method = RequestMethod.POST )
		@ResponseBody
		public List<Object[]> datadetails_sts(@RequestBody String Obj,  Model model)
		{
		 
			
			String no[] = Obj.split("=");
			List<Object[]> dataintab_sts= applicationststest.applicationsts_saveddata(Integer.parseInt(no[0]));
			if(dataintab_sts !=null)
			{
				model.addAttribute("dataintab_sts",dataintab_sts);
				return dataintab_sts;
			}
			else { return null;} 
			
			
		}
		
		
		
		 @RequestMapping(value = {"/ppvfra/getscreenvalue"}, method = RequestMethod.POST )
			@ResponseBody
			public List<ApplicationSubmission> fetchscreenval(@RequestBody String application_id, HttpServletRequest request,Model model)
			{
				String value="";
				String a = application_id;
				//System.out.println("IN GET SCREEN 1670" +a);
				String b[] = a.split("="); 
				List<ApplicationSubmission> appsubdata= applicationsubmissionrepository.getMFormSectiondata(Integer.parseInt(b[1]));
				 if(appsubdata !=null && appsubdata.size()>0)
					 {
					 model.addAttribute("appsubdata",appsubdata);
					 }
				 
				 
				return appsubdata;
			}
		 
		 
		 
		    @RequestMapping(value = { "/discrepancyreport_filling_applicant/{applicationid}" }, method = RequestMethod.GET)
			public String get_discrepancy_data( Model model,HttpServletRequest request,@PathVariable("applicationid") int applicationid)
			{	
			System.out.println("Control on line 1697 : "+applicationid);
			
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
			
			
			List<Object[]> header_data = applicationrepository.fetch_headerdata(applicationid);
			if(header_data != null && header_data.size()>0 )
			{  
			model.addAttribute("headerdata",header_data);
			}
			
			List<Object[]> getcan_var = candidatevarietydetailsrepository.can_var_type(applicationid);
			if(getcan_var!=null && getcan_var.size()>0)
			{
			model.addAttribute("can_var",getcan_var.get(0));
			}
			
			
			ApplicationScrutiny applicationscrutiny =new ApplicationScrutiny();
			model.addAttribute("applicationscrutiny",applicationscrutiny);
			
			//Adding here on 17-06-2020
			//List<ApplicationScrutiny> applicationscrutinydata=applicationscrutinyrepository.getdataviaid(applicationid);
			
			List<Object[]> applicationscrutinydata=applicationscrutinyrepository.get_scr_details(applicationid);
			if(applicationscrutinydata !=null && applicationscrutinydata.size()>0)
			model.addAttribute("applicationscrutinydata",applicationscrutinydata);
			
			List<Object[]> data_scr= applicationrepository.saved_data_scrutiny(applicationid);
			if(data_scr != null && data_scr.size()>0)
			{
				model.addAttribute("data_scr",data_scr.get(0));
			} 
			
			
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

			
			return "applicationprocessing/applicant_fill_discrepancy";
			
			
			}
		    
		    
		    
		    
				
			@RequestMapping(value = {"/ppvfra/get_toc_datavalidation"}, method = RequestMethod.POST )
			@ResponseBody
			public List<Object[]> toc_validationdetails(@RequestBody String Obj,  Model model)
				{
				 
				System.out.println("Printing The SNo And No as"+Obj);
				
					String no[] = Obj.split("&");
					String no1[] = no[0].split("=");
					String sno1[] = no[1].split("=");
					  List<Object[]> applicationvariety = applicationdusvarietyrepository.getAllVarityByApplicationId(Integer.parseInt(no1[1]),Integer.parseInt(sno1[1]));
					  if(applicationvariety !=null) 
					{
						  model.addAttribute("applicationvariety",applicationvariety);
						  return applicationvariety;
					}
					else { return null;} 
					
					
				}
			
			
			
			
			  @RequestMapping(value = {"/ppvfra/getdatasaved_forf1_10"}, method = RequestMethod.POST )
				@ResponseBody
				public List<Object[]> datadetails_f1_10(@RequestBody String Obj,  Model model)
				{
				 
					
					String no[] = Obj.split("=");
					List<Object[]> dataintab_f_10= applicationparentallinerepository.getParentalLineData(Integer.parseInt(no[0]));
					if(dataintab_f_10 !=null)
					{
						model.addAttribute("dataintab_f_10",dataintab_f_10);
						return dataintab_f_10;
					}
					else { return null;} 
					
					
				}
				

				@RequestMapping(value = { "/ppvfra/getrenewaldatafee" }, method = RequestMethod.POST)
				@ResponseBody
				List<ApplicationPayments> getrenewalfeesdatafees(@RequestBody String variety,Model model,HttpServletRequest request)
				{	
				 int appid=0;
			 
				System.out.println("Trace on 1824: "+variety);
				String a[] = variety.split("=");
				System.out.println("Trace value---1826-"+a[0]+" -var-"+a[1]);
				appid=Integer.parseInt(a[1]);
				
				List<ApplicationPayments> getdusfeedata=applicationpaymentrepository.getrenewalfeedata(appid);
				if(getdusfeedata != null && getdusfeedata.size()>0 )
				{
					model.addAttribute("getdusfeedata",getdusfeedata);
					model.addAttribute("getdusfeedata1",getdusfeedata.get(0));
					model.addAttribute("btenable",1);
				}else {
					model.addAttribute("btenable",0);
				}
				return getdusfeedata;
				 
				}
				
				
				@RequestMapping(value = "/ppvfra/downloadquery/{applicationid}/{filename}", method = RequestMethod.GET)
				 public void downloadReportofquery(HttpServletResponse response, @PathVariable("applicationid") int applicationid,@PathVariable("filename") String filename) throws IOException {
					 
																										   
					String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
					File file = null;
					file=new File(UPLOAD_FILEPATH+"PPVFRA/PROCESS/SENDQUERY/"+applicationid+"/"+filename);
					if(!file.exists())
					{
						file=new File(UPLOAD_FILEPATH+"PPVFRA/PROCESS/SENDQUERY/"+applicationid+"/"+filename);
					}
					if(!file.exists())
					{
				        String errorMessage = "Sorry. The file you are looking for does not exist";
				        OutputStream outputStream = response.getOutputStream();
				        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
				        outputStream.close();
				        return;
				     }
					    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
				        if(mimeType==null)
				        {
				            mimeType = "application/octet-stream";
				        }
				         response.setContentType(mimeType);
				         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
				         response.setContentLength((int)file.length());
						InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
						FileCopyUtils.copy(inputStream, response.getOutputStream());
				}			
				
				
				@RequestMapping(value = {"/ppvfra/checkdenomination"}, method = RequestMethod.POST )
				@ResponseBody
				public String check_denomination(@RequestBody String deno, HttpServletRequest request)
				{
					String value="";
					System.out.println("Trace on line 1971 === "+deno.toUpperCase());
					List<Application> denochk= applicationrepository.denocheck(deno.toUpperCase());
					if(denochk.size() >= 1)
					{
						value="taken";
					}
					else
					{
						value="not taken";
					}
					
					return value;
				}
				
				
				
		 @RequestMapping(value = "/ppvfra/download/f3formvnotification/{type}", method = RequestMethod.GET)
		 public void downloadf3formvnotification(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
			 
																								   
			String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
			File file = null;
			file=new File(UPLOAD_FILEPATH+"F3FORMV/"+type);
			if(!file.exists())
			{
				file=new File(UPLOAD_FILEPATH+"F3FORMV/"+type);
			}
			if(!file.exists())
			{
		        String errorMessage = "Sorry. The file you are looking for does not exist";
		        OutputStream outputStream = response.getOutputStream();
		        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
		        outputStream.close();
		        return;
		     }
			    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
		        if(mimeType==null)
		        {
		            mimeType = "application/octet-stream";
		        }
		         response.setContentType(mimeType);
		         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
		         response.setContentLength((int)file.length());
				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
				
		 
		 @RequestMapping(value = "/ppvfra/download/f3_app_attach/{type}", method = RequestMethod.GET)
		 public void f3_downloadforattachments(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
			 
																								   
			String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
			File file = null;
			file=new File(UPLOAD_FILEPATH+"F3DOCUMENTS/"+type);
			if(!file.exists())
			{
				file=new File(UPLOAD_FILEPATH+"F3DOCUMENTS/"+type);
			}
			if(!file.exists())
			{
		        String errorMessage = "Sorry. The file you are looking for does not exist";
		        OutputStream outputStream = response.getOutputStream();
		        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
		        outputStream.close();
		        return;
		     }
			    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
		        if(mimeType==null)
		        {
		            mimeType = "application/octet-stream";
		        }
		         response.setContentType(mimeType);
		         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
		         response.setContentLength((int)file.length());
				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
		 
		 @RequestMapping(value = {"/ppvfra/getscreenvalue_f3"}, method = RequestMethod.POST )
			@ResponseBody
			public List<ApplicationSubmission> fetchscreenvalf3(@RequestBody String application_id, HttpServletRequest request,Model model)
			{
				String value="";
				String a = application_id;
				System.out.println("IN GET SCREEN 1984" +a);
				String b[] = a.split("="); 
				List<ApplicationSubmission> appsubdata= applicationsubmissionrepository.getMFormSectiondata(Integer.parseInt(b[1]));
				 if(appsubdata !=null && appsubdata.size()>0)
					 {
					 model.addAttribute("appsubdata",appsubdata);
					 }
				 
				 
				return appsubdata;
			}
			
			
		 @RequestMapping(value = {"/ppvfra/getscreenvalue_f2"}, method = RequestMethod.POST )
			@ResponseBody
			public List<ApplicationSubmission> fetchscreenvalf2(@RequestBody String application_id, HttpServletRequest request,Model model)
			{
				String value="";
				String a = application_id;
				//System.out.println("IN GET SCREEN 1670" +a);
				String b[] = a.split("="); 
				List<ApplicationSubmission> appsubdata= applicationsubmissionrepository.getMFormSectiondata(Integer.parseInt(b[1]));
				 if(appsubdata !=null && appsubdata.size()>0)
					 {
					 model.addAttribute("appsubdata",appsubdata);
					 }
				 
				 
				return appsubdata;
			}
		 @RequestMapping(value = "/ppvfra/{type}", method = RequestMethod.GET)
		 public void openfullpdf(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
			 
																								   
			String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
			File file = null;
			file=new File(UPLOAD_FILEPATH+"PPVFRA/"+type);
			if(!file.exists())
			{
				file=new File(UPLOAD_FILEPATH+"PPVFRA/"+type);
			}
			if(!file.exists())
			{
		        String errorMessage = "Sorry. The file you are looking for does not exist";
		        OutputStream outputStream = response.getOutputStream();
		        outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
		        outputStream.close();
		        return;
		     }
			    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
		        if(mimeType==null)
		        {
		            mimeType = "application/octet-stream";
		        }
		         response.setContentType(mimeType);
		         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
		         response.setContentLength((int)file.length());
				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
		
		 
		 
		 
		 @RequestMapping(value = { "/ppvfra/getpaymentanual_fee_pop" }, method = RequestMethod.POST)
			@ResponseBody
			List<Object[]> getannualpayments_data_pop(@RequestBody String id,Model model,HttpServletRequest request)
			{	
			 int appid=0;
		 
			System.out.println("Trace on 2118: "+id);
			String a[] = id.split("=");
			System.out.println("Trace value---2120-"+a[0]+" -var-"+a[1]);
			appid=Integer.parseInt(a[1]);
			
			List<Object[]> getdusfeedata=feerepository.getannualfee_pop(appid);
			if(getdusfeedata != null && getdusfeedata.size()>0 )
			{
				model.addAttribute("getdusfeedata",getdusfeedata);
				model.addAttribute("getdusfeedata1",getdusfeedata.get(0));
				
			}
			
			return getdusfeedata;
			 
			}
	
		
			
			@RequestMapping(value = { "/ppvfra/getobservation_data" }, method = RequestMethod.POST)
			@ResponseBody
			public List<ObservationCategory> getobservationdata(@RequestBody String formcode,Model model,HttpServletRequest request)
			{	try {
			//String state=request.getParameter("stateCode");
			System.out.println("trace="+formcode);
			String a[] = formcode.split("=");
			       
			//int statecode = Integer.parseInt(state);
			System.out.println("trace="+a[0]+"and other"+a[1]);
			List<ObservationCategory>  observationdata = observationcategory.getobservationdata(Integer.parseInt(a[1]));
			       model.addAttribute("observationdata",observationdata);
			       return observationdata;

			} catch (NumberFormatException e) {

			e.printStackTrace();
			}
			return null;

			}

				 
}