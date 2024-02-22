package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name="m_dustqgroups")
public class DusTqGroup {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String dustqserialnumber;
    private String dustqgroup;
    private int createdby;
    private Timestamp createdon;
    private int modifiedby;
    private Timestamp modifiedon;
    private Boolean active;
    private String createdbyip;
    private String modifiedbyip;
    private String dustqgroup_hin;
}
