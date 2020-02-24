package pl.kupiec.recipes.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.kupiec.recipes.entity.Recipe;
import pl.kupiec.recipes.entity.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest

class RecipeRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RecipeRepository recipeRepository;
    
    @Test
    void should_find_recipe_by_author() {
        // given
        User user = new User();
        user.setEmail("author@email.com");
        user.setPassword("password");
        entityManager.persist(user);
        Recipe recipe = new Recipe();
        recipe.setAuthor(user);
        recipe.setTitle("title");
        recipe.setContent("content");
        entityManager.persist(recipe);
        
        PageRequest pageRequest = PageRequest.of(1, 10);
        Pageable pageable = pageRequest.first();
    
        // when
        Page<Recipe> result = recipeRepository.findByAuthor(user, pageable);
        // then
        Assertions.assertThat(result).containsExactly(recipe);
        
    }
    
  
}