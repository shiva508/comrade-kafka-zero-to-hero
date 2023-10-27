package com.comrade.service;

import com.comrade.constants.UserProfileConstants;
import com.comrade.entity.UserProfileEntity;
import com.comrade.modal.CommonResponse;
import com.comrade.modal.NoRecardsFoundException;
import com.comrade.repository.UserProfileRepository;
import com.comrade.util.UserProfileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDerviceServiceImpl implements UserProfileService {
	
	@Autowired
	private UserProfileRepository userprofileRepository;
	
	@Autowired
	private UserProfileUtil userProfileUtil;

	@Override
	@Transactional
	public UserProfileEntity saveUserProfile(UserProfileEntity userprofile) {
		return userprofileRepository.save(userprofile);
	}

	@Override
	@Transactional
	public List<UserProfileEntity> getUserProfiles() {
		List<UserProfileEntity> userprofiles=userprofileRepository.findAll();
		if(null ==userprofiles || userprofiles.size()==0) {
			throw new NoRecardsFoundException(UserProfileConstants.NO_RECORDS_FOUND_MESSAGE);
		}
		return userprofiles;
	}

	@Override
	@Transactional
	public UserProfileEntity getUserProfileByUserId(Integer userId) {
		return userprofileRepository.getById(userId);
	}

	@Override
	@Transactional
	public CommonResponse deleteUserProfileByUserId(Integer userId) {
		userprofileRepository.deleteById(userId);
		return userProfileUtil.generateCustomResponse(UserProfileConstants.DELETE_MESSAGE+userId);
	}

	@Override
	@Transactional
	public UserProfileEntity updateUserprofile(UserProfileEntity userprofile) {
		return userprofileRepository.saveAndFlush(userprofile);
	}

}
