package com.yotabytes.huntill.talentpool.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yotabytes.huntill.talentpool.domain.TalentCandidateInformation;
@Repository
public interface CandidateInformationRepository extends CrudRepository<TalentCandidateInformation, String> {

	TalentCandidateInformation findByUserName(String user_name);

	TalentCandidateInformation findByEmailId(String email);

	TalentCandidateInformation findByCandidateUniqueId(String candidateUniqeId);

	TalentCandidateInformation findByUserNameAndPasswordAndIsActive(String username, String password, String isActive);

	List<TalentCandidateInformation> findAll(Specification<TalentCandidateInformation> specification);

	TalentCandidateInformation findByUserNameAndIsActive(String userName, String isActive);

	/*List<TalentCandidateInformation> searchCandidateInfomation(TalentCandidateInformation candidateInformation)
	{
		 Query q = getEntityManager().createQuery()
	}*/

}
