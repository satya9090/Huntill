package com.yotabytes.huntill.talentpool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.domain.CandidateInformation;
import com.yotabytes.huntill.talentpool.domain.TalentQuestion;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionOption;
import com.yotabytes.huntill.talentpool.domain.Talent_candidate_experience;

@Service
public interface TalentPoolService {
	
	public List<TalentQuestion> findAll();
	
	public CandidateInformation saveCandidateInformation(CandidateInformation information);
	
	public Talent_candidate_experience saveCandidateExperience(Talent_candidate_experience experience);
	
	public CandidateInformation findByUserId(String userId);
	
	public CandidateInformation findByEmailId(String email);

	public CandidateInformation findByUserIdAndPassword(String username, String password);

	public CandidateInformation findByCandidateUniqeId(String candidateUniqeId);

	/* public CandidateInformation save(String encriptedPassword); */

	public Talent_candidate_experience findByCandidateUniqeid(String candidateUniqeId);

}
