package com.ppvfra.entity;

 

 
import javax.persistence.Entity;
 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


 
@Entity
@Table(name="cerificate_no")
//@IdClass(Seq_embeded.class)
public class Certificatenoissued {


	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Id
	private String fyear;

	public String getFyear() {
		return fyear;
	}

	public void setFyear(String fyear) {
		this.fyear = fyear;
	}

	 
	
	 
	
}