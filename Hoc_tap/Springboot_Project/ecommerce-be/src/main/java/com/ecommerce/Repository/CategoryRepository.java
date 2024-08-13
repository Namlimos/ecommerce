package com.ecommerce.Repository;

import com.ecommerce.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByCategoryName(String categoryName);

    Category findByCategoryName(String categoryName);
}
