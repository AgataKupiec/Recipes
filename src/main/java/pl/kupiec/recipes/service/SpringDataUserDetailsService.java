package pl.kupiec.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kupiec.recipes.entity.User;
import pl.kupiec.recipes.pojo.CurrentUser;

import java.util.HashSet;
import java.util.Set;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    private UserService userService;
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
        @Override
        public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userService.findByEmail(username);
            if (user == null || !user.getEnabled()) {throw new UsernameNotFoundException(username); }
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getRole())));
            return new CurrentUser(user.getEmail(), user.getPassword(), grantedAuthorities, user);
        }
    }


