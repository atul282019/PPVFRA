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
public class ApplicationContacts3 {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
		private int applicationid;
		@Column(name="name")
		private String name3;
		@Column(name="designation")
		private String designation3;
		@Column(name="nationality")
		private int nationality3;
		@Column(name="telephone_stdcode")
		private String telephone_stdcode3;
		@Column(name="telephone_number")
		private String telephone_number3;
		@Column(name="mobile_countrycode")
		private String mobile_countrycode3;
		@Column(name="mobile_number")
		private String mobile_number3;
		@Column(name="fax_stdcode")
		private String fax_stdcode3;
		@Column(name="fax_number")
		private String fax_number3;
		@Column(name="emailid")
		private String emailid3;
		@Column(name="contacttype")
		private int contacttype3;
		@Column(name="address")
		private String address3;
		@Column(name="countryid")
		private int countryid3;
		@Column(name="stateid")
		private int stateid3;
		@Column(name="districtid")
		private int districtid3;
		@Column(name="city")
		private String city3;
		@Column(name="pincode")
		private String pincode3;
		private int createdby;
		private Date createdon;
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

		public String getName3() {
			return name3;
		}

		public void setName3(String name3) {
			this.name3 = name3;
		}

		public String getDesignation3() {
			return designation3;
		}

		public void setDesignation3(String designation3) {
			this.designation3 = designation3;
		}

		public int getNationality3() {
			return nationality3;
		}

		public void setNationality3(int nationality3) {
			this.nationality3 = nationality3;
		}

		public String getTelephone_stdcode3() {
			return telephone_stdcode3;
		}

		public void setTelephone_stdcode3(String telephone_stdcode3) {
			this.telephone_stdcode3 = telephone_stdcode3;
		}

		public String getTelephone_number3() {
			return telephone_number3;
		}

		public void setTelephone_number3(String telephone_number3) {
			this.telephone_number3 = telephone_number3;
		}

		public String getMobile_countrycode3() {
			return mobile_countrycode3;
		}

		public void setMobile_countrycode3(String mobile_countrycode3) {
			this.mobile_countrycode3 = mobile_countrycode3;
		}

		public String getMobile_number3() {
			return mobile_number3;
		}

		public void setMobile_number3(String mobile_number3) {
			this.mobile_number3 = mobile_number3;
		}

		public String getFax_stdcode3() {
			return fax_stdcode3;
		}

		public void setFax_stdcode3(String fax_stdcode3) {
			this.fax_stdcode3 = fax_stdcode3;
		}

		public String getFax_number3() {
			return fax_number3;
		}

		public void setFax_number3(String fax_number3) {
			this.fax_number3 = fax_number3;
		}

		public String getEmailid3() {
			return emailid3;
		}

		public void setEmailid3(String emailid3) {
			this.emailid3 = emailid3;
		}

		public int getContacttype3() {
			return contacttype3;
		}

		public void setContacttype3(int contacttype3) {
			this.contacttype3 = contacttype3;
		}

		public String getAddress3() {
			return address3;
		}

		public void setAddress3(String address3) {
			this.address3 = address3;
		}

		public int getCountryid3() {
			return countryid3;
		}

		public void setCountryid3(int countryid3) {
			this.countryid3 = countryid3;
		}

		public int getStateid3() {
			return stateid3;
		}

		public void setStateid3(int stateid3) {
			this.stateid3 = stateid3;
		}

		public int getDistrictid3() {
			return districtid3;
		}

		public void setDistrictid3(int districtid3) {
			this.districtid3 = districtid3;
		}

		public String getCity3() {
			return city3;
		}

		public void setCity3(String city3) {
			this.city3 = city3;
		}

		public String getPincode3() {
			return pincode3;
		}

		public void setPincode3(String pincode3) {
			this.pincode3 = pincode3;
		}

		public int getCreatedby() {
			return createdby;
		}

		public void setCreatedby(int createdby) {
			this.createdby = createdby;
		}

		public Date getCreatedon() {
			return createdon;
		}

		public void setCreatedon(Date createdon) {
			this.createdon = createdon;
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
