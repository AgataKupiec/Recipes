package pl.kupiec.recipes.rest;

import org.springframework.stereotype.Service;
import pl.kupiec.recipes.entity.Product;
import pl.kupiec.recipes.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    
    public List<Product> getMatchedProducts(String name){
        return productRepository.findByNameContaining(name);
    }
    
    
    
}
