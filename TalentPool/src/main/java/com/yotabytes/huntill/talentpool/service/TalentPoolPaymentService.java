package com.yotabytes.huntill.talentpool.service;
import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.domain.TalentPointManagment;
@Service
public interface TalentPoolPaymentService {

	TalentPointManagment savePoint(TalentPointManagment pointManagment);

	TalentPointManagment findByCandidateUniqueId(String candidateUniqueId);

	TalentPointManagment updatePoint(TalentPointManagment talentPointManagment1);

	TalentPointManagment removePoint(TalentPointManagment talentPointManagment1);

}
