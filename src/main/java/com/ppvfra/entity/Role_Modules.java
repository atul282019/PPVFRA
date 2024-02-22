package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role_modules")
public class Role_Modules {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
    private int roleid;
    private int moduleid;
    private int accessid;
    private int createdby;
    private Timestamp createdon;
    private int modifiedby;
    private Timestamp modifiedon;
    private Boolean active;
    private String createdbyip;
    private String modifiedbyip;
    private int parentmoduleid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public int getModuleid() {
		return moduleid;
	}
	public void setModuleid(int moduleid) {
		this.moduleid = moduleid;
	}
	public int getAccessid() {
		return accessid;
	}
	public void setAccessid(int accessid) {
		this.accessid = accessid;
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
	
	public int getParentmoduleid() {
		return parentmoduleid;
	}
	public void setParentmoduleid(int parentmoduleid) {
		this.parentmoduleid = parentmoduleid;
	}
	@Override
	public String toString() {
		return "Role_Modules [id=" + id + ", roleid=" + roleid + ", moduleid=" + moduleid + ", accessid=" + accessid
				+ ", createdby=" + createdby + ", createdon=" + createdon + ", modifiedby=" + modifiedby
				+ ", modifiedon=" + modifiedon + ", active=" + active + ", createdbyip=" + createdbyip
				+ ", modifiedbyip=" + modifiedbyip + "]";
	}
}
