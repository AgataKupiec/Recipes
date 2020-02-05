package pl.kupiec.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kupiec.recipes.entity.Product;
import pl.kupiec.recipes.entity.Recipe;
import pl.kupiec.recipes.entity.User;

import java.util.List;
import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
//    @Query("select r from Recipe r where r.products not in :products")
//    Set<Recipe> findByProductsNotContaining (@Param("products") List<RecipeProducts> products);
    
    @Query(value = "select * from recipes where id in (\n" +
            "      select recipe_id from recipes_products where recipe_id NOT IN (\n" +
            "       select distinct recipe_id from recipes_products where product_id IN (:products)));", nativeQuery = true)
    Set<Recipe> findRecipesWithoutProducts(@Param("products")List<Product>products);
    
    @Query(value = "select * from recipes where id not in (\n" +
            "select recipe_id from recipes join recipes_products rp on recipes.id = rp.recipe_id where product_id not in :products);", nativeQuery = true)
    Set<Recipe> findRecipesFromProducts(@Param("products")List<Product>products);
    
    List<Recipe> findByTitleLike(String title);
    
    List<Recipe> findByAuthor(User user);
    
    @Query(value = "select * from recipes join users_favourite_recipes on id = users_favourite_recipes.favourite_recipes_id", nativeQuery = true)
    List<Recipe> findByAuthorFavouriteRecipes(Long id);

}
