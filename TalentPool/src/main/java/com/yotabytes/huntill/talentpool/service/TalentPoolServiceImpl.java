package com.yotabytes.huntill.talentpool.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.controller.TalentPoolPaymentController;
import com.yotabytes.huntill.talentpool.domain.*;
import com.yotabytes.huntill.talentpool.repository.CandidateProjectDetailsRepository;
import com.yotabytes.huntill.talentpool.repository.CandidateAddressRepository;
import com.yotabytes.huntill.talentpool.repository.CandidateEducationRepository;
import com.yotabytes.huntill.talentpool.repository.CandidateExperienceRepository;
import com.yotabytes.huntill.talentpool.repository.CandidateInformationRepository;
import com.yotabytes.huntill.talentpool.repository.TalentQuestionAnswerRepository;
import com.yotabytes.huntill.talentpool.repository.TalentQuestionRepository;
import com.yotabytes.huntill.talentpool.service.TalentPoolService;

@Service
public class TalentPoolServiceImpl implements TalentPoolService{
	
	final static Logger logger = Logger.getLogger(TalentPoolPaymentController.class);

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
	private CandidateExperienceRepository candidateExperienceRepository;
	
	
	
	@Autowired
	private TalentQuestionAnswerRepository talentQuestionAnswerRepository;

	 @PersistenceContext
	 private EntityManager manager;
	 
	public List<TalentQuestion> findAll() {
		return talentQuestionRepository.findAll();
	}

	public TalentCandidateInformation saveCandidateInformationProfiles(TalentCandidateInformation information) {
		TalentCandidateInformation information1 = null;
		
		try
		{
			information1 = candidateInformationRepository.findByCandidateUniqueId(information.getCandidateUniqueId());
			
			information1.setGender(information.getGender());
			//information1.setBloodGroup(information.getBloodGroup());
			information1.setAlternateEmailId(information.getAlternateEmailId());
			information1.setRole(information.getRole());
		//	information1.setDateOfBirth(information.getDateOfBirth());	
			information1.setSkills(information.getSkills());
			information1.setCurrentLocation(information.getCurrentLocation());
			information1.setExperience(information.getExperience());
			information1.setIsProfileComplete(information.getIsProfileComplete());
			
			return candidateInformationRepository.save(information1);
			//return information1;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public TalentCandidateInformation saveCandidateInformation(TalentCandidateInformation information) {
		return candidateInformationRepository.save(information);
	}
	public Iterable<TalentCandidateProjectDetails> saveCandidateProjectDetails(List<TalentCandidateProjectDetails> projectDetails) {
		return candidateProjectDetailsRepository.saveAll(projectDetails);
	}
	
	public Iterable<TalentCandidateAddress> saveCandidateAddress(List<TalentCandidateAddress> address)
	{
		return candidateAddressRepository.saveAll(address);
	}
	
	public TalentCandidateAddress findByCandidateAddressId(int addressId)
	{
		return candidateAddressRepository.findByAddressId(addressId);
	}
	
	public TalentCandidateAddress saveCandidateUpdateAddress(TalentCandidateAddress addressNew)
	{
		return candidateAddressRepository.save(addressNew);
	}
	
	public TalentCandidateProjectDetails findByCandidateProjectId(int projectId)
	{
		return candidateProjectDetailsRepository.findByprojectId(projectId);
	}
	
	
	public Iterable<TalentEducationDetails> saveEducationDetails(Iterable<TalentEducationDetails> educationDetails)
	{
		return  candidateEducationRepository.saveAll(educationDetails);
	}
	
	public TalentEducationDetails findEducationId(int educationId)
	{
		return  candidateEducationRepository.findByEducationId(educationId);
	}
	public TalentCandidateInformation findByUserId(String userId)
	{
		
		return candidateInformationRepository.findByUserName(userId);
	}
	
	public TalentEducationDetails saveEducationDetail(TalentEducationDetails educationNew)
	{
		return  candidateEducationRepository.save(educationNew);
	}
	public TalentCandidateProjectDetails saveCandidateProject(TalentCandidateProjectDetails project1)
	{
		return candidateProjectDetailsRepository.save(project1);
	}
	
	public TalentCandidateInformation findByEmailId(String email)
	{
		return candidateInformationRepository.findByEmailId(email);
	}
	
	public TalentCandidateInformation findByUserNameAndPasswordAndIsActive(String username, String password,String isActive)
	{
		return candidateInformationRepository.findByUserNameAndPasswordAndIsActive(username, password,isActive);
	}
	
	public TalentCandidateInformation findByCandidateUniqeId(String candidateUniqeId)
	{
		return candidateInformationRepository.findByCandidateUniqueId(candidateUniqeId);
	}
	
	   /*
	 * public CandidateInformation save(String encriptedPassword) { return null; }
	 */
	public TalentCandidateProjectDetails findByCandidateUniqeid(String candidateUniqeId)
	{
		return candidateProjectDetailsRepository.findBycandidateUniqueId(candidateUniqeId);
	}
	
	public TalentEducationDetails findByCandidateEducationDetails(String candidateUniqeId)
	{
		return candidateEducationRepository.findByCandidateUniqueId(candidateUniqeId);
	}
	
	public TalentCandidateAddress findByCandidateUniqeIdFromCandidateAddress(String candidateUniqeId)
	{
		return candidateAddressRepository.findByCandidateUniqueId(candidateUniqeId);
	}
	public List<TalentCandidateInformation> searchCandidateInfomation(TalentCandidateInformation candidateInformation)
	{
		return candidateInformationRepository.findAll(new Specification<TalentCandidateInformation>() {
	           @Override
	           public Predicate toPredicate(Root<TalentCandidateInformation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	               List<Predicate> predicates = new ArrayList<>();
	               if(candidateInformation.getSkills()!=null) {
	                   predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("skills"), "%"+candidateInformation.getSkills()+"%")));
	               }
	               if(candidateInformation.getCurrentLocation()!=null){
	                   predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("currentLocation"), candidateInformation.getCurrentLocation())));
	               }
	               if(candidateInformation.getExperience()!=null){
	                   predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("experience"), candidateInformation.getExperience())));
	               }
	               return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	           }
	       }); 
	}
	/*public List<TalentCandidateSearch> findBySkillsAndCurrentLocationAndExperience(String skills, String currentLocation,
			String experience)
	{
		return candidateSearchRepository.findBySkillsAndCurrentLocationAndExperience(skills,currentLocation,experience);
	}*/
	public TalentQuestionAnswer saveQuestionAnswer(TalentQuestionAnswer answer)
	{
		return talentQuestionAnswerRepository.save(answer);
	}
	public TalentCandidateInformation findByCandidateUniqueId(String candidateUniqueId)
	{
		return candidateInformationRepository.findByCandidateUniqueId(candidateUniqueId);
	}

	@Override
	public TalentCandidateInformation findByUserName(String name) {
		// TODO Auto-generated method stub
		TalentCandidateInformation talentCandidateInformation= candidateInformationRepository.findByUserName(name);
		talentCandidateInformation.setPassword(null);
		return talentCandidateInformation;
	}

	@Override
	public TalentCandidateInformation findByUserNameAndIsActive(String userName, String isActive) {
		// TODO Auto-generated method stub
		return candidateInformationRepository.findByUserNameAndIsActive(userName,isActive);
	}

	@Override
	public List<TalentCandidateInformation> findAllUser() {
		
		return (List<TalentCandidateInformation>) candidateInformationRepository.findAll();
		
	}

	@Override
	public List<TalentCandidateProjectDetails> find() {
		// TODO Auto-generated method stub
		return (List<TalentCandidateProjectDetails>) candidateProjectDetailsRepository.findAll();
	}
	
	
	public Iterable<TalentProfessionalDetails> saveTalentExperienceDetails(List<TalentProfessionalDetails> talentExperience)
	{
		return candidateExperienceRepository.saveAll(talentExperience);
	}
}
