package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_journal")
public class Journal {
	
	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private int journal_year;
	private String journal_month;
	private String journal_issue;
	private String journal_volume_no;
	private int createdby;
	private Timestamp createdon;
	private int modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getJournal_year() {
		return journal_year;
	}
	public void setJournal_year(int journal_year) {
		this.journal_year = journal_year;
	}
	public String getJournal_month() {
		return journal_month;
	}
	public void setJournal_month(String journal_month) {
		this.journal_month = journal_month;
	}
	public String getJournal_issue() {
		return journal_issue;
	}
	public void setJournal_issue(String journal_issue) {
		this.journal_issue = journal_issue;
	}
	public String getJournal_volume_no() {
		return journal_volume_no;
	}
	public void setJournal_volume_no(String journal_volume_no) {
		this.journal_volume_no = journal_volume_no;
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
}
