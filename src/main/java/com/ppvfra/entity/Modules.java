package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="m_modules")
public class Modules {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
    @Override
	public String toString() {
		return "Modules [Id=" + Id + ", modulename=" + modulename + ", moduletypeid=" + moduletypeid + ", parentid="
				+ parentid + ", linkname=" + linkname + ", orderofappearance=" + orderofappearance + ", isparent="
				+ isparent + ", isdisplay=" + isdisplay + ", imagepath=" + imagepath + ", lock=" + lock + ", createdby="
				+ createdby + ", createdon=" + createdon + ", modifiedby=" + modifiedby + ", modifiedon=" + modifiedon
				+ ", active=" + active + ", createdbyip=" + createdbyip + ", modifiedbyip=" + modifiedbyip + "]";
	}
	private String modulename;
    private int moduletypeid;
    private int parentid;
    private String linkname;
    private String orderofappearance;
    private String isparent;
    private String isdisplay;
    private String imagepath;
    private Boolean lock;
    private Integer createdby;
    private Timestamp createdon;
    private Integer modifiedby;
    private Timestamp modifiedon;
    private Boolean active;
    private String createdbyip;
    private String modifiedbyip;
    private String module_icon;
    //private String accessdesc;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public int getModuletypeid() {
		return moduletypeid;
	}
	public void setModuletypeid(int moduletypeid) {
		this.moduletypeid = moduletypeid;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}
	public void setModifiedby(Integer modifiedby) {
		this.modifiedby = modifiedby;
	}
	public String getLinkname() {
		return linkname;
	}
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}
	public String getOrderofappearance() {
		return orderofappearance;
	}
	public void setOrderofappearance(String orderofappearance) {
		this.orderofappearance = orderofappearance;
	}
	public String getIsparent() {
		return isparent;
	}
	public void setIsparent(String isparent) {
		this.isparent = isparent;
	}
	public String getIsdisplay() {
		return isdisplay;
	}
	public void setIsdisplay(String isdisplay) {
		this.isdisplay = isdisplay;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public Boolean getLock() {
		return lock;
	}
	public void setLock(Boolean lock) {
		this.lock = lock;
	}
	public Integer getCreatedby() {
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
	public Integer getModifiedby() {
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
	public String getModule_icon() {
		return module_icon;
	}
	public void setModule_icon(String module_icon) {
		this.module_icon = module_icon;
	}
	/*
	 * public String getAccessdesc() { return accessdesc; } public void
	 * setAccessdesc(String accessdesc) { this.accessdesc = accessdesc; }
	 */
}
