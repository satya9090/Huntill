package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yotabytes.huntill.talentpool.domain.TalentCandidateInformation;
@Repository
public interface CandidateInformationRepository extends CrudRepository<TalentCandidateInformation, String> {

	TalentCandidateInformation findByUserName(String userName);

	TalentCandidateInformation findByEmailId(String email);

	TalentCandidateInformation findByCandidateUniqeId(String candidateUniqeId);

	TalentCandidateInformation findByUserNameAndPasswordAndIsActive(String username, String password, String isActive);

}
