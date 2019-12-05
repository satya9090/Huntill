package com.yotabytes.huntill.talentpool.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yotabytes.huntill.talentpool.domain.CandidateInformation;
import com.yotabytes.huntill.talentpool.domain.SearchCandidate;

@Repository
public interface CandidateInformationRepository extends CrudRepository<CandidateInformation, String> {

	CandidateInformation findByUserId(String user_name);

	CandidateInformation findByEmailId(String email);

	/*
	 * CandidateInformation findByUserIdAndPassword(String username, String
	 * password);
	 */

	CandidateInformation findByCandidateUniqeId(String candidateUniqeId);

	CandidateInformation findByUserIdAndPasswordAndIsActive(String username, String password, String isActive);

	ArrayList<CandidateInformation> findByPassingYear(String passingYear);

}
