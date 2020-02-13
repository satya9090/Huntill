package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yotabytes.huntill.talentpool.domain.TalentAuditPointManagment;

public interface CandidateAuditManagmentRepository extends JpaRepository<TalentAuditPointManagment, Integer>{

}
