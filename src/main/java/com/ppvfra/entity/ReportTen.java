package com.ppvfra.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name="viewreport10_v1")
public class ReportTen {
	
	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
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
	private String name;
	private String crop_group;
	private String crop_common_name;
	private String denomination;
	private String status;
	private String remarks;
	private String createdon;
	private Integer cropgroupid;
	public String getCreatedon() {
		return createdon;
	}
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	
	public Integer getCropgroupid() {
		return cropgroupid;
	}
	public void setCropgroupid(Integer cropgroupid) {
		this.cropgroupid = cropgroupid;
	}
		
}
