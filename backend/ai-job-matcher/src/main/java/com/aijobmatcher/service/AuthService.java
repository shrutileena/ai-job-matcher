package com.aijobmatcher.service;

import com.aijobmatcher.dto.LoginRequest;
import com.aijobmatcher.dto.LoginResponse;
import com.aijobmatcher.dto.RegisterRequest;

public interface AuthService {

	void register(RegisterRequest request);
	
	LoginResponse login(LoginRequest request);
}
