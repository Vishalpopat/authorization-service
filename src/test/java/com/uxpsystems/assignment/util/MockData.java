package com.uxpsystems.assignment.util;

import com.uxpsystems.assignment.service.impl.UserDetailsImpl;

public class MockData {

	public static String mockBasicAuthorization() {
		return "Basic VmlzaGFsOjEyMzQ1Njc4";
	}

	public static UserDetailsImpl mockUserDetailsImpl() {
		return new UserDetailsImpl(1, "vishal", "12345678");
	}


}
