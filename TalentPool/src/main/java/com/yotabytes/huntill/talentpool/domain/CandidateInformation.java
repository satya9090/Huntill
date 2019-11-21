package com.yotabytes.huntill.talentpool.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity (name= "talent_candidate_personal_details")

@EqualsAndHashCode
@ToString
@Getter
@Setter
/* @Table( name="talent_candidate_information" ) */
public class CandidateInformation {
	
	@Id
	private int candidate_id;
	private String candidateUniqeId=UUID.randomUUID().toString().toUpperCase();
	private String first_name;
	private String middle_name;
	private String last_name;
	private String userId; 
	private String password;
	private String location;
	private Long contact_number;
	private String emailId;
	private String alternateEmail_id;
	private String institute_name;
	private String passing_year;
	private String grade;
	private String gender;
	private String isVerify;
	

}
