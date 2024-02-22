package com.ppvfra.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;
 
@Entity
@Table(name="m_crops")
public class Crops {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	@NotNull	
	@Min(value=1,message="Please select a crop group")
	private Integer cropgroupid;
    @NotNull
    @Size(min=1,message="Field Cannot be blank")
	private String crop_common_name;
	
	private Integer createdby;
	private Timestamp createdon;
	private Long modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	private Boolean active;
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
	
	public Integer getCropgroupid() {
		return cropgroupid;
	}

	public void setCropgroupid(Integer cropgroupid) {
		this.cropgroupid = cropgroupid;
	}

	public String getCrop_common_name() {
		return crop_common_name;
	}

	public void setCrop_common_name(String crop_common_name) {
		this.crop_common_name = crop_common_name;
	}

	public Integer getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Integer createdby) {
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

	@OneToMany(fetch = FetchType.LAZY,targetEntity=CropSpecies.class ,cascade= {CascadeType.ALL})
	@JoinColumn(name = "cropid",referencedColumnName="id")
	private Set<CropSpecies> cropspecies;

	public Set<CropSpecies> getCropspecies() {
		return cropspecies;
	}

	public void setCropspecies(Set<CropSpecies> cropspecies) {
		this.cropspecies = cropspecies;
	}
	
	
	
}