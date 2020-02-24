package pl.kupiec.recipes.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.kupiec.recipes.entity.Product;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Test
    void find_by_name_containing_then_return_list_of_products() {
    
        // given
        Product product = new Product();
        product.setName("orange");
        entityManager.persist(product);
        // when
        List<Product> result = productRepository.findByNameContaining("or");
        // then
        assertEquals(result.get(0).getName(), product.getName());
    }
    
    @Test
    void given_orange_find_apple_should_be_null(){
        // given
        Product product = new Product();
        product.setName("orange");
        entityManager.persist(product);
        // when
        List<Product> result = productRepository.findByNameContaining("apple");
        // then
        assertEquals(result, Collections.emptyList());
        
    }
    
    @Test
    void given_orange_and_banana_find_an_should_return_two_elements(){
        // given
        Product orange = new Product();
        orange.setName("orange");
        entityManager.persist(orange);
        Product banana = new Product();
        banana.setName("banana");
        entityManager.persist(banana);
        // when
        List<Product> result = productRepository.findByNameContaining("an");
        // then
        Assertions.assertThat(result).containsExactly(orange, banana);
        
    }
    
    @Test
    void given_orange_and_banana_find_by_empty_string_should_return_two_elements(){
        // given
        Product orange = new Product();
        orange.setName("orange");
        entityManager.persist(orange);
        Product banana = new Product();
        banana.setName("banana");
        entityManager.persist(banana);
        // when
        List<Product> result = productRepository.findByNameContaining("");
        // then
        Assertions.assertThat(result).containsExactly(orange, banana);
        
    }
    
}