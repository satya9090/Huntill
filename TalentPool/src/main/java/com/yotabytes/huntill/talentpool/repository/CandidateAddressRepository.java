package com.yotabytes.huntill.talentpool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yotabytes.huntill.talentpool.domain.TalentCandidateAddress;
@Repository
public interface CandidateAddressRepository extends CrudRepository<TalentCandidateAddress, String>{

	TalentCandidateAddress findBycandidateUniqeId(String candidateUniqeId);

}
