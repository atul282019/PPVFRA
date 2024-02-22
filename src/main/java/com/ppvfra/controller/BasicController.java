package com.ppvfra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasicController {


	
	    @RequestMapping(value="/login", method= RequestMethod.GET)
		public String getLogin()
		{ 
			return "login";
		}
	    @RequestMapping(value="/userlogin", method=RequestMethod.GET)  
	    public ModelAndView getAdminDashboard()
	    { 
		    ModelAndView modelAndView = new ModelAndView();  
		    modelAndView.setViewName("informatic_dashboard");    
			return modelAndView;  
	    }
	    
	    @RequestMapping(value="/register", method=RequestMethod.GET)  
        public ModelAndView getRegister()
	    {  
	        ModelAndView modelAndView = new ModelAndView();  
			modelAndView.setViewName("register");    
			return modelAndView;  
        }  

}
