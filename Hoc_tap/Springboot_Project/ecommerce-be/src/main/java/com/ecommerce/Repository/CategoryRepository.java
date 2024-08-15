package com.ecommerce.Repository;

import com.ecommerce.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryName(String categoryName);

    Category findByCategoryName(String categoryName);

    Optional<Category> findById(Long categoryId);
}
