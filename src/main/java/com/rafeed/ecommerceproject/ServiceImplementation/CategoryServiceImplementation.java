package com.rafeed.ecommerceproject.ServiceImplementation;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityAlreadyExistsException;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.Brand;
import com.rafeed.ecommerceproject.Entity.Category;
import com.rafeed.ecommerceproject.Repository.BrandRepository;
import com.rafeed.ecommerceproject.Repository.CategoryRepository;
import com.rafeed.ecommerceproject.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository,
                                         BrandRepository brandRepository) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public Category saveCategory(Category category) throws EntityAlreadyExistsException {

        //check whether the requested category already exists in the database.
        //if the category already exists, then throw an exception.
        //else save the category
        Category categoryCheck = categoryRepository.getCategoryByCategoryName(category.getCategoryName());
        if(categoryCheck != null){
            throw new EntityAlreadyExistsException("Entity Already exists!");
        }
        else{
            categoryCheck = categoryRepository.save(category);
        }
        return categoryCheck;
    }

    @Override
    public Category getCategoryById(int categoryId) throws EntityNotfoundException {

        //check if the requested category is available in the database or not
        //if unavailable then throw an exception
        //else return value
        Category categoryCheck = categoryRepository.getCategoryByCategoryId(categoryId);
        if(categoryCheck == null){
            throw new EntityNotfoundException("Entity Not Found!!!");
        }
        return categoryCheck;
    }

    @Override
    public Category getCategoryByName(String categoryName) throws EntityNotfoundException {

        //check if the requested category is available in the database or not
        //if unavailable then throw an exception
        //else return value
        Category categoryCheck = categoryRepository.getCategoryByCategoryName(categoryName);
        if(categoryCheck == null){
            throw new EntityNotfoundException("Entity Not Found!!!");
        }
        return categoryCheck;
    }

    @Override
    public Category updateCategory(int categoryId, Category category) throws EntityNotfoundException {

        //check if the requested category is available in the database or not
        //if unavailable then throw an exception
        //else update value
        Category categoryCheck = categoryRepository.getCategoryByCategoryId(categoryId);
        if(categoryCheck == null){
            throw new EntityNotfoundException("Entity Not Found!!!");
        }
        else{
            categoryCheck.setCategoryName(category.getCategoryName());
            return categoryRepository.save(categoryCheck);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(int categoryId) throws EntityNotfoundException {

        //check if the requested category is available in the database or not
        //if unavailable then throw an exception
        //else delete value
        Category categoryCheck = categoryRepository.getCategoryByCategoryId(categoryId);
        if(categoryCheck == null){
            throw new EntityNotfoundException("Entity Not Found!!!");
        }
        categoryRepository.delete(categoryCheck);
    }
}
