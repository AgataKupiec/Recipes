package pl.kupiec.recipes.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kupiec.recipes.entity.User;
import pl.kupiec.recipes.service.UserService;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@Controller
public class LoginController {
    private final UserService userService;
    
    @GetMapping("login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/login_v2";
    }
    
    @PostMapping("login")
    public String loginIn(@ModelAttribute(binding = false) User user) {
        return "redirect:/";
    }
    
    @GetMapping("logout")
    public String logout() {
        return "redirect:/";
    }
    
    
    @PostMapping("registration")
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        
        boolean userExists = userService.checkIfUserExists(user);
        if (userExists) {
            bindingResult
                    .rejectValue("email", "error.user");
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/login";
        } else {
            userService.saveUser(user);
        }
        model.addAttribute("user", userService.findByEmail(user.getEmail()));
        return "redirect:/login";
    }
    
}
