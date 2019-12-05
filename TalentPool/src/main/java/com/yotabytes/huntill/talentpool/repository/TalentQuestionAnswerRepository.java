package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yotabytes.huntill.talentpool.domain.TalentQuestion;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionAnswer;


public interface TalentQuestionAnswerRepository extends JpaRepository<TalentQuestionAnswer, Integer>{

}
