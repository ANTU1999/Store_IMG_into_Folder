package com.java.file.serive;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.file.entity.FileData;
import com.java.file.repository.FIleDataRepository;

@Service
public class StorageService {

	private FIleDataRepository fileDataRepository;

	public StorageService(FIleDataRepository fileDataRepository) {
		super();
		this.fileDataRepository = fileDataRepository;
	}
	
	private final String FOLDER_IMAGE_PATH="D:/Users/anmanna/Desktop/StoreIMG/";
	
	
	public String uploadImageToFileSystem(MultipartFile file) throws IllegalStateException, IOException {
		String filePath1=FOLDER_IMAGE_PATH+file.getOriginalFilename();
		FileData fileData = fileDataRepository.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filePath1).build());
				
				
				
		file.transferTo(new File(filePath1 ));
		
		if(fileData != null) {
			return "file uploaded successfully : "+ filePath1;
			
		}
		return null;
	}
	
	
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException{
		Optional<FileData> fileData = fileDataRepository.findByName(fileName);
		String filePath= fileData.get().getFilePath();
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		return images;
	}
	
	
		
	}
	
	

