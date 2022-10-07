package com.rafeed.ecommerceproject.Repository;

import com.rafeed.ecommerceproject.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    //get brand by category
    List<Brand> getBrandsByCategory(String  categoryName);

    //get brand by id
    Brand getBrandByBrandId(int brandId);

    //get brand by name
    Brand getBrandByBrandName(String brandName);
}
