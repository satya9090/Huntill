package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yotabytes.huntill.talentpool.domain.TalentQuestion;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionOption;


@Repository
public interface TalentQuestionRepository extends JpaRepository<TalentQuestionOption, Long> {

}