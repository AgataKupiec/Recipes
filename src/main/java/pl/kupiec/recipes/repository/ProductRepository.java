package pl.kupiec.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kupiec.recipes.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByNameContaining(String name);
    
}
