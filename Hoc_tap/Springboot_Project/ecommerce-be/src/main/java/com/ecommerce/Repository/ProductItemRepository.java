package com.ecommerce.Repository;

import com.ecommerce.Entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

}
