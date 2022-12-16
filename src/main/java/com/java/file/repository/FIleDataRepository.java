package com.java.file.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.file.entity.FileData;

public interface FIleDataRepository extends JpaRepository<FileData,Long> {

	Optional<FileData> findByName(String fileName);
	
	
}
