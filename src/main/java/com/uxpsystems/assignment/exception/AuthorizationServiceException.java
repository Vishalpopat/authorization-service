package com.uxpsystems.assignment.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizationServiceException extends RuntimeException {
	
	private final String errMsg;
	private final String errorCode;
	private final HttpStatus httpStatus;
	

}
