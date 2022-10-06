package com.rafeed.ecommerceproject.Service;

import com.rafeed.ecommerceproject.Entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    Product getProductById(int productId);
    Product getProductByName(String productName);
    Product getProductByNameIgnoreCase(String productName);
    Product updateProduct(int productId, Product product);
    List<Product> getProductsByCategory(String categoryName);
    List<Product> getProductsByBrand(String brandName);
    List<Product> getProductsByCategoryAndBrand(String categoryName, String brandName);
    List<Product> getAllProducts();
    void deleteProduct(int productId);
}
