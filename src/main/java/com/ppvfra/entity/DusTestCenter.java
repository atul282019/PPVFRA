package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Required;

 
@SuppressWarnings("deprecation")

@Entity
@Table(name="m_dustestcenter")

public class DusTestCenter {

	private Long Id;
	 
	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
    @NotNull
    @Min(value=1,message="Please select a crop group")
	private Long cropgroup_id;
	
    @NotNull
    @Min(value=1,message="Please select a crop")
	private Long crop_id;

	@NotNull	
	@Min(value=1,message="Please select a state")
	private Long state_id ;
	
	@NotNull	
	@Min(value=1,message="Please select a district")
	private Long district_id;
	
	@NotNull
	@Size(min=1,message="Field cannot be blank")
	private String dus_test_center;
	
	private Boolean isactive;
	private Integer createdby;
	private Timestamp createdon;
	private Integer modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	private String centertype;
	
	

	public Timestamp getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}

	public Integer getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(Integer modifiedby) {
		this.modifiedby = modifiedby;
	}

	public Timestamp getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(Timestamp modifiedon) {
		this.modifiedon = modifiedon;
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

	public Integer getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	
	
	public Long getCropgroup_id() {
		return cropgroup_id;
	}

	public void setCropgroup_id(Long cropgroup_id) {
		this.cropgroup_id = cropgroup_id;
	}

	public Long getCrop_id() {
		return crop_id;
	}

	public void setCrop_id(Long crop_id) {
		this.crop_id = crop_id;
	}

	public Long getState_id() {
		return state_id;
	}

	public void setState_id(Long state_id) {
		this.state_id = state_id;
	}

	public Long getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Long district_id) {
		this.district_id = district_id;
	}

	public String getDus_test_center() {
		return dus_test_center;
	}

	public void setDus_test_center(String dus_test_center) {
		this.dus_test_center = dus_test_center;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	/*
	 * @Transient private String crop_group;
	 * 
	 * @Transient private String crop_common_name;
	 * 
	 * @Transient private String statename_inenglish;
	 * 
	 * @Transient private String districtname_inenglish;
	 * 
	 * public String getCrop_group() { return crop_group; }
	 * 
	 * public void setCrop_group(String crop_group) { this.crop_group = crop_group;
	 * }
	 * 
	 * public String getCrop_common_name() { return crop_common_name; }
	 * 
	 * public void setCrop_common_name(String crop_common_name) {
	 * this.crop_common_name = crop_common_name; }
	 * 
	 * public String getStatename_inenglish() { return statename_inenglish; }
	 * 
	 * public void setStatename_inenglish(String statename_inenglish) {
	 * this.statename_inenglish = statename_inenglish; }
	 * 
	 * public String getDistrictname_inenglish() { return districtname_inenglish; }
	 * 
	 * public void setDistrictname_inenglish(String districtname_inenglish) {
	 * this.districtname_inenglish = districtname_inenglish; }
	 */

	public String getCentertype() {
		return centertype;
	}

	public void setCentertype(String centertype) {
		this.centertype = centertype;
	}
 
	
}
