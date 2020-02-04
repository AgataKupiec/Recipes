package pl.kupiec.recipes.storage;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
    
    /**
     * Folder location for storing files
     */
    private String location = "/home/agata/CodersLab/img";
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
}
