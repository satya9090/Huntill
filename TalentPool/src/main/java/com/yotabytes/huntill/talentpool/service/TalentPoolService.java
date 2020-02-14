package com.yotabytes.huntill.talentpool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.domain.TalentCandidateAddress;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateInformation;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateProjectDetails;

import com.yotabytes.huntill.talentpool.domain.TalentEducationDetails;
import com.yotabytes.huntill.talentpool.domain.TalentProfessionalDetails;
import com.yotabytes.huntill.talentpool.domain.TalentQuestion;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionAnswer;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionOption;

@Service
public interface TalentPoolService {

	public TalentCandidateInformation saveCandidateInformation(TalentCandidateInformation information);

	public Iterable<TalentCandidateProjectDetails> saveCandidateProjectDetails(
			List<TalentCandidateProjectDetails> projectDetails);

	public TalentCandidateInformation findByUserId(String userId);

	public TalentCandidateInformation findByEmailId(String email);

	public TalentCandidateInformation findByUserNameAndPasswordAndIsActive(String username, String password,
			String isActive);

	public TalentCandidateInformation findByCandidateUniqeId(String candidateUniqeId);

	public TalentCandidateInformation findByCandidateUniqueId(String candidateUniqueId);
	/* public CandidateInformation save(String encriptedPassword); */

	public TalentCandidateProjectDetails findByCandidateUniqeid(String candidateUniqeId);

	public TalentCandidateAddress findByCandidateUniqeIdFromCandidateAddress(String candidateUniqeId);

	public TalentQuestionAnswer saveQuestionAnswer(TalentQuestionAnswer answer);

	public Iterable<TalentCandidateAddress> saveCandidateAddress(List<TalentCandidateAddress> address);

	public Iterable<TalentEducationDetails> saveEducationDetails(Iterable<TalentEducationDetails> educationDetails);

	public TalentEducationDetails findByCandidateEducationDetails(String candidateUniqeId);

	public TalentCandidateAddress findByCandidateAddressId(int addressId);

	public TalentCandidateAddress saveCandidateUpdateAddress(TalentCandidateAddress addressNew);

	public TalentCandidateProjectDetails findByCandidateProjectId(int projectId);

	public TalentCandidateProjectDetails saveCandidateProject(TalentCandidateProjectDetails project1);

	public TalentEducationDetails findEducationId(int educationId);

	public TalentEducationDetails saveEducationDetail(TalentEducationDetails educationNew);

	public TalentCandidateInformation saveCandidateInformationProfiles(TalentCandidateInformation information1);

	public List<TalentCandidateInformation> searchCandidateInfomation(TalentCandidateInformation candidateInformation);

	public TalentCandidateInformation findByUserName(String name);

	public TalentCandidateInformation findByUserNameAndIsActive(String userName, String isActive);

	public List<TalentQuestion> findAll();

	public List<TalentCandidateProjectDetails> find();

	public List<TalentCandidateInformation> findAllUser();

	public Iterable<TalentProfessionalDetails> saveTalentExperienceDetails(
			List<TalentProfessionalDetails> talentExperience);

	/*
	 * public List<TalentCandidateSearch>
	 * findBySkillsAndCurrentLocationAndExperience(String skills, String
	 * currentLocation, String experience);
	 */

}
