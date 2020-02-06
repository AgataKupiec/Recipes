//package pl.kupiec.recipes.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import pl.kupiec.recipes.entity.Product;
//import pl.kupiec.recipes.repository.ProductRepository;
//
//import java.util.List;
//
//@RestController
//public class ProductRestController {
//
//    ProductRepository productRepository;
//
//    public ProductRestController(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    @GetMapping("/getProduct")
//    public ResponseEntity<Product> doAutoComplete(@RequestParam("q") final String input) {
//        List<Product> strings = productRepository.findByName(input);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String resp = "";
//
//        try {
//            resp = mapper.writeValueAsString(strings);
//        } catch (JsonProcessingException e) {
//        }
//
//        return new ResponseEntity<Product>(resp, HttpStatus.OK);
//    }
//
//
//
//}
