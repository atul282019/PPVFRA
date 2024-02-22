package com.ppvfra.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;



@Entity
@Table(name="applicants")
public class ApplicantRegistration {
		@Id 
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		
		@NotNull(message="Please select Applicant Type first")
		@Min(value=1,message="Please select Applicant Type first")
		private Integer applicanttype;
		
		private Integer companyid;
		private Integer institutionid;
		private String applicantname;
		
		@NotNull(message="Please select Country")
		@Min(value=1,message="Please select Country")
		private Integer countryid;
		
		
		@Min(value=1,message="Please select state first")
		private Integer stateid;
		
		
		@Min(value=1,message="Please select District")
		private Integer districtid;
		
		@NotBlank(message="City Cannot be blank")
		@Size(min=1,max=150)
		private String city;
		
		@NotBlank(message="Address Cannot be blank")
		@Size(min=1,max=150)
		private String address;
		
		@NotNull
		@Size(min=6, max=6)
		@Pattern(regexp="(^$|[0-9]{6})", message="Please enter numeric pincode")
		private String pincode;
		
		private Boolean active;
		private int createdby;
		private Timestamp createdon;
	    private int  modifiedby;
	    private Timestamp modifiedon;
	    private int verifiedby;
	    private String verifiedbydesignation;
	    private String verificationstatus;
	    private String verifierremarks;
	    private String createdbyip;
	    private String modifiedbyip;
	    private String record_language;
	    
	   
	    @NotBlank(message="Name Cannot be blank")
	    private transient String firstname;
	    
	    @Email
	    @NotBlank(message="Email Cannot be blank")
	    @Size(min=1,max=100)
	    private transient String email;
	 
	    @NotBlank(message="Mobile no Cannot be blank")
	    private transient String mobile_number;
	
	    @NotBlank(message="Contact no Cannot be blank")
	    private transient String contactno;
	   
	    @NotBlank(message="Field Cannot be blank")
	    private transient String designation;
	   
	    @NotBlank(message="Username Cannot be blank")
	    @Size(min=1,max=100)
	    private transient String username;
	  
	
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Integer getApplicanttype() {
			return applicanttype;
		}
		public void setApplicanttype(Integer applicanttype) {
			this.applicanttype = applicanttype;
		}
		public Integer getCompanyid() {
			return companyid;
		}
		public void setCompanyid(Integer companyid) {
			this.companyid = companyid;
		}
		public Integer getInstitutionid() {
			return institutionid;
		}
		public void setInstitutionid(Integer institutionid) {
			this.institutionid = institutionid;
		}
		public String getApplicantname() {
			return applicantname;
		}
		public void setApplicantname(String applicantname) {
			this.applicantname = applicantname;
		}
		public Integer getCountryid() {
			return countryid;
		}
		public void setCountryid(Integer countryid) {
			this.countryid = countryid;
		}
		public Integer getStateid() {
			return stateid;
		}
		public void setStateid(Integer stateid) {
			this.stateid = stateid;
		}
		public Integer getDistrictid() {
			return districtid;
		}
		public void setDistrictid(Integer districtid) {
			this.districtid = districtid;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public Boolean getActive() {
			return active;
		}
		public void setActive(Boolean active) {
			this.active = active;
		}
		public int getCreatedby() {
			return createdby;
		}
		public void setCreatedby(int createdby) {
			this.createdby = createdby;
		}
		public Timestamp getCreatedon() {
			return createdon;
		}
		public void setCreatedon(Timestamp createdon) {
			this.createdon = createdon;
		}
		public int getModifiedby() {
			return modifiedby;
		}
		public void setModifiedby(int modifiedby) {
			this.modifiedby = modifiedby;
		}
		public Timestamp getModifiedon() {
			return modifiedon;
		}
		public void setModifiedon(Timestamp modifiedon) {
			this.modifiedon = modifiedon;
		}
		public int getVerifiedby() {
			return verifiedby;
		}
		public void setVerifiedby(int verifiedby) {
			this.verifiedby = verifiedby;
		}
		public String getVerifiedbydesignation() {
			return verifiedbydesignation;
		}
		public void setVerifiedbydesignation(String verifiedbydesignation) {
			this.verifiedbydesignation = verifiedbydesignation;
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
		public String getCreatedbyip() {
			return createdbyip;
		}
		public void setCreatedbyip(String createdbyip) {
			this.createdbyip = createdbyip;
		}
		public String getModifiedbyip() {
			return modifiedbyip;
		}
		public void setModifiedbyip(String modifiedbyip) {
			this.modifiedbyip = modifiedbyip;
		}
		public String getRecord_language() {
			return record_language;
		}
		public void setRecord_language(String record_language) {
			this.record_language = record_language;
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
		public String getContactno() {
			return contactno;
		}
		public void setContactno(String contactno) {
			this.contactno = contactno;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
	

}
