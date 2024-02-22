package com.ppvfra.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.springframework.beans.factory.annotation.Required;

@SuppressWarnings("deprecation")
@Entity
@Table(name="user_role")
public class User_Role implements Serializable {

	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	 
	private int user_id;
	
	@NotNull
	@Min(value=1,message="Please select the role")
	private int role_id;
	private Integer createdby;
	private Integer modifiedby;
	private Timestamp createdon;
	private Timestamp modifiedon;
	
	
	
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public Integer getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	//public int getModifiedby() { return modifiedby;	}

	//public void setModifiedby(int modifiedby) { this.modifiedby = modifiedby;}

	public Timestamp getCreatedon() {
		return createdon;
	}

	public Integer getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(Integer modifiedby) {
		this.modifiedby = modifiedby;
	}

	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}

	public Timestamp getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(Timestamp modifiedon) {
		this.modifiedon = modifiedon;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	
	 
}