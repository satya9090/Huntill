package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.repository.CrudRepository;

import com.yotabytes.huntill.talentpool.domain.TalentCandidateExperience;;

public interface CandidateExperienceRepository extends CrudRepository<TalentCandidateExperience, Integer>{

	TalentCandidateExperience findBycandidateUniqueId(String candidateUniqeId);

}
