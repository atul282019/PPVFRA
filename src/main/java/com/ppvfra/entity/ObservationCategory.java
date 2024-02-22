package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="m_observation_category")
public class ObservationCategory {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
    @NotNull
    @Size(min=1,message="Please enter the category")
    private String category;
    private Integer createdby;
	private Timestamp createdon;
	private Long modifiedby;
	private Timestamp modifiedon;
	private String createbyip;
	private String modifiedbyip;
	private Boolean active;
	private Integer formsection_id;
	
	public Integer getFormsection_id() {
		return formsection_id;
	}
	public void setFormsection_id(Integer formsection_id) {
		this.formsection_id = formsection_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getCreatebyip() {
		return createbyip;
	}
	public void setCreatebyip(String createbyip) {
		this.createbyip = createbyip;
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
	
	
	
  
}
