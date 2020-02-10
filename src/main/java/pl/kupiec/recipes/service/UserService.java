package pl.kupiec.recipes.service;

import pl.kupiec.recipes.entity.User;

public interface UserService {
    
    User findByEmail(String email);
    
    void saveUser(User user);
    
    boolean checkIfUserExists(User user);
    
    User getUserFromContext();
    
}
