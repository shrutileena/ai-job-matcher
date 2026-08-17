package com.aijobmatcher.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aijobmatcher.entity.Resume;
import com.aijobmatcher.service.ResumeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

	private final ResumeService resumeService;
	
	public ResumeController(ResumeService resumeService) {
		this.resumeService = resumeService;
	}
	
	@PostMapping
	public ResponseEntity<Resume> createResume(@Valid @RequestBody Resume resume, Authentication authentication) {
		Resume savedResume = resumeService.createResume(resume, authentication);
		return ResponseEntity.ok(savedResume);
	}
}
