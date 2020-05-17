package com.uxpsystems.assignment.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.uxpsystems.assignment.models.ApiError;

import lombok.extern.log4j.Log4j2;

/**
 * Implement global exception handling behavior. This simplifies controller
 * code; it can just throw an appropriate exception and the appropriate error
 * response is generated.
 */
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(AuthorizationServiceException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(final AuthorizationServiceException userProfileServiceExeception){
		
		ApiError apiError = new ApiError(userProfileServiceExeception.getHttpStatus(), userProfileServiceExeception.getErrMsg(), MDC.get("X-B3-TraceId"), userProfileServiceExeception.getErrorCode(), null);
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(null, apiError, headers, apiError.getStatus(), null);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), MDC.get("X-B3-TraceId"), null, errors);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

}
