package com.ppvfra.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import com.ppvfra.entity.CropGroup;
import com.ppvfra.entity.Crops;
import com.ppvfra.entity.DusCharacteristicGroup;
import com.ppvfra.entity.DusCharacteristics;
import com.ppvfra.entity.DusCharacteristicsStates;
import com.ppvfra.entity.User;
import com.ppvfra.repository.ActivityLogRepository;
import com.ppvfra.repository.AddCropGroupRepository;
import com.ppvfra.repository.AddCropsRepository;
import com.ppvfra.repository.DusCharacteristicsRepository;
import com.ppvfra.repository.DusCharacteristicsStatesRepository;
import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.RoleAssignRepository;
import com.ppvfra.repository.Role_ModulesRepository;
import com.ppvfra.repository.UserRepository;
import com.ppvfra.service.ActivityLogService;
import com.ppvfra.service.AddCropGroupService;
import com.ppvfra.service.AddcropService;
import com.ppvfra.service.DusCharacteristicGroupService;
import com.ppvfra.service.DusCharacteristicsService;
import com.ppvfra.service.DusCharacteristicsStatesService;

@Controller
public class DusCharacteristicsController {
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	AddcropService addcropservice;

	@Autowired
	AddCropGroupService addcropgroupservice;
	
	@Autowired
	DusCharacteristicsRepository duscharacteristicsrepository;
	
	@Autowired
	DusCharacteristicGroupService duscharacteristicgroupservice;
	
	@Autowired
	Environment env;
	
	@Autowired
	DusCharacteristicsService duscharacteristicsservice;
	
	@Autowired 
	DusCharacteristicsStatesRepository duscharacteristicsstatesrepository;
	
	@Autowired
	DusCharacteristicsStatesService duscharacteristicsstatesservice;
	
	@Autowired
	Role_ModulesRepository rolemodulerepository;
	
	@Autowired
	ActivityLogService activitylogservice;
	
	@Autowired
	AddCropsRepository addcroprepository;
	
	@Autowired
	AddCropGroupRepository addcropgrprep;
	
	@Autowired
	RoleAssignRepository roleassignrepository;
	
	
	//comments
	//this method is used to get the view of duscharacteristics and show the saved data
	@RequestMapping(value = "/getduscharacteristics", method = RequestMethod.GET)
	public String getDuscharacteristics(Model model,HttpServletRequest request)
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
	
	  //Commenting here on 24-12-2019-
	  //List<CropGroup> Crop_Group = addcropgroupservice.listall();
	    List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
		model.addAttribute("Crop_Group", Crop_Group);
		
		//Umesh Adding here for active crops only
		//List<Crops> Crop = addcropservice.listall();
		List<Crops> Crop = addcroprepository.getAllActiveCrops();
		model.addAttribute("Crop", Crop);

		
	//	List <Object[]> addduscharacteristicslist = duscharacteristicsrepository.findAllDusCharacteristics();
		List <Object[]> addduscharacteristicslist = duscharacteristicsrepository.get_dus_data();
		model.addAttribute("addduscharacteristicslist", addduscharacteristicslist);
		
		DusCharacteristics duscharacteristics = new DusCharacteristics();
		model.addAttribute("duscharacteristics", duscharacteristics);
		 
		StringBuffer url=request.getRequestURL();
			String val=url.substring(url.lastIndexOf("/"),url.length());
			//System.out.println("PPPPP==="+val);
			  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
			 if(actd !=null)
			 {
				 Object actd_viewtrue=null;
				 Object actd_editvaluetrue = null;
				 
				 model.addAttribute("actd",actd);
				 
			//  System.out.println("PRINTING contains"+actd.contains("View")); 
				
			   if(actd.contains("View"))
					 {
					 actd_viewtrue ="valpresent";
		//	System.out.println("PRINING TRUEEEEE"+actd_viewtrue); 
					
					model.addAttribute("actd_viewtrue",actd_viewtrue);
			 		
					 }
			   
			   if(actd.contains("Edit"))
				 {
				 actd_editvaluetrue ="editpresent";
				 model.addAttribute("actd_editvaluetrue",actd_editvaluetrue);
		 		 }
			   else{
					 
				   System.out.println("EDIT NOT PRESENTTTTTT");
				   
				   actd_editvaluetrue ="editnotpresent";
					 model.addAttribute("actd_editvaluetrue",actd_editvaluetrue);
			 		 }
				 
			 }
			 
			 ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("view");
				act.setUrl("/getduscharacteristics");
				
				activitylogservice.save(act);
			   
				 //Umesh Adding here on 14-01-2020 -------
				//Added Here For Name And Role Showing in header
					
			   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
				model.addAttribute("usrname_header_val",usrname_header_val);
					List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
					model.addAttribute("rolespresent",rolespresent);
				//Umesh Adding ends here  
					
			   
		return "admin/master_dus_characteristics";
	}
	
	//comments
	//this method is used to get the form for adding duscharacteristics
	@RequestMapping(value = "/addduscharacteristics", method = RequestMethod.GET)
	public String addDusCharacteristics(Model model,HttpServletRequest request) {
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
	    
	    //Umesh Commenting here on 24-12-2019
		//List<CropGroup> Crop_Group = addcropgroupservice.listall();
	    List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
		model.addAttribute("Crop_Group", Crop_Group);
		
		List<Crops> Crop = addcropservice.listall();
		model.addAttribute("Crop", Crop);
		List<DusCharacteristicGroup> Group = duscharacteristicgroupservice.listall();
		model.addAttribute("Group",Group);
		DusCharacteristics duscharacteristics = new DusCharacteristics();
		model.addAttribute("duscharacteristics", duscharacteristics);
		
		 ActivityLogTable act = new ActivityLogTable();
			act.setIpaddress(request.getRemoteAddr());
			act.setActivityby(userdeail.getUsername());
			Date dt = new Date();
			System.out.println("Current date Is ="+dt);
			act.setLogin_time_stamp(dt);
			act.setActivity("add");
			act.setUrl("/add_duscharacteristics");
			
			activitylogservice.save(act);
		   
			 //Umesh Adding here on 14-01-2020 -------
			//Added Here For Name And Role Showing in header
				
		   List<Object[]> usrname_header_val =userrepository.getUser_Nameviaid(userdeail.getId()); 
			model.addAttribute("usrname_header_val",usrname_header_val);
				List<Object[]> rolespresent= roleassignrepository.getrole_nameviauserid(userid);
				model.addAttribute("rolespresent",rolespresent);
			//Umesh Adding ends here  
		   
		return "admin/master_add_dus_characteristics"; 
	}
 
	//comments
	//this method is used to save duscharacteristics
	@RequestMapping(value = "/saveduscharacteristics", method = RequestMethod.POST)
	public String saveduscharacteristics(@Valid @ModelAttribute(value = "duscharacteristics") DusCharacteristics duscharacteristics, BindingResult bindingResult, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes,@RequestParam MultipartFile[] fileUpload) throws IOException {
		String[] state =request.getParameterValues("State");
		String[] note =request.getParameterValues("Note");
		String[] examplevariety =request.getParameterValues("ExampleVariety");
		
		if (bindingResult.hasErrors())
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
		  
		  //Umesh Commenting here on 24-12-2019 
		  //List<CropGroup> Crop_Group = addcropgroupservice.listall();
		  List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
			model.addAttribute("Crop_Group", Crop_Group);
			
			List<Crops> Crop = addcropservice.listall();
			model.addAttribute("Crop", Crop);
			List<DusCharacteristicGroup> Group = duscharacteristicgroupservice.listall();
			model.addAttribute("Group",Group);
			
			 
			ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("add_duscharacteristics-error");
				act.setUrl("/save_duscharacteristics");
				
				activitylogservice.save(act);
			   
			return "admin/master_add_dus_characteristics";
		} 
		 
		else
		{
			
			 String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
			 HashSet s = new HashSet();
		     for(int i = 0; i < fileUpload.length; i++) {
			 DusCharacteristicsStates states = new DusCharacteristicsStates();
			 states.setStates(state[i]);

					  if((note[i])!=null && (note[i])!="")
					  states.setNote(Integer.parseInt(note[i])); 
					  states.setExample_variety(examplevariety[i]);
				      if(fileUpload[i].getOriginalFilename() == "" || fileUpload[i].getOriginalFilename() == null)
					  continue;
					  if (fileUpload[i].getSize() >0) 
					{
						int cropid=Integer.parseInt(request.getParameter("cropid"));
						StringBuilder filePath = new StringBuilder(
						UPLOAD_FILEPATH+"PPVFRA/DUS_CHARACHTERSTICS/CROPID"); 
						File file = new File(filePath.toString());
						if (!file.exists()) {
						   file.mkdirs();
						}
							
							String filename=	fileUpload[i].getOriginalFilename().substring(0, fileUpload[i].getOriginalFilename().lastIndexOf("."));
							String fileext=	fileUpload[i].getOriginalFilename().substring(fileUpload[i].getOriginalFilename().lastIndexOf("."), fileUpload[i].getOriginalFilename().length() );
							String ffname= (filename.replace(" ", "_")).trim();
							String originalName = cropid+ffname+ new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+fileext;System.out.println("trace in file name 2186 --- "+originalName);
							
							File actFile = new File(filePath.append(File.separator + originalName).toString());
							try {
								Files.copy(fileUpload[i].getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							states.setDocumentname(originalName); 
						    states.setDocumentpath(UPLOAD_FILEPATH+"PPVFRA/DUS_CHARACHTERSTICS/CROPID/"+originalName);
						}
				            s.add(states);
				            duscharacteristics.setDuscharacteristicsstates(s);
				 	 }
			
		     Boolean success=duscharacteristicsservice.save(duscharacteristics);
		
		
		     if (success)
				{
					redirectAttributes.addFlashAttribute("message", "Data Saved Successfully");
				}
				else
				{
					redirectAttributes.addFlashAttribute("message", "Something went wrong please try again.");
				}
		     
		     String username="";
		     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails)
					username = ((UserDetails) principal).getUsername();
					  
					User userdeail = userrepository.getUserDetail(username);
				
				ActivityLogTable act = new ActivityLogTable();
					act.setIpaddress(request.getRemoteAddr());
					act.setActivityby(userdeail.getUsername());
					Date dt = new Date();
					System.out.println("Current date Is ="+dt);
					act.setLogin_time_stamp(dt);
					act.setActivity("save");
					act.setUrl("/saveduscharacteristics");
					
					activitylogservice.save(act);
				   
				}
		return "redirect:/getduscharacteristics";
	}		

	  //comments
	  //this method is used to edit a particular record via its id
	  @RequestMapping(value = "/editduscharachterstics/{id}", method = RequestMethod.GET)
		public ModelAndView editByduscharecterId(@PathVariable(name = "id") Integer id,HttpServletRequest request) {

			ModelAndView mav = new ModelAndView("admin/edit_dus_charachterstics");
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
			 
				
		  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
		  mav.addObject("modulelist", modulelist);
		
		  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
		  mav.addObject("modulelist2", modulelist2);
		  
		  List<Object[]> statedusdata = duscharacteristicsstatesrepository.findAllEntries(id.intValue());
		  mav.addObject("statedusdata",statedusdata);	
		  
		   // Umesh Commenting here on 24-12-2019
		  //List<CropGroup> Crop_Group = addcropgroupservice.listall();
		  List<CropGroup> Crop_Group = addcropgrprep.cropgroup_activedata();
		  mav.addObject("Crop_Group", Crop_Group);
		  
		  List<Crops> Crop = addcropservice.listall();
		  mav.addObject("Crop", Crop);
		  
		  List<DusCharacteristicGroup> Group = duscharacteristicgroupservice.listall();
		  mav.addObject("Group",Group);
		  
		  DusCharacteristics duscharacteristics = duscharacteristicsservice.get(id);
		  mav.addObject("duscharacteristics",duscharacteristics);
		  
		 
			String val="/getduscharacteristics";
			//System.out.println("PPPPP==="+val);
			  List<Object[]> actd = rolemodulerepository.getPrevligedata_accesstypes(userid,val);
			 if(actd !=null)
			 {
				 Object actd_viewtrue=null;
				 Object actd_editvaluetrue = null;
				 
				 mav.addObject("actd",actd);
				 
			//  System.out.println("PRINTING contains"+actd.contains("View")); 
				
			   if(actd.contains("View"))
					 {
					 actd_viewtrue ="valpresent";
		//	System.out.println("PRINING TRUEEEEE"+actd_viewtrue); 
					
					 mav.addObject("actd_viewtrue",actd_viewtrue);
			 		
					 }
			   
			   if(actd.contains("Edit"))
				 {
				 actd_editvaluetrue ="editpresent";
				 mav.addObject("actd_editvaluetrue",actd_editvaluetrue);
		 		 }
			   else{
					 
				   System.out.println("EDIT NOT PRESENTTTTTT");
				   
				   actd_editvaluetrue ="editnotpresent";
				   mav.addObject("actd_editvaluetrue",actd_editvaluetrue);
			 		 }
				 
			 }
			 
			 ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("view");
				act.setUrl("/edit_duscharacteristics");
				
				activitylogservice.save(act);
			 
		  mav.setViewName("admin/edit_dus_charachterstics");
		  return mav;
		}
	  
	  //comments
	  //this method is used to save the record after making changes 
	  //saving method of edit
	  @SuppressWarnings("unlikely-arg-type")
	  @RequestMapping(value = "/newduscharacteristics", method = RequestMethod.POST)
		public String newduscharacteristics(@Valid @ModelAttribute(value = "duscharacteristics") DusCharacteristics duscharacteristics, BindingResult bindingResult, Model model,HttpServletRequest request,@RequestParam MultipartFile[] fileUpload) throws IOException {
			String[] state =request.getParameterValues("State");
			String[] note =request.getParameterValues("Note");
			String[] examplevariety =request.getParameterValues("ExampleVariety");
			
			if (bindingResult.hasErrors())
			{ 
				/// Getting Logged in user
				int userid = 0;
				User userdeail  =  null;
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
					//System.out.println("Priting Loggin user id: " + userid);
					
			  List<Object[]> modulelist = modulesrepository.findParentModulesByUserId(userid);
			  model.addAttribute("modulelist", modulelist);
			
			  List<Object[]> modulelist2 = modulesrepository.findAllModulesByUserId(userid);
			  model.addAttribute("modulelist2", modulelist2);
			  
			  ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("edit-duscharachterstics-error");
				act.setUrl("/edit_duscharacteristics");
				
				activitylogservice.save(act);
			 
				return "admin/master_add_dus_characteristics";
			} 
			else
			{
				System.out.println("REACHING LINE NUMBER 2150 ------------");
				String UPLOAD_FILEPATH = env.getRequiredProperty("static.content.directory");
				String dusidpresent[] =request.getParameterValues("dusid");
				
				HashSet s = new HashSet();
			    for (int i = 0; i < fileUpload.length; i++) {
				//System.out.println("LOOP FOR FILE UPLOAD === "+i+"LENGTH ="+fileUpload.length);
				DusCharacteristicsStates states = new DusCharacteristicsStates();
				//if(Integer.parseInt(dusidpresent[i]) !=0 && !(dusidpresent[i].equals("null")))
					//System.out.println("DUS PRINT "+Integer.parseInt(dusidpresent[i]));
				
				 if(Integer.parseInt(dusidpresent[i]) !=0 && !(dusidpresent[i].equals("null")))
				 states.setId(Integer.parseInt(dusidpresent[i]));
					   
				states.setDuscharacteristicid(Integer.parseInt(request.getParameter("id")));
				states.setStates(state[i]);

				if((note[i])!=null && (note[i])!="")
				states.setNote(Integer.parseInt(note[i])); 
				states.setExample_variety(examplevariety[i]);
				if(fileUpload[i].getOriginalFilename() == "" || fileUpload[i].getOriginalFilename() == null)
				continue;
				if (fileUpload[i].getSize() >0) 
				{
					int cropid=Integer.parseInt(request.getParameter("cropid"));
					//System.out.println("TRACE PRINTING CROP ID = "+cropid); 
					StringBuilder filePath = new StringBuilder(
					UPLOAD_FILEPATH+"PPVFRA/DUS_CHARACHTERSTICS/CROPID"); 
					File file = new File(filePath.toString());
					if (!file.exists()) 
					{
					  file.mkdirs();
					}
					
					
					String filename=	fileUpload[i].getOriginalFilename().substring(0, fileUpload[i].getOriginalFilename().lastIndexOf("."));
					//System.out.println("trace in file name 2182 --- "+filename);
					String fileext=	fileUpload[i].getOriginalFilename().substring(fileUpload[i].getOriginalFilename().lastIndexOf("."), fileUpload[i].getOriginalFilename().length() );
					//System.out.println("trace in file name 2184 --- "+fileext);
								
					String ffname= (filename.replace(" ", "_")).trim();
					//System.out.println("IF SPACE THEM REMOVING THAT SPACE WITH UNDERSCORE"+ffname);
					//String originalName = cropid+filename+ new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+fileext;
					String originalName = cropid+ffname+ new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+fileext;System.out.println("trace in file name 2186 --- "+originalName);
					
					File actFile = new File(filePath.append(File.separator + originalName).toString());
					try {
						Files.copy(fileUpload[i].getInputStream(), actFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(Integer.parseInt(dusidpresent[i]) !=0 && !(dusidpresent[i].equals("null")))
					{
						DusCharacteristicsStates duscharacteristicsstates = duscharacteristicsstatesservice.get(Integer.parseInt(dusidpresent[i]));
					}
				/*
					if(duscharacteristicsstates!= null && (duscharacteristicsstates.equals("null")))
					{
					//String s2=duscharacteristicsstatesrepository.getFileName_Dus(Integer.parseInt(dusidpresent[i]));
					  String s2= duscharacteristicsstates.getDiagram();
					  System.out.println("111"+s2);
					if(!s2.equals(filename))
					{
						System.out.println("222"+s2);
						try
				        { 
				            Files.delete(Paths.get(UPLOAD_FILEPATH+"PPVFRA/DUS_CHARACHTERSTICS/CROPID/"+s2)); 
				        } 
				        catch(NoSuchFileException e) 
				        { 
				            System.out.println("No such file/directory exists"); 
				        } 
					}
					}*/
				   	states.setDocumentname(originalName); 
					states.setDocumentpath(UPLOAD_FILEPATH+"PPVFRA/DUS_CHARACHTERSTICS/CROPID/"+originalName);
				}
					  s.add(states);
					  duscharacteristics.setDuscharacteristicsstates(s);
			}
					 
			    duscharacteristicsservice.save(duscharacteristics);
			    
			    User userdeail  =  null;
			    String username ="";
			    
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails)
				username = ((UserDetails) principal).getUsername();
				
				 userdeail = userrepository.getUserDetail(username);
				
			    
			    ActivityLogTable act = new ActivityLogTable();
				act.setIpaddress(request.getRemoteAddr());
				act.setActivityby(userdeail.getUsername());
				Date dt = new Date();
				System.out.println("Current date Is ="+dt);
				act.setLogin_time_stamp(dt);
				act.setActivity("save");
				act.setUrl("/edit_duscharacteristics");
				
				activitylogservice.save(act);
			 
			
		}
			return "redirect:/getduscharacteristics";
	}

	
			 
}
