package com.yotabytes.huntill.talentpool.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.yotabytes.huntill.talentpool.domain.CandidateInformation.CandidateInformationBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity(name = "talent_candidate_programm")

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class TalentCandidateProgrammerSkills {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String candidateUniqueId;
	private String projectName;
	private Date startDate;
}
