//package pl.kupiec.recipes.validator;
//
//import pl.kupiec.recipes.repository.RecipeProductsRepository;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class RecipeProductValidator implements ConstraintValidator<RecipeProduct, String> {
//
//    RecipeProduct recipeProduct;
//    RecipeProductsRepository recipeProductsRepository;
//    @Override
//    public void initialize(RecipeProduct constraintAnnotation) {
//       recipeProduct = constraintAnnotation;
//    }
//
//    @Override
//    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
//        if (s ==null){
//            return true;
//        }
//        RecipeProduct current = (RecipeProduct) s;
//
//
//
//        return false;
//    }
//
//}
