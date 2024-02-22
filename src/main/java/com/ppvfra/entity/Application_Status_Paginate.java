package com.ppvfra.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name="application_status_paginate")
public class Application_Status_Paginate {
	@Id
	private Integer application_id;
	
	private Integer applicantid;
	private String application_date;
	private String name_val;
	private String c_denomination;
	private String crop_common_name;
	private String crop_botanical_name;
	private String status;
	private Integer cropid;
	private String varietytype;
	private String subvarietytype;
	private String formtype;
	private String crop_group;
	
	
	
	public Integer getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(Integer applicantid) {
		this.applicantid = applicantid;
	}
	public String getApplication_date() {
		return application_date;
	}
	public void setApplication_date(String application_date) {
		this.application_date = application_date;
	}
	public String getName_val() {
		return name_val;
	}
	public void setName_val(String name_val) {
		this.name_val = name_val;
	}
	public String getC_denomination() {
		return c_denomination;
	}
	public void setC_denomination(String c_denomination) {
		this.c_denomination = c_denomination;
	}
	public String getCrop_common_name() {
		return crop_common_name;
	}
	public void setCrop_common_name(String crop_common_name) {
		this.crop_common_name = crop_common_name;
	}
	public String getCrop_botanical_name() {
		return crop_botanical_name;
	}
	public void setCrop_botanical_name(String crop_botanical_name) {
		this.crop_botanical_name = crop_botanical_name;
	}
	public Integer getApplication_id() {
		return application_id;
	}
	public void setApplication_id(Integer application_id) {
		this.application_id = application_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCropid() {
		return cropid;
	}
	public void setCropid(Integer cropid) {
		this.cropid = cropid;
	}
	public String getVarietytype() {
		return varietytype;
	}
	public void setVarietytype(String varietytype) {
		this.varietytype = varietytype;
	}
	public String getSubvarietytype() {
		return subvarietytype;
	}
	public void setSubvarietytype(String subvarietytype) {
		this.subvarietytype = subvarietytype;
	}
	public String getFormtype() {
		return formtype;
	}
	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}
	public String getCrop_group() {
		return crop_group;
	}
	public void setCrop_group(String crop_group) {
		this.crop_group = crop_group;
	}
	
	
	
		
}
