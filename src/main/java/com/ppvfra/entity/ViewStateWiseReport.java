package com.ppvfra.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name="viewstatewisereport_v2")
public class ViewStateWiseReport {
	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String principalplace;
	private String statename_inenglish;
	private String districtname_inenglish;
	private String crop_group;
	private String crop_common_name;
	private String denomination;
	private String status;
	private String remarks;
	private String createdon;
	private Integer statusid;
	private Integer statecode;
	private Integer districtcode;
	private Integer cropgroupid;
	private Integer cropid;
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
	public String getPrincipalplace() {
		return principalplace;
	}
	public void setPrincipalplace(String principalplace) {
		this.principalplace = principalplace;
	}
	public String getStatename_inenglish() {
		return statename_inenglish;
	}
	public void setStatename_inenglish(String statename_inenglish) {
		this.statename_inenglish = statename_inenglish;
	}
	public String getDistrictname_inenglish() {
		return districtname_inenglish;
	}
	public void setDistrictname_inenglish(String districtname_inenglish) {
		this.districtname_inenglish = districtname_inenglish;
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
	
	public Integer getStatusid() {
		return statusid;
	}
	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}
	public Integer getStatecode() {
		return statecode;
	}
	public void setStatecode(Integer statecode) {
		this.statecode = statecode;
	}
	public Integer getDistrictcode() {
		return districtcode;
	}
	public void setDistrictcode(Integer districtcode) {
		this.districtcode = districtcode;
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
