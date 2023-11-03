package com.duonghai.shoppingonline.oauth2resourceserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
		return "Welcome to Spring Resource Server 01! - " + (userDetails.getName()) + " - " + userDetails.getAuthorities();
	}

	@GetMapping("/loginSocial")
	public String ssoLogin() {
		Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
		return "Welcome to Spring SSO! - " + userDetails.getName() + " - " + userDetails.getAuthorities();
	}

}
