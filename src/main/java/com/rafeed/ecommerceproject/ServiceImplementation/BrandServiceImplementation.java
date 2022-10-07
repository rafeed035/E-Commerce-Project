package com.rafeed.ecommerceproject.ServiceImplementation;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityAlreadyExistsException;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.Brand;
import com.rafeed.ecommerceproject.Entity.Category;
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
    public Brand saveBrand(Brand brand) throws EntityAlreadyExistsException, EntityNotfoundException {

        //check whether the brand already exists in the database or not
        //if already exists, then throw an exception
        //else save the entity

        //check if the category already exists in the category table
        String categoryName = brand.getCategory().getCategoryName();
        Category categoryCheck = categoryRepository.getCategoryByCategoryName(categoryName);
        if(categoryCheck != null){
            brand.setCategory(categoryCheck);
        }
        else{
            throw new EntityNotfoundException("Category not found!");
        }

        //check if the brand with the specific category already exists in the brand table
        List<Brand> brands = brandRepository.getBrandsByCategory(brand.getCategory());
        Brand brandNew = brand;
        if(brands.size() > 0){
            for(int i=0; i<brands.size(); i++){
                if(brands.get(i).getBrandName().equals(brand.getBrandName())){
                    throw new EntityAlreadyExistsException("Entity Already exists");
                }
                else{
                    brandNew = brand;
                }
            }
        }
        else{
            brandNew = brand;
        }
        return brandRepository.save(brandNew);
    }

    @Override
    public Brand getBrandById(int brandId) throws EntityNotfoundException {

        //check whether the brand already exists in the database or not
        //if it does not exist, throw exception
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
        //if it does not exist, throw exception
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
    public Brand updateBrand(int brandId, Brand brand) throws EntityNotfoundException, EntityAlreadyExistsException {

        //check whether the brand already exists in the database or not
        //if it does not exist, throw exception
        //else update entity
        Brand brandCheck = brandRepository.getBrandByBrandId( brandId);
        if(brandCheck == null){
            throw new EntityNotfoundException("Entity not found!!!");
        }
        else{
            String brandName = brand.getBrandName();
            Brand brandCheckWithName = brandRepository.getBrandByBrandName(brandName);
            if(brandCheckWithName != null){
                throw new EntityAlreadyExistsException("Entity Already Exists");
            }
            else{
                brandCheck.setBrandName(brand.getBrandName());
                //check if the category of the brand already exists in the database
                String categoryName = brand.getCategory().getCategoryName();
                Category categoryCheck = categoryRepository.getCategoryByCategoryName(categoryName);
                if(categoryCheck != null){
                    brandCheck.setCategory(categoryCheck);
                }
                else{
                    throw new EntityNotfoundException("Category does not exist!");
                }
            }
            return brandRepository.save(brandCheck);
        }
    }

    @Override
    public List<Brand> getBrandsByCategory(String categoryName) throws EntityNotfoundException {
        //check if the category of the brand already exists in the database
        Category categoryCheck = categoryRepository.getCategoryByCategoryName(categoryName);
        if(categoryCheck == null){
            throw new EntityNotfoundException("Entity not found!!!");
        }
        else{
            return brandRepository.getBrandsByCategory(categoryCheck);
        }
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public void deleteBrand(int brandId) throws EntityNotfoundException {

        //check whether the brand already exists in the database or not
        //if it does not exist, throw exception
        //else delete entity
        Brand brandCheck= brandRepository.getBrandByBrandId(brandId);
        if(brandCheck == null){
            throw new EntityNotfoundException("Entity not found!!!");
        }
        else{
            brandRepository.delete(brandCheck);
        }
    }
}
