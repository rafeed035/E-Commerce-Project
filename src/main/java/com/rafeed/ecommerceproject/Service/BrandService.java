package com.rafeed.ecommerceproject.Service;

import com.rafeed.ecommerceproject.Entity.Brand;
import com.rafeed.ecommerceproject.Entity.Category;

import java.util.List;

public interface BrandService {
    Brand saveBrand(Brand brand);
    Brand getBrandById(int brandId);
    Brand getBrandByName(String brandName);
    Brand updateBrand(int brandId, Brand brand);
    List<Brand> getBrandsByCategory(String categoryName);
    List<Brand> getAllBrands();
    void deleteBrand(int brandId);
}
