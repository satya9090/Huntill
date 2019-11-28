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
	
}
