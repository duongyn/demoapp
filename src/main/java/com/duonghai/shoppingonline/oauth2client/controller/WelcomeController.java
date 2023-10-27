package com.duonghai.shoppingonline.oauth2client.controller;

import com.duonghai.shoppingonline.oauth2client.config.WelcomeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WelcomeController {
	
	private final WelcomeClient welcomeClient;
	
	@GetMapping("/")
	public String welcome() {
		
		String welcome = welcomeClient.getWelcome();			
		return "<h1>" +  welcome + "</h1>";
	}
}
