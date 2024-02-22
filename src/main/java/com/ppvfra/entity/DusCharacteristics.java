package com.ppvfra.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Required;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("deprecation")

@Entity
@Table(name="m_duscharacteristics")
public class DusCharacteristics {
	 
	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
        

	@NotNull
    @Min(value=1,message="Please select a crop")
	private Integer cropid;
	@NotNull
    @Min(value=1,message="Please select a characteristic group")
	private String characteristics_group;
	
	private Integer serial_number;
	
	
	
	
	public Integer getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(Integer serial_number) {
		this.serial_number = serial_number;
	}
	public String getCharacteristics_group() {
		return characteristics_group;
	}
	public void setCharacteristics_group(String characteristics_group) {
		this.characteristics_group = characteristics_group;
	}
	
	public Integer getCropid() {
		return cropid;
	}
	public void setCropid(Integer cropid) {
		this.cropid = cropid;
	}
	@NotNull
    @Size(min=1,message="Field cannot be blank")
	@Column(name="characteristics")
    private String characteristics;
	
	public String getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}
	@NotNull
    @Size(min=1,message="Field cannot be blank")
	@Column(name="stages_observation")
    private String stages_observation;
	
    @Column(name="type_assessment")
    private String type_assessment;
   // @NotNull
   // @Size(min=1,message="Field cannot be blank")
    @Column(name="is_groupcharacteristics")
    private String IsGroupCharacteristics;
    //@NotNull
    //@Size(min=1,message="Please select a option")
    @Column(name="is_required")
    private String IsRequired;
    //@NotNull
    //@Size(min=1,message="Field cannot be blank")
    @Column(name="explanation")
    private String Explanation;
    private Boolean active;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getStages_observation() {
		return stages_observation;
	}
	public void setStages_observation(String stages_observation) {
		this.stages_observation = stages_observation;
	}
	public String getType_assessment() {
		return type_assessment;
	}
	public void setType_assessment(String type_assessment) {
		this.type_assessment = type_assessment;
	}
	public String getIsGroupCharacteristics() {
		return IsGroupCharacteristics;
	}
	public void setIsGroupCharacteristics(String isGroupCharacteristics) {
		IsGroupCharacteristics = isGroupCharacteristics;
	}
	public String getIsRequired() {
		return IsRequired;
	}
	public void setIsRequired(String isRequired) {
		IsRequired = isRequired;
	}
	public String getExplanation() {
		return Explanation;
	}
	public void setExplanation(String explanation) {
		Explanation = explanation;
	}
	
	
	
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@OneToMany(fetch = FetchType.LAZY,targetEntity=DusCharacteristicsStates.class ,cascade= {CascadeType.ALL})
	@JoinColumn(name = "duscharacteristicid",referencedColumnName="id")
	private Set<DusCharacteristicsStates> duscharacteristicsstates;
	public Set<DusCharacteristicsStates> getDuscharacteristicsstates() {
		return duscharacteristicsstates;
	}
	public void setDuscharacteristicsstates(Set<DusCharacteristicsStates> duscharacteristicsstates) {
		this.duscharacteristicsstates = duscharacteristicsstates;
	}
	
	
		
	

	
}
