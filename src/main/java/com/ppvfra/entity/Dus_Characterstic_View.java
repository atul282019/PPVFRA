package com.ppvfra.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dus_characterstic_view")
public class Dus_Characterstic_View
{
	@Id
	private int id;
	private String characteristics;
	private String state;
	private String note;
	private String example_evaluation;
	private String stages_observation;
	private String type_assessment;
	private int cropid;
	private String is_groupcharacteristics;
	private String is_required;
	private String explanation;

	/*
	 * private String upload;
	 * 
	 * public String getUpload() { return upload; } public void setUpload(String
	 * upload) { this.upload = upload; }
	 */
	public String getIs_groupcharacteristics() {
		return is_groupcharacteristics;
	}
	public void setIs_groupcharacteristics(String is_groupcharacteristics) {
		this.is_groupcharacteristics = is_groupcharacteristics;
	}
	public String getIs_required() {
		return is_required;
	}
	public void setIs_required(String is_required) {
		this.is_required = is_required;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getExample_evaluation() {
		return example_evaluation;
	}
	public void setExample_evaluation(String example_evaluation) {
		this.example_evaluation = example_evaluation;
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
	public int getCropid() {
		return cropid;
	}
	public void setCropid(int cropid) {
		this.cropid = cropid;
	}
	
	
}