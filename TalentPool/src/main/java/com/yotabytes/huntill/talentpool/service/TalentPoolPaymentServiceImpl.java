package com.yotabytes.huntill.talentpool.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.domain.TalentAuditPointManagment;
import com.yotabytes.huntill.talentpool.domain.TalentPointManagment;
import com.yotabytes.huntill.talentpool.repository.CandidateAuditManagmentRepository;
import com.yotabytes.huntill.talentpool.repository.TalentPointManagmentRepository;
import com.yotabytes.huntill.talentpool.service.TalentPoolPaymentService;
import com.yotabytes.huntill.talentpool.utils.PointManagment;

@Service
public class TalentPoolPaymentServiceImpl implements TalentPoolPaymentService {

	@Autowired
	public TalentPointManagmentRepository PointManagmentRepository;

	@Autowired
	public CandidateAuditManagmentRepository auditManagmentRepository;

	public TalentPointManagment savePoint(TalentPointManagment pointManagment) {

		try {
			TalentAuditPointManagment auditPoint = new TalentAuditPointManagment();
			auditPoint.setCandidateUniqueId(pointManagment.getCandidateUniqueId());
			auditPoint.setAddPoint(pointManagment.getDollar() * PointManagment.dollar);
			auditPoint.setTransactionType(pointManagment.getTransactionType());
			auditPoint.setCreatedDate(new Date());
			auditManagmentRepository.save(auditPoint);

			pointManagment.setCurrentAvailablePoint(auditPoint.getAddPoint());
			pointManagment.setCreatedDate(new Date());
			return PointManagmentRepository.save(pointManagment);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public TalentPointManagment updatePoint(TalentPointManagment talentPointManagment1) {
		try {
			TalentAuditPointManagment auditPoint = new TalentAuditPointManagment();
			auditPoint.setCandidateUniqueId(talentPointManagment1.getCandidateUniqueId());
			auditPoint.setUpdatePoint(talentPointManagment1.getDollar() * PointManagment.dollar);
			auditPoint.setTransactionType(talentPointManagment1.getTransactionType());
			auditPoint.setUpdateDate(new Date());
			auditManagmentRepository.save(auditPoint);
			talentPointManagment1.setCurrentAvailablePoint(
					talentPointManagment1.getCurrentAvailablePoint() + auditPoint.getUpdatePoint());
			talentPointManagment1.setUpdateDate(new Date());
			return PointManagmentRepository.save(talentPointManagment1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public TalentPointManagment removePoint(TalentPointManagment talentPointManagment1) {
		try {

			if((talentPointManagment1.getCurrentAvailablePoint())>(talentPointManagment1.getResume() * PointManagment.Rusume))
			{
				System.out.println(talentPointManagment1.getCandidateUniqueId()+talentPointManagment1.getResume()+talentPointManagment1.getTransactionType());
				TalentAuditPointManagment auditPoint = new TalentAuditPointManagment();
				auditPoint.setCandidateUniqueId(talentPointManagment1.getCandidateUniqueId());
				auditPoint.setRemovePoint(talentPointManagment1.getResume() * PointManagment.Rusume);
				auditPoint.setTransactionType(talentPointManagment1.getTransactionType());
				auditPoint.setUpdateDate(new Date());
				auditManagmentRepository.save(auditPoint);	
				talentPointManagment1.setCurrentAvailablePoint(
						talentPointManagment1.getCurrentAvailablePoint() - auditPoint.getRemovePoint());
				talentPointManagment1.setUpdateDate(new Date());
				return PointManagmentRepository.save(talentPointManagment1); 
			}else {
				System.out.println(talentPointManagment1.getCurrentAvailablePoint()+talentPointManagment1.getCandidateUniqueId()+talentPointManagment1.getResume()+talentPointManagment1.getTransactionType());
			}
			
		} catch (Exception e) { 
			System.out.println(e.getMessage());
		}
		return null;
	}

	public TalentPointManagment findByCandidateUniqueId(String candidateUniqueId) {
		return PointManagmentRepository.findByCandidateUniqueId(candidateUniqueId);
	}
}
