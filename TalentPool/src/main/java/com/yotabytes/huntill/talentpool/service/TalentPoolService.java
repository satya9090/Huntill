package com.yotabytes.huntill.talentpool.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.yotabytes.huntill.talentpool.domain.TalentCandidateAddress;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateExperience;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateInformation;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateProjectDetails;
import com.yotabytes.huntill.talentpool.domain.TalentEducationDetails;
import com.yotabytes.huntill.talentpool.domain.TalentQuestion;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionAnswer;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionOption;



@Service
public interface TalentPoolService {
	
public List<TalentQuestion> findAll();
	
	public TalentCandidateInformation saveCandidateInformation(TalentCandidateInformation information);
	
	public TalentCandidateProjectDetails saveCandidateExperience(TalentCandidateProjectDetails experience);
	
	public TalentCandidateInformation findByUserName(String userName);
	
	public TalentCandidateInformation findByEmailId(String email);

	public TalentCandidateInformation findByUserNameAndPasswordAndIsActive(String username, String password,String isActive);

	public TalentCandidateInformation findByCandidateUniqeId(String candidateUniqeId);

	/* public CandidateInformation save(String encriptedPassword); */

	public TalentCandidateProjectDetails findByCandidateUniqeid(String candidateUniqeId);
	
	public TalentCandidateAddress findByCandidateUniqeIdFromCandidateAddress(String candidateUniqeId);

	public TalentQuestionAnswer saveQuestionAnswer(TalentQuestionAnswer answer);

	public TalentCandidateAddress saveCandidateAddress(TalentCandidateAddress address);

	public TalentEducationDetails saveEducationDetails(TalentEducationDetails educationDetails);

	public TalentEducationDetails findByCandidateEducationDetails(String candidateUniqeId);




	/*
	 * public ArrayList<CandidateInformation> findByCriteria(String passingYear,
	 * String instituteName,String technologyUsed);
	 */

}
