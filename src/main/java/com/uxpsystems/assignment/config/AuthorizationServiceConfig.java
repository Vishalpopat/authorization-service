package com.uxpsystems.assignment.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import com.uxpsystems.assignment.streams.UserProfileStreams;

@Configuration
@EnableAsync
@EnableBinding(UserProfileStreams.class)
public class AuthorizationServiceConfig {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
