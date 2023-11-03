package com.duonghai.shoppingonline.oauth2client.controller;

import com.duonghai.shoppingonline.oauth2client.config.WelcomeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WelcomeController {
	
	private final WelcomeClient welcomeClient;

	@GetMapping("/")
	public String index(Authentication authentication) {
		String welcome = welcomeClient.getWelcome();
		return "<h1> Welcome home: " + authentication.getName() + " - " + authentication.getAuthorities() + "</h1><h2>" + welcome + "</h2>";
	}

	@GetMapping("/social")
	public String success(Authentication authentication) {
		String welcome = welcomeClient.getSuccess();
		return "<h1> Welcome login: " + authentication.getName() + " - " + authentication.getAuthorities() + "</h1><h2>" + welcome + "</h2>";
	}

	@GetMapping("/errors/access-denied")
	public String denied() {
		String welcome = welcomeClient.getAccessDenied();
		return "<h1> Access Denied Page </h1><h2>" + welcome + "</h2>";
	}
}
