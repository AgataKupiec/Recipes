package pl.kupiec.recipes.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.kupiec.recipes.entity.Product;
import pl.kupiec.recipes.repository.ProductRepository;

public class ProductConverter implements Converter<String, Product> {
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public Product convert(String s) {
        if (s.trim().equals("0") || s.trim().equals("")) {
            return null;
        }
        Product product = productRepository.findById(Long.parseLong(s)).get();
        return product;
    }
}
