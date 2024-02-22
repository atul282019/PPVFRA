package com.ppvfra.config.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.ppvfra.entity.LogTable;
import com.ppvfra.repository.LoginLogRepository;

@Component
public class CustomAuthenticationFailureHandler 
extends SimpleUrlAuthenticationFailureHandler 
{
	@Autowired
	LoginLogRepository loginlog;
	
	@Override
	public void onAuthenticationFailure(
	HttpServletRequest request, 
	HttpServletResponse response,
	AuthenticationException exception) 
	throws IOException, ServletException 
	{
		
	//System.out.println("Trace in default login failure----34 Line No");
		setDefaultFailureUrl("/login?error=true");
		
		super.onAuthenticationFailure(request, response, exception);
		
		LogTable logTable = new LogTable();
		logTable.setIpaddress(request.getRemoteAddr());
		logTable.setLogintype("Login Failed");
		logTable.setLogin_time_stamp(new Date());
		logTable.setUsername(request.getParameter("username"));
		//System.out.println("Printing username=> "+request.getParameter("username"));
		String errorMessage = "Invalid Username or Password";
		
		loginlog.save(logTable);
		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
	}
	
}