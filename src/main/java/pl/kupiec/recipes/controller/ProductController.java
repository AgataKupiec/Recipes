package pl.kupiec.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kupiec.recipes.repository.ProductRepository;
import pl.kupiec.recipes.rest.ProductService;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    
    private ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/getProducts")
    //@Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody
    List getMatchingProducts(@RequestParam("term") String term) {
        List productList = productService.getMatchedProducts(term);
        return productList;
    }
    
    
    
    
    
}
