package com.yotabytes.huntill.talentpool.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity (name= "talent_candidate_experience_details")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter


public class Talent_candidate_experience {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String candidateUniqueId;
	private String project_name;
	private Date start_date;
	private Date end_date;
	private String Technology_used;
	private String description;
	
}
