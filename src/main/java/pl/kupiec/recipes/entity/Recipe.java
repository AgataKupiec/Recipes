package pl.kupiec.recipes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    
    private Boolean vege;
    
    private Boolean vegan;
    
    private Integer servings;
    
    private String image;
    
    @Transient
    private String imageBuff;
    //in minutes
    private Integer time;
    
    @ManyToOne
    private User author;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recipe")
    private List<RecipeProducts> products;
    
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
