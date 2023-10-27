package com.comrade.controller;


import com.comrade.entity.UserProfileEntity;
import com.comrade.modal.CommonResponse;
import com.comrade.service.KafkaUserProfileService;
import com.comrade.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/userprofile")
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private KafkaUserProfileService kafkaUserProfileService;
	List<String> data = new ArrayList<String>();
	UserProfileEntity  userProfile=null;

	@PostMapping("/save")
	public UserProfileEntity saveUserProfile(@RequestBody UserProfileEntity userprofile) {
		kafkaTemplate.send("studentpool", userprofile);
		return userProfileService.saveUserProfile(userprofile);
	}

	@GetMapping("/userprofiles")
	public List<UserProfileEntity> getUserProfiles() {
		return userProfileService.getUserProfiles();
	}

	@GetMapping("/getuserprofile/{userId}")
	public UserProfileEntity getUserProfileByUserId(@PathVariable("userId") Integer userId) {
		return userProfileService.getUserProfileByUserId(userId);
	}

	@DeleteMapping("/deleteuserprofile/{userId}")
	public CommonResponse deleteUserProfileByUserId(@PathVariable("userId") Integer userId) {
		return userProfileService.deleteUserProfileByUserId(userId);
	}

	@PutMapping("/update")
	public UserProfileEntity updateUserprofile(@RequestBody UserProfileEntity userprofile) {
		return userProfileService.updateUserprofile(userprofile);
	}

	@GetMapping("/publish/{data}")
	public String publishMessage(@PathVariable("data") String data) {
		kafkaTemplate.send("studentpool", "HI " + data);
		return "PUBLISHED";
	}

	@KafkaListener(groupId = "studentpool-2", topics = "studentpool", containerFactory = "userConcurrentKafkaListenerContainerFactory")
	public UserProfileEntity consumer(UserProfileEntity userprofileOp) {
		return userprofileOp;
	}

	@KafkaListener(groupId = "studentpool-1", topics = "studentpool", containerFactory = "concurrentKafkaListenerContainerFactory")
	public List<String> consumerUser(String opdata) {
		data.add(opdata);
		return data;
	}
	
	@GetMapping("/stringdata")
	public List<String> getStringData(){
		return data;
	}
	@GetMapping("/userkafka")
	public UserProfileEntity getStringDataUser(){
		return userProfile;
	}
	
}
