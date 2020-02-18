package pl.kupiec.recipes.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kupiec.recipes.entity.Role;
import pl.kupiec.recipes.entity.User;
import pl.kupiec.recipes.pojo.CurrentUser;
import pl.kupiec.recipes.repository.RoleRepository;
import pl.kupiec.recipes.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByRole("ROLE_USER");
        user.setRoles(new HashSet<Role>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }
    
    @Override
    public boolean checkIfUserExists(User user) {
        return userRepository.existsByEmail(user.getEmail());
    }
    
    @Override
    public User getUserFromContext() {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getUser();
    }
}
