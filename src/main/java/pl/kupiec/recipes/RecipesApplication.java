package pl.kupiec.recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.kupiec.recipes.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class RecipesApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RecipesApplication.class, args);
    }
    
}



