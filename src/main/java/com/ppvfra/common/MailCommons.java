package com.ppvfra.common;

import java.util.Date;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailCommons {

	public MimeMessage mimeMessage(){
		
        
        Properties javaMailProperties = new Properties();
       /* javaMailProperties.put("mail.smtp.host", "relay.nic.in");
        javaMailProperties.put("mail.smtp.socketFactory.port", 25);
        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.put("mail.smtp.auth", false);
        javaMailProperties.put("mail.smtp.port", 25);
       
        Session session = Session.getInstance(javaMailProperties);
		 */	
       javaMailProperties.put("mail.smtp.host", "smtp.gmail.com");
        javaMailProperties.put("mail.smtp.socketFactory.port", 465);
        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.port", 465);       
        
        Session session = Session.getInstance(javaMailProperties,    
                new javax.mail.Authenticator() {    
                protected PasswordAuthentication getPasswordAuthentication() {    
                return new PasswordAuthentication("akaltest8@gmail.com","test@1234");  
                }    
               });
        MimeMessage message = new MimeMessage(session); 
        return message;
	}
	
	
}
