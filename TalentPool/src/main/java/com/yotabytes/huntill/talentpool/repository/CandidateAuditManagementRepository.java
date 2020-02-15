package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yotabytes.huntill.talentpool.domain.TalentAuditPointManagement;

public interface CandidateAuditManagementRepository extends JpaRepository<TalentAuditPointManagement, Integer>{

}
