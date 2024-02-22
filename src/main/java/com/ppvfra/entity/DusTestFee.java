package com.ppvfra.entity;

import java.sql.Timestamp;
import java.util.Date;
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
@Table(name="dustestfee")
public class DusTestFee {

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}

    
    private Integer cropid;
    private Long dustestfee ;
    private Long onsitedustestfee ;
    private Date dustestfee_validfrom ;
    private Date dustestfee_validupto ;
    
	
	public Integer getCropid() {
		return cropid;
	}

	public void setCropid(Integer cropid) {
		this.cropid = cropid;
	}

	public Long getDustestfee() {
		return dustestfee;
	}

	public void setDustestfee(Long dustestfee) {
		this.dustestfee = dustestfee;
	}

	public Long getOnsitedustestfee() {
		return onsitedustestfee;
	}

	public void setOnsitedustestfee(Long onsitedustestfee) {
		this.onsitedustestfee = onsitedustestfee;
	}

	public Date getDustestfee_validfrom() {
		return dustestfee_validfrom;
	}

	public void setDustestfee_validfrom(Date dustestfee_validfrom) {
		this.dustestfee_validfrom = dustestfee_validfrom;
	}

	public Date getDustestfee_validupto() {
		return dustestfee_validupto;
	}

	public void setDustestfee_validupto(Date dustestfee_validupto) {
		this.dustestfee_validupto = dustestfee_validupto;
	}


	private Integer createdby;
	private Timestamp createdon;
	private Long modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	

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