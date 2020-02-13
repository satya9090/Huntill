package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yotabytes.huntill.talentpool.domain.TalentPointManagment;

public interface TalentPointManagmentRepository extends JpaRepository<TalentPointManagment, Integer>{

	TalentPointManagment findByCandidateUniqueId(String candidateUniqueId);

}
