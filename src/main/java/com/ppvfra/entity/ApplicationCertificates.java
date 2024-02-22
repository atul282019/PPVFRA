package com.ppvfra.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_certificates")
public class ApplicationCertificates {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Long applicationid;
	private String certificate_issued;
	 
	private String denomination_registered;
	private String registration_number;
	private String certificate_serial_no;
	private Date cert_issue_date;
	private Date period_of_protection;
	private long crop_id;
	private String category;
	private String type;
	private long createdby;
	private Date createdon;
	private String createdbyip;
	private long modifiedby;
	private Date modifiedon;
	private String modifiedbyip;
	private Boolean active;
	private String record_language;
	private Date protection_expiry_date;
	
	public Date getProtection_expiry_date() {
		return protection_expiry_date;
	}
	public void setProtection_expiry_date(Date protection_expiry_date) {
		this.protection_expiry_date = protection_expiry_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
	 
	public Long getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(Long applicationid) {
		this.applicationid = applicationid;
	}
	public String getCertificate_issued() {
		return certificate_issued;
	}
	public void setCertificate_issued(String certificate_issued) {
		this.certificate_issued = certificate_issued;
	}
	public String getDenomination_registered() {
		return denomination_registered;
	}
	public void setDenomination_registered(String denomination_registered) {
		this.denomination_registered = denomination_registered;
	}
	public String getRegistration_number() {
		return registration_number;
	}
	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}
	public String getCertificate_serial_no() {
		return certificate_serial_no;
	}
	public void setCertificate_serial_no(String certificate_serial_no) {
		this.certificate_serial_no = certificate_serial_no;
	}
	public Date getCert_issue_date() {
		return cert_issue_date;
	}
	public void setCert_issue_date(Date cert_issue_date) {
		this.cert_issue_date = cert_issue_date;
	}
	public Date getPeriod_of_protection() {
		return period_of_protection;
	}
	public void setPeriod_of_protection(Date period_of_protection) {
		this.period_of_protection = period_of_protection;
	}
	public long getCrop_id() {
		return crop_id;
	}
	public void setCrop_id(long crop_id) {
		this.crop_id = crop_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getCreatedby() {
		return createdby;
	}
	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public String getCreatedbyip() {
		return createdbyip;
	}
	public void setCreatedbyip(String createdbyip) {
		this.createdbyip = createdbyip;
	}
	public long getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(long modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Date getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(Date modifiedon) {
		this.modifiedon = modifiedon;
	}
	public String getModifiedbyip() {
		return modifiedbyip;
	}
	public void setModifiedbyip(String modifiedbyip) {
		this.modifiedbyip = modifiedbyip;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getRecord_language() {
		return record_language;
	}
	public void setRecord_language(String record_language) {
		this.record_language = record_language;
	}

}
