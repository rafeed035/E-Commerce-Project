package com.rafeed.ecommerceproject.Repository;

import com.rafeed.ecommerceproject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //get product by category
    List<Product> getProductsByCategory(String categoryName);

    //get product by brand
    List<Product> getProductsByBrand(String brandName);

    //get product by category and brand
    List<Product> getProductsByCategoryAndBrand(String categoryName, String brandName);

    //get product by id
    Product getProductByProductId(int productId);

    //get product by name
    Product getProductByProductName(String productName);

    //get product by name ignore case
    Product findByProductNameIgnoreCase(String productName);

}
