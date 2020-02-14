package com.yotabytes.huntill.talentpool.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity (name= "talent_candidate_personal_details")

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter

public class TalentCandidateInformation {

	@Id
	private String candidateUniqueId=UUID.randomUUID().toString().toUpperCase();
	 
	@NotEmpty(message = "Please enter first name")
	private String firstName;
	@NotEmpty(message = "middle name is required")
	private String middleName;
	@NotEmpty(message = "last name is required")
	private String lastName;
	@Size(max = 20, min = 5)
	@NotEmpty(message = "user name is required")
	private String userName; 
	//@Size(max = 20, min = 5)
	@NotEmpty(message = "Please enter password")
	private String password;
	@NotNull(message = "Please enter contactNumber")
	/*@Size(max = 12, min = 10)*/
	private Long contactNumber;
	@Email
	@NotEmpty(message = "Please enter email")
	private String emailId;
	private String alternateEmailId;
	
	private String gender;
	
	private String role;
	private String isVerify="N";
	private String isActive="Y";
	private String isProfileComplete="N";
	
	private String createdBy;
	@CreationTimestamp
	private Date createdDate;
	private String updateBy;
	@CreationTimestamp
	private Date updateDate;
	
	 @OneToMany(cascade=CascadeType.ALL, mappedBy="talentCandidateInformation")
	private List<PasswordResetToken> passwordResetToken;
	
	/*
	 * @OneToOne(mappedBy = "talentCandidateInformation") private PasswordResetToken
	 * passwordResetToken;
	 */
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="candidateUniqueId")
	private List<TalentCandidateProjectDetails> projectDetails;
	
	

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="candidateUniqueId")
	private List<TalentEducationDetails> educationDetails;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="candidateUniqueId")
	private List<TalentCandidateAddress> address;
	
	
	  @OneToMany(cascade=CascadeType.ALL)
	  @JoinColumn(name="candidate_unique_id") 
	  private List<TalentCandidateProgrammerSkills> programmerSkills;
	 
	
	
	
	
	public String getCandidateUniqueId() {
		return candidateUniqueId;
	}
	public void setCandidateUniqueId(String candidateUniqueId) {
		this.candidateUniqueId = candidateUniqueId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAlternateEmailId() {
		return alternateEmailId;
	}
	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}
	
	/*public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}*/
	

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	
	
	public String getIsVerify() {
		return isVerify;
	}
	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsProfileComplete() {
		return isProfileComplete;
	}
	public void setIsProfileComplete(String isProfileComplete) {
		this.isProfileComplete = isProfileComplete;
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
