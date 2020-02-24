package pl.kupiec.recipes.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import pl.kupiec.recipes.entity.Recipe;
import pl.kupiec.recipes.entity.User;
import pl.kupiec.recipes.repository.RecipeProductsRepository;
import pl.kupiec.recipes.repository.RecipeRepository;
import pl.kupiec.recipes.storage.StorageService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RecipeServiceTest {
    private static final Logger log =
            LoggerFactory.getLogger(RecipeServiceTest.class);
    
    private RecipeService recipeService;
    private RecipeRepository recipeRepository;
    private StorageService storageService;
    private RecipeProductsRepository recipeProductsRepository;
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        recipeRepository = mock(RecipeRepository.class);
        storageService = mock(StorageService.class);
        recipeProductsRepository = mock(RecipeProductsRepository.class);
        userService = mock(UserServiceImpl.class);
        recipeService = new RecipeService(storageService, recipeProductsRepository, recipeRepository, userService);
    }
    
    @Test
    void should_return_all_recipes_with_buffered_images() {
        // given
        User user = new User();
        //entityManager.persist(user);
        PageRequest pageRequest = PageRequest.of(1, 10);
        Pageable pageable = pageRequest.first();
        Recipe firstRecipe = new Recipe();
        firstRecipe.setImage("image");
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(firstRecipe);
        Page<Recipe> pagedResponse = new PageImpl(recipes);
        
        when(userService.getUserFromContext()).thenReturn(user);
        when(recipeRepository.findByAuthor(user, pageable)).thenReturn(pagedResponse);
        when(storageService.convertImage("image")).thenReturn("imageBuffered");
        
        // when
        Page<Recipe> result = recipeService.recipesListWithPictures(pageable);
        
        // then
        Assertions.assertThat(result).containsExactly(firstRecipe);
        assertEquals(result.get().findAny().get().getImageBuff(), "imageBuffered");
    }
    
    @Test
    void when_save_recipe_then_it_is_returned_correctly() {
        // given
        MultipartFile file = mock(MultipartFile.class);
        User user = new User();
        Recipe recipeToSave = new Recipe();
        when(userService.getUserFromContext()).thenReturn(user);
        when(recipeRepository.save(recipeToSave)).thenReturn(recipeToSave);
        
        // when
        Recipe result = recipeService.addRecipe(recipeToSave, file);
        
        // then
        assertEquals(result, recipeToSave);
        assertEquals(result.getAuthor(), user);
        
    }
    
}