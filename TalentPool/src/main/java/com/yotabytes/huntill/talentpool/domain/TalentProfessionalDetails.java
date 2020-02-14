package com.yotabytes.huntill.talentpool.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity (name= "talent_ProfessionalDetails")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class TalentProfessionalDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int experienceId;
	private String candidateUniqueId;
	private String jobTitel;
	private String company;
	private String yearOfExperience;
	private Date startDate;
	private Date endDate;
	private String description;
	private String createdBy;
	private Date createdDate;
	private String updateBy;
	private Date updateDate;
	
	public int getExperienceId() {
		return experienceId;
	}
	public void setExperienceId(int experienceId) {
		this.experienceId = experienceId;
	}
	public String getCandidateUniqueId() {
		return candidateUniqueId;
	}
	public void setCandidateUniqueId(String candidateUniqueId) {
		this.candidateUniqueId = candidateUniqueId;
	}
	public String getJobTitel() {
		return jobTitel;
	}
	public void setJobTitel(String jobTitel) {
		this.jobTitel = jobTitel;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
