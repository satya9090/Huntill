package com.yotabytes.huntill.talentpool.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.domain.*;
import com.yotabytes.huntill.talentpool.repository.CandidateExperienceRepository;
import com.yotabytes.huntill.talentpool.repository.CandidateInformationRepository;
import com.yotabytes.huntill.talentpool.repository.TalentQuestionRepository;
import com.yotabytes.huntill.talentpool.service.TalentPoolService;

@Service
public class TalentPoolServiceImpl implements TalentPoolService{
	
	@Autowired
	private TalentQuestionRepository talentQuestionRepository;

	@Autowired
	private CandidateInformationRepository candidateInformationRepository;
	
	@Autowired
	private CandidateExperienceRepository candidateExperienceRepository;
	
	
	public List<TalentQuestion> findAll() {
		return talentQuestionRepository.findAll();
	}

	public CandidateInformation saveCandidateInformation(CandidateInformation information) {
		return candidateInformationRepository.save(information);
	}
	
	public Talent_candidate_experience saveCandidateExperience(Talent_candidate_experience experience) {
		return candidateExperienceRepository.save(experience);
	}
	
	public CandidateInformation findByUserId(String userId)
	{
		System.out.println("service method call");
		return candidateInformationRepository.findByUserId(userId);
	}
	
	public CandidateInformation findByEmailId(String email)
	{
		return candidateInformationRepository.findByEmailId(email);
	}
	
	public CandidateInformation findByUserIdAndPasswordAndIsActive(String username, String password,String isActive)
	{
		return candidateInformationRepository.findByUserIdAndPasswordAndIsActive(username, password,isActive);
	}
	
	public CandidateInformation findByCandidateUniqeId(String candidateUniqeId)
	{
		return candidateInformationRepository.findByCandidateUniqeId(candidateUniqeId);
	}
	
	/*
	 * public CandidateInformation save(String encriptedPassword) { return null; }
	 */
	public Talent_candidate_experience findByCandidateUniqeid(String candidateUniqeId)
	{
		return candidateExperienceRepository.findBycandidateUniqueId(candidateUniqeId);
	}
}
