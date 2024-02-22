package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "m_states")
public class States {
	  @Id // one id column is mandatory
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int id;
	  private int statecode;
	  private int stateversion;
	  private String statename_inenglish;
	  private String statename_inlocal;
	  private int census2001code;
	  private int census2011code;
	  private String stateorut;
	  private int createdby;
	  
	  private int active;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatecode() {
		return statecode;
	}
	public void setStatecode(int statecode) {
		this.statecode = statecode;
	}
	public int getStateversion() {
		return stateversion;
	}
	public void setStateversion(int stateversion) {
		this.stateversion = stateversion;
	}
	public String getStatename_inenglish() {
		return statename_inenglish;
	}
	public void setStatename_inenglish(String statename_inenglish) {
		this.statename_inenglish = statename_inenglish;
	}
	public String getStatename_inlocal() {
		return statename_inlocal;
	}
	public void setStatename_inlocal(String statename_inlocal) {
		this.statename_inlocal = statename_inlocal;
	}
	public int getCensus2001code() {
		return census2001code;
	}
	public void setCensus2001code(int census2001code) {
		this.census2001code = census2001code;
	}
	public int getCensus2011code() {
		return census2011code;
	}
	public void setCensus2011code(int census2011code) {
		this.census2011code = census2011code;
	}
	public String getStateorut() {
		return stateorut;
	}
	public void setStateorut(String stateorut) {
		this.stateorut = stateorut;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
}