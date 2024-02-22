package com.ppvfra.entity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_contacts")
public class ApplicationContacts {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
		private int applicationid;
		private String name;
		private String designation;
		private int nationality;
		private String telephone_stdcode;
		private String telephone_number;
		private String mobile_countrycode;
		private String mobile_number;
		private String fax_stdcode;
		private String fax_number;
		private String emailid;
		private int contacttype;
		private String address;
		private int countryid;
		private int stateid;
		private int districtid;
		private String city;
		private String pincode;
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		public int getNationality() {
			return nationality;
		}
		public void setNationality(int nationality) {
			this.nationality = nationality;
		}
		public String getTelephone_stdcode() {
			return telephone_stdcode;
		}
		public void setTelephone_stdcode(String telephone_stdcode) {
			this.telephone_stdcode = telephone_stdcode;
		}
		public String getTelephone_number() {
			return telephone_number;
		}
		public void setTelephone_number(String telephone_number) {
			this.telephone_number = telephone_number;
		}
		public String getMobile_countrycode() {
			return mobile_countrycode;
		}
		public void setMobile_countrycode(String mobile_countrycode) {
			this.mobile_countrycode = mobile_countrycode;
		}
		public String getMobile_number() {
			return mobile_number;
		}
		public void setMobile_number(String mobile_number) {
			this.mobile_number = mobile_number;
		}
		public String getFax_stdcode() {
			return fax_stdcode;
		}
		public void setFax_stdcode(String fax_stdcode) {
			this.fax_stdcode = fax_stdcode;
		}
		public String getFax_number() {
			return fax_number;
		}
		public void setFax_number(String fax_number) {
			this.fax_number = fax_number;
		}
		public String getEmailid() {
			return emailid;
		}
		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}
		public int getContacttype() {
			return contacttype;
		}
		public void setContacttype(int contacttype) {
			this.contacttype = contacttype;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public int getCountryid() {
			return countryid;
		}
		public void setCountryid(int countryid) {
			this.countryid = countryid;
		}
		public int getStateid() {
			return stateid;
		}
		public void setStateid(int stateid) {
			this.stateid = stateid;
		}
		public int getDistrictid() {
			return districtid;
		}
		public void setDistrictid(int districtid) {
			this.districtid = districtid;
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
