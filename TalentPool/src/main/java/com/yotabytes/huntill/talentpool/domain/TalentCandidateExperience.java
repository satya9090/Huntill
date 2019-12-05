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

@Entity (name= "talent_candidate_experience_details")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class TalentCandidateExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String candidateUniqueId;
	private String projectName;
	private Date startDate;
	private Date endDate;
	private String TechnologyUsed;
	private String description;
}
