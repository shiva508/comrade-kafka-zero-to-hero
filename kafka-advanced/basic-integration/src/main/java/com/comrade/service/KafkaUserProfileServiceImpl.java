package com.comrade.service;


import com.comrade.entity.KafkaUserProfileEntity;
import com.comrade.modal.CommonResponse;
import com.comrade.repository.KafkaUserProfileRepository;
import com.comrade.util.UserProfileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaUserProfileServiceImpl implements KafkaUserProfileService {
	
	@Autowired
	private KafkaUserProfileRepository kafkaUserprofileRepository;
	
	@Autowired
	private UserProfileUtil userProfileUtil;

	@Override
	public KafkaUserProfileEntity saveUserProfile(KafkaUserProfileEntity userprofile) {
		
		return kafkaUserprofileRepository.save(userprofile);
	}

	@Override
	public List<KafkaUserProfileEntity> getUserProfiles() {
		
		return kafkaUserprofileRepository.findAll();
	}

	@Override
	public KafkaUserProfileEntity getUserProfileByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse deleteUserProfileByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KafkaUserProfileEntity updateUserprofile(KafkaUserProfileEntity userprofile) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
