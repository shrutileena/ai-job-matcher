package com.aijobmatcher.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.aijobmatcher.entity.Resume;
import com.aijobmatcher.entity.User;
import com.aijobmatcher.repository.ResumeRepository;
import com.aijobmatcher.repository.UserRepository;
import com.aijobmatcher.service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService {

	private final ResumeRepository resumeRepository;
	
	private final UserRepository userRepository;
	
	public ResumeServiceImpl(ResumeRepository resumeRepository,
			UserRepository userRepository) {
		this.resumeRepository = resumeRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Resume createResume(Resume resume, Authentication authentication) {
		String email = authentication.getName();
		
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		
		resume.setUser(user);
		return resumeRepository.save(resume);
	}
	
}
