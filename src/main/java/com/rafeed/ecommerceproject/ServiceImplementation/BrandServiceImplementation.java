package com.rafeed.ecommerceproject.ServiceImplementation;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityAlreadyExistsException;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.Brand;
import com.rafeed.ecommerceproject.Entity.Category;
import com.rafeed.ecommerceproject.Entity.Product;
import com.rafeed.ecommerceproject.Repository.BrandRepository;
import com.rafeed.ecommerceproject.Repository.CategoryRepository;
import com.rafeed.ecommerceproject.Service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImplementation implements BrandService {

    private BrandRepository brandRepository;
    public CategoryRepository categoryRepository;

    public BrandServiceImplementation(BrandRepository brandRepository,
                                      CategoryRepository categoryRepository) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Brand saveBrand(Brand brand) throws EntityAlreadyExistsException {

        //check whether the brand already exists in the database or not
        //if already exists, then throw an exception
        //else save the entity
        Brand brandCheck = brandRepository.getBrandByBrandId(brand.getBrandId());
        if(brandCheck != null){
            throw new EntityAlreadyExistsException("Entity already exists!");
        }
        else{
            //check if the category of the brand already exists in the database
            Category categoryCheck = categoryRepository.getCategoryByCategoryName(brand.getCategory().getCategoryName());
            brandCheck = brand;
            if(categoryCheck != null){
                brandCheck.setCategory(categoryCheck);
            }
            else{
                brandCheck.setCategory(categoryRepository.save(brand.getCategory()));
            }
            return brandRepository.save(brandCheck);
        }
    }

    @Override
    public Brand getBrandById(int brandId) throws EntityNotfoundException {

        //check whether the brand already exists in the database or not
        //if does not exist, throw exception
        //else return entity
        Brand brandCheck = brandRepository.getBrandByBrandId(brandId);
        if(brandCheck == null){
            throw new EntityNotfoundException("Entity not found!!!");
        }
        else{
            return brandCheck;
        }
    }

    @Override
    public Brand getBrandByName(String brandName) throws EntityNotfoundException {

        //check whether the brand already exists in the database or not
        //if does not exist, throw exception
        //else return entity
        Brand brandCheck = brandRepository.getBrandByBrandName(brandName);
        if(brandCheck == null){
            throw new EntityNotfoundException("Entity not found!!!");
        }
        else{
            return brandCheck;
        }
    }

    @Override
    public Brand updateBrand(int brandId, Brand brand) throws EntityNotfoundException {

        //check whether the brand already exists in the database or not
        //if does not exist, throw exception
        //else update entity
        Brand brandCheck = brandRepository.getBrandByBrandId( brandId);
        if(brandCheck == null){
            throw new EntityNotfoundException("Entity not found!!!");
        }
        else{
            brandCheck.setBrandName(brand.getBrandName());
            //check if the product list is updated or not
            List<Product>productList = brand.getProductList();
            if(productList.size() == 0){
                brandCheck.setProductList(brandCheck.getProductList());
            }
            else{
                brandCheck.setProductList(productList);
            }
            //check if the category of the brand already exists in the database
            Category categoryCheck = categoryRepository.getCategoryByCategoryName(brand.getCategory().getCategoryName());
            if(categoryCheck != null){
                brandCheck.setCategory(categoryCheck);
            }
            else{
                brandCheck.setCategory(categoryRepository.save(brand.getCategory()));
            }
            return brandRepository.save(brandCheck);
        }
    }

    @Override
    public List<Brand> getBrandsByCategory(String categoryName) {
        return null;
    }

    @Override
    public List<Brand> getAllBrands() {
        return null;
    }

    @Override
    public void deleteBrand(int brandId) {

    }
}
