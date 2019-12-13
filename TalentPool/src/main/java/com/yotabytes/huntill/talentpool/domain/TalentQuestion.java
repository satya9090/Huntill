package com.yotabytes.huntill.talentpool.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity (name= "talent_questions")
@EqualsAndHashCode
@ToString
@Getter
@Setter

public class TalentQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer questionId;
	private String question_name;
	private String is_active;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="questionId")
	private Set<TalentQuestionOption> talentQuestionOption;
	private String createdBy;
	private String createdDate;
	private String updateBy;
	private String updateDate;
	
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestion_name() {
		return question_name;
	}
	public void setQuestion_name(String question_name) {
		this.question_name = question_name;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public Set<TalentQuestionOption> getTalentQuestionOption() {
		return talentQuestionOption;
	}
	public void setTalentQuestionOption(Set<TalentQuestionOption> talentQuestionOption) {
		this.talentQuestionOption = talentQuestionOption;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
}
