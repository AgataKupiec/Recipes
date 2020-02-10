package pl.kupiec.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kupiec.recipes.entity.ProductUnit;

public interface ProductUnitRepository extends JpaRepository<ProductUnit, Long> {
    
}
