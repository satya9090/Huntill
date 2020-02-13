package com.yotabytes.huntill.talentpool.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yotabytes.huntill.talentpool.domain.TalentCandidateInformation;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserInfoService userInfoDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		TalentCandidateInformation userInfo = userInfoDAO.getUserInfoByUserName(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
		System.out.println(userInfo.getUserName() +"......."+Arrays.asList(authority));
		return new User(userInfo.getUserName(), userInfo.getPassword(), Arrays.asList(authority));
	}
}
