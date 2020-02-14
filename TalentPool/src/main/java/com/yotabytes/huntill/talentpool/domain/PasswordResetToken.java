package com.yotabytes.huntill.talentpool.domain;

import javax.persistence.*;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class PasswordResetToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false, unique = true)
	private String token;

	@Column(nullable = false, updatable = false)
	private String candidateUniqueId;

	@ManyToOne(fetch = FetchType.LAZY)
	private TalentCandidateInformation talentCandidateInformation;

	/*
	 * @OneToOne(targetEntity = TalentCandidateInformation.class, fetch =
	 * FetchType.EAGER)
	 * 
	 * @JoinColumn(nullable = false, name = "candidate_unique_id") private
	 * TalentCandidateInformation talentCandidateInformation;
	 */

	@Column(nullable = false)
	private Date expiryDate;

	@Column(columnDefinition = "varchar(255) default 'N'")
	private String isUpdate;

	/*
	 * public TalentCandidateInformation getTalentCandidateInformation() { return
	 * talentCandidateInformation; }
	 * 
	 * public void setTalentCandidateInformation(TalentCandidateInformation
	 * talentCandidateInformation) { this.talentCandidateInformation =
	 * talentCandidateInformation; }
	 */

	/*
	 * public String getCandidateUniqueId() { return candidateUniqueId; }
	 * 
	 * public void setCandidateUniqueId(String candidateUniqueId) {
	 * this.candidateUniqueId = candidateUniqueId; }
	 */

	public void setId(long id) {
		this.id = id;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Long getId() {
		return id;
	}

	/*
	 * public User getUser() { return user; }
	 * 
	 * public void setUser(User user) { this.user = user; }
	 */

	public String getCandidateUniqueId() {
		return candidateUniqueId;
	}

	public void setCandidateUniqueId(String candidateUniqueId) {
		this.candidateUniqueId = candidateUniqueId;
	}

	public TalentCandidateInformation getTalentCandidateInformation() {
		return talentCandidateInformation;
	}

	public void setTalentCandidateInformation(TalentCandidateInformation talentCandidateInformation) {
		this.talentCandidateInformation = talentCandidateInformation;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setExpiryDate(int minutes) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		this.expiryDate = now.getTime();
	}

	public boolean isExpired() {
		return new Date().after(this.expiryDate);
	}
}
