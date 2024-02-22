package com.ppvfra.config;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class MailConfig {

	@Autowired
	Environment env;
	
	//@Bean
	public MimeMessage mimeMessage(){
		
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("spring.mail.smtp.host", env.getProperty("spring.mail.smtp.host"));
        javaMailProperties.put("spring.mail.smtp.socketFactory.port", env.getProperty("spring.mail.smtp.socketFactory.port"));
        javaMailProperties.put("spring.mail.smtp.socketFactory.class", env.getProperty("spring.mail.smtp.socketFactory.class"));
        javaMailProperties.put("spring.mail.smtp.auth", env.getProperty("spring.mail.smtp.auth"));
        javaMailProperties.put("spring.mail.smtp.port", env.getProperty("spring.mail.smtp.port"));       
        
        Session session = Session.getInstance(javaMailProperties,    
                new javax.mail.Authenticator() {    
                protected PasswordAuthentication getPasswordAuthentication() {    
                return new PasswordAuthentication(env.getProperty("spring.mail.username"),env.getProperty("spring.mail.password"));  
                }    
               });
        MimeMessage message = new MimeMessage(session); 
        return message;
	}
	
}
