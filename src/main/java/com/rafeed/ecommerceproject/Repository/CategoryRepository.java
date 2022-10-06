package com.rafeed.ecommerceproject.Repository;

import com.rafeed.ecommerceproject.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    //get category by id
    Category getCategoryByCategoryId(int categoryId);

    //get category by name
    Category getCategoryByCategoryName(String categoryName);
}
