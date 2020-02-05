package pl.kupiec.recipes.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kupiec.recipes.repository.RecipeRepository;
import pl.kupiec.recipes.service.RecipeService;

@Controller
public class HomeController {
    RecipeRepository recipeRepository;
    private final RecipeService recipeService;
    
    public HomeController(RecipeRepository recipeRepository, RecipeService recipeService) {
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
    }
    
    @GetMapping("/")
    public String home(Model model, Pageable pageable) {
        model.addAttribute("recipes", recipeService.allRecipesListWithPictures(pageable));
        return "index";
    }
    
    @GetMapping("/about")
    @ResponseBody
    public String about() { return "Here you can find some details for logged users"; }
}
