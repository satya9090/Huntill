package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yotabytes.huntill.talentpool.domain.TalentEducationDetails;
@Repository
public interface CandidateEducationRepository extends CrudRepository<TalentEducationDetails, String> {

	TalentEducationDetails findByCandidateUniqueId(String candidateUniqueId);

	TalentEducationDetails findByEducationId(int educationId);

}
