package com.yotabytes.huntill.talentpool.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.domain.TalentAuditPointManagement;
import com.yotabytes.huntill.talentpool.domain.TalentPointManagement;
import com.yotabytes.huntill.talentpool.repository.CandidateAuditManagementRepository;
import com.yotabytes.huntill.talentpool.repository.TalentPointManagementRepository;
import com.yotabytes.huntill.talentpool.service.TalentPoolPaymentService;
import com.yotabytes.huntill.talentpool.utils.PointManagment;

@Service
public class TalentPoolPaymentServiceImpl implements TalentPoolPaymentService {

	@Autowired
	public TalentPointManagementRepository PointManagementRepository;

	@Autowired
	public CandidateAuditManagementRepository auditManagementRepository;

	public TalentPointManagement savePoint(TalentPointManagement pointManagement) {

		try {
			TalentAuditPointManagement auditPoint = new TalentAuditPointManagement();
			auditPoint.setCandidateUniqueId(pointManagement.getCandidateUniqueId());
			auditPoint.setAddPoint(pointManagement.getDollar() * PointManagment.dollar);
			auditPoint.setTransactionType(pointManagement.getTransactionType());
			auditPoint.setCreatedDate(new Date());
			auditManagementRepository.save(auditPoint);

			pointManagement.setCurrentAvailablePoint(auditPoint.getAddPoint());
			pointManagement.setCreatedDate(new Date());
			return PointManagementRepository.save(pointManagement);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public TalentPointManagement updatePoint(TalentPointManagement talentPointManagement1) {
		try {
			TalentAuditPointManagement auditPoint = new TalentAuditPointManagement();
			auditPoint.setCandidateUniqueId(talentPointManagement1.getCandidateUniqueId());
			auditPoint.setUpdatePoint(talentPointManagement1.getDollar() * PointManagment.dollar);
			auditPoint.setTransactionType(talentPointManagement1.getTransactionType());
			auditPoint.setUpdateDate(new Date());
			auditManagementRepository.save(auditPoint);
			talentPointManagement1.setCurrentAvailablePoint(
					talentPointManagement1.getCurrentAvailablePoint() + auditPoint.getUpdatePoint());
			talentPointManagement1.setUpdateDate(new Date());
			return PointManagementRepository.save(talentPointManagement1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public TalentPointManagement removePoint(TalentPointManagement talentPointManagement1) {
		try {

			if ((talentPointManagement1.getCurrentAvailablePoint()) > (talentPointManagement1.getResume()
					* PointManagment.Rusume)) {
				System.out.println(talentPointManagement1.getCandidateUniqueId() + talentPointManagement1.getResume()
						+ talentPointManagement1.getTransactionType());
				TalentAuditPointManagement auditPoint = new TalentAuditPointManagement();
				auditPoint.setCandidateUniqueId(talentPointManagement1.getCandidateUniqueId());
				auditPoint.setRemovePoint(talentPointManagement1.getResume() * PointManagment.Rusume);
				auditPoint.setTransactionType(talentPointManagement1.getTransactionType());
				auditPoint.setUpdateDate(new Date());
				auditManagementRepository.save(auditPoint);
				talentPointManagement1.setCurrentAvailablePoint(
						talentPointManagement1.getCurrentAvailablePoint() - auditPoint.getRemovePoint());
				talentPointManagement1.setUpdateDate(new Date());
				return PointManagementRepository.save(talentPointManagement1);
			} else {
				System.out.println(
						talentPointManagement1.getCurrentAvailablePoint() + talentPointManagement1.getCandidateUniqueId()
								+ talentPointManagement1.getResume() + talentPointManagement1.getTransactionType());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public TalentPointManagement findByCandidateUniqueId(String candidateUniqueId) {
		return PointManagementRepository.findByCandidateUniqueId(candidateUniqueId);
	}
}
