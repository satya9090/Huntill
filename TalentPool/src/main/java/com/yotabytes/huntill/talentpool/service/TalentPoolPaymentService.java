package com.yotabytes.huntill.talentpool.service;

import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.domain.TalentPointManagement;

@Service
public interface TalentPoolPaymentService {

	TalentPointManagement savePoint(TalentPointManagement pointManagement);

	TalentPointManagement findByCandidateUniqueId(String candidateUniqueId);

	TalentPointManagement updatePoint(TalentPointManagement talentPointManagement1);

	TalentPointManagement removePoint(TalentPointManagement talentPointManagement1);

}
