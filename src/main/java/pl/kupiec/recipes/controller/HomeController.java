package pl.kupiec.recipes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import pl.kupiec.recipes.repository.ProductRepository;
import pl.kupiec.recipes.repository.ProductUnitRepository;
import pl.kupiec.recipes.repository.RecipeRepository;
import pl.kupiec.recipes.repository.UnitRepository;
import pl.kupiec.recipes.service.RecipeService;

@Slf4j
@Controller
public class HomeController {
    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;
    private final ProductRepository productRepository;
    private final ProductUnitRepository productUnitRepository;
    private final UnitRepository unitRepository;
    
    
    public HomeController(RecipeRepository recipeRepository, RecipeService recipeService,
                          ProductRepository productRepository, ProductUnitRepository productUnitRepository,
                          UnitRepository unitRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
        this.productRepository = productRepository;
        this.productUnitRepository = productUnitRepository;
        this.unitRepository = unitRepository;
    }
    
    
}
