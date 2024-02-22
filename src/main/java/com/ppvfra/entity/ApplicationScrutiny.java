package com.ppvfra.entity;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_scrutiny")
public class ApplicationScrutiny {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int applicationid;
	
	private Integer category ;
	private String  observation;
    private Date dateof_submission ;
    private String complaince ;
    private String documentname; 
    private String documentpath; 
    private String remarks_doc_scrutiny ;
    private String remarks_deficiency_rep;
    private Integer createdby;
	private Timestamp createdon;
	private Long modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	private Boolean active;
	private Date finalsubmit;
	
	public Date getFinalsubmit() {
		return finalsubmit;
	}
	public void setFinalsubmit(Date finalsubmit) {
		this.finalsubmit = finalsubmit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(int applicationid) {
		this.applicationid = applicationid;
	}
	
	public String getObservation() {
		return observation;
	}
	
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	
	public Date getDateof_submission() {
		return dateof_submission;
	}
	public void setDateof_submission(Date dateof_submission) {
		this.dateof_submission = dateof_submission;
	}
	public String getComplaince() {
		return complaince;
	}
	public void setComplaince(String complaince) {
		this.complaince = complaince;
	}
	public String getDocumentname() {
		return documentname;
	}
	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}
	public String getDocumentpath() {
		return documentpath;
	}
	public void setDocumentpath(String documentpath) {
		this.documentpath = documentpath;
	}
	public String getRemarks_doc_scrutiny() {
		return remarks_doc_scrutiny;
	}
	public void setRemarks_doc_scrutiny(String remarks_doc_scrutiny) {
		this.remarks_doc_scrutiny = remarks_doc_scrutiny;
	}
	public String getRemarks_deficiency_rep() {
		return remarks_deficiency_rep;
	}
	public void setRemarks_deficiency_rep(String remarks_deficiency_rep) {
		this.remarks_deficiency_rep = remarks_deficiency_rep;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	
	
	
	
}
