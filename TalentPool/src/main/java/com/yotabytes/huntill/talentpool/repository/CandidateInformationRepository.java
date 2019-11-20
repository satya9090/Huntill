package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yotabytes.huntill.talentpool.domain.CandidateInformation;
@Repository
public interface CandidateInformationRepository extends CrudRepository<CandidateInformation, String> {

	CandidateInformation findByUsername(String user_name);

	CandidateInformation findByEmailId(String email);

	CandidateInformation findByUsernameAndPassword(String username, String password);

}
