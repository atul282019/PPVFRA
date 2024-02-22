package com.ppvfra.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "activitylog")
public class ActivityLogTable 
{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String ipaddress ;
	private String activityby;
	private Date login_time_stamp ;
	private String activity ;
	private String url;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getActivityby() {
		return activityby;
	}
	public void setActivityby(String activityby) {
		this.activityby = activityby;
	}
	 
	public Date getLogin_time_stamp() {
		return login_time_stamp;
	}
	public void setLogin_time_stamp(Date login_time_stamp) {
		this.login_time_stamp = login_time_stamp;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	 
	
	
}
