package com.yotabytes.huntill.talentpool.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.domain.*;
import com.yotabytes.huntill.talentpool.repository.CandidateAddressRepository;
import com.yotabytes.huntill.talentpool.repository.CandidateEducationRepository;
import com.yotabytes.huntill.talentpool.repository.CandidateExperienceRepository;
import com.yotabytes.huntill.talentpool.repository.CandidateInformationRepository;
import com.yotabytes.huntill.talentpool.repository.CandidateProjectDetailsRepository;

import com.yotabytes.huntill.talentpool.repository.TalentQuestionAnswerRepository;
import com.yotabytes.huntill.talentpool.repository.TalentQuestionRepository;
import com.yotabytes.huntill.talentpool.service.TalentPoolService;

@Service
public class TalentPoolServiceImpl implements TalentPoolService {

	@Autowired
	private TalentQuestionRepository talentQuestionRepository;

	@Autowired
	private CandidateInformationRepository candidateInformationRepository;

	@Autowired
	private CandidateProjectDetailsRepository candidateProjectDetailsRepository;

	@Autowired
	private CandidateEducationRepository candidateEducationRepository;

	@Autowired
	private CandidateAddressRepository candidateAddressRepository;

	@Autowired
	private TalentQuestionAnswerRepository talentQuestionAnswerRepository;

	public List<TalentQuestion> findAll() {
		return talentQuestionRepository.findAll();
	}

	public TalentCandidateInformation saveCandidateInformation(TalentCandidateInformation information) {
		return candidateInformationRepository.save(information);
	}

	public TalentCandidateProjectDetails saveCandidateExperience(TalentCandidateProjectDetails experience) {
		return candidateProjectDetailsRepository.save(experience);
	}

	public TalentCandidateAddress saveCandidateAddress(TalentCandidateAddress address) {
		return candidateAddressRepository.save(address);
	}

	public TalentEducationDetails saveEducationDetails(TalentEducationDetails educationDetails) {
		return candidateEducationRepository.save(educationDetails);
	}

	public TalentCandidateInformation findByUserName(String userName) {

		return candidateInformationRepository.findByUserName(userName);
	}

	public TalentCandidateInformation findByEmailId(String email) {
		return candidateInformationRepository.findByEmailId(email);
	}

	public TalentCandidateInformation findByUserNameAndPasswordAndIsActive(String username, String password,
			String isActive) {
		return candidateInformationRepository.findByUserNameAndPasswordAndIsActive(username, password, isActive);
	}

	public TalentCandidateInformation findByCandidateUniqeId(String candidateUniqeId) {
		return candidateInformationRepository.findByCandidateUniqeId(candidateUniqeId);
	}

	/*
	 * public CandidateInformation save(String encriptedPassword) { return null; }
	 */
	public TalentCandidateProjectDetails findByCandidateUniqeid(String candidateUniqeId) {
		return candidateProjectDetailsRepository.findBycandidateUniqeId(candidateUniqeId);
	}

	public TalentEducationDetails findByCandidateEducationDetails(String candidateUniqeId) {
		return candidateEducationRepository.findBycandidateUniqeId(candidateUniqeId);
	}

	public TalentCandidateAddress findByCandidateUniqeIdFromCandidateAddress(String candidateUniqeId) {
		return candidateAddressRepository.findBycandidateUniqeId(candidateUniqeId);
	}

	public TalentQuestionAnswer saveQuestionAnswer(TalentQuestionAnswer answer) {
		return talentQuestionAnswerRepository.save(answer);
	}

	/*
	 * public ArrayList<CandidateInformation> findByCriteria( String
	 * passingYear,String instituteName,String technologyUsed) {
	 * System.out.println("service:"+passingYear); return
	 * candidateInformationRepository.findAll(new
	 * Specification<CandidateInformation>() {
	 *//**
		* 
		*//*
			 * private static final long serialVersionUID = 1L;
			 * 
			 * @Override public Predicate toPredicate(Root<CandidateInformation> root,
			 * CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) { List<Predicate>
			 * predicates = new ArrayList<>();
			 * 
			 * CriteriaQuery<TalentCandidateExperience> query1 =
			 * criteriaBuilder.createQuery(TalentCandidateExperience.class);
			 * if(passingYear!=null) {
			 * predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(
			 * "passingYear"), passingYear))); } if(instituteName!=null){
			 * predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(
			 * "instituteName"), instituteName))); } //ListJoin<CandidateInformation,
			 * TalentCandidateExperience> phoneJoin =
			 * root.join(CandidateInformation.talentCandidateExperience);
			 * 
			 * //Path<Object> orderItems = root.get("talentCandidateExperience");
			 * 
			 * Join<CandidateInformation, TalentCandidateExperience> talent =
			 * root.join("talentCandidateExperience");
			 * 
			 * if (technologyUsed != null) { predicates.add(
			 * query1.select(talent).where(criteriaBuilder.equal(talent.get("technologyUsed"
			 * ),"technologyUsed"))); }
			 * 
			 * 
			 * 
			 * if (technologyUsed != null) { predicates.add(
			 * criteriaBuilder.and(criteriaBuilder.equal(talent.get("technologyUsed"),
			 * technologyUsed))); }
			 * 
			 * System.out.println("predicates"+predicates.toString());
			 * 
			 * return criteriaBuilder.and(predicates.toArray(new
			 * Predicate[predicates.size()])); } }); }
			 */
}
