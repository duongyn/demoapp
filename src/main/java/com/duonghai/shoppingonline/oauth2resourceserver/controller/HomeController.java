package com.duonghai.shoppingonline.oauth2resourceserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
		return "Welcome to Spring Resource Server 01! - " + (userDetails.getName()) + " - " + userDetails.getAuthorities();
	}

	@GetMapping("/social")
	public String ssoLogin() {
		Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
		return "Welcome to Spring SSO! - " + userDetails.getName() + " - " + userDetails.getAuthorities();
	}

	@GetMapping("/errors/access-denied")
	public String denied() {
		Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
		return "Access denied - " + userDetails.getName() + " is not allowed for this page!";
	}

	@GetMapping("/users")
	public String getUsers() {
		return "List Users: ";
	}

	@PostMapping("/users")
	public String addUser() {
		return "Created User: ";
	}
}
