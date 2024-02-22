package com.ppvfra.entity;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("deprecation")
@Entity
@Table(name="applications")
public class Applications {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int applicantid;
	private Integer applicanttypeid;
	private String formtype;
	private String acknowledgementno;
	private String fileno;
	private String pvpno;
	private Integer companyid;
	private Integer institutionid;
	private String principalplace;
	private Integer domicile_statecode;
	private Integer domicile_districtcode;
	private int cropid;
	private int cropspeciesid;
	private String denomination;
	private Integer varirtytypeid;
	private Integer subvarietytypeid;
	private Date date_notification;
	private String notification_number;
	private String copy_notiifcation_name;
	private String copy_notiifcation_path;
	private String release_proposal_name;
	private String release_proposal_path;
	private String candidatevariety;
	private String candidatevariety_other;
	private String dus_features;
	private String priority_in_countr;
	private Timestamp priority_on_date;
	private Boolean is_commercialised;
	private Date first_sale_date;
	private String issue_first_sale_name;
	private String issue_first_sale_path;
	private String protection_countries;
	private String c_denomination;
	private String trademark;
	private String variation;
	private String is_parental_required;
	private Boolean is_farmer_variety_required;
	private String planning_benefit_sharing;
	private String exotic_germplasm;
	private String expected_performance;
	private Integer branchoffice;
	private Integer registrarassigned;
	private Date filing_date;
	private Integer application_current_status;
	private Integer createdby;
	private Timestamp createdon;
	private Integer modifiedby;
	private Timestamp modifiedon;
	private Boolean active;
	private String createdbyip;
	private String modifiedbyip;
	private String record_language;
	private String cropdetail;
	
	private String company_name;
	private String company_address;
	private String company_incorporation;
	private Boolean participation_in_capital;
	private Boolean is_convention_applicable;
	private Date application_submission_date;
	private Date acknowledgement_date;
	private String acknowledge_doc_path;
	public String variation_name;
	public String transgenic_bioname;
	public String transgenic_biopath;
	
	private String genetic_engineering;
	private Integer initialvariety;
	private String essentially_derived_variety;
	private String essentially_derived_variety_denomination;
	private String essentially_derived_variety_geographical_source;
	private String essentially_derived_variety_owner;
	
	
	
	public Integer getInitialvariety() {
		return initialvariety;
	}
	public void setInitialvariety(Integer initialvariety) {
		this.initialvariety = initialvariety;
	}
	public String getTransgenic_bioname() {
		return transgenic_bioname;
	}
	public void setTransgenic_bioname(String transgenic_bioname) {
		this.transgenic_bioname = transgenic_bioname;
	}
	public String getTransgenic_biopath() {
		return transgenic_biopath;
	}
	public void setTransgenic_biopath(String transgenic_biopath) {
		this.transgenic_biopath = transgenic_biopath;
	}



	
	public String getVariation_name() {
		return variation_name;
	}
	public void setVariation_name(String variation_name) {
		this.variation_name = variation_name;
	}
	public String getAcknowledge_doc_path() {
		return acknowledge_doc_path;
	}
	public void setAcknowledge_doc_path(String acknowledge_doc_path) {
		this.acknowledge_doc_path = acknowledge_doc_path;
	}
	public Date getAcknowledgement_date() {
		return acknowledgement_date;
	}
	public void setAcknowledgement_date(Date acknowledgement_date) {
		this.acknowledgement_date = acknowledgement_date;
	}
	public Date getApplication_submission_date() {
		return application_submission_date;
	}
	public void setApplication_submission_date(Date application_submission_date) {
		this.application_submission_date = application_submission_date;
	}


	private String denominationsx;
	public String getDenominationsx() {
		return denominationsx;
	}
	public void setDenominationsx(String denominationsx) {
		this.denominationsx = denominationsx;
	}
	public String getGeographical_source() {
		return geographical_source;
	}
	public void setGeographical_source(String geographical_source) {
		this.geographical_source = geographical_source;
	}
	public String getAttribution() {
		return attribution;
	}
	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}
	public String getOwners() {
		return owners;
	}
	public void setOwners(String owners) {
		this.owners = owners;
	}


	private String geographical_source;
	private String attribution;
	private String owners;
	
	private Integer company_nationality;
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_incorporation() {
		return company_incorporation;
	}
	public void setCompany_incorporation(String company_incorporation) {
		this.company_incorporation = company_incorporation;
	}
	
	
	
	 
	public Integer getCompany_nationality() {
		return company_nationality;
	}
	public void setCompany_nationality(Integer company_nationality) {
		this.company_nationality = company_nationality;
	}
	public Boolean getIs_farmer_variety_required() {
		return is_farmer_variety_required;
	}
	public void setIs_farmer_variety_required(Boolean is_farmer_variety_required) {
		this.is_farmer_variety_required = is_farmer_variety_required;
	}
	public Boolean getParticipation_in_capital() {
		return participation_in_capital;
	}
	public void setParticipation_in_capital(Boolean participation_in_capital) {
		this.participation_in_capital = participation_in_capital;
	}

	public void setVarirtytypeid(Integer varirtytypeid) {
		this.varirtytypeid = varirtytypeid;
	}
	public void setSubvarietytypeid(Integer subvarietytypeid) {
		this.subvarietytypeid = subvarietytypeid;
	}

	
	private String application_declare_place;
    public String getApplication_declare_place() {
		return application_declare_place;
	}
	public void setApplication_declare_place(String application_declare_place) {
		this.application_declare_place = application_declare_place;
	}
	public Date getApplication_signed_date() {
		return application_signed_date;
	}
	public void setApplication_signed_date(Date application_signed_date) {
		this.application_signed_date = application_signed_date;
	}
	public String getIs_agreed_clause() {
		return is_agreed_clause;
	}
	public void setIs_agreed_clause(String is_agreed_clause) {
		this.is_agreed_clause = is_agreed_clause;
	}
	private Date application_signed_date;
    private String is_agreed_clause;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(int applicantid) {
		this.applicantid = applicantid;
	}
	
	public String getFormtype() {
		return formtype;
	}
	public Integer getApplicanttypeid() {
		return applicanttypeid;
	}
	public void setApplicanttypeid(Integer applicanttypeid) {
		this.applicanttypeid = applicanttypeid;
	}
	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}
	public String getAcknowledgementno() {
		return acknowledgementno;
	}
	public void setAcknowledgementno(String acknowledgementno) {
		this.acknowledgementno = acknowledgementno;
	}
	public String getFileno() {
		return fileno;
	}
	public void setFileno(String fileno) {
		this.fileno = fileno;
	}
	public String getPvpno() {
		return pvpno;
	}
	public void setPvpno(String pvpno) {
		this.pvpno = pvpno;
	}
	 
	 
	public Integer getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	public Integer getInstitutionid() {
		return institutionid;
	}
	public void setInstitutionid(Integer institutionid) {
		this.institutionid = institutionid;
	}
	public String getPrincipalplace() {
		return principalplace;
	}
	public void setPrincipalplace(String principalplace) {
		this.principalplace = principalplace;
	}
	public int getCropid() {
		return cropid;
	}
	public void setCropid(int cropid) {
		this.cropid = cropid;
	}
	public int getCropspeciesid() {
		return cropspeciesid;
	}
	public void setCropspeciesid(int cropspeciesid) {
		this.cropspeciesid = cropspeciesid;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public Integer getVarirtytypeid() {
		return varirtytypeid;
	}
	public void setVarirtytypeid(int varirtytypeid) {
		this.varirtytypeid = varirtytypeid;
	}
	public Integer getSubvarietytypeid() {
		return subvarietytypeid;
	}
	public void setSubvarietytypeid(int subvarietytypeid) {
		this.subvarietytypeid = subvarietytypeid;
	}
	public Date getDate_notification() {
		return date_notification;
	}
	public void setDate_notification(Date date_notification) {
		this.date_notification = date_notification;
	}
	public String getNotification_number() {
		return notification_number;
	}
	public void setNotification_number(String notification_number) {
		this.notification_number = notification_number;
	}
	public String getCopy_notiifcation_name() {
		return copy_notiifcation_name;
	}
	public void setCopy_notiifcation_name(String copy_notiifcation_name) {
		this.copy_notiifcation_name = copy_notiifcation_name;
	}
	public String getCopy_notiifcation_path() {
		return copy_notiifcation_path;
	}
	public void setCopy_notiifcation_path(String copy_notiifcation_path) {
		this.copy_notiifcation_path = copy_notiifcation_path;
	}
	public String getRelease_proposal_name() {
		return release_proposal_name;
	}
	public void setRelease_proposal_name(String release_proposal_name) {
		this.release_proposal_name = release_proposal_name;
	}
	public String getRelease_proposal_path() {
		return release_proposal_path;
	}
	public void setRelease_proposal_path(String release_proposal_path) {
		this.release_proposal_path = release_proposal_path;
	}
	public String getCandidatevariety() {
		return candidatevariety;
	}
	public void setCandidatevariety(String candidatevariety) {
		this.candidatevariety = candidatevariety;
	}
	public String getCandidatevariety_other() {
		return candidatevariety_other;
	}
	public void setCandidatevariety_other(String candidatevariety_other) {
		this.candidatevariety_other = candidatevariety_other;
	}
	public String getDus_features() {
		return dus_features;
	}
	public void setDus_features(String dus_features) {
		this.dus_features = dus_features;
	}
	public String getPriority_in_countr() {
		return priority_in_countr;
	}
	public void setPriority_in_countr(String priority_in_countr) {
		this.priority_in_countr = priority_in_countr;
	}
	public Timestamp getPriority_on_date() {
		return priority_on_date;
	}
	public void setPriority_on_date(Timestamp priority_on_date) {
		this.priority_on_date = priority_on_date;
	}
	public Boolean getIs_commercialised() {
		return is_commercialised;
	}
	public void setIs_commercialised(Boolean is_commercialised) {
		this.is_commercialised = is_commercialised;
	}
	public Date getFirst_sale_date() {
		return first_sale_date;
	}
	public void setFirst_sale_date(Date first_sale_date) {
		this.first_sale_date = first_sale_date;
	}
	public String getIssue_first_sale_name() {
		return issue_first_sale_name;
	}
	public void setIssue_first_sale_name(String issue_first_sale_name) {
		this.issue_first_sale_name = issue_first_sale_name;
	}
	public String getIssue_first_sale_path() {
		return issue_first_sale_path;
	}
	public void setIssue_first_sale_path(String issue_first_sale_path) {
		this.issue_first_sale_path = issue_first_sale_path;
	}
	public String getProtection_countries() {
		return protection_countries;
	}
	public void setProtection_countries(String protection_countries) {
		this.protection_countries = protection_countries;
	}
	public String getC_denomination() {
		return c_denomination;
	}
	public void setC_denomination(String c_denomination) {
		this.c_denomination = c_denomination;
	}
	public String getTrademark() {
		return trademark;
	}
	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}
	public String getVariation() {
		return variation;
	}
	public void setVariation(String variation) {
		this.variation = variation;
	}
	public String getIs_parental_required() {
		return is_parental_required;
	}
	public void setIs_parental_required(String is_parental_required) {
		this.is_parental_required = is_parental_required;
	}
	public String getPlanning_benefit_sharing() {
		return planning_benefit_sharing;
	}
	public void setPlanning_benefit_sharing(String planning_benefit_sharing) {
		this.planning_benefit_sharing = planning_benefit_sharing;
	}
	public String getExotic_germplasm() {
		return exotic_germplasm;
	}
	public void setExotic_germplasm(String exotic_germplasm) {
		this.exotic_germplasm = exotic_germplasm;
	}
	public String getExpected_performance() {
		return expected_performance;
	}
	public void setExpected_performance(String expected_performance) {
		this.expected_performance = expected_performance;
	}
	
	public Integer getBranchoffice() {
		return branchoffice;
	}
	public void setBranchoffice(Integer branchoffice) {
		this.branchoffice = branchoffice;
	}
	public Integer getRegistrarassigned() {
		return registrarassigned;
	}
	public void setRegistrarassigned(Integer registrarassigned) {
		this.registrarassigned = registrarassigned;
	}
	public Date getFiling_date() {
		return filing_date;
	}
	public void setFiling_date(Date filing_date) {
		this.filing_date = filing_date;
	}
	
	 
	public Integer getApplication_current_status() {
		return application_current_status;
	}
	public void setApplication_current_status(Integer application_current_status) {
		this.application_current_status = application_current_status;
	}
	public Timestamp getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}
	 
	public Integer getCreatedby() {
		return createdby;
	}
	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}
	public Integer getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(Integer modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Timestamp getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(Timestamp modifiedon) {
		this.modifiedon = modifiedon;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
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
	public String getCropdetail() {
		return cropdetail;
	}
	public void setCropdetail(String cropdetail) {
		this.cropdetail = cropdetail;
	}
	public Boolean getIs_convention_applicable() {
		return is_convention_applicable;
	}
	public void setIs_convention_applicable(Boolean is_convention_applicable) {
		this.is_convention_applicable = is_convention_applicable;
	}
	public String getGenetic_engineering() {
		return genetic_engineering;
	}
	public void setGenetic_engineering(String genetic_engineering) {
		this.genetic_engineering = genetic_engineering;
	}
	public String getEssentially_derived_variety() {
		return essentially_derived_variety;
	}
	public void setEssentially_derived_variety(String essentially_derived_variety) {
		this.essentially_derived_variety = essentially_derived_variety;
	}
	public String getEssentially_derived_variety_denomination() {
		return essentially_derived_variety_denomination;
	}
	public void setEssentially_derived_variety_denomination(String essentially_derived_variety_denomination) {
		this.essentially_derived_variety_denomination = essentially_derived_variety_denomination;
	}
	public String getEssentially_derived_variety_geographical_source() {
		return essentially_derived_variety_geographical_source;
	}
	public void setEssentially_derived_variety_geographical_source(String essentially_derived_variety_geographical_source) {
		this.essentially_derived_variety_geographical_source = essentially_derived_variety_geographical_source;
	}
	public String getEssentially_derived_variety_owner() {
		return essentially_derived_variety_owner;
	}
	public void setEssentially_derived_variety_owner(String essentially_derived_variety_owner) {
		this.essentially_derived_variety_owner = essentially_derived_variety_owner;
	}
	
	public Integer getDomicile_statecode() {
		return domicile_statecode;
	}
	public void setDomicile_statecode(Integer domicile_statecode) {
		this.domicile_statecode = domicile_statecode;
	}
	public Integer getDomicile_districtcode() {
		return domicile_districtcode;
	}
	public void setDomicile_districtcode(Integer domicile_districtcode) {
		this.domicile_districtcode = domicile_districtcode;
	}




}
