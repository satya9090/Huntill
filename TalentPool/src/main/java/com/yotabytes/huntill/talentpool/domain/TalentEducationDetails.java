package com.yotabytes.huntill.talentpool.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity (name= "talent_candidate_EducationDetails")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class TalentEducationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int educationId;
	private String candidateUniqueId;
	@NotEmpty(message = "Please enter Institute Name")
	private String institution;
	@NotEmpty(message = "Please enter Stating Year")
	private Date startYear;
	private Date endYear;
	@NotEmpty(message = "Please enter Specification")
	private String subject;
	@NotEmpty(message = "Please enter Percentage")
	private String percentage;
	private String createdBy;
	private Date createdDate;
	private String updateBy;
	private Date updateDate;
	public int getEducationId() {
		return educationId;
	}
	public void setEducationId(int id) {
		this.educationId = id;
	}
	
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
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
	public String getCandidateUniqueId() {
		return candidateUniqueId;
	}
	public void setCandidateUniqueId(String candidateUniqueId) {
		this.candidateUniqueId = candidateUniqueId;
	}
	
	
}
