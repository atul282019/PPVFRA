package com.ppvfra.entity;
import java.util.Date;
import java.sql.Timestamp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_convention_countries")
public class ApplicationConventionCountries {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private int applicationid;
		private String denomination;
		private String nature;
		private Date fillingdate;
		private int country;
		private String authority;
		private String application_number;
		private String applicationstatus;
		private int incountries;
		private Date ondateofapplication;
		private int createdby;
		private Timestamp createdon;
		private int modifiedby;
		private Timestamp modifiedon;
		private Boolean active;
		private String createdbyip;
		private String modifiedbyip;
		private String record_language;
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
		public String getDenomination() {
			return denomination;
		}
		public void setDenomination(String denomination) {
			this.denomination = denomination;
		}
		public String getNature() {
			return nature;
		}
		public void setNature(String nature) {
			this.nature = nature;
		}
		public Date getFillingdate() {
			return fillingdate;
		}
		public void setFillingdate(Date fillingdate) {
			this.fillingdate = fillingdate;
		}
		public int getCountry() {
			return country;
		}
		public void setCountry(int country) {
			this.country = country;
		}
		public String getAuthority() {
			return authority;
		}
		public void setAuthority(String authority) {
			this.authority = authority;
		}
		public String getApplication_number() {
			return application_number;
		}
		public void setApplication_number(String application_number) {
			this.application_number = application_number;
		}
		public String getApplicationstatus() {
			return applicationstatus;
		}
		public void setApplicationstatus(String applicationstatus) {
			this.applicationstatus = applicationstatus;
		}
		public int getIncountries() {
			return incountries;
		}
		public void setIncountries(int incountries) {
			this.incountries = incountries;
		}
		public Date getOndateofapplication() {
			return ondateofapplication;
		}
		public void setOndateofapplication(Date ondateofapplication) {
			this.ondateofapplication = ondateofapplication;
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


}
