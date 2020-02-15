package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yotabytes.huntill.talentpool.domain.TalentCandidateProjectDetails;
@Repository
public interface CandidateProjectDetailsRepository extends CrudRepository<TalentCandidateProjectDetails, String>{

	TalentCandidateProjectDetails findByCandidateUniqueId(String candidateUniqueId);

	TalentCandidateProjectDetails findByProjectId(int projectId);

	



}
