//package pl.kupiec.recipes.validator;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//@Constraint(validatedBy = RecipeProductValidator.class)
//@Target({ElementType.METHOD, ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface RecipeProduct {
//    String message() default "{startWith.error.message}";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
