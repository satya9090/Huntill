package com.yotabytes.huntill.talentpool.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity (name= "talent_candidate_Programme_skills")

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
	@Column(name = "skill_id")
	private int skillId;
	@Column(name = "candidate_unique_id")
	private String candidateUniqueId;
	@Column(name = "programing_language")
	private String programingLanguage;
	@Column(name = "data_base")
	private String database;
	@Column(name = "front_end")
	private String frontEnd;
	@Column(name = "tools")
	private String tools;
	@Column(name = "operating_system")
	private String operatingSystem;
	@Column(name = "created_by")
	private String createdBy;
	@CreationTimestamp
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "update_by")
	private String updateBy;
	@CreationTimestamp
	@Column(name = "update_date")
	private Date updateDate;
	
	@Column(name = "framework")
	private String framework;

}
