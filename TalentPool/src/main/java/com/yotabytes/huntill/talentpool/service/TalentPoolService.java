package com.yotabytes.huntill.talentpool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.domain.CandidateInformation;
import com.yotabytes.huntill.talentpool.domain.TalentQuestion;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionAnswers;
import com.yotabytes.huntill.talentpool.domain.Talent_candidate_experience;

@Service
public interface TalentPoolService {
	
	public List<TalentQuestionAnswers> findAll();
	
	public CandidateInformation saveCandidateInformation(CandidateInformation information);
	
	public Talent_candidate_experience saveCandidateExperience(Talent_candidate_experience experience);

}
