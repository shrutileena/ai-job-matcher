package com.aijobmatcher.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aijobmatcher.dto.RegisterRequest;
import com.aijobmatcher.entity.User;
import com.aijobmatcher.repository.UserRepository;
import com.aijobmatcher.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	public void register(RegisterRequest request) {
		
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new IllegalArgumentException("Email already exists");
		}
		
		User user = new User();
		
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		
		// encrypt password before saving using Bcrypt
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		userRepository.save(user);
	}
}
