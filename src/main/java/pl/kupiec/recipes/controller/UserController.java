package pl.kupiec.recipes.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kupiec.recipes.entity.User;
import pl.kupiec.recipes.pojo.CurrentUser;
import pl.kupiec.recipes.repository.ProductRepository;
import pl.kupiec.recipes.repository.RecipeProductsRepository;
import pl.kupiec.recipes.repository.RecipeRepository;
import pl.kupiec.recipes.repository.RoleRepository;
import pl.kupiec.recipes.repository.UnitRepository;
import pl.kupiec.recipes.repository.UserRepository;
import pl.kupiec.recipes.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Controller
//@RequestMapping("chef")
public class UserController {
    
    private final RecipeProductsRepository recipeProductsRepository;
    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserDetailsService userDetailsService;
    
    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setEmail("a@admin");
        user.setPassword("admin");
        user.setRoles((new HashSet<>(roleRepository.findAll())));
        User user2 = new User();
        user.setEmail("user");
        user.setPassword("user");
        user.setRoles(Set.of(roleRepository.findByRole("ROLE_USER")));
        userService.saveUser(user);
        userService.saveUser(user2);
        return "admin";
    }
    
    @GetMapping("/admin")
    @ResponseBody
    public String userInfo(ModelMap modelMap, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = principal.getName();
//
//        userDetailsService.loadUserByUsername();
//        User entityUser = customUser.getUser();
        return name;
    }
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        
        return "admin/login";
    }
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseBody
    public String loginIn(@AuthenticationPrincipal CurrentUser customUser) {
        
        return "redirect:admin";
    }
    
    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(ModelMap modelMap, Principal principal) {
        
        return "logout";
    }
    
    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    @ResponseBody
    public String logoutPost() {
        
        return "logged out";
    }
}
