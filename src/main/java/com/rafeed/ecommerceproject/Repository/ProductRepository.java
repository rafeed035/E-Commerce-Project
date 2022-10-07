package com.rafeed.ecommerceproject.Repository;

import com.rafeed.ecommerceproject.Entity.Brand;
import com.rafeed.ecommerceproject.Entity.Category;
import com.rafeed.ecommerceproject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //get product by category
    List<Product> getProductsByCategory(Category category);

    //get product by brand
    List<Product> getProductsByBrand(Brand brand);

    //get product by category and brand
    List<Product> getProductsByCategoryAndBrand(Category category, Brand brand);

    //get product by id
    Product getProductByProductId(int productId);

    //get product by name
    Product getProductByProductName(String productName);
}
