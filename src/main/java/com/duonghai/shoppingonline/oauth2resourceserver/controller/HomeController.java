package com.duonghai.shoppingonline.oauth2resourceserver.controller;

import com.duonghai.shoppingonline.oauth2authorizationserver.model.UserDetailsImpl;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();

		return "Welcome Home! - " + userDetails.toString();
	}

}
