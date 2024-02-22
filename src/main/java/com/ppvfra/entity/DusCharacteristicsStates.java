package com.ppvfra.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Required;

import java.sql.Timestamp;
import java.util.Set;

@SuppressWarnings("deprecation")

@Entity
@Table(name="m_duscharacteristics_states")

public class DusCharacteristicsStates {
	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer duscharacteristicid;
	
	  
	public Integer getDuscharacteristicid() {
		return duscharacteristicid;
	}
	public void setDuscharacteristicid(Integer duscharacteristicid) {
		this.duscharacteristicid = duscharacteristicid;
	}
	@Column(name="states")
	private String states; 
	@Column(name="note")
	private Integer note;
	@Column(name="example_variety")
	private String example_variety;
	@Column(name="documentname")
	private String documentname;
    @Column(name="documentpath")
	private String documentpath;
	
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public Integer getNote() {
		return note;
	}
	public void setNote(Integer note) {
		this.note = note;
	}
	public String getExample_variety() {
		return example_variety;
	}
	public void setExample_variety(String example_variety) {
		this.example_variety = example_variety;
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
	
	 
	
	
	
	
}
