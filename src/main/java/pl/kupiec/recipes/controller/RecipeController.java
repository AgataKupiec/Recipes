package pl.kupiec.recipes.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.kupiec.recipes.entity.Product;
import pl.kupiec.recipes.entity.Recipe;
import pl.kupiec.recipes.entity.RecipeProducts;
import pl.kupiec.recipes.entity.Unit;
import pl.kupiec.recipes.repository.ProductRepository;
import pl.kupiec.recipes.repository.RecipeProductsRepository;
import pl.kupiec.recipes.repository.RecipeRepository;
import pl.kupiec.recipes.repository.UnitRepository;
import pl.kupiec.recipes.repository.UserRepository;
import pl.kupiec.recipes.service.RecipeService;
import pl.kupiec.recipes.storage.StorageService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RecipeController {
    
    private final StorageService storageService;
    private final RecipeProductsRepository recipeProductsRepository;
    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;
    private final UserRepository userRepository;
    private final RecipeService recipeService;
    
    public RecipeController(RecipeProductsRepository recipeProductsRepository,
                            RecipeRepository recipeRepository,
                            ProductRepository productRepository,
                            UnitRepository unitRepository,
                            UserRepository userRepository,
                            StorageService storageService,
                            RecipeService recipeService) {
        this.recipeProductsRepository = recipeProductsRepository;
        this.recipeRepository = recipeRepository;
        this.productRepository = productRepository;
        this.unitRepository = unitRepository;
        this.userRepository = userRepository;
        this.storageService = storageService;
        this.recipeService = recipeService;
    }
    
    @ModelAttribute("units")
    public List<Unit> units() {
        return unitRepository.findAll();
    }
    
    @ModelAttribute("productsList")
    public List<Product> products() {
        return productRepository.findAll();
    }
    
    
    @GetMapping(value = "/recipe/add")
    public String recipeForm(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "recipe/addRecipeForm";
    }
    
    @GetMapping(value = "/recipe/list")
    public String recipeListPaged(Model model, Pageable pageable) {
        Page<Recipe> page = recipeService.recipesListWithPictures(pageable);
        model.addAttribute("recipes", page);
//        return "recipe/userRecipes";
        return "recipesList";
    }
    
//    @GetMapping("/list")
//    public String listPaged(Model model, Pageable pageable) {
//        Page<Drink> page = drinkRepository.findAll(pageable);
//        model.addAttribute("page", page);
//        return "drink/list";
//    }
    
    
    
    @GetMapping(value = "/recipe/myRecipes")
    public String usersRecipeList(Model model, Pageable pageable) {
        model.addAttribute("recipes", recipeService.recipesListWithPictures(pageable));
        return "recipesList";
    }
    
    @GetMapping(value = "/recipe/fav")
    public String usersFavouriteRecipeList(Model model, Pageable pageable) {
        Page<Recipe> favRecipes = recipeService.favAndOwnRecipesPageWithPictures(pageable);
        model.addAttribute("recipes", favRecipes);
        return "recipesList";
    }
    
    @PostMapping(value = "/recipe/add")
    public String recipeAdd(@Valid Recipe recipe, Model model,
                            @RequestParam("imageInput") MultipartFile file) {
        
        model.addAttribute("recipe", recipeService.addRecipe(recipe, file));
        return "redirect:/recipe/details/" + recipe.getId();
    }
    
    @GetMapping(value = "/recipe/details/{id}")
    public String recipeDetails(Model model, @PathVariable Long id) {
        Recipe recipe = recipeService.findRecipeOfLoggedUser(id);
        if (recipe != null) {
            model.addAttribute("recipeAtt", recipe);
            model.addAttribute("recipeProducts", new RecipeProducts());
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("units", unitRepository.findAll());
            return "recipe/addProductToRecipe";
        }
        return "redirect:/recipe/list";
    }
    
    @RequestMapping(value = "/recipe/addProduct", method = RequestMethod.POST)
    public String recipeAddProduct(@Valid RecipeProducts product, BindingResult bindingResult) {
        
        if (!recipeProductsRepository.existsByProductAndRecipe(product.getProduct(), product.getRecipe())) {
            recipeProductsRepository.save(product);
        }
        return "redirect:details/" + product.getRecipe().getId();
    }
    
    @RequestMapping(value = "/recipe/{recipeId}/delProduct/{recipeProductId}", method = RequestMethod.GET)
    public String recipeDeleteProduct(@PathVariable Long recipeId,
                                      @PathVariable Long recipeProductId) {
        
        recipeService.deleteProductFromRecipe(recipeId, recipeProductId);
        return "redirect:/recipe/details/" + recipeId;
    }
    
    @GetMapping(value = "/recipe/{id}")
    public String recipeDetailsShow(Model model, @PathVariable Long id) {
        Recipe recipe = recipeService.recipeWithPictures(id);
        if (recipe == null){
            return "redirect:/";
        }
        model.addAttribute("recipe", recipe);
        return "recipe/details";
    }
    
}
