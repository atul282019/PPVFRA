package com.ppvfra.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_contacts")
public class ApplicationContacts7 {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
		
		private int applicationid;
		@Column(name="name")
		private String name7;
		@Column(name="designation")
		private String designation7;
		@Column(name="nationality")
		private int nationality7;
		@Column(name="telephone_stdcode")
		private String telephone_stdcode7;
		@Column(name="telephone_number")
		private String telephone_number7;
		@Column(name="mobile_countrycode")
		private String mobile_countrycode7;
		@Column(name="mobile_number")
		private String mobile_number7;
		@Column(name="fax_stdcode")
		private String fax_stdcode7;
		@Column(name="fax_number")
		private String fax_number7;
		@Column(name="emailid")
		private String emailid7;
		@Column(name="contacttype")
		private int contacttype7;
		@Column(name="address")
		private String address7;
		@Column(name="countryid")
		private int countryid7;
		@Column(name="stateid")
		private int stateid7;
		@Column(name="districtid")
		private int districtid7;
		@Column(name="city")
		private String city7;
		@Column(name="pincode")
		private String pincode7;
		@Column(name="createdby")
		private int createdby7;
		@Column(name="createdon")
		private Date createdon7;
		private int modifiedby;
		private Date modifiedon;
	    private Boolean active;
	    private String createdbyip;
	    private String modifiedbyip;
	    private String record_language;
	   
		private String serialno;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getApplicationid() {
			return applicationid;
		}

		public void setApplicationid(int applicationid) {
			this.applicationid = applicationid;
		}

		public String getName7() {
			return name7;
		}

		public void setName7(String name7) {
			this.name7 = name7;
		}

		public String getDesignation7() {
			return designation7;
		}

		public void setDesignation7(String designation7) {
			this.designation7 = designation7;
		}

		public int getNationality7() {
			return nationality7;
		}

		public void setNationality7(int nationality7) {
			this.nationality7 = nationality7;
		}

		public String getTelephone_stdcode7() {
			return telephone_stdcode7;
		}

		public void setTelephone_stdcode7(String telephone_stdcode7) {
			this.telephone_stdcode7 = telephone_stdcode7;
		}

		public String getTelephone_number7() {
			return telephone_number7;
		}

		public void setTelephone_number7(String telephone_number7) {
			this.telephone_number7 = telephone_number7;
		}

		public String getMobile_countrycode7() {
			return mobile_countrycode7;
		}

		public void setMobile_countrycode7(String mobile_countrycode7) {
			this.mobile_countrycode7 = mobile_countrycode7;
		}

		public String getMobile_number7() {
			return mobile_number7;
		}

		public void setMobile_number7(String mobile_number7) {
			this.mobile_number7 = mobile_number7;
		}

		public String getFax_stdcode7() {
			return fax_stdcode7;
		}

		public void setFax_stdcode7(String fax_stdcode7) {
			this.fax_stdcode7 = fax_stdcode7;
		}

		public String getFax_number7() {
			return fax_number7;
		}

		public void setFax_number7(String fax_number7) {
			this.fax_number7 = fax_number7;
		}

		public String getEmailid7() {
			return emailid7;
		}

		public void setEmailid7(String emailid7) {
			this.emailid7 = emailid7;
		}

		public int getContacttype7() {
			return contacttype7;
		}

		public void setContacttype7(int contacttype7) {
			this.contacttype7 = contacttype7;
		}

		public String getAddress7() {
			return address7;
		}

		public void setAddress7(String address7) {
			this.address7 = address7;
		}

		public int getCountryid7() {
			return countryid7;
		}

		public void setCountryid7(int countryid7) {
			this.countryid7 = countryid7;
		}

		public int getStateid7() {
			return stateid7;
		}

		public void setStateid7(int stateid7) {
			this.stateid7 = stateid7;
		}

		public int getDistrictid7() {
			return districtid7;
		}

		public void setDistrictid7(int districtid7) {
			this.districtid7 = districtid7;
		}

		public String getCity7() {
			return city7;
		}

		public void setCity7(String city7) {
			this.city7 = city7;
		}

		public String getPincode7() {
			return pincode7;
		}

		public void setPincode7(String pincode7) {
			this.pincode7 = pincode7;
		}

		public int getCreatedby7() {
			return createdby7;
		}

		public void setCreatedby7(int createdby7) {
			this.createdby7 = createdby7;
		}

		public Date getCreatedon7() {
			return createdon7;
		}

		public void setCreatedon7(Date createdon7) {
			this.createdon7 = createdon7;
		}

		public int getModifiedby() {
			return modifiedby;
		}

		public void setModifiedby(int modifiedby) {
			this.modifiedby = modifiedby;
		}

		public Date getModifiedon() {
			return modifiedon;
		}

		public void setModifiedon(Date modifiedon) {
			this.modifiedon = modifiedon;
		}

		public Boolean getActive() {
			return active;
		}

		public void setActive(Boolean active) {
			this.active = active;
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

		public String getSerialno() {
			return serialno;
		}

		public void setSerialno(String serialno) {
			this.serialno = serialno;
		}
		
	
}
