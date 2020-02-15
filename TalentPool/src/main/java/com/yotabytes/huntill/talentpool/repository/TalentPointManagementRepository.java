package com.yotabytes.huntill.talentpool.repository;

import com.yotabytes.huntill.talentpool.domain.TalentPointManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yotabytes.huntill.talentpool.domain.TalentPointManagement;

public interface TalentPointManagementRepository extends JpaRepository<TalentPointManagement, Integer>{

	TalentPointManagement findByCandidateUniqueId(String candidateUniqueId);

}
