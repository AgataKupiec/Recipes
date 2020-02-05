package pl.kupiec.recipes.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kupiec.recipes.entity.Recipe;
import pl.kupiec.recipes.entity.RecipeProducts;
import pl.kupiec.recipes.entity.User;
import pl.kupiec.recipes.pojo.CurrentUser;
import pl.kupiec.recipes.repository.ProductRepository;
import pl.kupiec.recipes.repository.RecipeProductsRepository;
import pl.kupiec.recipes.repository.RecipeRepository;
import pl.kupiec.recipes.repository.UnitRepository;
import pl.kupiec.recipes.repository.UserRepository;
import pl.kupiec.recipes.storage.StorageService;

import java.util.Optional;

@Service
public class RecipeService {
    
    private final StorageService storageService;
    private final RecipeProductsRepository recipeProductsRepository;
    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;
    private final UserRepository userRepository;
    
    public RecipeService(StorageService storageService,
                         RecipeProductsRepository recipeProductsRepository,
                         RecipeRepository recipeRepository,
                         ProductRepository productRepository,
                         UnitRepository unitRepository,
                         UserRepository userRepository) {
        this.storageService = storageService;
        this.recipeProductsRepository = recipeProductsRepository;
        this.recipeRepository = recipeRepository;
        this.productRepository = productRepository;
        this.unitRepository = unitRepository;
        this.userRepository = userRepository;
    }
    
    public Page<Recipe> recipesListWithPictures(Pageable pageable) {
        User user = getUserFromContext();
        Page<Recipe> recipes = recipeRepository.findByAuthor(user, pageable);
        recipes.forEach(s -> {
            if (s.getImage() != null) {
                s.setImageBuff(storageService.convertImage(s.getImage()));
            }
        });
        return recipes;
    }
    
    public Page<Recipe> favAndOwnRecipesPageWithPictures(Pageable pageable) {
        User user = getUserFromContext();
        Page<Recipe> recipes = recipeRepository.findFavouriteAndOwnRecipes(user.getId(), pageable);
        recipes.forEach(s -> {
            if (s.getImage() != null) {
                s.setImageBuff(storageService.convertImage(s.getImage()));
            }
        });
        return recipes;
    }
    
    
    
//    public List<Recipe> favouriteRecipes() {
//        User user = getUserFromContext();
//        List<Recipe> recipes = recipeRepository.findByAuthorFavouriteRecipes(user.getId());
//        recipes.forEach(s -> {
//            if (s.getImage() != null) {
//                s.setImageBuff(storageService.convertImage(s.getImage()));
//            }
//        });
//        return recipes;
//    }
    
    public Recipe recipeWithPictures(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()){
            return null;
        }
        if (recipe.get().getImage() != null) {
            recipe.get().setImageBuff(storageService.convertImage(recipe.get().getImage()));
        }
        return recipe.get();
    }
    
    public Page<Recipe> allRecipesListWithPictures(Pageable pageable) {
        Page<Recipe> recipes = recipeRepository.findAll(pageable);
        recipes.forEach(s -> {
            if (s.getImage() != null) {
                s.setImageBuff(storageService.convertImage(s.getImage()));
            }
        });
        return recipes;
    }
    
    public Recipe addRecipe(Recipe recipe, MultipartFile file) {
        User user = getUserFromContext();
        recipe.setAuthor(user);
        if (!file.isEmpty()) {
            String filePath = storageService.store(file);
            recipe.setImage(filePath);
        }
        return recipeRepository.save(recipe);
        
    }
    
    public Recipe findRecipeOfLoggedUser(Long recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) {
            return null;
        }
        User user = getUserFromContext();
        if (recipe.get().getAuthor().equals(user)) {
            return null;
        }
        return recipe.get();
    }
    
    public void deleteProductFromRecipe(Long recipeId, Long recipeProductId) {
        if (findRecipeOfLoggedUser(recipeId) == null) {
            return;
        }
        Optional<RecipeProducts> recipeProduct = recipeProductsRepository.findById(recipeProductId);
        recipeProduct.ifPresent(recipeProductsRepository::delete);
    }
    
    
    private User getUserFromContext() {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getUser();
    }
    
    
}
