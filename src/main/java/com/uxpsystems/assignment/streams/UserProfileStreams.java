package com.uxpsystems.assignment.streams;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserProfileStreams {

	String OUTPUT = "user-profile-out";
	
	@Output(OUTPUT)
	MessageChannel userProfilePublisher();
	
}
