package com.yotabytes.huntill.talentpool.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity(name = "talent_question_answer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class TalentQuestionAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String candidateUniqueid;
	private Integer questionId;
	private String questionAnswer;
	private String answerValue;
	
}
