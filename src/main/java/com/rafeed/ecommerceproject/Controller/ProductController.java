package com.rafeed.ecommerceproject.Controller;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityAlreadyExistsException;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.Product;
import com.rafeed.ecommerceproject.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/saveProduct")
    public Product saveProduct(@RequestBody Product product) throws EntityNotfoundException, EntityAlreadyExistsException {
        return productService.saveProduct(product);
    }

    @GetMapping("/getProductById")
    public Product getProductById(@RequestParam int productId) throws EntityNotfoundException {
        return productService.getProductById(productId);
    }

    @GetMapping("/getProductByName")
    public Product getProductBuName(@RequestParam String productName) throws EntityNotfoundException {
        return productService.getProductByName(productName);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestParam int productId, @RequestBody Product product) throws EntityNotfoundException, EntityAlreadyExistsException {
        return productService.updateProduct(productId, product);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/getProductsByCategory")
    public List<Product> getAllProductsByCategory(@RequestParam String categoryName) throws EntityNotfoundException {
        return productService.getProductsByCategory(categoryName);
    }

    @GetMapping("/getProductsByBrand")
    public List<Product> getAllProductsByBrand(@RequestParam String brandName) throws EntityNotfoundException {
        return productService.getProductsByBrand(brandName);
    }

    @GetMapping("/getProductsByCategoryAndBrand")
    public List<Product> getAllProductsByCategoryAndBrand(String categoryName, String brandName) throws EntityNotfoundException {
        return productService.getProductsByCategoryAndBrand(categoryName, brandName);
    }

    @DeleteMapping("/deleteProduct")
    public String deleteProduct(int productId) throws EntityNotfoundException {
        productService.deleteProduct(productId);
        return "deleted";
    }
}
