package com.yotabytes.huntill.talentpool.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity (name= "talent_question_answers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter

public class TalentQuestionAnswers {

	@Id
	private Integer id;
	private Integer question_id;
	private String answer_description;
	private String answer_option;
	
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "question_id", nullable = false)
//	private TalentQuestion talentQuestion;
}
