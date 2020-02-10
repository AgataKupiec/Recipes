package pl.kupiec.recipes.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final pl.kupiec.recipes.entity.User user;
    
    public CurrentUser(String email,
                       String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.kupiec.recipes.entity.User user) {
        super(email, password, authorities);
        this.user = user;
    }
    
    public pl.kupiec.recipes.entity.User getUser() {
        return user;
    }
}
