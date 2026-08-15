package com.aijobmatcher.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aijobmatcher.dto.LoginRequest;
import com.aijobmatcher.dto.LoginResponse;
import com.aijobmatcher.dto.RegisterRequest;
import com.aijobmatcher.entity.User;
import com.aijobmatcher.repository.UserRepository;
import com.aijobmatcher.security.JwtService;
import com.aijobmatcher.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	public AuthServiceImpl(UserRepository userRepository, 
			PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager,
			JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
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
	
	@Override
	public LoginResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		String token = jwtService.generateToken(request.getEmail());
		return new LoginResponse(token);
	}
}
