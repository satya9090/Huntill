package com.yotabytes.huntill.talentpool.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.domain.CandidateInformation;
import com.yotabytes.huntill.talentpool.domain.SearchCandidate;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateExperience;
import com.yotabytes.huntill.talentpool.domain.TalentQuestion;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionAnswer;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionOption;



@Service
public interface TalentPoolService {
	
	public List<TalentQuestion> findAll();
	
	public CandidateInformation saveCandidateInformation(CandidateInformation information);
	
	public TalentCandidateExperience saveCandidateExperience(TalentCandidateExperience experience);
	
	public CandidateInformation findByUserId(String userId);
	
	public CandidateInformation findByEmailId(String email);

	public CandidateInformation findByUserIdAndPasswordAndIsActive(String username, String password,String isActive);

	public CandidateInformation findByCandidateUniqeId(String candidateUniqeId);

	/* public CandidateInformation save(String encriptedPassword); */

	public TalentCandidateExperience findByCandidateUniqeid(String candidateUniqeId);

	public TalentQuestionAnswer saveQuestionAnswer(TalentQuestionAnswer answer);

	public ArrayList<CandidateInformation> findByCriteria(String passingYear, String instituteName,String technologyUsed);

}
