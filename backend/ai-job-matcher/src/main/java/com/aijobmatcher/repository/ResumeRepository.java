package com.aijobmatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aijobmatcher.entity.Resume;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
	
	List<Resume> findByUserId(Long userId);
}
