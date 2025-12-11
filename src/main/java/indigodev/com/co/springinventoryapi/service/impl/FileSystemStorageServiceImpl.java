package indigodev.com.co.springinventoryapi.service.impl;

import indigodev.com.co.springinventoryapi.exception.ResourceNotFoundException;
import indigodev.com.co.springinventoryapi.exception.StorageException;
import indigodev.com.co.springinventoryapi.service.StorageService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileSystemStorageServiceImpl implements StorageService {

    @Value("${application.storage.upload-dir}")
    private  String uploadDir;
    private Path rootLocation;

    @Override
    @PostConstruct
    public void init() {
        try {
            this.rootLocation = Paths.get(uploadDir);
            Files.createDirectories(rootLocation);
        }catch (IOException e){
            throw new StorageException("Could not initialize storage service",e);
        }
    }

    @Override
    public String store(MultipartFile file) {

        try {
            if (file.isEmpty()) throw new StorageException("Failed to store empty file");
            String fileName = file.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf('.'));
            String newFileName = UUID.randomUUID().toString() + "." + extension;

            Path filePath = this.rootLocation.resolve(Paths.get(newFileName))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return newFileName;
        }catch (IOException e){
            throw new StorageException("Failed to store file",e);
        }
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path filePath = this.rootLocation.resolve(Paths.get(fileName));
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }else  {
                throw new ResourceNotFoundException("Resource not found: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException("could not read file: " +  fileName, e);
        }
    }
}
