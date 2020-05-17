package com.uxpsystems.assignment.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileMsg {

	private long timestamp;
	private String message;

}
