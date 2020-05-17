package com.uxpsystems.assignment.controller;

import static org.mockito.ArgumentMatchers.any;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.uxpsystems.assignment.models.JwtResponse;
import com.uxpsystems.assignment.service.AuthorizationService;
import com.uxpsystems.assignment.util.JwtUtils;
import com.uxpsystems.assignment.util.MockData;

@SpringBootTest
public class AuthorizationControllerTest {
	
	@Mock
	RestTemplate restTemplate;
	
	@Mock
	AuthenticationManager authenticationManager;
	
	@Mock
	PasswordEncoder encoder;

	@Mock
	JwtUtils jwtUtils;
	
	@Mock
	AuthorizationService authorizationService;
	
	@Mock
	Authentication authentication;
	
	@InjectMocks
	AuthorizationController authorizationController;

	@Test
	public void testAuthenticate() {
		Mockito.when(authenticationManager.authenticate(any())).thenReturn(authentication);
		Mockito.when(authentication.getPrincipal()).thenReturn(MockData.mockUserDetailsImpl());
		Mockito.when(jwtUtils.generateJwtToken(any())).thenReturn("jwtToken");
		ResponseEntity<JwtResponse> authorize = authorizationController.authorize(MockData.mockBasicAuthorization());
		Assertions.assertEquals("vishal", authorize.getBody().getUserName());
	}

	
	@Test
	public void testProfile() throws URISyntaxException {
		Mockito.when(authorizationService.handleProfileRequest(any(), any(), any())).thenReturn(new ResponseEntity<Object>(HttpStatus.NO_CONTENT));
		ResponseEntity<Object> profile = authorizationController.profile(null, HttpMethod.DELETE, null, null);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, profile.getStatusCode());
	}
	
}
