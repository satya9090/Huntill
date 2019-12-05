package com.yotabytes.huntill.talentpool.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yotabytes.huntill.talentpool.domain.SearchCandidate;

@Repository
public interface SearchCandidateRepository  extends JpaRepository<SearchCandidate, String>{

	//ArrayList<SearchCandidate> findByPassingYear(String passingYear);

}
