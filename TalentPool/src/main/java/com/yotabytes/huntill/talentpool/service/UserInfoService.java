package com.yotabytes.huntill.talentpool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yotabytes.huntill.talentpool.domain.TalentCandidateInformation;
import com.yotabytes.huntill.talentpool.domain.UserInfo;
import com.yotabytes.huntill.talentpool.repository.CandidateInformationRepository;
import com.yotabytes.huntill.talentpool.repository.UserDetailsRepository;

@Repository
@Transactional
public class UserInfoService {

	@Autowired
	private UserDetailsRepository userDatailsRepository;
	@Autowired
	private CandidateInformationRepository candidateInformationRepository;

	public TalentCandidateInformation getUserInfoByUserName(String userName) {
		short enabled = 1;
		return candidateInformationRepository.findByUserName(userName);
	}

	public List<UserInfo> getAllActiveUserInfo() {
		return userDatailsRepository.findAllByEnabled((short) 1);
	}

	public UserInfo getUserInfoById(Integer id) {
		return userDatailsRepository.findById(id);
	}

	public UserInfo addUser(UserInfo userInfo) {
		userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
		return userDatailsRepository.save(userInfo);
	}

	public UserInfo updateUser(Integer id, UserInfo userRecord) {
		UserInfo userInfo = userDatailsRepository.findById(id);
		userInfo.setUserName(userRecord.getUserName());
		userInfo.setPassword(userRecord.getPassword());
		userInfo.setRole(userRecord.getRole());
		userInfo.setEnabled(userRecord.getEnabled());
		return userDatailsRepository.save(userInfo);
	}

	public void deleteUser(Integer id) {
		userDatailsRepository.deleteById(id);
	}

	public UserInfo updatePassword(Integer id, UserInfo userRecord) {
		UserInfo userInfo = userDatailsRepository.findById(id);
		userInfo.setPassword(userRecord.getPassword());
		return userDatailsRepository.save(userInfo);
	}

	public UserInfo updateRole(Integer id, UserInfo userRecord) {
		UserInfo userInfo = userDatailsRepository.findById(id);
		userInfo.setRole(userRecord.getRole());
		return userDatailsRepository.save(userInfo);
	}
}