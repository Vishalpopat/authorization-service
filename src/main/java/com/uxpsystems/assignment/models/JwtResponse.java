package com.uxpsystems.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

	String jwtToken;
	String userName;
	
}
