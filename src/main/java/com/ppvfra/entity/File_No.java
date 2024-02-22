package com.ppvfra.entity;

 

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="file_no")
@IdClass(File_Composite.class)
public class File_No 
{
	@Id
	private String botanical_initial;
	@Id
	private String variety_type;
	@Id
	private Integer f_year;
	
	private Integer s_id;

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	@Override
	public String toString() {
		return "File_No [botanical_initial=" + botanical_initial + ", variety_type=" + variety_type + ", f_year="
				+ f_year + ", s_id=" + s_id + "]";
	}

	public File_No(String botanical_initial, String variety_type, Integer f_year, Integer s_id) {
		super();
		this.botanical_initial = botanical_initial;
		this.variety_type = variety_type;
		this.f_year = f_year;
		this.s_id = s_id;
	}

	public File_No() {
		super();
		// TODO Auto-generated constructor stub
	}


	 

	 

	
}