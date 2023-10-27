package com.comrade.service;



import com.comrade.entity.KafkaUserProfileEntity;
import com.comrade.modal.CommonResponse;

import java.util.List;

public interface KafkaUserProfileService {
	public KafkaUserProfileEntity saveUserProfile(KafkaUserProfileEntity kafkaUserProfileEntity);

	public List<KafkaUserProfileEntity> getUserProfiles();

	public KafkaUserProfileEntity getUserProfileByUserId(Integer userId);

	public CommonResponse deleteUserProfileByUserId(Integer userId);

	public KafkaUserProfileEntity updateUserprofile(KafkaUserProfileEntity userprofile);
}
