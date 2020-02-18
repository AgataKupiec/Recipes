package pl.kupiec.recipes.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.kupiec.recipes.entity.Product;
import pl.kupiec.recipes.entity.Recipe;
import pl.kupiec.recipes.entity.RecipeProducts;
import pl.kupiec.recipes.entity.Unit;
import pl.kupiec.recipes.repository.ProductRepository;
import pl.kupiec.recipes.repository.UnitRepository;
import pl.kupiec.recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RecipeController {
    
    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;
    private final RecipeService recipeService;
    
    public RecipeController(ProductRepository productRepository,
                            UnitRepository unitRepository,
                            RecipeService recipeService) {
        this.productRepository = productRepository;
        this.unitRepository = unitRepository;
        this.recipeService = recipeService;
    }
    
    @GetMapping("/")
    public String home(Model model, Pageable pageable) {
        model.addAttribute("recipes", recipeService.allRecipesListWithPictures(pageable));
        return "index";
    }
    
    @ModelAttribute("units")
    public List<Unit> units() {
        return unitRepository.findAll();
    }
    
    @ModelAttribute("productsList")
    public List<Product> products() {
        return productRepository.findAll();
    }
    
    @GetMapping(value = "recipe/list")
    public String recipeListPaged(Model model, Pageable pageable) {
        Page<Recipe> page = recipeService.recipesListWithPictures(pageable);
        model.addAttribute("recipes", page);
        return "recipesList";
    }
    
    @Secured("ROLE_USER")
    @GetMapping("recipe/myRecipes")
    public String usersRecipeList(Model model, Pageable pageable) {
        model.addAttribute("recipes", recipeService.recipesListWithPictures(pageable));
        return "recipesList";
    }
    
    @Secured("ROLE_USER")
    @GetMapping("recipe/fav")
    public String usersFavouriteRecipeList(Model model, Pageable pageable) {
        Page<Recipe> favRecipes = recipeService.usersFavouriteRecipes(pageable);
        model.addAttribute("recipes", favRecipes);
        return "recipesList";
    }
    
    @Secured("ROLE_USER")
    @GetMapping("recipe/add")
    public String recipeForm(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "recipe/addRecipeForm";
    }
    
    
    @Secured("ROLE_USER")
    @PostMapping("recipe/add")
    public String recipeAdd(@Valid Recipe recipe, Model model,
                            @RequestParam("imageInput") MultipartFile file) {
        
        model.addAttribute("recipe", recipeService.addRecipe(recipe, file));
        return "redirect:/recipe/details/" + recipe.getId();
    }
    
    @Secured("ROLE_USER")
    @GetMapping("recipe/edit")
    public String editRecipes(Model model, Pageable pageable) {
        model.addAttribute("recipes", recipeService.recipesListWithPictures(pageable));
        return "recipe/userRecipesPanel";
    }
    
    @Secured("ROLE_USER")
    @PostMapping("recipe/addProduct")
    public String addProductToRecipe(@Valid RecipeProducts product, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && product != null) {
            recipeService.addProductToRecipe(product);
        }
        return "redirect:details/" + product.getRecipe().getId();
    }
    
    @Secured("ROLE_USER")
    @GetMapping("recipe/{recipeId}/delProduct/{recipeProductId}")
    public String deleteProductFromRecipe(@PathVariable Long recipeId,
                                      @PathVariable Long recipeProductId) {
        
        recipeService.deleteProductFromRecipe(recipeId, recipeProductId);
        return "redirect:/recipe/details/" + recipeId;
    }
    
    @GetMapping("recipe/details/{id}")
    public String recipeDetails(Model model, @PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeOfLoggedUserWithPicture(id);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            model.addAttribute("recipeProducts", new RecipeProducts());
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("units", unitRepository.findAll());
            return "recipe/addProductToRecipe";
        }
        return "redirect:/recipe/list";
    }
    
    @GetMapping("recipe/{id}")
    public String recipeDetailsShow(Model model, @PathVariable Long id) {
        Recipe recipe = recipeService.recipeWithPictureById(id);
        if (recipe == null) {
            return "redirect:/";
        }
        model.addAttribute("recipe", recipe);
        return "recipe/details";
    }
    
    @Secured("ROLE_USER")
    @GetMapping("recipe/edit/{recipeId}")
    public String editRecipeForm(Model model, @PathVariable Long recipeId) {
        Recipe recipe = recipeService.getRecipeOfLoggedUserWithPicture(recipeId);
        if (recipe == null) {
            return "redirect:/";
        }
        model.addAttribute("recipe", recipe);
        return "recipe/editRecipe";
    }
    
    @Secured("ROLE_USER")
    @GetMapping("recipe/delete/{recipeId}")
    public String deleteRecipe(Model model, @PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
        return "redirect:/recipe/edit";
        
    }
    
    @Secured("ROLE_USER")
    @PostMapping("recipe/edit")
    public String editRecipe(@Valid Recipe recipe, @RequestParam("imageInput") MultipartFile file, BindingResult result) {
        if (!result.hasErrors()) {
            recipeService.updateRecipe(recipe, file);
        }
        return "redirect:/recipe/edit";
    }
    
    
    
}
