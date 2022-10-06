package com.rafeed.ecommerceproject.Service;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityAlreadyExistsException;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category) throws EntityAlreadyExistsException;
    Category getCategoryById(int categoryId) throws EntityNotfoundException;
    Category getCategoryByName(String categoryName) throws EntityNotfoundException;
    Category updateCategory(int categoryId, Category category) throws EntityNotfoundException;
    List<Category> getAllCategories();
    void deleteCategory(int categoryId) throws EntityNotfoundException;
}
