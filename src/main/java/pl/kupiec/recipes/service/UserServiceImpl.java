package pl.kupiec.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kupiec.recipes.entity.Role;
import pl.kupiec.recipes.entity.User;
import pl.kupiec.recipes.repository.RoleRepository;
import pl.kupiec.recipes.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService{
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
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
    
}
