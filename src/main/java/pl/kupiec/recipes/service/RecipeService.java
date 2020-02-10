package pl.kupiec.recipes.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kupiec.recipes.entity.Recipe;
import pl.kupiec.recipes.entity.RecipeProducts;
import pl.kupiec.recipes.entity.User;
import pl.kupiec.recipes.repository.RecipeProductsRepository;
import pl.kupiec.recipes.repository.RecipeRepository;
import pl.kupiec.recipes.storage.StorageService;

import java.util.Optional;

@Service
public class RecipeService {
    
    private final StorageService storageService;
    private final RecipeProductsRepository recipeProductsRepository;
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    
    public RecipeService(StorageService storageService,
                         RecipeProductsRepository recipeProductsRepository,
                         RecipeRepository recipeRepository,
                         UserService userService) {
        this.storageService = storageService;
        this.recipeProductsRepository = recipeProductsRepository;
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }
    
    public Page<Recipe> recipesListWithPictures(Pageable pageable) {
        User user = userService.getUserFromContext();
        Page<Recipe> recipes = recipeRepository.findByAuthor(user, pageable);
        recipes.forEach(s -> {
            if (s.getImage() != null) {
                s.setImageBuff(storageService.convertImage(s.getImage()));
            }
        });
        return recipes;
    }
    
    public Page<Recipe> favAndOwnRecipesPageWithPictures(Pageable pageable) {
        User user = userService.getUserFromContext();
        Page<Recipe> recipes = recipeRepository.findFavouriteAndOwnRecipes(user.getId(), pageable);
        recipes.forEach(s -> {
            if (s.getImage() != null) {
                s.setImageBuff(storageService.convertImage(s.getImage()));
            }
        });
        return recipes;
    }
    
    public Recipe recipeWithPictureById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()) {
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
        User user = userService.getUserFromContext();
        recipe.setAuthor(user);
        if (!file.isEmpty()) {
            String filePath = storageService.store(file);
            recipe.setImage(filePath);
        }
        return recipeRepository.save(recipe);
        
    }
    
    private Recipe findRecipeOfLoggedUser(Long recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) {
            return null;
        }
        User user = userService.getUserFromContext();
        if (!recipe.get().getAuthor().equals(user)) {
            return null;
        }
        return recipe.get();
    }
    
    public Recipe getRecipeOfLoggedUserWithPicture(Long recipeId) {
        Recipe recipe = findRecipeOfLoggedUser(recipeId);
        if (recipe == null) {
            return null;
        }
        if (recipe.getImage() != null) {
            recipe.setImageBuff(storageService.convertImage(recipe.getImage()));
        }
        return recipe;
    }
    
    public void updateRecipe(Recipe recipe, MultipartFile file) {
        Recipe existing = findRecipeOfLoggedUser(recipe.getId());
        if (existing == null) {
            return;
        }
        if (recipe.getImage() == null) {
            recipe.setImage(existing.getImage());
        }
        
        if (!file.isEmpty()) {
            String filePath = storageService.store(file);
            if (existing.getImage() != null) {
                storageService.delete(existing.getImage());
            }
            recipe.setImage(filePath);
        }
        recipeRepository.save(recipe);
    }
    
    public void deleteRecipe(Long recipeId) {
        Recipe recipeToDelete = findRecipeOfLoggedUser(recipeId);
        if (recipeToDelete != null) {
            recipeToDelete.getProducts().forEach(recipeProductsRepository::delete);
            if (recipeToDelete.getImage() != null) {
                storageService.delete(recipeToDelete.getImage());
            }
            recipeRepository.delete(recipeToDelete);
        }
    }
    
    public void addProductToRecipe(RecipeProducts product) {
        Recipe recipe = findRecipeOfLoggedUser(product.getRecipe().getId());
        
        if (recipe != null && !recipeProductsRepository.existsByProductAndRecipe(product.getProduct(), product.getRecipe())) {
            RecipeProducts savedProduct = recipeProductsRepository.save(product);
            recipe.getProducts().add(savedProduct);
            updateVegeVeganOnRecipe(recipe);
        }
    }
    
    public void deleteProductFromRecipe(Long recipeId, Long recipeProductId) {
        Recipe recipe = findRecipeOfLoggedUser(recipeId);
        if (recipe == null) {
            return;
        }
        Optional<RecipeProducts> recipeProduct = recipeProductsRepository.findById(recipeProductId);
        if (recipeProduct.isPresent() && recipeProduct.get().getRecipe().equals(recipe)) {
            recipeProductsRepository.delete(recipeProduct.get());
            recipe.getProducts().remove(recipeProduct.get());
            updateVegeVeganOnRecipe(recipe);
            
        }
    }
    
    private void updateVegeVeganOnRecipe(Recipe recipe) {
        if (recipe.getProducts() != null) {
            Optional<RecipeProducts> isVege = recipe.getProducts().stream().filter(s -> !s.getProduct().getVege()).findAny();
            Optional<RecipeProducts> isVegan = recipe.getProducts().stream().filter(s -> !s.getProduct().getVegan()).findAny();
            recipe.setVege(isVege.isEmpty());
            recipe.setVegan(isVegan.isEmpty());
        } else {
            recipe.setVege(true);
            recipe.setVegan(true);
        }
        recipeRepository.save(recipe);
    }
}
