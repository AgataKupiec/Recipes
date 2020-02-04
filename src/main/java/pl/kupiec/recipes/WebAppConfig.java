package pl.kupiec.recipes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.kupiec.recipes.converter.ProductConverter;
import pl.kupiec.recipes.converter.UnitConverter;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("admin/login");
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        
        registry.addConverter(getProductConverter());
        registry.addConverter(getUnitConverter());
    }
    @Bean
    public ProductConverter getProductConverter() {
        return new ProductConverter();
    }
    @Bean
    public UnitConverter getUnitConverter() {
        return new UnitConverter();
    }
}
