package com.uxpsystems.assignment.util;

import org.springframework.http.HttpMethod;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import com.uxpsystems.assignment.models.UserProfileRequest;
import com.uxpsystems.assignment.streams.UserProfileStreams;

@Component
public class MessageProducer {

	private final UserProfileStreams userProfileStreams;

	public MessageProducer(UserProfileStreams userProfileStreams) {
		this.userProfileStreams = userProfileStreams;
	}

	@Async
	public void sendUserProfileMessage(UserProfileRequest body, HttpMethod method, Integer userId) {
		MessageChannel messageChannel = userProfileStreams.userProfilePublisher();
		messageChannel.send(
				MessageBuilder.withPayload(body).setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
						.setHeader("requestType", method.toString()).setHeader("userId", userId).build());

	}

}
