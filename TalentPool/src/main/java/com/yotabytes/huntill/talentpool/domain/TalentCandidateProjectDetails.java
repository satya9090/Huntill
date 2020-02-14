package com.yotabytes.huntill.talentpool.domain;

import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity (name= "talent_candidate_project_details")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class TalentCandidateProjectDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projectId;
	private String candidateUniqueId;
	@NotEmpty(message = "Please enter project name")
	private String projectName;
	@NotEmpty(message = "Please enter Start Date")
	private Date startDate;

	private Date endDate;
	@NotEmpty(message = "Please enter technologyUsed")
	private String[] technologyUsed;
	@NotEmpty(message = "Please enter project Details")
	private String projectDetails;
	@NotEmpty(message = "Please enter Role")
	private String role;
	@NotEmpty(message = "Please enter Role Description")
	private String roleDescription;
	@NotEmpty(message = "Please enter Company Name")
	private String companyName;
	private String createdBy;
	@CreationTimestamp
	private Date createdDate;
	private String updateBy;
	@CreationTimestamp
	private Date updateDate;
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int id) {
		this.projectId = id;
	}
	public String getCandidateUniqueId() {
		return candidateUniqueId;
	}
	public void setCandidateUniqueId(String candidateUniqueId) {
		this.candidateUniqueId = candidateUniqueId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	
	public String[] getTechnologyUsed() {
		return technologyUsed;
	}
	public void setTechnologyUsed(String[] technologyUsed) {
		this.technologyUsed = technologyUsed;
	}
	public String getProjectDetails() {
		return projectDetails;
	}
	public void setProjectDetails(String projectDetails) {
		this.projectDetails = projectDetails;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
