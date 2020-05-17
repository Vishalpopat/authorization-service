package com.uxpsystems.assignment.controller;

import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.uxpsystems.assignment.models.JwtResponse;
import com.uxpsystems.assignment.models.UserProfileRequest;
import com.uxpsystems.assignment.service.AuthorizationService;
import com.uxpsystems.assignment.service.impl.UserDetailsImpl;
import com.uxpsystems.assignment.util.JwtUtils;

@RestController
public class AuthorizationController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthorizationService authorizationService;
	
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JwtResponse> authorize(@RequestHeader String authorization){

		String decoded = new String(Base64.getDecoder().decode(authorization.split(" ")[1]));
		String[] split = decoded.split(":");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(split[0], split[1]));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		return ResponseEntity.ok(new JwtResponse(jwt,  
												 userDetails.getUsername()));
	}
	
	
	
	@RequestMapping("/profile")
	@ResponseBody
	public ResponseEntity<Object> profile(@RequestBody(required = false) @Valid UserProfileRequest body, HttpMethod method, HttpServletRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) throws URISyntaxException
	{
		
		return authorizationService.handleProfileRequest(body, method, userDetails);
	}



}
