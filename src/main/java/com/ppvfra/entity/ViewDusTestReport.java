package com.ppvfra.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name="viewdustestreport_v1")
public class ViewDusTestReport {
	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String crop_group;
	private String crop_common_name;
	private String denomination;
	private String status;
	private String remarks;
	private String createdon;
	private String dus_test_completed;
	private String date_of_completion;
	private Integer cropgroupid;
	private Integer cropid;
	private Integer application_current_status;
	
	public Integer getApplication_current_status() {
		return application_current_status;
	}
	public void setApplication_current_status(Integer application_current_status) {
		this.application_current_status = application_current_status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCrop_group() {
		return crop_group;
	}
	public void setCrop_group(String crop_group) {
		this.crop_group = crop_group;
	}
	public String getCrop_common_name() {
		return crop_common_name;
	}
	public void setCrop_common_name(String crop_common_name) {
		this.crop_common_name = crop_common_name;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreatedon() {
		return createdon;
	}
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	public String getDus_test_completed() {
		return dus_test_completed;
	}
	public void setDus_test_completed(String dus_test_completed) {
		this.dus_test_completed = dus_test_completed;
	}
	public String getDate_of_completion() {
		return date_of_completion;
	}
	public void setDate_of_completion(String date_of_completion) {
		this.date_of_completion = date_of_completion;
	}
	public Integer getCropgroupid() {
		return cropgroupid;
	}
	public void setCropgroupid(Integer cropgroupid) {
		this.cropgroupid = cropgroupid;
	}
	public Integer getCropid() {
		return cropid;
	}
	public void setCropid(Integer cropid) {
		this.cropid = cropid;
	}

}
