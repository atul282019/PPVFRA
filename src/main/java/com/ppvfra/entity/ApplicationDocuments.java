package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_documents")
public class ApplicationDocuments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private int applicationid;
    private int documenttypeid;
    private String documentname;
    private String documentpath;
    private int createdby;
    private Timestamp createdon;
    private int modifiedby;
    private Timestamp modifiedon;
    private Boolean active;
    private String createdbyip;
    private String modifiedbyip;
    private String record_language;
    
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
  	public int getDocumenttypeid() {
  		return documenttypeid;
  	}
  	public void setDocumenttypeid(int documenttypeid) {
  		this.documenttypeid = documenttypeid;
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
  	public int getCreatedby() {
  		return createdby;
  	}
  	public void setCreatedby(int createdby) {
  		this.createdby = createdby;
  	}
  	public Timestamp getCreatedon() {
  		return createdon;
  	}
  	public void setCreatedon(Timestamp createdon) {
  		this.createdon = createdon;
  	}
  	public int getModifiedby() {
  		return modifiedby;
  	}
  	public void setModifiedby(int modifiedby) {
  		this.modifiedby = modifiedby;
  	}
  	public Timestamp getModifiedon() {
  		return modifiedon;
  	}
  	public void setModifiedon(Timestamp modifiedon) {
  		this.modifiedon = modifiedon;
  	}
  	public Boolean getActive() {
  		return active;
  	}
  	public void setActive(Boolean active) {
  		this.active = active;
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
  	public String getRecord_language() {
  		return record_language;
  	}
  	public void setRecord_language(String record_language) {
  		this.record_language = record_language;
  	}
    
	
}
