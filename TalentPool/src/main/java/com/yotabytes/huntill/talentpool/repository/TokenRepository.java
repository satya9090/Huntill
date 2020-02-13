package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.repository.CrudRepository;

import com.yotabytes.huntill.talentpool.domain.PasswordResetToken;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateInformation;

public interface TokenRepository extends CrudRepository<PasswordResetToken, Long> {

	PasswordResetToken findByCandidateUniqueId(String candidateUniqueId);

	PasswordResetToken findByToken(String token);

	//PasswordResetToken findByCandidateUniqueIdAndIsUpdate(String candidateUniqueId, Object setIsUpdate);

	PasswordResetToken findByCandidateUniqueIdAndIsUpdate(String candidateUniqueId, String setIsUpdate);

	//PasswordResetToken findByCandidateUniqueId(String candidateUniqueId);

	//PasswordResetToken findByCandidateUniqueId(String candidateUniqueId);

}
