package com.uxpsystems.assignment.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfile {
	
	private String address;
	
	private String phoneNumber;
	
	private Integer userId;

}

