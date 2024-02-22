package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_applicationstatus")
public class ApplicationStatus
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    private int stageid;
    private String status;
    private Boolean active;
    private String status_hin;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getStatus_hin() {
		return status_hin;
	}
	public void setStatus_hin(String status_hin) {
		this.status_hin = status_hin;
	}
	public int getStageid() {
		return stageid;
	}
	public void setStageid(int stageid) {
		this.stageid = stageid;
	}
    
}