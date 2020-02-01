package pl.kupiec.recipes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kupiec.recipes.entity.Product;
import pl.kupiec.recipes.entity.Recipe;
import pl.kupiec.recipes.entity.RecipeProducts;
import pl.kupiec.recipes.repository.ProductRepository;
import pl.kupiec.recipes.repository.RecipeProductsRepository;
import pl.kupiec.recipes.repository.RecipeRepository;
import pl.kupiec.recipes.repository.UnitRepository;
import pl.kupiec.recipes.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RecipeController {
    
    private final RecipeProductsRepository recipeProductsRepository;
    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;
    private final UserRepository userRepository;
    
    public RecipeController(RecipeProductsRepository recipeProductsRepository, RecipeRepository recipeRepository, ProductRepository productRepository, UnitRepository unitRepository, UserRepository userRepository) {
        this.recipeProductsRepository = recipeProductsRepository;
        this.recipeRepository = recipeRepository;
        this.productRepository = productRepository;
        this.unitRepository = unitRepository;
        this.userRepository = userRepository;
    }
    
    
    @RequestMapping("/test")
    @ResponseBody
    public String testing(){
        Product product1 = productRepository.findById(1L).get();
        Product product2 = productRepository.findById(2L).get();
        Product product3 = productRepository.findById(3L).get();
        Product product4 = productRepository.findById(4L).get();
    
        List<RecipeProducts> byProductIsNotIn = recipeProductsRepository.findByProductNot(product1);
        Set<Recipe> collect = byProductIsNotIn.stream().map(s -> s.getRecipe()).collect(Collectors.toSet());
        return String.join(" , ", collect.stream().map(s -> s.getId().toString()).collect(Collectors.toSet()));
        
    }
    
//    @RequestMapping("/test2")
//    @ResponseBody
//    public String testing2(){
//        Product product1 = productRepository.findById(1L).get();
//        Product product2 = productRepository.findById(2L).get();
//        Product product3 = productRepository.findById(3L).get();
//        Product product4 = productRepository.findById(4L).get();
//        List<Product> products = new ArrayList<>();
//        products.add(product1);
//        products.add(product2);
//        List<RecipeProducts> byProductIsIn = recipeProductsRepository.findByProductIn(products);
//        Set<Recipe> byProductNotIn = recipeRepository.findByProductsNotContaining(byProductIsIn);
//        return String.join(" , ", byProductNotIn.stream().map(s -> s.getId().toString()).collect(Collectors.toSet()));
//
//    }
    
}
