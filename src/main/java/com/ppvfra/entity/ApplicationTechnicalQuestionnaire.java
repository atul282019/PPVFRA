package com.ppvfra.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_technical_questionnaire")
public class ApplicationTechnicalQuestionnaire {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int applicationid;
    private String name;
    private String year_of_establishment;
    private String registered_address;
    private String location_office;
    private String telephone;
    private String fax;
    private String telephone_std;
	private String fax_std;
    private String email;
    private String candidatevariety;
    private Boolean releasedearlier;
    private String pedigree;
    private String pedigree_imagename;
    private String pedigree_imagepath;
    private int origination;
    private String parental_material;
    private String technique;
    private String selectioncriteria;
    private String selectionstage;
    private String breeding_location;
    private String comparative_trial_filename;
    private String comparative_trial_filepath;
    private String comparative_location;
    private String comparative_place;
    private String comparative_period;
    private String comparative_year;
    private String comparative_month;
    private String comparative_cultivation;
    private String comparative_scale;
    private String comparative_reference;
    private String comparative_criteria;
    private String comparative_design;
    private String comparative_analysis;
    private String comparative_other;
    private String characteristics_group;
    private String characteristics_distinguishing;
    private String characteristics_denomination;
    private String characteristics_variety_denomination;
    private String characteristics_variety_comparison;
    private String characteristics_variety_distinguishable;
    private String characteristics_variety_referencedenomination;
    private String characteristics_variety_choice;
    private String characteristics_basic_distinguishable;
    private String statement_distinctiveness;
    private String statement_uniformity;
    private String statement_stability;
    private String methods_candidate;
    private String grouping_characters;
    private String stability_parameter;
    private String variation_important_trait_filename;
    public String getPedigree_imagename() {
		return pedigree_imagename;
	}
	public void setPedigree_imagename(String pedigree_imagename) {
		this.pedigree_imagename = pedigree_imagename;
	}
	public String getPedigree_imagepath() {
		return pedigree_imagepath;
	}
	public void setPedigree_imagepath(String pedigree_imagepath) {
		this.pedigree_imagepath = pedigree_imagepath;
	}
	public String getVariation_important_trait_filename() {
		return variation_important_trait_filename;
	}
	public void setVariation_important_trait_filename(String variation_important_trait_filename) {
		this.variation_important_trait_filename = variation_important_trait_filename;
	}
	public String getVariation_important_trait_filepath() {
		return variation_important_trait_filepath;
	}
	public void setVariation_important_trait_filepath(String variation_important_trait_filepath) {
		this.variation_important_trait_filepath = variation_important_trait_filepath;
	}
	private String variation_important_trait_filepath;
    private boolean variety_withdrawn;
    private String supplement_information_name;
    private String supplement_information_path;
    private boolean active;
    private int createdby;
    private Date createdon;
    private int modifiedby;
    private Date modifiedon;
    private String createdbyip;
    private String modifiedbyip;
    private String record_language;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(int applicationid) {
		this.applicationid = applicationid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear_of_establishment() {
		return year_of_establishment;
	}
	public void setYear_of_establishment(String year_of_establishment) {
		this.year_of_establishment = year_of_establishment;
	}
	public String getRegistered_address() {
		return registered_address;
	}
	public void setRegistered_address(String registered_address) {
		this.registered_address = registered_address;
	}
	public String getLocation_office() {
		return location_office;
	}
	public void setLocation_office(String location_office) {
		this.location_office = location_office;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	 public String getTelephone_std() {
			return telephone_std;
		}
		public void setTelephone_std(String telephone_std) {
			this.telephone_std = telephone_std;
		}
		public String getFax_std() {
			return fax_std;
		}
		public void setFax_std(String fax_std) {
			this.fax_std = fax_std;
		}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCandidatevariety() {
		return candidatevariety;
	}
	public void setCandidatevariety(String candidatevariety) {
		this.candidatevariety = candidatevariety;
	}
	public Boolean getReleasedearlier() {
		return releasedearlier;
	}
	public void setReleasedearlier(Boolean releasedearlier) {
		this.releasedearlier = releasedearlier;
	}
	public String getPedigree() {
		return pedigree;
	}
	public void setPedigree(String pedigree) {
		this.pedigree = pedigree;
	}
	public int getOrigination() {
		return origination;
	}
	public void setOrigination(int origination) {
		this.origination = origination;
	}
	public String getParental_material() {
		return parental_material;
	}
	public void setParental_material(String parental_material) {
		this.parental_material = parental_material;
	}
	public String getTechnique() {
		return technique;
	}
	public void setTechnique(String technique) {
		this.technique = technique;
	}
	public String getSelectioncriteria() {
		return selectioncriteria;
	}
	public void setSelectioncriteria(String selectioncriteria) {
		this.selectioncriteria = selectioncriteria;
	}
	public String getSelectionstage() {
		return selectionstage;
	}
	public void setSelectionstage(String selectionstage) {
		this.selectionstage = selectionstage;
	}
	public String getBreeding_location() {
		return breeding_location;
	}
	public void setBreeding_location(String breeding_location) {
		this.breeding_location = breeding_location;
	}
	public String getComparative_trial_filename() {
		return comparative_trial_filename;
	}
	public void setComparative_trial_filename(String comparative_trial_filename) {
		this.comparative_trial_filename = comparative_trial_filename;
	}
	public String getComparative_trial_filepath() {
		return comparative_trial_filepath;
	}
	public void setComparative_trial_filepath(String comparative_trial_filepath) {
		this.comparative_trial_filepath = comparative_trial_filepath;
	}
	public String getComparative_location() {
		return comparative_location;
	}
	public void setComparative_location(String comparative_location) {
		this.comparative_location = comparative_location;
	}
	public String getComparative_place() {
		return comparative_place;
	}
	public void setComparative_place(String comparative_place) {
		this.comparative_place = comparative_place;
	}
	public String getComparative_period() {
		return comparative_period;
	}
	public void setComparative_period(String comparative_period) {
		this.comparative_period = comparative_period;
	}
	public String getComparative_year() {
		return comparative_year;
	}
	public void setComparative_year(String comparative_year) {
		this.comparative_year = comparative_year;
	}
	public String getComparative_month() {
		return comparative_month;
	}
	public void setComparative_month(String comparative_month) {
		this.comparative_month = comparative_month;
	}
	public String getComparative_cultivation() {
		return comparative_cultivation;
	}
	public void setComparative_cultivation(String comparative_cultivation) {
		this.comparative_cultivation = comparative_cultivation;
	}
	public String getComparative_scale() {
		return comparative_scale;
	}
	public void setComparative_scale(String comparative_scale) {
		this.comparative_scale = comparative_scale;
	}
	public String getComparative_reference() {
		return comparative_reference;
	}
	public void setComparative_reference(String comparative_reference) {
		this.comparative_reference = comparative_reference;
	}
	public String getComparative_criteria() {
		return comparative_criteria;
	}
	public void setComparative_criteria(String comparative_criteria) {
		this.comparative_criteria = comparative_criteria;
	}
	public String getComparative_design() {
		return comparative_design;
	}
	public void setComparative_design(String comparative_design) {
		this.comparative_design = comparative_design;
	}
	public String getComparative_analysis() {
		return comparative_analysis;
	}
	public void setComparative_analysis(String comparative_analysis) {
		this.comparative_analysis = comparative_analysis;
	}
	public String getComparative_other() {
		return comparative_other;
	}
	public void setComparative_other(String comparative_other) {
		this.comparative_other = comparative_other;
	}
	public String getCharacteristics_group() {
		return characteristics_group;
	}
	public void setCharacteristics_group(String characteristics_group) {
		this.characteristics_group = characteristics_group;
	}
	public String getCharacteristics_distinguishing() {
		return characteristics_distinguishing;
	}
	public void setCharacteristics_distinguishing(String characteristics_distinguishing) {
		this.characteristics_distinguishing = characteristics_distinguishing;
	}
	public String getCharacteristics_denomination() {
		return characteristics_denomination;
	}
	public void setCharacteristics_denomination(String characteristics_denomination) {
		this.characteristics_denomination = characteristics_denomination;
	}
	public String getCharacteristics_variety_denomination() {
		return characteristics_variety_denomination;
	}
	public void setCharacteristics_variety_denomination(String characteristics_variety_denomination) {
		this.characteristics_variety_denomination = characteristics_variety_denomination;
	}
	public String getCharacteristics_variety_comparison() {
		return characteristics_variety_comparison;
	}
	public void setCharacteristics_variety_comparison(String characteristics_variety_comparison) {
		this.characteristics_variety_comparison = characteristics_variety_comparison;
	}
	public String getCharacteristics_variety_distinguishable() {
		return characteristics_variety_distinguishable;
	}
	public void setCharacteristics_variety_distinguishable(String characteristics_variety_distinguishable) {
		this.characteristics_variety_distinguishable = characteristics_variety_distinguishable;
	}
	public String getCharacteristics_variety_referencedenomination() {
		return characteristics_variety_referencedenomination;
	}
	public void setCharacteristics_variety_referencedenomination(String characteristics_variety_referencedenomination) {
		this.characteristics_variety_referencedenomination = characteristics_variety_referencedenomination;
	}
	public String getCharacteristics_variety_choice() {
		return characteristics_variety_choice;
	}
	public void setCharacteristics_variety_choice(String characteristics_variety_choice) {
		this.characteristics_variety_choice = characteristics_variety_choice;
	}
	public String getCharacteristics_basic_distinguishable() {
		return characteristics_basic_distinguishable;
	}
	public void setCharacteristics_basic_distinguishable(String characteristics_basic_distinguishable) {
		this.characteristics_basic_distinguishable = characteristics_basic_distinguishable;
	}
	public String getStatement_distinctiveness() {
		return statement_distinctiveness;
	}
	public void setStatement_distinctiveness(String statement_distinctiveness) {
		this.statement_distinctiveness = statement_distinctiveness;
	}
	public String getStatement_uniformity() {
		return statement_uniformity;
	}
	public void setStatement_uniformity(String statement_uniformity) {
		this.statement_uniformity = statement_uniformity;
	}
	public String getStatement_stability() {
		return statement_stability;
	}
	public void setStatement_stability(String statement_stability) {
		this.statement_stability = statement_stability;
	}
	public String getMethods_candidate() {
		return methods_candidate;
	}
	public void setMethods_candidate(String methods_candidate) {
		this.methods_candidate = methods_candidate;
	}
	public String getGrouping_characters() {
		return grouping_characters;
	}
	public void setGrouping_characters(String grouping_characters) {
		this.grouping_characters = grouping_characters;
	}
	public String getStability_parameter() {
		return stability_parameter;
	}
	public void setStability_parameter(String stability_parameter) {
		this.stability_parameter = stability_parameter;
	}
	public boolean isVariety_withdrawn() {
		return variety_withdrawn;
	}
	public void setVariety_withdrawn(boolean variety_withdrawn) {
		this.variety_withdrawn = variety_withdrawn;
	}
	public String getSupplement_information_name() {
		return supplement_information_name;
	}
	public void setSupplement_information_name(String supplement_information_name) {
		this.supplement_information_name = supplement_information_name;
	}
	public String getSupplement_information_path() {
		return supplement_information_path;
	}
	public void setSupplement_information_path(String supplement_information_path) {
		this.supplement_information_path = supplement_information_path;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public int getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(int modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Date getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(Date modifiedon) {
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
	public String getRecord_language() {
		return record_language;
	}
	public void setRecord_language(String record_language) {
		this.record_language = record_language;
	}
}
