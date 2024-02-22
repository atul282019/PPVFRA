package com.ppvfra.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_fee")
public class Fees {
	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int varietyid;
	private int subvarietyid;
	private String paymenttype;
	private String applicantcategory;
	private Float fees;
	private Float salesvalue_percent;
	private Float royalty_percent;
	private Long createdby;
    private Date createdon;
    private String createdbyip;
    private Long modifiedby;
    private Date modifiedon;
    private String modifiedbyip;
    private Boolean active;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVarietyid() {
		return varietyid;
	}
	public void setVarietyid(int varietyid) {
		this.varietyid = varietyid;
	}
	public int getSubvarietyid() {
		return subvarietyid;
	}
	public void setSubvarietyid(int subvarietyid) {
		this.subvarietyid = subvarietyid;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	public String getApplicantcategory() {
		return applicantcategory;
	}
	public void setApplicantcategory(String applicantcategory) {
		this.applicantcategory = applicantcategory;
	}
	public Float getFees() {
		return fees;
	}
	public void setFees(Float fees) {
		this.fees = fees;
	}
	public Float getSalesvalue_percent() {
		return salesvalue_percent;
	}
	public void setSalesvalue_percent(Float salesvalue_percent) {
		this.salesvalue_percent = salesvalue_percent;
	}
	public Float getRoyalty_percent() {
		return royalty_percent;
	}
	public void setRoyalty_percent(Float royalty_percent) {
		this.royalty_percent = royalty_percent;
	}
	public Long getCreatedby() {
		return createdby;
	}
	public void setCreatedby(Long createdby) {
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
	public Long getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(Long modifiedby) {
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
	
}
