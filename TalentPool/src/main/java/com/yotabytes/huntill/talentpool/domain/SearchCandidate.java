package com.yotabytes.huntill.talentpool.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.yotabytes.huntill.talentpool.domain.CandidateInformation.CandidateInformationBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity (name= "talent_candidate_search")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter

public class SearchCandidate implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Id
	private String candidateUniqeId;
	private String passingYear;
	
	/*
	 * @OneToMany(cascade=CascadeType.ALL)
	 * 
	 * @JoinColumn(name="candidateUniqueId") private Set<CandidateInformation>
	 * candidateInformation;
	 */
	 
	/*
	 * @OneToMany(cascade=CascadeType.ALL) private Set<TalentCandidateExperience>
	 * talentCandidate;
	 */
	
	
	
}
