package com.rafeed.ecommerceproject.Service;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityAlreadyExistsException;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product) throws EntityAlreadyExistsException, EntityNotfoundException;
    Product getProductById(int productId) throws EntityNotfoundException;
    Product getProductByName(String productName) throws EntityNotfoundException;
    Product updateProduct(int productId, Product product) throws EntityNotfoundException;
    List<Product> getProductsByCategory(String categoryName) throws EntityNotfoundException;
    List<Product> getProductsByBrand(String brandName) throws EntityNotfoundException;
    List<Product> getProductsByCategoryAndBrand(String categoryName, String brandName) throws EntityNotfoundException;
    List<Product> getAllProducts();
    void deleteProduct(int productId) throws EntityNotfoundException;
}
