package pl.kupiec.recipes.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String email;
    
    private String password;
    
    private Boolean vege;
    
    private Boolean vegan;
    
    @OrderBy ("name")
    @ManyToMany
    private Set<Product> eliminatedProducts = new HashSet<>();
    
    @OrderBy ("title")
    @ManyToMany
    private Set<Recipe> favouriteRecipes = new HashSet<>();
    
    @OrderBy ("title")
    @ManyToMany
    private Set<Product> availableProducts = new HashSet<>();
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Boolean getVege() {
        return vege;
    }
    
    public void setVege(Boolean vege) {
        this.vege = vege;
    }
    
    public Boolean getVegan() {
        return vegan;
    }
    
    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }
    
    public Set<Product> getEliminatedProducts() {
        return eliminatedProducts;
    }
    
    public void setEliminatedProducts(Set<Product> eliminatedProducts) {
        this.eliminatedProducts = eliminatedProducts;
    }
    
    public Set<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }
    
    public void setFavouriteRecipes(Set<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }
}
