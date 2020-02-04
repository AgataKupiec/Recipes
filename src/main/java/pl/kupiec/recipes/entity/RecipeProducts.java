package pl.kupiec.recipes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "recipes_products", uniqueConstraints={
        @UniqueConstraint(columnNames = {"product_id", "recipe_id"})})
public class RecipeProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    private Double quantity;
    
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeProducts)) return false;
        RecipeProducts that = (RecipeProducts) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public Recipe getRecipe() {
        return recipe;
    }
    
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
