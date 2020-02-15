package com.yotabytes.huntill.talentpool.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "talent_Audit_PointManagement")

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class TalentAuditPointManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int auditPointId;
	private String candidateUniqueId;
	private int addPoint;
	private int updatePoint;
	private int removePoint;
	private String transactionType;
	private String createdBy;
	private Date createdDate;
	private String updateBy;
	private Date updateDate;

	public int getAuditPointId() {
		return auditPointId;
	}

	public void setAuditPointId(int auditPointId) {
		this.auditPointId = auditPointId;
	}

	public String getCandidateUniqueId() {
		return candidateUniqueId;
	}

	public void setCandidateUniqueId(String candidateUniqueId) {
		this.candidateUniqueId = candidateUniqueId;
	}

	public int getAddPoint() {
		return addPoint;
	}

	public void setAddPoint(int addPoint) {
		this.addPoint = addPoint;
	}

	public int getUpdatePoint() {
		return updatePoint;
	}

	public void setUpdatePoint(int updatePoint) {
		this.updatePoint = updatePoint;
	}

	public int getRemovePoint() {
		return removePoint;
	}

	public void setRemovePoint(int removePoint) {
		this.removePoint = removePoint;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
