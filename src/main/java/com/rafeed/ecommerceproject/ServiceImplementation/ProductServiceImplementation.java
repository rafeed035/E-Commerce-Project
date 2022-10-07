package com.rafeed.ecommerceproject.ServiceImplementation;

import com.rafeed.ecommerceproject.Entity.Product;
import com.rafeed.ecommerceproject.Service.ProductService;

import java.util.List;

public class ProductServiceImplementation implements ProductService {
    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductById(int productId) {
        return null;
    }

    @Override
    public Product getProductByName(String productName) {
        return null;
    }

    @Override
    public Product getProductByNameIgnoreCase(String productName) {
        return null;
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return null;
    }

    @Override
    public List<Product> getProductsByBrand(String brandName) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String categoryName, String brandName) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProduct(int productId) {

    }
}
