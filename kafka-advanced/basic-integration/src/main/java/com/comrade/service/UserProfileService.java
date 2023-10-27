package com.comrade.service;

import com.comrade.entity.UserProfileEntity;
import com.comrade.modal.CommonResponse;
import java.util.List;

public interface UserProfileService {
	public UserProfileEntity saveUserProfile(UserProfileEntity userprofile);

	public List<UserProfileEntity> getUserProfiles();

	public UserProfileEntity getUserProfileByUserId(Integer userId);

	public CommonResponse deleteUserProfileByUserId(Integer userId);

	public UserProfileEntity updateUserprofile(UserProfileEntity userprofile);
}
