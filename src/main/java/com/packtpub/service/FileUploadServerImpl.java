package com.packtpub.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServerImpl implements FileUploadService {
	private Path location;

	public FileUploadServerImpl() throws IOException {
		location = Paths.get("D:\\PERSONAL DOCUMENT\\experimentation\\test");
		Files.createDirectories(location); 
	} 
	
	@Override
	public void uploadFile(MultipartFile file) throws IOException {
		
		//Clean the file path 
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.isEmpty()) {
			throw new IOException("File is empty " + fileName);
		} try {
			
			//Uploading by copying the file from the client to the server 
			Files.copy(file.getInputStream(), 
					this.location.resolve(fileName),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("File Upload Error: " + fileName);
		}		
	}	
}
