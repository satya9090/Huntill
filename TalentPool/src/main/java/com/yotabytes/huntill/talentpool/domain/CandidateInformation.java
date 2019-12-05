package com.yotabytes.huntill.talentpool.domain;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionOption.TalentQuestionOptionBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "talent_candidate_personal_details")

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter

public class CandidateInformation {
	@Id
	private String candidateUniqeId = UUID.randomUUID().toString().toUpperCase();
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int candidate_id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String userId;
	private String password;
	private String location;
	private Long contactNumber;
	private String emailId;
	private String alternateEmailId;
	private String instituteName;
	private String passingYear;
	private String grade;
	private String gender;
	private String isEmployer;
	private String isVerify = "N";
	private String isActive = "Y";
	  @OneToMany(cascade=CascadeType.ALL)
	  @JoinColumn(name="candidateUniqueId")
	  private Set<TalentCandidateExperience> talentCandidateExperience;
	  
	  

}
