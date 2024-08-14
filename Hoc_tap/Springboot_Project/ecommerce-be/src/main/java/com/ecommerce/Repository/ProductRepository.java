package com.ecommerce.Repository;

import com.ecommerce.Entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductItem, Long> {

}
