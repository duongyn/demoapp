package com.duonghai.shoppingonline.oauth2client.controller;

import com.duonghai.shoppingonline.dto.UserDTO;
import com.duonghai.shoppingonline.oauth2client.config.WelcomeClient;
import com.duonghai.shoppingonline.oauth2client.service.ClientUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WelcomeController {
	
	private final WelcomeClient welcomeClient;

	@Autowired
	private ClientUserService userService;

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

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		List<UserDTO> list = userService.findActiveUsers();
		String welcome = welcomeClient.getUsers();
		return ResponseEntity.ok(welcome+"<br>"+list);
	}

	@PostMapping("/users")
	public ResponseEntity<?> postUser(@RequestBody UserDTO dto) {
		UserDTO user = userService.saveUser(dto);
		String welcome = welcomeClient.addUser();
		return ResponseEntity.ok(welcome+"<br>"+user);
	}
}
