package pl.kupiec.recipes.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    private String content;
    
    private Boolean vege;
    
    private Boolean vegan;
    
    private Integer servings;
    
    //in minutes
    private Integer time;
    
    @ManyToOne
    private User author;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recipe")
    private List<RecipeProducts> products;
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
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
    
    public Integer getServings() {
        return servings;
    }
    
    public void setServings(Integer servings) {
        this.servings = servings;
    }
    
    public Integer getTime() {
        return time;
    }
    
    public void setTime(Integer time) {
        this.time = time;
    }
    
    public User getAuthor() {
        return author;
    }
    
    public void setAuthor(User author) {
        this.author = author;
    }
    
    public List<RecipeProducts> getProducts() {
        return products;
    }
    
    public void setProducts(List<RecipeProducts> products) {
        this.products = products;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
