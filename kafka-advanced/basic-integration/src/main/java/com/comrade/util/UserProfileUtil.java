package com.comrade.util;


import com.comrade.modal.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserProfileUtil {

	public CommonResponse generateCustomResponse(String message) {
		return new CommonResponse().setMessage(message).setTimeStamp(new Date());
	}

}
