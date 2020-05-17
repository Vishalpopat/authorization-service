package com.uxpsystems.assignment.service;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.uxpsystems.assignment.models.UserProfileRequest;
import com.uxpsystems.assignment.service.impl.UserDetailsImpl;

public interface AuthorizationService {

	ResponseEntity<Object> handleProfileRequest(@Valid UserProfileRequest body, HttpMethod method, UserDetailsImpl userDetails) throws URISyntaxException;
}
