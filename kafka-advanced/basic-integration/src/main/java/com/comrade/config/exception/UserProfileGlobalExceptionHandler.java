package com.comrade.config.exception;

import com.comrade.modal.CustomErrorResponse;
import com.comrade.modal.NoRecardsFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class UserProfileGlobalExceptionHandler {

	@ExceptionHandler(value = NoRecardsFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleGenericNotFoundException(NoRecardsFoundException error){
		return new ResponseEntity<CustomErrorResponse>(new CustomErrorResponse()
															.setErrorCode(HttpStatus.NOT_FOUND.toString())
															.setErrorMsg(error.getMessage())
															.setStatus((HttpStatus.NOT_FOUND.value()))
															.setTimestamp(new Date()), HttpStatus.NOT_FOUND);
	}
}
