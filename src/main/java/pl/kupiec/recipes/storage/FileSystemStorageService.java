package pl.kupiec.recipes.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Base64;

@Service
public class FileSystemStorageService implements StorageService {
    
    private final Path rootLocation;
    
    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }
    
    @Override
    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        LocalDate date = LocalDate.now();
        String dateFilePath = "/" + date.getYear() + "/" + date.getMonthValue() + "/" + date.getDayOfMonth();
        Path fullFilePath = Path.of("/" + rootLocation + dateFilePath);
        try {
            Files.createDirectories(fullFilePath);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
        
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, fullFilePath.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        return dateFilePath + "/" + filename;
    }
    
    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }
    
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(this.rootLocation + filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException(
                        "Could not read file: " + filename);
                
            }
        } catch (MalformedURLException e) {
            throw new StorageException("Could not read file: " + filename, e);
        }
    }
    
    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
    
    @Override
    public String convertImage(String imageDir) {
        String fileName = this.rootLocation + "/" + imageDir;
        byte[] fileContent = new byte[0];
        try {
            fileContent = org.apache.commons.io.FileUtils.readFileToByteArray(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(fileContent);
    }
    
    @Override
    public void delete(String filePath) {
        Path fullFilePath = Path.of("/" + rootLocation + filePath);
        if (Files.exists(fullFilePath)) {
            try {
                Files.delete(fullFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
