package pl.kupiec.recipes.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kupiec.recipes.entity.Product;
import pl.kupiec.recipes.entity.User;
import pl.kupiec.recipes.repository.ProductRepository;
import pl.kupiec.recipes.repository.RecipeProductsRepository;
import pl.kupiec.recipes.repository.RecipeRepository;
import pl.kupiec.recipes.repository.RoleRepository;
import pl.kupiec.recipes.repository.UnitRepository;
import pl.kupiec.recipes.repository.UserRepository;
import pl.kupiec.recipes.service.UserService;

import java.security.Principal;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/chef")
public class UserController {
    
    private final RecipeProductsRepository recipeProductsRepository;
    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserDetailsService userDetailsService;
    
    @GetMapping("/profile")
    public String userDetails(Model model, Principal principal) {
        User currUser = userRepository.findByEmail(principal.getName());
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("user", currUser);
        model.addAttribute("products", productRepository.findAll());
        return "user/details";
    }
    
    @GetMapping("/eliminateProduct/remove/{id}")
    public String removeEliminatedProduct(Principal principal, @PathVariable Long id) {
        User currUser = userRepository.findByEmail(principal.getName());
        Product product = productRepository.findById(id).get();
        currUser.getEliminatedProducts().remove(product);
        userRepository.save(currUser);
        return "redirect:/chef/profile";
    }
    
    @GetMapping("/eliminateProduct/add")
    public String addEliminatedProduct(@RequestParam Long productId, Principal principal) {
        User currUser = userRepository.findByEmail(principal.getName());
        Product product = productRepository.findById(productId).get();
        currUser.getEliminatedProducts().add(product);
        userRepository.save(currUser);
        return "redirect:/chef/profile";
    }
    
    @GetMapping("/availableProduct/remove/{id}")
    public String removeAvailableProduct(Principal principal, @PathVariable Long id) {
        User currUser = userRepository.findByEmail(principal.getName());
        Product product = productRepository.findById(id).get();
        currUser.getAvailableProducts().remove(product);
        userRepository.save(currUser);
        return "redirect:/chef/profile";
    }
    
    @GetMapping("/availableProduct/add")
    public String addAvailableProduct(@RequestParam Long productId, Principal principal) {
        User currUser = userRepository.findByEmail(principal.getName());
        Product product = productRepository.findById(productId).get();
        currUser.getAvailableProducts().add(product);
        userRepository.save(currUser);
        return "redirect:/chef/profile";
    }
    
}