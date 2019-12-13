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

@Entity (name= "talent_candidate_ProgrammerSkills")

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class TalentCandidateProgrammerSkills {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int skillId;
	private String candidateUniqeId;
	private String programingLanguage;
	private String database;
	private String frontEnd;
	private String tools;
	private String IDE;
	private String operatingSystem;
	private String createdBy;
	private Date createdDate;
	private String updateBy;
	private Date updateDate;
	
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getProgramingLanguage() {
		return programingLanguage;
	}
	public void setProgramingLanguage(String programingLanguage) {
		this.programingLanguage = programingLanguage;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getFrontEnd() {
		return frontEnd;
	}
	public void setFrontEnd(String frontEnd) {
		this.frontEnd = frontEnd;
	}
	public String getTools() {
		return tools;
	}
	public void setTools(String tools) {
		this.tools = tools;
	}
	public String getIDE() {
		return IDE;
	}
	public void setIDE(String iDE) {
		IDE = iDE;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
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
	public String getCandidateUniqeId() {
		return candidateUniqeId;
	}
	public void setCandidateUniqeId(String candidateUniqeId) {
		this.candidateUniqeId = candidateUniqeId;
	}
	
	
}
