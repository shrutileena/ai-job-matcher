package com.aijobmatcher.service;

import org.springframework.security.core.Authentication;

import com.aijobmatcher.entity.Resume;

public interface ResumeService {

	Resume createResume(Resume resume, Authentication authentication);
}
