package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.repository.CrudRepository;

import com.yotabytes.huntill.talentpool.domain.Talent_candidate_experience;

public interface CandidateExperienceRepository extends CrudRepository<Talent_candidate_experience, Integer>{

	

	Talent_candidate_experience findBycandidateUniqueId(String candidateUniqeId);

}
