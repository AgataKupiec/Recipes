package pl.kupiec.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kupiec.recipes.entity.Product;
import pl.kupiec.recipes.entity.RecipeProducts;

import java.util.List;

public interface RecipeProductsRepository extends JpaRepository<RecipeProducts, Long> {

    List<RecipeProducts> findByProductNot(Product product);
    
    List<RecipeProducts> findByProductIsNotIn(List<Product> product);
    
    List<RecipeProducts> findByProductIn(List<Product> products);
    
    
}
