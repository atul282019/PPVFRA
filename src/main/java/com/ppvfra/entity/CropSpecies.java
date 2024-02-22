package com.ppvfra.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="m_cropspecies")
public class CropSpecies {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	} 
	
	/*
	 * @NotNull
	 * 
	 * @Size(min=1,message="Field cannot be blank")
	 */
	
	private String crop_botanical_name;
	
	/*
	 * @NotNull
	 * 
	 * @Size(min=1,message="Field cannot be blank")
	 */
	@Column(name="family")
    private String Family;
	 private Integer cropid; 
	public Integer getCropid() {
		return cropid;
	}
	public void setCropid(Integer cropid) {
		this.cropid = cropid;
	}

	private Long createdby;
	private Timestamp createdon;
	private Long modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	private Boolean active;
	
	private Integer cscount;
	
	
	public Integer getCscount() {
		return cscount;
	}
	public void setCscount(Integer cscount) {
		this.cscount = cscount;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getFamily() {
		return this.Family;
	}
	
	
	public String getCrop_botanical_name() {
		return crop_botanical_name;
	}
	public void setCrop_botanical_name(String crop_botanical_name) {
		this.crop_botanical_name = crop_botanical_name;
	}
	public void setFamily(String family) {
		this.Family = family;
	}
	public Long getCreatedby() {
		return this.createdby;
	}
	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}
	public Timestamp getCreatedon() {
		return createdon;
	} 
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}
	public Long getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(Long modifiedby) {
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
	
	private String initials;


	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	
}