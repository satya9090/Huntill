package com.yotabytes.huntill.talentpool.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter

public class Talent_Question_answer {

	private int id;
	private String candidate_Unique_id;
	private Integer question_id;
	private String question_Answer;
	
	
}
