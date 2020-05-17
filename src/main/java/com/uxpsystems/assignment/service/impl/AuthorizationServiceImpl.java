package com.uxpsystems.assignment.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uxpsystems.assignment.exception.AuthorizationServiceException;
import com.uxpsystems.assignment.models.UserProfile;
import com.uxpsystems.assignment.models.UserProfileRequest;
import com.uxpsystems.assignment.service.AuthorizationService;
import com.uxpsystems.assignment.util.MessageProducer;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AuthorizationServiceImpl implements AuthorizationService {

	

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MessageProducer messageProducer;
	
	@Override
	public ResponseEntity<Object> handleProfileRequest(@Valid UserProfileRequest body, HttpMethod method,
			UserDetailsImpl userDetails) throws URISyntaxException {
//		
		log.info("UserProfileRequest: {}", body);

		Integer userId = userDetails.getUserId();
		UserProfile userProfile = null;
		if (body != null) {
			userProfile = convertToDto(body, userId);
		}

		switch (method) {
		case POST:
			validateRequest(userId, false);
			URI uri = new URI("http", null, "localhost", 8081, "/user/profiles", null, null);
			return restTemplate.exchange(uri, method, new HttpEntity<UserProfile>(userProfile), Object.class);
		case PUT:
			validateRequest(userId, true);
			messageProducer.sendUserProfileMessage(body, method, userId);
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		case DELETE:
			validateRequest(userId, true);
			messageProducer.sendUserProfileMessage(new UserProfileRequest(), method, userId);
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		case GET:
			validateRequest(userId, true);
			uri = new URI("http", null, "localhost", 8081, "/user/profiles/" + userId, null, null);
			return restTemplate.exchange(uri, method, new HttpEntity<UserProfile>(userProfile), Object.class);
		default:
			uri = new URI("http", null, "localhost", 8081, "/user/profiles/" + userId, null, null);
			return restTemplate.exchange(uri, method, new HttpEntity<UserProfile>(userProfile), Object.class);
		}

	}

	private void validateRequest(Integer userId, Boolean shouldExist) throws URISyntaxException {
		URI uri = new URI("http", null, "localhost", 8081, "/user/profiles/" + userId, null, null);
		ResponseEntity<Object> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, Object.class);
		if (exchange.getBody() == null && shouldExist) {
			throw new AuthorizationServiceException("Profile does not exist", "1", HttpStatus.NOT_FOUND);
		} else if (exchange.getBody() != null && !shouldExist) {
			throw new AuthorizationServiceException("Profile already exist", "2", HttpStatus.BAD_REQUEST);
		}
	}

	private UserProfile convertToDto(UserProfileRequest userProfileRequest, Integer userId) {
		return new UserProfile(userProfileRequest.getAddress(), userProfileRequest.getPhoneNumber(), userId);
	}

}
