package pl.kupiec.recipes.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kupiec.recipes.entity.User;
import pl.kupiec.recipes.repository.ProductRepository;
import pl.kupiec.recipes.repository.RecipeProductsRepository;
import pl.kupiec.recipes.repository.RecipeRepository;
import pl.kupiec.recipes.repository.RoleRepository;
import pl.kupiec.recipes.repository.UnitRepository;
import pl.kupiec.recipes.repository.UserRepository;
import pl.kupiec.recipes.service.UserService;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@Controller
public class LoginController {
    private final RecipeProductsRepository recipeProductsRepository;
    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserDetailsService userDetailsService;
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/login_v2";
    }
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String loginIn(@ModelAttribute(binding = false) User user) {
        return "redirect:/";
    }
    
    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout() {
        return "redirect:/";
    }
    
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        
        boolean userExists = userService.checkIfUserExists(user);
        if (userExists) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Użytkownik o takim adresie już istnieje");
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/login";
        } else {
            userService.saveUser(user);
        }
        user = userService.findByEmail(user.getEmail());
        model.addAttribute("user", user);
        return "redirect:/login";
    }
    
}
