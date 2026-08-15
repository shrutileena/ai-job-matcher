package com.aijobmatcher.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@GetMapping("/public")
	public String publicApi() {
		return "Public API is working";
	}
	
	@GetMapping("/protected")
	public String protectedApi(Authentication authentication) {
		return "JWT authentication successful! Logged in as: " + authentication.getName();
	}
}
