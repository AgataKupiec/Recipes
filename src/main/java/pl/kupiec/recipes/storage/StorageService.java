package pl.kupiec.recipes.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {
    
    void init();
    
    String store(MultipartFile file);

    Path load(String filename);
    
    Resource loadAsResource(String filename);

    String convertImage(String dir);
    
    void delete(String filePath);
    
}
