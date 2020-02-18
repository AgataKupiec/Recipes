package pl.kupiec.recipes.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kupiec.recipes.entity.Product;
import pl.kupiec.recipes.entity.User;
import pl.kupiec.recipes.repository.ProductRepository;
import pl.kupiec.recipes.repository.UserRepository;
import pl.kupiec.recipes.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
@Secured("ROLE_USER")
@RequestMapping("/chef")
public class UserController {
    
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    
    
    @GetMapping("profile")
    public String userDetails(Model model, Principal principal) {
        User currUser = userRepository.findByEmail(principal.getName());
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("user", currUser);
        model.addAttribute("products", productRepository.findAll());
        return "user/details";
    }
    
    @GetMapping("eliminateProduct/remove/{id}")
    public String removeEliminatedProduct(Principal principal, @PathVariable Long id) {
        User currUser = userRepository.findByEmail(principal.getName());
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            currUser.getEliminatedProducts().remove(product.get());
            userRepository.save(currUser);
        }
        return "redirect:/chef/profile";
    }
    
    @GetMapping("eliminateProduct/add")
    public String addEliminatedProduct(@RequestParam Long productId, Principal principal) {
        User currUser = userRepository.findByEmail(principal.getName());
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            currUser.getEliminatedProducts().add(product.get());
            userRepository.save(currUser);
        }
        return "redirect:/chef/profile";
    }
    
    @GetMapping("availableProduct/remove/{id}")
    public String removeAvailableProduct(@PathVariable Long id) {
        User currUser = userService.getUserFromContext();
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            currUser.getAvailableProducts().remove(product.get());
            userRepository.save(currUser);
        }
        return "redirect:/chef/profile";
    }
    
    @GetMapping("availableProduct/add")
    public String addAvailableProduct(@RequestParam Long productId, Principal principal) {
        User currUser = userRepository.findByEmail(principal.getName());
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            currUser.getAvailableProducts().add(product.get());
            userRepository.save(currUser);
        }
        return "redirect:/chef/profile";
    }
    
    
    
}