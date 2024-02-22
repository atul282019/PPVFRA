package com.ppvfra.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name="applicantdata")
public class Applicantdata {
	@Id
	private Integer applicantid;
	private String applicantrole;
	private String name;
	private String username;
	private String email;
	private String mobile_number;
	private String designation;
	private String address;
	private String country;
	private String statename;
	private String districtname;
	private String city;
	private String pincode;
	private String verificationstatus;
	private String verifierremarks;
	
	
	
	
	public Integer getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(Integer applicantid) {
		this.applicantid = applicantid;
	}
	public String getApplicantrole() {
		return applicantrole;
	}
	public void setApplicantrole(String applicantrole) {
		this.applicantrole = applicantrole;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getDistrictname() {
		return districtname;
	}
	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getVerificationstatus() {
		return verificationstatus;
	}
	public void setVerificationstatus(String verificationstatus) {
		this.verificationstatus = verificationstatus;
	}
	public String getVerifierremarks() {
		return verifierremarks;
	}
	public void setVerifierremarks(String verifierremarks) {
		this.verifierremarks = verifierremarks;
	}
	
		
		
}
