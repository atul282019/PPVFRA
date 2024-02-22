package com.ppvfra.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="assignment_details")
public class Assignment_Details {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	 
	 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int applicationid ;
    private int form_type_id ;
    private Integer assigned_by_office_id ;
    private int assigned_by_user_id ;
    
    private String assigned_bydesignation ;
    private Integer received_by_office_code ;
    private int received_by_user_id ;
    @Transient
    private int received_by_user_role ;
    private String  received_by_designation ;
    private Date received_by_date;
    private String remarks ;
    private Integer applicationstatus_id ;
    private String isapproved ;
    private Date approvaldate ;
    private String action_type ;
    private int createdby ;
    private Date createdon ;
    private String createdbyip ;
    private int modifiedby ;
    private Date modifiedon ;
    private String modifiedbyip ;
    private String record_language ;
    
    
    
    public int getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(int applicationid) {
		this.applicationid = applicationid;
	}
	public int getForm_type_id() {
		return form_type_id;
	}
	public void setForm_type_id(int form_type_id) {
		this.form_type_id = form_type_id;
	}
	public Integer getAssigned_by_office_id() {
		return assigned_by_office_id;
	}
	public void setAssigned_by_office_id(Integer assigned_by_office_id) {
		this.assigned_by_office_id = assigned_by_office_id;
	}
	public int getAssigned_by_user_id() {
		return assigned_by_user_id;
	}
	public void setAssigned_by_user_id(int assigned_by_user_id) {
		this.assigned_by_user_id = assigned_by_user_id;
	}
	 
	public String getAssigned_bydesignation() {
		return assigned_bydesignation;
	}
	public void setAssigned_bydesignation(String assigned_bydesignation) {
		this.assigned_bydesignation = assigned_bydesignation;
	}
	public Integer getReceived_by_office_code() {
		return received_by_office_code;
	}
	public void setReceived_by_office_code(Integer received_by_office_code) {
		this.received_by_office_code = received_by_office_code;
	}
	public int getReceived_by_user_id() {
		return received_by_user_id;
	}
	public void setReceived_by_user_id(int received_by_user_id) {
		this.received_by_user_id = received_by_user_id;
	}
	public int getReceived_by_user_role() {
		return received_by_user_role;
	}
	public void setReceived_by_user_role(int received_by_user_role) {
		this.received_by_user_role = received_by_user_role;
	}
	public String getReceived_by_designation() {
		return received_by_designation;
	}
	public void setReceived_by_designation(String received_by_designation) {
		this.received_by_designation = received_by_designation;
	}
	public Date getReceived_by_date() {
		return received_by_date;
	}
	public void setReceived_by_date(Date received_by_date) {
		this.received_by_date = received_by_date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	 
	public Integer getApplicationstatus_id() {
		return applicationstatus_id;
	}
	public void setApplicationstatus_id(Integer applicationstatus_id) {
		this.applicationstatus_id = applicationstatus_id;
	}
	public String getIsapproved() {
		return isapproved;
	}
	public void setIsapproved(String isapproved) {
		this.isapproved = isapproved;
	}
	public Date getApprovaldate() {
		return approvaldate;
	}
	public void setApprovaldate(Date approvaldate) {
		this.approvaldate = approvaldate;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
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
	public int getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(int modifiedby) {
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
	public String getRecord_language() {
		return record_language;
	}
	public void setRecord_language(String record_language) {
		this.record_language = record_language;
	}

	
    
	 
}
