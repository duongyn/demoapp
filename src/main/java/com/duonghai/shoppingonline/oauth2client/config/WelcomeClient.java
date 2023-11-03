package com.duonghai.shoppingonline.oauth2client.config;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


@HttpExchange("http://localhost:8090")
public interface WelcomeClient {

	@GetExchange("/")
	String getWelcome();

	@GetExchange("/social")
	String getSuccess();

	@GetExchange("/errors/access-denied")
	String getAccessDenied();

}
