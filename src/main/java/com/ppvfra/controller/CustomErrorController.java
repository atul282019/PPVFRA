package com.ppvfra.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppvfra.repository.ModulesRepository;
import com.ppvfra.repository.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomErrorController implements ErrorController {
	@Autowired
	ModulesRepository modulesrepository;
	
	@Autowired
	UserRepository userrepository;
	
private static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorController.class);

 private static final String PATH = "/error";

 @RequestMapping("/error")
public String handleError(HttpServletRequest request) {
 Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

 if (status != null) {
     Integer statusCode = Integer.valueOf(status.toString());

     if(statusCode == HttpStatus.NOT_FOUND.value()) {
         return "error";
     }
     else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
         return "error";
     }
     else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
    	 return "error";
     }
 }
 return "error";
}

 @Override
 public String getErrorPath() {
  return PATH;
 }
}