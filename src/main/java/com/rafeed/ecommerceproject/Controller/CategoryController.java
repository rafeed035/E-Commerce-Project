package com.rafeed.ecommerceproject.Controller;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityAlreadyExistsException;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.Category;
import com.rafeed.ecommerceproject.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/saveCategory")
    public Category saveCategory(@RequestBody Category category) throws EntityAlreadyExistsException {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/getCategoryById")
    public Category getCategoryById(@RequestParam int categoryId) throws EntityNotfoundException {
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/getCategoryByName")
    public Category getCategoryByName(@RequestParam String categoryName) throws EntityNotfoundException {
        return categoryService.getCategoryByName(categoryName);
    }

    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PutMapping("/updateCategory")
    public Category updateCategory(int categoryId, Category category) throws EntityNotfoundException {
        return categoryService.updateCategory(categoryId, category);
    }

    @DeleteMapping("/deleteCategory")
    public String deleteCategory(int categoryId) throws EntityNotfoundException {
        categoryService.deleteCategory(categoryId);
        return "Deleted";
    }
}
