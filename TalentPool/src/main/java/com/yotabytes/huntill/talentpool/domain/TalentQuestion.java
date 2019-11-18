package com.yotabytes.huntill.talentpool.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

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
	private Integer question_id;
	private String question_name;
	private String is_active;
	
}
