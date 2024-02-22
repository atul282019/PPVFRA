package com.ppvfra.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DateConverter implements AttributeConverter<String,Date> {

	 @Override
	 public Date convertToDatabaseColumn(String orderdate) {
		 Date d;
		 if(orderdate!=null) {
		 try {
			 d=new SimpleDateFormat("yyyy-MM-dd").parse(orderdate);
			 return d;
		 }
		 catch(Exception ex)
		 {
			return null; 
			 
		 }
		 }
		 else {d=null;}
		 
		 return d;
	 }


	 @Override
	 public String convertToEntityAttribute(Date orderdate) {
	 
		 String d;
		 try {
			 DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");  
			 d= dateFormat.format(orderdate);  
			 return d;
		 }
		 catch(Exception ex)
		 {
			return null; 
			 
		 }	 }

	}
