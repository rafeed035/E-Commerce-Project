package com.rafeed.ecommerceproject.Service;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityAlreadyExistsException;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.Brand;
import com.rafeed.ecommerceproject.Entity.Category;

import java.util.List;

public interface BrandService {
    Brand saveBrand(Brand brand) throws EntityAlreadyExistsException;
    Brand getBrandById(int brandId) throws EntityNotfoundException;
    Brand getBrandByName(String brandName) throws EntityNotfoundException;
    Brand updateBrand(int brandId, Brand brand) throws EntityNotfoundException, EntityAlreadyExistsException;
    List<Brand> getBrandsByCategory(String categoryName) throws EntityNotfoundException;
    List<Brand> getAllBrands();
    void deleteBrand(int brandId) throws EntityNotfoundException;
}
